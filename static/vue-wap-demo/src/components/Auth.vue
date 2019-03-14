<template>
  <div>
    <el-tree
      :data="data"
      :props="defaultProps"
      node-key="uuid"
      show-checkbox
      @check-change="handleCheckChange">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <el-tooltip class="item" effect="dark" v-if="data.url" :content="data.url" placement="right">
          <span>{{ data.authName }}</span>
        </el-tooltip>
        <span v-if="!data.url">{{ data.authName }}</span>
        <span v-if="!data.authList">
          <el-button
            type="text"
            size="mini"
            @click="showDialog(data.authName)">
            Modify Auth Name
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog
      title="Modify Auth Name"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <span>
        <el-input placeholder="Auth Name" v-model="authName">
          <template slot="prepend">Auth Name</template>
        </el-input>
      </span>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {selectAuthTree} from '@/request/api';

  export default {
    name: "Auth",
    data() {
      return {
        dialogVisible: false,
        data: [],
        authName: '',
        defaultProps: {
          children: 'authList',
          label: 'classGroup',
          labels: 'classGroup'
        },
        centerDialogVisible: false
      };
    },
    created: function () {
      this.getAuthTree();
    },
    methods: {
      handleCheckChange(data, checked, indeterminate) {
        data.defaultCheck = checked;
      },
      async getAuthTree() {
        this.data = await selectAuthTree();
      },
      showDialog(data) {
        this.authName = data;
        this.dialogVisible = true;
        data = 123123;
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      }
    }
  };
</script>

<style>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
</style>
