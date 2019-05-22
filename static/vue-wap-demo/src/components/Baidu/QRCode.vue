<!--
<template>
  <div>
    <el-upload
      :multiple="multipleValue"
      action="/api/Image/QRCode"
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
        dialogVisible: false,
        multipleValue: true
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
-->

<template>
  <div>
    <el-upload
      class="upload-demo"
      action="/api/Image/QRCode"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-success="handleAvatarSuccess"
      :file-list="fileList"
      :multiple="multipleValue"
      list-type="picture">
      <el-button size="small" type="primary">点击上传</el-button>
      <!--<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
    </el-upload>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        fileList: [],
        multipleValue:true
      };
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleAvatarSuccess(response, file, fileList) {
        if (file.response.code === 1) {
          file.status = "error";
          file.name = file.name + "\t[" + file.response.msg + "]";
        }
      }
    }
  }
</script>
