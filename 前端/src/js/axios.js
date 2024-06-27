// axios.js
import axios from 'axios';
import { useRouter } from 'vue-router';
import { setSessionStorage,getSessionStorage } from '../common.js'

// 创建axios实例
const axiosInstance = axios.create({

  timeout: 10000,
});
// 请求拦截器
axiosInstance.interceptors.request.use(config => {
  // 从session storage获取token
  const token = getSessionStorage("token");
  console.log(token);
  
  // 如果token存在，则将其添加到Authorization头部
  if (token !== null) {
    config.headers.Authorization = `${token}`;
  }
  
  // 返回修改后的配置对象
  return config;
});
// 响应拦截器
axiosInstance.interceptors.response.use(response => {
  if(response.data.token !== null)
    setSessionStorage("token",response.data.token);

    console.log(getSessionStorage("token"))
    console.log(response.data.token)
  

  return response;
},  error => {
    console.log("l");
    if (error.response?.status === 302) {
      // 当检测到302状态码时，使用Vue Router进行跳转
      window.location.href = '/';
    } else {
      // 其他错误处理逻辑...
      return Promise.reject(error);
    }
});

export default axiosInstance;
