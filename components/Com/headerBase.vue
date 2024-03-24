<template>
    <view class="flex-center-horizontal container">
        <view class="flex-center-horizontal">
            <u-icon v-if="!props.backDelete" class="flex-center-both top-icon" size="18" :name="iconPath.left"></u-icon>
            <slot name="prefix"></slot>
        </view>
        <view class="flex-center-horizontal shrink">
            <slot name="midfix"></slot>
        </view>
        <view class="flex-center-horizontal">
            <slot name="suffix"></slot>
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
        backDelete: {
            type: Boolean,
            default: false
        },
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container {
    position: sticky;
    top: 0px;
    z-index: 10000;

    background-color: #fff;

    padding: 4px;
}


.top-icon {
    width: 40px;
    height: 40px;
}

</style>        