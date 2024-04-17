<template>
    <view class="flex-center-vertical container gap-10" @click.stop>
        <up-toast ref="uToastRef"></up-toast>
        <!--info TITLE-->
        <view class="flex-center-both gap-30">
            <u-icon :name="iconPath.LOGO" size="40"></u-icon>
            <text class="title">{{ titleList[questionIndex] }}</text>
        </view>
        <!--info 信息选择页面-->
        <view class="question-container">
            <position v-show="questionIndex == 0"/>
            <timeView v-show="questionIndex == 1"/>
        </view>
        <!--info 原本的输入框-->
        <!-- <u-icon class="mt-30" :name="iconPath.LOGO" size="105"></u-icon>
        <text class="talk">您希望您的旅行是什么样的呢？</text> -->
        <!-- <view class="input-container">
            <u--textarea v-model="content" 
                placeholder="说点什么吧......"
                border="none"
                fontSize="14"
                height="80"
                :customStyle="{
                    borderRadius: '20px',
                    padding: '15px',
                    border: '1px solid #000',
                    paddingRight: '45px',
                }"
            ></u--textarea>
            <view class="input-func-container">
                <view v-if="inputNULL" class="flex-horizontal gap-20">
                    <u-icon :name="iconInput.microphone" size="20"></u-icon>
                    <u-icon :name="iconInput.camera" size="20"></u-icon>
                </view>
                <u-icon v-else :name="iconPath.clear" size="20" @click="clearInput"></u-icon>
            </view>
        </view> -->
        <!--info 底部按钮-->
        <view class="flex-center-horizontal gap-35">
            <t-btn variant="tonal" class="btn-base" @click="lastQuestion">上一题</t-btn>
            <t-btn variant="tonal" class="btn-base" @click="nextQuestion">下一题</t-btn>
        </view>
        <view class="flex-center-horizontal gap-35 mt-10">
            <t-btn variant="outlined" class="btn-base" :custom-style="{
                color: '#666666',
                borderColor: '#666666'
            }" @click="gotoBack">取消</t-btn>
            <t-btn variant="tonal" class="btn-base" :custom-style="{
                color: '#FFFFFF',
                backgroundColor: '#FFC300'
            }" @click="gotoAiTalk">完成</t-btn>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import position from "./position.vue";
    import timeView from "./time.vue";
    // store
    import { useAiIconPath, useDetailIconPath } from "@/store/dataBase.ts";
    const iconPath = useAiIconPath();
    const iconInput = useDetailIconPath();  // info 原本的输入框有在用
    import { useSpot } from "@/store/dataBaseArray";
    const spotStore = useSpot();
// DATA
    const emits = defineEmits(['close']);

    const titleList = ref(["旅行地点", "旅行时间", "旅行偏好", "预算", "个性标签"]);
    const questionIndex = ref(0);

// FUNC
    // tag
    const lastQuestion = () => {
        let index = questionIndex.value - 1;
        questionIndex.value = Math.max(0, index);
    }

    const uToastRef = ref(null);
    const nextQuestion = () => {
        // info check finished ?
        // switch(questionIndex.value) {
        //     case 0:  
        //         if(!spotStore.finish) {
        //             uToastRef.value.show({
        //                 type: "error",
        //                 message: "请输入起点与终点"
        //             })
        //             return;
        //         }
        // }

        let index = questionIndex.value + 1;
        questionIndex.value = Math.min(4, index);
    }

    // tag router
    const gotoBack = () => {
        uni.navigateBack({ delta: 1 });
    }

    const gotoAiTalk = () => {
        uni.navigateTo({ url: '/pages/AiTalk/AiTalkView' })
    }



</script>

<style scoped>

.title {
    font-size: 35px;
    font-family: Alimama ShuHeiTi;
}

.question-container {
    height: 60vh;
    width: 80%;

    overflow: scroll;
}

.container {
    background-color: #fff;
    height: 100vh;
}

.talk {
    margin-top: 80px;

    font-size: 18px;
    line-height: 16.5px;
    font-weight: 300;
}

.input-container {
    margin-top: 15px;

    position: relative;

    width: 80%;
    max-width: 500px;
}

.input-func-container {
    position: absolute;
    top: 15px;
    right: 20px;

    z-index: 2000;
}

.btn-base {
    padding: 12px 0;
    height: 40px;
    width: 120px;

    font-size: 18px;
    line-height: 16.5px;
}



</style>        