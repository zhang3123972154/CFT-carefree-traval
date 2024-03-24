<template>
    <view class="flex-vertical main-container gap-5" @click.stop>
        <view class="flex-center-horizontal block-width">
            <view class="flex-center-horizontal">
                <t-btn-icon :icon="iconPath.reloadWin"></t-btn-icon>
                <t-btn-icon :icon="iconPath.shrink"></t-btn-icon>
            </view>
            <t-btn-icon :icon="iconPath.close"></t-btn-icon>
        </view>
        <slot></slot>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
    import { useWindowIconPath } from "@/store/dataBase";
    const iconPath = useWindowIconPath();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.main-container {
    width: 320px;
    padding: 10px;

    padding-bottom: 30px;

    background-color: #fff;
    border-radius: 20px;
}

</style>        