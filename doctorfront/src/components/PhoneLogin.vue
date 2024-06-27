<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>体检系统医生端</span>
      </div>
    </template>
    <div class="text item">

      <el-form ref="formRef" :model="state.loginForm" label-width="80px" :rules="rules">
        <el-form-item label="手机号码" prop="docCode">
          <el-input v-model="state.loginForm.docCode"></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="password">
          <div style="display: flex;align-items: center;">
            <el-input v-model="state.loginForm.code" type="text" style="width: 130px;"></el-input>
            <el-button type="text" @click="getCode()" :disabled="state.isCountingDown"
              :type="state.isCountingDown ? 'text' : 'text'">{{ countdownText }}</el-button>
          </div>
        </el-form-item>
        <div class="button1">
          <el-button type="primary" size="medium" @click="login">登录</el-button>
        </div>
      </el-form>

      <div class="button-group">
        <el-button type="text" plain size="small" @click="router.push('/')">账号密码</el-button>
        <el-button type="text" plain size="small" @click="router.push('/ForgetLogin')">忘记密码</el-button>
      </div>

    </div>
  </el-card>
</template>

<script setup>
import { reactive, onMounted, ref, computed,onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { setSessionStorage } from '../common.js';

import { inject } from 'vue';

const axios = inject('axios');


const router = useRouter();

const state = reactive({
  loginForm: {
    docCode: '',
    code: '',
  },
  countdown: 0,
  isCountingDown: false
});




const rules = {
  docCode: [
    { required: true, message: '手机号码不能为空', trigger: 'blur' }
  ],
  code: [
    { required: true, message: '验证码不能为空', trigger: 'blur' }
  ]
};

const login = () => {
  // 表单验证逻辑现在由Element Plus自动处理，无需手动alert
  axios.post('/api/doctor/loginByCode?phone='+state.loginForm.docCode+'&code='+state.loginForm.code)
        .then(response => {
          const doctor = response.data.data;
          if (doctor) {
            setSessionStorage('doctor', doctor);
            router.push('/ordersList');
          } else {
            alert('手机号码或验证码错误！');
          }
        })
        .catch(error => {
          console.error(error);
        });
};

// 定义formRef
const formRef = ref(null);

const countdownText = computed(() => {
  // 计算属性用于动态显示按钮文本
  return state.isCountingDown ? `${state.countdown}s后重新发送` : '获取验证码';
}
)


const getCode = () => {
  axios.post('/api/doctor/code?phone='+state.loginForm.docCode)
   .then(response => {
     console.log(response.data.data);
   });
  if (state.isCountingDown) return; 
  state.isCountingDown = true;
  state.countdown = 60
  detime();
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
<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}


.box-card {
  width: 400px;
  margin: 0 auto;
  margin-top: 150px;
  place-items: center;
  /* 同时实现水平和垂直居中 */

}


.button-group {
  display: flex;
  justify-content: space-between;
  height: 20px;
}

.button1 {
  display: flex;
  justify-content: center;
}
</style>