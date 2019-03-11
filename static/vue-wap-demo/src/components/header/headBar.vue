<template>
  <div>
    {{item}}
  </div>
</template>

<script>
  export default {
    name: "headBar",
    props: {
      item: {
        type: Object,
        required: true
      }
    }
  }
</script>

<style scoped>

</style>
