<template>
    <div>
        <div class="pageBlock" :style="{'backgroundColor':bgcColor,'height':boxHeight,'border':cardBorder}">
            <div class="titleText" :style="{'borderColor':iconColor,'fontSize':titleSize,'fontWeight':fontWeight,'color':titleColor,'textAlign':titleAlign}">
                <span style="margin-left:10px">{{titleName}}</span>
                
            </div>
            <slot name="blockContent"></slot>
        </div>
    </div>
</template>

<script>
    module.exports =  {
    props: ['bgcolor','titlealign','titlecolor','title','titlesize','iconcolor','fontweight','height','cardborder'],
    data() {
        return {
            bgcColor:'#fff', //背景颜色
            titleAlign:'left',//标题居中
            titleColor:'#222222',//标题颜色
            titleName:'',//标题
            titleSize:'16px',//标题文字大小
            iconColor:'#2F5BEA',
            fontWeight:'600',
            boxHeight:'',
            cardBorder:'1px solid #EAEDF3',//边框
        }
    },
    created(){
        if(this.bgcolor != undefined){
            this.bgcColor = this.bgcolor; //背景颜色
        }
        if(this.titlealign != 'undefined'){
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
        if(this.iconcolor != undefined){
            this.iconColor = this.iconcolor;//icon颜色
        }
        if(this.fontweight != undefined){
            this.fontWeight = this.fontweight;//标题加粗
        }
        if(this.height != undefined){
            this.boxHeight = this.height;//高度
        }
        if(this.cardborder != undefined){
            this.cardBorder = this.cardborder;//边框
        }
        
        
        
    },
    methods:{
        
    },
    watch:{
        
    }
}
</script>

<style>
    .pageBlock{
        width: 100%;
        padding: 15px 0;
    }
    .titleText{
        border-left: 10px solid red;
        height: 25px;
        line-height: 25px;
        overflow: hidden;
    }
    .pageBlock:hover{
-webkit-box-shadow: #ccc 0px 0px 10px;
-moz-box-shadow: #ccc 0px 0px 10px;
box-shadow: #ccc 0px 0px 10px;  }
</style>