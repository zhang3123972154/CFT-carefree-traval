<template>
    <view class="flex-vertical main-container gap-5" @click.stop>
        <view class="flex-center-horizontal block-width">
            <view class="flex-center-horizontal">
                <t-btn-icon :icon="iconPath.reloadWin"></t-btn-icon>
                <t-btn-icon :icon="iconPath.shrink"></t-btn-icon>
            </view>
            <t-btn-icon :icon="iconPath.close"></t-btn-icon>
        </view>
        <!--wataerfall-->
        <view class="waterfall">
            <Card src-width="150" src-height="180" />
            <Card src-width="150" src-height="180" />
            <Card src-width="150" src-height="180" />
            <Card src-width="150" src-height="180" />
            <Card src-width="150" src-height="180" />
            <Card src-width="150" src-height="180" />
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import Card from "@/components/Home/card.vue";
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
    height: 75%;
    padding: 10px;

    background-color: #fff;
    border-radius: 20px;
}

.waterfall {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 5px;
    height: calc(100% - 50px);

    overflow-y: auto;
}

</style>        