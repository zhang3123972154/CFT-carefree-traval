<template>
    <view class="flex-top-horizontal container gap-10">
        <view class="relative">
            <u-icon :name="props.avatar" size="30"></u-icon>
            <view v-if="props.wordByWord" class="loader"/>
        </view>
        <view class="talk-container wrap">
            <component :is="item.type" v-for="(item, index) in props.content" :key="index">
                <template v-if="item.type === 'endl'">
                    <view>{{ item.text }}</view>
                </template>
                <template v-else-if="item.type[0] === 'P'"> <!--info 为了方便，只比较了 ‘P’-->
                    <!--info 原版直接展示-->
                    <!-- <pt-item
                        :type="getType(item.type)"
                        :text="item.text"
                        :grade="item.grade"
                        :price="item.price"
                        :img-path="item.imgPath"
                        :location="item.location"  
                    /> -->
                    <!--info 新版集中展示-->
                    <trigger-func :item="item" @emit-back-object="loadinPTList"/>
                </template>
                <template  v-else-if="item.type[0] === 'C'">
                    <chip-group-single-choose
                        :tag-kind="getType(item.type)"
                        :tag-list="item.list"
                        :reload="item.reload"
                        :reply="item.reply"
                    />
                </template>
                <template v-else-if="item.type[0] === 'I'">
                    <ib-item
                        :type="getType(item.type)"
                        :text="item.text"
                        :grade="item.grade"
                        :price="item.price"
                        :img-path="item.imgPath"
                        :location="item.location"
                        :introduce="item.introduce"
                    />
                </template>
                <template v-else-if="item.type[0] === 'O'">
                    <optional-problem
                        :list="item.list"
                    />
                </template>
                <template v-else> <!--text || loading-->
                    <span v-if="!props.wordByWord" :class="item.type">{{ item.text }}</span>
                    <word-by-word v-else :classCustom="item.type" :text="item.text"/>
                </template>
            </component>
            <!--test-->
            <!--info 用于存放左右滑动版本的PT信息块-->
            <swiper v-if="PTList != []"
                class="swiper"
                :style="{
                    '--swiper-height': setSwiperHeight
                }"
                indicator-dots
                circular
            >
                <swiper-item v-for="(itemList, index) in PTList">
                    <pt-item v-for="(item, index) in itemList"
                        :type="item.type"
                        :text="item.text"
                        :grade="item.grade"
                        :price="item.price"
                        :img-path="item.imgPath"
                        :location="item.location"  
                    />
                </swiper-item>
        </swiper>
        </view>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // com
    import wordByWord from "./wordByWord.vue";
    import ptItem from "./ptItem.vue";
    import ibItem from "./ibItem.vue";
    import triggerFunc from "./triggerFunc.vue";
    import chipGroupSingleChoose from "@/components/AiTalk/chipGroupSingleChoose.vue";
    import optionalProblem from "./optionalProblem.vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({
        avatar: {
            type: String,
            default: "/static/icon/AiTalk/AI.svg"
        },
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
        time: String, // todo 
        // ... 如何处理 tags
        wordByWord: Boolean
    });
    const emits = defineEmits([]);

    const PTList = ref([[]]);
// FUNC
    const swiperColNum = computed(() => {
        return Math.ceil(PTList.value.length / 3);
    })
    const setSwiperHeight = computed(() => {
        let height = 0;
        if(PTList.value[0])
            switch(PTList.value[0].length) {
                case 0: height = 0; break;
                case 1: height = 80; break;
                case 2: height = 145; break;
                case 3: height = 205; break;
                default: height = 205; break;
            }
        return height.toString() + "px";
    })

    // info 会填入到 左右滑动栏 的版本
    const loadinPTList = (item) => {
        if(PTList.value.length == 0 || PTList.value[PTList.value.length-1].length == 3)
            PTList.value.push([]);
        PTList.value[PTList.value.length-1].push(item);
        console.info(PTList.value);
        return "";
    }

    // info re正则获取结点类别
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

.swiper {
    width: 100%;
    height: var(--swiper-height);
}

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

/* animation */
.loader {
    position: absolute;
    right: -2px;
    bottom: -2px;

    border: 1px solid #6464646b; /* 浅灰色背景 */
    border-top: 2px solid #000000; /* 蓝色 */
    border-radius: 50%;
    width: 10px;
    height: 10px;
    animation: spin 2s linear infinite;

    background-color: #fff;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

</style>        