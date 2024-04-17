import { defineStore } from "pinia";

const NUM_STEP = 10;

export const useSpot = defineStore("spotStore", {
    state: (): {
        provinceIndex: Number,
        provinceList: string[],
        provinceChosed: string[],   // info 选中 && 自添加的存储
        cityIndex: number,
        cityList: string[],
        cityChosed: string[],

        start: string,
        end: string
    } => {
        return {
            provinceIndex: 0,
            provinceList: [
                "湖北", "湖南", "河南", "安徽", "北京", "江西", "重庆", "陕西",
                "山东", "江苏", "浙江", "广东", "福建", "天津", "上海", "四川",
                "云南", "贵州", "广西", "西藏", "新疆", "青海", "甘肃", "宁夏",
                "内蒙古", "黑龙江", "吉林", "辽宁", "山西", "河北"
            ],
            provinceChosed: [],
            cityIndex: 0,
            cityList: [
                "武汉", "长沙", "郑州", "合肥", "北京", "南昌", "重庆", "西安",
                "济南", "南京", "杭州", "广州", "福州", "天津", "上海", "成都",
                "昆明", "贵阳", "南宁", "拉萨", "乌鲁木齐", "西宁", "兰州", "银川",
                "呼和浩特", "哈尔滨", "长春", "沈阳", "太原", "石家庄"
            ],
            cityChosed: [],

            start: "",
            end: "",
        }
    },
    actions: {
        // todo 更加智能的切换方式，把没有选中的换出来，选中的前置。
        getProvinces(): string[] {
            // 确保index是有效的，且在省份列表的范围内
            if (this.provinceIndex < 0 || this.provinceIndex >= this.provinceList.length)
                this.provinceIndex = 0; // 从头开始
            // 从index开始截取最多10个省份
            let newIndex = this.provinceIndex;
            let resArray = [];
            while(resArray.length < NUM_STEP && newIndex < this.provinceList.length) {
                let province = this.provinceList[newIndex];
                if(!this.provinceChosed.includes(province))
                    resArray.push(province);
                newIndex++;
            }
            // info update information
            this.provinceIndex = newIndex;
            return resArray;
        },
        getCities(): string[] {
            // 确保index是有效的，且在城市列表的范围内
            if (this.cityIndex < 0 || this.cityIndex >= this.cityList.length)
                this.cityIndex = 0;
            // main
            let newIndex = this.cityIndex;
            let resArray = [];
            while(resArray.length < NUM_STEP && newIndex < this.cityList.length) {
                let city = this.cityList[newIndex];
                if(!this.provinceChosed.includes(city))
                    resArray.push(city);
                newIndex++;
            }
            // info update information
            this.cityIndex = newIndex;
            return resArray;
        }
    }
})