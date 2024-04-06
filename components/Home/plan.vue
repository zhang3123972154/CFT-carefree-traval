<template>
    <view class="flex-center-horizontal plan-container" :class="'plan-container-' + props.kind">
        <view class="flex-vertical gap-5">
            <text class="title" :class="'title-' + props.kind">{{ getTitle() }}</text>
            <text class="time" :class="'time-' + props.kind">{{ props.timeStart }} 至 {{ props.timeEnd }}</text>
        </view>
        <view class="mid" :class="'mid-' + props.kind"></view>
        <view class="flex-center-horizontal content-container">
            <text class="content" :class="'content-' + props.kind">旅途详情及备注</text>
            <u-icon class="ml-20" :name="getIconPath"></u-icon>
        </view>
    </view>
</template>

<script setup>
    import { ref, computed } from "vue";
    // store
    import { useArrowsIconPath } from "@/store/dataBase";
    const iconPath = useArrowsIconPath();
// DATA
    const props = defineProps({
        index: {
            type: String,
            default: "1"
        },
        timeStart: {
            type: String,
            default: "01-01"
        },
        timeEnd: {
            type: String,
            default: "12-31"
        },
        kind: {
            type: String,
            default: "default" // default || ligh
        }
    });

// FUNC
    const getTitle = () => {
        switch(props.index) {
            case "1": return "方案一";
            case "2": return "方案二";
            case "3": return "方案三";
            default: return "方案X";
        };
    }

    const getIconPath = computed(() => {
        return props.kind == "light" ? iconPath.rightLight : iconPath.right;
    })

</script>

<style scoped>

.plan-container {
    padding: 8px 14px 4px 24px;
    border-radius: 15px;

    gap: 25px;
}

.plan-container-default {
    background-color: #f9f9f9;
}

.plan-container-light {
    background-color: #ffc3001a;
    border: solid 1px #ffc300;
}

.title {
    font-size: 14px;
    font-family: SourceHanSansCN;
    line-height: 13px;
}

.title-default {
    color: #000000;
}

.title-light {
    color: #FFC300;
}

.time {
    font-size: 8px;
    font-family: SourceHanSansCN;
    line-height: 10px;
    font-weight: 300;
}

.time-default {
    color: #999999;
}

.time-light {
    color: #b39533;
}

.mid {
    width: 1px;
    height: 21px;
}

.mid-default {
    background-color: #cccccc;
}

.mid-light {
    background-color: #ffc300;
}

.content-container {
    height: 100%;
}

.content {
    font-size: 10px;
    font-family: SourceHanSansCN;
    line-height: 10px;
    font-weight: 300;
}

.content-default {
    color: #666666;
}

.content-light {
    color: #b39533;
}

</style>        