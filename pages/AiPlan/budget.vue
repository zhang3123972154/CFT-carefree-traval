<template>
    <view class="text-container mt-30">
        <text>大约 {{ moneyAbout }} 元</text>
        <up-slider 
            :value="sliderValue_about"
            activeColor="#ffc300"
            @input="changeValue_about">
        </up-slider>
        <view class="mt-30"></view>
        <text>封顶 {{ moneyMax }} 元</text>
        <up-slider
            :min="sliderValue_about"
            :value="sliderValue_max"
            activeColor="#ffc300"
            @input="changeValue_max">
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

    const sliderValue_about = ref(30);
    const sliderValue_max = ref(38);

// FUNC
    const moneyAbout = computed(() => {
        let mappedValue;
        let roundedValue;

        if (sliderValue_about.value >= 0 && sliderValue_about.value <= 30) {
            // 映射 [0, 30] 到 [200, 1000]
            const A1 = 0;
            const B1 = 30;
            const C1 = 200;
            const D1 = 1000;
            mappedValue = ((sliderValue_about.value - A1) * (D1 - C1) / (B1 - A1) + C1);
            // 以 50 为步长进行四舍五入
            roundedValue = Math.round(mappedValue / 50) * 50;
        } else if (sliderValue_about.value > 30 && sliderValue_about.value <= 100) {
            // 映射 [30, 100] 到 [1000, 10000]
            const A2 = 30;
            const B2 = 100;
            const C2 = 1000;
            const D2 = 10000;
            mappedValue = ((sliderValue_about.value - A2) * (D2 - C2) / (B2 - A2) + C2);
            // 以 100 为步长进行四舍五入
            roundedValue = Math.round(mappedValue / 100) * 100;
        } else {
            // 如果 sliderValue_about.value 超出 [0, 100] 的范围，可以根据需要处理错误或返回默认值
            throw new Error('Input value out of range');
        }

        return roundedValue;
    })
    const moneyMax = computed(() => {
        let mappedValue;
        let roundedValue;

        if (sliderValue_max.value >= 0 && sliderValue_max.value <= 30) {
            // 映射 [0, 30] 到 [200, 1000]
            const A1 = 0;
            const B1 = 30;
            const C1 = 200;
            const D1 = 1000;
            mappedValue = ((sliderValue_max.value - A1) * (D1 - C1) / (B1 - A1) + C1);
            // 以 50 为步长进行四舍五入
            roundedValue = Math.round(mappedValue / 50) * 50;
        } else if (sliderValue_max.value > 30 && sliderValue_max.value <= 100) {
            // 映射 [30, 100] 到 [1000, 10000]
            const A2 = 30;
            const B2 = 100;
            const C2 = 1000;
            const D2 = 10000;
            mappedValue = ((sliderValue_max.value - A2) * (D2 - C2) / (B2 - A2) + C2);
            // 以 100 为步长进行四舍五入
            roundedValue = Math.round(mappedValue / 100) * 100;
        } else {
            // 如果 sliderValue_about.value 超出 [0, 100] 的范围，可以根据需要处理错误或返回默认值
            throw new Error('Input value out of range');
        }

        return roundedValue;
    })
    
    const changeValue_about = (value) => {
        sliderValue_about.value = value;

        if(value > sliderValue_max.value)
            sliderValue_max.value = value;
    }

    const changeValue_max = (value) => {
        sliderValue_max.value = value;
    }

    // 离开之时
    watch(() => props.questionIndex, (newValue, oldValue) => {
        if(oldValue == 2)
            budgetStore.setBudget(moneyAbout.value, moneyMax.value);
    })

</script>

<style scoped>

.text-container {
    font-family: Alimama ShuHeiTi;
    font-size: 25px;
}

</style>        