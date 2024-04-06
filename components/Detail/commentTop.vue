<template>
    <view class="flex-center-horizontal gap-50">
        <up-avatar :src="props.avatar" size="35"></up-avatar>
        <up-input shape="circle" border="none" placeholder="让大家听到你的声音" 
        fontSize="14"
        :customStyle="{
            padding: '0px 10px',
            margin: '4px',
            backgroundColor: '#f9f9f9',
            border: 'solid 1px #eeeeee80',
        }">
            <template #suffix>
                <view class="flex-horizontal gap-5">
                    <t-btn-icon :icon="iconPath.microphone"></t-btn-icon>
                    <t-btn-icon :icon="iconPath.camera"></t-btn-icon>
                </view>
            </template>
        </up-input>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
    import { useDetailIconPath } from '@/store/dataBase';
    const iconPath = useDetailIconPath();
// DATA
    const props = defineProps({
        avatar: {
            type: String,
            default: "/static/example/User/avatar-1.svg"
        }
    });
    const emits = defineEmits([]);

    const content = ref(null);

// FUNC

</script>

<style scoped>

</style>        