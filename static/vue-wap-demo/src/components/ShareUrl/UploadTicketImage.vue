<template>
  <div>
    {{ QRImage }}
    <el-upload
      action="/api/UploadImage/QRCode"
      list-type="picture-card"
      :on-preview="pictureCardPreview"
      :on-remove="removeImage">
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="VisibleImage">
      <img width="100%" :src="ImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>
  import {QRCode} from '@/request/api';

  export default {
    name: "UploadTicketImage",
    data() {
      return {
        QRImage: '',
        ImageUrl: '',
        VisibleImage: false
      };
    },
    removeImage(file, fileList) {
    },
    pictureCardPreview(file) {
      this.ImageUrl = file.url;
      this.VisibleImage = true;
    }
  }
</script>
