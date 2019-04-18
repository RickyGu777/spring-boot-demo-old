<template>
  <div>
    <el-tag v-for="(item,index) in tips" :key="index" :type="item.type" @click="changeType(item)">
      {{item.name}}
    </el-tag>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-input
          placeholder="请输入内容"
          v-model="input"
          clearable>
          <el-button slot="append" @click="addTips(input)">Add Tips</el-button>
        </el-input>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {selectDiaryTips, insertTips} from '@/request/api';// 导入我们的api接口

  export default {
    data() {
      return {
        tips: [],
        input: ""
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
      },
      async addTips(data) {
        if (data == "") {
          this.$message.error("Tips Can`t Be Null");
        }
        let temp = {"type": "info", "name": data, "status": "1"};
        let newVar = await insertTips(temp);
        if (newVar.code == 0) {
          this.input = "";
          newVar.data.tipsName = newVar.data.name;
          newVar.data.tipsUUID = newVar.data.uuid;
          if (!this.tips) {
            this.tips = [];
          }
          this.tips.push(newVar.data);
        } else {
          this.$message.error(newVar.msg);
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
