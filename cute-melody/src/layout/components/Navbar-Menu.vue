<script setup lang="ts">

import BreadcrumbMenu from "./Breadcrumb-Menu.vue";

const props = defineProps({
    isCollapse: {
        type: Boolean,
        default: false
    }
})

const emit = defineEmits(
    ['collect']
)

const collect = () => {
    emit('collect')
}

</script>

<template>
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        :ellipsis="false"
        @select="handleSelect"
    >
        <el-menu-item index="0">
            Melody Manage System
        </el-menu-item>
        <div style="align-self: center;">
            <el-icon :size="22" @click="collect" class="collectStyle" v-if="isCollapse"><Expand/></el-icon>
            <el-icon :size="22" @click="collect" class="collectStyle" v-else><Fold/></el-icon>
        </div>
        <div class="flex-grow" />
        <el-menu-item index="1">Processing Center</el-menu-item>
        <el-sub-menu index="2">
            <template #title>Workspace</template>
            <el-menu-item index="2-1">item one</el-menu-item>
            <el-menu-item index="2-2">item two</el-menu-item>
            <el-menu-item index="2-3">item three</el-menu-item>
            <el-sub-menu index="2-4">
                <template #title>item four</template>
                <el-menu-item index="2-4-1">item one</el-menu-item>
                <el-menu-item index="2-4-2">item two</el-menu-item>
                <el-menu-item index="2-4-3">item three</el-menu-item>
            </el-sub-menu>
        </el-sub-menu>
    </el-menu>
</template>

<style scoped>
.flex-grow {
    flex-grow: 1;
}
.logoPart:hover, .logoPart:focus, .logoPart {
    background-color: #fff !important;
    border-bottom: none !important;
    color: #40aaff !important;
    padding: 0 !important;
}
.collectStyle:hover {
    cursor: pointer;
}
</style>