<template>
    <view class="flex-top-horizontal container gap-10">
        <view class="flex-horizontal gap-10">
            <up-image :src="props.imgPath" width="40" height="40" shape="circle"/>
            <view class="flex-vertical">
                <text class="name">{{ props.name }}</text>
                <text class="message">{{ props.lastMessage }}</text>
            </view>
        </view>
        <text class="date">{{ props.date }}</text>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        imgPath: {
            type: String,
            default: "/static/example/Avatars_noLogin.png"
        },
        name: {
            type: String,
            default: "默认名"
        },
        lastMessage: {
            type: String,
            default: "最近的一条消息"
        },
        date: {
            type: String,
            default: "2024-04-01"
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container {
    padding: 10px 30px;
    border-radius: 25px;
}

.name {
    font-size: 14px;
}

.message {
    color: #CCCCCC;
    font-size: 12px;
}

.date {
    font-size: 10px;
    color: #CCCCCC;
}

</style>        