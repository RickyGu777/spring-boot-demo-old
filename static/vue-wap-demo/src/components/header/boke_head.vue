<template>
  <div>
    boke head
  </div>
</template>

<script>
  import {getMenuList} from '@/request/api';// 导入我们的api接口

  export default {
    data() {
      return {
        default_active: "1",
        isCollapse: true,
        menuObject: {}
      };
    },
    created: function () {
      this.getMenu();
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      handleSidebar(path) {
        if (path) {
          this.$router.push({path: path});
        } else {
          this.$router.push({path: '/'});
        }
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      async getMenu() {
        let newVar = await getMenuList();
        this.menuObject = newVar.data;
      }
    }
  }
</script>
