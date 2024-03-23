<template>
  <view class="star-container">
    <view id="container" class="tagBall" @touchmove.prevent @touchstart="handleTouchStart" @touchmove="handleTouchMove"
      @touchend="handleTouchEnd">
      <view v-for="(item, index) in nameList" :key="index" class="tag flex-center-vertical" :style="tagsStyles[index]"
        :class="{'tag-animation': animationFlag}" @click="deep(index+1)">
        <view class="point"></view>
        <view>
          {{ item.wordName }}
        </view>
      </view>
    </view>
    <view class="star-btn flex-horizontal gap-5">
      <t-btn-icon @click="reload" icon="/static/icon/reload.svg" shadow></t-btn-icon>
      <t-btn-icon @click="back" icon="/static/icon/back.svg" shadow></t-btn-icon>
    </view>
  </view>
</template>

<script setup>
  import { ref, onMounted, nextTick } from 'vue';

// DATA
  const props = defineProps({
    resetFlag: {
      type: Boolean,
      default: false
    },
    backFlag: {
      type: Boolean,
      default: false
    }
  })
  // const
  const RADIUS = 130;
  const RADIUS_BIGGER = 210;
  const FALL_LENGTH = 300;  // scale 有关

  const ANGLE_AUTO = 300;
  const ANGLE_MAGNITUDE = Math.PI / ANGLE_AUTO;

  const TIME_TOUCHED = 3000;
  const TIME_ANIMATION = 1000; // test
  const RATIO = .005;
  // var
  let angleX = Math.PI / ANGLE_AUTO;
  let angleY = Math.PI / ANGLE_AUTO;
  
  let touchStartX, touchStartY;
  // list
  const nameList = ref([
    // 添加Array1中的中文词汇
    { wordName: '打卡拍照', wordValue: 600 },
    { wordName: '徒步出行', wordValue: 600 },
    { wordName: '穷游', wordValue: 600 },
    { wordName: '旅行搭子', wordValue: 600 },
    { wordName: '人文风景', wordValue: 600 },
    { wordName: '自然风景', wordValue: 600 },
    { wordName: '娱乐商圈', wordValue: 600 },
    { wordName: '甜品饮品', wordValue: 600 },
    { wordName: '公园乐园', wordValue: 600 },
    { wordName: '美食小吃', wordValue: 600 },
    { wordName: '海景江景', wordValue: 600 },
    { wordName: '爬山', wordValue: 600 },
    // 添加Array2中的中文词汇
    { wordName: '春天', wordValue: 600 },
    { wordName: '夏天', wordValue: 600 },
    { wordName: '秋天', wordValue: 600 },
    { wordName: '冬天', wordValue: 600 },
    // 添加Array3中的中文词汇
    { wordName: '高铁火车', wordValue: 600 },
    { wordName: '飞机', wordValue: 600 },
    { wordName: '游轮', wordValue: 600 },
    { wordName: '大巴', wordValue: 600 },
    { wordName: '自驾游', wordValue: 600 },
    // ... 可能有的其他标签
  ]);
  let tags = [];
  const tagsStyles = ref(nameList.value.map(() => ({})));
  // flag
  let touchedFlag = false;  // 当触发按动后，一段时间内不会自动转（eg.3s?）。
  const animationFlag = ref(true);
  // store
  const pathHistory = ref([1   ]);

// FUNC
  onMounted(() => {
    init();
    console.info("finish init.");
    animate();
  });

  const init = (radius = RADIUS, transparent = false) => {
      console.info("init", radius);

      //  mark 使用uni-app的API获取元素尺寸
      const query = uni.createSelectorQuery().in(this);
      query.selectAll('.tag').fields({ size: true, rect: true,}, 
        (tagsRects) => { 
          tags = [];  // 重置！
          tagsRects.forEach((tagRect, i) => {
            // 计算 3D 模型的位置
            const k = (2 * (i + 1) - 1) / tagsRects.length - 1;
            const a = Math.acos(k);
            const b = a * Math.sqrt(tagsRects.length * Math.PI);
            const x = radius * Math.sin(a) * Math.cos(b);
            const y = radius * Math.sin(a) * Math.sin(b);
            const z = radius * Math.cos(a);
            tags.push({ element: tagRect, x, y, z });
          });
          if(transparent)
            tags.forEach((tag, index) => moveTag(tag, index, null));
          else
            tags.forEach((tag, index) => moveTag(tag, index, radius));
      }).exec();
  };

  // 移动 Tag
  const moveTag = (tag, index, radius) => {
    const scale = FALL_LENGTH / (FALL_LENGTH - tag.z);
    // mark alpha 强相关 radius，radius 无效时代表 transparent.
    let alpha = 0;
    if(radius)
      alpha = (tag.z + radius) / (2 * radius) + 0.1;
    const offsetX = tag.x - 10; // mark 这里手动调整了一下。
    const offsetY = tag.y;

    // console.info(index, scale, alpha, offsetX, offsetY);
    tagsStyles.value[index] = {
      transform: `translate3d(${offsetX}px, ${offsetY}px, 0) scale(${scale})`,
      opacity: alpha,
      zIndex: parseInt(scale * 100).toString(),
    };
  };

  const animate = (radius = RADIUS) => {
    console.info("animate run.");
    setInterval(() => {
      if(!touchedFlag) {
        rotateX();
        rotateY();
        tags.forEach((tag, index)=> moveTag(tag, index, radius));
      }
    }, 200);
  };

  const animateAgain = () => {
    touchedFlag = false;
    animate();
  }

  const rotateX = () => {
    const cos = Math.cos(angleX);
    const sin = Math.sin(angleX);
    tags.forEach(tag => {
      const x1 = tag.x * cos - tag.z * sin;
      const z1 = tag.z * cos + tag.x * sin;
      tag.x = x1;
      tag.z = z1;
    });
  };

  const rotateY = () => {
    const cos = Math.cos(angleY);
    const sin = Math.sin(angleY);
    tags.forEach(tag => {
      const y1 = tag.y * cos - tag.z * sin;
      const z1 = tag.z * cos + tag.y * sin;
      tag.y = y1;
      tag.z = z1;
    });
  };

  // 扩张 && 收缩
  const expand = async() => {
    //  基于原有的坐标实现。
    const scale = RADIUS_BIGGER / RADIUS;
    tags.forEach((tag, index) => {
      tag.x *= scale;
      tag.y *= scale;
      tag.z *= scale;
      moveTag(tag, index);  // update 如果点数过多，那么扩张很可能不太同步。
    });
  }

  const shrink = () => {
    
  }

  const clear = () => {
    tagsStyles.value = nameList.value.map(() => ({}));
  }

  // touch
  const handleTouchStart = (event) => {
    touchedFlag = true; // 打断自动转动。

    touchStartX = event.changedTouches[0].pageX;
    touchStartY = event.changedTouches[0].pageY;
    event.stopPropagation();
    console.info("touch-start!", touchStartX, touchStartY);
  }

  const handleTouchMove = (event) => {
    touchedFlag = true; // 继续打断

    const touchMoveX = event.changedTouches[0].pageX;
    const touchMoveY = event.changedTouches[0].pageY;

    const deltaX = touchStartX - touchMoveX;
    const deltaY = touchStartY - touchMoveY;
    // 映射
    angleX = deltaX * RATIO;
    angleY = deltaY * RATIO;

    // console.info("touch-move...", deltaX, deltaY);
    rotateX();
    rotateY();
    tags.forEach((tag, index)=> moveTag(tag, index, RADIUS));

    // ReLoad
    touchStartX = touchMoveX;
    touchStartY = touchMoveY;
    event.stopPropagation();
  };

  const handleTouchEnd = (event) => {
    // mark 方案一
    // let dis = Math.pow(angleX, 2) + Math.pow(angleY, 2);
    // let factor = ANGLE_MAGNITUDE / dis;
    // angleX *= factor;
    // angleY *= factor;

    // 方案二
    // angleX = angleY = Math.PI / ANGLE_AUTO;

    // 方案三
      // mark 目前效果最佳？
    angleX = angleX > 0 ? Math.min(angleX, ANGLE_MAGNITUDE) : Math.max(angleX, -ANGLE_MAGNITUDE);
    angleY = angleY > 0 ? Math.min(angleY, ANGLE_MAGNITUDE) : Math.max(angleY, -ANGLE_MAGNITUDE);
    
    setTimeout(() => {  // 再次触发动画。
      touchedFlag = false;
      animate();  
    }, TIME_TOUCHED);
    console.info("touch-end!", event);
  };

  // js
  const adjust = (x) => {
    let factor = ANGLE_MAGNITUDE / x;
    return (x * factor).toFixed(3);
  }
  // mark 模拟sleep，但是并不会闭塞整个程序，而是实现一定的延迟。
  const sleep = (ms) => {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  // click
  const deep = async(index) => {
    touchedFlag = true;

    // 1. expend
    // init(RADIUS_BIGGER, false);
    await expand();
    await sleep(TIME_ANIMATION);  // update
    // 2. data
    // update 实际上，获取新数据，更应该放在sleep的期间。
    pathHistory.value.push(index);  // 路径保存
    let newNameList = [];
    for(let i=1; i<21; i++)
      newNameList.push({wordName: `Star${index}-${i}`, worfValue: 600});    
    // 3. new
    // setTimeout(() => {
    //   tagsStyles.value = nameList.value.map(() => ({}));
    //   setTimeout(() => {
    //     nameList.value = newNameList;
    //     init(RADIUS);
    //     setTimeout(() => {
    //       animationFlag = false;
    //     }, TIME_ANIMATION / 2);
    //   }, TIME_ANIMATION / 2);
    // }, TIME_ANIMATION);  // mark 先这样
    animationFlag.value = false;
    clear();

    nextTick(() => {  // DOM 更新。
      animationFlag.value = true;
      nameList.value = newNameList;
      init();
      animateAgain();
    })
  }

  const reload = async() => {
    touchedFlag = true;

    clear();
    await sleep(TIME_ANIMATION * .7);  // update 之后同时获取新的数据

    init();
    animateAgain();
  }

  const back = async() => {
    touchedFlag = true;

    // 1.
    clear();
    await sleep(TIME_ANIMATION);
    // 2.
    let newNameList = [];
    let index = pathHistory.value.pop();
    for(let i=1; i<21; i++)
      newNameList.push({wordName: `Star${index}-${i}`, worfValue: 600});
    // 3.
    // setTimeout(() => {
    //   init(RADIUS_BIGGER);
    //   nameList.value = newNameList;
    //   setTimeout(() => {
    //     init(RADIUS);
    //   }, TIME_ANIMATION / 2);
    // }, TIME_ANIMATION);  // mark 先这样
    animationFlag.value = false;
    init(RADIUS_BIGGER, true);
    await sleep(50); // update 有效，但是不合适。
    console.info("wait-over");
    
    nextTick(() => {
      animationFlag.value = true;
      nameList.value = newNameList;
      init();
      animateAgain();
    })
  }

</script>

<style scoped>
.star-container {
  position: relative;
  height: 300px;
}

.tagBall {
  width: 100%;
  height: 300px;
  position: relative;

  overflow: hidden;
}

.point {
  height: 10px;
  width: 10px;
  border-radius: 50%;

  background-color: #FFC300;
}

/* .point:hover {
  box-shadow: 0 0 10px 5px #ff8e8e86;
} */

.tag {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 20px;

  color: #000;
  font-size: 10px;
  font-weight: bold;
  white-space: nowrap; /* 文本不换行 */

  /* animatoin */
  transform-origin: center; /* 确保扩散动画从中心开始 */
  opacity: 0; /* 初始透明度 */
}

.tag-animation {
  transition: transform 1s ease-out, opacity 1s ease-out; /* 过渡动画 */
}

.btn-test {
  margin: 50px;
}

.star-btn {
  position: absolute;
  bottom: 5px;
  right: 5px;
}

</style>
