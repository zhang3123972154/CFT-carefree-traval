<template>
    <tabberBase z-index="6" :bottom="moveHeight">
        <template #prefix>
            <t-btn-icon :icon="iconPath.voice"></t-btn-icon>
        </template>
        <template #midfix>
            <!--update delete focus-->
            <view class="input-container">
                <u--textarea 
                    v-model="inputContent"
                    :confirmType="null" :adjustPosition="false"
                    :height="inputHeight" :cursorSpacing="20"
                    border="none" fontSize="14"
                    :style="{
                        padding: '3px 7px',
                        backgroundColor: 'transparent',
                        // info 限制最大高度
                        maxHeight: '65px',
                        overflowY: 'scroll'
                    }"
                    @linechange="changeInputHeight"
                    @keyboardheightchange="keyboardChange"
                />
            </view>
        </template>
        <template #suffix>
            <t-btn-icon :icon="iconPath.emoji"></t-btn-icon>
            <t-btn-icon v-if="inputContent === ''" :icon="iconPath.add" @click="questionFlag = true"></t-btn-icon>
            <view v-else class="send-container" @touchend.prevent="sendUserMessage">
                <text>发送</text>
            </view>
        </template>
    </tabberBase>
    <pathFloatWin :move-height="moveHeight" :spot="plan.spot" :day="plan.day" :path="plan.path"/>
    <!--悬浮窗户-->
    <u-overlay class="flex-center-both" :show="questionFlag" @click="questionFlag = false">
        <questionWin/>
    </u-overlay>
</template>

<script setup>
    import { ref } from "vue";
    import { pathPoint } from "@/js/struct";
    // com
    import tabberBase from "../Com/tabberBase.vue";

    import pathFloatWin from "@/components/AiTalk/pathFloatWin.vue";
    import questionWin from "./questionWin.vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();

// DATA
    const props = defineProps({
        animation: null
    });
    const emits = defineEmits(["sendMessage", "keyBoardChange"]);

    const inputContent = ref('');
    const inputLineHeight = 20;
    const inputHeight = ref(inputLineHeight);
    const moveHeight = ref(0);  // info 监听键盘事件！！！
    // flag
    const topFlodFlag = ref(true);
    const questionFlag = ref(false);
    const plan = ref({
        spot: "武汉",
        day: 1,
        path: [
            pathPoint("traffic", "高铁"),
            pathPoint("spot", "民宿"),
            pathPoint("spot", "地点", ["热干面", "三鲜豆皮"]),
            pathPoint("spot", "黄鹤楼"),
            pathPoint("spot", "楚河汉街", ["鄂菜"]),
            pathPoint("spot", "武汉大学", ["樱花"]),
            pathPoint("spot", "街道口"),
            pathPoint("spot", "武汉长江大桥", ["江景"]),
            pathPoint("spot", "民宿"),
        ]
    })

// FUNC
    const sendUserMessage = () => {
        emits('sendMessage', inputContent.value);
        inputContent.value = "";
    }

    const keyboardChange = (infor) => {
        console.info("键盘变化", infor); // info
        emits("keyBoardChange", infor.detail);
        moveHeight.value = infor.detail.height;
    }
    
    const changeInputHeight = (infor) => {
        inputHeight.value = inputLineHeight * infor.detail.lineCount;
    }

</script>

<style scoped>

.send-container {
    border-radius: 10px;
    background-color: #ffc300;
    padding:  5px 7px;

    color: #fff;
    font-size: 15px;
    font-family: Alimama ShuHeiTi;
}

.input-container {
    width: 100%;
    margin: 3px;

    border-radius: 15px;
    background-color: #f9f9f9;

    max-height: 65px;
    overflow-y: hidden;
    border-top: 5px solid #f9f9f9;
    border-bottom: 5px solid #f9f9f9;
}

</style>        