<script setup lang="ts">
// This starter template is using Vue 3 <script setup> SFCs
// Check out https://vuejs.org/api/sfc-script-setup.html#script-setup
import { ElConfigProvider } from "element-plus";
import {defineComponent} from "vue";
import LayoutIndex from "./layout/Layout-Index.vue"

const size = 'small'
const zIndex = 3000

defineComponent({
    components: {
        LayoutIndex
    }
})
</script>

<template>
    <el-config-provider :size="size" :z-index="zIndex">
        <div>
            <layout-index/>
        </div>
    </el-config-provider>
</template>

<style scoped>
body {
    margin: 0;
}
</style>
