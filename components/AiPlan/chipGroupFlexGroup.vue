<template>
    <view class="flex-vertical content-container mt-20 gap-10">
        <template v-for="(item, index) in props.chipGroupList">
            <chip-group-flex
                :title="item.title"
                :way-list="item.tags"
                :spot-list="[]"
                :thing-list="[]"

                @add=""
                @delete=""
            />
            <!-- :light-index="item.lightIndex"-->
        </template>
    </view>
</template>

<script setup>
    import { ref } from "vue";
    // com
    import chipGroupFlex from "./chipGroupFlex.vue";
    // store
// DATA
    const props = defineProps({
        title: {
            type: String,
            default: "默认主标题" // 但是应该时用不到了
        },
        chipGroupList: {
            type: Array,
            default: [
                {
                    title: "sub-title-1",
                    kind: "way",
                    lightIndex: 0,
                    tags: ["tag-1", "tag-2", "tag-3"]
                },
                {
                    title: "sub-title-2",
                    kind: "way",
                    lightIndex: 0,
                    tags: ["tag-3", "tag-4", "tag-5"]
                }
            ]
        }
    });
    const emits = defineEmits(["add", "delete"]);

// FUNC
    const addTag = () => {

    }
    const deleteTag = () => {

    }

</script>

<style scoped>

</style>        