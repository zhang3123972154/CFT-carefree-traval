import { defineStore } from "pinia";

const NUM_STEP = 10;

// 1. 地点相关
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
    getters: {
        finish(state) {
            return state.start != "" && state.end != ""; 
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

// 2. 时间
export const useTime = defineStore("timeStore", {
    state: () : {
        dateStart: string,
        timeLength: number
    } => {
        return {
            dateStart: "",
            timeLength: 0
        }
    },
    getters: {
        finish(state) {
            return state.timeLength != 0 && state.dateStart != "";
        }
    }
})

// 3. 预算
export const useBudget = defineStore("budget", {
    state: () : {
        budget: Number
    } => {
        return {
            budget: 1000
        }
    },
    actions: {
        setBudget(money: Number) {
            this.budget = money;
            console.info("budget-change", this.budget);
        }
    }
})

// 4. 多种风格设定
export const usePreference = defineStore("preference", {
    state: () : {
        followList: string[],
        followChosed: string[],

        themeList: string[],
        themeChosed: string[],

        // info "or" 级别
        tightnessList: string[],
        tightnessChosed: string[],

        hotelStallList: string[],
        hotelStallChosed: string[]
    } => {
        return {
            followList: ["亲子", "情侣", "单人", "好友结伴"],
            followChosed: [],
            themeList: ["拍照", "美食", "购物", "自然", "人文", "休闲娱乐", "冒险刺激", "滑雪", "夜景"],
            themeChosed: [],

            tightnessList: ["宽松", "正常", "紧凑"],
            tightnessChosed: [],

            hotelStallList: ["穷游型", "平价型", "舒适型（3星级）", "奢华型（4星级）", "顶奢型（5星级以及以上）"],
            hotelStallChosed: []
        }
    },
    
})