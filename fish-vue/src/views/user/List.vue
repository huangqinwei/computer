<template>
  <div>
  <el-table
    :data="users"
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
      label="用户名"
      width="180">
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>姓名: {{ scope.row.userName }}</p>
          <p>住址: {{ scope.row.password }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.userName }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column
      label="密码"
      width="180">
      <template slot-scope="scope">
        <i class="el-icon-time"></i>
        <span style="margin-left: 10px">{{ scope.row.password }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="管理员"
      width="180">
      <template slot-scope="scope">
        <el-switch
          @change="updateRole(scope.row)"
          v-model="scope.row.role">
        </el-switch>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="danger"
          @click="deleteUserById(scope.row)">删除</el-button>
        <el-button
          size="mini"
          type="warning"
          @click="resetPassword(scope.row.id)">重置密码</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>
</template>
<script>
  export default {
    name:"UserList",
    beforeRouteEnter:(to,from,next)=>{
      console.log("进入路由之前");
      next(vm=>{
        vm.getData();
      });
    },
    data() {
        return {
          users: [
          ]
        }
    },
    methods: {
      getData(){
        this.$axios.get('views/user/list')
          .then(res=>{
            console.log(res)
            res.data.forEach((user)=>{
              user.role===0? user.role=true:user.role=false
            })
            this.users=res.data
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
        return this.$confirm(`此操作将重置 ${id}的密码, 是否继续?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
      },
      deleteUserById(user){
        this.openDelConfirm(user.id).then(res=>{
          this.$axios.post('views/user/delete/'+user.id)
            .then(res=>{
              console.log(user)
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
      updateRole(user){
        var role=''
        user.role ===true?role=0:role=1
        this.$axios.post('views/user/updateUserRole/'+user.id+'/'+role)
          .then(res=>{
            this.$message({
              type: 'success',
              message: '修改成功!'
            })
          })
          .catch(err=>{
            console.log(err)
          })
      },
      resetPassword(id){
        this.openDelConfirm2(id).then(res=>{
          this.$axios.post('views/user/resetPassword/'+id)
            .then(res=>{
              this.$message({
                type: 'success',
                message: '重置成功!'
              })
              this.getData()
            })
            .catch(err=>{
              console.log(err)
            })
        })
      }
    }
  }
</script>
