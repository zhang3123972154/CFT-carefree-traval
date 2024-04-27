<template>
    <view class="flex-center-both gap-5">
        <t-chip kind="spot" :text="props.spotStart"></t-chip>
        <view class="flex-center-vertical shrink">
            <view class="flex-center-both gap-5 time">
                <text>{{ props.timeStartDay }}</text>
                <text>{{ props.timeStartClock }}</text>
                <u-icon class="train-icon" :name="iconPath.train"></u-icon>
                <text>{{ props.timeEndDay }}</text>
                <text>{{ props.timeEndClock }}</text>
            </view>
            <u-icon class="icon" :name="iconPath.next" width="150"></u-icon>
            <!-- <up-image src="/static/example/nextArrow.svg"
                :width="180"
                :height="20"/> -->
        </view>
        <t-chip kind="spot" :text="props.spotEnd"></t-chip>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // store
    import { usePlanIconPath } from "@/store/dataBase";
    const iconPath = usePlanIconPath();
// DATA
    const props = defineProps({
        spotStart: {
            type: String,
            default: "出发地"
        },
        spotEnd: {
            type: String,
            default: "目的地"
        },
        timeStartDay: {
            type: String,
            default: "00-00"
        },
        timeStartClock: {
            type: String,
            default: "00：00"
        },
        timeEndDay: {
            type: String,
            default: "00-00"
        },
        timeEndClock: {
            type: String,
            default: "00：00"
        }
    });
    const emits = defineEmits([]);

// FUNC

</script>

<style scoped>

/* .train-icon {
    margin: 0 7px;
} */

.time {
    font-size: 10px;
    line-height: 12px;
    color: #ffffff;
}

.next-icon {
    width: 100%;
}

.icon {
    height: 10px;
}

</style>        