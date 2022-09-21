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
                            <span slot="title">{{ item.menuName }}</span>
                        </template>
                        <!-- 第二层 -->
                        <template v-for="subItem in item.children">
                            <!-- 如果第二层有子菜单，则继续循环 -->
                            <template v-if="subItem.children">
                                <el-submenu :index="subItem.id+''" :key="subItem.id">
                                    <template slot="title">
                                        <i :class="item.icon"></i>
                                        <span slot="title">{{ subItem.menuName }}</span>
                                    </template>
                                    <!-- <el-menu-item v-for="(threeItem,i) in subItem.subs" :key="i" :index="threeItem.index">{{ threeItem.title }}</el-menu-item> -->
                                    <!-- 第三层 -->
                                    <template v-for="subItem2 in subItem.children">
                                        <!-- 如果第三层有子菜单，则继续循环 -->
                                        <template v-if="subItem2.children">
                                            <el-submenu :index="subItem2.id+''" :key="subItem2.id">
                                                <template slot="title">
                                                    <i :class="item.icon"></i>
                                                    <span slot="title">{{ subItem2.menuName }}</span>
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
                                                                {{ subItem3.menuName }}
                                                            </template>
                                                            <el-menu-item v-for="(fiveItem,i) in subItem3.children" :key="i" :index="fiveItem.id+''">{{ fiveItem.menuName }}</el-menu-item>
                                                        </el-submenu>
                                                    </template>
                                                    <!-- 如果第四层没有子菜单 -->
                                                    <el-menu-item v-else :index="subItem3.id+''" :key="subItem3.id">{{ subItem3.menuName }}</el-menu-item>
                                                </template>
                                            </el-submenu>
                                        </template>
                                        <!-- 如果第三层没有子菜单 -->
                                        <el-menu-item v-else :index="subItem2.id+''" :key="subItem2.id">{{ subItem2.menuName }}</el-menu-item>
                                    </template>
                                </el-submenu>
                            </template>
                            <!-- 如果第二层没有子菜单 -->
                            <el-menu-item v-else :index="subItem.id+''" :key="subItem.id">{{ subItem.menuName }}</el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <!-- 如果第一层没有子菜单 -->
                <template v-else>
                    <el-menu-item :index="item.id+''" :key="item.id">
                        <i :class="item.icon"></i>
                        <span slot="title">{{ item.menuName }}</span>
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
            };
        },
        created(){
            this.sessionMenu = PLAT_GET_MENUTREE();
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
</style>