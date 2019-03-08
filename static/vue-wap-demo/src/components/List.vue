<template>
  <div>
    <button @click="selectJokeList">selectJokeList</button>
    <div v-for="data in jokeList" :key="data.uuid">
      <div v-html="data.joke"></div>
    </div>
  </div>
</template>

<script>
  import {selectJokeList} from '@/request/api';// 导入我们的api接口
  export default {
    name: "List",
    data() {
      return {
        jokeList: [],
        joke: {'page': 1, 'size': 10}
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

<style scoped>

</style>
