import {createRouter, createWebHashHistory} from 'vue-router'
import Login from '../components/Login.vue'
import Admin from '../components/Admin.vue'
import UserList from '../components/UserList.vue'
import Permission from '../components/Permission.vue'
import UserDetail from '../components/UserDetail.vue'


const router = createRouter({
    history: createWebHashHistory(),
    // 命名路由：给path指定唯一名称，router-link可以通过名称指定路由和参数
    routes: [
        // 路由重定向
        {path: "/", redirect: '/admin'},
        {path: "/login", component: Login},
        {
            path: "/admin", component: Admin, redirect: "/admin/users", children: [
                {path: "users", component: UserList},
                {path: "permission", component: Permission},
                {path: "users/:id", component: UserDetail, props: true},
            ]
        },
    ]
})
// 声明全局的导航守卫，3个参数可选，to(将要访问的路由),from(将要离开的路由),
// next(是一个函数，表示放行（不传表示默认放行，允许访问每一个路由，声明之后需调用next()函数，否则不允许访问任何一个路由，
// next(false)表示停留在当前页面，next('hash')强制用户访问指定路由）)
router.beforeEach((to, from, next) => {
    console.log('to:' + to);
    console.log('to:' + from);
    let token = localStorage.getItem("token");
    if (!token && '/login' !== to.path) {
        // 如果没有token，且不是登录页，跳转到登录页面
        next('/login');
    } else {
        next();
    }
})

export default router