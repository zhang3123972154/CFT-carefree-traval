<!--info 目前是规划页面-->
<template>
    <view class="flex-vertical">
        <header-plan/>
        <view class="z-1">
            <!--main - informatino-->
            <view class="flex-vertical main-container">
                <view class="flex-horizontal gap-10">
                    <planIndex v-for="(item, index) in timeList" :key="index"
                        :cur-index="planCurIndex"
                        :index="index" :time-start="item.st" :time-end="item.ed"
                    />
                </view>
                <chipGroup class="way-container" :way-list="chipList" :spot-list="[]" :thing-list="[]"/>
                <view class="flex-center-vertical traffic-container gap-10">
                    <traffic></traffic>
                    <traffic></traffic>
                </view>
            </view>
            <!--detail days - information-->
            <view class="flex-vertical detail-container gap-5">
                <pathCard/>
                <pathCard/>
            </view>
        </view>
        <tabber-ai></tabber-ai>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import headerPlan from "@/components/planDetail/header.vue";
    import tabberAi from "@/components/AiTalk/tabber.vue";

    import planIndex from "@/components/planDetail/planIndex.vue";
    import chipGroup from "@/components/Home/chipGroup.vue";
    import traffic from "@/components/planDetail/traffic.vue";
    import pathCard from "@/components/planDetail/pathCard.vue";
    // store
// DATA
    const timeList = ref([{ st: "03-27", ed: "03-28" }, { st: "03-27", ed: "03-29" }, { st: "03-28", ed: "03-30"}]);
    const planCurIndex = ref(0);
    const chipList = ref(["拍照打卡", "旅行搭子", "名胜古迹", "过早"]);
// FUNC

</script>

<style scoped>

.container {
    z-index: 1000;
}

.main-container {
    padding: 5px 20px;
}

.way-container {
    margin-top: 30px;
    margin-bottom: 20px;
}

.traffic-container {
    padding: 15px 20px;
    background-color: rgba(0, 0, 0, .4);
    border-radius: 15px;
}

.detail-container {
    background-color: #fff;
    border-radius: 20px 20px 0 0;

    padding: 5px;

    min-height: 55vh;
}

</style>        