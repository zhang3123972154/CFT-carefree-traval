<template>
    <view @touchmove="scrollStop">
        <slot></slot>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA

// FUNC
    const scrollStop = (event) => {
        console.info("scroll run");
        event.stopPropagation();
        event.preventDefault()
    }
</script>

<style scoped>

</style>        