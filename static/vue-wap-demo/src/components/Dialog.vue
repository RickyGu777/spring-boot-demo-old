<template>
  <div>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisibleValue"
      width="30%"
      :before-close="handleClose">
      <span>
        <el-input placeholder="Auth Name" v-model="inputValue.authName"></el-input>
      </span>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisibleValue = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisibleValue = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "Dialog",
    data() {
      return {
        dialogVisible: true
      }
    },
    props: {'inputValue': {type: Object, required: false}},
    methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      noticeGroup(name, age) {
        dialogVisibleValue = false;
        this.$emit('introduce', {
          'inputValue': inputValue
        })
      }
    }
  }
</script>
