<template>
    <view class="float-container">
        <view class="flex-center-horizontal">
            <view class="time-container flex-horizontal gap-10">
                <text>DAY {{ props.day }}</text>
                <text class="spot-font">{{ props.spot }}</text>
            </view>
            <view class="flex-horizontal gap-20">
                <u-icon :name="detailFlag ? iconPath.list : iconPath.detail" size="20" @click="detailFlag = !detailFlag"></u-icon>
                <u-icon :name="iconPath.LOGO" size="20"></u-icon>
            </view>
        </view>
        <!-- path detail -->
        <chipGroupPath v-if="!detailFlag" class="chips-container"/>
        <view v-else>
            <spotDetail>
                <view class="flex-center-both spot-time-container gap-5">
                    <timeLine></timeLine>
                    <view class="flex-vertical">
                        <text>获取</text>
                        <text>方案</text>
                    </view>
                </view>
            </spotDetail>
            <trafficDetail/>
            <spotDetail :things="props.path[0].things">
                <view class="flex-center-both spot-time-container gap-5">
                    <timeLine></timeLine>
                    <view class="flex-vertical">
                        <text>获取</text>
                        <text>方案</text>
                    </view>
                </view>
            </spotDetail>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    import { pathSpot, pathTraffic } from "@/js/struct";
    // com
    import chipGroupPath from "../AiTalk/chipGroupPath.vue";
    import spotDetail from "./spotDetail.vue";
    import timeLine from "./timeLine.vue";
    import trafficDetail from "./trafficDetail.vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({
        day: {
            type: [Number, String],
            default: 1
        },
        spot: { // info 大地点
            type: String,
            default: "默认地点"
        },
        // 
        path: {
            type: Array,
            // todo traffic 的详细 UI 形式 ？
            default: [
                pathSpot("民宿", ["I", "II"], "/static/example/background/home.png",
                    4.9, "XXX", "00：00", "07：30"),
                pathTraffic("高铁", "07：30", "08：00"),
                pathSpot("民宿", ["I", "II"], "/static/example/background/home.png",
                    4.9, "XXX", "08：00", "09：00"),
                pathTraffic("高铁", "09：00", "10：00"),
            ]
        }
    });
    const emits = defineEmits([]);

    // flag
    const detailFlag = ref(false);
// FUNC

</script>

<style scoped>

.float-container {
    padding: 5px 15px 15px;

    background-color: #fff;
    border-radius: 20px;
    box-shadow: 0px 0px 10px 3px #0000001a;
}

.time-container {
    padding: 12px;
    background-color: #ffc300;
    border-radius: 10px;
    height: 30px;
    margin: 0 7px;

    font-family: Alimama ShuHeiTi;
    font-size: 16px;
}

.spot-font {
    font-size: 14px;
    color: #FFF;
}

.chips-container {
    padding: 15px 0px 0;
}

.spot-time-container {
    border-radius: 10px;
    background-color: #F9F9F9;
    padding: 10px;

    font-size: 10px;
    line-height: 12px;
    color: #999999;
}

</style>        