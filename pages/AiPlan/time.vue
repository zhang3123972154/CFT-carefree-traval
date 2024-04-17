<template>
    <view>
        <view class="flex-vertical content-container mt-20 gap-10">
            <view class="flex-vertical gap-20 mt-10 block">
                <view class="flex-center-horizontal gap-10">
                    <text>游玩时长</text>
                    <chip-editable light 
                        :input-value="timeLength"
                        @text-finish="storeTimeLength"
                    />
                </view>
                <chip-group-choose 
                    title="常见时长"
                    :way-list="TIME_RECOMMEND"
                    :spot-list="[]"
                    :thing-list="[]"
                    @choose="choose"
                />
                <view class="flex-vertical gap-10 mt-10">
                    <text>出发日期</text>
                    <!-- <chip-editable light/> -->
                    <!--bug 文件缺失，无法使用-->
                    <!-- <up-datetime-picker
                        hasInput
                        :show="showTimeChoose"
                        v-model="timeStart"
                        mode="date"
                    ></up-datetime-picker> -->
                    <uni-datetime-picker
                        type="date"
                        :value="timeStart"
                        :start="TODAY"
                        end="2030-6-20"
                        @change="changeDate"
                    />
                </view>
            </view>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    import { getCurrentDateFormatted } from "@/js/date";
    // com
    import chipGroupChoose from "@/components/AiPlan/chipGroupChoose.vue";
    import chipEditable from "@/components/Com/chipEditable.vue";
    // store
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

    const timeLength = ref("");
    const TIME_RECOMMEND = ref(["一天", "三天", "五天", "一周"]);

    const TODAY = getCurrentDateFormatted();
    const timeStart = ref(TODAY);
    console.info(timeStart.value);
    const showTimeChoose = ref(false);

// FUNC
    const storeTimeLength = (value) => {

    }
    const choose = (index) => {
        timeLength.value = TIME_RECOMMEND.value[index];
    }
    const changeDate = (event) => {
        timeStart.value = event;
    }

</script>

<style scoped>

.content-container {
    font-size: 25px;
    font-family: Alimama ShuHeiTi;
}

.other-contaienr {
    font-size: 15px;
}

</style>        