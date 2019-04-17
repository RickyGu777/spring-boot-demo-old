<template>
  <div id="app" class="Fell">
    <el-container>
    <el-aside width="70px">
    <head-top></head-top>
    </el-aside>
    <el-main>
    <router-view/>
    </el-main>
    </el-container>
    <!--<div id="main-wrapper">-->
      <!--<div class="menu visible-md visible-lg">-->
        <!--<ul id="menu-list">-->
          <!--<li class="active home-menu"><a href="#">Home</a></li>-->
          <!--<li class="about-menu"><a href="#">About Us</a></li>-->
          <!--<li class="services-menu"><a href="#">Our Services</a></li>-->
          <!--<li class="testimonial-menu"><a href="#">Testimonials</a></li>-->
          <!--<li class="contact-menu"><a href="#">Contact</a></li>-->
        <!--</ul>-->
      <!--</div>&lt;!&ndash; /.menu &ndash;&gt;-->
    <!--</div>-->
  </div>
</template>

<script>
  import headTop from './components/header/head'

  export default {
    name: 'App',
    components: {headTop}
  }
</script>
