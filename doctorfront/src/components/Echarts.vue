<template>

    <body>
        <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
        <div id="main"></div>
        <div id="left"></div>
        <div id="right"></div>
    </body>
</template>

<script setup>
import * as echarts from 'echarts';
import { reactive, onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { setSessionStorage } from '../common.js';
import { inject } from 'vue';


const axios = inject('axios');
// 基于准备好的dom，初始化echarts实例

// 绘制图表
const echartMount = async () => {
    var myChart = echarts.init(document.getElementById('main'), 'dark');
    var leftChart = echarts.init(document.getElementById('left'), 'dark');
    var rightChart = echarts.init(document.getElementById('right'), 'dark');
    var maleList = {
        setName: [],
        setCount: []
    }
    var femaleList = {
        setName: [],
        setCount: []
    }
    var allmaleList = {
        setName: [],
        setCount: []
    }
    //获取套餐数量
    await axios.get('/api/orders/getCount').then(res => {
        console.log(res.data.data.length);
        for (var i = 0; i < res.data.data.length; i++) {
            console.log(res.data.data[i].length);
            for (var j = 0; j < res.data.data[i].length; j++) {
                if (i == 0) {
                    maleList.setName.push(res.data.data[i][j].seatmlName);
                    maleList.setCount.push(res.data.data[i][j].seatmlCount);
                    console.log(maleList.setName);
                    console.log(res.data.data[i][j].seatmlName);
                }
                if (i == 1) {
                    femaleList.setName.push(res.data.data[i][j].seatmlName);
                    femaleList.setCount.push(res.data.data[i][j].seatmlCount);
                } if (i == 2) {
                    allmaleList.setName.push(res.data.data[i][j].seatmlName);
                    allmaleList.setCount.push(res.data.data[i][j].seatmlCount);
                }
            }
        }
        console.log(maleList.setCount);
        console.log(maleList.setName);
    })

    var option = {
        title: {
            text: '男士体检套餐统计'
        },
        tooltip: {},
        xAxis: {
            data: maleList.setName,
            axisLabel: {
                textStyle: {
                    fontSize: 10
                }
            }
        },
        yAxis: {},
        series: [
            {
                name: '销量',
                type: 'bar',
                data: maleList.setCount
            }
        ]
    }

    var option2 = {
        title: {
            text: '女士体检套餐统计'
        },
        tooltip: {},
        xAxis: {
            data: femaleList.setName,
            axisLabel: {
                textStyle: {
                    fontSize: 10
                }
            }
        },
        yAxis: {},
        series: [
            {
                name: '销量',
                type: 'bar',
                data: femaleList.setCount
            }
        ]
    }
    var option3 = {
        title: {
            text: '总体检套餐数量分布统计'
        },
        tooltip: {},
        xAxis: {
            data: allmaleList.setName,
            axisLabel: {
                textStyle: {
                    fontSize: 8
                }
            }
        },
        yAxis: {},
        series: [
            {
                name: '销量',
                type: 'bar',
                data: allmaleList.setCount
            }
        ]
    }
    myChart.setOption(option);
    leftChart.setOption(option2);
    rightChart.setOption(option3);
    myChart.resize({
        width: 400,
        height: 500
    });
    leftChart.resize({
        width: 400,
        height: 500
    });
    rightChart.resize({
        width: 400,
        height: 500
    });
}
onMounted(() => {
    echartMount();
})

</script>