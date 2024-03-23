<template>
    <view class="flex-vertical gap-10">
        <text>共{{ props.commentNum }}条评论</text>
        <comment-top/>
        <comment/>
        <comment :content="props.list[0].content"/>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import commentTop from "@/components/Detail/commentTop.vue";
    import comment from "../../components/Detail/comment.vue";
    // store
// DATA
    const props = defineProps({
        commentNum: {
            type: [Number, String],
            default: 1856
        },
        list: {
            type: Array,
            default: () => [
                {
                    avatar: "/static/example/User/avatar-2.svg",
                    usr_name: "逍遥游用户1",
                    likeNum: 193,
                    likeFlag: true,
                    
                    content: "P1是在雾霾天拍出来的，雾霾天可以很好的排除轮廓和厚重感！！所以，千万不要因为天气不好而不去摄影！！P1的鸟也是真的，为了火车和鸟可以同框我等了一个小时！！！",
                    time: "2023-01-21",

                    replay: [ // tag 二级评论
                        {
                            avatar: "/static/example/User/avatar-2.svg",
                            usr_name: "逍遥游用户1",
                            likeNum: 43,
                            likeFlag: false,

                            content: "可以说我的运气真的很不错！竟然真的可以等到！！！"
                        }
                    ]
                }
            ]
        }
    });
    const emits = defineEmits([]);

// FUNC共1856条评论

</script>

<style scoped>

</style>        