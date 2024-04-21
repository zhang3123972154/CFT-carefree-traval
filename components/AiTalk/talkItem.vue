<template>
    <view class="flex-center-horizontal">
        <view class="flex-horizontal gap-20">
            <up-image :src="props.avatar" width="40" height="40" shape="circle"/>
            <text>{{ props.name }}</text>
        </view>
        <view class="divider"></view>
        <u-icon :name="openFlag ? iconPath.down : iconPath.up" 
            @click="openFlag = !openFlag"/>
    </view>
    <view v-if="openFlag" class="flex-vertical gap-5 dialog-container">
        <dialog-item/>
        <dialog-item/>
        <dialog-item/>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import dialogItem from "./dialogItem.vue";
    // store
    import { useArrowsIconPath } from "@/store/dataBase";
    const iconPath = useArrowsIconPath();
// DATA
    const props = defineProps({
        name: {
            type: String,
            default: "默认AI"
        },
        avatar: {
            type: String,
            default: "/static/example/User/avatar-3.svg"
        }
    });
    const emits = defineEmits([]);

    // flag
    const openFlag = ref(false);

// FUNC

</script>

<style scoped>

.dialog-container {
    margin-left: 50px;
}

</style>        