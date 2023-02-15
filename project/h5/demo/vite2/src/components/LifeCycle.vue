<template>
  <p>life cycle</p>
  <span>{{ count }}</span>
  <button @click="count++">update</button>
</template>
<script>
export default {
  name: "LifeCycle",
  created() {
    // 最常见的生命周期函数，例如发Ajax请求初始数据
    console.log("组件被创建了")
  },
  mounted() {
    console.log("组件被加载了")

  },
  unmounted() {
    console.log("组件被销毁了")
  },
  updated() {
    console.log("组件被重新加载了")
  },
  data() {
    return {
      count: 0
    }
  }
}
</script>
<style>

</style>