<template>
    <view class="flex-vertical gap-5 mt-5">
        <template v-for="(item, index) in props.list" :key="index">
            <problem-item :text="item" @clickChoose="reply(item)"/>
        </template>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import problemItem from "./problemItem.vue";
    // store
    import { aiTalk } from "@/store/aiTalk";
    const talkStore = aiTalk();
// DATA
    const props = defineProps({
        list: {
            type: Array,
            default: () => []
        }
    });
    const emits = defineEmits([]);

// FUNC
    const reply = (text) => {
        talkStore.sendUserMessage(text);
    }

</script>

<style scoped>

</style>        