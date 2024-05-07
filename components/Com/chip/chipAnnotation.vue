<template>
    <t-chip :light="inputFlag" :light-start="inputFlag" :kind="props.kind">
        <!--info 同时使用有奇效！！-->
        <view class="flex-center-both container"
            :style="{
                '--width': inputWidth
            }"
            >
            <input
                class="input"
                :value="inputValue"
                @focus="inputInit"
                @input="handleInput"
                @blur="handleBlur"/>
        </view>
    </t-chip>
</template>

<script setup>
    import { ref, computed } from "vue";
    // js
    import { countCharacters } from "@/js/data";
    // store
// DATA
    const props = defineProps({
        kind: String
    });
    const emits = defineEmits(["textFinish"]);

    // core data
    const inputValue = ref("点击进行备注");

    // flag
    const inputFlag = ref(false);
    
// FUNC
    const inputWidth = computed(() => {
        if(!inputFlag.value)    // info 优化一下，默认值不必计算。
            return "90px";

        const counts = countCharacters(inputValue.value);
        const length = (counts.englishCount + counts.digitCount) * 12 + ( counts.chineseCount + counts.symbolCount) * 15;
        return length.toString() + 'px';
    })

    // tag real
    const inputInit = () => {
        if(!inputFlag.value) {
            inputValue.value = "";
            inputFlag.value = true;
        }
    }
    const handleInput = (event) => {
        inputValue.value = event.detail.value;
    }
    const handleBlur = () => {
        if(inputValue.value == "") {
            inputValue.value = "点击进行备注"
            inputFlag.value = false;
        }
        else
            emits("textFinish", inputValue.value);
    }

</script>

<style scoped>

.container {
    min-width: 30px;
    width: var(--width);
    transition: width .2s ease; /* bug 输入时有一定的抖动，em... */
}

.input {
    font-size: 14px;
}

</style>        