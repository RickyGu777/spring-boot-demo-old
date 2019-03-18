<template>
  <div>
    <el-timeline>
      <el-timeline-item v-for="data in jokeList" :key="data.uuid" :timestamp="data.createDate" placement="top">
        <el-card>
          <h4>{{ data.title }}</h4>
          <p>{{ data.createDate }}</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
  import {selectJokeList} from '@/request/api';// 导入我们的api接口
  export default {
    name: "List",
    created: function () {
      this.selectJokeList();
    },
    data() {
      return {
        jokeList: [],
        joke: {'page': 1, 'size': 10},
      };
    },
    methods: {
      async selectJokeList() {
        let newVar = await selectJokeList(this.joke);
        this.jokeList = newVar.data.list;
      }
    }
  }
</script>
