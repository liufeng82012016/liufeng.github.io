<template>
  <input type="text" v-focus v-color="'red'"/>
</template>
<script>
export default {
  name: "InstructDemo",
  // 私有指令
  directives: {
    focus: {
      mounted(e) {
        e.focus();
      }
    },
    color: {
      // 获取指令参数
      mounted(e, binding) {
        console.log("binding.value:" + binding.value)
        e.style.color = binding.value;
      }
    }
  }
}
</script>