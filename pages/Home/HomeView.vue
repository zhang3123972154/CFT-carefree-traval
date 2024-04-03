<template>
    <view @click="baseClick">
        <!--top function-->
        <t-header/>
        <!--star-->
        <!--todo star的调整-->
        <starVue />
        <!--下层瀑布流信息-->
        <view class="m-5">
            <!--Search-->
            <view class="search-container">
                <Search/>
            </view>
            <!--waterfall-->
            <view class="waterfall gap-5">
                <template v-for="(item, index) in cardList" :key="index">
                    <Card 
                    :img-path="item.imgPath" 
                    :img-width="phoneInforStore.cardWidth_2"
                    @click="detail(index)"/>
                </template>
            </view>
            <!-- <Test/> -->
        </view>
        <!--end-->
        <t-tabbar />
    </view>
</template>

<script setup>
    import { ref } from 'vue';
    // store
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInforStore = usePhoneInfor();
    // com
    import Search from "@/components/Com/search.vue";
    import Card from "@/components/Home/card.vue";
    import planStartVue from './planStart.vue';
    import starVue from './star.vue';
    import tHeader from '@/components/Com/header.vue';

// DATA
    const cardList = ref([  
        {imgPath: "/static/example/Nothing.jpg" },
        {imgPath: "/static/example/avatar.png" },
        {imgPath: "/static/example/spot/pk-1.png" },
        {imgPath: "/static/example/spot/pk-2.png" },
        {imgPath: "/static/example/avatar.png" },
        {imgPath: "/static/example/avatar.png" },
    ])

// FUNC
    // click
    const baseClick = () => {   // mark 最基本的全局监听点击事件，
        uni.$emit("baseClick");
    }

</script>

<style scoped>

/* star */
.star-container {
    position: relative;
    height: 300px;
}

.waterfall {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 2px;
    /* height: calc(100% - 50px); */
    width: 100vw;
}

.search-container {
    position: sticky;   /* todo 如何检测到sticky的触发。 */
    top: 0px;
    z-index: 3000;
    padding: 5px 0;

    background-color: #fff;
}

</style>