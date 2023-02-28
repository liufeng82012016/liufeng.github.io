<template>
  <h3>用户管理</h3>
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
        <span class="badge text-bg-info"><router-link :to="'/admin/users/'+user.id">详情</router-link></span>
        <router-view></router-view>
      </td>
    </tr>
    </tbody>
  </table>
</template>
<script>
export default {
  name: "UserList",
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
  },
  methods: {
    detail(id) {

    }
  }
}
</script>