<template>
    <view class="flex-center-both">
        <floatWin>
            <view class="flex-center-vertical">
                <u-icon class="mt-80" :name="iconPath.LOGO" size="105"></u-icon>
                <text class="talk">您希望您的旅行是什么样的呢？</text>
                <view class="input-container">
                    <u--textarea v-model="content" 
                        placeholder="说点什么吧......"
                        border="none"
                        fontSize="14"
                        height="80"
                        :customStyle="{
                            borderRadius: '20px',
                            padding: '15px',
                            border: '1px solid #000',
                            paddingRight: '45px',
                        }"
                    ></u--textarea>
                    <view class="input-func-container">
                        <view v-if="inputNULL" class="flex-horizontal gap-20">
                            <u-icon :name="iconInput.microphone" size="20"></u-icon>
                            <u-icon :name="iconInput.camera" size="20"></u-icon>
                        </view>
                        <u-icon v-else :name="iconPath.clear" size="20" @click="clearInput"></u-icon>
                    </view>
                </view>
                <view class="flex-center-horizontal gap-35 mt-50">
                    <t-btn variant="tonal" class="btn-base">上一题</t-btn>
                    <t-btn variant="tonal" class="btn-base">下一题</t-btn>
                </view>
                <view class="flex-center-horizontal gap-35 mt-10">
                    <t-btn variant="outlined" class="btn-base" :custom-style="{
                        color: '#666666',
                        borderColor: '#666666'
                    }" @click="gotoBack">取消</t-btn>
                    <t-btn variant="tonal" class="btn-base" :custom-style="{
                        color: '#FFFFFF',
                        backgroundColor: '#FFC300'
                    }">完成</t-btn>
                </view>
            </view>
        </floatWin>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // com
    import floatWin from "@/components/Com/floatWin.vue";
    // store
    import { useAiIconPath, useDetailIconPath } from "@/store/dataBase.ts";
    const iconPath = useAiIconPath();
    const iconInput = useDetailIconPath();
// DATA
    const emits = defineEmits(['close']);

    const content = ref("");
// FUNC
    const inputNULL = computed(() => {
        return content.value === "";
    });

    const clearInput = () => {
        content.value = "";
    }

    const gotoBack = () => {
        uni.navigateBack({ delta: 1 });
    }

</script>

<style scoped>

.talk {
    margin-top: 50px;

    font-size: 18px;
    line-height: 16.5px;
    font-weight: 300;
}

.input-container {
    margin-top: 15px;

    position: relative;

    width: 100%;
    max-width: 500px;
}

.input-func-container {
    position: absolute;
    top: 15px;
    right: 20px;

    z-index: 2000;
}

.btn-base {
    padding: 12px 0;
    height: 40px;
    width: 120px;

    font-size: 18px;
    line-height: 16.5px;
}

</style>        