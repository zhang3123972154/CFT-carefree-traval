import { defineStore } from "pinia";

import { pathToBase64 } from "@/js/image";

import { useApiAI } from "../request/api";
const api = useApiAI;

function createUserMessage(text: String, images: String[]) {
    return {
        side: false,
        text: text,
        images: images
    }
}

function createAiMessage(text: String) { // test 简单版
    return {
        side: true,
        content: [
            { type: "text", text: text}
        ]
    }
}

export default defineStore("aiTalk", {
  state: () => {
    return {
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
                    // { type: "spot", text: "东湖樱花园" },
                    // { type: "text", text: "是武汉市规模最大、品种最多的樱花园之一。" },
                    // { type: "endl" },
                    // { type: "spot", text: "武汉大学" },
                    // { type: "text", text: "是武汉市最有影响力的赏樱景区。" },
                    // { type: "endl" },
                    // { type: "spot", text: "黄鹤楼" },
                    // { type: "text", text: "公园是武汉著名的历史文化风景区之一。" },
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
        ]
    }
  },
  getters: {

  },
  actions: {
    async sendUserMessage(text: String, images: String[]) {
        const userContent = createUserMessage(text, images);
        this.history.push(userContent);

        if(images != null) {
            const imagePath = images[0];
            pathToBase64(imagePath)
                    .then(base64 => {
                        console.info(base64);
                        const rse = api.sendImageOCR(base64)
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
        else {

        }
    },
    loadAiMessage(content: String) {
        const AiContent = createAiMessage(content);
        this.history.push(AiContent);
    }
  }
})