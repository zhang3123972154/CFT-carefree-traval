<template>
    <view class="text-container mt-30">
        <text>{{ money }}元</text>
        <up-slider 
            :value="sliderValue"
            activeColor="#ffc300"
            @input="changeValue">
        </up-slider>
    </view>
</template>

<script setup>
    import { ref, computed, watch } from "vue";
    // store
    import { useBudget } from "@/store/aiPlanQuestion";
    const budgetStore = useBudget();
// DATA
    const props = defineProps({
        questionIndex: Number
    });
    const emits = defineEmits([]);

    const sliderValue = ref(30);
// FUNC
    const money = computed(() => {
        let mappedValue;
        let roundedValue;

        if (sliderValue.value >= 0 && sliderValue.value <= 30) {
            // 映射 [0, 30] 到 [200, 1000]
            const A1 = 0;
            const B1 = 30;
            const C1 = 200;
            const D1 = 1000;
            mappedValue = ((sliderValue.value - A1) * (D1 - C1) / (B1 - A1) + C1);
            // 以 50 为步长进行四舍五入
            roundedValue = Math.round(mappedValue / 50) * 50;
        } else if (sliderValue.value > 30 && sliderValue.value <= 100) {
            // 映射 [30, 100] 到 [1000, 10000]
            const A2 = 30;
            const B2 = 100;
            const C2 = 1000;
            const D2 = 10000;
            mappedValue = ((sliderValue.value - A2) * (D2 - C2) / (B2 - A2) + C2);
            // 以 100 为步长进行四舍五入
            roundedValue = Math.round(mappedValue / 100) * 100;
        } else {
            // 如果 sliderValue.value 超出 [0, 100] 的范围，可以根据需要处理错误或返回默认值
            throw new Error('Input value out of range');
        }

        return roundedValue;
    })
    const changeValue = (value) => {
        sliderValue.value = value;
    }

    // 离开之时
    watch(() => props.questionIndex, (newValue, oldValue) => {
        if(oldValue == 2)
            budgetStore.setBudget(money.value);
    })

</script>

<style scoped>

.text-container {
    font-family: Alimama ShuHeiTi;
    font-size: 25px;
}

</style>        