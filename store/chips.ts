import { defineStore } from "pinia";

interface chip {
    text: string,
    kind: string
}

function createChip(text: string, kind: string) {
    return { text, kind };
}

export default defineStore("chips", {
  state: (): {
    chipsGroup: chip[]
  } => {
    return {
        chipsGroup: []
    }
  },
  getters: {

  },
  actions: {
    add(text: string, kind: string) {
        const chip = createChip(text, kind);
        this.chipsGroup.push(chip);
    },
    checkStore(text: string) {
      // todo 检查是否出现过这个词条；考虑单匹配，还是多匹配？
      // return this.chipsGroup.include(text);
    }
  }
})