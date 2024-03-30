<template>
    <view class="container" :class="{
        'shadow': props.shadow,
        
    }">
        <slot></slot>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        shadow: {
            type: Boolean,
            default: false
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container {
    z-index: 2000;

    position: fixed;
    left: 0;
    right: 0;
    bottom: 60px;
    margin: 0 5px;

    background-color: #ffffff;
    border-radius: 20px;
}

.shadow {
    box-shadow: 0px 0px 10px 3px #0000001a;
}

</style>        