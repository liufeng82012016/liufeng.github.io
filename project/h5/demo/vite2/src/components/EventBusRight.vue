<template>
  <span>right.count值为：{{ count }} </span>
  <button @click="addCount">+1</button>
</template>

<script>
import bus from "../assets/eventBus"

export default {
  name: 'EventBusLeft',
  data() {
    return {
      count: 0
    }
  },
  emits:['countChange'],
  methods:{
    addCount(){
      this.count++;
      // 发送事件
      bus.emit('countChange',this.count);
    }
  }
}
</script>

<style lang="css">
/*h1{*/
/*  color: #0dcaf0;*/
/*}*/
</style>
