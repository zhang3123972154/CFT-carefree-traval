<template>
    <view class="flex-top-horizontal container gap-10">
        <u-icon :name="iconPath.AI" size="30"></u-icon>
        <view class="talk-container wrap">
            <component :is="item.type" v-for="(item, index) in props.content" :key="index">
                <template v-if="item.type === 'endl'">
                    <view>{{ item.text }}</view>
                </template>
                <template v-else-if="item.type[0] === 'P'"> <!--info 为了方便，只比较了 ‘P’-->
                    <view class="flex-center-horizontal PT-container block gap-10">
                        <!-- <view class="flex-horizontal gap-10"> -->
                        <!--bug chip会收到 text 的宽度影响-->
                            <view class="relative">
                                <up-image :src="item.imgPath" fade radius="5" width="50" height="50"/>
                                <view class="img-grade">{{ item.grade }}</view>
                            </view>
                            <view class="shrink">
                                <view class="flex-horizontal">
                                    <t-chip class="t-ship" :kind="getType(item.type)" :text="item.text"></t-chip>
                                    <view class="shrink"></view>
                                </view>
                                <text class="location-text">{{ item.location }}</text>
                                <!-- <view class="location-text">{{ item.location }}</view> -->
                            </view>
                        <!-- </view> -->
                        <view class="flex-center-both price-container">
                            <view v-if="item.price === 0" style="justify-content: flex-end;">
                                <span style="font-size: 18px;">免费</span>
                            </view>
                            <view v-else>
                                <span style="font-size: 10px;">￥</span>
                                <span style="font-size: 18px;">{{ item.price }}</span>
                                <span style="font-size: 8px;">起</span>
                            </view>
                        </view>
                    </view>
                </template>
                <template v-else>
                    <span :class="item.type">{{ item.text }}</span>
                </template>
            </component>
        </view>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({
        content: {
            type: Array,
            default: () => [
                { type: "text", text: "AI助手生成中..." },
                { type: "way", text: "way-test" },
                { type: "text", text: "，" },
                { type: "spot", text: "spot-test" },
                { type: "text", text: "，" },
                { type: "thing", text: "thing-test" },
                { type: "endl", text: "" },
                { type: "text", text: "接下来是规划测试..."},
            ]
        },
        // ... 如何处理 tags
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

.container {
    justify-content: flex-start;
}

.talk-container {
    max-width: calc(100% - 80px);

    padding: 10px 12px;
    background-color: #fff;
    border-radius: 5px 15px 15px 15px;

    font-size: 16px;
    font-family: SourceHanSansCN;
}

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

.text {
    font-weight: 300;
}

.way {
    color: #959cf0;
}

.spot {
    color: #a795f0;
}

.thing {
    color: #c895f0;
}

.location-text {
    font-size: 10px;
    color: #a68f47;
}

.t-ship {
    flex-shrink: 0;
}

</style>        