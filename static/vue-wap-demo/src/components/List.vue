<template>
  <div>
    <button @click="selectJokeList">selectJokeList</button>
    <li v-for="joke in jokeList">
      <div v-html="joke"></div>
    </li>
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
        console.log(newVar);
        this.jokeList = newVar;
      }
    }
  }
</script>

<style scoped>

</style>
