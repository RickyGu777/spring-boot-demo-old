<template>
  <div class="top">
    <div class='nav'>
      <ul class='navHader'>
        <li @click="current='childVueOne'" :class="{active:current=='childVueOne'}">childVueOne</li>
        <li @click="current='childVueTwo'" :class="{active:current=='childVueTwo'}">childVueTwo</li>
        <li @click="current='diaLog'" :class="{active:current=='diaLog'}">diaLog</li>
      </ul>
    </div>
    <component :is="current"></component>
  </div>
</template>

<script>
  import childVueOne from './childvueone'
  import childVueTwo from './childvuetwo'
  import diaLog from '../Dialog'

  export default {
    name: "Father",
    data() {
      return {
        current: 'childVueOne',
        navs: [
          'childVueOne',
          'childVueTwo',
          'diaLog'
        ]
      }
    },
    mounted() {
      this.toggleSwitch(this.current)
    },
    components: {
      childVueOne,
      childVueTwo,
      diaLog
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
          case 'diaLog':
            self.toggleDatainit('diaLog');
            break;
        }
      },
      toggleDatainit(talbel_url) {
        const self = this;
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
