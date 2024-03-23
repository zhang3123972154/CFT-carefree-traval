<template>
    <view class="flex-horizontal">
        <up-avatar :src="props.avatar" size="35"></up-avatar>
        <view class="flex-vertical gap-5">
            <text class="user-name">{{ props.userName }}</text>
            <text class="content flex-fill wrap">
                {{ props.content }}
            </text>
            <text class="time">
                {{ props.time }}
                <span class="replay">回复</span>
            </text>
        </view>
        <iconText :icon="props.likeFlag ? iconPath.heartLight : iconPath.heart" :text="props.likeNum"/>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import iconText from "../Com/iconText.vue";
    // store
    import { useDetailIconPath } from '@/store/dataBase';
    const iconPath = useDetailIconPath();
// DATA
    const props = defineProps({
        avatar: {
            type: String,
            default: "/static/example/User/avatar-1.svg"
        },
        userName: {
            type: String,
            default: "用户1"
        },
        content: {
            type: String,   
            default: "默认文本"
        },
        likeNum: {
            type:[String, Number],
            default: 0
        },
        likeFlag: {
            type: Boolean,
            default: false
        },
        time: {
            type: String,
            default: "0000-00-00"
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.user-name {
    font-size: 14px;
    font-weight: 300;
    color: #999999;
    line-height: 13px;
}

.content {
    font-size: 14px;
    line-height: 17px;
    font-weight: 300;
}

.time {
    font-size: 10px;
    line-height: 12px;
    font-weight: 300;
    color: #cccccc;
}

.replay {
    font-size: 10px;
    line-height: 12px;
    color: #999999;
}

</style>        