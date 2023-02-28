import {createApp} from 'vue'
import App from './App.vue'

// 引用element-ui,全局引入不生效，且体积大，按需求引入（安装插件后，在vue.config.js 配置）
//  npm install -D unplugin-vue-components unplugin-auto-import
// import ElementPlus from 'element-plus'
import {ElLoading} from 'element-plus'
// import 'element-plus/dist/index.css'
import axios from 'axios'

let loadingInstance = null;
// 解决跨域问题（仅开发环境），使用vue-proxy，修改端口为本地服务端口，并在项目根目录vue.config.js声明代理配置
axios.defaults.baseURL = "http://localhost:8080"
// axios.defaults.baseURL = "http://localhost:8081"
// 配置请求拦截器
axios.interceptors.request.use(config => {
    loadingInstance = ElLoading.service({fullscreen: true});
    // 添加header
    config.headers.Authorization = "";
    // 固定写法
    return config;
})
// 配置相应拦截器
axios.interceptors.response.use(function (response) {
        if (loadingInstance) {
            // 关闭loading效果
            loadingInstance.close();
        }
        // 固定写法
        return response;
    }, function (error) {
        console.log("error:" + JSON.stringify(error))
        return Promise.reject(error);
    }
)
let app = createApp(App);
// 挂载全局axios
app.config.globalProperties.$http = axios

// app.use(ElementPlus);


app.mount('#app');
