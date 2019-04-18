<template>
  <div>
    <el-button @click="routerTo(diary)">Modify Diary</el-button>
    <el-button @click="deleteDiary(diary)" type="danger">Delete Diary</el-button>
    <div v-html="diary.text">
    </div>
    <el-tag v-for="(item,index) in diary.tipsRelations" :key="index">
      {{item.tipsName}}
    </el-tag>
  </div>
</template>

<script>
  import {selectDiaryByUUID,deleteDiaryByUUID} from '@/request/api';// 导入我们的api接口

  export default {
    name: "DiaryDetail",
    data() {
      return {
        diary: {},
      };
    },
    created() {
      this.getRouterData()
    },
    methods: {
      async getRouterData() {
        this.diary.uuid = this.$route.params.data;
        let newVar = await selectDiaryByUUID(this.diary);
        this.diary = newVar.data;
      },
      routerTo(data) {
        this.$router.push({
          name: `Diary`,
          params: {
            data: data
          }
        });
      },
      async deleteDiary(data) {
        let newVar = await deleteDiaryByUUID(data);
        if (newVar.code == 0) {
          this.$router.push({path: '/'});
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
