<template>
  <p>Home</p>
  <!--  可以指定一个对象，配合push方法实现编程式路由-->
  <router-link :to="{name:'mov',params:{id:3}}">go to movie</router-link>
  <button @click="goToMovie(3)">go to movie</button>
</template>
<script>

export default {
  name: "MyHome",
  methods: {
    goToMovie(id) {
      this.$router.push('/movie/' + id);
    }
  }
}
</script>
<style lang="less">

</style>