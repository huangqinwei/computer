<template>
  <div>
    <el-button type="primary"@click="toAddAndUpdate" plain>新增</el-button>
    <el-select style="width: 120px;margin-left: 20px" v-model="value" placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
    <el-input v-model="search" style="width: 200px;margin-left: 20px"></el-input>
    <el-button type="primary" @click="searchArticle(value,search)" plain>搜索</el-button>
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
            @click="toAddAndUpdate(scope.row)">编辑</el-button>
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
        name: "List",
      beforeRouteEnter:(to,from,next)=>{
        console.log("进入路由之前");
        next(vm=>{
          vm.getData();
        });
      },
      data() {
        return {
          options: [{
            value: '标题',
            label: '标题'
          }, {
            value: '内容',
            label: '内容'
          }],
          value: '',
          search:'',
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
          this.$axios.get('views/article/list/')
            .then(res => {
              console.log(res)
              this.articles = res.data
            })
            .catch(err => {
              console.log(err)
            })

        },
        toAddAndUpdate(params) {
          if (this.$route.path !== '/addAndUpdateArticle/:id') {
            this.$router.push({
              name: 'AddAndUpdateArticle',
              params: {
                title:params.title,
                id:params.id,
                content:params.content,
                //要一个个传值不然就是传一大串字符串
              }
            });
          }
        },
        openDelConfirm(id) {
          return this.$confirm(`此操作将删除 ${id}, 是否继续?`, '提示', {
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
        },
        searchArticle(value,search){
          if(search===""||search===null){
            this.getData()
          }else{
            console.log(value)
            switch (value) {
              case "标题":this.searchArticleByTitle(search);break;
              default:this.searchArticleByContent(search);break;
            }
          }
        },
        searchArticleByTitle(search){
          this.$axios.get('views/article/searchTitle/'+search)
            .then(res=>{
              console.log(res)
              this.articles=res.data
            })
            .catch(err=>{
              console.log(err)
            })
        },
        searchArticleByContent(search){
          this.$axios.get('views/article/searchContent/'+search)
            .then(res=>{
              console.log(res)
              this.articles=res.data
            })
            .catch(err=>{
              console.log(err)
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
