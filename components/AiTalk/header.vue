<template>
    <headerBase>
        <template #prefix>
            <u-icon class="flex-center-both top-icon" size="30" :name="iconPath.AI"></u-icon>
            <text class="ai-name ml-5">旅行助手</text>
        </template>
        <template #midfix>
            <view @click="toggleOverlay(true)" class="block-width" style="color: #fff;">-</view>
        </template>
        <template #suffix>
            <t-btn-icon :icon="iconPath.phone"></t-btn-icon>
            <t-btn-icon :icon="iconPath.information"></t-btn-icon>
        </template>
        <!--info 本次的个性标签库-->
        <view class="overlay-container" @click="toggleOverlay(false)" :animation="overlayAnimation">
            <view class="chips-container" @click.stop :animation="chipsAnimation" v-if="openFlag">
                <chipGroup/>
            </view>
        </view>
    </headerBase>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import headerBase from "../Com/headerBase.vue";
    import chipGroup from "@/components/Home/chipGroup.vue";
    // store
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInforStore = usePhoneInfor();

    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

    // flag
    const openFlag = ref(true);
    const overlayAnimation  = ref(null);
    const chipsAnimation = ref(null);

// FUNC
    const toggleOverlay = (show) => {   // 退场动画
      if (show) {
        openFlag.value = true;
        overlayAnimation.value = uni.createAnimation({
          duration: 500,
          timingFunction: 'ease'
        });
        overlayAnimation.value.opacity(1).step();
        
        chipsAnimation.value = uni.createAnimation({
          duration: 300,
          timingFunction: 'ease-out'
        });
        chipsAnimation.value.translateY(0).opacity(1).step();
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
          duration: 300,
          timingFunction: 'ease-out'
        });
        chipsAnimation.value.translateY('-100%').opacity(0).step();
      }

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
    top: 45px;
    height: 90vh;
    background-color: #2e181822;
}

.chips-container {
    padding: 15px;
    border-radius: 0 0 20px 20px;
    background-color: #fff;
}



</style>        