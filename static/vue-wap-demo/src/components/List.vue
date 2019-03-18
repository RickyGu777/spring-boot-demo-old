<template>
  <div>
    <button @click="selectJokeList">selectJokeList</button>
    <div v-for="data in jokeList" :key="data.uuid">
      <div v-html="data.joke"></div>
    </div>
    <!--<div class="radio">-->
      <!--排序：-->
      <!--<el-radio-group v-model="reverse">-->
        <!--<el-radio :label="true">倒序</el-radio>-->
        <!--<el-radio :label="false">正序</el-radio>-->
      <!--</el-radio-group>-->
    <!--</div>-->

    <el-timeline>
      <el-timeline-item v-for="data in jokeList" :key="data.uuid" :timestamp="data.createDate" placement="top">
        <el-card>
          <h4>{{ data.title }}</h4>
          <p>{{ data.createDate }}</p>
        </el-card>
      </el-timeline-item>
      <!--<el-timeline-item timestamp="2018/4/3" placement="top">-->
        <!--<el-card>-->
          <!--<h4>更新 Github 模板</h4>-->
          <!--<p>王小虎 提交于 2018/4/3 20:46</p>-->
        <!--</el-card>-->
      <!--</el-timeline-item>-->
      <!--<el-timeline-item timestamp="2018/4/2" placement="top">-->
        <!--<el-card>-->
          <!--<h4>更新 Github 模板</h4>-->
          <!--<p>王小虎 提交于 2018/4/2 20:46</p>-->
        <!--</el-card>-->
      <!--</el-timeline-item>-->
    </el-timeline>
  </div>
</template>

<script>
  import {selectJokeList} from '@/request/api';// 导入我们的api接口
  export default {
    name: "List",
    data() {
      return {
        jokeList: [],
        joke: {'page': 1, 'size': 10},
        reverse: true,
        activities: [{
          content: '活动按期开始',
          timestamp: '2018-04-15'
        }, {
          content: '通过审核',
          timestamp: '2018-04-13'
        }, {
          content: '创建成功',
          timestamp: '2018-04-11'
        }]
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
