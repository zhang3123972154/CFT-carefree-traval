<!--todo特化的chip, aiTalk 用-->
<template>
    <view class="flex-center-both basic" :class="[getClass, props.shape]" :style="{
        '--border-radius': checkKind.borderRadius,
        '--color': selectFlag ? checkKind.fontColorSelected : checkKind.fontColor,
        '--back-color': checkKind.backColor,   
    }" @click="click" @longpress="longpress" @touchend="touchend">
        <slot>
            {{ props.text }}
        </slot>
    </view>
</template>

<script setup>
    import { ref, computed, nextTick, onMounted, watch } from 'vue';

// DATA
    const props = defineProps({
        kind: {
            type: String,
            default: "way"
        },
        text: {
            type: [String, Number],
            default: "风格"
        },
        shape: {
            type: String,
            default: "rectangle"
        },
        light: { // info 是否保持常亮 light-hold
            type: Boolean,
            default: false
        },
        lightStart: {   // info  lightFlag 的初始值； // info 优先级 light > lightSrart
            type: Boolean,
            default: false
        }
    });
    const emits = defineEmits(["longPress", "clickChoose", "clickDelete"]);  // todo 用于历史记录的消息传递

    const FONTCOLOR = ref({
        unselected: "#cccccc",
        default: "#ffffff",
        history: "#999999",
    });

    const style = {
        way: { backColor: "#959cf0", borderRadius: '5px', 
            fontColor: FONTCOLOR.value.unselected, fontColorSelected: FONTCOLOR.value.default 
        },
        spot: { backColor: "#a795f0", borderRadius: '10px', 
            fontColor: FONTCOLOR.value.unselected, fontColorSelected: FONTCOLOR.value.default 
        },
        thing: { backColor: "#c895f0", borderRadius: '15px', 
            fontColor: FONTCOLOR.value.unselected, fontColorSelected: FONTCOLOR.value.default 
        },
        traffic: { backColor: "#FFC300", borderRadius: '10px', 
            fontColor: FONTCOLOR.value.unselected, fontColorSelected: FONTCOLOR.value.default 
        },

        history: { backColor: "#f9f9f9", borderRadius: '15px', 
            fontColor: FONTCOLOR.value.history, fontColorSelected: FONTCOLOR.value.history }
    };

    // flag
    const selectFlag = ref(props.light | props.lightStart);
    // test
    onMounted(() => {
        similarWinFlag.value = false;
    })
    // const
    const TOP = -45;

    // info chip type flag

// FUNC
    onMounted(() => {

    })

    watch(() => props.lightStart, () => {
        selectFlag.value = props.light | props.lightStart;
    })
    const checkKind = computed(() => {
        return style[props.kind] || style.way;
    })
    const checkHistory = computed(() => {
        return props.kind == "history" ? true : false;
    })
    const getClass = computed(() => {
        if(checkHistory.value)
            return "history";
        return selectFlag.value ? "light" : "default";
    });

// info 点击 && 长按
    let islongPress = false;
    const longpress = () => {
        console.info("长按事件-2");
        islongPress = true;
        similarWinFlag.value = true;
        emits('longPress'); // info 
    }

    const click = () => {
        if(!islongPress && !props.light) { // info 实现常亮效果
            if(!selectFlag.value)
                emits("clickChoose");
            else
                emits("clickDelete");  
        }
    }

    const touchend = () => {
        setTimeout(() => {
            islongPress = false;
        }, 200);
    }

    const close = () => {

    }
    
</script>

<style scoped>

.basic {
    height: 30px;

    border-radius: var(--border-radius);

    color: var(--color);
    letter-spacing: 1px;    /* 字间距 */
    font-size: 14px;
    line-height: 13px;
    font-family: SourceHanSansCN;

    flex-shrink: 0;
    flex-basis: auto;
    white-space: nowrap;
}

.rectangle {
    padding: 10px;
}

.square {
    padding: 10px 5px;
}

.default {
    border: solid 1px #cccccc;
}

.light {
    border: none;
    background-color: var(--back-color);
    font-weight: bold;
    border: solid 1px transparent;  /* 占位用，防止交互后错位, 但还是有一些bug */

    font-family: Alimama ShuHeiTi;
}

.history {
    background-color: #f9f9f9;;
}

.detail-container {
    position: fixed;
    top: var(--top);
    left: 0;
    right: 0;

    background-color: rgba(0, 0, 0, 0.1);

    opacity: 0;
}

.detail-inside-container {
    height: 100vh;
}

</style>      