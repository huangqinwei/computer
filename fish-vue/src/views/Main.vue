<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="220px" style="background-color: rgb(238, 241, 246)">
      <el-menu >
        <el-menu-item index="1" @click="toIndex">
          <i class="el-icon-s-home"></i>
          <span slot="title">首页</span>
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-message"></i>鱼类信息管理</template>
          <template slot="title"></template>
          <el-menu-item index="1-1" @click="toFishList">
            <template slot="title">鱼类信息</template>
          </el-menu-item>
          <el-menu-item index="1-2" @click="toExamineFish">鱼类审核</el-menu-item>
          <!--<el-menu-item index="1-4" @click="toDetails">鱼类详情</el-menu-item>-->
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-menu"></i>鱼类文章发布</template>
          <el-menu-item index="2-1" @click="toArticleList">文章管理</el-menu-item>
          <el-menu-item index="2-2" @click="toComment">评论管理</el-menu-item>
          <el-menu-item index="2-3" @click="toExamineArticle">文章审核</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title"><i class="el-icon-setting"></i>用户管理</template>
          <el-menu-item index="4-1" @click="toUserList">用户列表</el-menu-item>
          <!--<el-menu-item index="4-2" @click="toProfile">用户信息</el-menu-item>-->
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <span>{{user.userName}}</span>
      <el-dropdown>
        <el-avatar style="margin-top: 12px":size="size" :src="user.imagePath"></el-avatar>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看</el-dropdown-item>
          <el-dropdown-item @click.native="toLogin">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>鱼类信息管理系统</span>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
  </el-container>
</template>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
  html, body, #app, .el-container {

    /*统一设置高度为100%*/
    height: 100%;
  }
</style>

<script>
  import Index from "./Index";
  export default {
    name:"Main",
    beforeRouteEnter:(to,from,next)=>{
      document.querySelector('body').setAttribute('style', 'background:rgb(244,244,244)');
      document.querySelector('body').removeAttribute('style');
      console.log("进入路由之前");
      next(vm=>{
        vm.getData();
        vm.toIndex();
      });
    },
    data() {
      return {
        user:{},
        username:'aa',
        // circleUrl: "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1004953359,2131419137&fm=11&gp=0.jpg",
        size:"medium"
      }
    },
    methods:{
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
      toIndex(){
        if (this.$route.path !== '/index') {
          this.$router.push({
            path: '/index'
          });
        }
      },
      toUserList(){
        if (this.$route.path !== '/user/list') {
          this.$router.push({
            path: '/user/list'
          });
        }
      },
      toFishList(){
        if (this.$route.path !== '/fish/list') {
          this.$router.push({
            path: '/fish/list'
          });
        }
      },
      toProfile() {
        if (this.$route.path !== '/user/profile') {
          this.$router.push({
            path: '/user/profile'
          });
        }
      },
      toLogin() {
        if (this.$route.path !== '/login') {
          localStorage.removeItem('token');
          this.$router.push({
            path: '/login'
          });
        }
      },
      toDetails() {
        if (this.$route.path !== '/details') {
          this.$router.push({
            path: '/details'
          });
        }
      },
      toArticleList() {
        if (this.$route.path !== '/article/list/') {
          this.$router.push({
            path: '/article/list/'
          });
        }
      },
      toExamineArticle() {
        if (this.$route.path !== '/article/examine/') {
          this.$router.push({
            path: '/article/examine/'
          });
        }
      },
      toComment() {
        if (this.$route.path !== '/article/comment/') {
          this.$router.push({
            path: '/article/comment/'
          });
        }
      },
      toExamineFish(){
        if (this.$route.path !== '/fish/examineFish/') {
          this.$router.push({
            path: '/fish/examineFish/'
          });
        }
      }
    }
  }
</script>


