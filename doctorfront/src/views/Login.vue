<template>
  <div id="building" >
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>健康守护360°智慧体检医生端</span>
      </div>
    </template>
    <div class="text item">
      <el-form ref="formRef" :model="state.loginForm" label-width="80px" :rules="rules">
        <el-form-item label="医生编码" prop="docCode">
          <el-input v-model="state.loginForm.docCode"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" prop="password">
          <el-input v-model="state.loginForm.password" type="password"></el-input>
        </el-form-item>
        <div class="button1">
          <el-button type="primary" size="medium" @click="submitForm">登录</el-button>
        </div>
      </el-form>
      <div class="button-group">
        <el-button type="text" plain size="small" @click="router.push('/PhoneLogin')">验证码登录</el-button>
        <el-button type="text" plain size="small" @click="router.push('/ForgetLogin')">忘记密码</el-button>
      </div>
    </div>
  </el-card>
</div>
</template>

<script setup>
import { reactive, onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { setSessionStorage } from '../common.js';
import { inject } from 'vue';

const axios = inject('axios');

const router = useRouter();

const state = reactive({
  loginForm: {
    docCode: '',
    password: '',
  },
});

const rules = {
  docCode: [{ required: true, message: '医生编码不能为空', trigger: 'blur' }],
  password: [{ required: true, message: '密码不能为空', trigger: 'blur' }],
};

const formRef = ref(null);

const submitForm = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      login();
    } else {
      return false;
    }
  });
};

const login = () => {
  axios.post('/api/doctor/login', state.loginForm)
    .then(response => {
      console.log(response.data.success)
      if (response.data.success == true) {
        const doctor = response.data.data;
        setSessionStorage('doctor', doctor);
        router.push('/ordersList');
      } else {
        alert('医生编码或密码不正确！');
      }
    })
    .catch(error => {
      console.error(error);
    });
};

onMounted(() => {
  // 初始化逻辑...
});
</script>

<style scoped>
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
    width:400px;
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
  .button1{
    display: flex;
    justify-content: center;
  }

#building{
background:url("../assets/R.png");
width:100%;			
height:100%;		
position:fixed;
background-size:100% 100%;
}

</style>