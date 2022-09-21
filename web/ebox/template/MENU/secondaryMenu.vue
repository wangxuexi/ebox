 <template>
    <div>
        <div v-for='(item,index) in sessionMenu' class="menuBox">
            <el-row >
                <el-col :span="24" class="menuTitle" :class="item.menuDomClass" :id="item.menuDomId">{{item.menuName}}</el-col>
            </el-row>
			<div v-if="item.children == null">
				
			</div>
            <div v-else-if="item.children.length <=0">

            </div>
            <div v-else-if="item.children.length == 1">
                <el-row :gutter="40" >
                    <div v-for="(element,index) in item.children" >
                        <el-col :span="4" :offset="10">
                            <div class="menuContent" :class="element.menuDomClass" :id="element.menuDomId" @click="menuClick(element)">
                                <div class="num"></div>
                                <el-image
                                style="width: 80px; height: 80px;margin:40px auto 0 auto;display:block"
                                :src="element.menuIconUrl"
                                fit="fill"></el-image>
                                <p style="margin-top:25px;text-align:center;font-size:16px;font-weight:600;color:#222222">{{element.menuName}}</p>
                                <p style="margin-top:20px;text-align:center;color:#8D95A9">{{element.remark}}</p>
                            </div>
                        </el-col>
                    </div>
                </el-row>
            </div>
            <div v-else-if="item.children.length == 2">
                <el-row :gutter="40">
                    <el-col :span="8" >
                            <div style="width:100%;height:230px"></div>
                        </el-col>

                    <div v-for="(element,index) in item.children" >
                        <el-col :span="4">
                            <div class="menuContent" :class="element.menuDomClass" :id="element.menuDomId" @click="menuClick(element)">
                                <div class="num"></div>
                                <el-image
                                style="width: 80px; height: 80px;margin:40px auto 0 auto;display:block"
                                :src="element.menuIconUrl"
                                fit="fill"></el-image>
                                <p style="margin-top:25px;text-align:center;font-size:16px;font-weight:600;color:#222222">{{element.menuName}}</p>
                                <p style="margin-top:20px;text-align:center;color:#8D95A9">{{element.remark}}</p>
                            </div>
                        </el-col>
                    </div>
                </el-row>
            </div>
            <div v-else-if="item.children.length == 3">
                <el-row :gutter="40">
                    <el-col :span="6" >
                            <div style="width:100%;height:230px"></div>
                        </el-col>

                    <div v-for="(element,index) in item.children" >
                        <el-col :span="4">
                            <div class="menuContent" :class="element.menuDomClass" :id="element.menuDomId" @click="menuClick(element)">
                                <div class="num"></div>
                                <el-image
                                style="width: 80px; height: 80px;margin:40px auto 0 auto;display:block"
                                :src="element.menuIconUrl"
                                fit="fill"></el-image>
                                <p style="margin-top:25px;text-align:center;font-size:16px;font-weight:600;color:#222222">{{element.menuName}}</p>
                                <p style="margin-top:20px;text-align:center;color:#8D95A9">{{element.remark}}</p>
                            </div>
                        </el-col>
                    </div>
                </el-row>
            </div>
            <div v-else-if="item.children.length == 4">
                <el-row :gutter="40">
                    <el-col :span="4" >
                            <div style="width:100%;height:230px"></div>
                        </el-col>

                    <div v-for="(element,index) in item.children" >
                        <el-col :span="4">
                            <div class="menuContent" :class="element.menuDomClass" :id="element.menuDomId" @click="menuClick(element)">
                                <div class="num"></div>
                                <el-image
                                style="width: 80px; height: 80px;margin:40px auto 0 auto;display:block"
                                :src="element.menuIconUrl"
                                fit="fill"></el-image>
                                <p style="margin-top:25px;text-align:center;font-size:16px;font-weight:600;color:#222222">{{element.menuName}}</p>
                                <p style="margin-top:20px;text-align:center;color:#8D95A9">{{element.remark}}</p>
                            </div>
                        </el-col>
                    </div>
                </el-row>
            </div>
            <div v-else-if="item.children.length == 5">
                <el-row :gutter="40">
                    <el-col :span="2" >
                            <div style="width:100%;height:230px"></div>
                        </el-col>

                    <div v-for="(element,index) in item.children" >
                        <el-col :span="4">
                            <div class="menuContent" :class="element.menuDomClass" :id="element.menuDomId" @click="menuClick(element)">
                                <div class="num"></div>
                                <el-image
                                style="width: 80px; height: 80px;margin:40px auto 0 auto;display:block"
                                :src="element.menuIconUrl"
                                fit="fill"></el-image>
                                <p style="margin-top:25px;text-align:center;font-size:16px;font-weight:600;color:#222222">{{element.menuName}}</p>
                                <p style="margin-top:20px;text-align:center;color:#8D95A9">{{element.remark}}</p>
                            </div>
                        </el-col>
                    </div>
                </el-row>
            </div>
            <div v-else-if="item.children.length > 5">
                <el-row :gutter="40">
                    <div v-for="(element,index) in item.children" >
                        <template v-if=" (index+1)%5 == 1">
                            <template v-if="index > 4">
                                <el-col :span="2" style="margin-top: 20px">
                                    <div style="width:100%;height:230px"></div>
                                </el-col>
                            </template>
                            <template v-else>
                                <el-col :span="2" >
                                    <div style="width:100%;height:230px"></div>
                                </el-col>
                            </template>
                        </template>
                        <template v-if="index > 4">
                            <el-col :span="4" style="margin-top: 20px">
                                <div class="menuContent" :class="element.menuDomClass" :id="element.menuDomId" @click="menuClick(element)">
                                    <div class="num"></div>
                                    <el-image
                                            style="width: 80px; height: 80px;margin:40px auto 0 auto;display:block"
                                            :src="element.menuIconUrl"
                                            fit="fill"></el-image>
                                    <p style="margin-top:25px;text-align:center;font-size:16px;font-weight:600;color:#222222">{{element.menuName}}</p>
                                    <p style="margin-top:20px;text-align:center;color:#8D95A9">{{element.remark}}</p>
                                </div>
                            </el-col>
                        </template>
                        <template v-else>
                            <el-col :span="4">
                                <div class="menuContent" :class="element.menuDomClass" :id="element.menuDomId" @click="menuClick(element)">
                                    <div class="num"></div>
                                    <el-image
                                            style="width: 80px; height: 80px;margin:40px auto 0 auto;display:block"
                                            :src="element.menuIconUrl"
                                            fit="fill"></el-image>
                                    <p style="margin-top:25px;text-align:center;font-size:16px;font-weight:600;color:#222222">{{element.menuName}}</p>
                                    <p style="margin-top:20px;text-align:center;color:#8D95A9">{{element.remark}}</p>
                                </div>
                            </el-col>
                        </template>

                        <template v-if="(index+1)%5 == 0">
                            <template v-if=" index > 4">
                                <el-col :span="2" style="margin-top: 20px">
                                    <div style="width:100%;height:230px;"></div>
                                </el-col>
                            </template>
                            <template v-else>
                                <el-col :span="2" >
                                    <div style="width:100%;height:230px;"></div>
                                </el-col>
                            </template>

                        </template>
                    </div>
                </el-row>
            </div>
        </div>
        
    </div>
     
 </template>

<script>
    module.exports =  {
    props: [],
    data() {
        return {
            sessionMenu:[],
        }
    },
    created(){
        this.sessionMenu = JSON.parse(GET_SECONDARY_MENU()).children;
        let parentData = {
            topType:'1'
        }
        //向父页面传值
        window.parent.postMessage(parentData, '*');
    },
    methods:{
        menuClick(element){
            if (element.hasOwnProperty('children') == false){  //没有三级菜单
                if (element.menuUrl == "" || element.menuUrl == null){
                    this.$message.warning("此功能暂未开放哦！");
                }else {
                    if (element.backParams != '' && element.backParams != null){

                        if (element.menuUrl.indexOf("?") != -1){   //已经有参数
                            if(element.menuIsNewTab == false){
                                let parentData = {
                                    pageType:NEWTABS,
                                    page:element.menuUrl + '&backParams=' + element.backParams,
                                    name:element.menuName
                                }
                                //向父页面传值
                                window.parent.postMessage(parentData, '*');
                            }else {
                                window.open(element.menuUrl + '&backParams=' + element.backParams);
                            }
                        }else { //没有参数
                            if(element.menuIsNewTab == false){
                                let parentData = {
                                    pageType:NEWTABS,
                                    page:element.menuUrl + '?backParams=' + element.backParams,
                                    name:element.menuName
                                }
                                //向父页面传值
                                window.parent.postMessage(parentData, '*');
                            }else {
                                window.open(element.menuUrl + '?backParams=' + element.backParams);
                            }
                        }


                    }else {
                        if(element.menuIsNewTab == false){
                            let parentData = {
                                pageType:NEWTABS,
                                page:element.menuUrl,
                                name:element.menuName
                            }
                            //向父页面传值
                            window.parent.postMessage(parentData, '*');
                        }else {
                            window.open(element.menuUrl);
                        }
                    }
                }

            }else{
                sessionStorage.setItem(TENANT_CODE + "_threeMenu", JSON.stringify(element));
                window.location.href = "../template/emptyMenu.html"
            }
            this.numClick(element);
        },
        numClick(item){
            $.ajax({
                url: SERVER_IP_PORT+SERVER_PREFIX+"openApi/menuClick",
                type: "POST",
                data:{"tenant":TENANT_CODE,"applicationCode":APPLICATION_CODE,userId:PLAT_GET_UID(),loginName:PLAT_GET_USERNAME(),menuId:item.id,menuName:item.menuName},
                async: true,
                dataType: "json",
                xhrFields: {
                    withCredentials: xhrFieldsState
                },
                success: function (data) {

                }
            });
        }
    },
    watch:{
        
    }
}
</script>
<style>
    .menuBox{
        padding-bottom: 25px;
    }
    .menuBox:nth-child(2n){
        background-color: #fff;
    }
    .menuBox:nth-child(2n+1){
        background-color: #F2F6FF;
    }
    .menuTitle{
        margin:30px 0 20px 0;text-align:center;
        font-size: 18px;
        font-weight: 600;
    }
    .menuContent{
        width: 80%;
        background-color: #fff;
        border: 1px solid #EAEDF3;
        height: 230px;
        margin: auto;
        overflow: hidden;
        border-radius: 5px;padding: 0 4% 0 4%;
        position: relative;
        cursor: pointer;
    }
    .num{
        background-color: red;
        position: absolute;
        top: 10px;
        left: 0;
        color: #fff;
        border-radius: 3px;
        text-align: center;
        /*font-weight: 600;*/
        /*font-size: 16px;*/
    }
    .menuContent:hover{
            -webkit-box-shadow: #ccc 0px 0px 10px;
            -moz-box-shadow: #ccc 0px 0px 10px;
            box-shadow: #ccc 0px 0px 10px;  }
</style>

