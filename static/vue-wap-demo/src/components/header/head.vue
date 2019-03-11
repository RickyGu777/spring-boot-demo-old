<template>
  <div>
    <!--<el-menu-->
    <!--:default-active="activeIndex"-->
    <!--class="el-menu-demo"-->
    <!--mode="horizontal"-->
    <!--@select="handleSelect"-->
    <!--background-color="#545c64"-->
    <!--text-color="#fff"-->
    <!--active-text-color="#ffd04b">-->
    <!--<el-menu-item index="1" @click="handleSidebar('')">Home</el-menu-item>-->
    <!--<el-submenu index="2">-->
    <!--<template slot="title">Joke</template>-->
    <!--<el-menu-item index="2-1" @click="handleSidebar('')">Upload Joke</el-menu-item>-->
    <!--<el-menu-item index="2-2" @click="handleSidebar('List')">Joke List</el-menu-item>-->
    <!--<el-menu-item index="2-3">选项3</el-menu-item>-->
    <!--<el-submenu index="2-4">-->
    <!--<template slot="title">选项4</template>-->
    <!--<el-menu-item index="2-4-1">选项1</el-menu-item>-->
    <!--<el-menu-item index="2-4-2">选项2</el-menu-item>-->
    <!--<el-menu-item index="2-4-3">选项3</el-menu-item>-->
    <!--</el-submenu>-->
    <!--</el-submenu>-->
    <!--<el-menu-item index="3" disabled>消息中心</el-menu-item>-->
    <!--<el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>-->
    <!--</el-menu>-->
    <!--<el-menu-->
    <!--:default-active="activeIndex2"-->
    <!--class="el-menu-demo"-->
    <!--mode="horizontal"-->
    <!--@select="handleSelect"-->
    <!--background-color="#545c64"-->
    <!--text-color="#fff"-->
    <!--active-text-color="#ffd04b">-->
    <!--<el-menu-item v-for="firstMenu in menuObject" :key="firstMenu.uuid" index="firstMenu.index" @click="handleSidebar(firstMenu.menuPath)">-->
    <!--{{firstMenu.menuName}}-->
    <!--</el-menu-item>-->
    <!--</el-menu>-->
    <!--<div v-for="firstMenu in menuObject" :key="firstMenu.uuid">-->
    <!--{{firstMenu.menuName}}-->
    <!--<div v-for="secondMenu in firstMenu.nextMenuList" :key="secondMenu.uuid">-->
    <!--{{secondMenu.menuName}}-->
    <!--<div v-for="thirdMenu in secondMenu.nextMenuList" :key="thirdMenu.uuid">-->
    <!--{{thirdMenu.manuName}}-->
    <!--</div>-->
    <!--</div>-->
    <!--</div>-->
    <head-bar v-for="menu in menuList" :key="menu.uuid" item="menu"></head-bar>
  </div>
</template>

<script>
  import {getMenuList} from '@/request/api';// 导入我们的api接口
  import headBar from './headBar'

  export default {
    components: {headBar},
    props: {
      menuList: {
        type: Array,
        required: true
      }
    },
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',
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
          this.$router.push({path: '/' + path});
        }
      },
      async getMenu() {
        let newVar = await getMenuList();
        this.menuList = newVar.data;
        console.log(this.menuList);
      }
    }
  }
</script>
