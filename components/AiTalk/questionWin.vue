<template>
    <view class="flex-center-both">
        <floatWin>
            <view class="flex-vertical">
                <view class="flex-vertical gap-10">
                    <question-win-item :title="title[0]">
                        <view class="flex-horizontal gap-20">
                            <text>起点</text>
                            <t-chip kind="spot" :text="spotStore.start" light/>
                            <text>终点</text>
                            <t-chip kind="spot" :text="spotStore.end" light/>
                        </view>
                    </question-win-item>
                    <question-win-item :title="title[1]">
                        <view class="flex-horizontal gap-10">
                            <text>游玩时长</text>
                            <t-chip kind="way" :text="timeStore.timeLength" light/>
                        </view>
                        <view class="flex-horizontal gap-10">
                            <text>出发时间</text>
                            <t-chip kind="way" :text="timeStore.dateStart" light/>
                        </view>
                    </question-win-item>
                    <question-win-item :title="title[2]">
                        <view class="flex-horizontal gap-10">
                            <text>预算</text>
                            <t-chip kind="way" :text="'￥' + budgetStore.budget.toString()" light/>
                        </view>
                    </question-win-item>
                    <question-win-item :title="title[3]">
                        <chip-group-choose
                            title="旅行伴随" light-hold openStart
                            :way-list="preferenceStore.followChosed"
                            :spot-list="[]" :thing-list="[]"
                        />
                        <chip-group-choose
                            title="旅行主题" light-hold openStart
                            :way-list="preferenceStore.themeChosed"
                            :spot-list="[]" :thing-list="[]"
                        />
                        <chip-group-choose
                            title="行程紧凑度" light-hold openStart
                            :way-list="preferenceStore.tightnessChosedd"
                            :spot-list="[]" :thing-list="[]"
                        />
                        <chip-group-choose
                            title="酒店档位" light-hold openStart
                            :way-list="preferenceStore.hotelStallChosed"
                            :spot-list="[]" :thing-list="[]"
                        />
                    </question-win-item>
                    <question-win-item :title="title[4]">
                        
                    </question-win-item>
                </view>
            </view>
        </floatWin>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // com
    import floatWin from "@/components/Com/floatWin.vue";
    import questionWinItem from "./questionWinItem.vue";

    import chipGroupChoose from "@/components/AiPlan/chipGroupChoose.vue";
    // store
    import { useAiIconPath, useDetailIconPath } from "@/store/dataBase.ts";
    const iconPath = useAiIconPath();
    const iconInput = useDetailIconPath();
    import { useTitle, useSpot, useTime, useBudget, usePreference, useIndividual } from "@/store/aiPlanQuestion";
    const titleStore = useTitle();
    const spotStore = useSpot();
    const timeStore = useTime();
    const budgetStore = useBudget();
    const preferenceStore = usePreference();
    // todo...

// DATA
    const emits = defineEmits(['close']);
    const title = ref(titleStore.title);

// FUNC
    const inputNULL = computed(() => {
        return content.value === "";
    });

    const clearInput = () => {
        content.value = "";
    }

    const gotoBack = () => {
        uni.navigateBack({ delta: 1 });
    }

</script>

<style scoped>

/* .container {
    height: 80;
    overflow: scroll;
} */

.talk {
    margin-top: 50px;

    font-size: 18px;
    line-height: 16.5px;
    font-weight: 300;
}

.input-container {
    margin-top: 15px;

    position: relative;

    width: 100%;
    max-width: 500px;
}

.input-func-container {
    position: absolute;
    top: 15px;
    right: 20px;

    z-index: 2000;
}

.btn-base {
    padding: 12px 0;
    height: 40px;
    width: 120px;

    font-size: 18px;
    line-height: 16.5px;
}

</style>        