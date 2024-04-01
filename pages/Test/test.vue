<!--mark AITALK 的内容-->
<!--info 第一版-->
<template>
    <view class="flex-vertical">
        <header-ai></header-ai>
        <view class="flex-vertical container-dialogue gap-10">
            <userBubble/>
            <aiBubble/>
            <template v-for="(item, index) in dialogTEST" :key="index">
                <ai-bubble v-if="item.side" :content="item.content"/>
                <user-bubble v-else :content="item.content"/>
            </template>
        </view>
        <tabber-ai></tabber-ai>
    </view>
    <!--top flod window-->
    <!--todo 需要自定义了。-->
    <!-- <u-popup :show="topFlodFlag" mode="top" round="20" safeAreaInsetTop
        @click="topFlodFlag = false" :custom-style="{
            marggin: '20px'
        }">
        <chipGroup class="top-flod-container"></chipGroup>
    </u-popup> -->
</template>

<script setup>
    import { ref } from "vue";
    // com
    import headerAi from "@/components/AiTalk/header.vue";
    import tabberAi from "@/components/AiTalk/tabber.vue";
    import userBubble from "@/components/AiTalk/userBubble.vue";
    import aiBubble from "@/components/AiTalk/aiBubble.vue";
    
    import chipGroup from "@/components/Home/chipGroup.vue";
    // store
// DATA
    const dialogTEST = ref([
        {
            side: false,    // 代表 user
            content: "三月想出门旅行，推荐去哪儿？"
            // todo time: "2023-12-10-XXX"
        },
        {
            side: true,
            content: [
                { type: "text", text: "三月是" },
                { type: "thing", text: "樱花" },
                { type: "text", text: "盛开的季节，三月份去" },
                { type: "spot", text: "武汉"},
                { type: "text", text: "旅游一定是不错之选。"},
                { type: "endl" },
                {
                    type: "PT-spot", text: "东湖樱花园",
                    grade: 4.9, location: "武昌区鲁磨路665号", 
                    price: 55, imgPath: "/static/example/background/spot-1.png"
                },
                {
                    type: "PT-spot", text: "黄鹤楼",
                    grade: 4.8, location: "武昌区蛇山西山坡特2号", 
                    price: 70, imgPath: "/static/example/background/spot-2.png"
                },
                {
                    type: "PT-spot", text: "武汉大学",
                    grade: 4.9, location: "武昌区八一路299号", 
                    price: 0, imgPath: "/static/example/background/spot-3.png"
                }
                // { type: "spot", text: "东湖樱花园" },
                // { type: "text", text: "是武汉市规模最大、品种最多的樱花园之一。" },
                // { type: "endl" },
                // { type: "spot", text: "武汉大学" },
                // { type: "text", text: "是武汉市最有影响力的赏樱景区。" },
                // { type: "endl" },
                // { type: "spot", text: "黄鹤楼" },
                // { type: "text", text: "公园是武汉著名的历史文化风景区之一。" },
            ]
        },
        {
            sied: 0,
            content: "武汉有什么景点推荐？"
        },
        {
            side: true,
            content: [
                { type: "text", text: "三月是" },
                { type: "thing", text: "樱花" },
                { type: "text", text: "盛开的季节，三月份去旅游一定是不错之选。" },
                { type: "endl" },
                { type: "spot", text: "东湖樱花园" },
                { type: "text", text: "是武汉市规模最大、品种最多的樱花园之一。" },
                { type: "endl" },
                { type: "spot", text: "武汉大学" },
                { type: "text", text: "是武汉市最有影响力的赏樱景区。" },
                { type: "endl" },
                { type: "spot", text: "黄鹤楼" },
                { type: "text", text: "公园是武汉著名的历史文化风景区之一。" },
            ]
        },
    ])

// FUNC

</script>

<style scoped>

.container-dialogue {
   background-color: #f9f9f9;
   min-height: calc(100vh - 100px);

   padding: 5px 10px;
}

.top-flod-container {
    padding: 20px;
}

</style>        