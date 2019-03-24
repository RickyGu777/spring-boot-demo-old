<template>
  <div>
    <div style="margin-top: 15px;">
      <el-input placeholder="请输入内容" v-model="data.url">
        <el-button slot="append" @click="commit()">Commit</el-button>
      </el-input>
      <el-button @click="downloadAhri()">Download</el-button>
    </div>
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
