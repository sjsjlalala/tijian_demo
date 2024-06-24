<template>
    <!-- footer -->
    <footer>
            <ul>
                <li @click="Tohospital">
                        <Hospital/>
                    <p>云医院</p>
                </li>
                <li>
                       <Shop/>
                    <p>商城</p>
                </li>
                <li>
                    <Find/>
                    <p>发现</p>
                </li>
                <li @click="Topersonal">
                       <Me/>
                    <p>我</p>
                </li>
            </ul>
        </footer>
</template>

<script setup>
    import Shop from '../components/icons/shop.vue'
    import Hospital from '../components/icons/hospital.vue'
    import Find from '../components/icons/find.vue'
    import Me from '../components/icons/me.vue'
    import { useRouter } from "vue-router";
    
    const router = useRouter();

    const Tohospital = () => {
        router.push('/index')
    }

    const Topersonal = () => {
        router.push('/personal')
    }
    
</script>

<style scoped>
@import url('https://unpkg.com/tailwindcss@^2.0/dist/tailwind.min.css');
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
</style>