<template>
    <view class="container">
        <view class="item" v-for="(item, index) in props.historyList" :key="index">
            <t-chip kind="history" :text="item"></t-chip>
        </view>
        <!--todo 完善函数：更多历史记录-->
        <view class="item" @click="">
            <t-chip kind="history">
                <u-icon class="icon" :name="iconPath.downHistory"></u-icon>
            </t-chip>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
    import { useArrowsIconPath } from "@/store/dataBase";
    const iconPath = useArrowsIconPath();

// DATA
    const props = defineProps({
        historyList: {
            type: Array,
            default: () => ["雅鲁藏布江", "哈尔滨", "武汉在哪看樱花", "暑假去哪玩比较合适"]
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    gap: 9px;
}

.item {
    flex-shrink: 0;
    /* 防止子控件缩放 */
}

.icon {
    padding: 5px;
}

</style>        