<template>
    <headerBase back-delete bg-transparent>
        <template #prefix>
            <t-btn-icon :icon="iconPath.back" @click="back"></t-btn-icon>
        </template>
        <template #midfix>
            <text class="title">规划表</text>
        </template>
        <template #suffix>
            <t-btn-icon :icon="iconPath.share"></t-btn-icon>
        </template>

        <template #flodfix>
            <!-- <u-icon class="img-bg" name="/static/example/background/baskground.svg" :size="phoneInfor.phoneWidth + 50"/> -->
            <up-image class="img-bg" lazy-loding fade 
                src="/static/example/background/bg-default.png" 
                :width="phoneInfor.phoneWidth"
                :height="phoneInfor.phoneWidth + 50"/>
        </template>
    </headerBase>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import headerBase from "../Com/headerBase.vue";
    // store
    import { usePlanIconPath } from "@/store/dataBase";
    const iconPath = usePlanIconPath();
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInfor = usePhoneInfor();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

// FUNC
    const back = () => {
        uni.navigateBack({ delta: 1 })
    }

</script>

<style scoped>

.title {
    font-weight: 300;
    font-size: 14px;
    line-height: 12px;
    color: #cccccc;
}

.img-bg {
    position: fixed;
    top: 0;
}

</style>        