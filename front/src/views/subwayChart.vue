<template>
  <div class="chartContainer">
    <div id="buttons">
      <button class="button" :class="{ 'activeButton': buttonColor === 'bar' }" @click="drawBarChart()">
        평일+주말
      </button>
      <button class="button" :class="{ 'activeButton': buttonColor === 'doughnut' }" @click="drawDoughnutChart()">
        총 환승객 수
      </button>
      <button class="button" :class="{ 'activeButton': buttonColor === 'line' }" @click="drawLineChart()">
        변동추이
      </button>
    </div>
    <div id="barchart_material" style="width: 1000px; height: 550px"></div>
  </div>
</template>

<style></style>

<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'



const props = defineProps({
  subNM: String
})

const data = ref({
  subNM: '',
  chartData: []
})


const getChartData = async () => {
  try {
    const response = await axios.get(
      'https://test-api.cyber-i.com/svc/sjy/SubwayLine/StationDayTrnsitNmpr?subNM=' + props.subNM
    )
    data.value.chartData = response.data.StationDayTrnsitNmpr.row
    drawBarChart()
  } catch (error) {
    console.log(error)
  }
}

const buttonColor = ref('bar');

const drawBarChart = () => {
  buttonColor.value = 'bar';
  const dataParam = data.value.chartData;
  google.charts.load('current', { packages: ['bar'] })
  google.charts.setOnLoadCallback(() => {
    var dataArray = [['연도', '평일', '토요일', '일요일', '주말평균']]
    dataParam.forEach((item) => {
      dataArray.push([item.SN, item.WKDAY, item.SATDAY, item.SUNDAY, item.WEEKENDAVG])
    })
    var data = google.visualization.arrayToDataTable(dataArray)
    var options = {
      chart: {
        title: props.subNM +" 평일, 주말 환승인원",
      },
      titleTextStyle: { 
        fontSize: 12, 
        color : '#696969',
      },
      bars: 'horizontal',
      hAxis: { format: 'decimal' },
      chartArea: {
            width: '70%', 
            height: '70%', 
            top: 250, 
            left: 100, 
            bottom: 50, 
            right: 100 
          },
    }
    var chart = new google.charts.Bar(document.getElementById('barchart_material'))
    chart.draw(data, google.charts.Bar.convertOptions(options))
  })
}


const drawDoughnutChart = () => {
  buttonColor.value = 'doughnut'; 
  const dataParam = data.value.chartData;
  google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var dataArray = [['SN', 'data']]
          dataParam.forEach((item) => {
            var SN = item.SN+'년';
            var data = item.WKDAY + item.SATDAY + item.SUNDAY;
            dataArray.push([SN, data])
          })
        var data = google.visualization.arrayToDataTable(dataArray)
        var options = {
          title: props.subNM + ' 연도별 총 환승인원',
          titleTextStyle: { 
          fontSize: 12, 
          color : '#696969',
          },
          pieHole: 0.41, 
          chartArea: {
            chartArea: {
            width: '70%', 
            height: '50%', 
            left: 100, 
            bottom: 50, 
            right: 100 
          },
          },
          pieSliceText: 'data',
        };
        var chart = new google.visualization.PieChart(document.getElementById('barchart_material'));
        chart.draw(data, options);    
      }
}


const drawLineChart = () => {
  buttonColor.value = 'line';
  var dataParam = data.value.chartData;
  google.charts.load('current', {'packages':['Line']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var dataArray = [['해당연도', '평일', '토요일', '일요일']]
          const reverse = dataParam.reverse();
          reverse.forEach((item) => {
            var SN = item.SN+'년';
            dataArray.push([SN, item.WKDAY, item.SATDAY, item.SUNDAY])
          })
        var data = google.visualization.arrayToDataTable(dataArray)
        var options = {
          title: props.subNM + ' 요일별  환승인원 변동 추이',
          curveType: 'function',
          legend: { position: 'bottom' },
          titleTextStyle: { 
          fontSize: 12, 
          color : '#696969',
        },
        chartArea: {
            width: '70%', 
            height: '70%', 
            top: 50, 
            left: 100, 
            bottom: 50,
            right: 100 
          },

        };
        var chart = new google.visualization.LineChart(document.getElementById('barchart_material'));
        chart.draw(data, options);    
        dataParam.reverse()
      }
}


onMounted(() => {
  getChartData()
})
</script>

<style>
.chartContainer {
  padding: 100px 30px 30px 30px;
}


#pieTitle{
  font-weight: bold;
  font-size: 20px;
  color :#0052a4
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

#buttons {
  margin-bottom: 30px;
  
}

.activeButton {
  background-color: #009688; /* 활성 버튼 색상 */
}

</style>
