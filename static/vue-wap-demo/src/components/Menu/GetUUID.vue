<template>
  <div>
    {{ uuid }} <el-button @click="createUUID">Refresh UUID</el-button>
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
        let newVar = await createUUID();
        this.uuid = newVar.data;
      }
    }
  }
</script>

<style scoped>

</style>
