<template>
  <div>
    <el-collapse v-model="activeName" accordion @change="handleChange">
      <el-collapse-item :title="item.uploadImg.title" :name="index" v-for="(item,index) in data.info.list"
                        :key="index">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-input :disabled="modi" placeholder="请输入内容" v-model="item.uploadImg.title">
              <el-button slot="append" @click="changeModi()">Modify TiTle</el-button>
              <el-button slot="append" @click="changeModi()">Upload</el-button>
            </el-input>
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
  import {getOCRList} from '@/request/api';

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
      changeModi() {
        this.modi = !this.modi;
        if (this.modi) {
          console.log(this.modi);
          // 准备修改Title属性
        }
      },
      handleChange() {
        this.modi = true;
      }
    }
  }
</script>
