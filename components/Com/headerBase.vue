<template>
    <view class="flex-vertical block-width container" 
        :class="{'transparent': props.bgTransparent}"
        @click.stop>
        <view class="placeholder" :style="{
            '--status-height': phoneInforStore.statusBarHeight.toString() + 'px',
        }"></view> <!--info 状态栏占位-->
        <view class="flex-center-horizontal">
            <view class="flex-center-horizontal">
                <!--info 默认的返回键-->
                <u-icon v-if="!props.backDelete" @click="back"
                    class="flex-center-both top-icon" size="18" :name="iconPath.left"></u-icon>
                <slot name="prefix"></slot>
            </view>
            <view class="flex-center-both"> <!--info delete shrink-->
                <slot name="midfix"></slot>
            </view>
            <view class="flex-center-horizontal">
                <slot name="suffix"></slot>
            </view>
        </view>
    </view>
     <!--info 一个附加的下部窗口-->
    <view class="flod-container">
        <slot name="flodfix"></slot>
    </view>
</template>

<script setup>
    import { ref } from "vue";
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
        }
    });
    const emits = defineEmits([]);

// FUNC
    const back = () => {
        uni.navigateBack({ delta: 1 });
    }

</script>

<style scoped>

.placeholder {
    height: var(--status-height);
    background-color: #fff;
}

.container {
    position: sticky;
    top: 0px;
    height: 50px;
    z-index: 10000;

    background-color: #fff;
    padding: 4px;
}

.transparent {
    background-color: transparent;
}

.top-icon {
    width: 40px;
    height: 40px;
}

.flod-container {
    position: sticky;
    top: 50px;
    z-index: 2000;
}

</style>        