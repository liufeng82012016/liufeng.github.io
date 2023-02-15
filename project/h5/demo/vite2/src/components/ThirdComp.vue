<template>
<!--  响应式的值，需要用.value进行调用-->
  <span>third.name:{{ name }} third.author:{{ author }} third.bookCount:{{ bookCount.value }} </span>
</template>
<script>
export default {
  name: 'ThirdComp',
  data() {
    return {
    }
  },
  // 接收数据
  inject:['name','author','bookCount']
}
</script>
<style>

</style>