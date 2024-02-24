<template>
  <div class="picContainer">
    <br>
    <div class="pic_title">&nbsp;&nbsp;<span id="text1">미세먼지 수치 : </span>{{ data.PMq }}&nbsp;&nbsp;&nbsp;&nbsp;<span id="text2">기준시간 : </span>{{ data.CHECKDATE }}</div>
    <div id="station-sign">
      <div id="pic">
      <img :src="data.url" id="pic2" />
    </div>
    </div>
    <br>
   
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'

const props = defineProps({
  subNM: String
})

const data = ref({
  url: '',
  sub_NM: '',
  CHECKDATE: '',
  PMq: ''
})

const created = () => {
  axios
    .get(
      'https://test-api.cyber-i.com/svc/sjy/SubwayLine/SmrtEmergerncyGuideImg?subNM=' + props.subNM
    )
    .then((response) => {
      data.value.url = response.data.OutBlock_4[0].IMG_URL
      data.value.sub_NM = response.data.OutBlock_4[0].STN_NAME
      data.value.CHECKDATE = response.data.OutBlock_4[0].CHECKDATE
      data.value.PMq = response.data.OutBlock_4[0].PMq
    })
    .catch((error) => {
      console.log(error)
    })
}

onMounted(() => {
  created()
})
</script>

<style>
.picContainer {
  max-width: 1880px;
  padding : 5% 5% 10% 10%
}

#pic {
  max-width: 1880px;
  height: 70%;
  padding: auto;
  margin : auto;
  text-align: center;
}
#pic2 {
  width: 100%;
  height: 100%;
  display: inline-block; /* 이미지를 인라인 블록 요소로 만들어서 텍스트 흐름에 따라 가운데 정렬 */
  margin: 0 auto;
  padding: 10px;
}


.pic_title{
  font-weight: bold;
  font-size: 20px;
  color :#0052a4;
  margin-left: 5%;
}

#text1, #text2 {
  color : black

}

#station-sign {
  background-color: #fff;
  width: 90%; /* 가로 길이를 더 길게 설정합니다. */
  margin: 30px auto;
  border: 3px solid #0052a4;
  border-radius: 15px;
  margin-bottom: 0;
}




</style>
