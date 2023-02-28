<template>
  <div>
    <span>get url:{{ url }}</span>
    <p>{{ resp }}</p>
    <button @click="get" type="button">发起GET请求</button>
  </div>

</template>
<script>
// import axios from 'axios'

export default {
  name: 'AxiosGet',
  data() {
    return {
      url: "/book/list",
      resp: ""
    }
  },
  methods: {
    async get() {
      console.log("发起请求")
      const {data: res} = await this.$http.get(this.url, {
        params: {
          // 传参
        }
      });
      this.resp = res
      console.log("结束请求")
    },
    reset() {
      this.resp = '';
    }
  }
}
</script>
<style></style>