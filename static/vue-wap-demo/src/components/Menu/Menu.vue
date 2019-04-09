<template>
  <div>

  </div>
</template>

<script>
  import {getMenuList} from '@/request/api';// 导入我们的api接口
  export default {
    name: "Menu",
    data() {
      return {
        menuObject: []
      };
    },
    created: function () {
      this.getMenu();
    },
    methods: {
      async getMenu() {
        let newVar = await getMenuList();
        this.menuObject = newVar.data;
      }
    }
  }
</script>

<style scoped>

</style>
