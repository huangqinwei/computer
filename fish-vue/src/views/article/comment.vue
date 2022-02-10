<template>
  <div>
    <el-table
      :data="comments"
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
        label="父级id"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.pid }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="父级类型"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.ownerType }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="内容"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px;">{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="deleteCommentById(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "Comment",
    beforeRouteEnter:(to,from,next)=>{
      console.log("进入路由之前");
      next(vm=>{
        vm.getData();
      });
    },
    data() {
      return {
        comments:{
        }
      }
    },
    methods: {
      getData() {
        this.$axios.get('views/comment/list/')
          .then(res => {
            console.log(res)
            res.data.forEach((comment)=>{
              comment.ownerType==='article'? comment.ownerType='文章':comment.ownerType='评论'
            })
            this.comments = res.data
          })
          .catch(err => {
            console.log(err)
          })

      },
      adopt(article){
        this.openDelConfirm(article.id).then(res=> {
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
      deleteCommentById(comment) {
        this.openDelConfirm(comment.id).then(res => {
          this.$axios.post('views/comment/delete', comment)
            .then(res => {
              console.log(comment)
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
