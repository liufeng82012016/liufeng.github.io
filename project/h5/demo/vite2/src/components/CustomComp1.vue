<template>
  <h1>{{ msg }} </h1>
  <span>33</span>
</template>

<script>
export default {
  name: 'CustomComp1',
  props: {
    msg: String,
    count: Number
  },
  data() {
    return {
      // msg: "hello，comp1"
    }
  },
}
</script>

<style lang="css">
/*h1{*/
/*  color: #0dcaf0;*/
/*}*/
</style>
