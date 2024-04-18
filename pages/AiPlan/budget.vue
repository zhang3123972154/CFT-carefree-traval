<template>
    <view>
        <text>XX元</text>
        <up-slider v-model="value"></up-slider>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

    const value = ref(30);
// FUNC

</script>

<style scoped>

</style>        