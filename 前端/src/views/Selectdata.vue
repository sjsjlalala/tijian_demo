<template>
    <!-- 总容器 -->
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>选择体检日期</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        
  <div class="demo-date-picker">
    <div class="block">
      <span class="demonstration">请选择体检日期</span>
      <el-date-picker
        v-model="value"
        type="date"
        placeholder="Pick a day"
        :disabled-date="disabledDate"
        size="default"
      />
    </div>
  </div>

<div class="center-div">
<el-tooltip placement="top" v-model="remain">
    <template #content> multiple lines<br />second line </template>
    <el-button>剩余人数：{{remain}}</el-button>
  </el-tooltip>
</div>
        <div class="bottom-btn">
            <div></div>
            <div @click="Toconfirmorder">下一步</div>
        </div>
        
        <div class="bottom-ban"></div>
    <Footer></Footer>
</div>
</template>

<script lang="ts" setup>
import Footer from "@/components/Footer.vue";
import { onMounted, reactive, toRefs, ref,watch } from "vue";
import { useRouter } from "vue-router";
import { setSessionStorage, getSessionStorage } from "../common.js";
import axios from "axios";
import { ElMessage } from 'element-plus'

const openCenter = () => {
  ElMessage({
    showClose: true,
    message: 'Centered text',
    center: true,
  })
}

const value = ref(Date.now())
const data = ref([])
const remain = ref(0)
const router = useRouter();
const Toconfirmorder = () => {
    router.push( "/confirmorder" );
}


watch(value, (newValue, oldValue) => {
  const timeDifferenceInDays = Math.floor((newValue - Date.now()) / (1000 * 60 * 60 * 24)); 

  // 确保timeDifferenceInDays是一个非负数且在data.value的有效索引范围内
  if(timeDifferenceInDays >= 0 && timeDifferenceInDays < data.value.length) 
    // 使用相差的天数作为索引更新remain.value
    remain.value = data.value[timeDifferenceInDays+1];
    // 假设data是从UI组件直接获取的未格式化的日期时间字符串
const selectedDate = new Date(newValue);

// 如果有必要，调整到用户所在时区（这里假设是东八区，即中国标准时间）
selectedDate.setHours(selectedDate.getHours() + 8); // 根据实际情况调整

// 然后使用formatDate方法或其他方式格式化并显示
const formattedDate = selectedDate.toISOString();

// 最后将格式化后的日期时间字符串存入sessionStorage
    setSessionStorage("data",formattedDate);
    console.log(remain.value)
});

const selectDate = () => {
    axios.get('https://d793567f-d18f-4c92-9f2b-fdee835b8611.mock.pstmn.io/data/get')
    .then(res => {
        if (res.data.code === 200) {
            data.value = res.data.data
            console.log(data.value)
        } 
    })
}

onMounted(() => {
    selectDate()
})

const disabledDate = (time: Date) => {
  const today = new Date();
  const oneDayInMillis = 86400000;
  const sevenDaysBefore = new Date(today.getTime() - oneDayInMillis * 1); // 7天前
  const sevenDaysAfter = new Date(today.getTime() + oneDayInMillis * 6); // 7天后

  return time < sevenDaysBefore || time > sevenDaysAfter;
}
</script>

<style scoped>
.center-div {
  display: flex;
  justify-content: center;
  /* 不需要设置align-items，以保持默认的垂直对齐 */
}
.demo-date-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}

.demo-date-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}

.demo-date-picker .block:last-child {
  border-right: none;
}

.demo-date-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
    /*********************** 总容器 ***********************/
.wrapper{
    width: 100%;
    height: 100%;
    background-color: #F9F9F9;
}

/*********************** header ***********************/
header{
    width: 100%;
    height: 15.7vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    top: 0;

    display: flex;
    align-items: center;
    justify-content: space-between;

    box-sizing: border-box;
    padding: 0 3.6vw;
}
header .fa{
    font-size: 8vw;
}

/*********************** footer ***********************/
footer{
    width: 100%;
    height: 14.2vw;
    box-sizing: border-box;
    border-top: solid 1px #E9E9E9;
    background-color: #FFF;

    position: fixed;
    left: 0;
    bottom: 0;
}
footer ul{
    width: 100%;
    height: 14.2vw;
    display: flex;
    align-items: center;
    justify-content: space-around;
}
footer ul li{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    font-size: 3vw;
    color: #999;

    user-select: none;
    cursor: pointer;
}
footer ul li .fa{
    font-size: 5vw;
}

/*********************** common样式 ***********************/
.top-ban{
    width: 100%;
    height: 15.7vw;
}
.bottom-ban{
    width: 100%;
    height: 14.2vw;
}

/*********************** section ***********************/
section{
    width: 82vw;
    margin: 0 auto;
    margin-top: 12vw;
}
section .date-box{
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 4.5vw;
    font-weight: 600;
}
section .date-box p{
    color: #000;
}
section .date-box i{
    color: #999;
    user-select: none;
    cursor: pointer;
}
section table{
    width: 100%;
    font-size: 3.8vw;
}
section table tr th{
    text-align: center;
    font-weight: 600;
    line-height: 12vw;
}

section ul{
    width: 100%;
    display: flex;
    flex-wrap: wrap;
}
section ul li{
    width: 14.28%;
    height: 12vw;
    font-weight: 600;
    color: #999;
}
section ul li p:first-child{
    width: 6vw;
    height: 6vw;
    margin: 0 auto;
    border-radius: 3vw;

    display: flex;
    justify-content: center;
    align-items: center;

    font-size: 4.2vw;

    user-select: none;
    cursor: pointer;
}
section ul li p:last-child{
    font-size: 3vw;
    font-weight: 300;
    text-align: center;
    user-select: none;
}

.fontcolor {
    color: #333;
}

.pselect{
    background-color: #FB902B;
    color: #FFF;
}

/*********************** bottom-btn ***********************/
.bottom-btn{
    width: 100%;
    height: 12vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    bottom: 14.2vw;

    display: flex;
}
.bottom-btn div:first-child{
    flex: 2;
}
.bottom-btn div:last-child{
    flex: 1;
    background-color: #117C94;
    text-align: center;
    line-height: 12vw;
    font-size: 5vw;
    color: #FFF;

    user-select: none;
    cursor: pointer;
}
</style>