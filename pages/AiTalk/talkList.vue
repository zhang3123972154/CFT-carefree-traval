<template>
    <view class="flex-vertical container gap-10">
        <!-- <text>对话列表</text> -->
        <talk-item/>
        <talk-item/>
        <talk-item/>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import talkItem from "@/components/AiTalk/talkItem.vue";
    // store
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