<template>
    <view class="flex-center-vertical container gap-30">
        <up-image 
            src="/static/example/AI/AIBigHead.svg"
            width="205px" height="236px"
        />
        <view class="text-container">
            <view class="text-1">HELLO!</view>
            <view class="text-2">
                小主人！我是小助手萧萧！
            </view>
            <view class="text-2">
                让我们展开全新的旅行吧！
            </view>
        </view>
        <view class="flex-center-vertical block gap-20">
            <t-btn variant="tonal" class="btn-base" :custom-style="{
                color: '#000000',
                backgroundColor: '#FFC300',
            }" @click="gotoAiTalk">好的，现在开始吧！</t-btn>
            <t-btn variant="outlined" class="btn-base" :custom-style="{
                color: '#666666',
                borderColor: '#666666'
            }" @click="gotoBack">稍等，我再看看吧！</t-btn>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    // store
// DATA

// FUNC
    // tag router
    const gotoBack = () => {
        uni.navigateBack({ delta: 1 });
    }

    const gotoAiTalk = () => {
        uni.redirectTo({ url: '/pages/AiTalk/AiTalkView' })
    }

</script>

<style scoped>

.container {
    margin-top: 15vh;
}

.text-container {
    font-family: Alimama ShuHeiTi;
}

.text-2 {
    font-size: 24px;
}

.text-1 {
    font-size: 60px;
}

.btn-base {
    padding: 12px 0;
    width: 70%;
    height: 40px;

    font-size: 20px;
    font-weight: 500;
    line-height: 16.5px;
}


</style>        