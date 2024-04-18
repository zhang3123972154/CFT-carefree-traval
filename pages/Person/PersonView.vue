<template>
    <view>
        <h1>个人主页（临时）</h1>
        <view>
            <h2>注册</h2>
            <h2>登录</h2>
        </view>
        <t-tabbar />
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    // store
    // js
    import { useApiUser } from "@/request/api";
    const api = useApiUser();
// DATA
    const emailFrom = ref({
        email: "2993167370@qq.com", // test
        purposeIdentify: 0
    });

    const loginByUsername = ref({
        username: "2993167370@qq.com",
        password: "123456"
    })

    // const res = api.sendEmailCode(emailFrom.value);
    const res = api.loginByUsername(loginByUsername.value).then(res => {
        console.info("success", res);
    }).catch(err => {
        console.info("fail", err);
    });

// FUNC

</script>

<style scoped>

</style>        