<template>
    <view class="container flex-vertical"
    :style="{
        '--bottom': setBottom
    }"
    @click.stop>
        <slot name="topfix"></slot>
        <view class="flex-bottom-horizontal bottom-container" 
        :class="{
            [setZIndex]: true
        }">
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
    const setWidth = computed(() => {

    })

</script>

<style scoped>

.container {
    position: sticky;
    bottom: var(--bottom);

    background-color: transparent;
    transition: bottom .1s;
}

.bottom-container {
    background-color: #ffffff;
    padding: 2px;
}

</style>        