var vm = null;
Vue.use(httpVueLoader);

/**
 * 左侧菜单栏 url:对传过来的url进行ajax请求,加载菜单数据(适用二级菜单)
 */
Vue.component('fj-navmenu', {
    props: ["url"],
    data () {
        return {
            menuData:this.url,
            unique:true,
            isCollapse:false,

        }
    },
    template: `<div class="fj-navmenu-container">
            <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse" :unique-opened="unique"  @select="menuClick">
                <el-submenu v-for="(list,index) in menuData"   :key="index"  :index="list.menuName"  v-if="list.children && list.children.length>0">
                    <template slot="title" >
                        <span slot="title"  style="font-weight: 600"><i :class="list.menuIconUrl" style="position: relative;top:-1.5px"></i>&nbsp;{{list.menuName}}
                        <template v-if="list.menuDomId != ''">
                            <span :id="list.menuDomId" class="approvalClass"></span>
                        </template>
                        
                        </span>
                    </template>
                    <template v-if="list.children">
                            <div v-for="(menu,index) in list.children" :key="index" :index="menu.menuName">
                                <template v-if="menu.children ">
                                    <el-submenu :index="menu.menuName" :key="index">
                                    <span slot="title"><i :class="menu.menuIconUrl" style="position: relative;top:-1.5px"></i>&nbsp;{{menu.menuName}}
                                        <template v-if="menu.menuDomId != ''">
                                            <span :id="menu.menuDomId"  class="approvalClass"></span>
                                        </template>
                                    </span>
                                    <el-menu-item :index="chile.menuUrl+'*'+chile.menuName+'*'+chile.menuIsNewTab+'*'+chile.id" :key="index" v-for="(chile,index) in menu.children"  @click="numClick(chile)">{{chile.menuName}}
                                    <template v-if="chile.menuDomId != ''">
                                            <span :id="chile.menuDomId"  class="approvalClass"></span>
                                        </template>
                                    </el-menu-item>
                                    </el-submenu>
                                </template>
                                <template v-else=" !menu.children ">
                                    <el-menu-item-group >
                               
                                        <el-menu-item  :index="menu.menuUrl+'*'+menu.menuName+'*'+menu.menuIsNewTab+'*'+menu.id" :key="index"><i :class="menu.menuIconUrl" @click="numClick(menu)"></i>&nbsp;{{menu.menuName}}
                                        <template v-if="menu.menuDomId != ''">
                                            <span :id="menu.menuDomId"  class="approvalClass"></span>
                                        </template>
                                        </el-menu-item>
                                    </el-menu-item-group>
                                </template>
                            </div>
                    </template>
                </el-submenu>
                <el-menu-item v-else :index="list.menuUrl+'*'+list.menuName+'*'+list.menuIsNewTab+'*'+list.id" @click="numClick(list)">
                        <template slot="title">
                        <span  style="font-weight: 600"><i :class="list.menuIconUrl" style="position: relative;top:-1.5px"></i>&nbsp;{{list.menuName}}
                        <template v-if="list.menuDomId != ''">
                                            <span :id="list.menuDomId"  class="approvalClass"></span>
                                        </template>
                        </span>
                    </template>
                </el-menu-item>
            </el-menu>
                 </div> `,

    methods: {
        handleOpen(key, keyPath) {
        },
        handleClose(key, keyPath) {
        },
        menuClick(index, indexPath) {
          
            var srcPath = indexPath[indexPath.length-1].split('*');
            this.$emit('func',srcPath)
            if (srcPath[2] == false || srcPath[2] == 'false'){
                localStorage.setItem("paths",JSON.stringify(indexPath));
            }else if (srcPath[2] == true || srcPath[2] == 'true'){
                window.open(srcPath[0]);
            }
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
        url: {
            handler(newValue, oldValue) {
                this.menuData = newValue
            },
            deep: true
        }
    }

});

$(function(){
    initVue();
    initHeight();

})

var initVue = () =>{
    vm = new Vue({
        el:"#root",
        data() {
            return {
                dynamicHight:'',//菜单高度
                contentHight:0,//右侧iframe高度
                transDate:[],//菜单数据
                editableTabsValue: '',//
                editableTabs: [],
                iframesHight:'',//右侧内容区域高度
                approvalList:[],//审批构造的数组
            }
        },
        mounted() {

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
        created() {
            this.transDate = JSON.parse(GET_THREE_MENU()).children;
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
            let localPath = [];
            let menuUrlItem = ''; //菜单URL
            let menuNameItem = ''; //菜单名字
            let getUrl = []; //url参数
            let backParamsItem = []; //url参数
            let menuId = []; //id
            if (tool.isInList(this.transDate[0],'children')){   //如果有子菜单
                if (tool.isInList(this.transDate[0].children,'children')) {   //如果有子菜单
                    menuUrlItem = this.transDate[0].children[0].children[0].menuUrl;
                    menuNameItem = this.transDate[0].children[0].children[0].menuName;
                    getUrl =  getQueryList(this.transDate[0].children[0].children[0].menuUrl);
                    backParamsItem = this.transDate[0].children[0].children[0].backParams;
                    menuId = this.transDate[0].children[0].children[0].id;
                }else {
                    menuUrlItem = this.transDate[0].children[0].menuUrl;
                    menuNameItem = this.transDate[0].children[0].menuName;
                    getUrl =  getQueryList(this.transDate[0].children[0].menuUrl);
                    backParamsItem = this.transDate[0].children[0].backParams;
                    menuId = this.transDate[0].children[0].id;
                }
            }else {
                menuUrlItem = this.transDate[0].menuUrl;
                menuNameItem = this.transDate[0].menuName;
                getUrl =  getQueryList(this.transDate[0].menuUrl);
                backParamsItem = this.transDate[0].backParams;
                menuId = this.transDate[0].id;
            }

            localPath.push(menuUrlItem + '*' + menuNameItem + '*' +'false'+'*'+menuId)
            localStorage.setItem("paths",JSON.stringify(localPath));
           
            //初始化时显示的右侧iframe区域
            if (getUrl.length>0 || menuUrlItem.indexOf("?") == -1){
                this.editableTabs.push({
                    title: menuNameItem,
                    name: menuNameItem,
                    pageCode:'',
                    content: `<iframe class="iframes"   :height="iframesHight" src=${menuUrlItem+'?v='+new Date().getTime()+'&'+MENU_BACKPARAMS_KEY+'='+TENANT_CODE+ '_' + APPLICATION_CODE +'_'+ menuId} frameborder="0"></iframe>`
                })

            }else {
                this.editableTabs.push({
                    title: menuNameItem,
                    name: menuNameItem,
                    pageCode:'',
                    content: `<iframe class="iframes"   :height="iframesHight" src=${menuUrlItem+'&v='+new Date().getTime()+'&'+MENU_BACKPARAMS_KEY+'='+TENANT_CODE+ '_' + APPLICATION_CODE +'_'+ menuId} frameborder="0"></iframe>`
                })
            }
            this.editableTabsValue = menuNameItem;
            this.contentHight = 0
            setTimeout(() => {
                this.editableTabs.forEach((item,index)=>{
                    if(item.name == this.editableTabsValue){
                        $(".iframes")[index].contentWindow.location.reload(); //
                    }
                })
                setTimeout(()=>{
                    let resizeHight = document.documentElement.clientHeight;
                    let headerResizeHight = $(".el-tabs__header").outerHeight(true);
                    $(".iframes").height(resizeHight-headerResizeHight);
                    this.iframesHight = resizeHight-headerResizeHight;
                    this.contentHight = resizeHight  + 'px';
                    $(window).resize(function () {      //当浏览器大小变化时
                        var resizeHight = $(window).height();
                        let headerResizeHight = $(".el-tabs__header").outerHeight(true);
                        $(".iframes").height(resizeHight-headerResizeHight);
                        this.iframesHight = resizeHight-headerResizeHight;
                        this.contentHight = resizeHight  + 'px';
                    });
                },100)
                    
                    
            }, 300);

            let parentData = {
                topType:'2'
            }
            //向父页面传值
            window.parent.postMessage(parentData, '*');
        },
        methods: {
            getMsgFormSon(data){
                let flag = true;
                this.editableTabs.forEach((value,i)=> {
                    if (this.editableTabs[i].name == data[1]){
                        flag = false;
                    }
                });
                if (flag){
                    let getUrl =  getQueryList(data[0]);
                    if (getUrl.length>0 || data[0].indexOf("?") == -1){
                        vm.editableTabs.push({
                            title: data[1],
                            name: data[1],
                            pageCode:'',
                            content: `<iframe class="iframes"   :height="iframesHight" src=${data[0]+'?v='+new Date().getTime()+'&'+MENU_BACKPARAMS_KEY+'='+ TENANT_CODE+ '_' + APPLICATION_CODE +'_'+data[3]} frameborder="0"></iframe>`
                        })

                    }else {
                        vm.editableTabs.push({
                            title: data[1],
                            name: data[1],
                            pageCode:'',
                            content: `<iframe class="iframes"   :height="iframesHight" src=${data[0]+'&v='+new Date().getTime()+'&'+MENU_BACKPARAMS_KEY+'='+ TENANT_CODE+ '_' + APPLICATION_CODE +'_'+data[3]} frameborder="0"></iframe>`
                        })
                    }
                }
                this.editableTabsValue = data[1];
                vm.contentHight = 0
                setTimeout(() => {
                    vm.editableTabs.forEach((item,index)=>{
                        if(item.name == vm.editableTabsValue){
                            $(".iframes")[index].contentWindow.location.reload(); //
                        }
                    })
                    setTimeout(()=>{
                        addHeight();
                    },100)
                }, 300);
            },
            handleTabsEdit(targetName, action) {
                /*if (action === 'add') {
                    let newTabName = ++this.tabIndex + '';
                    this.editableTabs.push({
                        title: 'New Tab',
                        name: newTabName,
                        content: 'New Tab content'
                    });
                    this.editableTabsValue = newTabName;
                }*/
                if (action === 'remove') {
                    let tabs = this.editableTabs;
                    let activeName = this.editableTabsValue;
                    if (activeName === targetName) {
                        tabs.forEach((tab, index) => {
                            if (tab.name === targetName) {
                                let nextTab = tabs[index + 1] || tabs[index - 1];
                                if (nextTab) {
                                    activeName = nextTab.name;
                                }
                            }
                        });
                    }

                    this.editableTabsValue = activeName;
                    this.editableTabs = tabs.filter(tab => tab.name !== targetName);
                }
            },
            tabClick(e){
                let localPath = [];
                this.transDate.forEach(item=>{
                    if(e.label == item.menuName){
                        localPath.push(item.menuUrl + '*' + item.menuName + '*' +'false'+'*'+item.id)
                        localStorage.setItem("paths",JSON.stringify(localPath));
                    }
                })
            }
        },
        watch:{
            editableTabsValue: {
                handler(newVal, objVal) {
                    setTimeout(()=>{
                        this.editableTabs.forEach(item=>{
                            if(item.name == this.editableTabsValue){
                                console.log(item.pageCode)
                                if(item.pageCode != '' && item.pageCode != null && item.pageCode != undefined){  //如果页面号不为空
                                    let parentData = {
                                        operationType: 2, //操作类型
                                        orgTree:sessionStorage.getItem(item.pageCode + "_orgTree"),
                                    }
                                    //向父页面传值
                                    window.parent.postMessage(parentData, '*');
                                }else{
                                    let parentData = {
                                        operationType: 2, //操作类型
                                        orgTree:sessionStorage.getItem(TENANT_CODE + "_orgTree"),
                                    }
                                    //向父页面传值
                                    window.parent.postMessage(parentData, '*');
                                }
                            }
                        })
                    },300)
                    
                },
            }
        }
    })



    window.addEventListener('message', (event) => {//子获取父消息
        console.log(event)
        var data = event.data
        if (event.data.name != undefined){
            let flag = true;
            for (let k=0;k<vm.editableTabs.length;k++){
                if (vm.editableTabs[k].name == event.data.name){
                    flag = false;
                }
            }
            if (flag){
                let getUrl =  getQueryList(event.data.page);
                if (getUrl.length>0 || event.data.page.indexOf("?") == -1){
                    vm.editableTabs.push({
                        title: event.data.name,
                        name: event.data.name,
                        pageCode:'',
                        content: `<iframe class="iframes"    :height="iframesHight" src=${event.data.page+'?v='+new Date().getTime()} frameborder="0"></iframe>`
                    })
                }else {
                    vm.editableTabs.push({
                        title: event.data.name,
                        name: event.data.name,
                        pageCode:'',
                        content: `<iframe class="iframes"   :height="iframesHight" src=${event.data.page+'&v='+new Date().getTime()} frameborder="0"></iframe>`
                    })
                }
            }
            vm.editableTabsValue = event.data.name
            vm.contentHight = 0
            setTimeout(() => { 
                vm.editableTabs.forEach((item,index)=>{
                    if(item.name == vm.editableTabsValue){
                        $(".iframes")[index].contentWindow.location.reload(); //
                    }
                })
                setTimeout(()=>{
                    addHeight();
                },100)
                
            }, 300);

        }


		// 这里是当页面有自己单独的组织授权
		if(data.operationType == 1){
            sessionStorage.setItem(data.webPageCode + "_orgTree", JSON.stringify(data.orgDatas));
            let parentData = {
                operationType: 1, //操作类型
                orgData:data.orgData,
                webPageCode:data.webPageCode,
            }
            //向父页面传值
            window.parent.postMessage(parentData, '*');
            console.log(vm.editableTabsValue)
            vm.editableTabs.forEach(item=>{
                if(item.name == vm.editableTabsValue){
                    item.pageCode = data.webPageCode
                }
            })
		}

    }, false);
}

//初始化高度
var initHeight = () => {
    var initialHight = document.documentElement.clientHeight;
    vm.dynamicHight = initialHight  + 'px';
    vm.contentHight = initialHight  + 'px';
    $(window).resize(function () {      //当浏览器大小变化时
        var resizeHight = $(window).height();
        vm.dynamicHight = resizeHight  + 'px';
        vm.contentHight = initialHight  + 'px';
    });
}

//当打开新菜单的时候高度
var addHeight = () => {

    let resizeHight = document.documentElement.clientHeight;
    let headerResizeHight = $(".el-tabs__header").outerHeight(true);
    $(".iframes").height(resizeHight-headerResizeHight);
    vm.iframesHight = resizeHight-headerResizeHight;
    vm.contentHight = resizeHight  + 'px';
    $(window).resize(function () {      //当浏览器大小变化时
        var resizeHight = $(window).height();
        let headerResizeHight = $(".el-tabs__header").outerHeight(true);
        $(".iframes").height(resizeHight-headerResizeHight);
        vm.iframesHight = resizeHight-headerResizeHight;
        vm.contentHight = resizeHight  + 'px';
    });
}


function GET_APPLICATION_CODE(){
    return APPLICATION_CODE;
}


function initOrgTreeComplete(){
	vm.editableTabs.forEach((item,index)=>{
        if(item.name == vm.editableTabsValue){
            let childWindow = $(".iframes")[index].contentWindow; //
            childWindow.initOrgTreeComplete()
        }
    })
}