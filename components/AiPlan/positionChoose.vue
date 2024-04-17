<template>
    <view class="flex-horizontal gap-30">
        <t-chip/>
        <view class="recommend">
            <chip-group scroll/>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import chipGroup from "@/components/Home/chipGroup.vue";
    // store
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.recommend {
    overflow: scroll;

}

</style>        