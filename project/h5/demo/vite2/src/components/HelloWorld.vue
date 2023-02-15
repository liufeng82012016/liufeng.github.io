<template>
  <h1>{{ msg }}</h1>
  <button @click="addCount">count is: {{ count }}</button>
  <div class="father">
    <div class="son">
    </div>
  </div>
</template>

<script>
export default {
  // 相当于java的类名，可以和文件名不一致，通常会保持一致，会显示devtool
  name: 'HelloWorld',
  props: {
    // 按照java的构造器理解
    msg: String
  },
  data() {
    // 相当于vue的data
    return {
      count: 0
    }
  },
  methods: {
    addCount() {
      this.count++;
    }
  }
}
</script>

<!--lang可选css\less\scss，不写默认css-->
<!--less需安装-->
<style lang="less" >
.father {
  height: 200px;
  width: 200px;
  background-color: #0dcaf0;
  padding: 50px;

  .son {
    background-color: #0a58ca;
    height: 100px;
  }
}


</style>
