<template>
    <view>
        <tabberBase>
            <template #prefix>
                <t-btn-icon :icon="iconPath.voice"></t-btn-icon>
            </template>
            <template #midfix>
                <up-input shape="circle" border="none"
                    fontSize="14"
                    :customStyle="{
                        padding: '4px',
                        margin: '4px',
                        backgroundColor: '#f9f9f9',
                        border: 'solid 1px #eeeeee80',
                    }">
                </up-input>
            </template>
            <template #suffix>
                <t-btn-icon :icon="iconPath.emoji"></t-btn-icon>
                <t-btn-icon :icon="iconPath.add"></t-btn-icon>
            </template>
        </tabberBase>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import tabberBase from "../Com/tabberBase.vue";
    // store
    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

</style>        