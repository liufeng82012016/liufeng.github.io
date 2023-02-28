import {createRouter, createWebHashHistory} from 'vue-router'
import MyHome from '../components/MyHome.vue'
import MyAbout from '../components/MyAbout.vue'
import MyMovie from '../components/MyMovie.vue'
import Tab1 from '../components/Tab1.vue'
import Tab2 from '../components/Tab2.vue'


const router = createRouter({
    history: createWebHashHistory(),
    // 自定义路由激活类名，默认：router-link-active
    linkActiveClass: "router-active",
    // 命名路由：给path指定唯一名称，router-link可以通过名称指定路由和参数
    routes: [
        // 路由重定向
        {path: "/", redirect: '/home'},
        {path: "/home", component: MyHome},
        // 动态路由参数，组件可通过$route.params获取路径参数;或者使用props传递参数
        {path: "/movie/:id", component: MyMovie, props: true, name: "mov"},
        {
            // 默认展示tab1
            path: "/about", component: MyAbout, redirect: "/about/tab1", children: [
                // 子路由path不用/开头
                {path: 'tab1', component: Tab1},
                {path: 'tab2', component: Tab2}
            ]
        }
    ]
})
// 声明全局的导航守卫，3个参数可选，to(将要访问的路由),from(将要离开的路由),
// next(是一个函数，表示放行（不传表示默认放行，允许访问每一个路由，声明之后需调用next()函数，否则不允许访问任何一个路由，
// next(false)表示停留在当前页面，next('hash')强制用户访问指定路由）)
router.beforeEach(() => {
    console.log('ok');
})

export default router