<template>
  <view style="height: 62px"></view> <!--用来把主页内容顶上去-->
  <view class="flex-row justify-between items-center container">
    <view class="flex-col items-center" :class="{selected: indexStore.currentIndex == 0}"
      @click="goto(0)">
      <image class="icon" :src="getRealPath(0)"/>
      <text class="mt-6">主页</text>
    </view>
    <view class="flex-col items-center" :class="{selected: indexStore.currentIndex == 1}"
      @click="goto(1)">
      <image class="icon" :src="getRealPath(1)"/>
      <text class="mt-6">打卡</text>
    </view>
    
    <!--中间搜索-->
    <image class="icon-mid" :src="iconTabbarPath.pathMid"
      @click="goto(4)"/>
    
    <view class="flex-col items-center" :class="{selected: indexStore.currentIndex == 2}"
      @click="goto(2)">
      <image class="icon" :src="getRealPath(2)"/>
      <text class="mt-6">消息</text>
    </view>
    <view class="flex-col items-center" :class="{selected: indexStore.currentIndex == 3}"
      @click="goto(3)">
      <image class="icon" :src="getRealPath(3)"/>
      <text class="mt-6">我的</text>
    </view>
  </view>
</template>

<script setup>
  // store
  import { useIconTabbarPath } from '@/store/dataBase';
  import tabIndex from '@/store/tabIndex';
// DATA
  const props = defineProps({

  });
  // store
  const iconTabbarPath = useIconTabbarPath();
  const indexStore = tabIndex();
  // const
  const iconPath = iconTabbarPath.getFullPath();
  const pages = ["Home/HomeView", "Punch/PunchView", "Message/MessageView", "Person/PersonView", 'AiTalk/talkStart'];
// FUNC
  const getRealPath = (index) => {
    if(indexStore.currentIndex == index)
      return iconPath[index].selected;
    else
      return iconPath[index].unselected;
  }

  const goto = (index) => {
    console.info("跳转", index);
    indexStore.changeCurtabIndex(index);

    const pagePath = "../" + pages[index];
    uni.navigateTo({ url: pagePath, animationType: "fade-in"});
    // info fade-in 视觉效果会好很多。
  }

</script>

<style scoped lang="css">

.container {
  position: fixed;
  bottom: 0;
  width: 100%;

  padding: 5px 34px 12px;

  background-color: #fff;
  box-sizing: border-box;

  /* font */
  font-size: 10px;
  font-family: SourceHanSansCN;
  line-height: 9px;
  font-weight: 300;
  color: #333333;

  /* text */
  color: #000000;
  line-height: 9px;
  font-weight: unset;
}

.icon {
  width: 23px;
  height: 23px;
}

.icon-mid {
  width: 45px;
  height: 45px;
}

.selected {
  font-weight: bold;
  /* font-weight: unset; */
}

</style>