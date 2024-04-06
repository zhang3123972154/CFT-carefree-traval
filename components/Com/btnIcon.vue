<template>
    <view class="flex-center-both container" :class="{'container-shadow': props.shadow}"
        :style="{'--size': props.btnSize + 'px', '--border-radius': props.radius + 'px'}">
        <image class="icon" :style="{'--size': props.iconSize + 'px'}"
            :src="props.icon">
        </image>
    </view>
</template>

<script setup>
// DATA
    const props = defineProps({
        icon: {
            type: String,
            require: true
        },

        iconSize: {
            type: [Number, String],
            default: 21
        },
        btnSize: {
            type: [Number, String],
            default: 40
        },
        radius: {
            type: [Number, String],
            default: 15
        },
        shadow: {
            type: Boolean,
            deafult: false
        }
    });
</script>

<style scoped>

.container {
    width: var(--size);
    height: var(--size);
    border-radius: var(--border-radius);
    background-color: transparent;
    /* 设置初始背景色 */
    transition: background-color 0.1s ease-in, background-color 0.5s ease-out;;
    /* 启用背景色过渡动画 */
}

.container:active {
    background-color: #d1d1d1;
    /* 设置激活状态的背景色 */
}

.container-shadow {
  box-shadow: 0px 0px 10px #0000000d;
}

.icon {
    width: var(--size);
    height: var(--size);
}

</style>