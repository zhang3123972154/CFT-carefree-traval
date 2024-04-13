<template>
    <!--因为需要不同的zIndex-->
    <!-- <view class="sticky"> -->
        <view class="flex-vertical block container pd-5 top-container z-8" 
        :class="{'transparent': props.bgTransparent}" 
        :style="{
            '--status-height': phoneInforStore.statusBarHeight.toString() + 'px',
        }">
        <!--bug 感觉 pd-5 没有生效-->
            <view class="flex-center-horizontal">
                <view class="flex-center-horizontal">
                    <!--info 默认的返回键-->
                    <u-icon v-if="!props.backDelete" @click="back"
                        class="flex-center-both top-icon" size="18" :name="iconPath.left">
                    </u-icon>
                    <slot name="prefix"></slot>
                </view>
                <view class="flex-center-both shrink"> <!--info delete shrink-->
                    <slot name="midfix"></slot>
                </view>
                <view class="flex-center-horizontal">
                    <slot name="suffix"></slot>
                </view>
            </view>
        </view>
        <!--info 一个附加的下部窗口-->
        <view class="flod-container block"
            :class="{
                [setFlodWinZIndex]: true
            }">
            <slot name="flodfix"></slot>
        </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // store
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInforStore = usePhoneInfor();
    import { useArrowsIconPath } from "@/store/dataBase";
    const iconPath = useArrowsIconPath();
// DATA
    const props = defineProps({
        backDelete: {
            type: Boolean,
            default: false
        },
        bgTransparent: {
            type: Boolean,
            default: false
        },
        flodZIndexTop: {
            type: String,
            default: "0"
        }
    });
    const emits = defineEmits([]);

// FUNC
    // style
    const setFlodWinZIndex = computed(() => {
        return  "z-" + props.flodZIndexTop;
    })

    const back = () => {
        uni.navigateBack({ delta: 1 });
    }

</script>

<style scoped>

.container {
    position: sticky;
    top: 0px;

    background-color: #fff;
}

.transparent {
    background-color: transparent;
}

.top-icon {
    width: 40px;
    height: 40px;
}

.flod-container {
    position: fixed;
    top: 45px;
}

</style>        