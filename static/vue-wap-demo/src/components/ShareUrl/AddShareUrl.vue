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
          </el-col>
          <el-col :span="1" :offset="1">
            <el-button v-if="index==0" @click="listADD()">Add Item</el-button>
            <el-button v-else @click="listADD()">Reduce This Item</el-button>
          </el-col>
        </el-row>
      </el-header>
    </el-container>
  </div>
</template>

<script>
  import {ShareTicketUrlInsert} from '@/request/api';
  import {getShareTicketUrl} from '@/util/ShareTicketUrlUtil';
  export default {
    name: "AddShareUrl",
    data() {
      return {
        ShareTicketUrlList: [
          {
            ShareTicketUrl: {
              title: "",
              url: "",
              index: 0
            }
          }
        ],
        item: {
          title: "",
          url: "",
          index: 0
        }
      }
    },
    methods: {
      async ShareTicketUrlInsert() {
        let newVar = await ShareTicketUrlInsert(this.ShareTicketUrl);
        if (newVar.code == 0) {
          this.$message({
            message: '添加优惠券成功!',
            type: 'success'
          });
        } else {
          this.$message.error('添加优惠券失败:' + newVar.msg);
        }
      },
      listADD() {
        let shareTicketUrl = getShareTicketUrl(this.ShareTicketUrlList.length);
        console.log(shareTicketUrl);
        this.ShareTicketUrlList.push(shareTicketUrl);
        console.log(this.ShareTicketUrlList);
      }
    }
  }
</script>
