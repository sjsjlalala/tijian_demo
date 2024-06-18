// axios.js
import axios from 'axios';
import { useRoute } from 'vue-router';
const route = useRoute();
// 创建axios实例
const axiosInstance = axios.create({
  baseURL: process.env.VUE_APP_API_BASE_URL,
  timeout: 10000,
});

// 请求拦截器
axiosInstance.interceptors.request.use(config => {
  // 添加请求拦截逻辑，例如添加token
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, error => {
  // 处理请求错误
  return Promise.reject(error);
});

// 响应拦截器
axiosInstance.interceptors.response.use(response => {
  // 处理响应数据，例如对返回的数据做统一处理
  return response.data;
}, error => {
  // 处理响应错误，例如错误提示、重定向等
  if (error.response?.status === 302) {
      
    // 处理未授权的情况
  }
  return Promise.reject(error);
});

export default axiosInstance;
