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
        <tabber-ai @send-message="sendUserMessage"></tabber-ai>
    </view>
</template>

<script setup>
    import { ref, nextTick, onMounted } from "vue";
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
    const sendUserMessage = (content) => {
        talkStore.sendUserMessage(content);

        nextTick(() => {
            uni.pageScrollTo({ scrollTop: 9999, duration: 300 });
        });
    }

    onMounted(() => {
        uni.pageScrollTo({ scrollTop: 9999, duration: 0 });
    })

</script>

<style scoped>

.container-dialogue {
   background-color: #f9f9f9;
   min-height: calc(100vh - 100px);

   padding: 5px 10px;
}

.top-flod-container {
    padding: 20px;
}

</style>        