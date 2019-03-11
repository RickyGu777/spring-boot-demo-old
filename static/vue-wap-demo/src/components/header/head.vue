<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1" @click="handleSidebar('')">Home</el-menu-item>
      <el-submenu index="2">
        <template slot="title">Joke</template>
        <el-menu-item index="2-1" @click="handleSidebar('')">Upload Joke</el-menu-item>
        <el-menu-item index="2-2" @click="handleSidebar('List')">Joke List</el-menu-item>
        <el-menu-item index="2-3">选项3</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>
          <el-menu-item index="2-4-2">选项2</el-menu-item>
          <el-menu-item index="2-4-3">选项3</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3" disabled>消息中心</el-menu-item>
      <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
    </el-menu>
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item v-for="menu in menuList" :key="menu.uuid" index="menu.index" @click="handleSidebar(menu.menuPath)">
        {{menu.menuName}}
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  import {getMenuList} from '@/request/api';// 导入我们的api接口
  export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',
        menuList: []
      };
    },
    mounted: function () {
      getMenuList();
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      handleSidebar(name) {
        this.$router.push({path: '/' + name});
      },
      async getMenuList() {
        let newVar = await getMenuList();
        console.log(newVar);
        menuList = newVar;
      }
    }
  }
</script>
