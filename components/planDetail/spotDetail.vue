<template>
    <view class="flex-equal-horizontal PT-container block gap-10">
        <view class="relative no-shrink">
            <up-image :src="props.imgPath" fade radius="5" width="50" height="50"/>
            <view class="img-grade">{{ props.grade }}</view>
        </view>
        <view class="shrink mid-container">
            <view class="flex-horizontal gap-5">
                <t-chip :kind="props.type" :text="props.text"></t-chip>
                <t-chip v-for="(item, index) in props.things" kind="thing" :text="item"></t-chip>
                <view class="shrink"></view>
            </view>
            <text class="location-text">{{ props.location }}</text>
        </view>
        <!--info 第三列 自由组成-->
        <view class="no-shrink">
            <slot></slot>
        </view>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
// DATA
    const props = defineProps({
        type: {
            type: String,
            default: "spot"
        },
        text: {
            type: String,
            default: "民宿"
        },
        things: {
            type: Array,
            default: ["热干面", "TEST123"] // bug 当things过多时，会导致时间块越界。
        },
        imgPath: {
            type: String,
            default: "/static/example/background/home.png"
        },
        grade: {
            type: Number,
            default: 4.9
        },
        location: {
            type: String,
            default: "你武。balabalabalabala"
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

.PT-container {
    margin: 10px 0;
}

.img-grade {
    position: absolute;
    right: 3px;
    top: 3px;
    padding: 0 2px;

    border-radius: 3px;
    background-color: #00000080;

    font-size: 10px;
    font-family: Alimama ShuHeiTi;
    color: #ffc300;
}

.location-text {
    font-size: 10px;
    color: #a68f47;

    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis; /* 文本溢出时显示省略号 */
}

.mid-container {
    overflow-x: auto;
}

</style>        