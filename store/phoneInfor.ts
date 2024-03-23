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
    actions: {
        setStatusBarHeight(height: number) {
            this.statusBarHeight = height;
        },
        setPhoneWidth(width: number) {
            this.phoneWidth = width;
        }
    }
})