<template>
  <div class="quill-wrap">
    <el-header>
      <el-row :gutter="20">
        <el-col :span="12" :offset="6">
          <el-input placeholder="Input Diary Title" v-model="diary.title">
            <template slot="prepend">Title</template>
          </el-input>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <quill-editor
        v-model="diary.text"
        ref="myQuillEditor"
        :options="editorOption">
      </quill-editor>
      <el-button @click="addDiary" v-if="!buttonShow">上传</el-button>
      <el-button @click="updateDiaryByUUID" v-if="buttonShow">更新</el-button>
    </el-main>
  </div>
</template>

<script>
  import {Quill, quillEditor} from 'vue-quill-editor'
  import {container, ImageExtend, QuillWatch} from 'quill-image-extend-module'
  import {addDiary,selectDiaryByUUID,updateDiaryByUUID} from '@/request/api';// 导入我们的api接口
  import {formatDate} from '@/util/date';

  export default {
    name: "Diary",
    data() {
      return {
        diary: {
          title: '',
          text: ''
        },
        buttonShow: this.$route.params.data,
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
    created: function () {
      this.isModifyDiary();
    },
    methods: {
      async addDiary() {
        let newVar = await addDiary(this.diary);
      },
      async updateDiaryByUUID() {
        let newVar = await updateDiaryByUUID(this.diary);
        if (newVar.code == 0) {
          this.$router.push({path: '/'});
        }
      },
      async isModifyDiary() {
        if (this.$route.params.data) {
          this.diary.uuid = this.$route.params.data;
          let newVar = await selectDiaryByUUID(this.diary);
          this.diary = newVar.data;
        } else {
          this.diary.text = '<p><br></p></p><p class="ql-align-right">创建于 ' + formatDate(new Date(), 'yyyy-MM-dd hh:mm') + '</p>';
        }
      }
    }
  }
</script>

<style>
  .quill-wrap {
    width: 100%;
  }
</style>
