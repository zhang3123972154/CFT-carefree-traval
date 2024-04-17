<template>
    <t-chip :light="props.light" :kind="props.kind"
        @click="inputFlag = true">
        <view class="flex-center-both container"
            :style="{
                '--width': inputWidth
            }"
            >
            <u-icon v-if="!inputFlag" 
                name="/static/icon/add-plus.svg"
                />
            <input v-else 
                class="input"
                focus 
                @input="handleInput"
                @blur="handleBlur"/>
        </view>
    </t-chip>
</template>

<script setup>
    import { ref, computed, watch } from "vue";
    // store
// DATA
    const props = defineProps({
        light: {
            type: Boolean,
            default: false
        },
        kind: String,
        clearTrigger: { // info 通过反转来触发 clear
            type: Boolean,
            default: false
        }
    });
    const emits = defineEmits(["textFinish"]);
    const inputValue = ref("");

    // flag
    const inputFlag = ref(false);
    
// FUNC
    const inputWidth = computed(() => {
        // console.info(inputValue.value.length * 15);
        return (inputValue.value.length * 15).toString() + 'px';
    })

    watch(() => props.clearTrigger, () => {
        inputValue.value = "";
        inputFlag.value = false;
    });

    // tag real
    const handleInput = (event) => {
        inputValue.value = event.detail.value;
    }
    const handleBlur = () => {
        if(inputValue.value == "")
            inputFlag.value = false;
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