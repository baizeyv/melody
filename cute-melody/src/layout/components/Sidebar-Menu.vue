<script setup lang="ts">
const props = defineProps({
    isCollapse: {
        type: Boolean,
        default: false
    }
})
</script>

<template>
    <div class="main">
        <el-scrollbar>
            <el-menu
                default-active="2"
                class="el-menu-vertical-demo"
                :collapse="isCollapse">
                <el-sub-menu index="1">
                    <template #title>
                        <el-icon>
                            <location/>
                        </el-icon>
                        <span>Navigator One</span>
                    </template>
                    <el-menu-item-group>
                        <template #title><span>Group One</span></template>
                        <el-menu-item index="1-1">item one</el-menu-item>
                        <el-menu-item index="1-2">item two</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="Group Two">
                        <el-menu-item index="1-3">item three</el-menu-item>
                    </el-menu-item-group>
                    <el-sub-menu index="1-4">
                        <template #title><span>item four</span></template>
                        <el-menu-item index="1-4-1">item one</el-menu-item>
                    </el-sub-menu>
                </el-sub-menu>

                <el-menu-item index="2">
                    <el-icon>
                        <Menu/>
                    </el-icon>
                    <template #title>Navigator Two</template>
                </el-menu-item>
                <el-menu-item index="3" disabled>
                    <el-icon>
                        <document/>
                    </el-icon>
                    <template #title>Navigator Three</template>
                </el-menu-item>
                <el-menu-item index="4">
                    <el-icon>
                        <setting/>
                    </el-icon>
                    <template #title>Navigator Four</template>
                </el-menu-item>
            </el-menu>
        </el-scrollbar>
    </div>
</template>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 240px;
    min-height: calc(100vh - 60px);
}
.el-menu--collapse {
    min-height: calc(100vh - 60px);
}
.main {
    height: calc(100vh - 60px);
}
</style>