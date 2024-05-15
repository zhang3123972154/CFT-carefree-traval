<!--info 2.0 版本的信息块 -->
<template>
    <view class="flex-top-horizontal PT-container block gap-10" @click="gotoDetail">
            <view class="relative">
            <up-image :src="props.imgPath" fade radius="5" width="80" height="80"/>
            <view class="img-grade">{{ props.grade }}</view>
        </view>
        <view class="shrink">
            <view class="flex-horizontal">
                <view @click.top>
                    <t-chip class="t-ship" :kind="props.type" :text="props.text"></t-chip>
                </view>
                <view class="shrink"></view>
                <view @click.top>
                    <u-icon class="AI-btn" name="/static/icon/AiTalk/AI.svg" size="20"
                        @click="askMoreToAI"
                    />
                </view>
            </view>
            <text class="location-text">{{ props.introduce }}</text>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
    import { aiTalk } from "@/store/aiTalk";
    const talkStore = aiTalk();
// DATA
    const props = defineProps({
        type: {
            type: String,
            default: "spot"
        }, 
        text: {
            type: String,
            default: "默认点名"
        },
        grade: {
            type: Number,
            default: 4.9
        },
        location: {
            type: String,
            default: "XXX"
        },
        price: {
            type: Number,
            default: 0
        },
        imgPath: {
            type: String,
            default: ""
        },
        introduce: {
            type: String,
            default: "基本介绍"
        }
    });
    const emits = defineEmits([]);

// FUNC
    const askMoreToAI = () => {
        talkStore.sendUserMessage(props.text);
    }

    const gotoDetail = () => {
        // todo 后续转入 详细页。
    }

</script>

<style scoped>

.PT-container {
    margin: 5px 0;
}


.img-grade {
    position: absolute;
    right: 3px;
    top: 3px;
    padding: 0 2px;

    border-radius: 3px;
    background-color: #00000080;

    font-size: 16px;
    font-family: Alimama ShuHeiTi;
    color: #ffc300;
}

.location-text {
    font-size: 10px;
    font-weight: 300;
    color: #a68f47;
}

.t-ship {
    flex-shrink: 0;
}

.AI-btn {
    opacity: .8;
}

</style>        