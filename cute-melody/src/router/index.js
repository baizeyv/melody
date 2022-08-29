import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
    {
        path: '/test',
        name: 'Test',
        component: () => import('../views/TestPage')
    }
];

const router = createRouter({
    history: createWebHashHistory(), // hash 路由模式
    // history: createWebHistory(), // history 路由模式
    routes
})

export default router;