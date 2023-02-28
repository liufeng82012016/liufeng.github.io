<template>
  <div>
    <div class="admin-header">
      <img src="../../assets/logo.png"/>
      <div>
        <span>后台管理系统</span>
        <button type="button" class="btn btn-warning" @click="outlogin">退出登录</button>
      </div>
    </div>
    <div class="admin-body">
      <div class="items">
        <ul>
          <li>
            <router-link to="/admin/users">用户管理</router-link>
          </li>
          <li>
            <router-link to="/admin/permission">权限管理</router-link>
          </li>
        </ul>
      </div>
      <div class="content">
        <!-- 路由占位符所在的位置，就是组件渲染的位置-->
        <router-view></router-view>
      </div>
    </div>

  </div>

</template>

<script>
import UserList from './UserList.vue';
import Permission from './Permission.vue';

export default {
  name: "Admin",
  components: [
    Permission, UserList
  ],
  methods: {
    outlogin() {
      localStorage.removeItem("token");
      this.$router.push("/login");
    }
  }
}

</script>
<style lang="less" scoped>
.admin-header {
  height: 100px;
  padding: 10px 20px;
  display: flex;
  align-items: center;
  border-bottom: 1px solid lightslategray;

  img {
    width: 80px;
    border-radius: 50%;
    border: 1px solid lightslategray;
  }

  div {
    padding-left: 20px;
    flex: 1;
    justify-content: space-between;
    display: flex;
  }
}

.admin-body {
  display: flex;

  .items {
    width: 200px;
    text-align: left;
    //background-color: #8491a2;
    font-weight: 700;
    border-right: 1px solid lightslategray;


    ul {
      padding: 0;
    }

    li {
      list-style: none;
      height: 50px;
      line-height: 50px;
      text-align: center;

      a {
        text-decoration: none;
        color: black;
      }
    }

    .router-link-active {
      background-color: lightgoldenrodyellow;
    }
  }

  .content {
    //background-color: lightblue;
    min-height: 200px;
    flex: 1;
    position: relative;
  }
}
</style>