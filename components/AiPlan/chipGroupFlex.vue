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
            :light-index="props.lightIndex"
            @click-choose="clickChoose"
            @click-delete="clickDelete"
            belongChipGroupFlex
        >
            <template #prefix>
                <chip-editable light 
                    :clear-trigger="clearTrigger"
                    @text-finish="handleEditChip"
                />
            </template>

            <template v-if="!props.noReload" #suffix>
                <t-chip :kind="props.sufKind" light @click="emits('reload')">
                    <u-icon name="/static/icon/change-chip.svg"></u-icon>
                </t-chip>
            </template>

        </chip-group>
    </view>
</template>

<script setup>
    import { ref } from "vue";
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
        closeStart: {    // info 用于初始化 openFlag 的形式
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
        // tag 前后操作的功能键 样式
        preKind: String,
        sufKind: String,
        noReload: {    // info 关闭后置 reload 按钮
            type: Boolean,
            default: false
        }
    });
    const emits = defineEmits(["add", "reload", "delete"]);
    
    // flag
    const openFlag = ref(props.closeStart);
    const clearTrigger = ref(false);

// FUNC
    const handleEditChip = (value) => {
        clearTrigger.value = !clearTrigger.value;   // info 实现重置的效果
        emits("add", value);
    }
    const clickChoose = (index) => {
        emits("add", index);
    }
    const clickDelete = (index) => {
        console.info(index);
        emits("delete", index);
    }


</script>

<style scoped>

.font {
    font-family: Alimama ShuHeiTi;
    font-size: 15px;
}

</style>        