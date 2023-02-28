<template>
    <el-button>Primary</el-button>

</template>


<script>

export default {
  name: 'App',
  data() {
    return {
      msg: ""
    }
  },
  components: {},
  created() {
    // this.bookList();
  },
  methods: {
    async bookList() {
      const {data: res} = await this.$http('/book/list', {
        params: {}
      });
      console.log("res:" + res)
      this.msg = res;
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
