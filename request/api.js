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
const api = {
    loginByUsername:（data）=> get("/user/loginByUsername", {}, data),
}

export default api;