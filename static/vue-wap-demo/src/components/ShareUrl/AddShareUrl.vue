<template>
  <div>
    <el-container>
      <el-header>
        <el-row v-for="(item,index) in ShareTicketUrlList" :key="index">
          <el-col :span="6">
            <el-input placeholder="请输入标题" v-model="item.title">
            </el-input>
          </el-col>
          <el-col :span="10" :offset="1">
            <el-input placeholder="请输入链接" v-model="item.url">
              <el-button slot="append" @click="commit()">Commit</el-button>
            </el-input>
            {{item.msg}}
          </el-col>
          <el-col :span="1" :offset="1">
            <el-button v-if="index==0" @click="listADD()">Add Item</el-button>
            <el-button v-else @click="listReduce(index)">Reduce This Item</el-button>
          </el-col>
        </el-row>
      </el-header>
    </el-container>
    <el-button @click="ShareTicketUrlInsertList()">Up Url</el-button>
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
            console.log("this.ShareTicketUrlList");
            console.log(this.ShareTicketUrlList[i].url);
            console.log("this.ShareTicketUrlList");
            for (var j = 0; j < oldList.length; j++) {
              console.log("oldList");
              console.log(oldList[j].url);
              console.log(oldList[j].url == this.ShareTicketUrlList[i].url);
              console.log("oldList");
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
      }
    }
  }
</script>
