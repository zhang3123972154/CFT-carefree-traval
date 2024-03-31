<template>
    <tabberBase @click.stop>
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
            <t-btn-icon :icon="iconPath.add"></t-btn-icon>
        </template>
    </tabberBase>
    <pathFloatWin :spot="plan.spot" :day="plan.day" :path="plan.path"/>
</template>

<script setup>
    import { ref } from "vue";
    import { pathPoint } from "@/js/struct";
    // com
    import tabberBase from "../Com/tabberBase.vue";
    import pathFloatWin from "../../components/AiTalk/pathFloatWin.vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

        // flag
    const topFlodFlag = ref(true);
    const plan = ref({
        spot: "武汉",
        day: 1,
        path: [
            pathPoint("民宿"),
            pathPoint("地点", ["热干面", "三鲜豆皮"]),
            pathPoint("黄鹤楼"),
            pathPoint("楚河汉街", ["鄂菜"]),
            pathPoint("武汉大学", ["樱花"]),
            pathPoint("街道口"),
            pathPoint("武汉长江大桥", ["江景"]),
            pathPoint("民宿"),
        ]
    })

// FUNC

</script>

<style scoped>

</style>        