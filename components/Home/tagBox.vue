<template>
    <view class="flex-col gap11">
        <view class="flex-row justify-between items-center" @click="openFlag = !openFlag">
            <text class="title">{{ props.title }}</text>
            <t-btn-icon :icon="openFlag ? iconPath.down : iconPath.up"
                icon-size="11" btn-size="11" radius="4" class="icon">
            </t-btn-icon>
        </view>
        <!--content-->
        <view class="tags-container" :style="{'height': openFlag ? getHeight : '0'}">
            <t-tag v-for="(text, index) in props.tagList" :key="index" :text="text">
            </t-tag>
        </view>
    </view>
</template>

<script setup>
    import { ref, computed, watch } from "vue";
    // store
    import { useArrowsIconPath } from "@/store/dataBase.ts";
    const iconPath = useArrowsIconPath();
    // com
    import tTag from "./tag.vue";

// DATA
    const props = defineProps({
        index: {
            type: Number,
            default: 0
        },
        title: {
            type: String,
            default: "默认标题"
        },
        tagList: {
            type: Array,
            default: () => ["敬请期待"]
        },
        closeFlag: {
            type: Boolean,
            default: false
        }
    });
    const emits = defineEmits(['open']);

    // flag
    const openFlag = ref(false);

// FUNC
    // css
    const colNum = 3;
    const getHeight = computed(() => {
        const rowNum = Math.ceil(props.tagList.length / colNum);
        return (rowNum * 41 - 11).toString() + 'px';
    });

    // close
    // todo 增加 [每次开放一个窗口] 的限制 ？ 需要根据 Array 的长度来判断。
    watch(() => props.closeFlag, () => {
        openFlag.value = props.closeFlag;
    });


</script>

<style scoped lang="css">

/* top */
.title {
  font-size: 14px;
  font-family: SourceHanSansCN;
  line-height: 13px;
  color: #000000;
}

.icon {
  margin-right: 24px;
}

.gap11 {
    gap: 11px;
}

.tags-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 11px;

    overflow: hidden;
    transition: height 0.3s ease-in-out;
}

</style>