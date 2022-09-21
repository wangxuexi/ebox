var vm = null;
var zTreeObj;
var zTreeObj1;
const TENANT = PLAT_GET_NOW_TENANT();
const ORGCODE = PLAT_GET_ORGCODE();
const WEBPAGECODE = 'ECSY0008';
var cols_table = null; //表格id为table的表头配置存放仓库
//是否作废枚举
const IS_TO_VOID_ENUM = [{//code和msg与后端的保持一致
    "code": 1,
    "msg": "是"
}, {
    "code": 0,
    "msg": "否"
}];
$(document).ready(function () {
    initVm(); //实例化vue
    //设置导出路径
    $("#form").attr("action", SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/exportTable"); //导出路径
    $("#form").submit(function (e) {
        //绑定显示列点击事件
        vm.queryStr = JSON.stringify(vm.queryForm);
        vm.columnList = JSON.stringify($("#table").bootstrapTable('getVisibleColumns'));
    });
    queryOrgcode();
    initDDOption(); //初始化数据字典下拉框




});

//初始化数据字典下拉框
function initDDOption() {
    var ddoptionList = [{
        recAttrForm: vm.queryDdOptionArray, //表单对象
        recAttrValName: null, //接收数据字典的list 例如 stockerTypeList
        ddCode: null //数据字典code 例如 StockerType
    }]
    PLAT_INIT_DDOPTION_AUTO(ddoptionList, ORGCODE);
}


//初始化vue实例
function initVm() {
    vm = new Vue({
        el: "#root",
        store,
        data: {
            orgCode: ORGCODE, //组织编码
            dialogFormVisible: false, //新增查看修改模态框
            close: false, //设置点击模态框外部不消失
            size: "medium", //设置按钮大小
            top: "2vh", //设置模态框距离顶部距离
            pageNo: 1, //表格默认起始页
            operateType: null, //
            operateName: null, //
			enums: { // 枚举
            	isToVoidEnums: [], // 是否作废枚举
			},
            webResource:{
                delete:{
                    display:null,
                    disabled:null,
                },
                insert:{
                    display:null,
                    disabled:null,
                },
                document:{
                    display:null,
                    disabled:null,
                },
                share:{
                    display:null,
                    disabled:null,
                }
            },
            queryForm: {
                id: null,//
                ddid: null,//字典id
                parentDdid: null,//父项字典值ID
                orgCode: null,//组织编码
                code: null,//编码,冗余
                name: null,//值
                remark: null,//描述
                sortNo: null,//排序码，步长为5
                isToVoid: null,//是否作废1=是，0=否
                enabled: null,//是否有效
                removed: null,//是否删除
                createUserId: null,//创建人ID
                createUserName: null,//创建人
                createTime: null,//创建时间
                updateUserId: null,//更新人ID
                updateUserName: null,//更新人
                updateTime: null,//更新时间
            },
            queryDdOptionArray: { //数据字典列表项
                stockerTypeList: [],
            },
            dataForm: {
                id: null,//
                ddid: null,//字典id
                parentDdid: null,//父项字典值ID
                orgCode: null,//组织编码
                code: null,//编码,冗余
                name: null,//值
                remark: null,//描述
                sortNo: null,//排序码，步长为5
                isToVoid: null,//是否作废1=是，0=否
                enabled: null,//是否有效
                removed: null,//是否删除
                createUserId: null,//创建人ID
                createUserName: null,//创建人
                createTime: null,//创建时间
                updateUserId: null,//更新人ID
                updateUserName: null,//更新人
                updateTime: null,//更新时间
            },
            rules: { //提交表单验证
                id: [{
                    required: false,
                    message: '请输入',
                    trigger: 'blur'
                }],
                ddid: [{
                    required: false,
                    message: '请输入字典id',
                    trigger: 'blur'
                }],
                parentDdid: [{
                    required: false,
                    message: '请输入父项字典值ID',
                    trigger: 'blur'
                }],
                orgCode: [{
                    required: false,
                    message: '请输入组织编码',
                    trigger: 'blur'
                }],
                code: [{
                    required: false,
                    message: '请输入编码,冗余',
                    trigger: 'blur'
                }],
                name: [{
                    required: true,
                    message: '请输入值',
                    trigger: 'blur'
                }],
                remark: [{
                    required: false,
                    message: '请输入描述',
                    trigger: 'blur'
                }],
                sortNo: [{
                    required: false,
                    message: '请输入排序码，步长为5',
                    trigger: 'blur'
                }],
                isToVoid: [{
                    required: true,
                    message: '请选择是否作废',
                    trigger: 'blur'
                }],
                enabled: [{
                    required: false,
                    message: '请输入是否有效',
                    trigger: 'blur'
                }],
                removed: [{
                    required: false,
                    message: '请输入是否删除',
                    trigger: 'blur'
                }],
                createUserId: [{
                    required: false,
                    message: '请输入创建人ID',
                    trigger: 'blur'
                }],
                createUserName: [{
                    required: false,
                    message: '请输入创建人',
                    trigger: 'blur'
                }],
                createTime: [{
                    required: false,
                    message: '请输入创建时间',
                    trigger: 'blur'
                }],
                updateUserId: [{
                    required: false,
                    message: '请输入更新人ID',
                    trigger: 'blur'
                }],
                updateUserName: [{
                    required: false,
                    message: '请输入更新人',
                    trigger: 'blur'
                }],
                updateTime: [{
                    required: false,
                    message: '请输入更新时间',
                    trigger: 'blur'
                }],
            },
            queryStr: null,//查询条件值
            columnList: null,
            drawer: false,
            drawerTree: false,
            deawerSize: '90%',
            orgList: [],//组织数组
            parentId: '', // 选中节点id
            parentName: '',//选中节点name
            parentChildren: false,
            treeDate: [],//选中节点数据
            pNode: '',//选中节点的父节点
            pNodeId: '',//选中节点的父节点Id
            orgCodeTree: '',//选择的组织
            orgChange: [],//选中的组织
            shareCode: '',//部分分享还是全部分享  1，全部分享  2部分分享
            pNodeList: [],//父节点下拉框
            changeIds: '',//需要分享的数据字典值的id
            drawTitle: '',//抽屉标题
        },
        computed: { //获取vuex数据
            count() {
                return store.state.cssSettings
            }
        },
		created() {
        	this.enums.isToVoidEnums = IS_TO_VOID_ENUM;
		},
        methods: {
            insert() { //新增

                if (this.parentId == '' || this.parentId == null) {
                    this.$message.warning('请先选择需要新增的节点！');
                } else {
                    //清空form表单
                    $.each(vm.dataForm, function (index, val) {
                        vm.dataForm[index] = null;
                        vm.dataForm.isToVoid = IS_TO_VOID_ENUM[1].code
                    })
                    this.operateType = EDITENUM_ADD;
                    this.dialogFormVisible = true;
                    $.ajax({
                        url: SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/getParentValue",
                        type: "POST",
                        data: {'ddid': vm.pNodeId, 'orgCode': vm.orgCodeTree},
                        async: true,
                        dataType: "json",
                        xhrFields: {
                            withCredentials: xhrFieldsState
                        },
                        success: function (data) {
                            if (data.code == '1') {
                                vm.pNodeList = data.data;
                            } else {
                                vm.$message.error(data.msg);
                            }
                        },
                        error: function (data) {
                            vm.$message.error('失败');
                        }
                    });
                }
            },
            deleteById() { //删除
                let allSelectedList = $('#table').bootstrapTable('getAllSelections'); //获取选中的行
                let pageNo = $('#table').bootstrapTable('getOptions').pageNumber; //获取当前页码
                vm.pageNo = pageNo;
                if (allSelectedList == null || allSelectedList.length == 0) {
                    this.$message.warning('请选择需要作废的行！');
                    return false;
                } else {
                    let ids = "";
                    $.each(allSelectedList, function (index, val) {
                        ids += val.id + ",";
                    })
                    this.$confirm('此操作将作废' + allSelectedList.length + '条数据, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        deleteById(ids); //执行删除
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消'
                        });
                    });
                }
            },
            query() { //查询
                queryTableValue();
            },
            confirmOperate(formName) { //弹出层，新增修改确认按钮点击事件
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        confirmOperate();
                        this.dialogFormVisible = false;
                    } else {
                        this.$message.warning('请检查必填项！');
                        return false;
                    }
                });
            },
            openedDrawer(){
                PLAT_INIT_AU_WEBDOM(
                    vm.webResource,//步骤1）中定义的变量
                    TENANT,//租户编码
                    APPLICATION_CODE,//应用编码
                    WEBPAGECODE,//页面号
                    PLAT_GET_AUTHORITY_ID()//当前用户的权限组id
                );
            },
            orgClick(item) {
                console.log(item);
                this.drawer = true;
                this.drawTitle = '数据字典(' + item.name + ')';
                initZtree();
                this.orgCodeTree = item.orgCode;
                this.dataForm.orgCode = item.orgCode;
                this.orgChange = item;
                this.parentId = '';
                initTableHead('table');

            },
            documentAdd() {   //批量分享

                let allSelectedList = $('#table').bootstrapTable('getAllSelections'); //获取选中的行
                let pageNo = $('#table').bootstrapTable('getOptions').pageNumber; //获取当前页码
                vm.pageNo = pageNo;
                if (allSelectedList == null || allSelectedList.length == 0) {
                    this.$message.warning('请选择需要分享的行！');
                    return false;
                } else {
                    this.drawerTree = true;
                    this.shareCode = '2';
                    initZtree1();
                    let ids = "";
                    $.each(allSelectedList, function (index, val) {
                        ids += val.id + ",";
                    })
                    this.changeIds = ids;
                }
            },
            share() {  //全部分享

                this.$confirm('此操作将覆盖目标组织下所有的数据字典值, 是否继续?（慎用！慎用！！慎用！！！）', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.drawerTree = true;
                    this.shareCode = '1';
                    initZtree1();
                }).catch(() => {

                });




            },
            isOk() {
                if (this.shareCode == "1") {  //全部分享

                    this.$confirm('此操作将把' + this.orgChange.name + '组织下所有数组字典值分享至所选组织,并清空目标组织的所有数据字典，是否继续?（慎用！慎用！！慎用！！！）', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        const loading = this.$loading({
                            lock: true,
                            text: '正在分享中，请稍候。。。',
                            spinner: 'el-icon-loading',
                            background: 'rgba(0, 0, 0, 0.7)'
                        });
                        setTimeout(() => {
                            let checkedNodes = zTreeObj1.getCheckedNodes();
                            let ids = "";
                            checkedNodes.forEach(item => {
                                ids = ids + item.orgCode + ','
                            })
                            $.ajax({
                                url: SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/shareAllDdvalueList2Org",
                                type: "POST",
                                data: {'orgCode': ids, 'nowOrgCode': vm.orgCodeTree},
                                async: true,
                                dataType: "json",
                                xhrFields: {
                                    withCredentials: xhrFieldsState
                                },
                                success: function (data) {
                                    if (data.code == '1') {
                                        vm.drawerTree = false;
                                        loading.close();
                                        vm.$notify({
                                            title: '成功',
                                            message: data.msg,
                                            type: 'success'
                                        });
                                    } else {
                                        loading.close();
                                        vm.$message.error(data.msg);
                                    }
                                },
                                error: function (data) {
                                    loading.close();
                                    vm.$message.error('失败');
                                }
                            });
                        }, 200);
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消'
                        });
                    });
                } else if (this.shareCode == "2") {  //部分分享
                    let checkedNodes = zTreeObj1.getCheckedNodes();
                    let ids = "";
                    checkedNodes.forEach(item => {
                        ids = ids + item.orgCode + ','
                    })
                    $.ajax({
                        url: SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/shareSelectDdvalueList2Org",
                        type: "POST",
                        data: {'orgCode': ids, 'ids': vm.changeIds},
                        async: true,
                        dataType: "json",
                        xhrFields: {
                            withCredentials: xhrFieldsState
                        },
                        success: function (data) {
                            if (data.code == '1') {
                                vm.drawerTree = false;
                                vm.$notify({
                                    title: '成功',
                                    message: data.msg,
                                    type: 'success'
                                });
                            } else {
                                vm.$message.error(data.msg);
                            }
                        },
                        error: function (data) {
                            vm.$message.error('失败');
                        }
                    });
                }
            }

        },
        watch: {
            operateType: {
                handler(newValue, oldValue) {
                    if (newValue == EDITENUM_ADD) {
                        this.operateName = '新增';
                    } else if (newValue == EDITENUM_UPDATE) {
                        this.operateName = '修改'
                    }
                },
                deep: true
            },

        }
    });
}


function initZtree() {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        callback: {    //第一步
            onClick: zTreeOnClick1
        }
    };

    $.ajax({
        url: SERVER_IP_PORT + SERVER_PREFIX + "ECSY0007/queryListByTenant",
        type: "POST",
        data: {'tenant': TENANT},
        async: true,
        dataType: "json",
        xhrFields: {
            withCredentials: xhrFieldsState
        },
        success: function (data) {
            if (data.code == '1') {
                zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data.data);
                zTreeObj.expandAll(true);
            } else {
                vm.$message.error(data.msg);
            }
        },
        error: function (data) {
            vm.$message.error('失败');
        }
    });

    function zTreeOnClick1(event, treeId, treeNode) {       //第二步
        vm.parentId = treeNode.id;
        vm.dataForm.ddid = treeNode.id;
        vm.parentName = treeNode.name;
        vm.treeDate = treeNode;
        if (treeNode.children) {
            vm.parentChildren = false
        } else {
            vm.parentChildren = true
        }
        var pNode = treeNode.getParentNode();
        if (pNode == null) {
            vm.pNode = '';
            vm.pNodeId = '';
        } else {
            vm.pNode = pNode.name;
            vm.pNodeId = pNode.id;
        }
        initTableHead('table');
    }
}


function initZtree1() {
    var setting1 = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        check: {
            enable: true,//显示复选框
            chkStyle: "checkbox"
        },
        callback: {    //第一步
            onClick: zTreeOnClick2
        }
    };

    $.ajax({
        url: SERVER_IP_PORT + SERVER_PREFIX + "/queryByTenant",
        type: "POST",
        data: {'tenant': TENANT},
        async: true,
        dataType: "json",
        xhrFields: {
            withCredentials: xhrFieldsState
        },
        success: function (data) {
            if (data.code == '1') {
                let list = [];
                data.data.forEach(item => {
                    if (item.level == '1' && item.orgCode != vm.orgCodeTree) {
                        list.push(item)
                    }
                });
                zTreeObj1 = $.fn.zTree.init($("#treeDemo1"), setting1, list);
                zTreeObj1.expandAll(true);
            } else {
                vm.$message.error(data.msg);
            }
        },
        error: function (data) {
            vm.$message.error('失败');
        }
    });

    function zTreeOnClick2(event, treeId, treeNode) {       //第二步


    }
}

var queryOrgcode = () => {
    $.ajax({
        url: SERVER_IP_PORT + SERVER_PREFIX + "/queryByTenant",
        type: "POST",
        data: {'tenant': TENANT},
        async: true,
        dataType: "json",
        xhrFields: {
            withCredentials: xhrFieldsState
        },
        success: function (data) {
            if (data.code == '1') {
                let list = [];
                data.data.forEach(item => {
                    if (item.level == '1') {
                        list.push(item)
                    }
                })
                vm.orgList = list;
            } else {
                vm.$message.error(data.msg);
            }
        },
        error: function (data) {
            vm.$message.error('失败');
        }
    });
};

//初始化表头
function initTableHead(tableDomId) {
    let params = {};
    params.tenant = TENANT;
    params.orgCode = ORGCODE;
    params.applicationCode = APPLICATION_CODE;
    params.webPageCode = WEBPAGECODE;
    params.tableDomId = tableDomId;
    params.applicationCode = APPLICATION_CODE;
    let _url = TABLE_COL_SETTINGS_URL;
    $.ajax({
        //接口地址
        url: _url,
        type: 'POST',
        dataType: "JSON",
        data: params,
        success: function (data) {
            let str = data;
            str = str.replace(/'/g, '"');
            cols_table = $.parseJSON(str);
            $.each(cols_table, function (index, val) {
                if (val.formatter == "initRow") {
                    val.formatter = function (value, row, i) {
                        return i + 1;
                    };
                }
                if (val.formatter == "f_opt") {
                    val.formatter = function (value, row, i) {
                        return `
							<i style="color:${vm.count.button.bgc};" 
								class="el-icon-setting i_opt" 
								onclick="update('${row.id}')" 
							></i>
						`
                    }
                }
                if (val.formatter == "f_isToVoid") {
                    val.formatter = function (value, row, i) {
                        if (value == 1) {
                            return '作废';
                        } else if (value == 0) {
                            return '正常';
                        } else {
                            return value;
                        }
                    }
                }
                //此处自定义回调函数
            });
            //查询表格数据
            queryTableValue();
        },
        error: function (data) {
            vm.$message.error(data);
        }
    });
}

/**
 * 查询表格数据
 */
function queryTableValue() {
    //查询表格数据
    const loading = vm.$loading({
        lock: true,
        text: '正在加载数据请稍候！',
        spinner: 'el-icon-loading',
        background: 'rgba(255, 255, 255, 0.7)',
    });
    let url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/queryDDvalueListByDdidAndOrgCode";
    $.ajax({
        url: url,
        data: {"ddid": vm.parentId, 'orgCode': vm.orgCodeTree},
        type: "POST",
        async: true,
        dataType: "json",
        xhrFields: {
            withCredentials: xhrFieldsState
        },
        success: function (data) {
            if (data.code == 1) {
                $('#table').bootstrapTable('destroy');
                /*展示列表*/
                let tables = $('#table').bootstrapTable({
                    showColumnsToggleAll: true,
                    striped: true, //是否显示行间隔色
                    cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                    pagination: true, //是否显示分页（*）
                    sortable: false, //是否启用排序
                    sortOrder: "asc", //排序方式
                    sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
                    pageNumber: vm.pageNo, //初始化加载第一页，默认第一页,并记录
                    pageSize: 10, //每页的记录行数（*）
                    pageList: [10, 20, 50, 100], //可供选择的每页的行数（*）
                    search: true, //是否显示表格搜索
                    strictSearch: false,
                    showColumns: true, //是否显示所有的列（选择显示的列）
                    showRefresh: false, //是否显示刷新按钮
                    minimumCountColumns: PLAT_GET_MIN_COLUMN_SIZE(cols_table), //最少允许的列数
                    clickToSelect: true, //是否启用点击选中行
                    //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                    uniqueId: "ID", //每一行的唯一标识，一般为主键列
                    showToggle: true, //是否显示详细视图和列表视图的切换按钮
                    cardView: false, //是否显示详细视图
                    detailView: false, //是否显示父子表
                    showExport: true,
                    data: data.data,
                    columns: cols_table
                });
                loading.close();
            } else {
                loading.close();
                vm.$message.error(data.msg);
            }
        },
        error: function (data) {
            loading.close();
            vm.$message.error('查询失败');
        }
    });
}


/**
 * 更新
 * @param {Object} id
 */
function update(id) {
    $.each(vm.dataForm, function (index, val) {
        vm.dataForm[index] = null;
    });//清空历史数据


    $.ajax({
        url: SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/getParentValue",
        type: "POST",
        data: {'ddid': vm.pNodeId, 'orgCode': vm.orgCodeTree},
        async: true,
        dataType: "json",
        xhrFields: {
            withCredentials: xhrFieldsState
        },
        success: function (data) {
            if (data.code == '1') {
                vm.pNodeList = data.data;
            } else {
                vm.$message.error(data.msg);
            }
        },
        error: function (data) {
            vm.$message.error('失败');
        }
    });


    let pageNo = $('#table').bootstrapTable('getOptions').pageNumber; //获取当前页码
    vm.pageNo = pageNo;
    vm.operateType = EDITENUM_UPDATE; //设置操作类型为修改
    let url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/queryById";
    let params = {};
    params.id = id;
    $.ajax({
        url: url,
        type: "POST",
        async: true,
        data: params,
        dataType: "json",
        xhrFields: {
            withCredentials: xhrFieldsState
        },
        success: function (data) {
            if (data.code == RESULT_SUCCESS) {
                $.each(vm.dataForm, function (index, val) {
                    vm.dataForm[index] = data.data[index];
                })
                //获取当前选择行的数据
                vm.dialogFormVisible = true;
            } else {
                vm.$message.error(data.msg);
            }
        },
        error: function (data) {
            vm.$message.error(data);
        }
    });
}

/**
 * 新增、修改
 */
function confirmOperate() {


    let params = vm.dataForm;
    let url = null;
    if (vm.operateType == EDITENUM_ADD) { //新增
        vm.dataForm.ddid = vm.parentId;
        vm.dataForm.orgCode = vm.orgCodeTree;
        vm.dataForm.isToVoid = '0';
        url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/insert";
    } else if (vm.operateType == EDITENUM_UPDATE) { //修改
        url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/update";
    }
    $.ajax({
        url: url,
        type: "POST",
        async: true,
        data: params,
        dataType: "json",
        xhrFields: {
            withCredentials: xhrFieldsState
        },
        success: function (data) {
            if (data.code == RESULT_SUCCESS) {
                vm.$notify({
                    title: '成功',
                    message: data.msg,
                    type: 'success'
                });
                queryTableValue();
            } else {
                vm.$message.error(data.msg);
            }
        },
        error: function (data) {
            vm.$message.error(data);
        }
    });

}

function deleteById(ids) {
    let params = {};
    params.id = ids;
    let url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/toVoidDDvalue";

    $.ajax({
        url: url,
        type: "POST",
        async: true,
        data: params,
        dataType: "json",
        xhrFields: {
            withCredentials: xhrFieldsState
        },
        success: function (data) {
            if (data.code == RESULT_SUCCESS) {
                vm.$notify({
                    title: '成功',
                    message: data.msg,
                    type: 'success'
                });
                queryTableValue();
            } else {
                vm.$message.error(data.msg);
            }
        },
        error: function (data) {
            vm.$message.error(data);
        }
    });
}

/**
 * 初始化空表头
 * @param {Object} tableId
 */
function initEmptyTable(tableId) {
    //查询表格数据
    $('#' + tableId).bootstrapTable('destroy');
    /*展示列表*/
    let tables = $('#' + tableId).bootstrapTable({
        showColumnsToggleAll: true,
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: false, //是否启用排序
        sortOrder: "asc", //排序方式
        sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: vm.pageNo, //初始化加载第一页，默认第一页,并记录
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 20, 50, 100], //可供选择的每页的行数（*）
        search: true, //是否显示表格搜索
        strictSearch: false,
        showColumns: true, //是否显示所有的列（选择显示的列）
        showRefresh: false, //是否显示刷新按钮
        minimumCountColumns: PLAT_GET_MIN_COLUMN_SIZE(cols_table), //最少允许的列数
        clickToSelect: true, //是否启用点击选中行
        //height: 500,//行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID", //每一行的唯一标识，一般为主键列
        showToggle: true, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        showExport: true,
        data: [],
        columns: cols_table
    });
}

