<template>
    <view class="container flex-center-horizontal gap-10">
        <up-input shape="circle" border="none" placeholder="说点什么吧......" 
        fontSize="14"
        :customStyle="{
            padding: '4px',
            margin: '4px',
            backgroundColor: '#f9f9f9',
            border: 'solid 1px #eeeeee80',
        }">
        </up-input>
        <iconText :icon="iconPath.heart" :text="props.heartNum" icon-size="30" text-size="10" :text-style="{
            marginTop: '13px'
        }"/>
        <iconText :icon="iconPath.star" :text="props.starNum" icon-size="30" text-size="10" :text-style="{
            marginTop: '13px'
        }"/>
        <iconText :icon="iconPath.message" :text="props.commentNum" icon-size="30" text-size="10" :text-style="{
            marginTop: '13px'
        }"/>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import iconText from "../Com/iconText.vue";
    // store
    import { useDetailIconPath } from "@/store/dataBase";
    const iconPath = useDetailIconPath();
// DATA
    const props = defineProps({
        heartNum: {
            type: [Number, String],
            default: 520
        },
        starNum: {
            type: [Number, String],
            default: 1314
        },
        commentNum: {
            type: [Number, String],
            default: 100
        },
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.container {
    position: sticky;
    bottom: 0;
    height: 62px;

    background-color: #fff;
}

</style>        