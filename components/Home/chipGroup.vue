<template>
    <view>  <!--mark 用来档外部布局的控制-->
        <view class="container" :class="{'scroll': props.scroll}">
            <slot name="prefix"></slot>
            <!--info main-->
            <view class="item" v-for="(item, index) in props.wayList" :key="index">
                <t-chip kind="way" :text="item" :belongAiHeader="props.belongAiHeader"
                    @long-press="open" @close-similar-win="close"

                    :belongChipGroupFlex="props.belongChipGroupFlex"
                    :light-start="index < props.lightIndex"
                    @clickChoose="emits('clickChoose', index)" @clickDelete="emits('clickDelete', index)">
                </t-chip>
            </view>
            <view class="item" v-for="(item, index) in props.spotList" :key="index">
                <t-chip kind="spot" :text="item" 
                    :belongAiHeader="props.belongAiHeader" 
                    @long-press="open" @close-similar-win="close"
                    
                    :belongChipGroupFlex="props.belongChipGroupFlex"
                    :light-start="index < props.lightIndex"
                    @clickChoose="emits('clickChoose', index)" @clickDelete="emits('clickDelete', index)">
                </t-chip>
            </view>
            <view class="item" v-for="(item, index) in props.thingList" :key="index">
                <t-chip kind="thing" :text="item" :belongAiHeader="props.belongAiHeader"
                    @long-press="open" @close-similar-win="close"
                    
                    :belongChipGroupFlex="props.belongChipGroupFlex"
                    :light-start="index < props.lightIndex"
                    @clickChoose="emits('clickChoose', index)" @clickDelete="emits('clickDelete', index)">
                </t-chip>
            </view>

            <slot name="suffix"></slot>
        </view> 
    </view>
</template>

<script setup>
    // import { computed } from 'vue';

// DATA
    const props = defineProps({
        wayList: {    // 话题
            type: Array,
            default: ["徒步旅行", "打卡拍照", "人文", "一起去看海"] // mark 先用来演示一下，之后清空。
        }, 
        spotList: {
            type: Array,
            default: ["北京", "武汉", "归元禅寺", "江汉村", "黄鹤楼"]
        },
        thingList: { // 食物
            type: Array,
            default: ["热干面", "汤包", "茶颜悦色", "湘菜", "鄂菜"]
        },
        scroll: {   // 是否启用长轴滚动。
            type: Boolean,
            default: false
        },
        belongAiHeader: {
            type: Boolean,
            default: false
        },
        lightIndex: {   // info 默认的前多少个会点亮，但暂时，只适用于单类别
            type: Number,
            default: 0
        },
        belongChipGroupFlex: {
            type: Boolean,
            default: false
        }
    });
    const emits = defineEmits(['longPress', 'closeSimilarWin', "clickChoose", "clickDelete"]);

// FUNC
    // tag container 的切换
    const open = () => {
        emits("longPress");
    }

    const close = () => {
        emits("closeSimilarWin");
    }

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