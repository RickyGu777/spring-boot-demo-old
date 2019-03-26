<template>
  <div>
    <el-collapse v-model="activeName" accordion @change="handleChange">
      <el-collapse-item :title="item.uploadImg.title" :name="index" v-for="(item,index) in data.info.list"
                        :key="index">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-input :disabled="modi" placeholder="请输入内容" v-model="item.uploadImg.title">
              <el-button slot="append" @click="changeModi()" v-if="modi">Modify TiTle</el-button>
              <el-button slot="append" @click="changeModi(item.uploadImg)" v-if="!modi">Upload</el-button>
            </el-input>
          </el-col>
          <el-col :span="6" :offset="6">
            <el-button @click="deleteBaiduOCR(item)">Delete This Image</el-button>
          </el-col>
        </el-row>
        <div v-for="(words,wordsIndex) in item.wordsResult" :key="wordsIndex">
          {{ words.words }}
        </div>
        <img :src="item.uploadImg.responseUrl"/>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
  import {getOCRList, updateImageTitle, deleteBaiduOCR} from '@/request/api';

  export default {
    name: "OCRList",
    created: function () {
      this.getOCRList();
    },
    data() {
      return {
        data: {
          page: 1,
          size: 10,
          info: {}
        },
        activeName: '1',
        modi: true
      };
    },
    methods: {
      async getOCRList() {
        let newVar = await getOCRList(this.data);
        this.data.info = newVar.data;
      },
      async changeModi(data) {
        this.modi = !this.modi;
        if (this.modi) {
          let newVar = await updateImageTitle(data);
          if (newVar.code == 0) {
            this.$message({
              message: 'Modify Title Success!',
              type: 'success'
            });
          } else {
            this.$message.error('Modify Title Error:' + newVar.msg);
          }
        }
      },
      handleChange() {
        this.modi = true;
      },
      async deleteBaiduOCR(data) {
        let newVar = await deleteBaiduOCR(data);
        if (newVar.code == 0) {
          this.$message({
            message: 'Delete Image Success!',
            type: 'success'
          });
        } else {
          this.$message.error('Delete Image Error:' + newVar.msg);
        }
      }
    }
  }
</script>
