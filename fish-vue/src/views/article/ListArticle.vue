<template>
  <div>
    <el-table
      :data="articles"
      style="width: 100%">
      <el-table-column
        label="鱼类文章"
        prop="date">
        <div slot-scope="scope">
          <h3 style="color: #2d64b3;" @click="toArticleDetails(scope.row)">{{scope.row.title}}</h3>
          <span>{{scope.row.content}}</span>
        </div>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      :page-size="15"
      layout="prev, pager, next"
      :total="size"
      :current-page.sync="currentPage"
      @current-change="getData(currentPage)">
    </el-pagination>
    <el-divider></el-divider>
    <div>
      <el-form label-width="80px">
        <span style="margin-bottom: 20px">发表鱼类文章</span>
        <el-input v-model="article.title"  placeholder="请输入标题"></el-input>
        <el-input
          type="textarea"
          :rows="8"
          placeholder="请输入内容"
          v-model="article.content">
        </el-input>
        <el-button type="primary" @click="addArticle(article)">发布</el-button>
    </el-form>
    </div>
  </div>
</template>


<script>
    export default {
        name: "ListArticle",
      beforeRouteEnter:(to,from,next)=>{
        console.log("进入路由之前");
        next(vm=>{
          vm.getData(1);
          vm.getSize();
        });
      },
      data() {
        return {
          currentPage:1,
          size:0,
          article:{
            title:'',
            content:''
          },
          articles:[]
        }
      },
      methods: {
        getData(articlePage) {
          this.$axios.get('views/article/page/'+articlePage)
            .then(res=>{
              console.log(res)
              this.articles=res.data
            })
            .catch(err=>{
              console.log(err)
            })

        },
        getSize() {
          this.$axios.get('views/article/size')
            .then(res=>{
              console.log(res.data)
              this.size=res.data
            })
            .catch(err=>{
              console.log(err)
            })

        },
        toArticleDetails(params) {
          console.log(params)
          if (this.$route.path !== '/articleDetails/:id') {
            this.$router.push({
              name: 'ArticleDetails',
              params: {
                id:params.id,
                title:params.title,
                content:params.content
                //要一个个传值不然就是传一大串字符串
              }
            });
          }
        },
        addArticle(article){
          this.$axios.post('views/article/save/'+localStorage.getItem("userName"),article)
            .then(res=>{
              console.log(article)
              this.article.title=''
              this.article.content=''
              this.$message({
                type: 'success',
                message: '发布成功,等待管理员审核！'
              })
              this.getData(1)
            })
            .catch(err=>{
              console.log(article)
            })
        }
      }
    }
</script>

<style scoped>
  h3:hover{
    text-decoration:underline
  }
  span{
    overflow: hidden;
    text-overflow: ellipsis;
    -o-text-overflow: ellipsis;
    white-space:nowrap;
    width:480px;
    height:24px;
    display:block;
  }
  .el-pagination{
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .el-input{
    margin-bottom: 20px;
  }
  .el-button{
    margin-top: 20px;
  }
</style>
