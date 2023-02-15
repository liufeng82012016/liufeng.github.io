<!--计算属性demo-->
<template>
  <div class="compute-demo">
    <ul>
      <li v-for="fruit in fruits" :key="fruit.name">
        <input type="checkbox" v-model="fruit.select"/>
        <img src="../assets/logo.png">
        <div>
          <div>{{ fruit.name }}</div>
          <div class="attr">¥{{ fruit.price }}</div>
        </div>
        <div class="actions">
          <span @click="fruit.count--">-</span>
          <span @click="fruit.count++">{{ fruit.count }}</span>
          <span>+</span>
        </div>
      </li>
    </ul>
    <span> 总数量：{{ totalCount }}</span>
    <span> 总价格：{{ totalValue }}</span>
    <span> 结算</span>
  </div>

</template>
<script>
export default {
  name: "ComputeDemo",
  data() {
    return {
      fruits: [
        {
          name: "柚子",
          price: 3,
          count: 0,
          select: false
        }, {
          name: "苹果",
          price: 5,
          count: 0,
          select: false
        }
      ]
    }
  }, computed: {
    totalCount() {
      let total = 0;
      for (let i = 0; i < this.fruits.length; i++) {
        if (this.fruits[i].select) {
          total += this.fruits[i].count;
        }
      }
      return total;
    },
    totalValue() {
      let total = 0;

      for (let i = 0; i < this.fruits.length; i++) {
        if (this.fruits[i].select) {
          total += (this.fruits[i].count * this.fruits[i].price);
        }
      }
      return total;
    }
  },
  methods: {
    sub(fruit) {
      if (fruit.count > 0) {
        fruit.count--;

      }
    }, add(fruit) {
      fruit.count++;
    }
  }
}
</script>
<style lang="less">
ul {
  margin: 10px;
  padding: 0;

  li {
    list-style: none;
    display: flex;
    height: 100px;
    //background-color: #0dcaf0;
    //justify-content: space-around;

    button {
      height: 20px;
    }

    img {
      width: 80px;
      height: 80px;
      margin-left: 20px;
    }

    div {
      margin-left: 20px;
    }

    .attr {
      margin-top: 35px;
      color: red;
    }

    .actions {
      padding-left: 90px;

      span {
        display: inline-block;
        width: 20px;
        margin-top: 58px;
      }

      span:nth-child(2n+1) {
        background-color: lightgray;
      }


    }

  }
}


</style>