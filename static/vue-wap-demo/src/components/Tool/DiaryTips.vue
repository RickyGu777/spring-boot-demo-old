<template>
  <div>
    <el-tag v-for="(item,index) in tips" :key="index" :type="item.type" @click="changeType(item)">
      {{item.name}}
    </el-tag>
  </div>
</template>

<script>
  import {selectDiaryTips} from '@/request/api';// 导入我们的api接口

  export default {
    data() {
      return {
        tips: []
      };
    },
    created: function () {
      this.getTps();
    },
    methods: {
      async getTps() {
        let newVar = await selectDiaryTips();
        if (newVar.code == 0) {
          this.tips = newVar.data;
        }/* else {
          this.$message.error('Get Diary Tips Error:' + newVar.msg);
        }*/
      },
      changeType(data){
        if (data.type != "success") {
          data.type = "success";
        } else {
          data.type = "info";
        }
      }
    }
  }
</script>

<style scoped>
  .el-tag + .el-tag {
    margin-left: 10px;
  }
</style>
