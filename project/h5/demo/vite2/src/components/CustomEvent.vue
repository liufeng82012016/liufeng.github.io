<template>
  <div>
    count的值为:{{ count }}
    <button @click="add">
      +1
    </button>
  </div>
</template>

<script>
export default {
  name: "CustomEvent",
  emits: [
    // 1. 声明自定义事件
    'countChange'
  ],
  data() {
    return {
      count: 0,

    }
  },
  methods: {
    add() {
      this.count++;
      // 2. 触发自定义事件，第二个参数可以穿参
      this.$emit('countChange',this.count);
    }
  }
}
</script>
<style>

</style>