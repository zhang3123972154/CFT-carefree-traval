<template>
    <view class="container">
        <t-btn variant="tonal" :pre-icon="props.icon" icon-size="21"
            :custom-style="{
                padding: '10px',
            }">
            <view class="flex-vertical container-text">
                <text class="func-name">{{ props.title }}</text>
                <text class="func-used-num">{{ props.subTitle }}</text>
            </view>
        </t-btn>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    import tBtn from "@/components/Com/Button.vue";
    // store
// DATA
    const props = defineProps({
        icon: {
            type: String,
        },
        title: {
            type: String,
            default: "默认名"
        },
        subTitle: {
            type: String,
            default: "已有000人使用"
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container {
    padding: 10px;
}

.func-name {
    font-size: 12px;
    line-height: 11px;
}

.func-used-num { 
    margin-top: 3px;
    font-size: 8px;
    line-height: 7.5px;
    font-weight: 300;
    color: #999999;
}

.container-text {
    margin-left: 5px;
}

</style>        