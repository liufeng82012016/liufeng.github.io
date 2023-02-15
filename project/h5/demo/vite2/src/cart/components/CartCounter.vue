<template>
  <div class="cart-counter">
    <span @click="sub">-</span>
    <span class="count">{{ count }}</span>
    <span @click="add">+</span>
  </div>
</template>
<script>
export default {
  name: "CartCounter",
  props: {
    count: {
      name: Number,
      default: 0
    }
  },
  emits: ['update:count'],
  data() {
    return {}
  },
  methods: {
    add() {
      this.$emit('update:count', this.count + 1);
    },
    sub() {
      if (this.count > 0) {
        this.$emit('update:count', this.count - 1);
      }
    }
  }
}
</script>
<style lang="less">
.cart-counter {
  span {
    padding: 0 5px;
  }

  .count {
    border: 1px solid lightgray;
  }
}
</style>