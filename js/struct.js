// info 模仿 C++ struct 的结构

export function pathPoint(type, text, things) {
    return {type, text, things};
}

export function pathSpot(text, things, imgPath, grade, location, timeStart, timeEnd) {
    return {
        type: "spot", text, things, imgPath, grade, location, timeStart, timeEnd
    };
}

export function pathTraffic(text, timeStart, timeEnd) {
    return {
        type: "traffic",
        timeStart, timeEnd
    }
}