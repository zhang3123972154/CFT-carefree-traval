<template>
    <headerBase :flodZIndexTop="flodZIndexTop"  back-delete>
        <template #prefix>
            <!--update 还是得考虑一下到选择页面的方式-->
            <u-icon @click="gotoChoose"
              class="flex-center-both top-icon" size="18" :name="iconArrowPath.left">
            </u-icon>
            <u-icon class="flex-center-both top-icon" size="30" :name="iconPath.AI"></u-icon>
            <text @click="toggleOverlay(true)" class="ai-name ml-5">旅行助手</text>
        </template>
        <template #midfix>
            <view @click="toggleOverlay(true)" class="block-width" style="color: #fff;">-</view>
        </template>
        <template #suffix>
            <t-btn-icon :icon="iconPath.phone"></t-btn-icon>
            <!--test 先放到这里-->
            <t-btn-icon :icon="iconPath.information" @click="gotoChoose"></t-btn-icon>
        </template>
        <!--info 本次的个性标签库-->
        <template #flodfix>
          <!--info v-show放在外层效果最佳。-->
            <view v-show="openFlag"
            class="overlay-container top-float-win"
            :style="{
                '--color': setColor,
                '--status-height': phoneInforStore.statusBarHeight.toString() + 'px',
            }"
            :animation="overlayAnimation" 
             @click="toggleOverlay(false)">
                <view class="chips-container" @click.stop :animation="chipsAnimation">
                    <chipGroup belong-ai-header @long-press="open" @close-similar-win="close"/>
                </view>
            </view>
        </template>
    </headerBase>
</template>

<script setup>
    import { ref, nextTick, computed } from "vue";
    // com
    import headerBase from "../Com/headerBase.vue";
    import chipGroup from "@/components/Home/chipGroup.vue";
    // store
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInforStore = usePhoneInfor();

    import { useAiIconPath, useArrowsIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
    const iconArrowPath = useArrowsIconPath();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

    // flag
    const openFlag = ref(false);
    const longPressFlag = ref(false);

    // animation
    const overlayAnimation  = ref(uni.createAnimation());
    const chipsAnimation = ref(uni.createAnimation());
    // style
    const OVERLAY_COLOR = "rgba(0, 0, 0, .1)";
// FUNC
    // style
    const setColor = computed(() => {
      return longPressFlag.value ? "rgba(0, 0, 0, 0)" : OVERLAY_COLOR;
    })
    const flodZIndexTop  = computed(() => {
      return longPressFlag.value ? "9" : "7";
    })

    // animation
    const toggleOverlay = (show) => {   // 退场动画
      if (show) {
        openFlag.value = true;
        nextTick(() => {  // info App端的渲染效率 < 面对过程的代码运行。
          overlayAnimation.value = uni.createAnimation({
            duration: 500,
            timingFunction: 'ease'
          });
          overlayAnimation.value.opacity(1).step();
          
          chipsAnimation.value = uni.createAnimation({
            duration: 300,
            timingFunction: 'ease-in'
          });
          chipsAnimation.value.translateY(0).opacity(1).step();
          });
      } else {
        setTimeout(() => {
            openFlag.value = false;
        }, 500);

        overlayAnimation.value = uni.createAnimation({
          duration: 500,
          timingFunction: 'ease'
        });
        overlayAnimation.value.opacity(0).step();
        
        chipsAnimation.value = uni.createAnimation({
          duration: 500,
          timingFunction: 'ease-out'
        });
        chipsAnimation.value.translateY('-50%').opacity(0).step();
      }
    }

    // similar Win
    const open = () => {
      longPressFlag.value = true;
    }

    const close = () => {
      longPressFlag.value = false;
    }

    // test
    const gotoChoose = () => {
      uni.navigateTo({ url: '/pages/AiTalk/talkAndPlan' })
    }

</script>

<style scoped>

.top-icon {
    width: 40px;
    height: 40px;
}

.ai-name {
  color: #666666;
  font-size: 14px;
  font-weight: 300;
  line-height: 13px;
}

.overlay-container {
  position: absolute;
  height: 100vh;
  background-color: var(--color);
}

.chips-container {
    padding: 15px;
    border-radius: 0 0 20px 20px;
    background-color: #fff;

    transform: translateY(-70%);  /* 有效触发动画 ？ */
}

</style>        