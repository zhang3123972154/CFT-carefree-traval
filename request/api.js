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

function apiTest() {
    console.info("api-test 123");
}

// tag 外部结构
const api = {
    apiTest: () => apiTest(),
    loginByUsername: (data) => get("/user/loginByUsername", {}, data),
    sendEmailCode: (data) => get("/user/sendEmailCode", {}, data),

};

export default api;