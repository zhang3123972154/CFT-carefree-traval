<template>
    <view>
        <view class="flex-vertical content-container mt-20 gap-10">
            <view class="grid-2 gap-5 mt-10">
                <text>起点</text>
                <text>终点</text>
                <chip-editable light kind="spot" @text-finish="storeStart"/>
                <chip-editable light kind="spot" @text-finish="storeEnd"/>
            </view>
            <!-- <chip-group scroll/> -->
            <view class="other-contaienr flex-vertical gap-5">
                <chip-group-flex 
                    title="心仪省份"
                    :spot-list="provinceList"
                    :way-list="[]"
                    :thing-list="[]"
                    :lightIndex="spotStore.provinceChosed.length"
                    @reload="reloadProvinceList"
                    @add="addProvince"
                    @delete="deleteProvince"
                />
                <chip-group-flex 
                    title="必去城市"
                    :way-list="[]"
                    :spot-list="cityList"
                    :thing-list="[]"
                    :light-index="spotStore.cityChosed.length"
                    @reload="reloadCityList"
                    @add="addCity"
                    @delete="deleteCity"
                />
            </view>
        </view>
    </view>
</template>

<script setup>
    import { ref, onMounted } from "vue";
    // com
    import chipEditable from "@/components/Com/chipEditable.vue";
    import chipGroupFlex from "@/components/AiPlan/chipGroupFlex.vue";
    // store
    import { useSpot } from "@/store/aiPlanQuestion";
    const spotStore = useSpot();
// DATA
    const props = defineProps({

    });
    const emits = defineEmits([]);

    // DATA
    const provinceList = ref(["湖北", "湖南", "河南", "安徽", "北京", "江西", "重庆", "陕西"]);
    const cityList = ref(["武汉", "长沙", "郑州", "合肥", "北京", "南昌", "重庆", "西安"]);
    
    // const provinceInput = []; // update 关于自定义点位的记录保留想法
    
// FUNC
    onMounted(() => {
        provinceList.value = spotStore.getProvinces();
        cityList.value = spotStore.getCities();
    })

    // tag st && ed
    const storeStart = (value) => {
        spotStore.start = value;
        // console.info(spotStore.start);
    }
    const storeEnd = (value) => {
        spotStore.end = value;
    }

    // tag reload
    const reloadProvinceList = async() => {
        provinceList.value = [...spotStore.provinceChosed, ...spotStore.getProvinces()];
    }
    const reloadCityList = async() => {
        cityList.value = [...spotStore.cityChosed, ...spotStore.getCities()];
    }

    // tag add
    const addProvince = (value) => {
        const type = typeof value;
        // info 根据 string 和 number 来区分
        if(type == "string") {
            spotStore.provinceChosed.push(value);
            provinceList.value = [value, ...provinceList.value];
        }
        else if(type == "number") {
            const index = value;
            const data = provinceList.value.splice(index, 1)[0];
            spotStore.provinceChosed.push(data);
            provinceList.value.splice(spotStore.provinceChosed.length-1, 0, data);
        }
    }
    const addCity = (value) => {
        const type = typeof value;
        // info 根据 string 和 number 来区分
        if(type == "string") {
            spotStore.cityChosed.push(value);
            cityList.value = [value, ...cityList.value];
        }
        else if(type == "number") {
            const index = value;
            const data = cityList.value.splice(index, 1)[0];
            spotStore.cityChosed.push(data);
            cityList.value.splice(spotStore.cityChosed.length-1, 0, data);
        }
    }

    // tag delete
    const deleteProvince = (index) => {
        const data = provinceList.value.splice(index, 1)[0];
        spotStore.provinceChosed.splice(index, 1);
        provinceList.value.splice(spotStore.provinceChosed.length, 0, data);
    }

    const deleteCity = (index) => {
        const data = cityList.value.splice(index, 1)[0];
        spotStore.cityChosed.splice(index, 1);
        cityList.value.splice(spotStore.cityChosed.length, 0, data);
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