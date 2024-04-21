<template>
    <view>
        <header-talk-or-plan @changeIndex="getIndex"/>
        <view>
            <talk-list v-if="kindIndex == 0"/>
            <plan-list v-else/>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import headerTalkOrPlan from "@/components/AiPlan/header.vue";
    import talkList from "./talkList.vue";
    import planList from "./planList.vue"
    // store
// DATA
    const kindIndex = ref(0);

// FUNC
    const getIndex = (index) => {
        kindIndex.value = index;
    }

</script>

<style scoped>

</style>        