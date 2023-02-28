<template>
  <div class="login-bg">
    <div class="login-container">
      <img src="../../assets/logo.png"/>
      <div class="input-group">
        <span class="input-group-text">账号</span>
        <input type="text" aria-label="Last name" class="form-control" v-model="account">
      </div>
      <div class="input-group">
        <span class="input-group-text">密码</span>
        <input type="password" aria-label="Last name" class="form-control" v-model="password">
      </div>
      <div class="d-grid gap-1">
        <button class="btn btn-primary" type="button" @click="login">登录</button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Login",
  data() {
    return {
      account: "",
      password: ""
    }
  },
  methods: {
    login() {
      if (this.account.length <= 0) {
        return alert("请输入账号");
      }
      if (this.password.length <= 0) {
        return alert("请输入密码");
      }
      if (this.password !== 'admin' || this.account !== 'admin') {
        return alert("账号或密码错误");
      }
      localStorage.setItem("token", "13312345679");
      this.$router.push('/admin');
    }
  }
}
</script>
<style lang="less">
.login-bg {
  width: 100%;
  height: 100%;
  background-color: #15519d;

  .login-container {
    width: 400px;
    height: 300px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    //background-color: orange;
    padding: 100px 20px;

    img {
      position: absolute;
      width: 100px;
      left: 150px;
      top: -50px;
      border-radius: 50%;
      border: 1px solid lightslategray;
    }

    div {
      margin-top: 20px;
    }

    .login {
      background-color: white;
      height: 38px;
      line-height: 38px;
      border-radius: 5px;
    }
  }
}
</style>