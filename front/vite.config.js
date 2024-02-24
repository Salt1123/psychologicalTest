import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  /*  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  } */
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      // '/boot'로 시작하는 요청은 스프링 부트의 8080 포트로 프록시됩니다.
      '/boot': {
        target: 'http://localhost:8080',
        changeOrigin: true, // 요청 헤더의 호스트를 타깃 URL의 호스트로 변경합니다.
        rewrite: (path) => path.replace(/^\/boot/, '') // 요청 경로에서 '/boot'를 제거합니다.
      }
    }
  }
})
