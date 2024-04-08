<template>
    <tabberBase>
        <template #prefix>
            <t-btn-icon :icon="iconPath.voice"></t-btn-icon>
        </template>
        <template #midfix>
            <u--textarea v-model="inputContent"
                focus confirm-hold :height="inputHeight" :cursorSpacing="20"
                shape="circle" border="none" fontSize="14" :holdKeyboard="true"
                :style="{
                    padding: '7px',
                    margin: '3px',
                    backgroundColor: '#f9f9f9',
                    border: 'solid 1px #eeeeee80',
                    borderRadius: '20px'
                }"
                @linechange="changeInputHeight"
                @confirm="sendUserMessage"
                @keyboardheightchange="keyboardChange"/>
        </template>
        <template #suffix>
            <t-btn-icon :icon="iconPath.emoji"></t-btn-icon>
            <t-btn-icon v-if="inputContent === ''" :icon="iconPath.add" @click="questionFlag = true"></t-btn-icon>
            <view v-else class="send-container" @touchend.prevent="sendUserMessage">
                <text>发送</text>
            </view>
        </template>
    </tabberBase>
    <pathFloatWin :spot="plan.spot" :day="plan.day" :path="plan.path"/>
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

    });
    const emits = defineEmits(["sendMessage"]);

    const inputContent = ref('');
    const inputLineHeight = 20;
    const inputHeight = ref(inputLineHeight);
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

    const keyboardChange = (height, deration) => {
        console.info("键盘变化", height, deration);

        // uni.onKeyboardheightchange(res => {
        //     console.info(res.height, "keyBoard-height");
        // })
    }
    
    const changeInputHeight = (infor) => {
        inputHeight.value = inputLineHeight * infor.detail.lineCount;
    }

    

</script>

<style scoped>

.send-container {
    border-radius: 5px;
    background-color: #ffc300;
    padding: 4px;

    color: #fff;
    font-size: 14px;
    font-family: Alimama ShuHeiTi;
}

</style>        