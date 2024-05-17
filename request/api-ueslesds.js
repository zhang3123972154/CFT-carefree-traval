import Request from ".";
const request = new Request().http;

// token
// import userInfor from "../store/userInfor.ts"; // bug 加载顺序不同，无法直接调用
// const userStore = userInfor();

function get(url, header, data) {
    return request({
        url: url,
        method: "GET",
        header: header,
        data: data
    })
}

function post(url, header, data) {
    let headerToken = Object.assign({
        'Authorization': "Bearer " + userStore.access_token
    }, header);
    return request({
        url: url,
        method: "POST",
        header: headerToken,
        data: data
    })
}

// tag 外部结构
export const apiUser = {
    loginByUsername: (data) => get("/user/loginByUsername", {}, data),
    sendEmailCode: (data) => get("/user/sendEmailCode", {}, data),
    register: (data) => post("/user/register", {}, data),
    refreshToken: (data) => get("/user/register", {}, data),

    // tag individual
    
};

export const apiAI = {
    getAIHistory: (date) => get("/ai/history", {}, data),
    sendImageOCR: (data) => post("/ai/ocr/general", {}, data),
    sendMessage: (data) => post("/ai/send", {}, data),
    uploadImg: (data) => post("/ai/upload", {
        'content-type': 'multipart/form-data'
    }, data),
}
