<template>
  <p>Movie：{{ $route.params.id }}</p>
  <button @click="goBack">go back</button>
</template>
<script>

export default {
  name: "MyMovie",
  methods: {
    goBack() {
      this.$router.go(-1);
    }
  }
}
</script>
<style lang="less">

</style>