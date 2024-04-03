<template>
    <view class="flex-center-horizontal container">
        <view class="flex-horizontal ">
            <u-icon class="flex-center-both icon-back" :name="iconArrows.left" size="20"
                @click="back">
            </u-icon>
            <up-avatar class=" ml-15" :src="props.avatar" size="30"></up-avatar>
            <text class="user-name">{{ props.usrName }}</text>
        </view>
        <view class="flex-center-horizontal gap-10">
            <text class="flex-center-both no-wrap attention-text">关注</text> <!--todo 函数实现-->
            <u-icon class="flex-center-both icon-share" :name="iconPath.share" size="20"></u-icon>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
    import { useDetailIconPath, useArrowsIconPath } from "@/store/dataBase";
    const iconPath = useDetailIconPath();
    const iconArrows = useArrowsIconPath();
// DATA
    const props = defineProps({
        avatar: {
            type: String,
            default: "/static/example/User/avatar-2.svg"
        },
        usrName: {
            type: String,
            default: "逍遥游用户1"
        },
        attentionFlag: {    // 是否关注
            type: Boolean,
            default: false
        },

    });
    const emits = defineEmits([]);

// FUNC
    const back = () => {
        uni.navigateBack();
    }

</script>

<style scoped>

.container {
    position: sticky;
    top: 0;
    z-index: 10000;
    height: 50px;

    background-color: #fff;
}

.icon-back {
    height: 40px;
    width: 20px;
}

.icon-share {
    width: 40px;
    height: 40px;
}

.user-name {
    margin-left: 10px;

    font-size: 14px;
    font-weight: 300;
    color: #666666;
    line-height: 13px;
}

.attention-text {
    padding: 2px 8px;
    border-radius: 15px;
    width: 40px;
    height: 20px;
    border: solid 1px #ffc300;

    color: #ffc300;
    font-size: 12px;
    font-weight: 300;
    line-height: 11px;
}

.attention-text-light { /* todo */

}

</style>        