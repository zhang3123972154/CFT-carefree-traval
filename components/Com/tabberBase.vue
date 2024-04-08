<template>
    <view class="container flex-bottom-horizontal gap-10"
    :class="{
        [setZIndex]: true
    }"    
    @click.stop>
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
    import { ref, computed } from "vue";
// DATA
    const props = defineProps({
        zIndex: {
            type: String,
            default: "8"
        }
    });
    const emits = defineEmits([]);

// FUNC
    // style
    const setZIndex = computed(() => {
        return "z-" + props.zIndex;
    })

</script>

<style scoped>

.container {
    position: sticky;
    bottom: 0px;
    
    background-color: #fff;
    padding: 4px;
}

</style>        