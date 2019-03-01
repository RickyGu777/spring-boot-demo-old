<template>
  <div>
    <!--<img src="../../static/GIF/sanjiu.gif">-->
    <!--<video src="../../static/GIF/QQ视频20190123163248.mp4"/>-->
    <h1>{{ msg }}</h1>
    <quillEditor v-model="msg"></quillEditor>
    <!--<quill-editor ref="myTextEditor"-->
                  <!--v-model="msg"-->
                  <!--:config="editorOption"-->
                  <!--@blur="onEditorBlur($event)"-->
                  <!--@focus="onEditorFocus($event)"-->
                  <!--@ready="onEditorReady($event)">-->
    <!--</quill-editor>-->
  </div>
</template>

<script>
  import {quillEditor} from 'vue-quill-editor'

  export default {
    components: {
      quillEditor
    },
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
