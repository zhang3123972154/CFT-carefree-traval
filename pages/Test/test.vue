<template>
    <view class="carousel-container" @touchstart="touchStart" @touchend="touchEnd">
      <view class="carousel-wrapper" :style="{ transform: `translate3d(${offset}px, 0, 0)`, transitionDuration: transitionDuration + 'ms' }">
        <view class="carousel-item" v-for="(item, index) in carouselItems" :key="index">
          <!-- 轮播内容 -->
          <text>{{ item }}</text>
          <text>123</text>
        </view>
      </view>
    </view>
  </template>
  
  <script>
    export default {
      data() {
        return {
          carouselItems: ["Item 1", "Item 2", "Item 3"], // 轮播内容数组
          offset: 0, // 轮播偏移量
          transitionDuration: 300, // 动画过渡时间
          currentIndex: 0, // 当前轮播项索引
          startTouchX: 0, // 触摸开始的X坐标
        };
      },
      methods: {
        touchStart(event) {
          // 记录开始触摸的位置
          this.startTouchX = event.touches[0].clientX;
          // 清除动画时间，以便跟随手指即时滑动
          this.transitionDuration = 0;
        },
        touchEnd(event) {
          // 计算手指滑动结束时的X坐标，并与开始触摸的X坐标比较
          let endTouchX = event.changedTouches[0].clientX;
          let deltaX = endTouchX - this.startTouchX;
          // 根据滑动的距离判断是向左还是向右滑动，并更新currentIndex
          if (deltaX < -50 && this.currentIndex < this.carouselItems.length - 1) { // 向左滑动
            this.currentIndex++;
          } else if (deltaX > 50 && this.currentIndex > 0) { // 向右滑动
            this.currentIndex--;
          }
          // 设置偏移量，更新视图位置
          this.offset = -this.currentIndex * 100; // 假设每个轮播项的宽度是100%
          // 恢复动画时间，平滑过渡
          this.transitionDuration = 300;
        }
      }
    }
  </script>
  
  <style scoped>
  .carousel-container {
    overflow: hidden;
    position: relative;
    width: 100%; /* 容器宽度 */
    height: 200px; /* 容器高度 */
  }
  
  .carousel-wrapper {
    display: flex;
    flex-direction: row;
    width: 300%; /* 假设有三个轮播项，可以根据轮播内容数量动态计算宽度 */
  }
  
  .carousel-item {
    flex: 0 0 100%;
    /* 定义单个滑动项的宽度，这里为容器的100%，意味着在屏幕中完整显示 */
    width: 100%;
    height: 100%; /* 单个滑动项的高度 */
    text-align: center;
    line-height: 200px; /* 示例样式 */
  }
  </style>
  