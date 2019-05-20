<template>
  <div>
    <el-row :gutter="20">
    <div style="margin-top: 15px;">
      <el-col :span="8">
      <el-input placeholder="请输入内容" v-model="data.url">
        <el-button slot="append" @click="commit()">Commit</el-button>
      </el-input>
      </el-col>
      <el-col :span="4">
      <el-button @click="downloadAhri()">Download</el-button>
      </el-col>
    </div>
    </el-row>
  </div>
</template>

<script>
  import {insertAhri, downloadAhri} from '@/request/api';// 导入我们的api接口
  export default {
    name: "Ahri",
    data() {
      return {
        data: {url: ''},
        errorTitle: false,
        successAlert: false
      };
    },
    methods: {
      async commit() {
        let newVar = await insertAhri(this.data);
        if (newVar.code == 0) {
          this.$message({
            message: 'Insert Ahri Url Success!',
            type: 'success'
          });
        } else {
          this.$message.error('Insert Ahri Url Error:' + newVar.msg);
        }
      },
      downloadAhri() {
        downloadAhri();
        this.data.url = '';
      }
    }
  }
</script>
