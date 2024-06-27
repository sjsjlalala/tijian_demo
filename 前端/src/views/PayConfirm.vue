<template>
    <div>
        订单确认中
    </div>
</template>

<script setup>
import { onMounted, reactive, toRefs, ref, watch, onUnmounted } from 'vue';
import { useRouter } from "vue-router";
import { setSessionStorage, getSessionStorage } from "../common.js";
import { inject } from 'vue';

const axios = inject('axios');
const router = useRouter();
const out_trade_no = getSessionStorage('out_trade_no');
onMounted(() => {
    //1.确认订单
    confirm();
});

const confirm = () => {
    axios.put('/api/orders/confirm?out_trade_no='+out_trade_no)
    .then(res => {
         if(res.data.code == 200)
            router.push('/appointmentsuccess');
})
}



</script>