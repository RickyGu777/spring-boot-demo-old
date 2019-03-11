<template>
  <div id="app">
    <head-top></head-top>
    <el-container>
      <router-view/>
    </el-container>
  </div>
</template>

<script>
  import headTop from './components/header/head'

  export default {
    name: 'App',
    components: {headTop}
  }
</script>
