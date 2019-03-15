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
        current: 'Sunyi',
        navs: [
          'childVueOne',
          'childVueTwo'
        ]
      }
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
          case 'Sunyi':
            self.toggleDatainit('pl');
            break;
          case 'Caiwu':
            self.toggleDatainit('bs');
            break;
          case 'Jingying':
            self.toggleDatainit('t1');
            break;
        }
      },
      toggleDatainit(talbel_url) {
        const self = this;
        self.$http.get('getInitTable/init/' + talbel_url).then(res => {
          if (res.data.status == 2000) {
            console.log(res.data);
            self.callbackdata = res;
          }
        }).then(error => {
        });
      },
    },
  }
</script>
