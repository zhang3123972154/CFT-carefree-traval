<!--info 适用于 单一样式 展示-->
<template> 
    <view>  <!--mark 用来档外部布局的控制-->
        <view class="container" :class="{'scroll': props.scroll}">

            <slot name="prefix"></slot>
            <!--info main-->
            <view class="item" v-for="(item, index) in props.tagList" :key="index">
                <t-chip 
                    :kind="props.tagKind" 
                    :text="item"
                    :light="props.light"
                    :belongChipGroupSingle="props.reply"
                    @clickChoose="emits('clickChoose', index)">
                </t-chip>
                <!--info belongChipGroupFlex 启用点击监听-->
            </view>

            <slot name="suffix"></slot>
        </view> 
    </view>
</template>

<script setup>
    // import { computed } from 'vue';

// DATA
    const props = defineProps({
        // data
        tagKind: String,
        tagList: Array,
        // funtion
        scroll: {   // 是否启用长轴滚动。
            type: Boolean,
            default: false
        },
        light: Boolean,
        reply: Boolean
    });
    const emits = defineEmits(["clickChoose"]);

// FUNC

</script>

<style scoped>

.container {
    width: 100%;    /* mark 块级元素 */
    display: flex;
    flex-wrap: wrap;
    gap: 9px;
}

.scroll {
    flex-wrap: nowrap;
    overflow-x: auto;
}

.item {
    flex-shrink: 0; /* 防止子控件缩放 */
}

</style>