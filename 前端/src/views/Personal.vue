<template>
    <!-- 总容器 -->
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>个人中心</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <div class="info">
                <div class="content">
                    <img src="../assets/img/user.png">
                    <div>
                        <p v-if="user!== '0'">{{user.realName}}</p>
                        <p v-else>请登入</p>
                        <p v-if="user!== '0'">{{user.userId}}</p>
                        <p v-else @click="Tologin1">点击登录</p>
                    </div>
                </div>
            </div>
            <ul>
                <li>
                    <div class="left">
                        <i class="fa fa-user-plus"></i>
                        <p>我的预约</p>
                    </div>
                    <div class="right" @click="Toappointmentlist">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-volume-control-phone"></i>
                        <p>我的服务</p>
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-bed"></i>
                        <p>我的医生</p>
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-sticky-note"></i>
                        <p>问诊订单</p>
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-cart-plus"></i>
                        <p>商城订单</p>
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-sign-out"></i>
                        <p>退出登录</p>
                    </div>
                    <div class="right" @click="Tologin">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
            </ul>
        </section>
        
        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script setup>
import Footer from '../components/Footer.vue'
import { useRouter } from "vue-router";
import { inject } from 'vue';
import { setSessionStorage, getSessionStorage ,removeSessionStorage} from "../common.js";
import { ref } from 'vue';

const axios = inject('axios');
let user = ref(0);

  if(getSessionStorage('users') !== null){
    user.value = getSessionStorage('users'); // 使用.value且提供一个默认值以防万一
  } else {
    user.value = "0"; // 直接修改user.value
  }

const router = useRouter();

const Toappointmentlist = () => {
    router.push('/appointmentlist');
}

const Tologin = () => {
    axios.post('api/users/logout').then(() => {
        removeSessionStorage('users');
        router.push('/index');
    });
    // router.push('/');
}

const Tologin1 = () => {
     router.push('/');
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

/*********************** section ***********************/
section{
    width: 100%;
}
section .info{
    width: 100%;
    height: 30vw;
    background-image: linear-gradient(135deg,#7DB35D,#02A6C9,#02A6C9);
    display: flex;
    align-items: center;
}
section .info .content{
    width: 90vw;
    margin: 0 auto;
    display: flex;
}
section .info .content img{
    width: 14vw;
    height: 14vw;
    border-radius: 7vw;
}
section .info .content div{
    height: 14vw;
    margin-left: 3vw;
    color: #FFF;
}
section .info .content div p:first-child{
    font-size: 4.8vw;
}
section .info .content div p:last-child{
    font-size: 3.2vw;
    margin-top: 2.6vw;
}

section ul{
    width: 86vw;
    margin: 0 auto;
}
section ul li{
    width: 100%;
    height: 14vw;
    border-bottom: solid 1px #EEE;
    color: #555;
    font-size: 4.2vw;
    font-weight: 600;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
section ul li .left{
    display: flex;
    align-items: center;
}
section ul li .left p{
    margin-left: 3vw;
}
section ul li .right{
    user-select: none;
    cursor: pointer;
}
section ul li .right i{
    font-size: 6vw;
}
</style>