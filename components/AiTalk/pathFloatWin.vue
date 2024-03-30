<template>
    <view v-if="!openFlag" class="open-beside" @click="openFlag = true">
        <view class="line"></view>
    </view>
    <floatBase v-else shadow>
        <view class="float-container">
            <view class="flex-center-horizontal">
                <view class="time-container flex-horizontal gap-10">
                    <text>DAY {{ props.day }}</text>
                    <text class="spot-font">{{ props.spot }}</text>
                </view>
                <view class="flex-horizontal gap-20">
                    <u-icon :name="iconPath.detail" size="24"></u-icon>
                    <u-icon :name="iconPath.LOGO" size="20"></u-icon>
                </view>
            </view>
            <chipGroupPath class="chips-container" :path="props.path"/>
        </view>
    </floatBase>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import floatBase from "../Com/floatBase.vue";
    import chipGroupPath from "./chipGroupPath.vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({
        day: {
            type: [Number, String],
            default: 1
        },
        spot: {
            type: String,
            default: "默认地点"
        },
        path: {
            type: Array,
            default: [{
                spot: "暂无规划"
            }]
        }
    });
    const emits = defineEmits([]);

    // flag
    const openFlag = ref(false);
// FUNC

</script>

<style scoped>

.open-beside {
    position: fixed;
    right: 0;
    bottom: 72px;   /* 可以写成可拖动 */

    width: 20px;
    height: 60px;

    border-radius: 10px 0 0 10px;
    border: 1px solid #CCCCCC66;
    border-right: none;
    background-color: #00000066; /* todo */

    box-sizing: border-box;
}

.line {
    position: relative;
    top: 14px;
    left: 5px;

    width: 1px;
    height: 30px;
    border-radius: 1px;

    background-color: #cccccc66;
}

.float-container {
    padding: 5px 15px 15px;
}

.time-container {
    padding: 0 20px;
    background-color: #ffc300;
    border-radius: 15px;
    height: 40px;

    font-family: Alimama ShuHeiTi;
    font-size: 20px;
}

.spot-font {
    color: #FFF;
}

.chips-container {
    padding: 15px 0px 0;
}

</style>        