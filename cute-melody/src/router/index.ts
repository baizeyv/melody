import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";

export const constantRoutes: Array<RouteRecordRaw> = [
    {
        path: '/index',
        redirect: '/index'
    },
    {
        path: '/test',
        name: 'Test',
        // redirect: '/test'
        component: () => import('../views/Test-Page.vue')
    },
    {
        path: '/role',
        name: 'Role',
        redirect: '/test'
        // component: () => import('../views/role/Role-Page')
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes: constantRoutes
})

export default router