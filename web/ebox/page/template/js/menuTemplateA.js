/**
 * Created by Kevin on 2021/7/6.
 */
var vm = null;

Vue.use(httpVueLoader);




$(function () {
    initVue();
    // 注册

    initHeight();
})


var initVue = () =>{
    vm = new Vue({
        el:'#root',
        components: {
            'menu-template': 'url:../../template/MENU/menutemplateA.vue',
        },
        data:{
            selectMenu:null,
            dynamicHight:'',//菜单高度
            contentHight:0,//右侧iframe高度
            transDate:[],//菜单数据
            editableTabsValue: '',//
            editableTabs: [],
            iframesHight:'',//右侧内容区域高度
            approvalList:[],//审批构造的数组
        },
        mounted(){
            let sessionMenu = PLAT_GET_MENUTREE();
            if (sessionMenu[0].children){ //如果有子节点
                if(sessionMenu[0].children[0].children){ //如果子节点还有子节点
                    if (sessionMenu[0].children[0].children[0].menuUrl == '' || sessionMenu[0].children[0].children[0].menuUrl == null){
                        this.editableTabs.push({
                            title: sessionMenu[0].children[0].children[0].menuName,
                            name: sessionMenu[0].children[0].children[0].menuName,
                            pageCode:'',
                            content: `<iframe class="iframes"   :height="iframesHight" src='404.html' frameborder="0"></iframe>`
                        })
                    }else {
                        this.editableTabs.push({
                            title: sessionMenu[0].children[0].children[0].menuName,
                            name: sessionMenu[0].children[0].children[0].menuName,
                            pageCode:'',
                            content: `<iframe class="iframes"   :height="iframesHight" src=${sessionMenu[0].children[0].children[0].menuUrl+ '?'+MENU_BACKPARAMS_KEY+'=' + TENANT_CODE+ '_' + APPLICATION_CODE +'_'+ sessionMenu[0].children[0].children[0].id} frameborder="0"></iframe>`
                        })

                    }
                    this.editableTabsValue = sessionMenu[0].children[0].children[0].menuName;
                }else {
                    if (sessionMenu[0].children[0].menuUrl == '' || sessionMenu[0].children[0].menuUrl == null){
                        this.editableTabs.push({
                            title: sessionMenu[0].children[0].menuName,
                            name: sessionMenu[0].children[0].menuName,
                            pageCode:'',
                            content: `<iframe class="iframes"   :height="iframesHight" src='404.html' frameborder="0"></iframe>`
                        })
                    }else {
                        this.editableTabs.push({
                            title: sessionMenu[0].children[0].menuName,
                            name: sessionMenu[0].children[0].menuName,
                            pageCode:'',
                            content: `<iframe class="iframes"   :height="iframesHight" src=${sessionMenu[0].children[0].menuUrl+ '?'+MENU_BACKPARAMS_KEY+'=' + TENANT_CODE+ '_' + APPLICATION_CODE +'_'+ sessionMenu[0].children[0].id} frameborder="0"></iframe>`
                        })
                    }
                    this.editableTabsValue = sessionMenu[0].children[0].menuName;
                }
            }else {
                if (sessionMenu[0].menuUrl == '' || sessionMenu[0].menuUrl == null){
                    this.editableTabs.push({
                        title: sessionMenu[0].menuName,
                        name: sessionMenu[0].menuName,
                        pageCode:'',
                        content: `<iframe class="iframes"   :height="iframesHight" src='404.html' frameborder="0"></iframe>`
                    })
                }else {
                    this.editableTabs.push({
                        title: sessionMenu[0].menuName,
                        name: sessionMenu[0].menuName,
                        pageCode:'',
                        content: `<iframe class="iframes"   :height="iframesHight" src=${sessionMenu[0].menuUrl+ '?'+MENU_BACKPARAMS_KEY+'=' + TENANT_CODE+ '_' + APPLICATION_CODE +'_'+ sessionMenu[0].id} frameborder="0"></iframe>`
                    })
                }
                this.editableTabsValue = sessionMenu[0].menuName;
            }
            this.contentHight = 0
            setTimeout(() => {
                this.editableTabs.forEach((item,index)=>{
                    if(item.name == this.editableTabsValue){
                        $(".iframes")[index].contentWindow.location.reload(); //
                    }
                })
                setTimeout(()=>{
                    addHeight();
                },100)
            }, 300);

        },
        methods:{
            showFather (menu) {
                if (menu.menuIsNewTab == true || menu.menuIsNewTab == 'true'){
                    window.open(menu.menuUrl);
                }else {
                    this.selectMenu = menu;
                    console.log(this.selectMenu)
                    let flag = true;
                    this.editableTabs.forEach((value,i)=> {
                        if (this.editableTabs[i].name == menu.menuName){
                            flag = false;
                        }
                    });
                    if (flag){
                        if (menu.menuUrl == '' || menu.menuUrl == null) {  //如果点击的菜单路径为空
                            vm.editableTabs.push({
                                title: menu.menuName,
                                name: menu.menuName,
                                pageCode:'',
                                content: `<iframe class="iframes"   :height="iframesHight" src='404.html' frameborder="0"></iframe>`
                            })
                        }else {
                            let getUrl =  getQueryList(menu.menuUrl);
                            if (getUrl.length>0 || menu.menuUrl.indexOf("?") == -1){
                                vm.editableTabs.push({
                                    title: menu.menuName,
                                    name: menu.menuName,
                                    pageCode:'',
                                    content: `<iframe class="iframes"   :height="iframesHight" src=${menu.menuUrl+'?v='+new Date().getTime()+'&'+MENU_BACKPARAMS_KEY+'='+ TENANT_CODE+ '_' + APPLICATION_CODE +'_'+menu.id} frameborder="0"></iframe>`
                                })

                            }else {
                                vm.editableTabs.push({
                                    title: menu.menuName,
                                    name: menu.menuName,
                                    pageCode:'',
                                    content: `<iframe class="iframes"   :height="iframesHight" src=${menu.menuUrl+'&v='+new Date().getTime()+'&'+MENU_BACKPARAMS_KEY+'='+ TENANT_CODE+ '_' + APPLICATION_CODE +'_'+menu.id} frameborder="0"></iframe>`
                                })
                            }
                        }
                    }
                    this.editableTabsValue = menu.menuName;
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



            },

            handleTabsEdit(targetName, action) {  //点击
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