<template>
    <span>{{ dataShow }}</span>
</template>

<script setup>
    import { ref, onUnmounted } from "vue";
    // store
// DATA
    const props = defineProps({
        text: {
            type: String,
            default: ""
        },
        interval: {
            type: Number,
            default: 100
        }
    });
    const emits = defineEmits([]);

    const dataShow = ref("");
    let dataLength = 0;
// FUNC
    const timer = setInterval(() => {
        dataShow.value = props.text.substr(0, dataLength);
        if(dataLength < props.text.length) 
            dataLength++;
        else
            clearInterval(timer);
    }, props.interval);

    onUnmounted(() => {
        clearInterval(timer);
    })
    

</script>

<style scoped>

</style>        