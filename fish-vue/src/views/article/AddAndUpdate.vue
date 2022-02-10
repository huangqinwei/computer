<template>
  <div>
    <el-form label-width="80px">
      <el-form-item label="id:">
        <el-input v-model="article.id" :disabled="true" placeholder="请输入类别"></el-input>
      </el-form-item>
      <el-form-item label="标题:">
        <el-input v-model="article.title" placeholder="请输入标题"></el-input>
      </el-form-item>
      <el-form-item label="内容:">
        <el-input
          type="textarea"
          :rows="8"
          placeholder="请输入内容"
          v-model="article.content">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addArticle(article)">确认</el-button>
        <el-button @click="toArticleList">返回</el-button>
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
          article:{
            title:'',
            content:'',
            id:''
          }
        }
      },
      methods:{
        getData(){
          this.article.id=this.$route.params.id,
          this.article.title=this.$route.params.title,
          this.article.content=this.$route.params.content
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
        },
        toArticleList() {
          if (this.$route.path !== '/article/list/') {
            this.$router.push({
              path: '/article/list/'
            });
          }
        },
        addArticle(article){
          if (this.$route.params.id !== null && this.$route.params.id !== undefined) {
            this.$axios.post('views/article/updata',article)
              .then(res=>{
                console.log(this.$route.params.id)
                this.$message({
                  type: 'success',
                  message: '修改成功!'
                })
                this.toArticleList()
              })
              .catch(err=>{
                console.log(fish)
              })
          }else{
            this.$axios.post('views/article/adminSave',article)
              .then(res=>{
                console.log(article)
                this.$message({
                  type: 'success',
                  message: '添加成功!'
                })
                this.toArticleList()
              })
              .catch(err=>{
                console.log(article)
              })
          }
        }
      }
    }
</script>

<style scoped>

</style>
