<template xmlns:border-radius="http://www.w3.org/1999/xhtml">
  <div>
    <div v-for="fish in fishs" class="fishBox" style="background-color: #FFF;text-align: center" >
      <div class="demo-image">
      <div class="block">
        <el-image
          style="width: 160px; height: 100px; margin: 20px;"
          :src="fish.imagePath"></el-image>
      </div>
    </div>
      <span>{{ fish.fishName }}</span><br/>
      <el-button
        style="margin-bottom: 20px;"
        type="primary" plain
      @click="toFishDetails(fish)">鱼类详情</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    name: "ListFish",
    beforeRouteEnter:(to,from,next)=>{
      console.log("进入路由之前");
      next(vm=>{
        vm.getData();
      });
    },
    data() {
      return {
        fishs:[
        ]
      }
    },
    methods:{
      getData(){
        this.$axios.get('views/fish/list')
          .then(res=>{
            console.log(res)
            this.fishs=res.data
          })
          .catch(err=>{
            console.log(err)
          })
      },
      toFishDetails(params) {
        if (this.$route.path !== '/fishDetails/:id') {
          this.$router.push({
            name: 'FishDetails',
            params: {
              fishName:params.fishName,
              id:params.id,
              content:params.content,
              imagePath:params.imagePath
              //要一个个传值不然就是传一大串字符串
            }
          });
        }
      }
    }
  }
</script>

<style scoped>
  .fishBox{
    display: inline-block;
    border-radius: 4px;
    list-style-type:none;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    width: 200px;
    margin: 20px;
  }
</style>
