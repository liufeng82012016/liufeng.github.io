<template>
  <button type="button" class=" goBack" @click="goBack">后退</button>
  <h3>用户详情</h3>
  <div>{{ id }}</div>
</template>
<script>
export default {
  name: "UserDetail",
  props: ['id'],
  methods: {
    goBack() {
      this.$router.go(-1);
    }
  }
}
</script>
<style>
.goBack {
  position: absolute;
  left: 5px;
  top: 5px;
  padding: 1px;
  font-size: 12px;
}
</style>