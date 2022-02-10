<template>
  <div style="text-align: center">
    <div class="box">
      <h1>向系统提供鱼类信息</h1>
      <el-form  label-width="80px" style="margin-top: 20px" >
        <el-form-item label="名称:">
          <el-input v-model="fish.fishName" placeholder="请输入名称"></el-input>
        </el-form-item>
        <el-form-item label="内容:">
          <el-input v-model="fish.content" placeholder="请输入内容"></el-input>
        </el-form-item>
        <el-form-item label="特征:">
          <el-input v-model="fish.features" placeholder="请输入特征"></el-input>
        </el-form-item>
        <el-form-item label="图片:">
          <el-input v-model="fish.imagePath" placeholder="请输入图片地址"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="provideFish(fish)">确认</el-button>
          <el-button>返回</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
    export default {
        name: "ProvideFish",
      data(){
          return{
            fish:{}
          }
      },
      methods:{
          provideFish(fish){
            this.$axios.post('views/fish/provideFish',fish)
              .then(res=>{
                console.log(res)
                if(res.data){
                  this.$message({
                    type: 'success',
                    message: '提交成功，等待管理员审核!'
                  })
                  this.fish=''
                }else{
                  this.$message({
                    type: 'error',
                    message: '提交失败!'
                  })
                }
              })
              .catch(err=>{
                console.log(err)
              })
          }
      }
    }
</script>

<style scoped>
.el-input{
  width: 600px;
}
.box{
  display: inline-block;
  border-radius: 4px;
  list-style-type:none;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  width: 800px;
  max-height: 500px;
  margin: 20px;
  background-color: hsla(0,0%,100%,.8);
  box-sizing: border-box;
  border-radius: 1.2em;
}
</style>
