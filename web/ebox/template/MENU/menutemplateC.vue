<template>
    <div class="sidebar">
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#FFF" @select="menuRoutes"
                  unique-opened router>
            <!-- 第一层 -->
            <template v-for="item in sessionMenu">
                <!-- 如果第一层有子菜单，则继续循环 -->
                <template v-if="item.children">
                    <el-submenu :index="item.id+''" :key="item.id">
                        <template slot="title">
                            <i :class="item.icon"></i>
                            <span slot="title"><i :class="item.menuIconUrl" style="position: relative;top:-1.5px"></i> {{ item.menuName }}
                                <template v-if="item.menuDomId != ''">
                                    <span :id="item.menuDomId" class="approvalClass"></span>
                                </template>
                            </span>
                        </template>
                        <!-- 第二层 -->
                        <template v-for="subItem in item.children">
                            <!-- 如果第二层有子菜单，则继续循环 -->
                            <template v-if="subItem.children">
                                <el-submenu :index="subItem.id+''" :key="subItem.id">
                                    <template slot="title">
                                        <i :class="item.icon"></i>
                                        <span slot="title"><i :class="subItem.menuIconUrl" style="position: relative;top:-1.5px"></i> {{ subItem.menuName }}
                                            <template v-if="subItem.menuDomId != ''">
                                                <span :id="subItem.menuDomId" class="approvalClass"></span>
                                            </template>
                                        </span>
                                    </template>
                                    <!-- <el-menu-item v-for="(threeItem,i) in subItem.subs" :key="i" :index="threeItem.index">{{ threeItem.title }}</el-menu-item> -->
                                    <!-- 第三层 -->
                                    <template v-for="subItem2 in subItem.children">
                                        <!-- 如果第三层有子菜单，则继续循环 -->
                                        <template v-if="subItem2.children">
                                            <el-submenu :index="subItem2.id+''" :key="subItem2.id">
                                                <template slot="title">
                                                    <i :class="item.icon"></i>
                                                    <span slot="title"><i :class="subItem2.menuIconUrl" style="position: relative;top:-1.5px"></i> {{ subItem2.menuName }}
                                                        <template v-if="subItem2.menuDomId != ''">
                                                            <span :id="subItem2.menuDomId" class="approvalClass"></span>
                                                        </template>
                                                    </span>
                                                </template>
                                                <!-- <el-menu-item v-for="(fourItem,i) in subItem2.subs" :key="i" :index="fourItem.index">{{ fourItem.title }}</el-menu-item> -->
                                                <!-- 第四层 -->
                                                <template v-for="subItem3 in subItem2.children">
                                                    <!-- 如果第四层有子菜单，则继续循环 -->
                                                    <template v-if="subItem3.children">
                                                        <el-submenu :index="subItem3.id+''" :key="subItem3.id">
                                                            <template slot="title">
                                                                <!-- <i :class="item.icon"></i> -->
                                                                <!-- <span slot="title">{{ subItem2.title }}</span> -->
                                                                <i :class="subItem3.menuIconUrl" style="position: relative;top:-1.5px"></i> {{ subItem3.menuName }}
                                                                <template v-if="subItem3.menuDomId != ''">
                                                                    <span :id="subItem3.menuDomId" class="approvalClass"></span>
                                                                </template>
                                                            </template>
                                                            <el-menu-item v-for="(fiveItem,i) in subItem3.children" :key="i" :index="fiveItem.id+''"><i :class="list.menuIconUrl" style="position: relative;top:-1.5px"></i> {{ fiveItem.menuName }}
                                                                <template v-if="fiveItem.menuDomId != ''">
                                                                    <span :id="fiveItem.menuDomId" class="approvalClass"></span>
                                                                </template>
                                                            </el-menu-item>
                                                        </el-submenu>
                                                    </template>
                                                    <!-- 如果第四层没有子菜单 -->
                                                    <el-menu-item v-else :index="subItem3.id+''" :key="subItem3.id"><i :class="subItem3.menuIconUrl" style="position: relative;top:-1.5px"></i> {{ subItem3.menuName }}
                                                        <template v-if="subItem3.menuDomId != ''">
                                                            <span :id="subItem3.menuDomId" class="approvalClass"></span>
                                                        </template>
                                                    </el-menu-item>
                                                </template>
                                            </el-submenu>
                                        </template>
                                        <!-- 如果第三层没有子菜单 -->
                                        <el-menu-item v-else :index="subItem2.id+''" :key="subItem2.id"><i :class="subItem2.menuIconUrl" style="position: relative;top:-1.5px"></i> {{ subItem2.menuName }}
                                            <template v-if="subItem2.menuDomId != ''">
                                                <span :id="subItem2.menuDomId" class="approvalClass"></span>
                                            </template>
                                        </el-menu-item>
                                    </template>
                                </el-submenu>
                            </template>
                            <!-- 如果第二层没有子菜单 -->
                            <el-menu-item v-else :index="subItem.id+''" :key="subItem.id"><i :class="subItem.menuIconUrl" style="position: relative;top:-1.5px"></i> {{ subItem.menuName }}
                                <template v-if="subItem.menuDomId != ''">
                                    <span :id="subItem.menuDomId" class="approvalClass"></span>
                                </template>
                            </el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <!-- 如果第一层没有子菜单 -->
                <template v-else>
                    <el-menu-item :index="item.id+''" :key="item.id">
                        <i :class="item.icon"></i>
                        <span slot="title"><i :class="item.menuIconUrl" style="position: relative;top:-1.5px"></i> {{ item.menuName }}
                            <template v-if="item.menuDomId != ''">
                                    <span :id="item.menuDomId" class="approvalClass"></span>
                                </template>
                        </span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    module.exports =  {
        props: [],
        data() {
            return {
                collapse: false,
                sessionMenu:null,
                transDate:null,
                approvalList:[],//审批构造的数组
            };
        },
        mounted(){
            var datas = PLAT_GET_APPROVAL();
            this.approvalList.forEach(item => {  //遍历菜单回传参数构造的对象
                let nums = 0; //显示的数量
                item.approval.forEach(element=>{  //如果一个菜单牵涉到多张表  那这里就是一个数组  需要遍历
                    Object.keys(datas).forEach((key) => { //这里遍历待我审批的所有数据
                        if (element.approvalDb == key) {  //根据拿到的待我审批的数据的   实体类型+表明   跟菜单回传参数做对比
                            if (element.approvalState == ''){  //查全部
                                nums = nums + (datas[key].resultList.length-0);
                            }else {  //根据状态过滤
                                let stateList = [];
                                let stateArr = element.approvalState.split( ',' );
                                stateArr.forEach(items=>{
                                    datas[key].resultList.forEach(type=>{
                                        if (items == type.state){
                                            stateList.push(type)
                                        }
                                    })
                                })
                                nums = nums + (stateList.length-0);
                            }
                        }
                    });
                })

                $(`#${item.domId}`).text('(' + nums + ')');
            });
        },
        created(){
            this.sessionMenu = PLAT_GET_SELECTMENU().children;

            this.transDate = PLAT_GET_SELECTMENU().children;
            this.approvalList = [];
            let selectMenus  = this.transDate;
            selectMenus.forEach(item=>{   //遍历一级菜单
                if (item.backParams != '' && item.backParams != null){   //如果有参数
                    let urlData = JSON.parse(item.backParams);
                    if (urlData.approval != undefined){//确保有扶持配置
                        var _nstr = urlData.approval.replace(/'/g, '"');
                        let urlDataJson = _nstr.split(';');
                        let summary = [];
                        urlDataJson.forEach(key=>{
                            summary.push(JSON.parse(key))
                        })
                        this.approvalList.push({domId:item.menuDomId,approval:summary})
                    }
                }
                if (item.children !=undefined){   //如果有子节点
                    item.children.forEach(element=>{   //遍历二级菜单
                        if (element.backParams != '' && element.backParams != null){   //如果有参数
                            let urlData = JSON.parse(element.backParams);
                            if (urlData.approval != undefined){//确保有扶持配置
                                var _nstr = urlData.approval.replace(/'/g, '"');
                                let urlDataJson = _nstr.split(';');
                                let summary = [];
                                urlDataJson.forEach(key=>{
                                    summary.push(JSON.parse(key))
                                })
                                this.approvalList.push({domId:element.menuDomId,approval:summary})
                            }
                        }
                        if (element.children != undefined){  //如果有三级菜单
                            element.children.forEach(key=>{
                                if (key.backParams != '' && key.backParams != null){   //如果有参数
                                    let urlData = JSON.parse(key.backParams);
                                    if (urlData.approval != undefined){//确保有扶持配置
                                        var _nstr = urlData.approval.replace(/'/g, '"');
                                        let urlDataJson = _nstr.split(';');
                                        let summary = [];
                                        urlDataJson.forEach(key=>{
                                            summary.push(JSON.parse(key))
                                        })
                                        this.approvalList.push({domId:key.menuDomId,approval:summary})
                                    }
                                }
                            })
                        }
                    })
                }
            });
        },
        methods:{
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
            },
            menuRoutes(index, indexPath) {
                let menuSelect = this.recursiveFunction(index) //这里拿到点击的菜单
                this.numClick(menuSelect);//点击菜单时计数
                this.$emit('show', menuSelect);
            },
            recursiveFunction(menuId){ //递归遍历菜单  拿到点击的菜单
                var str = null
                const getStr = (list)=>{
                    list.forEach(function(row){
                        if(row.children){
                            getStr(row.children)
                        }else {
                            if (menuId == row.id){
                                str = row
                            }

                        }
                    })
                }
                getStr(this.sessionMenu)
                return str;
            }
        },
        watch:{

        },
        computed: {
            onRoutes() {
//                return this.$route.path.replace('/', '');
            }
        },
    }
</script>

<style>
    .approvalClass{
        color: red;
        position: relative;top:-1.5px
    }
</style>