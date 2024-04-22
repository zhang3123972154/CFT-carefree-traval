<template>
    <view class="flex-center-horizontal">
        <view class="flex-horizontal gap-20">
            <up-image :src="props.avatar" width="40" height="40" shape="circle"/>
            <text>{{ props.name }}</text>
        </view>
        <view class="divider"></view>
        <u-icon :name="openFlag ? iconPath.down : iconPath.up" 
            @click="openFlag = !openFlag"/>
    </view>
    <view v-if="openFlag" class="flex-vertical gap-20 dialog-container">
        <template v-for="(item, index) in props.childTalk" key="index">
            <dialog-item :title="item.title" :time="item.time" @click="gotoAiTalk(index)"/>
        </template>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import dialogItem from "./dialogItem.vue";
    // store
    import { useArrowsIconPath } from "@/store/dataBase";
    const iconPath = useArrowsIconPath();

    import { aiTalk } from "@/store/aiTalk";
    const aiTalkStore = aiTalk();

// DATA
    const props = defineProps({
        name: {
            type: String,
            default: "默认AI"
        },
        avatar: {
            type: String,
            default: "/static/example/User/avatar-3.svg"
        },
        childTalk: Array
    });
    const emits = defineEmits([]);

    // flag
    const openFlag = ref(false);

// FUNC
    const gotoAiTalk = (index) => {
        aiTalkStore.loadAnohterTalk(props.avatar, props.name);

        // todo 请求历史对话
        uni.redirectTo({ url: '/pages/AiTalk/AiTalkView' })
    }

</script>

<style scoped>

.dialog-container {
    margin-left: 50px;
}

</style>        