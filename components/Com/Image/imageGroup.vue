<template>
    <view class="flex-horizontal gap-5" :class="props.class">
        <t-image 
            v-for="(item, index) in props.images" :key="index"
            :index="index"
            :src="item"
            :radius="props.imgRadius"
            :width="props.imgWidth"
            :height="props.imgHeight"

            :close-able="props.closeAble"
            @close="deleteImage(index)"
            @click="previewImage(index)"
        />
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        images: Array,
        imgWidth: {
            type: [Number, String],
            default: 60
        },
        imgHeight: {
            type: [Number, String],
            default: 60
        },
        imgRadius: {
            type: [Number, String],
            default: 5
        },
        class: {
            type: [String, Array, Object],
            default: ''
        },
        closeAble: Boolean
    });
    const emits = defineEmits(["close"]);

// FUNC
    const previewImage = (index) => {
        uni.previewImage({
            current: index,
            urls: props.images,
            longPressActions: {
                itemList: ['发送给朋友', '保存图片', '收藏'],
                success: function(data) {
                    console.log('选中了第' + (data.tapIndex + 1) + '个按钮,第' + (data.index + 1) + '张图片');
                },
                fail: function(err) {
                    console.log(err.errMsg);
                }
            }
        });
    }

    const deleteImage = (index) => {
        emits("close", index);
    }

</script>

<style scoped>

</style>        