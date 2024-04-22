<template>
    <view class="container flex-vertical gap-5">
        <view class="flex-horizontal gap-10 ml-10">
            <u-icon name="/static/icon/LOGO.svg"></u-icon>
            <text class="title">{{ props.title }}</text>
        </view>
        <slot></slot>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        title: {
            type: String,
            default: "默认标题"
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container {
    font-family: Alimama ShuHeiTi;
    font-size: 16px;
}

.title {
    font-size: 20px;
}

</style>        