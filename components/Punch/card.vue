<template>
    <view class="flex-center-vertical gap-10 container">
        <view class="relative">
            <!--info 右上角的标签-->
            <view v-if="getTagColor.flag" class="tag flex-center-both" :style="{
                '--bg-color': getTagColor.bg,
                '--font-color': getTagColor.font
            }">
                {{ getTagColor.text }}
            </view>
            <up-image :src="props.imgPath" lazy-load fade :width="props.imgWidth" mode="aspectFill"
                :height="props.imgHeight" :radius="props.imgRadius">
            </up-image>
            <!--info 地点 tag-->
            <view class="flex-horizontal location">
                <u-icon :name="iconPath.location" size="8"></u-icon>
                <!--update 文字颜色 可能和背景图片混淆-->
                <text class="ml-5">{{ props.locationCity }}</text>
                <text class="ml-10">{{ props.locationSpot }}</text>
            </view>
        </view>
        <view class="text-container" :style="{'--card-width': props.imgWidth + 'px'}">
            <text class="title">{{ props.title }}</text>
            <view class="flex-horizontal user-container gap-10">
                <u-avatar-group
                    :urls="props.avatarList"
                    size="20"
                    gap="0.2"
                    maxCount="3"
                ></u-avatar-group>
                <text>等{{ props.userNum }}人在线</text>
            </view>
        </view>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // store
    import { useCardIconPath } from "@/store/dataBase";
    const iconPath = useCardIconPath();
// DATA
    const props = defineProps({
        // photos Setting
        imgPath: {
            type: String,
            default: "/static/example/spot/pk-1.png"
        },
        imgWidth: {
            type: [Number, String],
            default: 180
        },
        imgHeight: {
            type: [Number, String],
            default: 180
        },
        imgRadius: {
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
        avatarList: {
            type: Array,
            default: ["/static/example/Avatars_noLogin.png", "/static/example/User/avatar-3.svg", "/static/example/User/avatar-4.svg"]
        },
        userNum: {
            type: [String, Number],
            default: 99
        },
        tagKind: {  // info tag 的基本种类
            type: Number,
            default: 0
        }
    });
    const emits = defineEmits([]);

// FUNC
    // style
    const getTagColor = computed(() => {
        switch(props.tagKind) {
            case 1: return { flag: true, bg: "#ffc300", font: "#000", text: "官方" };
            case 2: return { flag: true, bg: "#FF5733", font: "#fff", text: "HOT" };
            default: return { flag: false };
        }
    })

</script>

<style scoped>

.container {
    flex: 1 1 180px;
    background-color: #ffffff;
    border-radius: 10px;
}

.location {
    position: absolute;
    bottom: 12px;
    left: 15px;

    /* font */
    font-size: 8px;
    line-height: 9px;
    color: #ffffff;
}

.tag {
    position: absolute;
    top: 5px;
    right: 5px;

    font-size: 18px;
    font-family: Alimama ShuHeiTi;

    padding: 6px 0;
    border-radius: 5px 5px 5px 15px;
    width: 50px;
    background-color: var(--bg-color);
    color: var(--font-color);

    z-index: 100;
}

.text-container {
    width: var(--card-width);
}

.title {
    font-size: 14px;
    line-height: 13px;
    margin-left: 6px;
}

.user-container {
    font-size: 10px;
    line-height: 9px;
    font-weight: 300;
    color: #999999;
}

</style> 