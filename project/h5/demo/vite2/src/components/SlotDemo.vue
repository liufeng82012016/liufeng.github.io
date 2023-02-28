<template>
  <p>before slot</p>
  <slot name="header"></slot>
  <slot>placeholder</slot>
  <slot name="footer"></slot>
  <slot name="scope" :info="infomation"></slot>
  <p>after slot</p>
</template>

<script>
export default {
  name: "SlotDemo",
  data() {
    return {
      infomation: {
        phone: "133",
        address: 'si'
      }
    }
  }
}
</script>