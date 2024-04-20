import { defineStore } from "pinia";

import { apiUser } from "@/request/api";
const api = apiUser;

interface LoginData {
  username: string,
  password: string
}

export default defineStore("userInfor", {
  state: (): {
        avatar: string,
        name: string,

        loginFlag: boolean,
        access_token: string,
        refresh_token: string,
        token_type: string,
  } => {
    return {
        avatar: "/static/example/User/avatar-1.svg",
        name: "XiaoTripUser",

        loginFlag: false,
        access_token: "",
        refresh_token: "",
        token_type: "",
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
      }
  }
});