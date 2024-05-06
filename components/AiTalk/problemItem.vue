<template>
    <view class="container" :class="{
        'selected-container': selecteFlag
    }"
    @click="triggle">
        {{props.text}}
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        text: {
            type: String,
            default: ""
        }
    });
    const emits = defineEmits(["clickChoose"]);

    // flag
    const selecteFlag = ref(false);

// FUNC
    const triggle = () => {
        selecteFlag.value = true;
        emits("clickChoose");
    }

</script>

<style scoped>

.container {
    padding: 5px;

    font-weight: 300;

    border-radius: 5px;
    border: 1px solid #0000008c;

    transition: color .2s ease, background-color .2s ease;
}

.selected-container {
    border-color: #ffc300;
    color: #ffc300;
    font-weight: 500;
    background-color: #ffc40019;
}

</style>        