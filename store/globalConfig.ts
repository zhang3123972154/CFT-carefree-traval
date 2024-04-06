import { defineStore } from "pinia";

export const useColors = defineStore("colorStore", {
    state: () : {
        bgColor: String;    // tag 需要和 bgColor.css 保持一致。
        borderWhite: String;
        borderBlack: String;
    } => {
        return {
            bgColor: "#f0f0f0",
            borderWhite: "#ffffffff",
            borderBlack: "#00000033",
        }
    }
})