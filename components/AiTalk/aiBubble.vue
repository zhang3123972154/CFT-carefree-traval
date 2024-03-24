<template>
    <view class="flex-top-horizontal container gap-10">
        <u-icon :name="iconPath.AI" size="30"></u-icon>
        <view class="talk-container wrap">
            <component :is="item.type" v-for="(item, index) in props.content" :key="index">
                <template v-if="item.type === 'endl'">
                    <view>{{ item.text }}</view>
                </template>
                <template v-else>
                    <span :class="item.type">{{ item.text }}</span>
                </template>
            </component>
            
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
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

    const rawHtml = ref('<p>这是一段<topic-chunk>格式化的</topic-chunk>HTML内容。</p>');

// FUNC

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

</style>        