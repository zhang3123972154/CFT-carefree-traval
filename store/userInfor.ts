import { defineStore } from "pinia";

// import { apiUser } from "@/request/api";
// const api = apiUser;

interface LoginData {
  username: string,
  password: string
}

// info 用于规划的 单个node
interface Place {  
  id: string;
  name: string;
  start_time: string;
  end_time: string;
  cost: string;
  rating: string;
  type: string;
  photo: string;
  address: string;
}

interface Schedule {
  [key: string]: { // question 索引签名，这样就可以是 Day1, Day2 ...
    [node: string]: Place;  // 同理
  };
}

interface ScheduleHistory {
  create_time: string,
  plan_start_time: string,
  schedule: Schedule,
}

export default defineStore("userInfor", {
  state: (): {
        avatar: string,
        name: string,

        loginFlag: boolean,
        access_token: string,
        refresh_token: string,
        token_type: string,

        scheduleHistory: ScheduleHistory[], // 
  } => {
    return {
        avatar: "/static/example/User/avatar-1.svg",
        name: "XiaoTripUser",

        loginFlag: false,
        access_token: "",
        refresh_token: "",
        token_type: "",

        scheduleHistory: [] as ScheduleHistory[]
    };
  },
  actions: {
      setLoginState(loginRes) {
        this.access_token = loginRes.access_token;
        this.refresh_token = loginRes.refresh_token;
        this.token_type = loginRes.token_type;

        this.loginFlag = true;
      },
      login(username: string, password: string) {
          api.loginByUsername({
            username: username,
            password: password
          }).then(res => {
            console.info("test-login", res);
            this.setLoginState(res.data);
          });
      },
      setScheduleHistory_TEST() { // test
        this.scheduleHistory = [
          {
            create_time: "2024-4-20",
            plan_start_time: "2024-5-20",
            schedule: {
                "Day1": {
                    "node1": {
                        "id": "B0JKP7VC0E", 
                        "name": "小食侯湘(武汉旗舰店)", 
                        "start_time": "10:00", 
                        "end_time": "12:00", 
                        "cost": "50", 
                        "rating": "4.5", 
                        "type": "餐饮服务;中餐厅;湖南菜(湘菜)", 
                        "photo": "http://store.is.autonavi.com/showpic/c9f477d537e8c9ec6d83644850d2e583", 
                        "address": "中南欢乐汇A座文安街与丁字桥路交叉口西120米"
                    }, 
                    "node2": {
                        "id": "B0JGAAFH35", 
                        "name": "食钵记湘菜馆", 
                        "start_time": "12:30",
                        "end_time": "14:00",
                        "cost": "50", 
                        "rating": "4.4", 
                        "type": "餐饮服务;餐饮相关场所;餐饮相关", 
                        "photo": "http://s-pic.oss-cn-beijing.aliyuncs.com/desensitize/images/publish/72def6c5f5ad62055e222abcebda0096.jpg", 
                        "address": "二七街街道二七路131号"
                    }
                }, 
                "Day2": {
                    "node1": {
                        "id": "B0FFHGNCL5", 
                        "name": "肥肥湘菜馆(天后社区店)", 
                        "start_time": "10:00", 
                        "end_time": "12:00", 
                        "cost": "102", 
                        "rating": "4.3", 
                        "type": "餐饮服务;中餐厅;湖南菜(湘菜)", 
                        "photo": "http://store.is.autonavi.com/showpic/51bfcfd8be6b254e8dba8f3d2a3beb84", 
                        "address": "友谊路128附1号附近"
                    }, 
                    "node2": {
                        "id": "B001B1H29G", 
                        "name": "湘菜馆", 
                        "start_time": "12:30", 
                        "end_time": "14:00", 
                        "cost": "40", 
                        "rating": "4.2", 
                        "type": "餐饮服务;中餐厅;湖南菜(湘菜)", 
                        "photo": "http://store.is.autonavi.com/showpic/30759ae81e1bc9f2010f61064187f6d3", 
                        "address": "武汉大学工学部教工40舍20号"
                    }
                }, 
                "Day3": {
                    "node1": {
                        "id": "B0IATALZL3",
                        "name": "湘菜美食烧烤", 
                        "start_time": "10:00", 
                        "end_time": "12:00", 
                        "cost": "40", 
                        "rating": "4.1", 
                        "type": "餐饮服务;中餐厅;湖南菜(湘菜)", 
                        "photo": "http://store.is.autonavi.com/showpic/2194b45140c6f82601a29e0b6c48f5aa", 
                        "address": "卓刀泉街关公社区东北1门旁"
                    }, 
                    "node2": {
                        "id": "B0J2376PAZ", 
                        "name": "食钵记湘菜馆(盘龙城店)", 
                        "start_time": "12:30", 
                        "end_time": "14:00", 
                        "cost": "63", 
                        "rating": "4.2", 
                        "type": "餐饮服务;中餐厅;湖南菜(湘菜)", 
                        "photo": "http://store.is.autonavi.com/showpic/da677952e91e8ac937876f5cdee7897a", 
                        "address": "盘龙大道44号"
                    }
                }
            }
          }
        ]
      }
  }
});