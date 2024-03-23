<template>
    <view class="container flex-col gap-5">
        <view class="img-container">
            <up-image :src="props.src" lazy-load fade :width="props.srcWidth" mode="aspectFill"
                :height="props.srcHeight" :radius="props.srcRadius">
            </up-image>
            <view class="flex-horizontal location">
                <u-icon :name="iconPath.location" size="8"></u-icon>
                <!--update 文字颜色 可能和背景图片混淆-->
                <text class="ml-5">{{ props.locationCity }}</text>
                <text class="ml-10">{{ props.locationSpot }}</text>
            </view>
        </view>
        <view class="text-container" :style="{'--card-width': props.srcWidth + 'px'}">
            <text class="title">{{ props.title }}</text>
            <!--information-->
            <view class="flex-center-horizontal information">
                <view class="flex-horizontal user-container">
                    <up-avatar :src="props.avatar" size="20"></up-avatar>
                    <text class="ml-10">{{ props.userName }}</text>
                </view>
                <view class="flex-center-horizontal like-container">
                    <u-icon size="14" :name="likeFlag ? iconPath.likeSelected : iconPath.like" @click="handleLike"></u-icon>
                    <text class="ml-4" :class="{'like-text': likeFlag}">{{ likeNum }}</text>
                </view>
            </view>
        </view>
    </view>

</template>

<script setup>
    import { ref } from "vue";
    // store
    import { useCardIconPath } from "@/store/dataBase";
    const iconPath = useCardIconPath();
// DATA
    const props = defineProps({
        // photos Setting
        src: {
            type: String,
            default: "/static/example/Nothing.jpg"
        },
        srcWidth: {
            type: [Number, String],
            default: 180
        },
        srcHeight: {
            type: [Number, String],
            default: 200
        },
        srcRadius: {
            type: [Number, String],
            default: 10
        },
        // base information
        locationCity: {
            type: String,
            default: "默城"
        },
        locationSpot :{
            type: String,
            default: "默景"
        },
        title: {
            type: String,
            default: "默认标题"
        },
        avatar: {
            type: String,
            default: "/static/example/Avatars_noLogin.png"
        },
        userName: {
            type: String,
            default: "默认用户名"
        },
        likeNum: {
            type: [String, Number],
            default: 0
        }
    });

    // num
    const likeNum = ref(props.likeNum);
    // flag
    const likeFlag = ref(false);

// FUNC
    const handleLike = () => {
        if(likeFlag.value)
            likeNum.value -= 1;
        else
            likeNum.value += 1;

            likeFlag.value = !likeFlag.value;
    }

</script>

<style scoped>

.container {
    flex: 1 1 180px;
    background-color: #ffffff;
    border-radius: 10px;
}

.img-container {
    position: relative;
    border-radius: 10px;
    height: 200px;
}

.text-container {
    width: var(--card-width);
}

.location {
    position: absolute;
    bottom: 12px;
    left: 15px;

    /* font */
    font-size: 8px;
    font-family: SourceHanSansCN;
    line-height: 9px;
    color: #ffffff;
}

.title {
    font-size: 14px;
    font-family: SourceHanSansCN;
    line-height: 13px;
    color: #000000;
    
    margin-left: 6px;
}

.information {
    padding: 0 4px;
}

.user-container {
    font-size: 10px;
    font-family: SourceHanSansCN;
    line-height: 9px;
    font-weight: 300;
    color: #999999;
}

.like-container {
    font-size: 12px;
    font-family: SourceHanSansCN;
    line-height: 9px;
    font-weight: 300;
    color: #999999;
    line-height: 9px;
}

.like-text {
    color: #FF2F00;
}

</style>