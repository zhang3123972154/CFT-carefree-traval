<template>
    <span></span>
</template>

<script setup>
    import { ref, onMounted } from "vue";
    // store
// DATA
    const props = defineProps({
        item: Object
    });
    const emits = defineEmits(['emitBackObject']);

// FUNC
    onMounted(() => {
        emits('emitBackObject', props.item); // info 避免了多次返回值
    })
    

</script>

<style scoped>

</style>        