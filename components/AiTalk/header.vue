<template>
    <headerBase>
        <template #prefix>
            <u-icon class="flex-center-both top-icon" size="30" :name="iconPath.AI"></u-icon>
            <text class="ai-name ml-5">旅行助手</text>
        </template>
        <template #suffix>
            <t-btn-icon :icon="iconPath.phone"></t-btn-icon>
            <t-btn-icon :icon="iconPath.information"></t-btn-icon>
        </template>
    </headerBase>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import headerBase from "../Com/headerBase.vue";
    // store
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInforStore = usePhoneInfor();

    import { useAiIconPath } from "@/store/dataBase";
    const iconPath = useAiIconPath();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.top-icon {
    width: 40px;
    height: 40px;
}

.ai-name {
  color: #666666;
  font-size: 14px;
  font-weight: 300;
  line-height: 13px;
}

</style>        