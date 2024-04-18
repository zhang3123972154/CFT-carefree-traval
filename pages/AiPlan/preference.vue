<template>
    <view>
        <view class="flex-vertical content-container mt-20 gap-10">
            <chip-group-flex
                title="旅行伴随"
                :way-list="followList"
                :spot-list="[]"
                :thing-list="[]"

                :light-index="preferStore.followChosed.length"
                @add="addFollow"
                @delete="deleteFollow"
            />
            <chip-group-flex
                title="旅行主题"
                :way-list="themeList"
                :spot-list="[]"
                :thing-list="[]"

                :light-index="preferStore.themeChosed.length"
                @add="addTheme"
                @delete="deleteTheme"
            />
            <chip-group-flex
                title="行程紧凑度" no-reload
                :way-list="tightnessList"
                :spot-list="[]"
                :thing-list="[]"

                :light-index="preferStore.tightnessChosed.length"
                @add="addTightness"
                @delete="deleteTightness"
            />
            <!--update 后续改成不会改变顺序的状态-->
            <chip-group-flex
                title="酒店档位" no-reload close-start
                :way-list="[]"
                :spot-list="hotelStallList"
                :thing-list="[]"

                :light-index="preferStore.hotelStallChosed.length"
                @add="addHotelStall"
                @delete="deleteHotelStall"
            />
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import chipGroupFlex from "@/components/AiPlan/chipGroupFlex.vue";
    import chipGroupChoose from "@/components/AiPlan/chipGroupChoose.vue";

    // store
    import { usePreference } from "@/store/aiPlanQuestion";
    const preferStore = usePreference();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);
    
    const followList = ref(preferStore.followList);
    const themeList = ref(preferStore.themeList);
    const tightnessList = ref(preferStore.tightnessList);
    const hotelStallList = ref(preferStore.hotelStallList);

// FUNC
    // tag add
    const addFollow = (value) => {
        const type = typeof value;
        // info 根据 string 和 number 来区分
        if(type == "string") {
            preferStore.followChosed.push(value);
            followList.value = [value, ...followList.value];
        }
        else if(type == "number") {
            const index = value;
            const data = followList.value.splice(index, 1)[0];
            preferStore.followChosed.push(data);
            followList.value.splice(preferStore.followChosed.length-1, 0, data);
        }
    }

    const addTheme = (value) => {
        const type = typeof value;
        // info 根据 string 和 number 来区分
        if(type == "string") {
            preferStore.themeChosed.push(value);
            themeList.value = [value, ...themeList.value];
        }
        else if(type == "number") {
            const index = value;
            const data = themeList.value.splice(index, 1)[0];
            preferStore.themeChosed.push(data);
            themeList.value.splice(preferStore.themeChosed.length-1, 0, data);
        }
    }

    const addTightness = (value) => {
        const type = typeof value;
        // info 根据 string 和 number 来区分
        if(type == "string") {
            preferStore.tightnessChosed.push(value);
            tightnessList.value = [value, ...tightnessList.value];
        }
        else if(type == "number") {
            const index = value;
            const data = tightnessList.value.splice(index, 1)[0];
            preferStore.tightnessChosed.push(data);
            tightnessList.value.splice(preferStore.tightnessChosed.length-1, 0, data);
        }
    }

    const addHotelStall = (value) => {
        const type = typeof value;
        // info 根据 string 和 number 来区分
        if(type == "string") {
            preferStore.hotelStallChosed.push(value);
            hotelStallList.value = [value, ...hotelStallList.value];
        }
        else if(type == "number") {
            const index = value;
            const data = hotelStallList.value.splice(index, 1)[0];
            preferStore.hotelStallChosed.push(data);
            hotelStallList.value.splice(preferStore.hotelStallChosed.length-1, 0, data);
        }
    }

    // tag delete
    const deleteFollow = (index) => {
        const data = followList.value.splice(index, 1)[0];
        preferStore.followChosed.splice(index, 1);
        followList.value.splice(preferStore.followChosed.length, 0, data);
    }

    const deleteTheme = (index) => {
        const data = themeList.value.splice(index, 1)[0];
        preferStore.themeChosed.splice(index, 1);
        themeList.value.splice(preferStore.themeChosed.length, 0, data);
    }

    const deleteTightness = (index) => {
        const data = tightnessList.value.splice(index, 1)[0];
        preferStore.tightnessChosed.splice(index, 1);
        tightnessList.value.splice(preferStore.tightnessChosed.length, 0, data);
    }

    const deleteHotelStall = (index) => {
        const data = hotelStallList.value.splice(index, 1)[0];
        preferStore.hotelStallChosed.splice(index, 1);
        hotelStallList.value.splice(preferStore.hotelStallChosed.length, 0, data);
    }



</script>

<style scoped>

</style>        