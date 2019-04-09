<template>
  <div>
    <el-container>
      <el-header>
        <el-row v-for="(item,index) in ShareTicketUrlList" :key="index">
          <el-col :span="4">
            <el-input placeholder="请输入标题" v-model="item.title">
            </el-input>
          </el-col>
          <el-col :span="9" :offset="1">
            <el-input placeholder="请输入链接" v-model="item.url">
              <el-button slot="append" @click="commit()">Commit</el-button>
            </el-input>
          </el-col>
          <el-col :span="3" :offset="1">
            <el-button v-if="index==0" @click="listADD()">Add Item</el-button>
            <el-button v-else @click="listReduce(index)">Reduce This Item</el-button>
          </el-col>
          <el-col :span="2" :offset="1">
            {{item.msg}}
          </el-col>
        </el-row>
        <el-button @click="ShareTicketUrlInsertList()">Up Url</el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="refresh">Clean</el-button>
      </el-header>
    </el-container>
  </div>
</template>

<script>
  import {ShareTicketUrlInsertList} from '@/request/api';
  import {getShareTicketUrl} from '@/util/ShareTicketUrlUtil';

  export default {
    name: "AddShareUrl",
    data() {
      return {
        ShareTicketUrlList: [
          {
            title: "",
            url: "",
            msg: ""
          }
        ]
      }
    },
    methods: {
      async ShareTicketUrlInsertList() {
        let newVar = await ShareTicketUrlInsertList(this.ShareTicketUrlList);
        if (newVar.code == 0) {
          this.$message({
            message: '添加优惠券成功!',
            type: 'success'
          });
        } else {
          this.$message.error('添加优惠券失败:' + newVar.msg);
        }
        let oldList = newVar.data;
        if (oldList) {
          for (var i = 0; i < this.ShareTicketUrlList.length; i++) {
            for (var j = 0; j < oldList.length; j++) {
              if (oldList[j].url == this.ShareTicketUrlList[i].url) {
                this.ShareTicketUrlList[i].msg = "该链接已存在";
              }
            }
          }
        }
        console.log(this.ShareTicketUrlList)
      },
      listADD() {
        var str = {
          ShareTicketUrl: {
            title: "",
            url: ""
          }
        }
        this.ShareTicketUrlList.push(str);
      },
      listReduce(index) {
        this.ShareTicketUrlList.splice(index, 1);
      },
      refresh() {
        this.ShareTicketUrlList = [
          {
            title: "",
            url: "",
            msg: ""
          }
        ]
      }
    }
  }
</script>
