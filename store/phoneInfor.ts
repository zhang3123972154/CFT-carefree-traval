import { defineStore } from "pinia";

export default defineStore("phoneStore", {
    state: (): {
        statusBarHeight: number;
        phoneWidth: number;
    } => {
        return {
            statusBarHeight: 20,
            phoneWidth: 420,
        }
    },
    getters: {
        cardWidth_2: function() {
            return Math.floor((this.phoneWidth - 20) / 2);
        },
        cardWidth_similarWin_2: function() {
            return Math.floor((this.phoneWidth * .8) / 2);  // info 两个设备的效果都不错
        }
    },  
    actions: {
        setStatusBarHeight(height: number) {
            this.statusBarHeight = height;
        },
        setPhoneWidth(width: number) {
            this.phoneWidth = width;
        }
    }
})