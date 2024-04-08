<!--mark AITALK 的内容-->
<!--info 第二版-->
<template>
    <view class="flex-vertical">
        <header-ai></header-ai>
        <view class="flex-vertical container-dialogue gap-10">
            <userBubble/>
            <aiBubble/>
            <template v-for="(item, index) in talkStore.history" :key="index">
                <ai-bubble v-if="item.side" :content="item.content"/>
                <user-bubble v-else :content="item.content"/>
            </template>
        </view>
        <tabber-ai @send-message="sendUserMessage" @key-board-change="handleKeyBoard"/>
    </view>
</template>

<script setup>
    import { ref, nextTick, onMounted, computed } from "vue";
    // com
    import headerAi from "@/components/AiTalk/header.vue";
    import tabberAi from "@/components/AiTalk/tabber.vue";
    import userBubble from "@/components/AiTalk/userBubble.vue";
    import aiBubble from "@/components/AiTalk/aiBubble.vue";
    // store
    import useTalkStore from "@/store/aiTalk";
    const talkStore = useTalkStore();
// DATA

// FUNC
    // style
    const setPageMinHeight = computed(() => {
        console.info("min-height", uni.getSystemInfoSync().windowHeight);
        return uni.getSystemInfoSync().windowHeight + 'px';
    })
    const handleKeyBoard = (infor) => {
        console.info("test-keyboard-outside", infor);
    }
    // 
    const sendUserMessage = (content) => {
        talkStore.sendUserMessage(content);

        nextTick(() => {
            uni.pageScrollTo({ scrollTop: 99999, duration: 200 });
        });
    }

    onMounted(() => {
        uni.pageScrollTo({ scrollTop: 99999, duration: 0 });
    })

</script>

<style scoped>

.container-dialogue {
    background-color: #f9f9f9;

    overflow-y: scroll;
    padding: 5px 10px;
}

.top-flod-container {
    padding: 20px;
}

</style>        