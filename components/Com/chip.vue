<template>
    <view class="flex-center-both basic" :class="getClass" :style="{
        '--border-radius': style[props.kind].borderRadius,
        '--color': selectFlag ? style[props.kind].fontColorSelected : style[props.kind].fontColor,
        '--back-color': style[props.kind].backColor,   
    }" @click="click" @longpress="longpress" @touchend="touchend">
        <slot>
            {{ props.text }}
        </slot>
        <!--info tag 的介绍框框-->
        <u-overlay class="flex-center-both" :show="similarWinFlag" @click="similarWinFlag = false">
            <view class="flex-center-both">
                <similarWin />
            </view>
        </u-overlay>
    </view>
</template>

<script setup>
    import { ref, computed } from 'vue';
    // com
    import similarWin from "./similarWin.vue";

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
    const similarWinFlag = ref(false);

// FUNC
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
        // console.info("长按事件-2");
        islongPress = true;
        similarWinFlag.value = true;
    }
    const click = () => {
        if(!islongPress)
            selectFlag.value = !selectFlag.value;
        // if(islongPress)
        //    console.info("长按事件-1");
        // else {
        //     console.info("点击事件");
        // }
    }
    const touchend = () => {
        setTimeout(() => {
            islongPress = false;
        }, 200);
    }
    
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

    flex-shrink: 0;
    flex-basis: auto;
    white-space: nowrap;
}

.default {
    border: solid 1px #cccccc;
}

.light {
    border: none;
    background-color: var(--back-color);
    font-weight: bold;
    border: solid 1px transparent;  /* 占位用，防止交互后错位 */

    font-family: Alimama ShuHeiTi;
}

.history {
    background-color: #f9f9f9;;
}

</style>