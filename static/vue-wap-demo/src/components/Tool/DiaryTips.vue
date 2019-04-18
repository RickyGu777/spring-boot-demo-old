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
    props: {
      tipsRelations: {
        type: Array
      }
    },
    created: function () {
      this.getTps();
    },
    methods: {
      async getTps() {
        let newVar = await selectDiaryTips();
        if (newVar.code == 0) {
          for (let dataKey in newVar.data) {
            newVar.data[dataKey].tipsUUID = newVar.data[dataKey].uuid;
            newVar.data[dataKey].tipsName = newVar.data[dataKey].name;
            if (this.tipsRelations) {
              for (let tipsRelationsKey in this.tipsRelations) {
                if (this.tipsRelations[tipsRelationsKey].tipsUUID == newVar.data[dataKey].tipsUUID) {
                  newVar.data[dataKey].type = "success";
                }
              }
            }
          }
          this.tips = newVar.data;
        }/* else {
          this.$message.error('Get Diary Tips Error:' + newVar.msg);
        }*/
      },
      changeType(data) {
        if (data.type != "success") {
          data.type = "success";
        } else {
          data.type = "info";
        }
        this.$emit("addTips", {data});
      }
    }
  }
</script>

<style scoped>
  .el-tag + .el-tag {
    margin-left: 10px;
  }
</style>
