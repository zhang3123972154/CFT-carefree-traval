// info 整合 token 的 新版本。
import Request from ".";
const requset = new Request().http;

function get(url: string, header: Object, data: Object) {
    return requset({
        url: url,
        method: "GET",
        header: header,
        data: data
    })
}

function post(url: string, header: Object, data: Object) {
    return requset({
        url: url,
        method: "POST",
        header: header,
        data: data
    })
}

const apiUser = {
    loginByUsername: (data: Object) => get("/user/loginByUsername", {}, data),
    sendEmailCode: (data: Object) => get("/user/sendEmailCode", {}, data),
    register: (data: Object) => post("/user/register", {}, data),
    // info refresh token need token, then header
    refreshToken: (header: Object, data: Object) => get("/user/register", header, data),

    // tag individual
}

// tag store api
import { defineStore } from "pinia";

export default defineStore("api", {
    state: (): {
        loginFlag: boolean,
        access_token: string,
        refresh_token: string,
        token_type: string,
    } => {
        return {
            loginFlag: false,
            access_token: "",
            refresh_token: "",
            token_type: "",
        }
    },
    actions: {
        // info Login && set State
        setLoginState(loginRes) {
            this.access_token = loginRes.access_token;
            this.refresh_token = loginRes.refresh_token;
            this.token_type = loginRes.token_type;
    
            this.loginFlag = true;

            console.info(this.access_token, this.token_type); // test
        },
        login(username: string, password: string) {
            apiUser.loginByUsername({
                username: username,
                password: password
            }).then(res => {
                // console.info("test-login", res);
                this.setLoginState(res.data);
            });
        },
        // 
    }
})