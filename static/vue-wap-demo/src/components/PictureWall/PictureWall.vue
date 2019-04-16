<template>
  <div>
    <vue-waterfall-easy :imgsArr="imgsArr" @scrollReachBottom="getData">
    </vue-waterfall-easy>
  </div>
</template>

<script>
  import {getImageWall} from '@/request/api';// 导入我们的api接口
  import vueWaterfallEasy from 'vue-waterfall-easy'

  export default {
    name: "PictureWall",
    components: {
      vueWaterfallEasy
    },
    data() {
      return {
        imgsArr: [],
        group: 0, // request param
        page: {
          page: 0,
          size: 10
        }
      }
    },
    methods: {
      async getData(data) {
        let newVar = await getImageWall(data);
        if (newVar.code == 0) {
          newVar.data.list.forEach(item => {
            console.log(item);
            item.src = item.responseUrl;
            item.link = item.responseUrl;
            item.info = item.responseUrl;
          });
          this.imgsArr = newVar.data.list;
          this.imgsArr.type = Array;
          this.imgsArr.required = true;
        }
      },
    },
    created() {
      this.getData(this.page)
    }
  }
</script>
