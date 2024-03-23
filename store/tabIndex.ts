import { defineStore } from "pinia";

export default defineStore("curtabIndexStore", {
  state: (): {
    currentIndex: number;
  } => {
    return {
      currentIndex: 0,
    };
  },
  actions: {
    changeCurtabIndex(index: number) {
      if (index >= 0 && index <= 5) 
        this.currentIndex = index;
    //   console.info("change-test", index);
    },
  },
});