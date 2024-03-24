<template>
    <view class="flex-center-both basic" :class="getClass" :style="{
        '--border-radius': style[props.kind].borderRadius,
        '--color': selectFlag ? style[props.kind].fontColorSelected : style[props.kind].fontColor,
        '--back-color': style[props.kind].backColor,   
    }" @click="selectFlag = !selectFlag">
        <slot>
            {{ props.text }}
        </slot>
    </view>
</template>

<script setup>
    import { ref, computed } from 'vue';

// DATA
    const props = defineProps({
        kind: {
            type: String,
            default: "way"
        },
        text: {
            type: String,
            default: "风格"
        }
    });
    const emits = defineEmits(["choose"]);  // todo 用于历史记录的消息传递

    const FONTCOLOR = ref({
        unselected: "#cccccc",
        default: "#ffffff",
        history: "#999999",
    })

    const style = {
        way: { backColor: "#959cf0", borderRadius: '5px', 
            fontColor: FONTCOLOR.value.unselected, fontColorSelected: FONTCOLOR.value.default },
        spot: { backColor: "#a795f0", borderRadius: '10px', 
            fontColor: FONTCOLOR.value.unselected, fontColorSelected: FONTCOLOR.value.default },
        thing: { backColor: "#c895f0", borderRadius: '15px', 
            fontColor: FONTCOLOR.value.unselected, fontColorSelected: FONTCOLOR.value.default },

        history: { backColor: "#f9f9f9", borderRadius: '15px', 
            fontColor: FONTCOLOR.value.history, fontColorSelected: FONTCOLOR.value.history }
    };

    // flag
    const selectFlag = ref(false);

// FUNC
    const checkHistory = computed(() => {
        return props.kind == "history" ? true : false;
    })
    const getClass = computed(() => {
        if(checkHistory.value)
            return "history";
        return selectFlag.value ? "light" : "default";
    });

    
</script>

<style scoped>

.basic {
    height: 30px;
    padding: 10px;

    border-radius: var(--border-radius);

    color: var(--color);
    letter-spacing: 1px;    /* 字间距 */
    font-size: 14px;
    line-height: 13px;
    font-family: SourceHanSansCN;
}

.default {
    border: solid 1px #cccccc;
}

.light {
    border: none;
    background-color: var(--back-color);
    font-weight: bold;
    border: solid 1px transparent;  /* 占位用，防止交互后错位 */
}

.history {
    background-color: #f9f9f9;;
}

</style>