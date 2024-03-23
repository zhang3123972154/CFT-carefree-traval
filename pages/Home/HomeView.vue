<template>
    <view @click="baseClick">
        <!--top function-->
        <view class="flex-center-horizontal top-container" :style="{
            '--status-height': phoneInforStore.statusBarHeight.toString() + 'px',
        }">
            <!--update 滑块有一些错位-->
            <u-tabs :list="TabList" :current="tabIndex" @click="test" line-color="#ffc300" line-height="2"
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
                <t-btn-icon icon="/static/icon/LOGO.svg" @click.stop="planFlag = true"></t-btn-icon>
            </view>
        </view>
        <!--菜单功能-->
        <transition name="fade">
            <FunctionMenu v-show="functionFlag" class="function-menu" />
        </transition>
        <!--other function-->
        <u-popup :show="settingFlag" mode="right" overlayOpacity="0.3" safeAreaInsetTop :customStyle="{
                borderBottomLeftRadius: '20px'
            }" @close="settingFlag = false">
            <Setting />
        </u-popup>
        <!--规划功能-->
        <u-overlay class="flex-center-both" :show="planFlag" @click="planFlag = false">
            <view class="flex-center-both">
                <planStartVue />
            </view>
        </u-overlay>
        <!--star-->
        <!--todo star的调整-->
        <starVue />
        <!--下层瀑布流信息-->
        <view class="m-5">
            <!--Search-->
            <Search />
            <!--waterfall-->
            <view class="waterfall gap-5">
                <Card />
                <Card src="/static/example/avatar.png" />
                <Card />
                <Card src="/static/example/avatar.png" />
                <Card />
                <Card src="/static/example/avatar.png" />
            </view>
            <!-- <Test/> -->
        </view>
        <!-- todo 相似栏 放到合适的位置去-->
        <!-- <u-overlay class="flex-center-both" :show="similarWinFlag" @click="similarWinFlag = false">
            <view class="flex-center-both">
                <similarWin />
            </view>
        </u-overlay> -->
        <!--end-->
        <t-tabbar />
    </view>
</template>

<script setup>
    import { ref } from 'vue';
    // store
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInforStore = usePhoneInfor();
    // com
    import Search from "@/components/Com/search.vue";
    import Card from "@/components/Home/card.vue";
    import Setting from "./setting.vue";
    import FunctionMenu from "./function.vue";
    import planStartVue from './planStart.vue';
    import starVue from './star.vue';
    import similarWin from './similarWin.vue';

    import scrollStop from '../../components/Handle/scrollStop.vue';

    // test
    import Test from "../Test/testDesi.vue";

// DATA
    // const
    //todo 第一个词条需要和定位绑定，以及处理“定位失效”时的容错。
    const TabList = ref([{name: "武汉"}, {name: "关注"}, {name: "规划"}, {name: "推荐"}]);
    const tabIndex = ref(0);   // bug 好像无效

    // flag
    const settingFlag = ref(false);
    const functionFlag = ref(false);
    const planFlag = ref(false);
    const similarWinFlag = ref(true);

// FUNC
    // click
    const baseClick = () => {   // mark 最基本的全局监听点击事件，
        uni.$emit("baseClick");
    }
    const openFunction = () => {
        functionFlag.value = !functionFlag.value;
        uni.$once("baseClick", () => functionFlag.value = false);
    }
    // test
    function test() {
        // console.info(tabIndex.value);
        console.info("scroll run");
    }

</script>

<style scoped>

/* TOP */
.top-container {
    font-family: SourceHanSansCN;
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

/* star */
.star-container {
    position: relative;
    height: 300px;
}

.function-menu {
    position: absolute;
    right: 20px;
}

.waterfall {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 5px;
    height: calc(100% - 50px);

    overflow-y: auto;
}

</style>