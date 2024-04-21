<template>
    <header-base flod-z-index-top="6">
        <template #midfix>
            <view class="flex-center-horizontal container">
                <template v-for="(item, index) in TitleList" :key="index">
                    <text :class="{
                        'title-chosed': index == kind
                    }"
                    @click="change(index)">
                    {{ item }}
                    </text>
                </template>
            </view>
        </template>
    </header-base>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import headerBase from "../Com/headerBase.vue";
    // store
// DATA
    const props = defineProps({
        
    });
    const emits = defineEmits(["changeIndex"]);

    const TitleList = ref(["对话", "规划"]);
    // flag
    const kind = ref(0);

// FUNC
    const change = (index) => {
        if(index != kind.value) {
            kind.value = index;
            emits("changeIndex", index);
        }
    }

</script>

<style scoped>

.container {
    width: 60%;
    font-size: 18px;
}

.title-chosed {
    font-family: Alimama ShuHeiTi;
    font-size: 20px;

    border-bottom: 3px solid #ffc300;
}

</style>        