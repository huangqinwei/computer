<template>
  <div>
    <div class="front">
      <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
        <h3 class="login-title">欢迎登录</h3>
        <el-form-item label="账号" prop="username">
          <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
        </el-form-item>
        <el-form-item >
          <el-button class="btn1" type="primary" v-on:click="onSubmit('loginForm')">登录</el-button>
          <el-button type="primary" v-on:click="toRegister">注册</el-button>
        </el-form-item>
      </el-form>
      <el-dialog
        :append-to-body="true"
        title="温馨提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <span>请输入账号和密码</span>
        <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
      </el-dialog>
    </div>
    <div class="background">
      <img :src="imgSrc"width="100%" height="100%" alt="" />
    </div>
  </div>
</template>

<style lang="scss" scoped>
  .btn1{
    margin-right:32px;
  }

  .front{
    z-index:1;
    position: absolute;
    width: 100%;
  }
  .background{
    width:100%;
    height: 100%;
    z-index:-1;
    position: absolute;
  }
  .login-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 180px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
  }

  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
  }
</style>

<script>
  export default {
    name: "Login",
    beforeRouteEnter(to, from, next) {
      // 添加背景色 margin:0;padding:0是为了解决vue四周有白边的问题
      document.querySelector('body').setAttribute('style', 'margin:0;padding:0')
      next()
    },
    beforeRouteLeave(to, from, next) {
      // 去除背景色
      document.querySelector('body').setAttribute('style', '')
      next()
    },
    data() {
      return {
        imgSrc:require('../assets/images/bj.png'),
        form: {
          username: '',
          password: '',
          islogin:"flase"
        },

        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
        rules: {
          username: [
            {required: true, message: '账号不可为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不可为空', trigger: 'blur'}
          ]
        },

        // 对话框显示和隐藏
        dialogVisible: false
      }
    },
    methods: {
      onSubmit(formName) {
        // 为表单绑定验证功能
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
            this.$axios.post('views/user/login/'+this.form.username+'/'+this.form.password)
              .then(res=>{
                console.log(res)
                if (res.data!==null&&res.data!=="") {
                    localStorage.setItem("userName",this.form.username)
                    localStorage.setItem("token",res.data)
                    this.toMain()
                  this.$message({
                    type: 'success',
                    message: '登录成功!'
                  })
                }else{
                  this.$message({
                    type: 'error',
                    message: '密码错误!'
                  })
                }
              })
              .catch(err=>{
                console.log(err)
                this.$message({
                  type: 'error',
                  message: '用户名不存在!'
                })
              })
          } else {
            this.dialogVisible = true;
            return false;
          }
        });
      },
      toRegister(){
        if (this.$route.path !== '/register') {
          this.$router.push({
            path: '/register'
          });
        }
      },
      handleClose(){
      },
      toMain(){
        this.$axios.get('views/user/findUserByName/'+this.form.username)
          .then(res=>{
            console.log(res)
            if(res.data.role===0){
              this.$router.push({
                name: "Main",
                params: {
                  username:this.form.username
                }
              });
            }else{
              this.$router.push({
                name: "Main2",
                params: {
                  username:this.form.username
                }
              });
            }
          })
          .catch(err=>{
            console.log(err)
          })
      },
      findUserByName(userName){
        this.$axios.get('views/user/findUserByName/'+userName)
          .then(res=>{
            console.log(res)
            res.data.role==0?this.role=true:this.role=false
          })
          .catch(err=>{
            console.log(err)
          })
      }
    }
  }
</script>

