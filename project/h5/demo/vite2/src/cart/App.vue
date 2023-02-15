<template>
  <CartHeader></CartHeader>
  <CartGoods></CartGoods>
  <CartFooter></CartFooter>
</template>
<script>
import CartHeader from './components/CartHeader.vue'
import CartFooter from './components/CartFooter.vue'
import CartGoods from './components/CartGoods.vue'

export default {
  name: "App",
  components: {
    CartHeader,
    CartFooter,
    CartGoods,
  }
}
</script>
<style>

</style>