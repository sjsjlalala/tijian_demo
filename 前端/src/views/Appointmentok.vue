<template>
    <!-- 总容器 -->
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>您的订单明细</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <div class="title">
                <p>体检人信息</p>
            </div>
            <table>
                <tr>
                    <td>姓名:</td>
                    <td>{{value.realName}}</td>
                </tr>
                <tr>
                    <td>证件号码:</td>
                    <td>{{value.identityCard}}</td>
                </tr>
                <tr>
                    <td>出生日期:</td>
                    <td>{{value.birthday}}</td>
                </tr>
                <tr>
                    <td>手机号码:</td>
                    <td>{{value.userId}}</td>
                </tr>
            </table>
            <div class="title">
                <p>体检日期</p>
            </div>
            <table>
                <tr>
                    <td>{{data}}</td>
                </tr>
            </table>
            <div class="title">
                <p>体检机构</p>
            </div>
            <table>
                <tr>
                    <td v-if="hpId==1" colspan="2">{{hospital[0].name}}</td>
                    <td v-if="hpId==2" colspan="2">{{hospital[1].name}}</td>
                </tr>
                <tr>
                    <td>营业时间:</td>
                    <td v-if="hpId==1">{{hospital[0].businessHours}}</td>
                    <td v-if="hpId==2">{{hospital[1].businessHours}}</td>
                </tr>
                <tr>
                    <td>采血截止:</td>
                    <td v-if="hpId==1">{{hospital[0].deadline}}</td>
                    <td v-if="hpId==2">{{hospital[1].deadline}}</td>
                </tr>
                <tr>
                    <td>机构电话:</td>
                    <td v-if="hpId==1">{{hospital[0].telephone}}</td>
                    <td v-if="hpId==2">{{hospital[1].telephone}}</td>
                </tr>
                <tr>
                    <td>机构地址:</td>
                    <td v-if="hpId==1">{{hospital[0].address}}</td>
                    <td v-if="hpId==2">{{hospital[1].address}}</td>
                </tr>
            </table>
            <div class="title">
                <p>套餐类型</p>
            </div>
            <table>
                <tr>
                    <td v-if="smId==1">普通男士客户-基础套餐</td>
                    <td v-if="smId==2">普通男士客户-肾病套餐</td>
                    <td v-if="smId==3">普通男士客户-肝病套餐</td>
                    <td v-if="smId==4">普通女士客户-基础套餐</td>
                    <td v-if="smId==5">普通女士客户-肾病套餐</td>
                    <td v-if="smId==6">普通女士客户-肝病套餐</td>
                </tr>
            </table>
        </section>
        
        <div class="bottom-btn">
            <div class="first">实付款: <span>￥350</span></div>
            <div class="last" @click=Toappointmentlist>确认</div>
        </div>

        <div class="bottom-ban"></div>
    <Footer></Footer>
</div>
</template>

<script setup>
import { onMounted, reactive, toRefs, ref,watch } from "vue";
import { useRouter } from "vue-router";
import { setSessionStorage, getSessionStorage } from "../common.js";
import Footer from "@/components/Footer.vue";
import { inject } from 'vue';

const axios = inject('axios');

const router = useRouter();

const value = getSessionStorage("users");

function simplifyDateString(dateTimeStr) {
  const date = new Date(dateTimeStr);
  const month = date.getMonth() + 1; // getMonth()返回的月份是从0开始的，所以需要+1
  const day = date.getDate();
  const year = date.getFullYear();
  
  // 使用padStart方法保证月份和日期始终为两位数，但根据你的需求，月份不需要前导零
  return `${year}-${month}-${day}`.replace(/^0/, ''); // 移除月份前可能出现的0
}

const appointment = getSessionStorage("appointment");

const data = appointment.orderDate;

const hospital=getSessionStorage("hospitals");

const hpId = appointment.hpId;

const smId = appointment.smId;

const now = new Date().toLocaleDateString();

const orderDate = getSessionStorage("data");

const user = getSessionStorage("users");


function Toappointmentlist() {
    // axios.post("/api/order/confirm", {
    //     orderDate: orderDate,
    //     userId: user.userId,
    //     hpId: hpId,
    //     smId: smId
    // }).then(res => {
    //     if (res.data.code === 200) {
    router.push("/appointmentlist");
    //     }
    //     else {
    //         alert(res.data.msg);
    //     }
    // })

}


</script>

<style scoped>
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
    height: 26.2vw;
}

/*********************** section ***********************/
section{
    width: 86vw;
    margin: 0 auto;
}
section .title{
    width: 100%;
    height: 12vw;
    border-bottom: solid 1px #EEE;

    display: flex;
    align-items: center;
}
section .title p{
    height: 3.4vw;
    line-height: 3.4vw;
    font-size: 4.2vw;
    font-weight: 600;
    box-sizing: border-box;
    padding-left: 3vw;
    border-left: solid 2px #127A90;
}
section table{
    font-size: 3.6vw;
    color: #555;
    margin-top: 2vw;
}
section table tr{
    line-height: 8vw;
}
section table tr td:first-child{
    width: 22%;
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
.bottom-btn .first{
    flex: 2;
    line-height: 12vw;
    font-size: 4.6vw;
    box-sizing: border-box;
    padding-left: 6vw;
}
.bottom-btn .first span{
    color: #F77B2D;
}
.bottom-btn .last{
    flex: 1;
    background-color: #117C94;
    line-height: 12vw;
    text-align: center;
    font-size: 5vw;
    color: #FFF;

    user-select: none;
    cursor: pointer;
}
</style>