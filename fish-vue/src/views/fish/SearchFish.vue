<template>
    <div>
      <el-form>
        <el-form-item label="输入鱼类名称:">
          <el-input v-model="search" style="width: 200px;margin-left: 20px"></el-input>
          <el-button type="primary" @click="searchFish(search)" plain>搜索</el-button>
        </el-form-item>
      </el-form>
      <div>
        <div v-for="fish in fishs" class="fishBox" style="background-color: #FFF" >
          <div class="demo-image">
            <div class="block">
              <el-image
                style="width: 160px; height: 100px; margin: 20px;"
                :src="fish.imagePath"></el-image>
            </div>
          </div>
          <span style="margin-left: 20px">{{ fish.fishName }}</span><br/>
          <el-button
            style="margin-left: 40px;margin-bottom: 20px;"
            type="primary" plain
            @click="toFishDetails(fish)">鱼类详情</el-button>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
        name: "SearchFish",
      data() {
        return {
          search:'',
          fishs:[
          ]
        }
      },
      methods:{
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
        },
        searchFish(search){
          if(search===""||search===null){
            this.$message({
              type: 'error',
              message: '请输入查询信息!'
            })
          }else{
            this.$axios.get('views/fish/search/'+search)
              .then(res=>{
                console.log(res.data)
               if(res.data.length===0){
                 this.$message({
                   type: 'error',
                   message: '搜索不到鱼类信息!'
                 })
               }else{
                 this.fishs=res.data
               }
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
  .fishBox{
    display: inline-block;
    border-radius: 4px;
    list-style-type:none;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    width: 200px;
    margin: 20px;
  }
</style>
