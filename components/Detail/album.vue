<template>
    <view class="relative">
        <text class="absolute num-text flex-center-both">{{ currentIndex+1 }}/{{ length }}</text>
        <u-swiper :list="props.list" height="500" radius="10" @change="changeIndex">
            <template #indicator>
                <view class="flex-center-horizontal gap-5">
                    <view v-for="index in length" class="dot" :class="{'dot-light': index-1 === currentIndex}"></view>
                </view>
            </template>
        </u-swiper>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // store
// DATA
    const props = defineProps({
        list: {
            type: Array,
            default: () => [
                "/static/example/1.jpg",
                "/static/example/5.jpg",
                "/static/example/3.jpg"
            ]
        },
    });
    const emits = defineEmits([]);
    const length =  computed(() => {
        return props.list.length;
    })
    // flag
    const currentIndex = ref(0);

// FUNC
    const changeIndex = (index) => {
        currentIndex.value = index.current;
        // console.info(index, currentIndex.value);
    }

</script>

<style scoped>

.num-text {
    right: 20px;
    top: 20px;

    padding: 4px 0;
    background-color: #33333366;
    border-radius: 10px;
    width: 40px;

    font-size: 14px;
    color: #ffffff;
    line-height: 13px;
}

.dot {
    background-color: #CCCCCC;
    width: 4px;
    height: 4px;
    border-radius: 50%;
}

.dot-light {
    background-color: #FFC300;
    width: 5px;
    height: 5px;
    border-radius: 50%;
}

</style>        