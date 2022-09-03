<template>
    <div class="mainBody">
        <navbar-menu></navbar-menu>
        <div style="display: flex">
            <sidebar-menu></sidebar-menu>
            <div style="width: 100%;padding: 10px">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script>
import NavbarMenu from "@/layout/components/Navbar-Menu";
import SidebarMenu from "@/layout/components/Sidebar-Menu";
export default {
    name: "Layout-Index",
    components: {SidebarMenu, NavbarMenu},
    data() {
        return {
            NavbarMenu,
            SidebarMenu
        }
    },
    methods: {
        test: function() {
            this.axios({
                method: 'get',
                url: '/test/test',
            }).then(res => {
                console.log(res)
            })
        }
    },
    created() {
        this.test();
    }
}
</script>

<style scoped>
.mainBody {
    /*display: flex;*/
}
</style>