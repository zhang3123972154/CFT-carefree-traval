<template>
    <view class="relative">
        <up-image 
            :show-loading="true" 
            :src="props.src" 
            :radius="props.radius" 
            :width="props.width.toString() + 'px'" 
            :height="props.height.toString() + 'px'"
        />
        <view class="btn-close" @click.stop>
            <u-icon v-if="props.closeAble"
                name="/static/icon/Com/closeRed.svg" 
                size="18"
                @click="close"
            />
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        width: {
            type: [Number, String],
            default: 60
        },
        height: {
            type: [Number, String],
            default: 60
        },
        radius: {
            type: [Number, String],
            default: 5
        },
        src: {
            type: String,
            default: "/static/example/Avatars_noLogin.png"
        },
        closeAble: Boolean,
        index: {
            type: Number,
            default: 0
        }
    });
    const emits = defineEmits(["close"]);

// FUNC

    const close = () => {
        emits("close", props.index);
    }

</script>

<style scoped>

.btn-close {
    position: absolute;
    right: -4px;
    top: -4px;
}

</style>        