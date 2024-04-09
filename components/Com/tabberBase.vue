<template>
    <view class="container flex-bottom-horizontal gap-10"
    :class="{
        [setZIndex]: true
    }"
    :style="{
        '--bottom': setBottom
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
        },
        bottom: {
            type: Number,
            default: 0
        }
    });
    const emits = defineEmits([]);

// FUNC
    // style
    const setZIndex = computed(() => {
        return "z-" + props.zIndex;
    })
    const setBottom = computed(() => {
        return props.bottom.toString() + 'px';
    })

</script>

<style scoped>

.container {
    position: sticky;
    bottom: var(--bottom);
    
    background-color: #fff;
    padding: 4px;

    transition: bottom .1s;
}

</style>        