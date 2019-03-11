<template>
  <div class="quill-wrap">
    <head-top></head-top>
    <!--<img src="../../static/GIF/sanjiu.gif">-->
    <h1 class="title">{{ joke.title }}</h1>
    <input v-model="joke.title">
    <quill-editor
      v-model="joke.joke"
      ref="myQuillEditor"
      :options="editorOption"
    >
    </quill-editor>
    <el-button @click="uploadJoke">上传</el-button>
    <el-row>
      <el-button>默认按钮</el-button>
      <el-button type="primary">主要按钮</el-button>
      <el-button type="success">成功按钮</el-button>
      <el-button type="info">信息按钮</el-button>
      <el-button type="warning">警告按钮</el-button>
      <el-button type="danger">危险按钮</el-button>
    </el-row>
  </div>
</template>
<script>
  import {Quill, quillEditor} from 'vue-quill-editor'
  import {container, ImageExtend, QuillWatch} from 'quill-image-extend-module'
  import {uploadJoke} from '@/request/api';// 导入我们的api接口
  import headTop from './header/head'

  Quill.register('modules/ImageExtend', ImageExtend)
  export default {
    components: {quillEditor,headTop},
    data() {
      return {
        joke: {
          title: '',
          joke: ''
        },
        // joke: '',
        // title: '',
        // 富文本框参数设置
        editorOption: {
          modules: {
            ImageExtend: {
              loading: true,  // 可选参数 是否显示上传进度和提示语
              name: 'img',  // 图片参数名
              size: 3,  // 可选参数 图片大小，单位为M，1M = 1024kb
              action: '/api/UploadImage/upload',  // 服务器地址, 如果action为空，则采用base64插入图片
              // response 为一个函数用来获取服务器返回的具体图片地址
              // 例如服务器返回{code: 200; data:{ url: 'baidu.com'}}
              // 则 return res.data.url
              response: (res) => {
                // console.log(res.img);
                if (res.code == 0) {
                  return res.img;
                } else {
                  return "/static/GIF/sanjiu.gif";
                }
              },
              headers: (xhr) => {
              },  // 可选参数 设置请求头部
              start: () => {
              },  // 可选参数 自定义开始上传触发事件
              end: () => {
              },  // 可选参数 自定义上传结束触发的事件，无论成功或者失败
              error: (res) => {
              },  // 可选参数 自定义网络错误触发的事件
              change: (xhr, formData) => {
              } // 可选参数 选择图片触发，也可用来设置头部，但比headers多了一个参数，可设置formData
            },
            toolbar: {
              container: container,  // container为工具栏，此次引入了全部工具栏，也可自行配置
              handlers: {
                'image': function () {  // 劫持原来的图片点击按钮事件
                  QuillWatch.emit(this.quill.id)
                }
              }
            }
          }
        }
      }
    },
    methods: {
      async uploadJoke() {
        let newVar = await uploadJoke(this.joke);
      }
    }
  }
</script>
