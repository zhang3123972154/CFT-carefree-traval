<template>
    <view class="flex-horizontal container gap-20">
        <timeLine/>
        <view class="flex-horizontal gap-10">
            <u-icon :name="iconPath.star" size="14"></u-icon>
            <text class="detail" @click="gotoGaode">点击获取具体路线</text>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import timeLine from "./timeLine.vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

// FUNC
    // tag router
    const gotoGaode = () => {
        uni.navigateTo({ url: '/pages/NavigationGaode/navigationView' });
    }

</script>

<style scoped>

.container {
    border-radius: 10px;
    background-color: #F9F9F9;
    padding: 10px;
}

.detail {
    font-size: 10px;
    line-height: 9px;
    color: #999999;
}

</style>        