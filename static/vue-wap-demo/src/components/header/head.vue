<template>
  <div>
    <el-radio-group style="margin-bottom: 20px;">
    </el-radio-group>
    <el-menu :default-active="default_active"
             class="el-menu-vertical-demo"
             :collapse="isCollapse"
             @open="handleOpen"
             @close="handleClose"
             v-for="(menu,index) in menuObject" :key="index">
      <el-submenu v-if="menu.nextMenuList"
                  :index="menu.index">
        <template slot="title">
          {{menu.menuName}}
          <span slot="title">{{menu.menuName}}</span>
        </template>
        <el-menu-item-group>
          <span slot="title">{{menu.menuName}}</span>
          <el-menu-item v-for="(nextMenu,nextIndex) in menu.nextMenuList"
                        :key="nextIndex"
                        :index="nextMenu.index" @click="handleSidebar(nextMenu.menuPath)">
            {{nextMenu.menuName}}
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item v-if="!menu.nextMenuList"
                    :index="menu.index" @click="handleSidebar(menu.menuPath)">
        {{menu.menuName}}
        <span slot="title">{{menu.menuName}}</span>
      </el-menu-item>
    </el-menu>
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
