<template>
    <div>
        <div class="pageBox" :style="{'backgroundColor':bgcColor,'height':bgHeight}">
            <el-row>
                <el-col :span="22" :offset="1">
                    <p class="titlep" :style="{'color':titleColor,'fontSize':titleSize,'textAlign':titleAlign,'fontWeight':fontWeight}" style="text-align:center">{{titleName}}</p>
                    <slot name="content"></slot>
                </el-col>
            </el-row>
            
        </div>
    </div>
</template>

<script>
    module.exports =  {
    props: ['bgcolor','titlealign','titlecolor','title','titlesize','height','fontweight'],
    data() {
        return {
            bgcColor:'#fff', //背景颜色
            titleAlign:'center',//标题居中
            titleColor:'#222222',//标题颜色
            titleName:'',//标题
            titleSize:'18px',//标题文字大小
            bgHeight:'',//高度
            fontWeight:'600',//标题加粗
        }
    },
    created(){
        if(this.bgcolor != undefined){
            this.bgcColor = this.bgcolor; //背景颜色
        }
        if(this.titlealign != undefined){
            this.titleAlign = this.titlealign;//标题居中
        }
        if(this.titlecolor != undefined){
            this.titleColor = this.titlecolor;//标题颜色
        }
        if(this.title != undefined){
            this.titleName = this.title;//标题
        }
        if(this.titlesize != undefined){
            this.titleSize = this.titlesize;//标题大小
        }
        if(this.height != undefined){
            this.bgHeight = this.height;//高度
        }
        if(this.fontweight != undefined){
            this.fontWeight = this.fontweight;//高度
        }
        
        
        
    },
    methods:{
        
    },
    watch:{
        
    }
}
</script>

<style>
    .pageBox{
        width: 100%;
        padding: 30px 0;
        overflow: hidden;
    }
    .titlep{
        padding-bottom: 10px;
    }
</style>