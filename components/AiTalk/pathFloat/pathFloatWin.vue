<!--info 新版：一天分时间段的展示-->
<template>
    <view v-if="!openFlag" class="open-beside"     
    :style="{
        '--bottom': setBottom_swtich
    }"
    @click.stop="openFlag = true"
    @touchend.prevent="openFlag = true">    <!--info 允许弹出keyboard后打开【规划表】-->
        <view class="line"></view>
    </view>
    <floatBase v-else shadow z-index="9" :bottom="setBottom_floatwin">
        <view class="float-container">
            <!--header-->
            <view class="flex-center-horizontal">
                <view class="time-container flex-horizontal gap-10">
                    <text>DAY {{ dateIndex + 1 }}</text>
                    <text class="spot-font">{{ spot }}</text>
                </view>
                <view class="flex-horizontal gap-20">
                    <u-icon :name="iconPath.detail" size="24"></u-icon>
                    <u-icon :name="iconPath.LOGO" size="20" @click="gotoPlanDetail"></u-icon>
                </view>
            </view>
            <!--info 左右滑动-->
            
            <swiper class="swiper" circular indicator-dots 
                :style="{
                    '--swiper-height': swiperHeight + 'px'
                }"
                @change="changeSwiper">
                <template v-for="(item, index) in talkStore.plan" :key="index">
                    <swiper-item>
                        <path-item :list="item.list"/>
				    </swiper-item>
                </template>
			</swiper>
        </view>
    </floatBase>
    <!--info 简易的遮罩。-->
    <view v-show="openFlag" class="overlay" @click="openFlag = false"></view>
</template>

<script setup>
    import { ref, computed, onMounted } from "vue";
    // com
    import floatBase from "../../Com/floatBase.vue";
    import pathItem from "./item.vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
    import { aiTalk } from "@/store/aiTalk";
    const talkStore = aiTalk();
// DATA
    const props = defineProps({
        moveHeight: {
            type: Number,
            default: 0
        }
    });
    const emits = defineEmits([]);

    // core data
    const dateIndex = ref(0);
    const spot = ref("");
    const swiperHeight = ref(258);

    // flag
    const openFlag = ref(false);

    // const
    const BOTTOM_SWITCH = 72;
    const BOTTOM_FLOATWIN = 60;
    const CHIP_HEIGHT = 43;

// FUNC
    // animation
    const setBottom_swtich = computed(() => {
        return (BOTTOM_SWITCH + props.moveHeight).toString() + 'px';
    });
    const setBottom_floatwin = computed(() => {
        return BOTTOM_FLOATWIN + props.moveHeight;  // info Number 属性
    })

    // tag router
    const gotoPlanDetail = () => {
        uni.navigateTo({ url: '/pages/AiPlan/planDetailView' })
    }

    // handle data
    onMounted(() => {
        loadSeting();
    })

    const loadSeting = () => {
        spot.value = talkStore.plan[dateIndex.value].spot;
        swiperHeight.value = CHIP_HEIGHT * talkStore.plan[dateIndex.value].length;
    }

    const changeSwiper = (event) => {
        dateIndex.value = event.detail.current;
        loadSeting();
    }


</script>

<style scoped>

.open-beside {
    position: fixed;
    right: 0;
    bottom: var(--bottom);   /* 可以写成可拖动 */

    width: 30px;
    height: 60px;

    border-radius: 10px 0 0 10px;
    border: 1px solid #CCCCCC66;
    border-right: none;
    background-color: #ffc300; /* todo */

    box-sizing: border-box;

    transition: bottom .1s;
}

.line {
    position: relative;
    top: 14px;
    left: 10px;

    width: 2px;
    height: 30px;
    border-radius: 1px;

    background-color: #FFFFFF;
}

.float-container {
    padding: 5px 15px 15px;
}

.time-container {
    padding: 0 20px;
    background-color: #ffc300;
    border-radius: 15px;
    height: 40px;

    font-family: Alimama ShuHeiTi;
}

.spot-font {
    color: #FFF;
    font-size: 14px;
}

.chips-container {
    padding: 15px 0px 0;
}

.overlay {
    position: fixed;
    bottom: 0;
    height: 100%;
    width: 100%;
    /* background-color: #00000030; 但是依旧能起到一个遮罩的效果 */
}

.swiper {
    height: var(--swiper-height);
    padding-top: 10px;

    transition: height .5s ease;
}

</style>        