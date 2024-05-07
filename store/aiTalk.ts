import { defineStore } from "pinia";

import { pathToBase64 } from "@/js/image";

import { apiAI } from "../request/api";
const api = apiAI;

function createUserMessage(text: String, images: String[]) {
    return {
        side: false,
        text: text,
        images: images
    }
}

function createAiMessage(type: string = "loading", text: string = "...") { // test 简单版
    return {
        side: true,
        content: [
            { 
                type, 
                text 
            }
        ]
    }
}

function createAiMessageItem(type: string, text: string) { 
    return {
        type, 
        text 
    }
}

// mark core talk history load
export const aiTalk = defineStore("aiTalkContent", {
  state: () => {
    return {
        // avatar: "/static/icon/AiTalk/AI.svg",    // info 原版本
        // name: "旅行助手",
        avatar: "/static/example/AI/AI.svg",
        name: "旅行助手—萧萧",
        history: [
            {   // info 可选作为回复的 个性标签
                side: true,
                content: [
                    { type: "text", text: "小主人！萧萧在这里为您推荐了一些热门旅游城市，有您喜欢的吗？" },
                    { 
                        type: "CG-spot", 
                        list: [
                            "武汉", "长沙", "郑州", "合肥", "北京", "南昌", "重庆", "西安",
                            "济南", "南京", "杭州", "广州", "福州", "天津", "上海", "成都",
                            "昆明", "贵阳", "南宁", "拉萨", "乌鲁木齐", "西宁", "兰州", "银川",
                            "呼和浩特", "哈尔滨", "长春", "沈阳", "太原", "石家庄"
                        ],
                        reload: true,
                        reply: true,
                        light: false
                    }
                ]
            },
            {
                side: false,
                text: "武汉"
            },
            {
                side: true,
                content: [
                    { type: "spot", text: "武汉" },
                    { type: "text", text: "素来有“江城”之称，确实是旅行的不错之选！！！" },
                ]
            },
            {   // info 样图展示
                side: true,
                content: [
                    {
                        type: "IB-spot", text: "黄鹤楼",
                        introduce: "作为武汉的地标，黄鹤楼不仅是历史悠久的古建筑，还能远眺长江美景。",
                        grade: 4.8, location: "武昌区蛇山西山坡特2号", 
                        price: 70, imgPath: "/static/example/spot/spot-1.png"
                    },
                    {
                        type: "IB-spot", text: "东湖风景区",
                        introduce: "这里有美丽的自然风光，适合散步和骑行。",
                        grade: 4.9, location: "武昌区鲁磨路665号", 
                        price: 55, imgPath: "/static/example/spot/spot-2.png"
                        // ... 
                    },
                    {
                        type: "IB-spot", text: "武汉大学",
                        introduce: "春天的武汉大学樱花盛开，是拍照的好地方。",
                        grade: 4.9, location: "武昌区八一路299号", 
                        price: 0, imgPath: "/static/example/spot/spot-3.png"
                    },
                    {
                        type: "IB-spot", text: "户部巷",
                        introduce: "这里聚集了各种地道的小吃，可以体验地道的武汉生活。",
                        grade: 4.8, location: "武昌区八一路299号", 
                        price: 0, imgPath: "/static/example/spot/spot-4.png"
                    },
                    {
                        type: "IB-spot", text: "江汉路步行街",
                        introduce: "这里有很多购物和娱乐场所，是体验武汉现代都市生活的好地方。",
                        grade: 4.7, location: "武昌区八一路299号", 
                        price: 0, imgPath: "/static/example/spot/spot-5.png"
                    }
                ]
            },
            {   // info 可选问答模板
                side: true,
                content: [
                    { type: "text", text: "小主人！很多游客去武汉旅行会问这些问题哦！" },
                    {   
                        type: "OP", // info Optional problem
                        list: [
                            "武汉有哪些适合拍照的景点？",
                            "武汉有哪些著名的夜市？",
                            "武汉的住宿推荐有哪些？",
                            "武汉的购物场所有哪些？"
                        ]
                     }
                ]
            },
            {   // info 规划 模板
                side: true,
                content: [
                    { type: "text", text: "小主人！萧萧已经为您制定好了基础规划，点击右侧悬浮窗可以查看规划细节。" },
                    { type: "endl" },
                    { type: "text", text: "第一天：" },
                    { 
                        type: "CG-spot", 
                        list: ["黄鹤楼", "户部巷"],
                        reload: false,
                        reply: false,
                        light: true
                    },
                    { type: "text", text: "第二天：" },
                    { 
                        type: "CG-spot", 
                        list: ["宝通寺", "街道口", "街道口", "武昌江滩"],
                        reload: false,
                        reply: false,
                        light: true
                    },
                    { type: "text", text: "第三天：" },
                    { 
                        type: "CG-spot", 
                        list: ["古德寺", "江汉路", "汉口江滩"],
                        reload: false,
                        reply: false,
                        light: true
                    },
                    { type: "text", text: "第四天：" },
                    { 
                        type: "CG-spot", 
                        list: ["湖北省博物馆", "楚河汉街", "昙华林"],
                        reload: false,
                        reply: false,
                        light: true
                    },
                ]
            }
            // {
            //     side: false,    // 代表 user
            //     text: "三月想出门旅行，推荐去哪儿？"
            //     // todo time: "2023-12-10-XXX"
            // },
            // {
            //     side: true,
            //     content: [
            //         { type: "text", text: "三月是" },
            //         { type: "thing", text: "樱花" },
            //         { type: "text", text: "盛开的季节，三月份去" },
            //         { type: "spot", text: "武汉"},
            //         { type: "text", text: "旅游一定是不错之选。"},
            //         { type: "endl" },
            //         {
            //             type: "PT-spot", text: "东湖樱花园",
            //             grade: 4.9, location: "武昌区鲁磨路665号", 
            //             price: 55, imgPath: "/static/example/background/spot-1.png"
            //             // ... 
            //         },
            //         {
            //             type: "PT-thing", text: "黄鹤楼",
            //             grade: 4.8, location: "武昌区蛇山西山坡特2号", 
            //             price: 70, imgPath: "/static/example/background/spot-2.png"
            //         },
            //         {
            //             type: "PT-spot", text: "武汉大学",
            //             grade: 4.9, location: "武昌区八一路299号", 
            //             price: 0, imgPath: "/static/example/background/spot-3.png"
            //         }
            //     ]
            // },
            // {
            //     sied: false,
            //     text: "武汉有什么景点推荐？"
            // },
            // {
            //     side: true,
            //     content: [
            //         { type: "text", text: "三月是" },
            //         { type: "thing", text: "樱花" },
            //         { type: "text", text: "盛开的季节，三月份去旅游一定是不错之选。" },
            //         { type: "endl" },
            //         { type: "spot", text: "东湖樱花园" },
            //         { type: "text", text: "是武汉市规模最大、品种最多的樱花园之一。" },
            //         { type: "endl" },
            //         { type: "spot", text: "武汉大学" },
            //         { type: "text", text: "是武汉市最有影响力的赏樱景区。" },
            //         { type: "endl" },
            //         { type: "spot", text: "黄鹤楼" },
            //         { type: "text", text: "公园是武汉著名的历史文化风景区之一。" },
            //     ]
            // },
        ],
        loading: false, // info 访问 api 时不允许再次发送。
        loadingClear: false,
        plan: [
            {   // info day 1
                spot: "武汉", 
                list: [
                    [
                        { type: "traffic", text: "出发" }
                    ],
                    [
                        { type: "spot", text: "" }  // info ""  表示待定，用户可以自行填写
                    ],
                    [
                        { type: "spot", text: "黄鹤楼" }
                    ],
                    [
                        { type: "spot", text: "" }
                    ],
                    [
                        { type: "spot", text: "户部巷" },
                        { type: "spot", text: "宜必思酒店" }
                    ]
                ],
                length: 6 // info 统计单个结点数量
            },
            { 
                spot: "武汉", 
                list: [
                    [
                        { type: "spot", text: "宝通寺" }
                    ],
                    [
                        { type: "spot", text: "街道口" }
                    ],
                    [
                        { type: "spot", text: "武汉大学" }
                    ],
                    [
                        { type: "spot", text: "街道口" }
                    ],
                    [
                        { type: "spot", text: "武昌江滩" },
                        // { type: "spot", text: "宜必思酒店" }
                    ]
                ],
                length: 5
            }
        ]
    }
  },
  getters: {
    lastIndex(state) {
        return state.history.length-1;
    }
  },
  actions: {
    async sendUserMessage(text: String, images: String[]) {
        if(this.loading)    // info 如果AI正在回复，那么无法发送消息。
            return;
        const userContent = createUserMessage(text, images);
        this.history.push(userContent);
        
        const aiContent = createAiMessage();
        this.history.push(aiContent);
        this.loadingClear = false;
        this.loading = true;
        if(images != null) { // info image
            const imagePath = images[0];
            pathToBase64(imagePath)
                    .then(base64 => {
                        console.info(base64);
                        const rse = api.sendImageOCR({
                            image: base64
                        })
                            .then(res => {
                                console.info("api-back", res);
                                this.loadAiMessage(res);
                            })
                            .catch(error => {
                                console.info(error);
                            });
                    })
                    .catch(error => {
                        console.info("base64", error);
                    })
        }
        else { // info text
            // todo 访问后端接口获取 需要的
            const eventSourse = new EventSource('http://localhost:8080/test/sse');
            eventSourse.onmessage = (event) => {
                if(!this.loading)   // 打断 直接关闭
                    eventSourse.close();
                // console.info("New message", event);
                this.addAiMessage(event.data);
            }
            eventSourse.onerror = (event) => {
                console.error(event);
                eventSourse.close();    // test 直接关闭
                this.loading = false;
            };
        }
    },
    addAiMessage(content: String) { // test
        var obj = JSON.parse(content);
        const aiMessage = obj;
        const index = this.history.length - 1;
        if(this.loadingClear)
            this.history[index]["content"].push(aiMessage);
        else {
            this.history[index]["content"] = [
                aiMessage
            ];
            this.loadingClear = true;
        }
    },
    stopAiTalk() { // 中断当前 sse 连接
        if(this.loading) 
            this.loading = false;
    },
    loadAnohterTalk(avatar: string, name: string) {
        this.avatar = avatar;
        this.name = name;
    }
  }
})


// tag Ai Talk List

interface subTalk {
    title: string,
    time: string
}

interface AiHead {
    avatar: string,
    name: string,
    childTalk: subTalk[]
}

function creatAiHead(avatar: string, name: string) {
    return {
        avatar, name
    }
}


export const aiTalkList = defineStore("aiTalkList", {
    state: () : {
        aiTalkList: AiHead[]
    } => {
        return {
            aiTalkList: [
                { 
                    avatar: "/static/example/User/avatar-1.svg",
                    name: "小A",
                    childTalk: [
                        { title: "子问题-1", time: "2024-4-20" },
                        { title: "子问题-2", time: "2024-4-18" },
                        { title: "子问题-3", time: "2024-3-20" }
                    ]
                },
                { 
                    avatar: "/static/example/User/avatar-2.svg",
                    name: "小B",
                    childTalk: [
                        { title: "子问题-1", time: "2024-4-20" },
                        { title: "子问题-2", time: "2024-4-18" },
                        { title: "子问题-3", time: "2024-3-20" }
                    ]
                },
                { 
                    avatar: "/static/example/User/avatar-3.svg",
                    name: "小C",
                    childTalk: [
                        { title: "子问题-1", time: "2024-4-20" },
                        { title: "子问题-2", time: "2024-4-18" },
                        { title: "子问题-3", time: "2024-3-20" }
                    ]
                }
            ]
        }
    },
    actions: {
        // todo 一个根据 time 的重新排序
        addAiHelper(avatar: string, name: string, time: string) {
            const newTalk = creatAiHead(avatar, name, time);
            this.aiTalkHistory.unshift(newTalk);
        }
    }
})