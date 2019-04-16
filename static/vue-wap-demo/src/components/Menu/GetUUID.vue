<template>
  <div>
    {{ uuid }} <el-button @click="createUUID">Refresh UUID</el-button>
    <br>
    <el-row>
      <el-button>默认按钮</el-button>
      <el-button type="primary">主要按钮</el-button>
      <el-button type="success">成功按钮</el-button>
      <el-button type="info">信息按钮</el-button>
      <el-button type="warning">警告按钮</el-button>
      <el-button type="danger">危险按钮</el-button>
    </el-row>
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
