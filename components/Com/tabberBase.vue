<template>
    <view class="container flex-center-horizontal gap-10" @click.stop>
        <view class="flex-center-horizontal">
            <slot name="prefix"></slot>
        </view>
        <view class="flex-center-horizontal shrink">
            <slot name="midfix"></slot>
        </view>
        <view class="flex-center-horizontal"> 
            <slot name="suffix"></slot>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container {
    position: sticky;
    bottom: 0px;
    
    z-index: 10000;

    background-color: #fff;
    padding: 4px;
}

</style>        