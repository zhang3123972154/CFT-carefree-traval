import { defineStore } from "pinia";

// 基本icon文件夹的位置
const iconDirPath = "/static/icon/";

// tag 底部导航栏 - iconPath
interface iconStatus {
    unselected: string;
    selected: string;
}
export const useIconTabbarPath = defineStore("iconTabbar", {
    state: (): {
        basePath: string;
        path: iconStatus[];
        pathMid: string;
    } => {
        return {
          basePath: "/static/icon/",
          path: [
            { unselected: "home.svg", selected: "homeSelected.svg" },
            { unselected: "punch.svg", selected: "punchSelected.svg" },
            { unselected: "message.svg", selected: "messageSelected.svg" },
            { unselected: "person.svg", selected: "personSelected.svg" },
          ],
          pathMid: "/static/icon/add.svg",
        };
    },
    actions: {
        getFullPath(): iconStatus[] {
            return this.path.map(icon => ({
                unselected: `${this.basePath}${icon.unselected}`,
                selected: `${this.basePath}${icon.selected}`,
            }))
        }
    }
});

// tag 伸展菜单栏

interface iconName {
    text :string;
    path: string;
}

export const useIconExtendBoxPath = defineStore("iconExtendBox", {
    state: (): {
        QRcode: iconName,
        addFriend: iconName,
        post: iconName,
        searchGroup: iconName,
        // 
        preTalk: iconName,
        planDetail: iconName,
    } => {
        return {
            // tag Home
            QRcode: {
                text: "扫一扫",
                path: "/static/icon/QRcode.svg"
            },
            addFriend: {
                text: "添加好友",
                path: "/static/icon/addFriend.svg"
            },
            post: {
                text: "查找群聊",
                path: "/static/icon/post.svg"
            },
            searchGroup: {
                text: "发表笔记",
                path: "/static/icon/searchGroup.svg"
            },
            // tag AiTalk
            preTalk: {
                text: "常规设定",
                path: ""
            },
            planDetail: {
                text: "进入规划",
                path: ""
            },
        }
    },
    actions: {
        getHomeFunctionsIconName(): iconName[] {
            return [this.QRcode, this.addFriend, this.searchGroup, this.post];
        },
        getAiTalkFunctionsIconName(): iconName[] {
            return [this.preTalk, this.planDetail];
        }
    }
});

// 方向键
export const useArrowsIconPath = defineStore("iconArrow", {
    state: (): {
        up: string,
        down: string,
        downHistory: string,
        left: string,
        right: string,
        rightLight: string,
    } => {
        return {
            up: "/static/icon/up.svg",
            down: "/static/icon/down.svg",
            downHistory: "/static/icon/downHistory.svg",
            left: "/static/icon/left.svg",
            right: "/static/icon/right.svg",
            rightLight: "/static/icon/rightLight.svg"
        }
    }
})

// 窗口所用
export const useWindowIconPath = defineStore("iconWindow", {
    state: (): {
        shrink: string,
        reloadWin: string,
        close: string
    } => {
        return {
            shrink: "/static/icon/shrink.svg",
            reloadWin: "/static/icon/reloadWin.svg",
            close: "/static/icon/close.svg"
        }
    }
})

// tag Card
export const useCardIconPath = defineStore("iconCard", {
    state: (): {
        like: string,
        likeSelected: string,
        location: string
    } => {
        return {
            like: "/static/icon/like.svg",
            likeSelected: "/static/icon/likeSelected.svg",
            location: "/static/icon/location.svg"
        }
    }
})

export const useComIconPath = defineStore("iconCom", {
    state: (): {
        fire: string
    } => {
        return {
            fire: "/static/icon/fire.svg"
        }
    }   
})

export const useDetailIconPath = defineStore("iconDetail", {
    state: (): {
        dir: string,
    } => ({
        dir: "detailView/",
    }),
    getters: {
        // link: function(iconName: string): string {
        //     return iconDirPath + this.dir + iconName + ".svg";
        // },

        heart: function(): string {
            return iconDirPath + this.dir + "heart.svg";
        },
        heartLight: function(): string {
            return iconDirPath + this.dir + "heartLight.svg";
        },
        share: function(): string {
            return iconDirPath + this.dir + "share.svg";
        },
        star: function(): string {
            return iconDirPath + this.dir + "star.svg";
        },
        message: function(): string {
            return iconDirPath + this.dir + "message.svg";
        },
        microphone: function(): string {
            return iconDirPath + this.dir + "microphone.svg";
        },
        camera: function(): string {
            return iconDirPath + this.dir + "camera.svg";
        },
        // 三个功能键
        group: function(): string {
            return iconDirPath + this.dir + "group.svg";
            // return this.link("group");
        },
        plan: function(): string {
            return iconDirPath + this.dir + "plan.svg";
        },
        helper: function(): string {
            return iconDirPath + this.dir + "helper.svg";
        },
        location: function(): string {
            return iconDirPath + this.dir + "location.svg";
        }
    }
});

// tag Ai
export const useAiIconPath = defineStore("iconAi", {
    state: (): {
        dir: string,
    } => ({
        dir: "AiTalk/",
    }),
    getters: {
        add: function(): string {
            return iconDirPath + this.dir + "add.svg";
        },
        AI: function(): string {
            return iconDirPath + this.dir + "AI.svg";
        },
        emoji: function(): string {
            return iconDirPath + this.dir + "emoji.svg";
        },
        information: function(): string {
            return iconDirPath + this.dir + "information.svg";
        },
        phone: function(): string {
            return iconDirPath + this.dir + "phone.svg";
        },
        voice: function(): string {
            return iconDirPath + this.dir + "voice.svg";
        },
        LOGO: function(): string {
            return iconDirPath + "LOGO.svg";
        },
        clear: function(): string {
            return iconDirPath + this.dir + "clear.svg";
        },
        arrow: function(): string {
            return iconDirPath + this.dir + "arrow.svg";
        },
        line: function(): string {
            return iconDirPath + this.dir + "line.svg";
        },
        detail: function(): string {
            return iconDirPath + this.dir + "detail.svg";
        },
        star: function(): string {
            return iconDirPath + this.dir + "star.svg";
        },
        timedown: function(): string {
            return iconDirPath + this.dir + "down.svg";
        },
        list: function(): string {
            return iconDirPath + this.dir + "list.svg";
        },
    }
})

// tag plan-detail
export const usePlanIconPath = defineStore("iconPlan", {
    state: (): {
        dir: string,
    } => ({ dir: "planDetail/" }),
    getters: {
        back: function(): string {
            return iconDirPath + this.dir + "back.svg";
        },
        LOGO: function(): string {
            return iconDirPath + this.dir + "LOGO.svg";
        },
        next: function(): string {
            return iconDirPath + this.dir + "next.svg";
        },
        plan: function(): string {
            return iconDirPath + this.dir + "plan.svg";
        },
        share: function(): string {
            return iconDirPath + this.dir + "share.svg";
        },
        train: function(): string {
            return iconDirPath + this.dir + "train.svg";
        },
    }
})

// tag message
export const useMessageIconPath = defineStore("iconMessage", {
    state: (): {
        dir: string,
    } => ({ dir: "message/" }),
    getters: {
        like: function(): string {
            return iconDirPath + this.dir + "like.svg";
        },
        user: function(): string {
            return iconDirPath + this.dir + "user.svg";
        },
        message: function(): string {
            return iconDirPath + this.dir + "message.svg";
        },
        more: function(): string {
            return iconDirPath + "more.svg";
        },
        search: function(): string {
            return iconDirPath + "search.svg";
        },
    }
})