<template>
  <div class="top">
    <div class='nav'>
      <ul class='navHader'>
        <li @click="current='Sunyi'" :class="{active:current=='Sunyi'}">childVueOne</li>
        <li @click="current='Caiwu'" :class="{active:current=='Caiwu'}">childVueTwo</li>
      </ul>
      <Menus></Menus>
    </div>
    <keep-alive>
      <component :is="current" :callbackdata="callbackdata"></component>
    </keep-alive>
  </div>
</template>

<script>
  import childVueOne from './childvueone'
  import childVueTwo from './childvuetwo'

  export default {
    name: "Father",
    data() {
      return {
        current: 'childVueOne',
        navs: [
          'childVueOne',
          'childVueTwo'
        ]
      }
    },
    mounted() {
      this.toggleSwitch(this.current)
    },
    components: {
      childVueOne,
      childVueTwo
    },
    methods: {
      toggleSwitch(parameter) {
        const self = this;
        let this_toggle = parameter;
        switch (this_toggle) {
          case 'childVueOne':
            self.toggleDatainit('childVueOne');
            break;
          case 'childVueTwo':
            self.toggleDatainit('childVueOne');
            break;
        }
      },
      toggleDatainit(talbel_url) {
        const self = this;
        self.callbackdata = talbel_url;
        // self.$http.get('getInitTable/init/' + talbel_url).then(res => {
        //   if (res.data.status == 2000) {
        //     console.log(res.data);
        //     self.callbackdata = res;
        //   }
        // }).then(error => {
        // });
      },
    },
  }
</script>
