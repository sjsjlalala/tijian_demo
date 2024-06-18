<template>
    <!-- 总容器 -->
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>用户体检预约</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <img src="../assets/img/yuyue.png">
            <p>
                <img src="../assets/img/benrenyuyue.png" @click="Tohospital">
                <img src="../assets/img/jiashuyuyue.png">
            </p>
        </section>
        
        <div class="bottom-ban"></div>
    <Footer></Footer>
</div>
</template>

<script>
import { useRouter } from "vue-router";
import Footer from "../components/Footer.vue";
export default {
    setup() {
        const router = useRouter();
       function Tohospital() {
            router.push({ name: "hospital" });
        }
        return {
            Tohospital
        }
    },
    components: {
        Footer
    }
}
</script>

<style scoped>
    /*********************** 总容器 ***********************/
.wrapper{
    width: 100%;
    height: 100%;
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
section img{
    width: 100vw;
}
section p{
    box-sizing: border-box;
    padding: 0 3.6vw;
    margin-top: 10vw;

    display: flex;
    justify-content: space-between;
}
section p img{
    width: 44vw;
    border-radius: 1.6vw;
    display: block;
    cursor: pointer;
}

</style>