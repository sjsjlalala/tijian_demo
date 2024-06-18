<template>
    <body>
    <!-- 总容器 -->
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <!-- <p>注册</p> -->
            <div></div>
        </header>
        <div class="top-ban"></div>
        <h1>欢迎注册</h1>
        <table>
            <tr>
                <td>手机号码</td>
                <td><input type="text" v-model="users.userId" placeholder="请输入手机号码"></td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td><input type="text" v-model="users.realName" placeholder="真实姓名便于查看体检报告"></td>
            </tr>
            <tr>
                <td>生日</td>
                <td><input v-model="users.birthday" type="date"></td>
            </tr>
            <tr>
                <td>性别</td>
                <td>
                    <input type="radio" v-model="users.sex" name="gender" value="1" checked> 男
                    <input type="radio" v-model="users.sex" name="gender" value="0"> 女
                </td>
            </tr>
            <tr>
                <td>身份证号</td>
                <td><input type="text" v-model="users.identityCard" placeholder="请输入身份证号"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" v-model="users.password" placeholder="请输入密码"></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input type="password" v-model="users.repassword" placeholder="请再次输入密码"></td>
            </tr>
        </table>
        <div class="btn" @click="handleSubmit">注册</div>
    </div>
    
</body>
</template>

<script>
import { reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
import { setSessionStorage, getSessionStorage } from "../common.js";
import axios from "axios";
//axios.defaults.baseURL = "http://localhost:8080/";

export default {
    setup(){
        const router = useRouter();

        const state = reactive({
      users: {
            userId: "",
            realName: "",
            birthday: "",
            sex: "1",
            identityCard: "",
            password: "",
            repassword: "",
            userType: "1"
      }
    });

    const handleSubmit = () => {
        const { userId, realName, birthday, sex, identityCard, password, repassword } = state.users;
        if (userId === "" || realName === "" || birthday === "" || sex === "" || identityCard === "" || password === "" || repassword === "") {
            alert("请填写完整信息");
            return;
        }
        if (password !== repassword) {
            alert("两次密码输入不一致");
            return;
        }
        axios.post("/api/users/register", state.users).then(res => {
            if (res.data.code === 200) {
                alert("注册成功");
                setSessionStorage("user", res.data.data);
                router.push("/index");
            } else {
                alert(res.data.msg);
            }
        }).catch(err => {
            console.log(err);
            alert("注册失败");
        });
    };

    return {
       ...toRefs(state),
        handleSubmit
    }
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

/*********************** 标题部分 ***********************/
h1{
    font-size: 7.4vw;
    font-weight: 500;
    box-sizing: border-box;
    padding: 0 3.6vw;
    margin-top: 6vw;
}

/*********************** common样式 ***********************/
.top-ban{
    width: 100%;
    height: 15.7vw;
}

/*********************** table ***********************/
table{
    width: 92.8vw;
    margin: 0 auto;
    margin-top: 5vw;
    border-collapse: collapse;

    font-size: 4.2vw;
}
table tr td{
    height: 12vw;
    border-bottom: solid 1px #DDD;
}
table tr td input{
    border: none;
    outline: none;
}

/*********************** btn ***********************/
.btn{
    width: 92.8vw;
    margin: 0 auto;
    height: 12vw;
    margin-top: 8vw;
    background-color: #137E92;
    border-radius: 2vw;
    color: #FFF;
    font-size: 5vw;
    text-align: center;
    line-height: 12vw;

    user-select: none;
    cursor: pointer;
}
</style>