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

export const aiTalk = defineStore("aiTalkContent", {
  state: () => {
    return {
        avatar: "/static/icon/AiTalk/AI.svg",
        name: "旅行助手",
        history: [
            {
                side: false,    // 代表 user
                text: "三月想出门旅行，推荐去哪儿？"
                // todo time: "2023-12-10-XXX"
            },
            {
                side: true,
                content: [
                    { type: "text", text: "三月是" },
                    { type: "thing", text: "樱花" },
                    { type: "text", text: "盛开的季节，三月份去" },
                    { type: "spot", text: "武汉"},
                    { type: "text", text: "旅游一定是不错之选。"},
                    { type: "endl" },
                    {
                        type: "PT-spot", text: "东湖樱花园",
                        grade: 4.9, location: "武昌区鲁磨路665号", 
                        price: 55, imgPath: "/static/example/background/spot-1.png"
                        // ... 
                    },
                    {
                        type: "PT-thing", text: "黄鹤楼",
                        grade: 4.8, location: "武昌区蛇山西山坡特2号", 
                        price: 70, imgPath: "/static/example/background/spot-2.png"
                    },
                    {
                        type: "PT-spot", text: "武汉大学",
                        grade: 4.9, location: "武昌区八一路299号", 
                        price: 0, imgPath: "/static/example/background/spot-3.png"
                    }
                ]
            },
            {
                sied: false,
                text: "武汉有什么景点推荐？"
            },
            {
                side: true,
                content: [
                    { type: "text", text: "三月是" },
                    { type: "thing", text: "樱花" },
                    { type: "text", text: "盛开的季节，三月份去旅游一定是不错之选。" },
                    { type: "endl" },
                    { type: "spot", text: "东湖樱花园" },
                    { type: "text", text: "是武汉市规模最大、品种最多的樱花园之一。" },
                    { type: "endl" },
                    { type: "spot", text: "武汉大学" },
                    { type: "text", text: "是武汉市最有影响力的赏樱景区。" },
                    { type: "endl" },
                    { type: "spot", text: "黄鹤楼" },
                    { type: "text", text: "公园是武汉著名的历史文化风景区之一。" },
                ]
            },
        ],
        loading: false, // info 访问 api 时不允许再次发送。
        loadingClear: false
    }
  },
  getters: {
    lastIndex(state) {
        return state.history.length-1;
    }
  },
  actions: {
    async sendUserMessage(text: String, images: String[]) {
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
                // console.info("New message", event.data);
                this.addAiMessage(event.data);
            }
            eventSourse.onerror = (event) => {
                // if(eventSourse.readyState === EventSource.CLOSED) {
                //     eventSourse.close();
                //     console.log('The connection has been closed by the server.');
                // } else {
                //     console.error('SSE error:', event);
                // }
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