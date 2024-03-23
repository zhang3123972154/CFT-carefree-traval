<template>
<view class="flex-row justify-between items-center group_7">
  <view class="flex-col items-center">
    <image
      class="image_11"
      src="/static/icon/homeSelected.svg"
    />
    <text class="mt-6 font_6 text_10">主页</text>
  </view>
  <view class="flex-col items-center">
    <image
      class="image_12"
      src="https://ide.code.fun/api/image?token=65ed6558ba406d00116099aa&name=2eda16d35de9bf1ed8dca6c2751c0384.png"
    />
    <text class="mt-6 font_6 text_11">打卡</text>
  </view>
  <image
    class="image_10"
    src="https://ide.code.fun/api/image?token=65ed6558ba406d00116099aa&name=fa8233aba3e2700bd9f89939a0a947a8.png"
  />
  <view class="flex-col items-center">
    <image
      class="image_13"
      src="https://ide.code.fun/api/image?token=65ed6558ba406d00116099aa&name=c9fefe572718e66230e6bea8f55f26f9.png"
    />
    <text class="mt-6 font_6 text_12">消息</text>
  </view>
  <view class="flex-col items-center">
    <image
      class="image_14"
      src="https://ide.code.fun/api/image?token=65ed6558ba406d00116099aa&name=6d2ea86bbf45a7fca3c9600dd83073b3.png"
    />
    <text class="mt-6 font_6">我的</text>
  </view>
</view>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {};
  },

  methods: {},
};
</script>

<style scoped lang="css">
.group_7 {
    position: fixed;
    top: 0;
    /* bottom: 0; */
    width: 100%;
  padding: 5px 34px 12px;
    background-color: #3333337a;
}
.image_11 {
  width: 23px;
  height: 23px;
}
.font_6 {
  font-size: 10px;
  font-family: SourceHanSansCN;
  line-height: 9px;
  font-weight: 300;
  color: #333333;
}
.text_10 {
  color: #000000;
  line-height: 9px;
  font-weight: unset;
}
.image_12 {
  border-radius: 5px;
  width: 23px;
  height: 23px;
}
.text_11 {
  line-height: 9px;
}
.image_10 {
  border-radius: 50%;
  width: 45px;
  height: 45px;
}
.image_13 {
  border-radius: 11px 11px 11px 5px;
  width: 23px;
  height: 23px;
}
.text_12 {
  line-height: 9px;
}
.image_14 {
  width: 23px;
  height: 23px;
}
</style>