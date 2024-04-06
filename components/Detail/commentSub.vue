<template>
   <view class="flex-top-horizontal gap-10 block-width">
        <up-avatar class="no-shrink" :src="props.information.avatar" size="35"></up-avatar>
            <view class="flex-vertical gap-5 shrink mt-5">
                <text class="user-name">{{ props.information.userName }}</text>
                <text class="content flex-fill wrap mt-5">
                    {{ props.information.content }}
                </text>
                <text class="time">
                    {{ props.information.time }}
                    <span class="replay">回复</span>
                </text>
            </view>
            <!-- <text class="no-shrink">TEST</text> -->
            <iconText class="no-shrink mt-5" @click="handleLike"
            :icon="likeFlag ? iconPath.heartLight : iconPath.heart" 
            :text="likeNum" text-size="10"
            :textStyle="{
                marginTop: '-1px',
                color: likeFlag ? '#FF2F00' : '#000000'
            }"/>    <!--bug 怪怪的 不兼容-->
    </view>
</template>

<script setup>
    import { ref } from "vue";
    import iconText from "../Com/iconText.vue";
    // store
    import { useDetailIconPath } from '@/store/dataBase';
    const iconPath = useDetailIconPath();
// DATA
    const props = defineProps({
        information: {
            type: Object,
            default: () => ({
                avatar: '/static/example/User/avatar-1.svg',
                userName: '用户1',
                likeNum: 0,
                likeFlag: false,
                content: '默认文本',
                time: '0000-00-00'
            })
        }
    });
    const emits = defineEmits([]);

    // status
    const likeFlag = ref(props.information.likeFlag);
    const likeNum = ref(props.information.likeNum);

// FUNC
    const handleLike = () => {
        if(likeFlag.value)
            likeNum.value--;
        else
            likeNum.value++;
        likeFlag.value = !likeFlag.value;
    }

</script>

<style scoped>

.user-name {
    font-size: 14px;
    font-weight: 300;
    color: #999999;
    line-height: 13px;
}

.content {
    flex: 1; /* 让内容文本占据剩余空间 */
    white-space: normal; /* 允许换行 */
    word-wrap: break-word; /* 在必要时断字 */
    
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