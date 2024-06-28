<template>

  <body>
    <!-- 总容器 -->
    <div class="wrapper">
      <h1>体检预约-登录</h1>

      <section v-if="state.flag===1">
        <div class="reg-box" @click="change">
          <p>密码登录</p>
        </div>
        <div class="input-box">
          <i class="fa fa-user-o"></i>

          <input type="text" v-model="state.users.userId" placeholder="输入手机号">
        </div>
        <div class="input-box">
          <i class="fa fa-lock"></i>

          <input type="password" v-model="state.users.code" placeholder="输入验证码">
          
            <div style="display: flex;align-items: center;">
              <el-button type="text" @click="getCode()" :disabled="state.isCountingDown"
                :type="state.isCountingDown ? 'text' : 'text'">{{ countdownText }}</el-button>
            </div>
          
        </div>
        <div class="reg-box">
          <p @click="toRegister()">还没有账号？注册</p>
          <p>忘记密码？</p>
        </div>
        <div class="button-box" @click="loginByCode()">登录</div>
      </section>

      <section v-else>
        <div class="reg-box" @click="change">
          <p>验证码登录</p>
        </div>
        <div class="input-box">
          <i class="fa fa-user-o"></i>

          <input type="text" v-model="state.users.userId" placeholder="输入手机号">
        </div>
        <div class="input-box">
          <i class="fa fa-lock"></i>

          <input type="password" v-model="state.users.password" placeholder="输入登录密码">
        </div>
        <div class="reg-box">
          <p @click="toRegister()">还没有账号？注册</p>
          <p @click="ForgetLogin()">忘记密码？</p>
        </div>
        <div class="button-box" @click="login()">登录</div>
      </section>

      <footer>
        <div>
          <div class="line"></div>
          <p>有疑问请联系客服</p>
          <div class="line"></div>
        </div>
        <p>4008-XXX-XXX</p>
      </footer>
    </div>

  </body>
</template>

<script setup>
import { reactive, watchEffect,onMounted, ref, computed, } from 'vue';
import { useRouter } from 'vue-router';
import { setSessionStorage, getSessionStorage } from '../common.js';
import Usericon from '../components/icons/Usericon.vue';
import Pwd from '../components/icons/Password.vue';
import { inject } from 'vue';

const router = useRouter();
const axios = inject('axios'); // 根据你的代码，这里使用inject注入axios
const state = reactive({
  flag: 0,
  users: {
    userId: '',
    password: '',
    code: ''
  },
  countdown: 0,
  isCountingDown: false
});

function login() {
  // 表单验证
  if (!state.users.userId) {
    alert('手机号码不能为空！');
    return;
  }
  if (!state.users.password) {
    alert('密码不能为空！');
    return;
  }

  // 登录
  axios.post('/api/users/login', state.users)
    .then((response) => {
      const code = response.data.code;
      const users = response.data.data;
      if (code === 200) {
        setSessionStorage('users', users);
        console.log(getSessionStorage('users'));
        router.push('/index');
      } else {
        const message = response.data.errorMsg;
        alert(message);
      }
    })
    .catch((error) => {
      console.error(error);
    });
}

function toRegister() {
  router.push('/register');
}

function change() {
  state.flag = state.flag === 0 ? 1 : 0;
}

// 由于<script setup>不支持this关键字，移除了change函数中的this.flag
const countdownText = computed(() => {
  // 计算属性用于动态显示按钮文本
  return state.isCountingDown ? `${state.countdown}s后重新发送` : '获取验证码';
}
)

const loginByCode = () => {
// 表单验证
if (!state.users.userId) {
    alert('手机号码不能为空！');
    return;
  }
  if (!state.users.code) {
    alert('验证码不能为空！');
    return;
  }

  // 登录
  axios.post('/api/users/loginByCode?phone='+state.users.userId+'&code='+state.users.code)
    .then((response) => {
      const code = response.data.code;
      const users = response.data.data;
      if (code === 200) {
        setSessionStorage('users', users);
        console.log(getSessionStorage('users'));
        router.push('/index');
      } else {
        const message = response.data.errorMsg;
        alert(message);
      }
    })
    .catch((error) => {
      console.error(error);
    });

}



const getCode = () => {
  axios.post('/api/users/code?phone='+state.users.userId)
   .then(response => {
     console.log(response.data.data);
   });
  if (state.isCountingDown) return; 
  state.isCountingDown = true;
  state.countdown = 60
  detime();
}

const ForgetLogin = () => {
  router.push('/forgetLogin');
}

const detime = ()=>{
  const timer = setInterval(() => {
    if (state.countdown<= 0) {
      clearInterval(timer);
      state.isCountingDown = false;
      state.countdown = 0;
      localStorage.removeItem('pcountdown'); // 倒计时结束，移除存储的计数
    } else {
      state.countdown--; 
      localStorage.setItem('pcountdown', new Date().getTime() + state.countdown * 1000 );
      

       // 每秒更新存储的计数
    }
  }, 1000);
}
  
  // 如果有需要在组件挂载后执行的逻辑，可以使用onMounted
  onMounted(() => {
    // 初始化逻辑...
    const storedCountdown = localStorage.getItem('pcountdown');
    

if (storedCountdown != null) {
  state.countdown = parseInt((storedCountdown - new Date().getTime())/1000); // 倒计时还剩多少秒

  console.log(state.countdown);
  if (state.countdown > 0) {
    state.isCountingDown = true;
    detime();
  }
}

  });
</script>

<style scoped>
@import url('https://unpkg.com/tailwindcss@^2.0/dist/tailwind.min.css');
/*********************** 总容器 ***********************/
.wrapper{
  width: 100%;
  height: 100%;
  background-image: linear-gradient(45deg,#266C9F,#266C9F,#7EB059);
  overflow: hidden;
}

/*********************** 标题部分 ***********************/
h1{
  text-align: center;
  color: #FFF;
  font-size: 9.5vw;
  font-weight: 500;
  margin-top: 13vh;
  margin-bottom: 3vh;
}

/*********************** 内容部分 ***********************/
section{
  width: 86vw;
  margin: 0 auto;
  background-color: #FFF;
  border-radius: 2.4vw;

  box-sizing: border-box;
  padding: 6vw;
}

section .input-box{
  width: 100%;
  height: 12vw;
  border: solid 1px #CCC;
  border-radius: 2vw;
  margin-top: 5vw;

  display: flex;
  align-items: center;
}
section .input-box i{
  margin: 0 3.6vw;
  font-size: 5.4vw;
  color: #CCC;
}
section .input-box input{
  border: none;
  outline: none;
  width: 120px;
}

section .reg-box{
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 5vw;
  margin-bottom: 2vw;
}
section .reg-box p{
  font-size: 3.3vw;
  color: #2D727E;
  user-select: none;
  cursor: pointer;
}

section .button-box{
  width: 100%;
  height: 13vw;
  border-radius: 2vw;
  background-color: #70B0BC;

  text-align: center;
  line-height: 13vw;
  font-size: 4.6vw;
  color: #FFF;
  letter-spacing: 1vw;

  user-select: none;
  cursor: pointer;
}

/*********************** footer部分 ***********************/
footer{
  width: 86vw;
  margin: 0 auto;
  margin-top: 73vw;
  font-size: 3.8vw;
  color: #FFF;
}
footer div{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
footer div .line{
  width: 22vw;
  height: 1px;
  background-color: #FFF;
}
footer > p{
  text-align: center;
  margin-top: 2vw;
}
</style>