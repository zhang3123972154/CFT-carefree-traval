<template>
    <view class="flex-center-both container" :class="{'selected': selectedFlag}" @click="selectedFlag = !selectedFlag">
        <text>{{ props.text }}</text>
    </view>
</template>

<script setup>
    import { ref } from "vue";

// DATA
    const props = defineProps({
        text: {
            type: String,
            default: "默认"
        }
    })

    // flag
    const selectedFlag = ref(false);

</script>

<style scoped>

.container {
    font-size: 14px;
    font-family: SourceHanSansCN;
    line-height: 13px;
    font-weight: 300;

    padding: 8px 0;
    height: 30px;
    width: 78.5px;

    border-radius: 10px;
    background-color: #f9f9f9;
}

.view {
  flex: 1 1 80px;
}

.selected {
    color: #ffc300;
    border: solid 1px #ffc300;
    background-color: #ffc3001a;
}
</style>
