<template>
    <view class="flex-center-both basic" :class="[getClass, props.shape]" :style="{
        '--border-radius': checkKind.borderRadius,
        '--color': selectFlag ? checkKind.fontColorSelected : checkKind.fontColor,
        '--back-color': checkKind.backColor,   
    }" @click="click" @longpress="longpress" @touchend="touchend">
        <slot>
            {{ props.text }}
        </slot>
        <!--info tag 的介绍框框-->
        <view v-if="similarWinFlag"
            :animation="similarWinAnimation"
            class="z-9 detail-container"
            :style="{
                '--top': getTop
            }"
            @click.stop="close"
            @touchmove.prevent>
            <!--info 套上这层来阻断click的传递，导致chip被点击-->
            <!-- <u-overlay class="flex-center-both" :show="similarWinFlag"> -->
                <view class="flex-center-both detail-inside-container">
                    <similarWin @close="close"/>
                </view>
            <!-- </u-overlay> -->
        </view>
    </view>
</template>

<script setup>
    import { ref, computed, nextTick, onMounted, watch } from 'vue';
    // com
    import similarWin from "./similarWin.vue";

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
        belongAiHeader: { // info 为了 aitalk-header 特化
            type: Boolean,
            default: false
        },
        light: { // info 是否保持常亮 light-hold
            type: Boolean,
            default: false
        },
        lightStart: {   // info  lightFlag 的初始值； // info 优先级 light > lightSrart
            type: Boolean,
            default: false
        },
        belongChipGroupFlex: { // info 
            type: Boolean,
            default: false
        }
    });
    const emits = defineEmits(["longPress", "closeSimilarWin", "clickChoose", "clickDelete"]);  // todo 用于历史记录的消息传递

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
    const similarWinFlag = ref(true);
    // test
    onMounted(() => {
        similarWinFlag.value = false;
    })
    // animation
    const TIME_ANIMATION = 200;
    const similarWinAnimation = ref(null);

    // const
    const TOP = -45;

// FUNC
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
    const getTop = computed(() => {
        return props.belongAiHeader ? TOP.toString() + 'px' : '0';
    })

// info 点击 && 长按
    let islongPress = false;
    const longpress = () => {
        console.info("长按事件-2");
        islongPress = true;
        similarWinFlag.value = true;
        emits('longPress'); // info 

        nextTick(() => {
            similarWinAnimation.value = uni.createAnimation({
                duration: TIME_ANIMATION,
                timingFunction: 'ease'
            })

            similarWinAnimation.value.opacity(1).step();
        });
    }

    const click = () => {
        if(!islongPress && !props.light) { // info 实现常亮效果
            // selectFlag.value = !selectFlag.value;
            // info 向上传递具体的操作形式，判断处理形式
            if(props.belongChipGroupFlex) {
                if(!selectFlag.value)
                    emits("clickChoose");
                else
                    emits("clickDelete");
            }
            else
                selectFlag.value = !selectFlag.value;
        }
    }

    const touchend = () => {
        setTimeout(() => {
            islongPress = false;
        }, 200);
    }

    const close = () => {
        emits("closeSimilarWin"); // info 提前返回信号，让 aitalk-header 的背景色恢复，同时similarWin自带fade-out，所以效果挺好。
        setTimeout(() => {
            similarWinFlag.value = false;
        }, TIME_ANIMATION);

        similarWinAnimation.value = uni.createAnimation({
            duration: TIME_ANIMATION,
            timingFunction: 'ease'
        })
        similarWinAnimation.value.opacity(0).step();
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