<template>
  <div>
    <el-table
      :data="articles"
      style="width: 100%">
      <el-table-column
        label="id"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="标题"
        width="320">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="内容"
        width="360">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px;">{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="adopt(scope.row)">通过</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteArticleById(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "ExamineArticle",
    beforeRouteEnter:(to,from,next)=>{
      console.log("进入路由之前");
      next(vm=>{
        vm.getData();
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
      getData() {
        this.$axios.get('views/article/examinerArticle/')
          .then(res => {
            console.log(res)
            this.articles = res.data
          })
          .catch(err => {
            console.log(err)
          })

      },
      adopt(article){
        this.openDelConfirm2(article.id).then(res=> {
          this.$axios.post('views/article/adopt/', article)
            .then(res => {
              console.log(res)
              this.$message({
                type: 'success',
                message: '审核成功!'
              })
              this.getData()
            })
            .catch(err => {
              console.log(err)
            })
        }).catch(err=>{
          console.log(err)
        })
      },
      openDelConfirm(id) {
        return this.$confirm(`此操作将删除 ${id}, 是否继续?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
      },
      openDelConfirm2(id) {
        return this.$confirm(`此操作将通过 ${id}, 是否继续?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
      },
      deleteArticleById(article) {
        this.openDelConfirm(article.id).then(res => {
          this.$axios.post('views/article/delete', article)
            .then(res => {
              console.log(article)
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.getData()
            })
            .catch(err => {
              console.log(err)
            })
        })
      }
    }
  }
</script>

<style scoped>
  span{
    overflow: hidden;
    text-overflow: ellipsis;
    -o-text-overflow: ellipsis;
    white-space:nowrap;
    height:24px;
  }
</style>
