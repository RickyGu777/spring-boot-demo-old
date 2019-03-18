<template>
  <div v-html="diary.text">
  </div>
</template>

<script>
  import {selectByUUID} from '@/request/api';// 导入我们的api接口

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
        let newVar = await selectByUUID(this.diary);
        this.diary = newVar.data;
      }
    }
  }
</script>
