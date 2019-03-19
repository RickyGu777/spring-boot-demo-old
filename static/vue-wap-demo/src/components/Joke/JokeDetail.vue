<template>
  <div v-html="joke.joke">
  </div>
</template>

<script>
  import {selectJokeByUUID} from '@/request/api';// 导入我们的api接口

  export default {
    name: "JokeDetail",
    data() {
      return {
        joke: {},
      };
    },
    created() {
      this.getRouterData()
    },
    methods: {
      async getRouterData() {
        this.joke.uuid = this.$route.params.data;
        let newVar = await selectJokeByUUID(this.joke);
        this.joke = newVar.data;
      }
    }
  }
</script>
