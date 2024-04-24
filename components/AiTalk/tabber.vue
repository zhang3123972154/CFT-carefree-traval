<template>
    <tabberBase z-index="6" :bottom="moveHeight">
        <template #topfix>
            <t-image-group v-if="photosPath"
                close-able
                class="photo-container"
                :images="photosPath"
                @close="deletePhoto"
            />
        </template>

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
            <!-- <t-btn-icon :icon="iconPath.emoji"></t-btn-icon> -->
            <t-btn-icon :icon="iconPath.add" @click="toggleMenu"></t-btn-icon>
            <view v-if="inputContent !== ''" class="send-container flex-horizontal gap-5" @touchend.prevent="sendUserMessage">
                <view v-if="talkStore.loading" class="loader"/>
                <text>{{ talkStore.loading ? "停止" : "发送" }}</text>
            </view>
        </template>
    </tabberBase>
    <pathFloatWin :move-height="moveHeight" :spot="plan.spot" :day="plan.day" :path="plan.path"/>
    <!--悬浮窗户-->
    <u-overlay class="flex-center-both" :show="questionFlag" @click="questionFlag = false">
        <questionWin/>
    </u-overlay>
    <!--info 菜单栏-->
    <!--todo 之后写一个动画-->
    <view v-show="menuFlag" class="flex-vertical menu-container" @click.stop>
        <t-btn-icon icon="/static/icon/LOGO.svg" @click="questionFlag = true"/>
        <t-btn-icon :icon="iconPath.photo" @click="addPhoto"/>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    import { pathPoint } from "@/js/struct";
    // import { pathToBase64 } from "@/js/image";
    // com
    import tabberBase from "../Com/tabberBase.vue";

    import pathFloatWin from "@/components/AiTalk/pathFloatWin.vue";
    import questionWin from "./questionWin.vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
    import { aiTalk } from "@/store/aiTalk";
    const talkStore = aiTalk();

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
    const menuFlag = ref(false);

    const photosPath = ref(null);
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
    });

// FUNC
    const sendUserMessage = () => {
        emits('sendMessage', inputContent.value, photosPath.value);
        inputContent.value = "";
        photosPath.value = null;
    }

    const keyboardChange = (infor) => {
        console.info("键盘变化", infor); // info
        emits("keyBoardChange", infor.detail);
        moveHeight.value = infor.detail.height;
    }
    
    const changeInputHeight = (infor) => {
        inputHeight.value = inputLineHeight * infor.detail.lineCount;
    }

    // tag photos
    const addPhoto = () => {
        // info 关于权限，会自动申请，挺好。
        uni.chooseImage({
            count: 3,
            sizeType: ['original', 'compressed'],
            sourceType: ['album', 'camera'],
            success: ({ tempFilePaths, tempFiles }) => {
                photosPath.value = tempFilePaths;

                // pathToBase64(photosPath.value[0])
                //     .then(base64 => {
                //         console.info(base64);
                //     })
                //     .catch(error => {
                //         console.info("base64", error);
                //     })
            },
            fail: (error) => {

            }
        });
    }

    const deletePhoto = (index) => {
        photosPath.value.splice(index, 1);
    }

    const toggleMenu = () => {
        menuFlag.value = !menuFlag.value;
        uni.$once("baseClick", () => {
            menuFlag.value = false;
        })
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

.menu-container {
    position: fixed;
    right: 0;
    bottom: 60px;
 
    background-color: #ffffff;
    border-radius: 20px 0 0 20px;
    padding: 2px;
    padding-right: 0;
}

.photo-container {
    background-color: #ffffff;
    border-top-right-radius: 15px;
    width: 200px;
    padding: 5px;
    padding-bottom: 0;
}

.loader {
  border: 3px solid #ffffff6b; /* 浅灰色背景 */
  border-top: 3px solid #ffffff; /* 蓝色 */
  border-radius: 50%;
  width: 20px;
  height: 20px;
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

</style>        