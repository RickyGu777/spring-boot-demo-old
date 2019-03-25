<template>
  <div>
    {{ uuid }}
  </div>
</template>

<script>
  import {createUUID} from '@/request/api';// 导入我们的api接口
  export default {
    name: "GetUUID",
    data() {
      return {
        uuid: ''
      }
    },
    created: function () {
      this.createUUID();
    },
    methods: {
      async createUUID() {
        console.log(123123);
        let newVar = await createUUID();
        this.uuid = newVar.data;
      }
    }
  }
</script>

<style scoped>

</style>
