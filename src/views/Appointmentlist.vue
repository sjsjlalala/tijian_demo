<template>
    <!-- 总容器 -->
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>我的预约</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <ul v-for="(item,index) in appointmentlist" :key="index">
            <li >
                <div class="left" @click="Toappointmentok(item.orderId)">
                    <p>{{item.orderDate}}</p>
                    <p v-if="item.smId==1">普通男士客户-基础套餐</p>
                    <p v-if="item.smId==2">普通男士客户-肾病套餐</p>
                    <p v-if="item.smId==3">普通男士客户-肝病套餐</p>
                    <p v-if="item.smId==4">普通女士客户-基础套餐</p>
                    <p v-if="item.smId==5">普通女士客户-肾病套餐</p>
                    <p v-if="item.smId==6">普通女士客户-肝病套餐</p>
                </div>
                <div class="right" @click="cancelAppointment(item.orderId)">
                    取消预约
                </div>
            </li>
        </ul>
        
        <div class="bottom-ban"></div>
    <Footer></Footer>
</div>
</template>

<script setup>
import Footer from "../components/Footer.vue";
import { onMounted, reactive, toRefs, ref,watch } from "vue";
import { useRouter } from "vue-router";
import { setSessionStorage, getSessionStorage } from "../common.js";
import { inject } from 'vue';
import axios from 'axios';
const axios = inject('axios');

const router = useRouter();

const user = getSessionStorage("users");

const appointmentlist=ref([]);

const getappointmentlist = () => {
    // axios.get("/api/appointmentlist?userId="+user.userId)
    if(user==null){
        axios.get("/api/orders/getOrdersByUserId?userId=0")
    }else{
    axios.get("/api/orders/getOrdersByUserId?userId="+user.userId)
    .then(res => {
        appointmentlist.value=res.data.data;
        console.log(appointmentlist.value);
     })
    .catch(err => {
        console.log(err);
     });
    }
}

onMounted(() => {

    getappointmentlist();
});

const cancelAppointment = (id) => {
    axios.delete("/api/orders/cancelOrder?orderId="+id
        
    )
    .then(res => {
        if(res.data.code==200){
            getappointmentlist();
            router.push("/appointmentcancel");
        }else{
            alert("取消预约失败");
        }
     })
}

const Toappointmentok = (id) => {
    for(let i=0;i<appointmentlist.value.length;i++){
        if(appointmentlist.value[i].orderId==id)
        {
            setSessionStorage("appointment",appointmentlist.value[i]);
            break;
        }
    }
    router.push("/appointmentok");
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
    height: 14.2vw;
}

/*********************** ul ***********************/
ul{
    width: 86vw;
    margin: 0 auto;
}
ul li{
    width: 100%;
    height: 14vw;
    border-bottom: solid 1px #EEE;

    display: flex;
    justify-content: space-between;
    align-items: center;
}
ul li .left{
    user-select: none;
    cursor: pointer;
}
ul li .left p:first-child{
    color: #555;
    font-size: 3.3vw;
}
ul li .left p:last-child{
    color: #333;
    font-size: 4.2vw;
    font-weight: 600;
}
ul li .right{
    font-size: 4vw;
    color: #E6A23C;
}
</style>