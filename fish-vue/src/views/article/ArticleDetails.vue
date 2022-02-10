<template>
  <div>
    <div ><h3 >{{article.article.title}}</h3>
    </div>
    <div style="border:1px solid rgb(225, 228, 230);width: 1460px;height: 355px;background-color: #FFF">
      <div style=" text-align:center;float:left; border-right:1px solid rgb(225, 228, 230); width:10%;height: 100%;">
        <el-image
          style="width: 100px; height: 100px;margin-top: 15%"
          :src="article.user.imagePath"></el-image>
        <div style= "margin-top: 15%"><span>{{article.user.userName}}</span></div>
      </div>
      <div style="float:right;width:89%;height: 100%;">
        <span>{{article.article.content}}</span>
      </div>
    </div>
    <div v-for="articlecomment in comments" style="border:1px solid rgb(225, 228, 230);width: 1460px;height: 355px;background-color: #FFF">
      <div style=" text-align:center;float:left; border-right:1px solid rgb(225, 228, 230); width:10%;height: 100%;">
        <el-image
          style="width: 100px; height: 100px;margin-top: 15%"
          :src="articlecomment.user.imagePath"></el-image>
        <div style= "margin-top: 15%"><span>{{articlecomment.user.userName}}</span></div>
      </div>
      <div style="float:right;width:89%;height: 100%; ">
        <div style="min-height: 200px;width: 99%;">
          <span>{{articlecomment.articleComment.content}}</span>
        </div>
        <div style="background-color: rgb(247, 248, 250);min-height: 107px;width: 99%;  position: relative;">
          <span v-for="commentComment in articlecomment.commentComment">{{commentComment.content}}</span>
          <a >回复</a>
          <el-input v-model="comment2.content"></el-input>
          <el-button type="primary" @click="commentComment()">发表</el-button>
        </div>
      </div>
    </div>
    <el-row>
      <el-button type="success" icon="el-icon-thumb" circle @click="favoriteArticle(article.article.id)"></el-button>
      <el-button type="warning" icon="el-icon-star-off" circle @click="collectionArticle(article.article.id)"></el-button>
    </el-row>
    <div>
      <el-form label-width="80px" >
        <span style="margin-bottom: 20px">发表评论</span>
        <el-input
          type="textarea"
          :rows="8"
          placeholder="请输入评论"
          v-model="comment.content">
        </el-input>
        <el-button type="primary" @click="commentArticle(article.article.id)">发表</el-button>
      </el-form>
    </div>
  </div>
</template>
<script>
    export default {
        name: "ArticleDetails",
      beforeRouteEnter:(to,from,next)=>{
        console.log("进入路由之前");
        next(vm=>{
          vm.getData();
          vm.getComment ();
          vm.comment.userName=localStorage.getItem("userName");
        });
      },
      data(){
          return{
            url:'https://gss0.baidu.com/7Ls0a8Sm2Q5IlBGlnYG/sys/portraith/item/tb.1.29b002a.pEjRLqXP_7NJtMd6ocyelw',
            article:{
            },
            comment:{
            },
            comment2:{
            },
            comments:{
              // articleComment:{
              // },
              // commentComment:[]
            }
            // comments:[
            // ],
            // comment:{
            //   content:""
            // },
            // comments2:[
            // ],
            // comment2:{
            //   content:""
            // }
          }
      },
      methods: {
        getData() {
          this.$axios.get('views/article/findById/'+this.$route.params.id)
            .then(res=>{
              console.log(res)
              this.article=res.data
            })
            .catch(err=>{
              console.log(err)
            })
        },
        // getArticleComment(id) {
        //   this.$axios.get('views/comment/getComment/'+this.$route.params.id)
        //     .then(res=>{
        //       console.log(res)
        //       this.comments=res.data
        //     })
        //     .catch(err=>{
        //       console.log(err)
        //     })
        // },
        getComment() {
          this.$axios.get('views/comment/getComment/'+this.$route.params.id)
            .then(res=>{
              console.log(res.data)
              this.comments=res.data
            })
            .catch(err=>{
              console.log(err)
            })
        },
        favoriteArticle(id) {
          this.$axios.post('views/favorite/'+id+'/'+localStorage.getItem("userName"))
            .then(res=>{
              console.log(res)
              if(res.data==="success"){
                this.$message({
                  type: 'success',
                  message: '点赞成功!'
                })
              }else{
                this.$message({
                  type: 'success',
                  message: '取消点赞成功!'
                })
              }
            })
            .catch(err=>{
              console.log(err)
            })
        },
        collectionArticle(id) {
          this.$axios.post('views/collection/'+id)
            .then(res=>{
              console.log(res)
              this.$message({
                type: 'success',
                message: '收藏成功!'
              })
            })
            .catch(err=>{
              console.log(err)
            })
        },
        commentArticle(id){
          if(this.comment.content!=null&&this.comment.content!=""){
            this.$axios.post('views/comment/'+id,this.comment)
              .then(res=>{
                console.log(res)
                this.$message({
                  type: 'success',
                  message: '发表成功!'
                })
                this.comment.content=""
                this.getComment()
              })
              .catch(err=>{
                console.log(err)
              })
          }else{
            this.$message({
              type: 'error',
              message: '请输入评论!'
            })
          }
        },
        commentComment(id){
          if(this.comment2.content!=null&&this.comment2.content!=""){
            this.$axios.post('views/comment/comment/'+id,this.comment2)
              .then(res=>{
                console.log(res)
                this.$message({
                  type: 'success',
                  message: '发表成功!'
                })
                this.comment.content=""
                this.getComment()
              })
              .catch(err=>{
                console.log(err)
              })
          }else{
            this.$message({
              type: 'error',
              message: '请输入评论!'
            })
          }
        }
      }
    }
</script>

<style scoped>
  span{
    width:480px;
    height:24px;
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
  }
  .el-input{
    margin-bottom: 20px;
  }
  .el-button{
    margin-top: 20px;
  }
</style>
