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
            :light-index="setLightHold"
            @click-choose="choose"
            @click-delete="choose"
            :belongChipGroupFlex="props.lightHold"
        >
            <template v-if="props.inputAble" #prefix>
                <chip-editable light 
                    :clear-trigger="clearTrigger"
                    @text-finish="handleEditChip"
                />
            </template>
        </chip-group>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // com
    import chipGroup from "@/components/Home/chipGroup.vue";
    import chipEditable from "@/components/Com/chipEditable.vue";
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
        lightHold: { // info 
            type: Boolean,
            default: false
        },
        wayList: Array,
        spotList: Array,
        thingList: Array,
        inputAble: {    // info 启用 前置的 输入框
            type: Boolean,
            default: false
        },
        multipleChoice: {
            type: Boolean,
            default: false
        }
    });
    const emits = defineEmits(["choose", "add"]);

    // flag
    const openFlag = ref(props.openStart);
    const clearTrigger = ref(false);

// FUNC
    const setLightHold = computed(() => {
        return props.lightHold ? 9999 : 0;
    })

    // tag 
    const choose = (index) => {
        emits("choose", index);
    }
    const handleEditChip = (value) => {
        clearTrigger.value = !clearTrigger.value;   // info 实现重置的效果
        emits("add", value);
    }

</script>

<style scoped>

.font {
    font-family: Alimama ShuHeiTi;
    font-size: 15px;
}

</style>        