<template>
    <up-input v-model="content"
        shape="circle"
        border="none"
        :customStyle="{
            padding: '4px',
            margin: '4px 0',
            border: 'solid 1px #000000',
        }">
        <!--mark “none” 取消原本的border，然后用style设置-->

        <template #prefix>
            <view class="flex-center-both ai-container">
                <u-icon name="/static/icon/AI.svg"></u-icon>
            </view>
        </template>

        <template #suffix>
            <view class="flex-horizontal gap-5">
                <t-btn-icon icon="/static/icon/camera.svg"></t-btn-icon>
                <t-btn-icon icon="/static/icon/search.svg"></t-btn-icon>
            </view>
        </template>
    </up-input>
</template>

<script setup>
    import { ref } from "vue";

// DATA
    const props = defineProps({});
    const content = ref("");
    


</script>

<style scoped>

/* search */
.search-container {
    padding: 4px;
    margin: 4px 0;

    background-color: #ffffff;
    border-radius: 25px;
    border: solid 1px #000000;
}

.ai-container {
    width: 40px;    
    height: 40px;

    background-color: #FFC300;
    border-radius: 50%;
}


</style>