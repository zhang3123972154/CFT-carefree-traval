<template>
    <up-input v-model="content"
        shape="circle"
        border="none"
        :class="{
            'thin': props.thin
        }"
        :style="{
            '--width': getWidth
        }"
        :customStyle="{
            padding: '4px',
            margin: '4px 0',
            border: 'solid 1px #000000',
        }">
        <!--mark “none” 取消原本的border，然后用style设置-->

        <template #prefix>
            <view class="flex-center-both ai-container" :style="{
                '--icon-size': getAiSize
            }"
                @click="gotoSearch">
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
    import { ref, computed } from "vue";
    // store
    import usePhoneInfor from "@/store/phoneInfor";
    const phoneInforStore = usePhoneInfor();
// DATA
    const props = defineProps({
        thin: {
            type: Boolean,
            default: false
        },
        block: {
            type: Boolean,
            default: false
        }
    });
    const content = ref("");

// FUNC
    // css
    const getAiSize = computed(() => {
        return props.thin ? "30px" : "40px";
    })
    const getWidth = computed(() => {
        return (props.block ? (phoneInforStore.phoneWidth - 10) : (phoneInforStore.phoneWidth - 50)).toString() + 'px';
    })
    //
    const gotoSearch = () => {
        uni.navigateTo({ url: '/pages/Search/SearchView' })
    }

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
    width: var(--icon-size);    
    height: var(--icon-size);

    background-color: #FFC300;
    border-radius: 50%;
}

.thin {
    height: 40px;
    width: var(--width);
}

</style>