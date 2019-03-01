<template>
  <div class="back_img">
    <img src="../../static/GIF/sanjiu.gif">
    <!--<video src="../../static/GIF/QQ视频20190123163248.mp4"/>-->
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
  export default {
    components: {},
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },
    methods: {
      toggleSideBar() {
        this.sidebar = !this.sidebar;
        console.log("sidebar current value: " + this.sidebar);
      },
    },
  }
</script>
