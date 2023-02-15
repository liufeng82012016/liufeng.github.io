<template>
  <div class="cart-header" :style="{color: color,backgroundColor:bgColor}">
    {{ title }}
  </div>
</template>
<script>
export default {
  name: "CartHeader",
  props: {
    title: {
      name: String,
      default: "购物车案例"
    },
    color: {
      name: String,
      default: "white"
    },
    bgColor: {
      name: String,
      default: "blue"
    }
  },
  data() {
    return {}
  }
}
</script>
<style>
.cart-header {
  height: 40px;
  line-height: 40px;
  text-align: center;
  position: fixed;
  top:0;
  left: 0;
  width: 100%;
}
</style>