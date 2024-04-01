// info 模仿 C++ struct 的结构

export function pathPoint(kind, text, things) {
    // console.info({kind, text, things});
    return {kind, text, things};
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