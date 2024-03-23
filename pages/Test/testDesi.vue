<template>
  <view class="flex-col section_3 mt-192">
    <view class="flex-col items-start">
      <text class="text_5">愉快的旅行即将启航！</text>
      <image
        class="image_5 mt-5"
        src="https://ide.code.fun/api/image?token=65f8fbaa0a3c2f00118f3ce8&name=e3f75f1171b45abe11c2b4602bdf8a42.png"
      />
    </view>
    <view class="mt-28 flex-col">
      <view class="flex-row justify-between items-center section_4">
        <view class="flex-col items-start">
          <text class="font_2 text_6">方案一</text>
          <image
            class="mt-2 image_8"
            src="https://ide.code.fun/api/image?token=65f8fbaa0a3c2f00118f3ce8&name=78b4e950dd88e3b13bf9edefdd69f48d.png"
          />
        </view>
        <view class="flex-row items-center group_4">
          <image
            class="image_6"
            src="https://ide.code.fun/api/image?token=65f8fbaa0a3c2f00118f3ce8&name=11573b41d824c78dee1ffcb9b2791ed6.png"
          />
          <image
            class="image_7 ml-25"
            src="https://ide.code.fun/api/image?token=65f8fbaa0a3c2f00118f3ce8&name=840eae1bcc69551d80e6403c7cb8cb0b.png"
          />
        </view>
      </view>
      <view class="mt-10 flex-row justify-between items-center section_6">
        <view class="flex-col group_6">
          <text class="self-center font_3">方案二</text>
          <text class="mt-4 self-stretch font_5">03-17 至 03-21</text>
        </view>
        <view class="section_7"></view>
        <text class="font_4 text_7">旅途详情及备注</text>
        <image
          class="image_7"
          src="https://ide.code.fun/api/image?token=65f8fbaa0a3c2f00118f3ce8&name=8e6a4a4fdd9771c59d62af472fb8d43b.png"
        />
      </view>
      <view class="mt-10 flex-row justify-between items-center section_6">
        <view class="flex-col group_6">
          <text class="self-center font_3">方案三</text>
          <text class="mt-4 self-stretch font_5">03-17 至 03-19</text>
        </view>
        <view class="section_7"></view>
        <text class="font_4 text_7">旅途详情及备注</text>
        <image
          class="image_7"
          src="https://ide.code.fun/api/image?token=65f8fbaa0a3c2f00118f3ce8&name=8e6a4a4fdd9771c59d62af472fb8d43b.png"
        />
      </view>
    </view>
    <view class="mt-28 flex-row">
      <view class="flex-col justify-start items-center text-wrapper"><text class="font_6 text_8">取消</text></view>
      <view class="flex-col justify-start items-center text-wrapper_2 ml-25">
        <text class="font_6 text_9">完成</text>
      </view>
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
.mt-192 {
  margin-top: 192px;
}
.mt-5 {
  margin-top: 5px;
}
.ml-25 {
  margin-left: 25px;
}
.font_2 {
  font-size: 14px;
  font-family: SourceHanSansCN;
  line-height: 13px;
  color: #000000;
}
.section_3 {
  margin-right: 20px;
  padding: 38px 30px 30px;
  background-color: #ffffff;
  border-radius: 30px;
}
.text_5 {
  color: #000000;
  font-size: 24px;
  font-family: SourceHanSansCN;
  font-weight: 300;
  line-height: 22px;
}
.image_5 {
  width: 132px;
  height: 18px;
}
.section_4 {
  padding: 8px 14px 4px 24px;
  background-color: #ffc3001a;
  border-radius: 15px;
  border-left: solid 1px #ffc300;
  border-right: solid 1px #ffc300;
  border-top: solid 1px #ffc300;
  border-bottom: solid 1px #ffc300;
}
.text_6 {
  color: #ffc300;
  line-height: 13px;
}
.image_8 {
  width: 52px;
  height: 12px;
}
.group_4 {
  padding-left: 25px;
  padding-bottom: 5px;
  border-left: solid 1px #cccccc;
}
.image_6 {
  width: 70px;
  height: 15px;
}
.image_7 {
  border-radius: 4px;
  width: 11px;
  height: 11px;
}
.section_6 {
  padding: 8px 14px 4px 24px;
  background-color: #f9f9f9;
  border-radius: 15px;
}
.group_6 {
  width: 47px;
}
.font_3 {
  font-size: 14px;
  font-family: SourceHanSansCN;
  line-height: 13px;
  font-weight: 300;
  color: #000000;
}
.font_5 {
  font-size: 8px;
  font-family: SourceHanSansCN;
  line-height: 10px;
  font-weight: 300;
  color: #999999;
}
.section_7 {
  background-color: #cccccc;
  width: 1px;
  height: 21px;
}
.font_4 {
  font-size: 10px;
  font-family: SourceHanSansCN;
  line-height: 10px;
  font-weight: 300;
  color: #666666;
}
.text_7 {
  line-height: 9px;
}
.text-wrapper {
  padding: 10px 0;
  flex: 1 1 111px;
  border-radius: 15px;
  height: 41px;
  border-left: solid 1px #666666;
  border-right: solid 1px #666666;
  border-top: solid 1px #666666;
  border-bottom: solid 1px #666666;
}
.font_6 {
  font-size: 20px;
  font-family: SourceHanSansCN;
  line-height: 18.5px;
}
.text_8 {
  color: #666666;
}
.text-wrapper_2 {
  padding: 10px 0;
  flex: 1 1 111px;
  background-color: #ffc300;
  border-radius: 15px;
  height: 40px;
}
.text_9 {
  color: #ffffff;
  line-height: 18.5px;
}
</style>