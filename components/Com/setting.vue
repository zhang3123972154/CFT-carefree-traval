<template>
    <!-- <u-popup :show="showFlag" mode="right" overlayOpacity="0.3"
        :customStyle="{
            borderBottomLeftRadius: '20px'
        }" @close="close"> -->
        <view class="container top-container">
            <Box title="旅行风格" :tag-list="Array1"/>
            <Box title="旅行季节" :tag-list="Array2"/>
            <Box title="旅行人群"/>
            <Box title="旅行城市"/>
            <Box title="旅行出发" :tag-list="Array3"/>
            <!-- <view class="spacer"></view> -->
            <view class="flex-center-horizontal btn-group">
                <u-button :customStyle="{
                    padding: '10px 0',
                    borderRadius: '15px',
                    height: '40px',
                    border: 'solid 1px #666666'
                }">重置</u-button>
                <u-button :customStyle="{
                    padding: '10px 0',
                    borderRadius: '15px',
                    height: '40px',
                    backgroundColor: '#ffc300',
                    color: '#ffffff'
                }">完成</u-button>
            </view>
        </view>
    <!-- </u-popup> -->
</template>

<script setup>
    import { ref } from "vue";
    // store
    import usePhoneStore from "@/store/phoneInfor";
    const phoneInfor = usePhoneStore();

    import Box from "../../components/Home/tagBox.vue";

// DATA
    const props = defineProps({
    });
    const emits = defineEmits(['close']);

    // db
    const Array1 = ref(["打卡拍照", "徒步出行", "穷游", "旅行搭子", "人文风景", "自然风景",
            "娱乐商圈", "甜品饮品", "公园乐园", "美食小吃", "海景江景", "爬山"]);
    const Array2 = ref(["春天", "夏天", "秋天", "冬天"]);
    const Array3 = ref(["高铁火车", "飞机", "游轮", "大巴", "自驾游"]);

// FUNC

</script>

<style scoped>

.container {
    padding: 18px 15px 32px 20px;
    height: 100%;

    display: flex;
    flex-direction: column;
    gap: 25px;
    
    background-color: #fff;
    /* border-bottom-left-radius: 20px; */

    /* transition: width 0.5s ease-in-out; */
}

.btn-group {
    position: absolute;
    bottom: 15px;
    width: 88%;

    gap: 20px;
    
    line-height: 18.5px;
    font-size: 20px;
    font-family: SourceHanSansCN;

    background-color: #ffffff;
    padding: 10px 0;
}
</style>