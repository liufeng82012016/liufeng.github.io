<template>
  <MyTable :bookList="bookList">
    <template #tb-header>
      <th scope="col">#</th>
      <th scope="col">商品名称</th>
      <th scope="col">价格</th>
      <th scope="col">标签</th>
      <th scope="col">操作</th>
    </template>

    <template #tb-body="{book,index}">
      <th scope="row">{{ book.id }}</th>
      <td>{{ book.name }}</td>
      <td>¥{{ book.price }}</td>
      <td>
        <input type="text" v-if="book.inputVisible" @keyup.esc="book.inputVisible=''"
               v-model.trim="book.inputValue" @keyup.enter="addTag(book)" v-focus class="book-input"
               @blur="addTag(book)"/>
        <span class="badge text-bg-primary" v-else @click="book.inputVisible=true">+Tag</span>
        <span v-for="tag in book.tags" class="badge text-bg-warning" :key="tag">{{ tag }}</span>
      </td>
      <td>
        <span class="badge text-bg-danger" @click="remove(book.id)">删除</span>
      </td>
    </template>
  </MyTable>
</template>
<script>
import MyTable from './components/MyTable.vue'

export default {
  name: "App",
  components: {
    MyTable,
  },
  data() {
    return {
      bookList: [
        {
          id: 1,
          name: "西游记",
          price: "55",
          tags: [
            "四大名著", "明朝"
          ]
        }
      ]
    }
  },
  mounted() {
    this.getBookList();
  },
  methods: {
    async getBookList() {
      // const {data: res} = await this.$http('');
      // if (res.code !== 0) {
      //   return console.log('获取图书列表失败')
      // }
      // this.bookList = res.data;
    },
    remove(id) {
      this.bookList = this.bookList.filter(x => x.id !== id);
    },
    addTag(book) {
      console.log(JSON.stringify(book))
      const inputValue = book.inputValue;
      book.inputValue = '';
      book.inputVisible = false;
      if (inputValue === undefined || inputValue.length <= 0) {
        // 可以写成 !inputValue === undefined
        return;
      }
      if (book.tags.indexOf(inputValue) === -1){
        // 查找不存在
        book.tags.push(inputValue);
      }
    }
  },
}
</script>
<style lang="less">
.book-input {
  width: 80px;
  margin-right: 10px;
  height: 30px;
}
</style>