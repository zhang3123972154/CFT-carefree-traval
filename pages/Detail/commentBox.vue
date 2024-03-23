<template>
    <view class="flex-vertical gap-10">
        <text>共{{ props.commentNum }}条评论</text>
        <comment-top/>
        <!---->
        <comment v-for="(item, index) in props.list" :key="index"
            :self="item.self" :replay="item.replay"/>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import commentTop from "@/components/Detail/commentTop.vue";
    import comment from "@/components/Detail/comment.vue";
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
                    self: {
                        avatar: "/static/example/User/avatar-2.svg",
                        userName: "逍遥游用户1",
                        likeNum: 193,
                        likeFlag: true,
                        
                        content: "P1是在雾霾天拍出来的，雾霾天可以很好的排除轮廓和厚重感！！所以，千万不要因为天气不好而不去摄影！！P1的鸟也是真的，为了火车和鸟可以同框我等了一个小时！！！",
                        time: "2023-01-21",
                    },
                    replay: [ // tag 二级评论
                        {
                            avatar: "/static/example/User/avatar-2.svg",
                            userName: "逍遥游用户1",
                            likeNum: 43,
                            likeFlag: false,

                            content: "可以说我的运气真的很不错！竟然真的可以等到！！！",
                            time: "2023-01-21",
                        },
                        {
                            avatar: "/static/example/User/avatar-1.svg",
                            userName: "逍遥游用户 TEST",
                            likeNum: 0,
                            likeFlag: false,

                            content: "TEST",
                            time: "2023-01-22",
                        }
                    ]
                },
                {
                    // dafult
                }
            ]
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

</style>        