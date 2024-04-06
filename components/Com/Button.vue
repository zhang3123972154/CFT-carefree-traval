<!--复刻一下 vuetify-btn-->
<template>
    <!-- <Ripple> -->
        <view class="base flex-center-both" :class="classObject" :style="getOutsideStyle"
            @touchstart="handleTouchStart" @touchend="handleTouchEnd">
            <slot name="prefix">
                <u-icon v-if="props.preIcon" :name="props.preIcon" :size="props.iconSize"></u-icon>
            </slot>
            <view class="vertical-flex">
                <slot name="topfix">
                    <u-icon v-if="props.icon" :name="props.icon" :size="props.iconSize"></u-icon>
                    <u-icon v-if="props.topIcon" :name="props.topIcon" :size="props.iconSize"></u-icon>
                </slot>
                <slot>
                    <!--选中icon属性时，默认只有icon-->
                    <!--todo 丰富文本内容的样式-->
                    <text v-if="!props.icon">{{ props.text }}</text>   
                </slot>
            </view>
            <slot name="suffix">
                <u-icon v-if="props.sufIcon" :name="props.sufIcon" :size="props.iconSize"></u-icon>
            </slot>
        </view>
    <!-- </Ripple> -->
</template>

<script setup>
    // import Ripple from "@/components/animation/Ripple.vue";
    import { ref, computed, onMounted } from 'vue';

    const props = defineProps({
        variant: {
            type: String,
            default: "default"
        },
        text: {
            type: String,
            default: "Button"
        },
        icon: {
            type: String,
            default: null     // 没有值，那么就会无效？
        },
        preIcon: {
            type: String,
            default: null
        },
        topIcon: {
            type: String,
            default: null
        },
        sufIcon: {
            type: String,
            default: null
        },
        iconSize: {
            type: String,
            default: "16"
        },
        animationClose: {
            type: Boolean,
            default: false
        },
        radius: {
            type: [String, Number],
            default: 15
        },
        // bigger Style
        customStyle: {
            type: Object,
            default: () => ({})
        },
    });

    const isFadingOut = ref(false);
    const shape = ref(15);
// FUNC
    onMounted(() => {
        if(props.shape === "circle")
            shape.value = 9999;
    })
    // animation
    function handleTouchStart() {
        isFadingOut.value = true;
    }
    function handleTouchEnd() {
        // Set a timeout to match the CSS transition duration
        setTimeout(() => {
            isFadingOut.value = false;
        }, 200);
    }
    // classObject
    const classObject = computed(() => {
        return {
            'btn': !props.animationClose,
            'fade-out': isFadingOut.value && !props.animationClose,
            [props.variant]: true
        };
    });
    // tag 外部容器的样式
    const getOutsideStyle = computed(() => {
        return {
            '--border-radius': props.radius.toString() + 'px',
            ...props.customStyle,   // 外部优先级高，覆盖前者。
        }
    })

</script>

<style scoped>

.base {
    gap: 5px;
    padding: 6px 2px;
    background-color: #fff;
    height: inherit;
    border-radius: var(--border-radius);

    transition: background-color 0.2s ease;
}

.btn:active, .btn.fade-out {
    background-color: #dadada;
}

.default {
    box-shadow: 0px 2px 5px rgba(0, 0, 0, .5);
}

.outlined {
    background-color: transparent;
    border: 1px solid black;
}

.text {
    background-color: transparent;
}

.tonal {
    background-color: #f9f9f9;
}

.vertical-flex {
    display: flex;
    flex-direction: column;
    align-items: center;
	justify-content: center;
}

</style>