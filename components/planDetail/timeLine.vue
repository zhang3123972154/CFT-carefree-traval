<template>
    <view class="flex-center-horizontal gap-10">
        <up-icon class="icon" size="25" :name="iconPath.timedown"/>
        <view class="flex-center-vertical time">
            <text>{{ props.timeStart }}</text>
            <text>{{ props.timeEnd }}</text>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({
        timeStart: {
            type: String,
            default: "00：00"
        },
        timeEnd: {
            type: String,
            default: "23：59"
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.time {
    font-size: 10px;
    line-height: 12px;
    font-weight: 300;
    color: #333333;
}

.icon {
    width: 10px;
    height: 25px;
}

</style>        