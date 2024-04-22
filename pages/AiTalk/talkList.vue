<template>
    <view class="flex-vertical container gap-10">
        <template v-for="(item, index) in talkList.aiTalkList" :key="index">
            <talk-item 
                :avatar="item.avatar"
                :name="item.name"
                :child-talk="item.childTalk"
            />
        </template>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import talkItem from "@/components/AiTalk/talkItem.vue";
    // store
    import { aiTalkList } from "@/store/aiTalk";
    const talkList = aiTalkList();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container {
    padding: 0 10px;
}

</style>        