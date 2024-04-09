<template>
    <view 
    class="container" 
    :class="{
        'shadow': props.shadow,
        [getZindex]: true,
    }"
    :style="{
        '--bottom': setBottom
    }">
        <slot></slot>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // store
// DATA
    const props = defineProps({
        shadow: {
            type: Boolean,
            default: false
        },
        zIndex: {
            type: String,
            default: "1"
        },
        bottom: {
            type: Number,
            default: 60
        }
    });
    const emits = defineEmits([]);

// FUNC
    // style 
    const getZindex = computed(() => {
        return "z-" + props.zIndex;
    })
    const setBottom = computed(() => {
        return props.bottom.toString() + 'px';
    })

</script>

<style scoped>

.container {
    position: fixed;
    left: 0;
    right: 0;
    bottom: var(--bottom);
    margin: 0 5px;

    background-color: #ffffff;
    border-radius: 20px;

    transition: bottom .1s;
}

.shadow {
    box-shadow: 0px 0px 10px 3px #0000001a;
}

.z-9 {
    z-index: 9000;
}

</style>        