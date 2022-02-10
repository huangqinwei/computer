<template>
  <div>
    <div class="front">
      <el-form ref="loginForm" :model="user" :rules="rules" label-width="80px" class="login-box">
        <h3 class="login-title">注册用户</h3>
        <el-form-item label="账号" prop="username">
          <el-input type="text" placeholder="请输入账号" v-model="user.userName"/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" placeholder="请输入密码" v-model="user.password"/>
        </el-form-item>
        <el-form-item label="确认密码" prop="password2">
          <el-input type="password" placeholder="请输入确认密码" v-model="user.password2"/>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input type="text" placeholder="请输入密码" v-model="user.email"/>
        </el-form-item>
        <el-form-item >
          <el-button class="btn1" type="primary" v-on:click="register(user)">注册</el-button>
          <el-button type="primary" v-on:click="toLogin">返回</el-button>
        </el-form-item>
      </el-form>
      <el-dialog
        :append-to-body="true"
        title="温馨提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <span>请输入注册信息</span>
        <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
      </el-dialog>
      <el-dialog
        :append-to-body="true"
        title="温馨提示"
        :visible.sync="dialogVisible2"
        width="30%"
        :before-close="handleClose2">
        <span>密码和确认密码不一致</span>
        <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible2 = false">确 定</el-button>
      </span>
      </el-dialog>
    </div>
    <div class="background">
      <img :src="imgSrc"width="100%" height="100%" alt="" />
    </div>
  </div>
</template>

<script>
    export default {
      name: "Register",
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
          user:{
            userName:"",
            password:"",
            password2:"",
            email:"",
          },
          // 表单验证，需要在 el-form-item 元素中增加 prop 属性
          rules: {
            username: [
              {required: true, message: '账号不可为空', trigger: 'blur'}
            ],
            password: [
              {required: true, message: '密码不可为空', trigger: 'blur'}
            ],
            password2: [
              {required: true, message: '确认密码不可为空', trigger: 'blur'}
            ]
          },

          // 对话框显示和隐藏
          dialogVisible: false,
          dialogVisible2: false
        }
      },
      methods:{
        toLogin(){
          if (this.$route.path !== '/ligon') {
            this.$router.push({
              path: '/login'
            });
          }
        },
        register(user){
          if (user.userName!==""&&user.password!==""&&user.password2!==""){
            if(user.password===user.password2){
              this.$axios.post('views/user/register',user)
                .then(res=>{
                  console.log(this.user)
                  this.$message({
                    type: 'success',
                    message: '注册成功!'
                  })
                  this.toLogin()
                })
                .catch(err=>{
                  console.log(err)
                })
            }else{
              this.dialogVisible2= true;
            }
          }else{
            this.dialogVisible = true;
            console.log(user)
          }
        },
        handleClose(){
        },
        handleClose2(){
        }
      }
    }
</script>

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
