<template>
    <view class="container flex-vertical gap-10">
        <view>
            <text class="title">搜索历史</text>
            <historyChips />
        </view>
        <view>
            <text class="title">推荐标签</text>
            <chipGroup />
        </view>
        <!--hot key-->
        <view class="container-fire flex-vertical gap-10">
            <view class="flex-horizontal gap-5">
                <u-icon :name="iconPath.fire"></u-icon>
                <text class="title-fire">逍遥游热点</text>
            </view>
            <view>
                <searchBox v-for="(item, index) in hotSearch" :key="index" :index="index + 1" :content="item.content"
                    :num="item.searchNum" />
            </view>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import chipGroup from "@/components/Home/chipGroup.vue";
    import historyChips from "@/components/Home/historyChips.vue";
    import searchBox from "@/components/Home/searchBox.vue";
    // store
    import { useComIconPath } from "@/store/dataBase";
    const iconPath = useComIconPath();
// DATA
    const hotSearch = ref([
        { content: "稻城亚丁旅行攻略", searchNum: "1180" },
        { content: "一定不能错过的风景", searchNum: "973" },
        { content: "比起乌镇，我更爱这个江南水乡", searchNum: "921" },
        { content: "最适合学生穷游的城市", searchNum: "861" },
        { content: "令人向往的地方", searchNum: "854" },
        { content: "那些再也拍不出第二张的人生照片", searchNum: "819" },
        { content: "旅途中那些转瞬即逝的瞬间", searchNum: "694" },
        { content: "11-12月最佳旅行推荐", searchNum: "567" },
        { content: "秋冬的大理", searchNum: "514" },
        { content: "旅行究竟追求什么", searchNum: "508" },
    ]);
// FUNC

</script>

<style scoped>

.container {
    padding: 10px;
}

.title {
    margin-left: 20px;

    font-size: 14px;
    font-family: SourceHanSansCN;
    line-height: 13px;
    color: #000000;
}

.container-fire {
    padding: 0 10px;
}

.title-fire {
    font-size: 14px;
    font-family: SourceHanSansCN;
    line-height: 13px;
    color: #ffc300;
}

</style>