<template>
  <router-link to="/home">首页</router-link>
  <router-link to="/movie/1">电影1</router-link>
  <router-link to="/movie/2">电影2</router-link>
  <router-link to="/movie/3">电影3</router-link>
  <router-link to="/about">关于</router-link>
  <router-view></router-view>
</template>
<script>
import MyHome from './components/MyHome.vue'
import MyAbout from './components/MyAbout.vue'
import MyMovie from './components/MyMovie.vue'

export default {
  name: "App",
  components: {
    MyHome,
    MyAbout,
    MyMovie,
  },
  data() {
    return {
      comName: "MyHome"
    }
  },
  mounted() {
  },
  methods: {},
}
</script>
<style lang="less">
//激活的路由
.router-link-active{
  color: orange;
}

.router-active{
  color: orange;
}
</style>