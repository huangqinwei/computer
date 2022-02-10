import Vue from 'vue'
import  Router from 'vue-router'

import Main from '../views/Main'
import Main2 from '../views/Main2'
import Index from '../views/Index'
import Index2 from '../views/Index2'
import Login from '../views/Login'
import UserList from '../views/user/List'
import UserProfile from '../views/user/Profile'
import FishList from '../views/fish/List'
import AddAndUpdate from '../views/fish/AddAndUpdate'
import Details from '../views/fish/Details'
import Register from '../views/Register'
import ListFish from '../views/fish/ListFish'
import FishDetails from '../views/fish/FishDetails'
import ListArticle from '../views/article/ListArticle'
import ArticleDetails from '../views/article/ArticleDetails'
import ArticleList from '../views/article/List'
import ExamineArticle from '../views/article/ExamineArticle'
import AddAndUpdateArticle from '../views/article/AddAndUpdate'
import Comment from '../views/article/Comment'
import UserInformation from '../views/user/UserInformation'
import ProvideFish from '../views/fish/ProvideFish'
import ExamineFish from '../views/fish/ExamineFish'
import SearchFish from '../views/fish/SearchFish'
Vue.use(Router);

const router =new Router({
  routes:[
    { path: '/', redirect: '/login' },
    {
      path:'/login',
      name:'Login',
      component:Login
    },{
      path:'/register',
      name:'Register',
      component:Register
    },{
      path:'/main/:username',
      name:'Main',
      component:Main,
      children:[
        {path:'/user/profile',component:UserProfile},
        {path:'/user/list/', name:'UserList', component:UserList},
        {path:'/fish/list/', name:'FishList', component:FishList},
        {path:'/index', name:'Index', component:Index},
        {path:'/addAndUpdate/:id', name:'AddAndUpdate', component:AddAndUpdate},
        {path:'/details', name:'Details', component:Details},
        {path:'/article/list/', name:'ArticleList', component:ArticleList},
        {path:'/article/examine/', name:'ExamineArticle', component:ExamineArticle},
        {path:'/addAndUpdateArticle/:id', name:'AddAndUpdateArticle', component:AddAndUpdateArticle},
        {path:'/article/comment/', name:'comment', component:Comment},
        {path:'/fish/examineFish/', name:'ExamineFish', component:ExamineFish}
      ]
    },{
      path:'/main2',
      name:'Main2',
      component:Main2,
      children:[
        {path:'/index2', name:'Index2', component:Index2},
        {path:'/fishDetails/:id', name:'FishDetails', component:FishDetails},
        {path:'/listFish', name:'ListFish', component:ListFish},
        {path:'/articleDetails/:id', name:'ArticleDetails', component:ArticleDetails},
        {path:'/listArticle', name:'ListArticle', component:ListArticle},
        {path:'/userInformation', name:'UserInformation', component:UserInformation},
        {path:'/provideFish', name:'ProvideFish', component:ProvideFish},
        {path:'/searchFish', name:'SearchFish', component:SearchFish}
      ]
    }
  ]
});

router.beforeEach((to, from, next) => {
//to到哪儿 from从哪儿离开 next跳转 为空就是放行
  if (to.path === "/login"||to.path === "/register") {
    //如果跳转为登录，就放行
    next();
  } else {
    //取出localStorage判断
    let token = localStorage.getItem("token");
    if (token === null || token === "") {
      console.log("请先登录")
      next('/login');
    } else {
      next();
    }
  }
})

export default router;
