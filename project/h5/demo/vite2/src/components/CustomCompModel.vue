<!--组件上使用v-model，同步父/子组件数据-->
<!--父组件中使用v-bind属性绑定的形式，将数据传递给子组件-->
<!--子组件中，通过props接收父组件传递过来的数据-->
<template>
  <div>
    count的值为:{{ number }}
    <!--报错：    uncaught TypeError: 'set' on proxy: trap returned falsish for property 'number'-->
    <button @click="add">+1</button>
  </div>
</template>
<script>
export default {
  name: "CustomCompModel",
  props: {
    number: Number
  },
  // 固定格式，update:fieldName
  emits: ['update:number'],
  methods: {
    add() {
      // this.number++;
      // this.number++ 报错：    uncaught TypeError: 'set' on proxy: trap returned falsish for property 'number'
      // 直接传入this.number+1则正常？？
      this.$emit('update:number', this.number + 1);
    }
  }
}
</script>
<style>

</style>