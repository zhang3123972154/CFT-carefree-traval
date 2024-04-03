<template>
    <view class="flex-vertical gap-5">
        <t-header/>
        <view class="flex-center-both search-container">
            <search thin/>
        </view>
        <view class="waterfall">
            <template v-for="(item, index) in spotList" :key="index">
                <card :img-path="item.imgPath" :tag-kind="item.tagKind"/>
            </template>
        </view>
        <t-tabbar/>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import tHeader from '@/components/Com/header.vue';
    import search from "@/components/Com/search.vue";
    import card from '@/components/Punch/card.vue';

// DATA
    const spotList = ref([
        {imgPath: "/static/example/spot/pk-1.png", tagKind: 1 },
        {imgPath: "/static/example/spot/pk-2.png", tagKind: 2 },
        {imgPath: "/static/example/spot/pk-3.png", tagKind: 0 },
        {imgPath: "/static/example/spot/pk-4.png", tagKind: 0 },
        {imgPath: "/static/example/spot/pk-5.png", tagKind: 0 },
        {imgPath: "/static/example/spot/pk-6.png", tagKind: 0 },
    ])
</script>

<style scoped>

.search-container {
    padding: 0 5px;
}

.waterfall {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 2px;
    width: 100vw;
}

</style>