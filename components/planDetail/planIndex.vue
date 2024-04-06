<template>
    <view class="flex-center-col-vertical container gap-3" :class="{'bg-selected': seleted}">
        <text class="title" :class="{'title-selected': seleted}">{{ getTitle }}</text>
        <view class="flex-center-horizontal time gap-5" :class="{'time-selected': seleted}">
            <text>{{ props.timeStart }}</text>
            <text>{{ props.timeEnd }}</text>
        </view>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // store
// DATA
    const props = defineProps({
        curIndex: {
            type: Number,
            default: 0
        },
        index: {
            type: Number,
            default: 0
        },
        timeStart: {
            type: String,
            default: "00-00"
        },
        timeEnd: {
            type: String,
            default: "00-00"
        }
    });
    const emits = defineEmits([]);

// FUNC
    const getTitle = computed(() => {
        switch(props.index) {
            case 0: return "方案一";
            case 1: return "方案二";
            case 2: return "方案三";
            default: return "方案X";
        };
    })
    const seleted = computed(() => {
        return props.index === props.curIndex;
    })

</script>

<style scoped>

.container {
    padding: 10px 13px;
    height: 45px;
    width: 80px;
    border-radius: 15px;
    background-color: rgba(0, 0, 0, .3);
}

.title {
    font-size: 14px;
    line-height: 13px;
    color: #fff;
}

.time {
    font-size: 10px;
    line-height: 12px;
    color: #666666;
}

.bg-selected {
    background-color: #ffc300;
}

.title-selected {
    color: #000;
}

.time-selected {
    color: #fff;
}


</style>        