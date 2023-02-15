<template>
  <span>father.name:{{ name }} father.author:{{ name }} father.author:{{ name }}</span>
</template>
<script>
export default {
  name: 'FatherComp',
  data() {
    return {
      name: "西游记",
      author: "吴承恩",
      press: "北京出版社",
    }
  },
  provide: {
    name: this.name,
    author: this.author,
    press: this.press,
  }
}
</script>
<style>

</style>