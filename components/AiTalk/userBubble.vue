<template>
    <view class="flex-top-horizontal container gap-10">
        <view class="talk-container flex-vertical">
            <text class="wrap">{{ props.text }}</text>
            <t-image-group :images="props.images"/>
        </view>
        <u-icon :name="userInfor.avatar" size="30"></u-icon>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
    import useUserStore from "@/store/userInfor";
    const userInfor = useUserStore();
// DATA
    const props = defineProps({
        text: {
            type: String,
            default: "TEST"
        },
        images: {
            type: Array,
            default: () => []
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container {
    justify-content: flex-end;
}

.talk-container {
    max-width: calc(100% - 80px);

    padding: 10px 12px;
    background-color: #ffc300;
    border-radius: 15px 5px 15px 15px;

    font-size: 16px;
    font-family: SourceHanSansCN;
    font-weight: 300;
    color: #ffffff;
}

</style>        