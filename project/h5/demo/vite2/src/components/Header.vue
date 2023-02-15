<template>
  <div class="header" :style="{backgroundColor:bgcolor,color:color}">
    {{ title }}
    <span>{{ item }}</span>
    <span>{{ count }}的值乘以2={{ plus }}</span>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    title: String,
    bgcolor: String,
    color: String,
    // props验证：防止输入不合法的数据（指定数据类型），可选String，Number，Boolean，Array，Object，Date，Function
    // 指定多个可用多个类型
    info: [String, Number],
    // 必填项
    item: {
      name: String,
      required: false,
      // 默认值
      default: 'zhangsan',
      validator(value) {
        // 自定义校验函数，必须返回flase或true
        console.log(value);
        return false;
      }
    }

  },
  data() {
    return {
      count: 1
    }
  },
  computed: {
    // 计算属性本质上是一个函数，监听data内属性的值，并返回新的值；计算属性必须有返回值，可当作普通属性使用
    // 计算属性会做缓存，方法每次都会调用，前者性能更好
    plus() {
      return this.count * 2;
    }
  }
}
</script>

<style lang="css">
.header {
  height: 40px;
  line-height: 40px;
  text-align: center;
  position: absolute;
  font-size: 20px;
  top: 0;
  left: 0;
  z-index: 999;
  width: 100%;
}
</style>