<template>
    <view>  <!--mark 用来档外部布局的控制-->
        <view class="container" :class="{scroll: props.scroll}">
            <view class="item" v-for="(item, index) in props.topicList" :key="index">
                <t-chip kind="topic" :text="item"></t-chip>
            </view>
            <view class="item" v-for="(item, index) in props.locationList" :key="index">
                <t-chip kind="location" :text="item"></t-chip>
            </view>
            <view class="item" v-for="(item, index) in props.foodList" :key="index">
                <t-chip kind="food" :text="item"></t-chip>
            </view>
        </view> 
    </view>
</template>

<script setup>
    // import { reactive } from 'vue';

// DATA
    const props = defineProps({
        topicList: {    // 话题
            type: Array,
            default: ["徒步旅行", "打卡拍照", "人文", "一起去看海"] // mark 先用来演示一下，之后清空。
        }, 
        locationList: {
            type: Array,
            default: ["北京", "武汉", "归元禅寺", "江汉村", "黄鹤楼"]
        },
        foodList: { // 食物
            type: Array,
            default: ["热干面", "汤包", "茶颜悦色", "湘菜", "鄂菜"]
        },
        scroll: {   // 是否启用长轴滚动。
            type: Boolean,
            default: false
        }
    });


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