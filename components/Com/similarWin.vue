<template>
    <view class="flex-vertical main-container gap-5" @click.stop>
        <view class="flex-center-horizontal block-width">
            <view class="flex-center-horizontal">
                <t-btn-icon :icon="iconPath.reloadWin"></t-btn-icon>
                <t-btn-icon :icon="iconPath.shrink"></t-btn-icon>
            </view>
            <t-btn-icon :icon="iconPath.close" @click="emits('close')"></t-btn-icon>
        </view>
        <!--wataerfall-->
        <view class="waterfall">
            <Card :img-width="phoneInforStore.cardWidth_similarWin_2" img-height="180" />
            <Card :img-width="phoneInforStore.cardWidth_similarWin_2" img-height="180" />
            <Card :img-width="phoneInforStore.cardWidth_similarWin_2" img-height="180" />
            <Card :img-width="phoneInforStore.cardWidth_similarWin_2" img-height="180" />
            <Card :img-width="phoneInforStore.cardWidth_similarWin_2" img-height="180" />
            <Card :img-width="phoneInforStore.cardWidth_similarWin_2" img-height="180" />
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import Card from "@/components/Home/card.vue";
    // store
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInforStore = usePhoneInfor();
    import { useWindowIconPath } from "@/store/dataBase";
    const iconPath = useWindowIconPath();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits(["close"]);

// FUNC

</script>

<style scoped>

.main-container {
    width: 90%;
    height: 80%;
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