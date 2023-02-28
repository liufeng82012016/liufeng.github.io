<template>
  <table class="table">
    <thead>
    <tr>
      <slot name="tb-header"></slot>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(book,index) in bookList" :key="book.id">
      <slot name="tb-body" :book="book" :index="index"></slot>
    </tr>
    </tbody>
  </table>
</template>
<script>

export default {
  name: "MyTable",
  props: {
    bookList: {
      type: Array,
      required: true,
      default: []
    }
  }
}
</script>
<style lang="less">
.table {
  text-align: left;
  //font-size: 14px;

  span {
    margin-right: 10px;
  }
}

</style>