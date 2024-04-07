import { defineStore } from "pinia";

function createUserMessage(content: String) {
    return {
        side: false,
        content: content
    }
}

export default defineStore("aiTalk", {
  state: () => {
    return {
        history: [
            {
                side: false,    // 代表 user
                content: "三月想出门旅行，推荐去哪儿？"
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
                    },
                    {
                        type: "PT-spot", text: "黄鹤楼",
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
                content: "武汉有什么景点推荐？"
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
    sendUserMessage(content: String) {
        const userContent = createUserMessage(content);
        this.history.push(userContent);
    }
  }
})