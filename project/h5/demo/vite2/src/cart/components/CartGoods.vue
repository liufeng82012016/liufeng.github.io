<template>
  <ul class="cart-goods">
    <li v-for="goods in goodsList" :key="goods.id">
      <input type="checkbox" v-model="goods.select"/>
      <img src="../../assets/logo.png"/>
      <div class="desc">
        <div class="title">{{ goods.desc }}</div>
        <div class="goods-count">
          <span class="red">¥ {{ goods.price }}</span>
          <CartCounter v-model:count="goods.count"></CartCounter>
        </div>
      </div>
    </li>
  </ul>
</template>
<script>
import CartCounter from './CartCounter.vue'
import bus from '../../assets/eventBus'

export default {
  name: "CartGoods",
  props: {
    url: {
      name: String,
      default: "book/goodsList"
    }
  },
  components: {
    CartCounter
  },
  data() {
    return {
      goodsList: []
    }
  },
  emits: ['selectChange', 'goodsChange'],
  async created() {
    // 发起Http请求获取数据
    const {data: res} = await this.$http.get(this.url);
    console.log(res);
    this.goodsList = res;
    bus.on('selectChange', (select => {
      for (let i = 0; i < this.goodsList.length; i++) {
        this.goodsList[i].select = select;
      }
    }))
  },
  watch: {
    goodsList: {
      handler(newVal, oldVal) {
        console.log(JSON.stringify(newVal))
        bus.emit("goodsChange", newVal);
      },
      deep: true
    }
  },
  methods: {}
}
</script>
<style lang="less">
.cart-goods {
  padding: 0;
  margin: 0;

  li {
    list-style: none;
    height: 100px;
    display: flex;
    align-items: center;
    width: 100%;
    overflow: hidden;

    img {
      width: 80px;
    }

    .desc {
      display: flex;
      flex-direction: column;
      justify-content: space-evenly;
      height: 100%;

      .title {
        display: block;
        word-break: break-word;
        font-weight: 500;

        //text-overflow: -o-ellipsis-lastline;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
      }

      .goods-count {
        display: flex;
        justify-content: space-between;
      }

      .red {
        color: red;
        font-weight: 700;
      }
    }


  }
}
</style>