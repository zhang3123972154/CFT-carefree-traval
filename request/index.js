const requestUrl = "http://43.139.8.195:8009/";

export default class Request {
    http(param) {
        const url = requestUrl + param.url;
        const method = param.method;
        let header = param.header || {};
        const data = Object.assign(param.data || {});
        // const hideLoading = param.hideLoading || false; // info toast 的显示
        // Run
        if(method == "POST") {
            header = Object.assign({
                'content-type': "application/x-www-from-urlencoded"
            }, param.hedaer)
        } else { // tag GET
            header = Object.assign({
                'content-type': "application/json"
            }, param.header);
        }

        // if(!hideLoading) // info 并不通用...
        //     uni.showLoading({
        //         title: '加载中',
        //         mask: true
        //     });

        return new Promise((resolve, reject) => {
            uni.request({
                url: url,
                method: method,
                hedaer: header,
                data: data,
                success: (res) => {
                    if(res.data.code === 200) { // mark 一般默认会用200吧，不过也看实际了，
                        resolve(res.data);
                        return true;
                    } else {
                        // todo 错误处理
                        console.info("request bug:", res);
                        return false;
                    }
                },
                fail: (err) => {
                    console.info("request bug:", res);
                    return false;
                }
            })
        })
    }
}