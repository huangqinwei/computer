<template>
    <div style="text-align: center">
      <div class="box">
        <el-form  label-width="80px" >
          <h1>修改密码</h1>
          <el-form-item label="用户名">
            <el-input  v-model="user.userName" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="原始密码:">
            <el-input type="password" v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item label="修改密码:">
            <el-input type="password" v-model="modifyPassword"></el-input>
          </el-form-item>
          <el-form-item label="确认密码:">
            <el-input type="password" v-model="confirmPassword"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="newPassword(user,modifyPassword,confirmPassword)">确认</el-button>
            <el-button @click="toFishList">返回</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="box">
        <el-form>
          <h1>修改头像</h1>
          <el-form-item label="修改头像:">
            <el-input cd v-model="user.imagePath"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updateImagePath(user)">确认</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
</template>

<script>
    export default {
      beforeRouteEnter:(to,from,next)=>{
        console.log("进入路由之前");
        next(vm=>{
          vm.getUserName();
        });
      },
        name: "UserInformation",
      data() {
        return {
          user:{
            imagePath:'',
            userName: '',
            password: ''
          },
          modifyPassword: '',
          confirmPassword: ''
        }
      },
      methods:{
        getUserName(){
          this.user.userName=localStorage.getItem("userName");
        },
        toLogin(){
          if (this.$route.path !== '/ligon') {
            localStorage.removeItem('token');
            this.$router.push({
              path: '/login'
            });
          }
        },
        updateImagePath(user){
          this.$axios.post('views/user/imagePath',user)
            .then(res=>{
              console.log(res)
              if(res.data){
                this.$message({
                  type: 'success',
                  message: '头像修改成功!'
                })
              }else{
                this.$message({
                  type: 'error',
                  message: '头像修改失败!'
                })
              }
            })
            .catch(err=>{
              console.log(err)
            })
        },
        newPassword(user,password1,password2){
          if(password1===password2){
            this.$axios.post('views/user/modifyPassword/'+password1,user)
              .then(res=>{
                console.log(res.data)
                if(res.data===true){
                  this.$message({
                    type: 'success',
                    message: '修改成功，请重新登录!'
                  })
                  this.toLogin()
                }else {
                  this.$message({
                    type: 'error',
                    message: '密码错误!'
                  })
                }
              })
              .catch(err=>{
                console.log(err)
              })
          }else{
            this.$message({
              type: 'error',
              message: '两次输入密码不一致!'
            })
          }
        }
      }
    }
</script>

<style scoped>
  .el-input{
    width: 600px;
  }
  .el-form{
    margin-left: 20px;
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
