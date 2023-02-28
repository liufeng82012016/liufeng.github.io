<template>
  <h3>权限管理</h3>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">姓名</th>
      <th scope="col">年龄</th>
      <th scope="col">头衔</th>
      <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(user,index) in userList" :key="user.id">
      <th>{{ user.id }}</th>
      <td>{{ user.name }}</td>
      <td>{{ user.age }}</td>
      <td>{{ user.dept }}</td>
      <td>
        <span class="badge text-bg-danger" @click="remove(user.id)">详情</span>
      </td>
    </tr>
    </tbody>
  </table>
</template>
<script>
export default {
  name: "Permission",
  data() {
    return {
      userList: [
        {
          id: 1,
          name: "李斯",
          age: 12,
          dept: "政治家"
        }
      ]
    }
  }
}
</script>
<style>

</style>