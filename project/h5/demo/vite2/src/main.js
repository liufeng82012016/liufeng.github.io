// 导入需要的函数
import {createApp} from 'vue'
// 导入待渲染的App.vue组件
import App from './App.vue'
// import App from './cart/App.vue'
import './index.css'
import axios from 'axios'

// 1. 导入全局组件
// import CustomComp1 from './components/CustomComp1.vue'

// 调用createApp函数，创建SPA应用实例
let app = createApp(App);

//2. 注册全局组件
// app.component(CustomComp1.name,CustomComp1)

// 全局配置axios
axios.defaults.baseURL = "http://localhost:8080"
app.config.globalProperties.$http = axios

// 调用mount()把App组件的模版结构，渲染到指定的el区域内
app.mount('#app')




