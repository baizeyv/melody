<script setup lang="ts">
import NavbarMenu from "../layout/components/Navbar-Menu.vue"
import SidebarMenu from "../layout/components/Sidebar-Menu.vue"
import {reactive} from "vue";
import BreadcrumbMenu from "./components/Breadcrumb-Menu.vue";

const clt = () => {
    col.collapseFlag = !col.collapseFlag;
    return col.collapseFlag
}
const col = reactive({
    collapseFlag: false
})
</script>

<template>
    <div>
        <navbar-menu @collect="clt" :is-collapse="col.collapseFlag" />
        <div style="display: flex">
            <sidebar-menu :is-collapse="col.collapseFlag"/>
            <div class="contentBox">
                <breadcrumb-menu/>
                <el-scrollbar>
                    <router-view></router-view>
                </el-scrollbar>
            </div>
        </div>
    </div>
</template>

<style>
.contentBox{
    width: 100%;
    padding: 10px;
    display: flex;
    flex-direction: column;
    max-height: calc(100vh - 80px);
}
</style>