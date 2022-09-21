<template>
    <div>
        <div class="pageCard" :style="{'backgroundColor':bgcColor,'height':boxHeight,'border':cardBorder}">
            <div class="titleTextCard" :style="{'borderColor':iconColor,'fontSize':titleSize,'fontWeight':fontWeight,'color':titleColor,'textAlign':titleAlign,'backgroundColor':titleBgc,'height':titleHeight,'lineHeight':titleHeight}">
                <span style="margin-left:20px">
                    <span v-if="iconUrl != '' " :style="{'fontSize':iconSize,'color':iconColor}">
                        <i :class="iconUrl" style="position: relative;
    top: 3px;"></i>
                    </span>
                    <span v-if="imgUrl != ''">
                        <img :src="imgUrl" alt="" :style="{'width':imgHeight,'height':imgHeight}">
                    </span>
                
                    <span style="margin-left:5px">{{titleName}}</span>

                    <div class='cardRightSlot' :style="{'height':titleHeight}">
                        <slot name="cardRight"></slot>
                    </div>
                    
                </span>
                
                
            </div>
            <slot name="cardContent"></slot>
        </div>
    </div>
</template>

<script>
    module.exports =  {
    props: ['bgcolor','titlealign','titlecolor','title','titlesize','iconcolor','fontweight','height','titlebgc','titleheight','icon','img','imgheight','iconsize','cardborder'],
    data() {
        return {
            bgcColor:'#fff', //背景颜色
            titleAlign:'left',//标题居中
            titleColor:'#222222',//标题颜色
            titleName:'',//标题
            titleSize:'',//标题文字大小
            iconColor:'#2F5BEA',//icon颜色
            fontWeight:'',
            boxHeight:'',
            titleBgc:'#DDE8FF',//标题栏背景颜色
            titleHeight:'35px',//标题栏高度
            iconUrl:'',//icon图标
            imgUrl:'',//logo
            imgHeight:'16px',//
            iconSize:'20px',//icon大小
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
        if(this.titlebgc != undefined){
            this.titleBgc = this.titlebgc;//标题栏背景颜色
        }
        if(this.titleheight != undefined){
            this.titleHeight = this.titleheight;//标题栏高度
        }
        if(this.icon != undefined){
            this.iconUrl = this.icon;//icon
        }
        if(this.img != undefined){
            this.imgUrl = this.img;//图片icon地址
        }
        if(this.imgheight != undefined){
            this.imgHeight = this.imgheight;//logo宽高
        }
        if(this.iconsize != undefined){
            this.iconSize = this.iconsize;//icon大小
        }
        if(this.cardborder != undefined){
            this.cardBorder = this.cardborder;//边框
        }
        if(this.icon == undefined && this.img == undefined){
            this.iconUrl = 'el-icon-menu'
        }
        
    },
    methods:{
        
    },
    watch:{
        
    }
}
</script>

<style>
    .pageCard{
        width: 100%;
    }
    .titleTextCard{
        overflow: hidden;
    }
    .cardRightSlot{
        float: right;
    }
</style>