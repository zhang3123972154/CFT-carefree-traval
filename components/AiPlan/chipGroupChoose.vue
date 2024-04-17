<template>
    <view class="flex-vertical gap-5">
        <view class="flex-center-horizontal">
            <text class="font">{{ props.title }}</text>
            <u-icon :name="openFlag ? iconPath.down : iconPath.up" 
                @click="openFlag = !openFlag"/>
        </view>
        <chip-group 
            :scroll="openFlag"
            :way-list="props.wayList"
            :spot-list="props.spotList"
            :thing-list="props.thingList"
            :light-index="9999"
            @click-choose="choose"
            @click-delete="choose"
            belongChipGroupFlex
        >
        </chip-group>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import chipGroup from "@/components/Home/chipGroup.vue";
    // store
    import { useArrowsIconPath } from "@/store/dataBase";
    const iconPath = useArrowsIconPath();
// DATA
    const props = defineProps({
        title: {
            type: String,
            default: "默认标题"
        },
        openStart: {    // info 用于初始化 openFlag 的形式
            type: Boolean,
            default: false
        },
        lightIndex: { // info 
            type: Number,
            default: 0
        },
        wayList: Array,
        spotList: Array,
        thingList: Array,
    });
    const emits = defineEmits(["choose"]);

    // flag
    const openFlag = ref(props.openStart);

// FUNC
    const choose = (index) => {
        emits("choose", index);
    }

</script>

<style scoped>

.font {
    font-family: Alimama ShuHeiTi;
    font-size: 15px;
}

</style>        