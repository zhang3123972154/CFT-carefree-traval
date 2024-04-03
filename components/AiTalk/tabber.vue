<template>
    <tabberBase>
        <template #prefix>
            <t-btn-icon :icon="iconPath.voice"></t-btn-icon>
        </template>
        <template #midfix>
            <up-input shape="circle" border="none"
                fontSize="14"
                :customStyle="{
                    padding: '4px',
                    margin: '4px',
                    backgroundColor: '#f9f9f9',
                    border: 'solid 1px #eeeeee80',
                }">
            </up-input>
        </template>
        <template #suffix>
            <t-btn-icon :icon="iconPath.emoji"></t-btn-icon>
            <t-btn-icon :icon="iconPath.add" @click="questionFlag = true"></t-btn-icon>
        </template>
    </tabberBase>
    <pathFloatWin :spot="plan.spot" :day="plan.day" :path="plan.path"/>
    <!--悬浮窗户-->
    <u-overlay class="flex-center-both" :show="questionFlag" @click="questionFlag = false">
        <questionWin/>
    </u-overlay>
</template>

<script setup>
    import { ref } from "vue";
    import { pathPoint } from "@/js/struct";
    // com
    import tabberBase from "../Com/tabberBase.vue";

    import pathFloatWin from "@/components/AiTalk/pathFloatWin.vue";
    import questionWin from "./questionWin.vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

        // flag
    const topFlodFlag = ref(true);
    const questionFlag = ref(false);
    const plan = ref({
        spot: "武汉",
        day: 1,
        path: [
            pathPoint("traffic", "高铁"),
            pathPoint("spot", "民宿"),
            pathPoint("spot", "地点", ["热干面", "三鲜豆皮"]),
            pathPoint("spot", "黄鹤楼"),
            pathPoint("spot", "楚河汉街", ["鄂菜"]),
            pathPoint("spot", "武汉大学", ["樱花"]),
            pathPoint("spot", "街道口"),
            pathPoint("spot", "武汉长江大桥", ["江景"]),
            pathPoint("spot", "民宿"),
        ]
    })

// FUNC

</script>

<style scoped>

</style>        