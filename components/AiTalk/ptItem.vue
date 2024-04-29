<!--info 用 aitalk 一般的点位信息块展示-->
<template>
    <view class="flex-center-horizontal PT-container block gap-10">
        <!-- <view class="flex-horizontal gap-10"> -->
        <!--bug chip会收到 text 的宽度影响-->
            <view class="relative">
            <up-image :src="props.imgPath" fade radius="5" width="50" height="50"/>
            <view class="img-grade">{{ props.grade }}</view>
        </view>
        <view class="shrink">
            <view class="flex-horizontal">
                <t-chip class="t-ship" :kind="getType(props.type)" :text="props.text"></t-chip>
                <view class="shrink"></view>
            </view>
            <text class="location-text">{{ props.location }}</text>
            <!-- <view class="location-text">{{ props.location }}</view> -->
        </view>
        <!-- </view> -->
        <view class="flex-center-both price-container">
            <view v-if="props.price === 0" style="justify-content: flex-end;">
                <span style="font-size: 18px;">免费</span>
            </view>
            <view v-else>
                <span style="font-size: 10px;">￥</span>
                <span style="font-size: 18px;">{{ props.price }}</span>
                <span style="font-size: 8px;">均</span>
            </view>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        type: {
            type: String,
            default: "PT-spot"
        }, 
        text: {
            type: String,
            default: "默认点名"
        },
        grade: {
            type: Number,
            default: 4.9
        },
        location: {
            type: String,
            default: "XXX"
        },
        price: {
            type: Number,
            default: 0
        },
        imgPath: {
            type: String,
            default: ""
        }
    });
    const emits = defineEmits([]);

// FUNC
    const getType = (type) => {
        const regexp = /-(.*)/;
        const matches = type.match(regexp);

        if (matches && matches.length > 1) {
            console.log(matches[1]); // 输出: spot
            return matches[1];
        } else {
            console.log("没有找到匹配的字符串");
        }
    }

</script>

<style scoped>

.PT-container {
    margin: 10px 0;
}


.img-grade {
    position: absolute;
    right: 3px;
    top: 3px;
    padding: 0 2px;

    border-radius: 3px;
    background-color: #00000080;

    font-size: 10px;
    font-family: Alimama ShuHeiTi;
    color: #ffc300;
}

.price-container {
    padding: 8px 0;
    background-color: #ffc300;
    border-radius: 5px;
    width: 50px;
    height: 30px;

    color: #fff;
    font-family: Alimama ShuHeiTi;
}

.location-text {
    font-size: 10px;
    color: #a68f47;
}

.t-ship {
    flex-shrink: 0;
}

</style>        