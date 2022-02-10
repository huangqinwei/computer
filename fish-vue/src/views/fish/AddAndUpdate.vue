<template>
  <div>
    <el-form  label-width="80px" >
      <el-form-item label="id:">
        <el-input v-model="fish.id" :disabled="true" placeholder="请输入类别"></el-input>
      </el-form-item>
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
        <el-button type="primary" @click="addAndUpdateFishById(fish)">确认</el-button>
        <el-button @click="toFishList">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "AddAndUpdate",
      beforeRouteEnter:(to,from,next)=>{
        console.log("进入路由之前");
        next(vm=>{
          vm.getData();
        });
      },
      data() {
        return {
          fileList: [
            {name: 'food.jpeg', url: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2062164223,3783917881&fm=26&gp=0.jpg'},
            {name: '1.png', url: '../../assets/images/1.png'}
            ],
          fish:{
            features:'',
            fishName:'',
            time:null,
            imagePath:null,
            imageKey:null,
            id:'',
            content:'',
            dialogImageUrl:''
          }
        }
      },
      methods:{
        handleRemove(file, fileList) {
          console.log(file, fileList);
        },
        handlePreview(file) {
          console.log(file);
        },
        getData(){
          this.fish.features=this.$route.params.features,
          this.fish.fishName=this.$route.params.fishName,
          this.fish.id=this.$route.params.id,
          this.fish.content=this.$route.params.content,
          this.fish.imagePath=this.$route.params.imagePath
        },
        toFishList(){
          if (this.$route.path !== '/fish/list') {
            this.$router.push({
              path: '/fish/list'
            });
          }
        },
        addAndUpdateFishById(fish){
          if (this.$route.params.id !== null && this.$route.params.id !== undefined) {
            this.$axios.post('views/fish/updata',fish)
              .then(res=>{
                console.log(this.$route.params.id)
                this.$message({
                  type: 'success',
                  message: '修改成功!'
                })
                this.toFishList()
              })
              .catch(err=>{
                console.log(fish)
              })
          }else{
            this.$axios.post('views/fish/save',fish)
              .then(res=>{
                console.log(fish)
                this.$message({
                  type: 'success',
                  message: '添加成功!'
                })
                this.toFishList()
              })
              .catch(err=>{
                console.log(fish)
              })
          }
        }
      }
    }
</script>

<style scoped>

</style>
