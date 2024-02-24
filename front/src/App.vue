<script setup></script>

<template>
  <div class="top-bar">
    <!-- 마크 -->
    <img src="../public/Ci.png" alt="마크" height="30" />
    <!-- 로그인 input -->
    <div>
      <input type="text" v-model="loginData.id" class="login-input" placeholder="아이디" />
      <input type="password" v-model="loginData.pw" class="login-input" placeholder="비밀번호" />
      <button class="login-btn" @click="goSpringLogin()">로그인!!!</button>
    </div>
  </div>

  <router-view></router-view>
</template>

<script setup>
import { inject, onMounted, ref } from 'vue'
const $axios = inject('$axios')

const loginData = ref({
  id: '',
  pw: ''
})

const goSpringLogin = async () => {
  const requestData = {
    id: loginData.value.id,
    pw: loginData.value.pw
  }

  try {
    console.log(requestData.id)

    const response = await $axios.post('/boot/login', requestData, {
      headers: {
        'Content-Type': 'application/json' // 데이터 형식을 JSON으로 지정합니다.
      }
    })

    console.log(response.data) // 서버에서 받은 응답을 출력합니다.
  } catch (error) {
    console.error('Error:', error)
  }
}
</script>

<style>
/* 상단바 스타일 */
.top-bar {
  background-color: #f3f3f3; /* 연한 회색 */
  color: #333; /* 텍스트 색상을 회색으로 설정 */
  display: flex;
  justify-content: space-between;
  width: 100%; /* 너비를 100%로 설정하여 좌우로 꽉 차게 만듦 */
  margin-top: 0; /* body의 margin 제거 */
  position: fixed; /* 화면에 고정 */
  top: 0; /* 화면의 맨 위에 배치 */
  width: 100%; /* 가로 폭을 100%로 설정하여 전체 너비를 차지 */
  background-color: #f3f3f3; /* 배경색 설정 */
  padding: 10px 20px; /* 내부 여백 설정 */
  z-index: 1000; /* 다른 요소 위에 표시 */
}

/* 로그아웃 스타일 */
.login-btn {
  background-color: #0052a4; /* 1호선 파란색 */
  color: #fff;
  border: none;
  padding: 8px 15px;
  border-radius: 5px;
  cursor: pointer;
  margin-left: 10px; /* 입력 필드와 버튼 사이의 여백 */
}

.login-btn:hover {
  background-color: #003366; /* darker shade of blue */
}

/* 로그인 input 스타일 */
.login-input {
  border: 1px solid #ccc; /* 회색 테두리 */
  padding: 5px;
  border-radius: 5px;
  margin-left: 3px;
}
</style>
