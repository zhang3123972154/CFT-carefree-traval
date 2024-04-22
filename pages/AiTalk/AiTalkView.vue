<!--mark AITALK 的内容-->
<!--info 第二版-->
<template>
    <view class="flex-vertical base-color">
        <header-ai></header-ai>
        <!--bug 和 chip-similarWin 冲突 -->
        <view class="flex-vertical container-dialogue gap-10">
            <userBubble/>
            <aiBubble/>
            <template v-for="(item, index) in talkStore.history" :key="index">
                <ai-bubble v-if="item.side" :content="item.content"/>
                <user-bubble v-else :text="item.text" :images="item.images"/>
            </template>
            <view :style="{
                height: keyboardHeight.toString() + 'px'
            }"/>
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
    import { aiTalkHistory } from "@/store/aiTalk";
    const talkStore = aiTalkHistory();
// DATA
    // animation
    const keyboardHeight = ref(0);

// FUNC
    const gotoPageEnd = (time) => {
        nextTick(() => {
            uni.pageScrollTo({ scrollTop: 99999, duration: time });
        });
    }
    onMounted(() => {
        gotoPageEnd(0);
    })
    // animation
    const handleKeyBoard = (infor) => {
        keyboardHeight.value = infor.height;
        gotoPageEnd(100);
    }
    // 
    const sendUserMessage = (text, images) => {
        console.info(text, images);
        talkStore.sendUserMessage(text, images);
        gotoPageEnd(200);
    }


</script>

<style scoped>

.base-color {
    background-color: #f9f9f9;
}

.container-dialogue {
    background-color: #f9f9f9;

    overflow-y: scroll;
    padding: 5px 10px;
}

.top-flod-container {
    padding: 20px;
}


</style>        