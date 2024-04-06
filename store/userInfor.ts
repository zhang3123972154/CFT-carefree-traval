import { defineStore } from "pinia";

export default defineStore("userInfor", {
  state: (): {
        avatar: string,
        name: string,
  } => {
    return {
        avatar: "/static/example/User/avatar-1.svg",
        name: "XiaoTripUser"
    };
  },
});