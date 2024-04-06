<template>
    <view class="flex-vertical container">
        <template v-for="([icon, text], index) in getContent" :key="index">
            <view class="flex-horizontal func-row block-width" @click="test(index)">
                <u-icon class="icon" :name="icon"></u-icon>
                <text>{{ text }}</text>
            </view>
        </template>
    </view>
</template>

<script setup>
    import { computed } from "vue";
    import { zip } from "@/js/data";
    // store
    import { useIconExtendBoxPath } from '@/store/dataBase';
    const iconExtendPath = useIconExtendBoxPath();

// DATA
    const emits = defineEmits([]);
    const iconPaths = iconExtendPath.getHomeFunctionsIconPath();
    const text = ["扫一扫", "添加好友", "查找群聊", "发表笔记"];

// FUNC
    const getContent = computed(() => {
        return zip(iconPaths, text);
    })

    const test = (index) => {
        console.info(index);
    }

</script>

<style scoped>

.container {
    width: 120px;
    border-radius: 10px;
    
    background-color: #f9f9f9;
    z-index: 9999;  /* mark 很神奇的BUG，在没有zIndex的时候，无法点击 */
}

.func-row {
    height: 41px;
    width: auto;
    padding: 10px;
    box-sizing: border-box;
    gap: 15px;

    font-size: 14px;
    font-family: SourceHanSansCN;
    line-height: 13px;
    font-weight: 300;
    color: #000000;
    
    background-color: transparent;
    border-radius: 10px;
    
    transition: background-color 0.3s ease;
}

.func-row:active {
    background-color: #0000000d;
    border-radius: 10px;
}

.icon {
    width: 21px;
}

</style>