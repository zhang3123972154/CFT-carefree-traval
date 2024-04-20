import Request from ".";
const request = new Request().http;

function get(url, header, data) {
    return request({
        url: url,
        method: "GET",
        header: header,
        data: data
    })
}

function post(url, header, data) {
    return request({
        url: url,
        method: "POST",
        header: header,
        data: data
    })
}

// tag 外部结构
export const apiUser = {
    loginByUsername: (data) => get("/user/loginByUsername", {}, data),
    sendEmailCode: (data) => get("/user/sendEmailCode", {}, data),
    register: (data) => post("/user/register", {}, data),
    refreshToken: (data) => get("/usr/register", {}, data),

    // tag individual
    
};

export const apiAI = {
    getAIHistory: (date) => get("/ai/history", {}, data),
    sendImageOCR: (data) => post("/ai/ocr/general", {}, data),
    sendMessage: (data) => post("/ai/send", {}, data)
}
