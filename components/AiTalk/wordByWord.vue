<template>
    <span :class="props.classCustom">
        <span>{{ dataShow }}</span>
        <span class="fade-word">{{ fadeWord }}</span>
    </span>
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
        },
        classCustom: {
            type: String,
            default: "text"
        }
    });
    const emits = defineEmits([]);

    const dataShow = ref("");
    const fadeWord = ref("");
    let dataLength = 0;
// FUNC
    const timer = setInterval(() => {
        dataShow.value = props.text.substr(0, dataLength);
        fadeWord.value = props.text[dataLength];
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

.fade-word {
    color: rgba(204, 204, 204);
}

.text {
    font-weight: 300;
}

.way {
    color: #959cf0;
}

.spot {
    color: #a795f0;
}

.thing {
    color: #c895f0;
}

</style>        