<template>
  <div>
<!--
    {{msg}}
    <group title="禁用内置验证及显示成功或者错误样式">
      <x-input title="禁用验证" placeholder="I'm placeholder" novalidate :icon-type="iconType" :show-clear="false"
               @on-blur="onBlur" placeholder-align="right"></x-input>
    </group>
    <div style="padding:15px;">
      <x-button @click.native="iconType = 'success'" type="primary"> set success</x-button>
      <x-button @click.native="iconType = 'error'" type="primary"> set error</x-button>
      <x-button @click.native="iconType = ''" type="primary"> set empty</x-button>
    </div>
-->
    <x-img src="https://i.loli.net/2019/04/18/5cb86a01ded5d.jpg"></x-img>
  </div>
</template>

<script>

  import {XInput, Group, XButton, XImg} from 'vux'

  export default {
    components: {
      XInput, Group, XButton, XImg
    },
    data() {
      return {
        msg: 'Hello World!',
        iconType: ''
      }
    },
    methods: {
      onBlur(val) {
        console.log('on blur', val)
      }
    }
  }
</script>
