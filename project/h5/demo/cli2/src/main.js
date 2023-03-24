import {createApp} from 'vue'
import App from './App.vue'
import router from './router'

import {ElLoading} from 'element-plus'
import axios from 'axios'

let loadingInstance = null;
axios.defaults.baseURL = "http://localhost:8081"
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

app.use(router);
app.use(axios);
app.mount('#app')
