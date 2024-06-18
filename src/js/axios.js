// axios.js
import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();
// 创建axios实例
const axiosInstance = axios.create({
  baseURL: "http://localhost:5173/",
  timeout: 10000,
});
// 请求拦截器
axiosInstance.interceptors.request.use(config => {
    // 添加请求拦截逻辑
    return config;
  });
// 响应拦截器
axiosInstance.interceptors.response.use(response => {
  // 处理响应数据
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
