<template>
  <div>
    <div id="myChart":style="{width: '900px', height: '300px'}" ></div>
    <div style="margin-left: 300px">
      <el-button :type="btn1" round @click="drawLine('bar');" >柱状图</el-button>
      <el-button :type="btn2" round @click="drawLine('line');">折线图</el-button>
    </div>
  </div>
</template>
<style>

</style>

<script>
  export default {
    name: "Index",
    // beforeRouteEnter:(to,from,next)=>{
    //   console.log("进入路由之前");
    //   next(vm=>{
    //     vm.getData();
    //   });
    // },
    data() {
      return {
        btn1:'',
        btn2:'',
        loginLog:{
        }
      };
    },
    mounted(){
      this.drawLine('bar');
    },
    methods:{
      // getData(){
      //   this.$axios.get('views/log/login')
      //     .then(res=>{
      //       console.log(res)
      //       this.loginLog=res.data
      //     })
      //     .catch(err=>{
      //       console.log(err)
      //     })
      // },
      changeButton1(){
        this.btn1=''
        this.btn2='primary'
      },
      changeButton2(){
        this.btn1='primary'
        this.btn2=''
      },
      drawLine(type){
        if(type==='line'){
          this.changeButton1()
        }else {
          this.changeButton2()
        }
        this.$axios.get('views/log/login')
          .then(res=>{
            console.log(res)
            let myChart = this.$echarts.init(document.getElementById('myChart'))
            myChart.setOption({
              title: { text: '近七天系统用户访问量' },
              tooltip: {},
              toolbox:{
                show:true,
                feature:{
                  saveAsImage:{
                    show:true
                  }
                }
              },
              xAxis: {
                data: res.data.loginTime
              },
              legend:{
                data:['用户访问量']
              },
              yAxis: {},
              series: [{
                name: '用户访问量',
                type: type,
                data: res.data.loginSum
              }]
            });
          })
          .catch(err=>{
            console.log(err)
          })
      }
    }
  }
</script>
