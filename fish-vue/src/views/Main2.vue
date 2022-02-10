<template>
  <div>
    <div>
      <img :src="imgSrc"width="100%" height="100%" alt="" />
    </div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item style="margin-right: 28px;font-size: 28px;color: #000;font-family:KaiTi; font-weight: bolder; font-weight: 800" >中国东海鱼类信息管理系统</el-menu-item>
      <el-menu-item index="1" @click="toIndex">首页</el-menu-item>
      <!--<el-menu-item index="2">概况</el-menu-item>-->
      <el-menu-item index="3" @click="toListFish">鱼类</el-menu-item>
      <!--<el-menu-item index="4">东海</el-menu-item>-->
      <el-menu-item index="5" @click="toListArticle">文章</el-menu-item>
      <el-menu-item index="6" @click="toSearchFish">搜索</el-menu-item>
      <el-menu-item index="7" @click="toUserInformation">用户</el-menu-item>
      <el-menu-item index="8" @click="toProvideFish">鱼类信息提供</el-menu-item>
        <el-dropdown style>
          <el-avatar style="text-align: right;margin-top: 12px":src="user.imagePath"></el-avatar>
          <!--<span>{{user.userName}}</span>-->
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="toLogin">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
    </el-menu>
    <el-main>
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script>
    export default {
      name: "Main2",
      beforeRouteEnter(to, from, next) {
        // 添加背景色 margin:0;padding:0是为了解决vue四周有白边的问题
        document.querySelector('body').setAttribute('style', 'margin:0;padding:0;background-color:rgb(222,239,255);');
        next(vm=>{
          vm.getData();
          vm.toIndex()
        });
      },
      data() {
        return {
          user:{},
          imgSrc:require('../assets/images/1.png'),
          username:'',
          activeIndex: '1',
          activeIndex2: '1'
        };
      },
      methods: {
        getData(){
          // this.username=localStorage.getItem("userName");
          this.$axios.get('views/user/'+localStorage.getItem("userName"))
            .then(res=>{
              console.log(res)
              this.user=res.data
            })
            .catch(err=>{
              console.log(err)
            })
        },
        handleSelect(key, keyPath) {
          console.log(key, keyPath);
        },
        toLogin() {
          if (this.$route.path !== '/login') {
            localStorage.removeItem('token');
            this.$router.push({
              path: '/login'
            });
          }
        },
        toIndex(){
          if (this.$route.path !== '/index2') {
            this.$router.push({
              path: '/index2'
            });
          }
        },
        toListFish(){
          if (this.$route.path !== '/listFish') {
            this.$router.push({
              path: '/listFish'
            });
          }
        },
        toListArticle(){
          if (this.$route.path !== '/listArtice') {
            this.$router.push({
              path: '/listArticle'
            });
          }
        },
        toUserInformation(){
          if (this.$route.path !== '/userInformation') {
            this.$router.push({
              path: '/userInformation'
            });
          }
        },
        toProvideFish(){
          if (this.$route.path !== '/provideFish') {
            this.$router.push({
              path: '/provideFish'
            });
          }
        },
        toSearchFish(){
          if (this.$route.path !== '/searchFish') {
            this.$router.push({
              path: '/searchFish'
            });
          }
        }
      }
    }
</script>

<style scoped>
  .el-menu-item{
    margin-right: 28px;
    font-weight: 400;
    font-size: 14px;
  }
</style>
