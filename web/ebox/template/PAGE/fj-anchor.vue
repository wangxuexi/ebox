//锚点
<template>
<div class="anchorBox">
    <div style="width:50px" class="mouse">
        <div @click="btnClick" style="width:50px;height:100px" @mouseenter="enter(index)">
                <div style="width:50px;height:100px;float:right;background-color:#e6a23c;line-height:100px;text-align:center;color:#fff;border-top-left-radius: 5px;cursor: pointer;border-bottom-left-radius: 5px;">
                    <i :class="icon"></i>
                </div>
        </div>
    </div>
    <div class="anchorContent"  @mouseleave="leave()" >
        <p v-for="(item,index) in list">
            <a  :data="'#'+item.id" class="jumpAnchor" :content='item.id'>{{item.name}}</a>
        </p>
        
    </div>
</div>
    
        
    
</template>

<script>
    module.exports =  {
    props: ['data'],
    data() {
        return {
            list:[],
            width:'0',
            icon:'el-icon-d-arrow-right',
            text:'收缩',
        }
    },
    created(){
        let datas = this.data;
        datas.forEach(element => {
            let flag = tool.isInList(element,'type')
            if(flag == false){
                this.list.push(element);
            }else if(flag == true){
                if(element.type == ANCHOR_FLAG){
                    let sessionMenu = JSON.parse(GET_SECONDARY_MENU()).children;
                    if(sessionMenu != undefined){
                        sessionMenu.forEach(item=>{
                            this.list.push({id:item.menuDomId,name:item.menuName})
                        })
                    }
                    
                }else{
                    this.list.push(element);
                }
            }
        });

        
    },
    methods:{
        btnClick(){
            
            if(this.width == '0'){
                this.icon = 'el-icon-d-arrow-right'
                this.width = '100'
                $(".anchorContent").animate({right:'0'});
                $(".mouse").animate({right:'-50px'});
            }else{
                this.icon = 'el-icon-d-arrow-left'
                this.width = '0'
                $(".anchorContent").animate({right:'-155px'});
                $(".mouse").animate({right:'0'});
            }
            
        },
        enter(index){
            this.icon = 'el-icon-d-arrow-right'
            this.width = '100'
            $(".anchorContent").animate({right:'0'});
            $(".mouse").animate({right:'-50px'});
        },
        leave(){
            this.icon = 'el-icon-d-arrow-left'
            this.width = '0'
            $(".anchorContent").animate({right:'-155px'});
            $(".mouse").animate({right:'0'});
        }
    },
    watch:{
        
    },

    
}
</script>

<style >
    .anchorBox{
        width:155px;position:fixed;right: 0;top: 100px;
        z-index: 999999;
    }
    .mouse{
        position: absolute;
        right: 0;
        height: 100px;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
    }
    .anchorContent{
        position: relative;
        right: -155px;
        width: 100%;
        background-color: rgb(255, 255, 255);border: 1px solid gainsboro;
        padding:15px 15px 15px 10px;
        text-align: right;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
    }
    .anchorBox a{
        text-decoration: none;
        cursor: pointer;
        color: #999;
    }
    .anchorBox a:link    {color:#999999;}
    .anchorBox a:visited {color:#999999;}
    .anchorBox a:hover   {color:#2F5BEA;}
    .anchorBox a:active  {color:#999999;}


    .anchorContent:hover{
        background-color: #fff;
            -webkit-box-shadow: #ccc 0px 0px 10px;
            -moz-box-shadow: #ccc 0px 0px 10px;
            box-shadow: #ccc 0px 0px 10px;  }
    .mouse i{
        line-height: 100px !important;
    }
</style>