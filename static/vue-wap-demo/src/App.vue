<template>
  <div id="app">
<!--
    <el-container>
    <el-aside width="70px">
    <head-top></head-top>
    </el-aside>
    <el-main>
    <router-view/>
    </el-main>
    </el-container>
-->
  </div>
</template>

<script>
  import headTop from './components/header/head'

  export default {
    name: 'App',
    components: {headTop}
  }
</script>
