<template>
  <router-link to="/login"></router-link>
  <router-link to="/admin"></router-link>
  <router-view></router-view>
</template>

<script>
import Login from './components/Login.vue'
import Admin from './components/Admin.vue'
import {computed} from 'vue'

export default {
  name: 'App',
  components: {
    Login,
    Admin,
  },
  created() {

  },
  data() {
    return {}
  },
  // 向子孙组件共享数据，默认不是响应式的数据，可以使用computed
  provide() {
    return {}
  },
  methods: {},
  computed: {}
}
</script>
<style lang="css">
</style>