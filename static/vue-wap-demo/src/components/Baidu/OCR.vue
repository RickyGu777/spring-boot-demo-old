<template>
  <div>
    <el-upload
      action="/api/Baidu/OCR"
      list-type="picture-card"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove">
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "Word",
    data() {
      return {
        AccessToken: '',
        dialogImageUrl: '',
        dialogVisible: false
      };
    },
    methods: {
      handleRemove(file, fileList) {
        console.log("handleRemove");
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        console.log("handlePictureCardPreview");
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      }
    }
  }
</script>
