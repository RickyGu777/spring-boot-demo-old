<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'app'
  }
</script>

<style lang="less">
  @import '~vux/src/styles/reset.less';
  @import '~vux/src/styles/close.less';
  @import "colorui/main.css";
  @import "colorui/icon.css";

  body {
    background-color: white;
  }


</style>
