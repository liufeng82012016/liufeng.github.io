<template>
  <span>left.count值为：{{ count }} </span>
</template>

<script>
import bus from "../assets/eventBus";
export default {
  name: 'EventBusLeft',
  data() {
    return {
      count: 0
    }
  },
  created() {
    // 自定义事件，接受数据
    bus.on('countChange', (count) => {
      this.count = count;
    })
  }
}
</script>

<style lang="css">
/*h1{*/
/*  color: #0dcaf0;*/
/*}*/
</style>
