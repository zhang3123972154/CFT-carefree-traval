<template>
    <view class="flex-top-horizontal gap-10 relative">
        <view class="flex-vertical container-main-title">
            <template v-for="(item, index) in individualStore.mainTitle" :key="index">
                <text class="main-title no-wrap"
                    :class="{
                        'main-title-chosed': index == mainTitleIndex
                    }"
                    @click="changeMainTitleIndex(index)">
                    {{ item }}
                </text>
            </template>
        </view>
        <view class="sub-title-container">
            <chip-group-flex-group
                :chipGroupList="subTitleListData"
            />
        </view>
    </view>
</template>

<script setup>
    import { ref, onMounted } from "vue";
    // com
    import chipGroupFlexGroup from "@/components/AiPlan/chipGroupFlexGroup.vue";
    // store
    import { useIndividual } from "@/store/aiPlanQuestion";
    const individualStore = useIndividual();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

    const mainTitleIndex = ref(0);
    const subTitleListData = ref([]);

// FUNC
    onMounted(() => {
        subTitleListData.value = individualStore.getSubTitleListData(0);
    })

    const changeMainTitleIndex = (value) => {
        mainTitleIndex.value = value;
        subTitleListData.value = individualStore.getSubTitleListData(value);
    }

</script>

<style scoped>

.container-main-title {
    position: sticky;
    top: 20px;

    font-family: Alimama ShuHeiTi;
    font-size: 18px;

    width: 30%;
}

.main-title {
    overflow: scroll;

    padding: 5px 2px;
}

.main-title-chosed {
    background-color: #ffc300;
    border-radius: 5px;
}

.sub-title-container {
    width: 65%;
}

</style>        