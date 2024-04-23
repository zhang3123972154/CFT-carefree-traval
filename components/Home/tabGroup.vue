<template>
    <view class="flex-center-horizontal gap-20">
        <template v-for="(item, index) in tabList" :key="index">
            <view class="flex-vertical title-unchosed" @click="change(index)">
                <view :class="{
                    'title-chosed': index === tabIndex
                }">{{ item }}</view>
                <view v-show="index == tabIndex" class="sliding-block"/>
            </view>
        </template>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        tabList: Array
    });
    const emits = defineEmits(["changeIndex"]);

    // const tabList = ref(["地点", "关注", "规划", "推荐"]);
    const tabIndex = ref(0);
// FUNC
    const change = (index) => {
        if(index != tabIndex.value) {
            tabIndex.value = index;
            emits("changeIndex", index);
        }
        else if(index == 0) {
            // info 再次点击 地点项
            // uni.chooseLocation({
            //     success: function(res) {
                    
            //     }
            // })
            // uni.navigateTo({ url: '/components/linzq-citySelect/linzq-citySelect' })
        }
    }

</script>

<style scoped>

.sliding-block {
    height: 2px;
    border-radius: 2px;
    background-color: #ffc000;
}

.title-unchosed {
    font-size: 16px;
    color: #333333;
}

.title-chosed {
    font-size: 18px;
    color: #000000;
}

</style>        