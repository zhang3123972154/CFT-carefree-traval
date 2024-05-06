<!--info 用 aitalk 一般的点位信息块展示-->
<template>
    <view class="flex-top-horizontal PT-container block gap-10">
        <!--bug chip会收到 text 的宽度影响-->
            <view class="relative">
            <up-image :src="props.imgPath" fade radius="5" width="80" height="80"/>
            <view class="img-grade">{{ props.grade }}</view>
        </view>
        <view class="shrink">
            <view class="flex-horizontal">
                <t-chip class="t-ship" :kind="props.type" :text="props.text"></t-chip>
                <view class="shrink"></view>
            </view>
            <text class="location-text">{{ props.introduce }}</text>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        type: {
            type: String,
            default: "spot"
        }, 
        text: {
            type: String,
            default: "默认点名"
        },
        grade: {
            type: Number,
            default: 4.9
        },
        location: {
            type: String,
            default: "XXX"
        },
        price: {
            type: Number,
            default: 0
        },
        imgPath: {
            type: String,
            default: ""
        },
        introduce: {
            type: String,
            default: "基本介绍"
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.PT-container {
    margin: 5px 0;
}


.img-grade {
    position: absolute;
    right: 3px;
    top: 3px;
    padding: 0 2px;

    border-radius: 3px;
    background-color: #00000080;

    font-size: 16px;
    font-family: Alimama ShuHeiTi;
    color: #ffc300;
}

.location-text {
    font-size: 10px;
    font-weight: 300;
    color: #a68f47;
}

.t-ship {
    flex-shrink: 0;
}

</style>        