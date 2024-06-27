import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    
    proxy:{
      '/api': {   //  拦截以 /api 开头的接口
        target: 'http://localhost:8081',//设置你调用的接口域名和端口号 别忘了加http
        changeOrigin: true,    //这里true表示实现跨域
        ws:true,
        secure: false, // 如果是https接口，需要配置这个参数
        rewrite: (path) => path.replace(/^\/api/, '') // 重写请求
      },
}

  }
})
