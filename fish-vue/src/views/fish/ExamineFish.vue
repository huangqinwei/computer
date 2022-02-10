<template>
  <div>
    <el-table
      :data="fishs"
      style="width: 100%">
      <el-table-column
        label="名称"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.fishName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="特征"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.features }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="内容"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="图片"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.imagePath }}</span>
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
            @click="deleteFishById(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
  export default {
    name:"ExamineFish",
    beforeRouteEnter:(to,from,next)=>{
      console.log("进入路由之前");
      next(vm=>{
        vm.getData();
      });
    },
    data() {
      return {
        search:'',
        fishs: [
        ]
      }
    },
    methods: {
      getData(){
        this.$axios.get('views/fish/examineFish')
          .then(res=>{
            console.log(res)
            this.fishs=res.data
          })
          .catch(err=>{
            console.log(err)
          })
      },
      deleteFishById(fish){
        this.openDelConfirm(fish.id).then(res=>{
          this.$axios.post('views/fish/delete',fish)
            .then(res=>{
              console.log(fish)
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.getData()
            })
            .catch(err=>{
              console.log(err)
            })
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
        return this.$confirm(`此操作将删除 ${id}, 是否继续?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
      },
      adopt(fish){
        this.openDelConfirm2(fish.id).then(res=> {
          this.$axios.post('views/fish/adopt/', fish)
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
      searchFish(search){
        if(search===""||search===null){
          this.getData()
        }else{
          this.$axios.get('views/fish/search/'+search)
            .then(res=>{
              console.log(res)
              this.fishs=res.data
            })
            .catch(err=>{
              console.log(err)
            })
        }
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
