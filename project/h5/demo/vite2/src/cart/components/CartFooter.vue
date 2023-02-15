<template>
  <div class="cart-footer">
    <div><input type="checkbox" v-model="select"/>全选</div>
    <div>合计：<span class="red">¥{{ value }}</span></div>
    <span class="submit">结算({{ count }})</span>
  </div>
</template>
<script>
import bus from '../../assets/eventBus.js'

export default {
  name: "CartFooter",
  data() {
    return {
      value: 0,
      count: 0,
      select: false
    }
  },
  emits: ['selectChange', 'goodsChange'],
  watch: {
    select() {
      bus.emit('selectChange', this.select);
    }
  },
  created() {
    bus.on('goodsChange', (goodsList => {
      this.count = 0;
      this.value = 0;
      for (let i = 0; i < goodsList.length; i++) {
        if (goodsList[i].select) {
          this.count += goodsList[i].count;
          this.value += (goodsList[i].count * goodsList[i].price);
        }
      }
    }))
  }
}
</script>
<style lang="less">
.cart-footer {
  height: 40px;
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;

  .red {
    color: red;
    font-weight: 600;
  }

  .submit {
    background-color: blue;
    color: white;
    height: 100%;
    line-height: 40px;
    padding: 0 20px;
    border-radius: 20px;
  }
}
</style>