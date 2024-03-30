<template>
    <view class="container-base">
        <template v-for="(point, index) in props.path" ::key="index">
            <u-icon v-if="index != 0" :name="iconPath.arrow" size="10"></u-icon>

            <t-chip kind="spot" :text="point.spot"></t-chip>

            <t-chip 
                v-for="(thing, index) in point.things" :key="index"
                kind="thing" :text="thing">
            </t-chip>
        </template>
    </view> 
</template>

<script setup>
    import { ref } from "vue";
    import { pathPoint } from "@/js/struct";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({
        path: {
            type: Array,
            default: () => [
                pathPoint("地点", ["事物I", "事物II"]),
                pathPoint("地点", ["事物I", "事物II"]),
                pathPoint("地点", ["事物I", "事物II"]),
            ]
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container-base {
    width: 100%;    /* mark 块级元素 */
    display: flex;
    flex-wrap: wrap;
    gap: 5px;
}

.item {
    flex-shrink: 0; /* 防止子控件缩放 */
}

</style>        