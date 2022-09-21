// 导航栏
<template>
    <div class="fj-breadcrumb-container" style="width:100%;padding:12px 20px 10px 0;height:40px;color:red;line-height: 30px;background-color: rgb(245,245,245);border: 1px solid gainsboro">
                    <el-breadcrumb separator-class="el-icon-arrow-right" style="height: 100%;padding: 0 0 0 20px">
                        <el-breadcrumb-item v-for="(item,index) in path" :key="index">{{item}}</el-breadcrumb-item> <span @click="refresh" style="float: right;color: rgba(86, 86, 86, 0.75);cursor: pointer;"><i class="fa fa-refresh"></i>刷新</span>
                    </el-breadcrumb>
                    
                </div>
</template>


<script>
    module.exports =  {
   props: [],
    data: function () {
        return {
            path: Array,
        }
    },
    created(){
        var params =  JSON.parse(localStorage.getItem("paths"));
            var path1 = [];
            if (params){
                for (var i=0;i<params.length-1;i++){
                    path1.push(params[i]);
                }
                var newPath = params[params.length-1].split('*');
                path1.push(newPath[1]);
                this.path = path1;
            }
    },
    methods:{
        refresh(){
            window.location.reload();
        }
    },
    watch:{
        
    }
}
</script>