<template>
    <!-- <t-tabbar/> -->
    <view class="flex-vertical">
        <detail-header />
        <album />
        <!--content-->
        <view class="flex-vertical container gap-10">
            <text class="title">{{ title }}</text>
            <text>{{ content }}</text>
            <chipGroup :topicList="chipsList.topicList" :locationList="chipsList.locationList"
            :foodList="chipsList.foodList" />
            <view class="flex-equal-horizontal" :style="{
                margin: '-10px 0'
            }">
                <funcBtn :icon="iconPath.plan" title="热门规划"/>
                <funcBtn :icon="iconPath.group" title="旅行圈子"/>
                <funcBtn :icon="iconPath.helper" title="旅行助手"/>
            </view>
            <view class="flex-center-horizontal container-location">
                <view class="flex-horizontal gap-5">
                    <u-icon :name="iconPath.location"></u-icon>
                    <text>{{ location }}</text>
                </view>
                <text>{{ time }}</text>
            </view>
            <commentBox/>
        </view>
        <!--end-->
        <tabbar/>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import detailHeader from "@/components/Detail/header.vue";
    import album from '@/components/Detail/album.vue';
    import chipGroup from "@/components/Home/chipGroup.vue";
    import tabbar from "../../components/Detail/tabbar.vue";
    import funcBtn from "../../components/Detail/funcBtn.vue";
    import commentBox from "./commentBox.vue";
    // store
    import { useDetailIconPath } from "@/store/dataBase";
    const iconPath = useDetailIconPath();

// DATA
    const title = ref("故人西辞黄鹤楼！！！")
    const content = ref("p1和p3是在长江大桥靠近黄鹤楼的武警岗亭那边，p2是在长江大桥靠近龟山那边。")
    const chipsList = ref({
        topicList: ["记录生活"],
        locationList: ["武汉", "武汉长江大桥", "黄鹤楼"],
        foodList: ["摄影"]
    });
    const location = ref("武汉  黄鹤楼");
    const time = ref("2023-01-21");


</script>

<style scoped>

.container {
    padding: 13px;
}

.title {
    font-size: 18px;
    line-height: 16.5px;
}

.content {
    font-size: 16px;
    line-height: 19px;
    font-weight: 300;
}

.container-location {
    padding: 0 20px;

    font-size: 8px;
    font-weight: 300;
    color: #cccccc;
    line-height: 10px;
}

</style>