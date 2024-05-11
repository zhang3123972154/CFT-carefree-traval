<template>
  <movable-area class="music-layout">
    <movable-view class="img-layout" :x="x" :y="y" direction="all">
      <t-chip/>
    </movable-view>
  </movable-area>
</template>

<script>
export default {
  name: "music-icon",
  props: {
    musicDetail: {
      type: Object,
      default: {}``
    }
  },
  data() {
    return {
      innerAudioContext: {},
      x: 300,
      y: 300,
    }
  },
}
</script>

<style scoped lang="scss">

.music-layout {
  height: 100vh;
  width: 750rpx;
  top: 0;
  position: fixed;
  pointer-events: none; //鼠标事件可以渗透
}

.img-layout {
  position: relative;
  width: 100rpx;
  height: 100rpx;
  border-radius: 50%;
  pointer-events: auto; //恢复鼠标事件
}

</style>
