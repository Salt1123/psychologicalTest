<template>
  <br />
  <div class="container">
    <div v-for="(STATION_NM_CHN, i) in data.dataList" :key="i">
      <div class="station-sign">
        <div class="station-line">
          <div class="line-circle" style="background-color: #0077c8; color: #fff">1</div>
        </div>
        <div class="station-name">{{ data.dataList[i].STATION_NM }}</div>
        <div class="station-name-foreign">{{ data.dataList[i].STATION_NM_CHN }}</div>
        <div class="station-name-foreign">{{ data.dataList[i].STATION_NM_ENG }}</div>
        <div class="station-name-foreign">{{ data.dataList[i].STATION_NM_JPN }}</div>
        <button class="button" @click="clickChartpage(data.dataList[i].STATION_NM)">
          환승객 현황
        </button>
        <button class="button" @click="emergencyPage(data.dataList[i].STATION_NM)">
          비상대피로
        </button>
      </div>
      <div v-if="data.dataList[i].ORIGIN !== ''" class="station-history">
        ※ {{ data.dataList[i].ORIGIN }}
      </div>
    </div>
  </div>
  <div>
    <paging :page="data.page" @onPage="onPage" v-if="data.dataList.length !== 0" />
  </div>
</template>

<script setup>
import router from '@/router/index.js'
import paging from '@/components/paging.vue'
import { inject, onMounted, ref } from 'vue'

const $axios = inject('$axios')
const data = ref({
  dataList: [],
  START_INDEX: 1,
  END_INDEX: 10,
  page: {
    total: '',
    page: 1,
    count: 10
  }
})

onMounted(() => {
  //  create()
})

const create = async () => {
  try {
    const response = await $axios.get(
      `https://test-api.cyber-i.com/svc/sjy/SubwayLine/SearchSTNBySubwayLineInfo?START_INDEX=${data.value.START_INDEX}&END_INDEX=${data.value.END_INDEX}&page=${data.value.page.page}`
    )
    data.value.dataList = response.data.SearchSTNBySubwayLineInfo.row
    data.value.page.total = response.data.SearchSTNBySubwayLineInfo.list_total_count
    data.value.page.page = response.data.OutBlock_3[0].PAGE || 1
  } catch (error) {
    console.error(error)
  }
}

const onPage = (page) => {
  data.value.page.page = page
  if (page === 1) {
    data.value.START_INDEX = 1
    data.value.END_INDEX = 10
  } else {
    data.value.START_INDEX = (page - 1) * 10 + 1
    data.value.END_INDEX = data.value.START_INDEX + 9
  }
  create()
}

const clickChartpage = (data) => {
  router.push({
    name: 'subwayChart',
    params: { subNM: data }
  })
}

const emergencyPage = (data) => {
  router.push({
    name: 'emergencyPage',
    params: { subNM: data }
  })
}
</script>

<style>
.container {
  width: 120%; /* 컨테이너 주위에 패딩을 추가합니다 */
}

.station-sign {
  background-color: #fff;
  width: 90%; /* 가로 길이를 더 길게 설정합니다. */
  margin: 50px auto;
  padding: 30px;
  border: 3px solid #0052a4;
  border-radius: 15px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 0;
}

.station-line {
  margin-right: 20px;
}

.line-circle {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: #0052a4;
  color: #fff;
  font-size: 22px;
  line-height: 40px;
  text-align: center;
  margin-right: 10px;
}

.station-name {
  font-size: 26px;
  font-weight: bold;
  margin-bottom: 10px;
}

.station-name-foreign {
  font-size: 16px;
  color: #888;
}

.button {
  background-color: #0052a4;
  color: #fff;
  border: none;
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 5px;
  margin-top: 10px;
  margin-right: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.button:hover {
  background-color: #003366;
}

.station-history {
  background-color: #f3f3f3;
  color: #333;
  font-size: 10px;
  border-radius: 10px;
  width: 90%;
  max-width: 1750px;
  margin: 5px auto;
  padding: 10px;
}

.small-text {
  font-size: 7px;
}
</style>
