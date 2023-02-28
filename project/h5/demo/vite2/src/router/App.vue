<template>
  <div class="router-container">
    <h3>手动声明路由</h3>
    <a href="#/home">Home</a>
    <a href="#/about">About</a>
    <a href="#/movie">Movie</a>
  </div>
  <component :is="comName"></component>
  <hr>
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
  created() {
    window.onhashchange = () => {
      switch (location.hash) {
        case '#/home':
          this.comName = 'MyHome';
          break;
        case '#/about':
          this.comName = 'MyAbout';
          break;
        case '#/movie':
          this.comName = 'MyMovie';
          break;
      }
    }
  },
  mounted() {
  },
  methods: {},
}
</script>
<style lang="less">
.router-container {
  a {
    display: inline-block;
    width: 50px;
    margin-right: 20px;
  }
}
</style>