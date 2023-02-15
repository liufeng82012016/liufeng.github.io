<template>
  <!--  <span>{{username}}</span>-->
  <input type="text" v-model.trim="username"/>
</template>
<script>
// import axios from 'axios'

export default {
  name: 'Watch',
  data() {
    return {
      username: "zhangsan",
      book: {
        author: "",
        name: ""
      }
    }
  },
  // 默认组件在一次渲染之后不会触发watch，需要使用immediate
  watch: {
    // 侦听器方法名和属性名相同
    // username(newVal,oldVal){
    //   console.log(newVal,oldVal);
    // }
    // username(newVal, oldVal) {
    // console.log(newVal);
    // const res = axios.get('http://localhost:8080/book/checkName?username=' + newVal);
    // console.log(res);
    // },

    username: {
      handler(newVal, oldVal) {
        console.log(newVal);
      },
      immediate: true,
    },
    // 监听对象的某个指定属性
    // 'book.name': {
    book: {
      handler(newVal, oldVal) {
        console.log(newVal.name);
      },
      // 默认watch无法侦听对象中的属性，需要使用deep属性去添加监听；添加之后，每个属性都会被侦听
      deep: true,
    }
  }
}
</script>
<style></style>