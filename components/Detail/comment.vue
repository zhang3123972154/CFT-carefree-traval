<template>
    <view>
        <!--一级评论-->
        <comment-sub :information="props.self"/>
            <!-- :avatar="props.self.avatar"/> -->
        <!--附属评论-->
        <view class="sub-container">
            <view>
                <comment-sub v-for="(item, index) in props.replay" :key="index"
                    v-show="index < (openFlag ? length : 1)"
                    :information="item"/>
            </view>
            <text v-if="length > 1" 
                class="open-comment"
                @click="openFlag = !openFlag">
                {{ openFlag ? "收起回复" : `展开${length - 1}条回复` }}
            </text>
        </view>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";  
    // com
    import commentSub from "./commentSub.vue";
    // store

// DATA
    const props = defineProps({
        self: {
            type: Object,
            default: () => ({
                avatar: '/static/example/User/avatar-1.svg',
                userName: '用户1',
                likeNum: 0,
                likeFlag: false,
                content: '默认文本',
                time: '0000-00-00'
            })
        },
        replay: {
            type: Array,
            default: () => []
        }
    });
    const emits = defineEmits([]);

    // flag
    const openFlag = ref(false);

// FUNC
    // gatter
    const length = computed(() => {
        return props.replay.length;
    })

</script>

<style scoped>

.sub-container {
    margin-left: 40px;
    margin-top: 5px;
}

.open-comment {
    font-size: 14px;
    font-weight: 500;
    color: #806201;
}

</style>        