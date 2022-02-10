<template>
    <div style="margin-left: 520px">
      <h1>欢迎进入中国东海鱼类信息管理系统</h1>
      <el-carousel height="400px" style="width: 500px" indicator-position="outside">
        <el-carousel-item v-for="image in images" :key="image" style="text-align: center;">
          <el-image style="max-width: 100%; max-height: 100%; vertical-align: middle;" :src=image></el-image>
        </el-carousel-item>
      </el-carousel>
    </div>
</template>
<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
.el-image{
  width: 100%;
  height: 100%;
  object-fit:cover;

}
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>

<script>
  export default {
    name: "Index",
    data() {
      return {
        images:[
          "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=613110271,2153136237&fm=11&gp=0.jpg",
          "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2177108819,334543984&fm=26&gp=0.jpg",
          "https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/38dbb6fd5266d0161b0c60d8972bd40735fa352c.jpg"
        ],
        username:'',
        isCollapse: true
      };
    },

  }
</script>
