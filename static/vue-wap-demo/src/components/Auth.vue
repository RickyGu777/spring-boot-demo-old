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
            @click="showDialog(data)">
            Modify Auth Name
          </el-button>
        </span>
      </span>
    </el-tree>
    <dia-log :input-value="dialogInfo" @introduce="introduceSelf"></dia-log>
  </div>
</template>

<script>
  import {selectAuthTree} from '@/request/api';
  import diaLog from './Dialog'

  export default
    {
      name: "Auth",
      components:
        {
          diaLog
        },
      data() {
        return {
          data: [],
          dialogInfo: {},
          authName: '',
          defaultProps: {
            children: 'authList',
            label: 'classGroup'
          }
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
          this.dialogInfo = data;
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
