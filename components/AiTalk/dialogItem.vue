<template>
    <view class="flex-center-horizontal">
        <text class="title">{{ props.title }}</text>
        <text class="time">{{ props.time }}</text>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        time: {
            type: String,
            default: "2024-4-20"
        },
        title: {
            type: String,
            default: "默认子标题"
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.title {
    font-size: 14px;
}

.time {
    color: #989898;
    font-size: 14px;
}
</style>        