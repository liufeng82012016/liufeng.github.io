<template>
  <p>About</p>
  <router-link to="/about/tab1">tab1</router-link>&nbsp;
  <router-link to="/about/tab2">tab2</router-link>
  <router-view></router-view>
</template>
<script>

import Tab1 from './Tab1.vue'
import Tab2 from './Tab2.vue'

export default {
  name: "MyAbout",
  components: [
    Tab1, Tab2
  ]
}
</script>
<style lang="less">


</style>