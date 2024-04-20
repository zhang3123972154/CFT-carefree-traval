<template>
    <view>
        <h1>个人主页（临时）</h1>
        <view class="flex-vertical gap-10" :style="{
            padding: '20px'
        }">
            <h2>注册()</h2>
            <t-btn @click="login">
                登录
            </t-btn>
            <t-btn @click="addTag">
                添加标签
            </t-btn>
        </view>
        <t-tabbar />
    </view>
</template>

<script setup>
    import { ref, reactive } from "vue";
    // com
    // store
    import useUserStore from "@/store/userInfor";
    const userInfor = useUserStore();
// DATA
    // const emailFrom = ref({
    //     email: "2993167370@qq.com", // test
    //     purposeIdentify: 0
    // });

    const loginByUsername = reactive({
        username: "2993167370@qq.com",
        password: "123456"
    })

// FUNC

    const login = () => {
        userInfor.login(loginByUsername.username, loginByUsername.password);
    }

    const addTag = () => {

    }

</script>

<style scoped>

</style>        