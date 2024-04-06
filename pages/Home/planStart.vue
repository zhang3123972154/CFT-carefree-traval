<template>
    <view class="flex-center-vertical container" @click.stop>
        <view class="flex-vertical gap-5">
            <text class="title-1">愉快的旅行即将启航！</text>
            <text class="title-2">您将选择以下哪个方案？</text>
        </view>
        <view class="flex-center-vertical gap-10">
            <planVue index="1" kind="light"/>
            <planVue index="2"/>
            <planVue index="3"/>
        </view>
        <view class="flex-center-horizontal btn-group block-width">
            <u-button :customStyle="{
                padding: '10px 0',
                borderRadius: '15px',
                height: '40px',
                border: 'solid 1px #666666'
            }">取消</u-button>
            <u-button :customStyle="{
                padding: '10px 0',
                borderRadius: '15px',
                height: '40px',
                backgroundColor: '#ffc300',
                color: '#ffffff'
            }">完成</u-button>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import planVue from "@/components/Home/plan.vue";
    // store
// DATA

// FUNC

</script>

<style scoped>

.container {
    gap: 28px;
    width: 305px;
    height: 350px;

    padding: 30px;
    background-color: #ffffff;
    border-radius: 30px;
}

.title-1 {
    font-size: 24px;
}

.title-2 {
    font-size: 12px;
    color: #b39533;
}

.btn-group {
    gap: 20px;
    
    line-height: 18.5px;
    font-size: 20px;
    font-family: SourceHanSansCN;
}

</style>        