<template>
    <!--info 强行捕获 touchmove-->
    <view @click="closeFunctionWin" @touchstart="closeFunctionWin" 
        @touchmove.capture="scrollHandle" @touchend.capture="scrollEnd">
        <!--top function-->
        <t-header/>
        <!--star-->
        <!--todo star的调整-->
        <starVue @touchstart="closeFunctionWin"/>
        <!--下层瀑布流信息-->
        <view class="m-5 watch-container">
            <!--bug 需要条件判断。Search-->
            <view
            class="search-container" 
            :class="{
                'search-container-top': searchZIndex
            }"
            :style="{
                '--status-bar-height': phoneInforStore.statusBarHeight.toString() + 'px'
            }">
                <Search @click="gotoSearch"/>
            </view>
            <!--waterfall-->
            <view class="waterfall gap-5 of-x-hidden">
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
    import { ref, onMounted, computed } from 'vue';
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
    //flag
    const searchTouchTop = ref(false);
    const searchTouchHeader = ref(false);
    const searchZIndex = ref(false);
    let scrollFlag = true;
    const scrollTime = computed(() => {  // Info 不同情况的两种触发频率
        return searchTouchHeader.value ? 50 : 100;
    })

// FUNC
    // click
    const closeFunctionWin = () => {   // mark 最基本的全局监听点击事件，
        uni.$emit("baseClick");
    }

    const detail = (index) => {
        uni.navigateTo({ url: '/pages/PostDetail/postDetailView' })
    }

    const gotoSearch = () => {
        if(searchTouchTop.value)
            uni.navigateTo({
                url: "/pages/Search/SearchView",
                animationType: 'fade-in',
            })
    }

    const query = uni.createSelectorQuery().in(this).select(".watch-container");
    const checkSearch = () => {
        query.boundingClientRect((data) => {
            // console.log("得到布局位置信息" + JSON.stringify(data));
            console.log("节点离页面顶部的距离为" + data.top);
            if(data.top <= phoneInforStore.statusBarHeight + 60)
                searchTouchHeader.value = true;
            else {
                searchTouchHeader.value = false;
                // searchZIndex.value = false;
            }

            if(data.top <= phoneInforStore.statusBarHeight)
                searchTouchTop.value = true;
            else
                searchTouchTop.value = false;
        }).exec();
    }

    const scrollHandle = (event) => {
        if(scrollFlag) {
            // info 触发过于频繁
            scrollFlag = false;
            setTimeout(() => {
                scrollFlag = true;
            }, scrollTime.value);

            checkSearch();
        }

        if(!searchZIndex.value)
            searchZIndex.value = true;
    }

    const scrollEnd = () => {
        if(!searchTouchHeader.value)
            searchZIndex.value = false;
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
}

.search-container {
    position: sticky;   /* todo 如何检测到sticky的触发。 */
    top: var(--status-bar-height);

    background-color: #fff;
    padding: 1px 0;
}

.search-container-top {
    z-index: 3000;
}

.search-blank {
    position: absolute;
    top: var(--height-top);

    width: 100%;
    background-color: #fff;

    height: var(--height);
}

</style>