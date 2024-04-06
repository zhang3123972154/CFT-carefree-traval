<template>
    <view>
        <message-header/>
        <view class="flex-center-horizontal btn-container">
            <t-btn-icon :icon="iconPath.like"></t-btn-icon>
            <t-btn-icon :icon="iconPath.user"></t-btn-icon>
            <t-btn-icon :icon="iconPath.message"></t-btn-icon>
        </view>
        <view>
            <talk/>
            <talk/>
            <talk/>
            <talk/>
            <talk/>
            <talk/>
            <talk/>
            <talk/>
        </view>
        <t-tabbar/>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import talk from "@/components/Message/talk.vue";
    import messageHeader from "@/components/Message/header.vue";
    // store
    import { useMessageIconPath } from "@/store/dataBase";
    const iconPath = useMessageIconPath();
// DATA

// FUNC

</script>

<style scoped>

.btn-container {
    margin: 0 30px;
}

</style>        