<template>
  <div class="fishBox">
    <div style="
    display: flex;">
      <el-image :src="fish.imagePath"></el-image>
      <div style="width: 1000px">
        <h1>{{fish.fishName}}</h1>
        <span>{{fish.content}}</span>
      </div>
    </div>
    <div style="margin: 20px;">
      <span>特征：</span>
      <span>{{fish.features}}</span>
    </div>
  </div>
</template>

<script>
    export default {
        name: "FishDetails",
      beforeRouteEnter:(to,from,next)=>{
        console.log("进入路由之前");
        next(vm=>{
          vm.getData();
        });
      },
      data() {
        return {
          srcList:[],
          fish: {
            imagePath:''
          }
        }
      },
      methods: {
        getData() {
          this.$axios.get('views/fish/findById/'+this.$route.params.id)
            .then(res=>{
              console.log(res)
              this.fish=res.data
              this.srcList=[res.data.imagePath]
            })
            .catch(err=>{
              console.log(err)
            })
        }
      }
    }
</script>

<style scoped>
#fishDetails{
  min-width: 20em;
  min-height: 8em;
  background-color: hsla(0,0%,100%,.8);
  border-radius: 1.2em;
  box-shadow: 0 2px 10px -5px rgb(9 2 4 / 80%);
  padding: 2em;
  box-sizing: border-box;
}
.fishBox{
  display: inline-block;
  border-radius: 4px;
  list-style-type:none;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  width: 1450px;
  height: 500px;
  margin: 20px;
  background-color: hsla(0,0%,100%,.8);
  box-sizing: border-box;
  border-radius: 1.2em;
}
  li{
    list-style:none;
  }
  .el-image{
    width: 200px;
    height: 200px;
    margin: 25px 0 0 25px;
    margin-right: 20px;
  }
</style>
