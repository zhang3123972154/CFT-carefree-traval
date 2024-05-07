<template>
    <view class="flex-vertical gap-10 container">
        <template v-for="(item, index) in TITLE" :key="index">
            <view class="flex-top-left-horizontal gap-20">
                <view class="title">{{ item }}</view>
                <view class="flex-vertical gap-10">
                    <template v-for="(tag, id) in props.list[index]" :key="id">
                        <view class="flex-horizontal gap-10">
                            <t-chip
                                :kind="tag.type"
                                :text="setText(tag.type, tag.text)"
                                :light="tag.text != ''"
                            />
                            <chip-annotation
                                :kind="tag.type"
                            />
                        </view>
                    </template>
                </view>
            </view>
        </template>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // com
    import chipAnnotation from "../../Com/chip/chipAnnotation.vue";
    // store
// DATA
    const props = defineProps({
        list: {
            type: Array,
            default: () => []
        }
    });
    const emits = defineEmits([]);

    const TITLE = ["上午", "午餐", "中午", "晚餐", "晚上"];

// FUNC
    const setText = (type, text) => {
        if(text != "")
            return text;
        switch(type) {
            case "spot": return "地点";
        }
    }
</script>

<style scoped>

.container {
    font-family: Alimama ShuHeiTi;
    font-size: 12px;
}

.title {
    transform: translateY(7px); /* 手动对齐 */
}

</style>        