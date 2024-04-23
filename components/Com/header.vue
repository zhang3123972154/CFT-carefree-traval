<template>
    <view class="flex-center-horizontal container top-container" :style="{
            '--status-height': phoneInforStore.statusBarHeight.toString() + 'px',
        }" @click.stop>
        <!--update 滑块有一些错位-->
        <tab-group class="tab-container" :tab-list="tabList"
            @change-index="changeIndex"/>
        <view class="flex-center-horizontal gap-1">
            <t-btn-icon icon="/static/icon/list.svg" @click="settingFlag = true"></t-btn-icon>
            <t-btn-icon icon="/static/icon/more.svg" @click.stop="openFunction"></t-btn-icon>
            <!--todo 这里换规划 -> 导航-->
            <t-btn-icon icon="/static/icon/LOGO.svg" @click.stop="planFlag = true"></t-btn-icon>
        </view>
        <!--菜单功能-->
        <FunctionMenu v-show="functionFlag"
        kind="home"
        :animation="functionAnimation"
        class="function-menu top-float-win"
        :class="{
            // 'none': !functionFlag
        }"
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
    <u-overlay class="flex-center-both" :show="planFlag" @click="planFlag = false">
        <!-- 未有规划之时 -->
        <!-- <questionFullView/> -->
        <!-- 已有规划之时 -->
        <view class="flex-center-both">
            <planStart />
        </view>
    </u-overlay>
</template>

<script setup>
    import { ref, nextTick, onMounted, watch } from "vue";
    // com
    import tabGroup from "@/components/Home/tabGroup.vue";
    import FunctionMenu from "./functionFloat.vue";
    import Setting from "./setting.vue";
    import planStart from '@/pages/Home/planStart.vue';
    // store
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInforStore = usePhoneInfor();
    import { useLocation } from "@/store/location";
    const locationStore = useLocation();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);
    //todo 第一个词条需要和定位绑定，以及处理“定位失效”时的容错。
    const tabList = ref(["地点", "关注", "规划", "推荐"]);
    const tabIndex = ref(0);   // bug 好像无效
    const MenuList = ref({})
    // flag
    const settingFlag = ref(false);
    const planFlag = ref(false);
        // mark 两个flag共同维护
    const functionFlag = ref(false);
    let clickOnceFlag = false;
    // tag style - animation
    const functionAnimation = ref(null);

// FUNC
    onMounted(() => {
       locationStore.getLocation();
    })
    watch(() => locationStore.city, () => {
        tabList.value[0] = locationStore.city;
    })

    // tag menu animation
    const TIME_ANIMATION = 200;
    const toggleFloatWin = (show) => { // update 之后最好封装好一下。
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
    const openFunction = () => {
        if(functionFlag.value) // info 在动画生效期间，如果点击过快，就会bug，flag反转，无法正常工作；所以直接打断。
            return;
        console.info("openFunction", functionFlag.value);
        if(!clickOnceFlag) {
            toggleFloatWin(!functionFlag.value);
        } else {
            clickOnceFlag = false;
        }
        if(functionFlag.value) {
            clickOnceFlag = true;
            uni.$once("baseClick", () => {
                clickOnceFlag = false;
                console.info("baseClick-open", functionFlag.value); // info stop 好像成功阻止
                toggleFloatWin(false);
            });
        }
        else {
            uni.$off("baseClick");
            console.info("baseClick-close");
        }
    }
    // tag router
    const gotoAiPlan = () => {
        uni.navigateTo({ url: '/pages/AiPlan/questionFullView'})
    }

    // tag tag-group
    const changeIndex = (index) => {
        tabIndex.value = index;
    }

    // test
    const getLocation = () => {
        
    }
    
</script>

<style scoped>
/* TOP */
.tab-container {
    padding: 0 10px;
}

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
    opacity: 0; /* info 设定初值，然后入场动画才能够正常使用 */

    z-index: 3000;
}
</style>        