<template>
    <view class="flex-center-horizontal container">
        <view>
            <text class="index" :style="getStyle">{{ props.index }}</text>
            <text class="contetn ml-20">{{ props.content }}</text>
        </view>
        <text class="num-text">{{ props.num }}W</text>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // store
// DATA
    const props = defineProps({
        index: {
            type: Number,
            default: 1
        },
        content: {
            type: String,
            default: "默认标题"
        },
        num: {
            type: [Number, String],
            default: 1000
        }
    });
    const emits = defineEmits([]);

    const FONTCOLOR = ["#FF5733", "#FF8D1A", "#FFEB3B", "999999"];

// FUNC
    const getStyle = computed(() => {
        let color = "";
        if(props.index <= 3)
            color =  FONTCOLOR[props.index - 1];
        else
            color = FONTCOLOR[3];

        return {
            'color': color
        }
    })

</script>

<style scoped>

.container {
    padding: 14.5px 8.5px 12.5px 15.5px;
    border-radius: 15px;
}

.index {
    font-size: 14px;
    font-family: SourceHanSansCN;
    line-height: 10.5px;
    line-height: 10.5px;
}

.content {
    line-height: 13px;
    font-size: 14px;
    font-family: SourceHanSansCN;
    line-height: 13px;
    font-weight: 300;
    color: #000000;
}

.num-text {
    font-size: 10px;
    font-family: SourceHanSansCN;
    line-height: 7.5px;
    font-weight: 300;
    color: #cccccc;
}

</style>        