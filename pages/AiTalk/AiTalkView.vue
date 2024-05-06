<!--mark AITALK 的内容-->
<!--info 第二版-->
<template>
    <view class="flex-vertical base-color" @click="closeWin" @touchmove="closeWin">
        <header-ai :avatar="talkStore.avatar" :name="talkStore.name"></header-ai>
        <!--bug 和 chip-similarWin 冲突 -->
        <view class="flex-vertical container-dialogue gap-10">
            <ai-bubble avatar="/static/example/AI/AI.svg"/>
            <template v-for="(item, index) in talkStore.history" :key="index">
                <ai-bubble v-if="item.side" 
                    :avatar="talkStore.avatar"
                    :content="item.content"
                    :word-by-word="index === talkStore.lastIndex && talkStore.loading"
                />
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
    import { ref, nextTick, onMounted, watch } from "vue";
    // com
    import headerAi from "@/components/AiTalk/header.vue";
    import tabberAi from "@/components/AiTalk/tabber.vue";
    import userBubble from "@/components/AiTalk/userBubble.vue";
    import aiBubble from "@/components/AiTalk/aiBubble.vue";
    // store
    import { aiTalk } from "@/store/aiTalk";
    const talkStore = aiTalk();
// DATA
    // animation
    const keyboardHeight = ref(0);

// FUNC
    const closeWin = () => {   // mark 最基本的全局监听点击事件，
        uni.$emit("baseClick");
    }

    const gotoPageEnd = (time) => {
        nextTick(() => {
            uni.pageScrollTo({ scrollTop: 99999, duration: time });
        });
    }

    onMounted(() => {
        gotoPageEnd(0);
    })
    watch(talkStore.history, () => {
        // todo 增加关于 滑动范围的判定。
        gotoPageEnd(100);
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
    min-height: 87vh;

    overflow-y: scroll;
    padding: 5px 10px;
}

.top-flod-container {
    padding: 20px;
}


</style>        