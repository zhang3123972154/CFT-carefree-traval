<template>
    <view class="flex-center-horizontal container top-container" :style="{
            '--status-height': phoneInforStore.statusBarHeight.toString() + 'px',
        }" @click.stop>
        <!--update 滑块有一些错位-->
        <u-tabs :list="TabList" :current="tabIndex" @scroll="test" line-color="#ffc300" line-height="2"
            line-width="36" :activeStyle="{
                color: '#000000',
                fontSize: '18px',
                lineHeight: '16.5px'
            }" :inactiveStyle="{
                color: '#333333',
                fontWeight: '300',
                lineHeight: '14.5px'
            }" :itemStyle="{
                paddingBottom: '7px',
                paddingTop: '5px',
            }" />
        <view class="flex-center-horizontal gap-1">
            <t-btn-icon icon="/static/icon/list.svg" @click="settingFlag = true"></t-btn-icon>
            <t-btn-icon icon="/static/icon/more.svg" @click.stop="openFunction"></t-btn-icon>
            <t-btn-icon icon="/static/icon/LOGO.svg" @click.stop="gotoAiPlan"></t-btn-icon>
        </view>
        <!--菜单功能-->
        <FunctionMenu :animation="functionAnimation"
        class="function-menu top-float-win" 
        :style="{
            '--status-height': phoneInforStore.statusBarHeight.toString() + 'px',
        }"/>
    </view>
    <!--other function-->
    <!--bug 手机端偶尔动画bug-->
    <u-popup :show="settingFlag" mode="right" overlayOpacity="0.3" safeAreaInsetTop :customStyle="{
        borderBottomLeftRadius: '20px'
    }" @close="settingFlag = false">
        <Setting />
    </u-popup>
    <!--规划功能-->
    <!-- <u-overlay class="flex-center-both" :show="planFlag" @click="planFlag = false"> -->
        <!--未有规划之时-->
        <!-- <questionFullView/> -->
        <!--已有规划之时-->
        <!-- <view class="flex-center-both">
            <planStartVue />
        </view> -->
    <!-- </u-overlay> -->
</template>

<script setup>
    import { ref, nextTick } from "vue";
    import FunctionMenu from "./function.vue";
    import Setting from "./setting.vue";
    // store
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInforStore = usePhoneInfor();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);
    //todo 第一个词条需要和定位绑定，以及处理“定位失效”时的容错。
    const TabList = ref([{name: "武汉"}, {name: "关注"}, {name: "规划"}, {name: "推荐"}]);
    const tabIndex = ref(0);   // bug 好像无效
    // flag
    const settingFlag = ref(false);
    const functionFlag = ref(false);
    const planFlag = ref(false);
    // style
    const functionAnimation = ref(null);
// FUNC
    // animation
    const TIME_ANIMATION = 300;
    const toggleFloatWin = (show) => {
        if(show) {
            functionFlag.value = true;

            nextTick(() => {
                functionAnimation.value = uni.createAnimation({
                    duration: TIME_ANIMATION,
                    timingFunction: 'ease'
                });
                functionAnimation.value.opacity(1).step();
            });
        } else {
            setTimeout(() => {
                functionFlag.value = false;
            }, TIME_ANIMATION);

            functionAnimation.value = uni.createAnimation({
                duration: TIME_ANIMATION,
                timingFunction: "ease"
            });
            functionAnimation.value.opacity(0).step();
        }
    }
    // router
    const openFunction = () => {
        toggleFloatWin(!functionFlag.value);
        uni.$once("baseClick", () => toggleFloatWin(false));
    }
    const gotoAiPlan = () => {
        uni.navigateTo({ url: '/pages/AiPlan/questionFullView'})
    }
    // test
    function test() {
        // console.info(tabIndex.value);
        console.info("scroll run");
    }
    
</script>

<style scoped>
/* TOP */
.container {
    position: sticky;
    top: 0px;

    background-color: #fff;
    z-index: 2000;
}

.top-btn {
    width: 21px;
    height: 21px;
    margin: 10px;
}

.function-menu {
    position: absolute;
    top: 40px; 
    right: 20px;
    opacity: 0; /* 一开始进入dom，但是不显示 */

    z-index: 3000;
}
</style>        