<template>

  <body>
    <!-- 总容器 -->
    <div class="wrapper">
      <h1>体检预约-登录</h1>

      <section >
        <svg @click="back()" t="1719501406027" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2913" width="16" height="16"><path d="M744.3372563 1017.13692445c11.1289837 0 22.2701037-4.2477037 30.76551111-12.74311112 16.99081482-16.99081482 16.99081482-44.54020741 0-61.51888592L345.02883555 512.80099555 775.10276741 82.7392c16.99081482-16.97867852 16.99081482-44.54020741 0-61.51888592-16.99081482-16.99081482-44.52807111-16.99081482-61.51888593 0L252.74443852 482.04762075a43.51469037 43.51469037 0 0 0 0 61.53102222l460.83944296 460.81517036c8.48327111 8.49540741 19.62439111 12.74311111 30.75337482 12.74311112z" fill="#2c2c2c" p-id="2914"></path></svg>
        <el-form ref="formRef" :model="state.loginForm" label-width="80px" :rules="rules">
         <el-form-item label="手机号码" prop="docCode">
           <el-input v-model="state.loginForm.docCode"  ></el-input>
         </el-form-item>
         <el-form-item label="新密码" prop="password1" >
           <el-input v-model="state.loginForm.password1" ></el-input>
         </el-form-item>
         <el-form-item label="再次输入" prop="password2" >
           <el-input v-model="state.loginForm.password2" ></el-input>
         </el-form-item>
         <el-form-item label="验证码">
          <div style="display: flex;align-items: center;">
            <el-input v-model="state.loginForm.code" type="text" style="width: 100px;"></el-input>
           <el-button type="text" @click="getCode()"
           :disabled="state.isCountingDown"
              :type="state.isCountingDown ? 'text' : 'text'">{{countdownText}}</el-button>
          </div>
         </el-form-item>
         
         <div class="button1">
           <el-button type="primary" size="medium" @click="login">登录</el-button>
         </div>
       </el-form>
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
import { reactive, onMounted,ref, computed ,onUnmounted} from 'vue';
  import { useRouter } from 'vue-router';
  import { setSessionStorage } from '../common.js';
  
  import { inject } from 'vue';

const axios = inject('axios');
  
  const router = useRouter();
  
  const state = reactive({
    loginForm: {
      docCode: '',
      password1: '',
      password2:'',
      code:''
    },
    countdown:0,
    isCountingDown:false
  });

  const rules = {
  docCode: [
    { required: true, message: '手机号码不能为空', trigger: 'blur' }
  ],
  password1: [
    { required: true, message: '新密码不能为空', trigger: 'blur' }
  ],
  password2: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    { 
      validator: (rule, value, callback) => {
        if (value !== state.loginForm.password1) {
          callback(new Error('两次输入的密码不一致'));
        } else {
          callback();
        }
      },
      trigger: 'blur'
    }
  ],
  code: [
    { required: true, message: '验证码不能为空', trigger: 'blur' }
  ]
};

  const login = () => {
    if (!state.loginForm.docCode) {
      alert('手机号码不能为空！');
      return;
    }
    if (!state.loginForm.code) {
      alert('验证码不能为空！');
      return;
    }
  
    axios.post('/api/users/changepwd?phone='+state.loginForm.docCode+'&newpwd='+state.loginForm.password1+'&code='+state.loginForm.code)
      .then(response => {
        const doctor = response.data.data;
        if (doctor) {
          setSessionStorage('users', doctor);
          router.push('/index');
        } else {
          alert('手机号码或验证码错误！');
        }
      })
      .catch(error => {
        console.error(error);
      });
  };

  const countdownText = computed(() => {
  // 计算属性用于动态显示按钮文本
  return state.isCountingDown ? `${state.countdown}s后重新发送` : '获取验证码';
}
)

// 从localStorage恢复倒计时状态


const getCode = () => {
  axios.post('/api/users/code?phone='+state.loginForm.docCode)
   .then(response => {
     console.log(response.data.data);
   });
  if (state.isCountingDown) return; 
  state.isCountingDown = true;
  state.countdown = 60
  detime();
}

const back=()=>{
  router.push('/');
}

const detime = ()=>{
  const timer = setInterval(() => {
    if (state.countdown<= 0) {
      clearInterval(timer);
      state.isCountingDown = false;
      state.countdown = 0;
      localStorage.removeItem('countdown'); // 倒计时结束，移除存储的计数
    } else {
      state.countdown--; 
      localStorage.setItem('countdown', new Date().getTime() + state.countdown * 1000 );
      

       // 每秒更新存储的计数
    }
  }, 1000);
}
  
  // 如果有需要在组件挂载后执行的逻辑，可以使用onMounted
  onMounted(() => {
    // 初始化逻辑...
    const storedCountdown = localStorage.getItem('countdown');
    

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