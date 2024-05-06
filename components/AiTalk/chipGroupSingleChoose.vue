<template>
    <view class="container">
        <chip-group-single
            :tag-kind="props.tagKind"
            :tag-list="tagList"
            :reply="props.reply"
            
            @clickChoose="reply"
        >
            <template v-if="props.reload" #suffix>
                <t-chip 
                    :kind="props.reloadKind" 
                    light 
                    shape="square"
                    @click="reload">
                    <u-icon name="/static/icon/chip-reload.svg" size="20"></u-icon>
                </t-chip>
            </template>

        </chip-group-single>
    </view>
</template>

<script setup>
    import { ref, onMounted } from "vue";
    // com
    import chipGroupSingle from "@/components/Home/chipGroupSingle.vue";
    // store
    import { aiTalk } from "@/store/aiTalk";
    const talkStore = aiTalk();
// DATA
    const props = defineProps({
        tagKind: String,
        tagList: {
            type: Array,
            default: () => ["1", "2", "3", "4", "5"]
        },
        // function
        light: Boolean, // info 初始化是否点亮
        // tag reload
        reload: Boolean, // info 添加 reload 按钮
        reloadKind: { // info 前后操作的功能键 样式
            type: String,
            default: "traffic"
        },
        reloadNum: {
            type: Number,
            default: 10
        },
        reply: Boolean // info 点击是否代表用户回答
    });
    const emits = defineEmits([]);

    let tagIndex = 0;
    const SHOW_NUM = props.reloadNum;
    const tagList = ref([]);

// FUNC
    onMounted(() => {
        if(props.reload)
            loadNextGroup();
        else
            tagList.value = props.tagList;
    })

    const reload = () => {
        if(tagIndex >= props.tagList.length)
            tagIndex = 0;

        loadNextGroup();
    }

    const loadNextGroup = () => {
        const num = Math.min(tagIndex + SHOW_NUM, props.tagList.length);
        tagList.value = props.tagList.slice(tagIndex, num);
        tagIndex = num;
    }

    const reply = (index) => {
        if(!props.reply)
            return;
        talkStore.sendUserMessage(tagList.value[index]);
    }
</script>

<style scoped>

.container {
    padding: 5px 0;
}

</style>        