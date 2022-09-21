

/**
 * 
 * 公用组件的封装(标题，下拉框，日期控件 ，文本框，按钮，统一按element-ui的样式来使用)
 *              (注：表格按照bootstrap-table的样式来进行封装)
 */

/**
 * 日期控件 content:控件前面的文字内容
 * 
 */
/**
 * Created by Kevin on 2019/10/29.
 */
//头部
Vue.component('fjec-query', {
    props: [],
    template: `<div class="query">
                <slot name="query"></slot>
            </div>`,
});

//列表
Vue.component('fjec-body', {
    props: [],
    template: `<div class="resultTable" style="background-color: #fff;margin-top: 15px;padding: 0 10px;min-height: 180px">
                <slot name="info"></slot>
    </div>`,
});

//模态框
Vue.component('fjec-dialog', {
    props: [],
    template: `<div class="el-dialog-div">
            <slot name="dialog"></slot>
        </div>`,
});



Vue.component('fj-date', {
    props: ["content","center","name"],
    data: function () {
        return {
            value: '',
        }
    },
    template: `<div class="fj-date-container"  style="width: 100%" >
                    <div class="block">
                        <div style="width: 34%;float: left;line-height: 37px;overflow: hidden;height: 39px" :class="center">
                            <span class="demonstration">{{content}}</span>
                        </div>
                        <div style="width: 65%;float: left;margin-left: 1%">
                            <el-date-picker style="width: 100%"
                            v-model="value"
                            type="date"
                            :name="name"
                            placeholder="请选择日期">
                            </el-date-picker>
                        </div>
                    </div>
                </div>`,
});
/**
 * lable 加冒号
 */
Vue.component('fj-lable', {
    props: ["content","required"],
    template: `<span style="width: 34%;float: left;text-align: right;line-height: 37px;overflow: hidden;height: 39px;white-space: nowrap;text-overflow: ellipsis;" :title="content"><span v-show="required" style="color: red;">*</span>{{content}}：</span>`,
});

//不加冒号
Vue.component('fj-lables', {
    props: ["content"],
    template: `<span style="width: 34%;float: left;text-align: right;line-height: 37px;overflow: hidden;height: 39px;white-space: nowrap;text-overflow: ellipsis;" :title="content">{{content}}</span>`,
});



/**
 * lable 加冒号
 */
Vue.component('fj-title', {
    props: ["content","required"],
    template: `<span style="width:100%;display:inline-block;text-align: right;line-height: 37px;overflow: hidden;height: 39px;white-space: nowrap;text-overflow: ellipsis;" :title="content"><span v-show="required" style="color: red;">*</span>{{content}}：</span>`,
});

//不加冒号
Vue.component('fj-titles', {
    props: ["content"],
    template: `<span style="width:100%;display:inline-block;text-align: center;line-height: 37px;overflow: hidden;height: 39px;white-space: nowrap;text-overflow: ellipsis;" :title="content">{{content}}</span>`,
});
/**
 * 按钮 content:按钮内容，type:按钮类型(primary / success / warning / danger / info / text)，
 *      size：按钮大小（medium / small / mini)，id、class用于用户自定义样式
 * 
 * :style="{'color':btnCssConfigs.color,
                    'background-color':btnCssConfigs.bgc,'border-color':btnCssConfigs.bgc,'font-size':btnCssConfigs.font-size,
                    }"
 */
Vue.component('fj-btn', {
    props: ["content", "type", "size", "id"],
    data: function () {
        return {
            //btnCssConfigs: store.state.cssSettings.btn,
        }
    },
    template: `<div class="fj-button-container" style="float: right;max-width: 100%;margin-right: 5px">
                    <el-button :type="type" :size="size" :id="id" style="max-width: 100%">{{content}}</el-button>
                </div>`,

})


/**
 * 输入框 content:输入框前面label的值,value:输入框的值,type:输入框的类型(text:文本框,password:密码框)
 *        placeholder:输入框提示文本,disabled:是否禁用(取值为布尔类型false:不禁用,true:禁用,默认不禁用)
 *        name:用于form表单绑定,id、class用于用户自定义样式
 */
Vue.component('fj-input', {
    props: ["content","val","type","placeholder","disabled","name","id"],
    /*{
        content: String,
        value: String,
        type: String,
        placeholder: String,
        disabled: false,
        name: '',
        id: String,
    },*/
    data: function () {
        return {
            newVal: this.val,
            //inputCss: store.state.cssSettings.input
        }
    },
    template: ` <div style="width: 100%" class="fj-input-container">
                    <div style="width: 34%;float: left;text-align: right;line-height: 37px;overflow: hidden;height: 39px">
                        <span>{{content}}</span>
                    </div>
                    <div style="width: 65%;float: left;margin-left: 1%">
                    <el-input :placeholder="placeholder" :id="id" v-model="val" clearable :type="type" :disabled="disabled" :name="name" ></el-input>
                    </div>
                </div>`,
    watch: {
        val: function (oldVal, newVal) {
            //this.newVal = newVal
            this.$emit('subSee.partyName',newVal);
        },
        deep:true
    }
});


/**
 * 文本域 content:文本域前面label的值,placeholder:文本域提示文本,size:文本域大小(medium / small / mini),
 *       rows:文本域输入行数, placeholder:输入框提示文本,name:用于form表单绑定,id、class用于用户自定义样式
 */
Vue.component('fj-textarea', {
    props: ["content", "placeholder", "size", "rows", "name", "id"],
    data: function () {
        return {
            value: ''
        }
    },
    template: ` <div style=" position: relative;;margin-top:10px;margin-right:10px;width:100%;" class="fj-textarea-container">
                    <label style="position: absolute;top: 0; left: 0;width:96px;">{{content}}</label>
                    <div style="width:calc(100% - 86px);position: absolute;top: 0; right: 0;">
                    <el-input :placeholder="placeholder" :size="size" :id="id" :rows="rows" v-model="value" :name="name"  type="textarea"></el-input>
                    </div>
                </div>`,

})


/**
 * 下拉框 content:下拉框前面label的值,value:下拉框的值,url:对传过来的url进行ajax请求
 *   disabled:是否禁用(默认false),multiple:是否多选(默认false)
 *      
 */
Vue.component('fj-select', {
    props: {
        content: String,
        url: String,
        multiple: Boolean,
        disabled: Boolean,
        id: String
    },
    /*props: ["content", "url", "multiple", "disabled", "id"],*/
    data: function () {
        return {
            options: getData(this.url),
            value: ''
        }
    },
    template: ` <div style="width: 100%;" class="fj-select-container">
                    
                    <div style="width: 34%;float: left;line-height: 37px;text-align: right;overflow: hidden;height: 39px">
                        <span>{{content}}</span>
                    </div>
                    <div style="width: 65%;float: left;margin-left: 1%">
                        <el-select v-model="value" placeholder="请选择" clearable :multiple="multiple" :disabled="disabled" :id="id" style="width: 100%">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                      >
                      
                    </el-option>
                  </el-select>
                    </div>
                    
                  
                </div>`,

})

/**
 * cascader级联选择器 content:级联选择器前面label的值,url:对传过来的url进行ajax请求,
 * name:用于form表单的绑定,separator:选项分隔符(默认“/”)
 *      
 */
Vue.component('fj-cascader', {
    props: {
        content: String,
        url: String,
        name: String,
        filterable: Boolean,
        separator: String,
        id: String
    },
    data: function () {
        return {
            options: getData(this.url),
            selectedOptions: []
        };
    },
    template: ` <div style="margin-right:10px;width:100%;display:inline-block;" class="fj-cascader-container">
                    <label>{{content}}</label>
                    <el-cascader
                      expand-trigger="hover"
                      :options="options"
                      v-model="selectedOptions"
                       :name="name" 
                       clearable
                       :separator="separator">
                    </el-cascader>
                </div>`,

})

/**
 * 
 * @param {*} url 对传过来的url进行ajax请求，加载数据,将数据添加到下拉列表框里（下拉框,级联选择器）
 */


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
                        <span slot="title"  style="font-weight: 600"><i :class="list.menuIconUrl"></i>&nbsp;{{list.menuName}}</span>
                    </template>
                    <template v-if="list.children">
                            <div v-for="(menu,index) in list.children" :key="index" :index="menu.menuName">
                                <template v-if="menu.children ">
                                    <el-submenu :index="menu.menuName" :key="index">
                                    <span slot="title"><i :class="menu.menuIconUrl"></i>&nbsp;{{menu.menuName}}</span>
                                    <el-menu-item :index="chile.menuUrl+'*'+chile.menuName+'*'+chile.menuIsNewTab+'*'+chile.backParams" :key="index" v-for="(chile,index) in menu.children" >{{chile.menuName}}</el-menu-item>
                                    </el-submenu>
                                </template>
                                <template v-else=" !menu.children ">
                                    <el-menu-item-group >
                               
                                        <el-menu-item  :index="menu.menuUrl+'*'+menu.menuName+'*'+menu.menuIsNewTab+'*'+menu.backParams" :key="index"><i :class="menu.menuIconUrl"></i>&nbsp;{{menu.menuName}}</el-menu-item>
                                    </el-menu-item-group>
                                </template>
                            </div>
                    </template>
                </el-submenu>
                <el-menu-item v-else :index="list.menuUrl+'*'+list.menuName+'*'+list.menuIsNewTab+'*'+list.backParams">
                        <template slot="title">
                        <span  style="font-weight: 600"><i :class="list.menuIconUrl"></i>&nbsp;{{list.menuName}}</span>
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
            //console.log(index)
            //console.log(indexPath)
          
            var srcPath = indexPath[indexPath.length-1].split('*');
            this.$emit('func',srcPath)
            if (srcPath[2] == false || srcPath[2] == 'false'){
                localStorage.setItem("paths",JSON.stringify(indexPath));
                // $(".iframes").attr("src",srcPath[0]);
                //console.log(srcPath)
                //console.log(srcPath[0]);

                /*$.ajax({
                    url: SERVER_IP_PORT+SERVER_PREFIX+"collectMenuClick",
                    data:{"tenant":getCookie("TENANT"),"loginUser":getCookie("LOGINUSER"),"userId":localStorage.getItem("userNameRemember"),"menuName":srcPath[1]},
                    type: "POST",
                    async: true,
                    dataType: "json",
                    xhrFields: {
                        withCredentials: xhrFieldsState
                    },
                    success: function (data) {

                    }
                });*/
            }else if (srcPath[2] == true || srcPath[2] == 'true'){
                window.open(srcPath[0]);
            }
        }
    },
    watch:{
        url: {
            handler(newValue, oldValue) {
                this.menuData = newValue
                //console.log(newValue)
            },
            deep: true
        }
    }

});

/**
 * 面包屑
 */

Vue.component('fj-breadcrumb', {
    props: [],
    data: function () {
        return {
            path: Array,
        }
    },
    template: `<div class="fj-breadcrumb-container" style="width:100%;padding:12px 20px 10px 0;height:40px;color:red;line-height: 30px;background-color: rgb(245,245,245);border: 1px solid gainsboro">
                    <el-breadcrumb separator-class="el-icon-arrow-right" style="height: 100%;padding: 0 0 0 20px">
                        <el-breadcrumb-item v-for="(item,index) in path" :key="index">{{item}}</el-breadcrumb-item> <span @click="refresh" style="float: right;color: rgba(86, 86, 86, 0.75);cursor: pointer;"><i class="fa fa-refresh"></i>刷新</span>
                    </el-breadcrumb>
                    
                </div>`,
    methods: {
        refresh(){
            window.location.reload();
        }
    },
    created() {
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

    }
});




/**
 * 登录页面组件
 */

Vue.component('fj-login', {
    props: ['name'],
    data: function () {
        return {
            //name:'闵行经济技术开发区管理平台'
        }
    },
    template: `<div class="fj-login" style="width: 100%;height:100%;">
                <div style="margin:150px auto;">
                    <h2 style="text-align: center;color:red;font-size: 42px;">{{name}}</h2>
                    <form style="margin-top:30px;width: 100%;text-align: center;">
                        <div style=" position: relative;width:50%;margin:0 auto;">
                            <i class="el-icon-s-custom" style=" position: absolute; left: 29%; top: 10px;width:20px;"></i>
                            <input
                                style="width: 40%;height:36px;border-radius:21px;text-indent:16px;background-color:red;border: none;outline: none;padding: 0 20px;" />
                        </div>
                        <div style=" position: relative;width:50%;margin:20px auto;">
                            <i class="el-icon-s-custom" style=" position: absolute; left: 29%; top: 10px;width:20px"></i>
                            <input
                                style="width: 40%;height:36px;border-radius:21px;text-indent:16px;background-color:red;border: none;outline: none;padding: 0 20px;" />
                        </div>
                        <div style="margin-top: 30px;">
                            <button style="width: 126px;height:42px;outline:none;background-color:red;color:#fff;border:none;border-radius:21px;font-size:16px;">登录</button>
                        </div>
                        <div style="margin-top: 30px;">
                            <span>爱我中华/凝心聚力/爱党敬业</span>
                            </div>
                    </form>
                </div>
            </div>`,
})

/**
 * 首页头部组件 title:标题,name:登陆姓名
 */
Vue.component('fj-header', {
    props: ["title","name","urls","group","orgid","visibel","version","logourl"],
    data: function () {
        return {
            width:"100%",
            height:"78px",
            display:"flex",
            flexDirection:"row",
            orgDatas:null,
            props: { multiple: true },
            text:null,
            disabled:false,
            //getsUrl:'',
        }
    },
    /*<div  :style="{backgroundImage:'url(' + urls.bgc + ')',width: width,height: height,display:display,flexDirection:flexDirection,backgroundSize:'100% 78px',backgroundRepeat: 'no-repeat'}" class="fj-header">*/
    template: ` <div  style="height: 78px;width: 100%;display: flex;flexDirection:row" class="fj-header">
                    <div style="width: 12%;height: 78px;overflow: hidden">
                        <img :src="logourl" alt="" style="height: 50px;margin-right: 5%;float: right;margin-top: 14px">
                    </div>
                    <div style="color:#fff;font-size:32px;height:78px;line-height:78px;width:53%;overflow: hidden;">
                        <p style="display: inline-block;height: 78px;line-height: 78px;float: left;">{{title}}</p>
                        <!--<img src="../img/zszxlogo.png" alt="" style="height: 180px;margin-left: 5%;float: right;margin-top: -55px">-->
                    </div>
                    <!--<div style="color:#fff;font-size:32px;height:78px;line-height:78px;width:40%;overflow: hidden">
                        {{title}}{{urls.bgc}}
                    </div>-->
                    <div style="width:40%;height: 78px;line-height: 78px;overflow: hidden;color: #fff;text-align: right;padding-right: 5%">
                        <span>欢迎您：{{name}}&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <el-cascader ref="myCascader" style="width: 150px" :options="orgDatas" :collapse-tags="true" size="mini" :show-all-levels="false" :props="props" @change="changeCascader"  placeholder="默认选择所有组织" v-model="text" :disabled="disabled"  popper-class="sssss" dropDownVisible="true"></el-cascader>
                        <!--<div style="display:inline-block;">
                            <slot name="cascader"></slot>
                        </div>-->
                        <!--<button style="width: 76px;height:26px;border:none;outline:none;color:#fff;" @click="signOut"><i
                                class="el-icon-remove-outline" style="margin-right: 5px;"></i>退出</button>-->
                                &nbsp;&nbsp;&nbsp;
                                <i title="修改密码" class="fa fa-user-circle-o" style="color: #fff;cursor: pointer" @click="changePassword"></i>
                                &nbsp;&nbsp;&nbsp;
                                <i title="退出" class="fa fa-power-off" style="color: #fff;cursor: pointer" @click="signOut"></i>
                                <span style="margin-left: 15px;cursor: pointer"  @click="myclick">{{version}}</span>
                             
                                
                    </div>
                </div>`,
    methods: {
        myclick(){
            this.$emit('showbox',true);
        },
        signOut() {
            $.ajax({
                url: SERVER_IP_PORT+SERVER_PREFIX+"signout",
                type: "POST",
                async: true,
                dataType: "json",
                data:{"userName":"","tenant":getCookie("TENANT")},
                xhrFields: {
                    withCredentials: xhrFieldsState
                },
                success: function (data) {
                    if (data.code == "1"){
                        window.location.href = "login.html";
                    }else if(data.code == "0"){
                        alert(data.msg);
                    }
                }
            });
        },
        changePassword(){
            //this.$emit('fatherMethod');
            this.$emit('father',true);
            //$(".iframes").attr("src","index/changePassword.html");
            // let parentData = {
            //     page: 'index/changePassword.html',
            //     name: '修改密码'
            // };
            // window.parent.postMessage(parentData, '*');
        },
        changeCascader(){
            //document.getElementById('myframe').contentWindow.location.reload(true);
            let orgCodes = '';
            //document.getElementByclassName('iframes').contentWindow.location.reload(true);
            var myCascaderLength = this.$refs.myCascader.getCheckedNodes();
            let checkCode  = [];
            let checkname  = [];
            if (myCascaderLength.length != "0"){
                let myCascaderName = "";
                let xxx="";
                for (var j=0;j<myCascaderLength.length;j++){
                    myCascaderLength[j].pathNodes.forEach((item,i)=>{
                        checkCode.push(item.value);
                        checkname.push(item.label);
                    });
                }

                let checkCodes = unique(checkCode);
                let checknames = unique(checkname);
                checkCodes.forEach((value,v)=>{
                    xxx += value+",";
                });
                checknames.forEach((name,n)=>{
                    myCascaderName += name+",";
                });

                let myCascaderNames = myCascaderName.substring(0,myCascaderName.length - 1);
                sessionStorage.setItem("orgName", myCascaderNames);
                let yyy = xxx.substring(0,xxx.length - 1);
                sessionStorage.setItem("orgCode", yyy);
                orgCodes = yyy;
            }else if (myCascaderLength.length == "0"){
                let orgDatasAttr = this.orgDatas;
                console.log(orgDatasAttr)
                let resultid="";
                let resultname="";
                orgDatasAttr.forEach((item,i)=>{
                    if (item.children){
                        resultid+= item.value+",";
                        item.children.forEach((k,i)=>{
                            resultid+= k.value+",";
                        });
                    }else {
                        resultid+= item.value+",";
                    }
                });
                let resultArrs = resultid.substring(0,resultid.length - 1);
                sessionStorage.setItem("orgCode",resultArrs);
                /*取子节点的name*/
                orgDatasAttr.forEach((name,i)=>{
                    if (name.children){
                        resultname+= name.label+",";
                        name.children.forEach((n,i)=>{
                            resultname+= n.label+",";
                        });
                    }else {
                        resultname+= name.label+",";
                    }
                });
                let resultnames = resultname.substring(0,resultname.length - 1);
                sessionStorage.setItem("orgName", resultnames);
            }
        }
    },
    watch: {
        orgid: {
            handler(newValue, oldValue) {
                this.orgDatas = newValue;
                //取子节点id
                let orgDatasAttr = newValue;
                let resultid="";
                let resultname="";
                orgDatasAttr.forEach((item,i)=>{
                    if (item.children){
                        resultid+= item.value+",";
                        item.children.forEach((k,i)=>{
                            resultid+= k.value+",";
                        });
                    }else {
                        resultid+= item.value+",";
                    }
                });
                let resultArrs = resultid.substring(0,resultid.length - 1);
                sessionStorage.setItem("orgCode",resultArrs);
                /*取子节点的name*/
                orgDatasAttr.forEach((name,i)=>{
                    if (name.children){
                        resultname+= name.label+",";
                        name.children.forEach((n,i)=>{
                            resultname+= n.label+",";
                        });
                    }else {
                        resultname+= name.label+",";
                    }
                });
                let resultnames = resultname.substring(0,resultname.length - 1);
                sessionStorage.setItem("orgName", resultnames);

                console.log(resultArrs)
                console.log(resultnames)

            },
            //immediate: true
        },
        visibel:{
            handler(newValue, oldValue) {
                console.log(newValue)
                if (newValue==false){
                    this.$refs.myCascader.dropDownVisible = false;
                }
            }
        }
    },

});

/**
 * 获取所有叶子节点
 * @param arr
 */
function getAllChildren(arr,orgChildArr){

    $.each(arr ,function(index,val){
        if(val.children != null && val.children.length > 0){
            getAllChildren(val.children,orgChildArr);
        }else{
            orgChildArr.push(val.value);
        }
    } )
    return orgChildArr;
}

function getAllChildrenName(arr,orgChildName){

    $.each(arr ,function(index,val){
        if(val.children != null && val.children.length > 0){
            getAllChildren(val.children,orgChildName);
        }else{
            orgChildName.push(val.label);
        }
    } )
    return orgChildName;
}

//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}


/**
 * 查询框组件
 */

Vue.component('fj-query-container', {
    props: [],
    data: function () {
        return {

        }
    },
    template: `<div style="width: 100%;height:58px;background-color:bisque;line-height:58px;">
                <slot name="queryContainer"></slot>
            </div>`,

});
/**
 * 模态框小标题
 */

Vue.component('fj-dialogtitle', {
    props: ['name'],
    data: function () {
        return {

        }
    },
    template: `<p style="height: 35px;line-height: 35px;text-align: center;color: #235FCC;font-size: 16px;font-weight: 600;margin-top: 10px">{{name}}</p>`,

});
/**
 * 表格(按照bootstrap-table样式)
 */
Vue.component('fj-table', {
    props: ["url", "option", "tabledata", "columns"],
    data:function() {
        return {
            tableVal: this.tabledata,
            columnlength: this.columns,
            options:this.option,
        }
    },
    template: `<table class="table table-hover mytab" data-pagination-detail="false">
                    <slot name="theader"></slot>
               </table>`,
    watch: {
        option: function (newValue, oldValue) {
            this.options = newValue;
        },
        columns: function (newValue, oldValue) {
            this.columnlength = newValue;
// initTable(this.columnlength,this.tableVal);
        },
        tabledata: function (newValue, oldValue) {
            this.tableVal = newValue;
            initTable(this.columnlength,this.tableVal,this.options);

        },
    }

})

/**
 * tab页组件
 */
Vue.component('fj-nav', {
    props: ["list"],
    data: function () {
        return {
            tabList:this.list,
            width:100 / this.list.length
        }
    },
    template: ` <ul class="list" style="margin-top: 15px; list-style: none; width: 100%;background-color: #235FCC; height: 42px;font-size: 0;">
                    <li v-for="(item,index) in tabList" :key="index" :id="item.id"><input type="text" :value="item.pageCode"  style="display:none">{{item.title}}</li>
                </ul>`,
});

function initTable(columnslen,tableVal,options) {
    $(".mytab").bootstrapTable('destroy');
    /**
     * 定义bootstrap-table属性
     */
    $('.mytab').bootstrapTable({
// url: './json/tabel.json',
        queryParams: "queryParams",
        toolbar: "#toolbar",
        sidePagination: "client",//分页方式：客户端分页
        striped: true, // 是否显示行间隔色
        cache:false, // 是否使用缓存
        pageNumber:1, //初始化加载第一页，默认第一页，并记录
        pageList:[10,20,50,100],
        search: true,
        uniqueId: "id",
        pageSize: "5",
        sortName: "id,",
        sortOrder: "asc",
        showColumns:true,
        showRefresh: false,//刷新
        clickToSelect: true,//启用点击选中行
        pagination: true, // 是否分页
        sortable: true, // 是否启用排序
// showExtendedPagination:true,//显示扩展分页
        strictSearch: false,
        showToggle: true, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子
        columns: formatterOption(options),
        data: getData(tableVal),
    });
    function getData(val) {
        return val;
    }
    function formatterOption(options) {
        var newColumns = [];
        newColumns.push({
            field: 'SerialNumber',
            formatter: function (value, row, index) {
                return index + 1;
            },

        });
        if (options) {
            for (var i = 0; i < columnslen; i++) {
                newColumns.push({});
            }
            if (newColumns.length = columnslen) {
                newColumns.push({
                    title: '操作',
                    field: 'id',
                    align: 'center',
                    valign: 'middle',
                    // value：表示当前单元格中的值
                    // row：表示当前行
                    // index：表示当前行的下标
                    formatter: function (value, row, index) {
                        var newOptions = [];
                        var optionsDetails = [];
                        for (var i = 0; i < options.length; i++) {
                            newOptions.push(options[i].split(":")[0]);
                            optionsDetails.push(options[i].split(":")[1]);
                        }
                        var model = '';
                        $.each(newOptions, function (num, val) {
                            if (val == 'detail') {
                                var a = `<a  onclick="userDetail_onclick(${row.id})" style="color:#f55d54;cursor:pointer;">查看&nbsp;&nbsp;</a>`;
                                model += a;

                            }
                            if (val == 'delete') {
                                var b = `<a onclick="userDelete_onclick(${row.id})" style="color:#f55d54;cursor:pointer">删除&nbsp;&nbsp;</a>`;
                                model += b;

                            }
                            if (val == 'update') {
                                var c = `<a onclick="userUpdate_onclick(${row.id})" style="color:#f55d54;cursor:pointer">更新&nbsp;&nbsp;</a>`;
                                model += c;

                            }
                            if (val == 'updateState') {
                                var d = `<a onclick="userUpdateState_onclick(${row.id})" style="color:#f55d54;cursor:pointer">修改&nbsp;&nbsp;</a>`;
                                model += d;

                            }

                        })
                        return model;
                    }

                })

            }
            return newColumns;
        }
        else {
            return;
        }
    }

}


function getData(url) {
    var optionData;
    $.ajax({
        //请求方式
        type: "post",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        async: true,
        //请求地址
        url: url,
        //数据，json字符串
        // data : JSON.stringify(list),
        //请求成功
        success: function (result) {
            optionData = result.data;
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
        }
    });
    return optionData;
}


function unique (arr) {  //数组去重
    return Array.from(new Set(arr))
}