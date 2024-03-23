<template>
    <view class="flex-horizontal">
        <u-icon :size="props.iconSize + 'px'" :name="props.icon"></u-icon>
        <text class="ml-4 text" :style="textStyle">{{ props.text }}</text>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // store
// DATA
    const props = defineProps({
        icon: {
            type: String
        },
        iconSize: {
            type: [Number, String],
            default: 14
        },
        text: {
            type: [Number, String]
        },
        textSize: {
            type: [Number, String],
            default: 12
        },
        textStyle: {
            type: Object,
            default: () => ({})
        }
    });
    const emits = defineEmits([]);

// FUNC
    // css
    const textStyle = computed(() => {
        return {
            '--font-size': props.textSize + 'px',
            ...props.textStyle
        };
    });

</script>

<style scoped>

.text {
    font-size: var(--font-size);
    margin-top: 15px;
}

</style>        