<template>
  <h1 class="title">{{ msg }}</h1>
  <!--  动态绑定class-->
  <span class="green" :class="isGreen?'green':'red'">vv</span>
  <button @click="isGreen=!isGreen">颜色</button>
  <!--  多个class-->
  <span :class="[isGreen?'green':'red',isBig?'big':'small']">double</span>
  <button @click="isBig=!isBig">大小</button>
  <!--  以对象的属性绑定class-->
  <span :class="classObj">object</span>
  <button @click="classObj.italic=!classObj.italic">倾斜</button>
  <button @click="classObj.weight=!classObj.weight">粗细</button>

  <!--  以对象绑定内联style-->
  <div :style="{color: styleObj.color,fontSize: styleObj.fontSize+'px'}">styleObj</div>
  <button @click="styleObj.fontSize=styleObj.fontSize+1">+1</button>
  <button @click="styleObj.fontSize=styleObj.fontSize-1">-1</button>

</template>

<script>
export default {
  name: 'CustomComp2',
  props: {
    msg: String,
  },
  data() {
    return {
      // msg: "hello，comp2"
      isGreen: true,
      isBig: true,
      classObj: {
        italic: false,
        weight: false,
      },
      styleObj: {
        color: 'red',
        fontSize: 30
      }
    }
  },
}
</script>

<style lang="css">
.green {
  font-weight: 200;
  color: #0dcaf0;
}

.red {
  color: red;
  font-weight: 400;
}

.small {
  font-size: 12px;
}

.big {
  font-size: 18px;
}

.italic {
  font-style: italic;
}

.weight {
  font-weight: 500;
}
</style>
