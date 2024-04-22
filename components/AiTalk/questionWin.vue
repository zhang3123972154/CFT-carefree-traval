<template>
    <view class="flex-center-both">
        <floatWin>
            <view class="flex-center-vertical">
                <view class="flex-horizontal">
                    <text></text>
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
    import { useTitle } from "@/store/aiPlanQuestion";
    const titleStore = useTitle();
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