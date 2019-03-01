<template>
  <div class="back_img">
    <!-- head -->
    <!--<head-top @toggleSideBar="toggleSideBar"></head-top>-->
    <!-- sidebar -->
    <!--<sidebar></sidebar>-->
    <!-- masking -->
    <!--<masking></masking>-->
    <!-- footer -->
    <!--<foot-menu :activeIndex="0"></foot-menu>-->
    <!-- main -->
    <img src="../../static/GIF/sanjiu.gif">
    <!--<video src="../../static/GIF/QQ视频20190123163248.mp4"></video>-->
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
  import headTop from './header/head'
  import sidebar from './sidebar/sidebar'
  import masking from './masking/masking'
  import footMenu from '@/components/footer/footer'
  export default {
    components: {
      headTop,
      sidebar,
      masking,
      footMenu
    },
    data () {
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
