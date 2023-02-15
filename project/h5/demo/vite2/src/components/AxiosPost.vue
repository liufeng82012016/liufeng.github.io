<template>
  <div>
    <span>post url:{{ url }}</span>
    <p>{{ resp }}</p>
    <button @click="post">发起POST请求</button>
  </div>
</template>
<script>
// import axios from 'axios'

export default {
  name: 'AxiosPost',
  data() {
    return {
      url: "/book/addBook",
      resp: ""
    }
  },
  methods: {
    async post() {
      const {data: res} = await this.$http.post(this.url, {
        name: "西游记",
        author: "吴承恩",
        press: "四川出版社",
      })
      this.resp = res;
    }
  }
}
</script>
<style></style>