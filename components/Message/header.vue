<template>
    <headerBase back-delete>
        <template #prefix>
            <view class="divider">-----------</view>
        </template>
        <template #midfix>
            <text class="title">消息</text>
        </template>
        <template #suffix>
            <t-btn-icon :icon="iconPath.search"></t-btn-icon>
            <t-btn-icon :icon="iconPath.more"></t-btn-icon>
        </template>
    </headerBase>
</template>

<script setup>
    import { ref } from "vue";
    import headerBase from "../Com/headerBase.vue";
    // store
    import { useMessageIconPath } from "@/store/dataBase";
    const iconPath = useMessageIconPath();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.title {
    font-size: 18px;
}

.divider {
    color: #FFFFFF;
}

</style>        