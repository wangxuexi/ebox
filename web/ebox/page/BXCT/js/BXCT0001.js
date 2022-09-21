var vm = null;
const TENANT = PLAT_GET_NOW_TENANT();
const ORGCODE = PLAT_GET_ORGCODE();
const WEBPAGECODE = 'BXCT0001';
var cols_table = null; //表格id为table的表头配置存放仓库
Vue.use(httpVueLoader);
$(document).ready(function() {
	initVm(); //实例化vue
	initTableHead("table"); //初始化表头
	initDDOption(); //初始化数据字典下拉框
	queryTemplateFields();//查询模板配置的字段
});

//初始化数据字典下拉框
function initDDOption() {
	var ddoptionList = [{
		recAttrForm: vm.queryDdOptionArray, //表单对象
		recAttrValName: null, //接收数据字典的list 例如 stockerTypeList
		ddCode: null //数据字典code 例如 StockerType
	}]
	PLAT_INIT_DDOPTION_AUTO(ddoptionList, PLAT_GET_ORGCODE()); //用来查询的下拉框
	//PLAT_INIT_DDOPTION_FORINSERT_AUTO(ddoptionList, PLAT_GET_ORGCODE());//带数据字典id的下拉框
}



//初始化vue实例
function initVm() {
	vm = new Vue({
		el: "#root",
		store,
		components: {
            'fj-breadcrumb':'url:../../template/PAGE/fj-breadcrumb.vue',
            'fj-query':'url:../../template/PAGE/fj-query.vue',
            'fj-resultbody':'url:../../template/PAGE/fj-resultbody.vue',
            'fj-dialogtitle':'url:../../template/PAGE/fj-dialogtitle.vue',
            'fj-title':'url:../../template/PAGE/fj-title.vue',
        },
		data: {
			fileBaseConfig:{
				dialogUploadFileFormVisible:false,//上传附件的模态框
				operateUploadFileName:null,
				customTemplateVisible:false,//自定义模板下载弹出框
				checkAll: false,
				checkedFields: [],
				fieldOptions: null,
				isIndeterminate: true,
				fieldConfigStr:null,//需要下载的字段列表字符串
				acceptFileType:'.xlsx',//接收的文件类型
				dataForm_file_upload:{
					file:null,
				},
			},
			fieldIsDisabled: false, //控制表单字段是否可编辑
			orgCode: PLAT_GET_ORGCODE(), //组织编码
			dialogFormVisible: false, //新增查看修改模态框
			close: false, //设置点击模态框外部不消失
			size: "medium", //设置按钮大小
			top: "2vh", //设置模态框距离顶部距离
			pageNo: 1, //表格默认起始页
			operateType: null, //
			operateName: null, //
			queryForm: {
				id:null,//id
				brand:null,//品牌
				name:null,//名称
				alias:null,//别名
				tradePrice:null,//批发价
				retailPrice:null,//零售价
				col1:null,//预留字段1
				col2:null,//预留字段2
				col3:null,//预留字段3
				col4:null,//预留字段4
				col5:null,//预留字段5
				col6:null,//预留字段6
				col7:null,//预留字段7
				col8:null,//预留字段8
				col9:null,//预留字段9
				col10:null,//预留字段10
				sort:null,//排序
				remark:null,//备注
				enabled:null,//是否有效
				removed:null,//是否删除
				createUserId:null,//创建人ID
				createUserName:null,//创建人姓名
				createTime:null,//创建时间
				updateUserId:null,//更新人ID
				updateUserName:null,//更新人姓名
				updateTime:null,//更新时间

			},
			queryDdOptionArray: { //数据字典列表项
				stockerTypeList: [],
			},
			dataForm: {
				id:null,//id
				brand:null,//品牌
				name:null,//名称
				alias:null,//别名
				tradePrice:null,//批发价
				retailPrice:null,//零售价
				col1:null,//预留字段1
				col2:null,//预留字段2
				col3:null,//预留字段3
				col4:null,//预留字段4
				col5:null,//预留字段5
				col6:null,//预留字段6
				col7:null,//预留字段7
				col8:null,//预留字段8
				col9:null,//预留字段9
				col10:null,//预留字段10
				sort:null,//排序
				remark:null,//备注
				enabled:null,//是否有效
				removed:null,//是否删除
				createUserId:null,//创建人ID
				createUserName:null,//创建人姓名
				createTime:null,//创建时间
				updateUserId:null,//更新人ID
				updateUserName:null,//更新人姓名
				updateTime:null,//更新时间

			},
			rules: { //提交表单验证
				id: [{
					required: false,
					message: '请输入id',
					trigger: 'blur'
				}],
				brand: [{
					required: false,
					message: '请输入品牌',
					trigger: 'blur'
				}],
				name: [{
					required: false,
					message: '请输入名称',
					trigger: 'blur'
				}],
				alias: [{
					required: false,
					message: '请输入别名',
					trigger: 'blur'
				}],
				tradePrice: [{
					required: false,
					message: '请输入批发价',
					trigger: 'blur'
				}],
				retailPrice: [{
					required: false,
					message: '请输入零售价',
					trigger: 'blur'
				}],
				col1: [{
					required: false,
					message: '请输入预留字段1',
					trigger: 'blur'
				}],
				col2: [{
					required: false,
					message: '请输入预留字段2',
					trigger: 'blur'
				}],
				col3: [{
					required: false,
					message: '请输入预留字段3',
					trigger: 'blur'
				}],
				col4: [{
					required: false,
					message: '请输入预留字段4',
					trigger: 'blur'
				}],
				col5: [{
					required: false,
					message: '请输入预留字段5',
					trigger: 'blur'
				}],
				col6: [{
					required: false,
					message: '请输入预留字段6',
					trigger: 'blur'
				}],
				col7: [{
					required: false,
					message: '请输入预留字段7',
					trigger: 'blur'
				}],
				col8: [{
					required: false,
					message: '请输入预留字段8',
					trigger: 'blur'
				}],
				col9: [{
					required: false,
					message: '请输入预留字段9',
					trigger: 'blur'
				}],
				col10: [{
					required: false,
					message: '请输入预留字段10',
					trigger: 'blur'
				}],
				sort: [{
					required: false,
					message: '请输入排序',
					trigger: 'blur'
				}],
				remark: [{
					required: false,
					message: '请输入备注',
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
					message: '请输入创建人姓名',
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
					message: '请输入更新人姓名',
					trigger: 'blur'
				}],
				updateTime: [{
					required: false,
					message: '请输入更新时间',
					trigger: 'blur'
				}],

			},
			queryStr:null,//查询条件值
			columnList: null,
			fcdata:{}//存放业务功能的数据
		},
		computed: { //获取vuex数据
			count() {
				return store.state.cssSettings
			}
		},
		methods: {
			handleExceed(files, fileList) {//超过文件数量狗子
				this.$message.warning("请勿选择多个文件！");
			},
			handleChange(file, fileList){//选择文件钩子
				this.fileBaseConfig.dataForm_file_upload.file = file.raw;
			},
			handleRemove(file, fileList){//文件移除钩子
				this.fileBaseConfig.dataForm_file_upload.file = null;
			},
			importFile(){//上传导入文件
				uploadFile();
			},
			confirmImport(){
				if(this.fileBaseConfig.dataForm_file_upload.file == null){
					notify.warning("请先上传文件！")
				}else{
					confirmImport();//确认导入
				}
			},
			dialogOpened(){
				this.$refs['file-upload'].clearFiles();//上传文件模态框打开后
			},
			customTemplateConfig(){
				//自定义模板下载
				this.fileBaseConfig.customTemplateVisible = true;
			},
			handleCheckAllChange(val) {
				let _checkedFields = _.pluck(this.fileBaseConfig.fieldOptions,"fieldEn");
				this.fileBaseConfig.checkedFields = val ? _checkedFields : [];
				this.fileBaseConfig.isIndeterminate = false;
			},
			handleCheckedFieldsChange(value) {
				let checkedCount = value.length;
				this.fileBaseConfig.checkAll = checkedCount === this.fileBaseConfig.fieldOptions.length;
				this.fileBaseConfig.isIndeterminate = checkedCount > 0 && checkedCount < this.fileBaseConfig.fieldOptions.length;
			},
			bindExport(){
				//设置导出路径
				$("#form").attr("action", SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/exportTable"); //导出路径
				$("#form").submit(function(e) {
					vm.queryForm.orgCode = PLAT_GET_ORGCODE();//设置组织编码
					//绑定显示列点击事件
					vm.queryStr = JSON.stringify(vm.queryForm);
					vm.columnList = JSON.stringify($("#table").bootstrapTable('getVisibleColumns'));
				});
				//绑定模板下载
				$("#formTemplate").attr("action", SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/downloadTemplate"); //导出路径
				//确认下载模板
				$("#formCustomTemplate").attr("action", SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/downloadCustomTemplate"); //导出路径
				$("#formCustomTemplate").submit(function(e) {
					//根据已经选择的字段，过滤出配置
					//获取所有字段配置的en值
					let _fieldConfigStr = _.filter(vm.fileBaseConfig.fieldOptions,function(item){
						return _.contains(vm.fileBaseConfig.checkedFields, item.fieldEn);
					})
					vm.fileBaseConfig.fieldConfigStr = JSON.stringify(_fieldConfigStr);
					vm.fileBaseConfig.customTemplateVisible = false;
				});
			},
			insert() { //新增

				//清空form表单
				$.each(vm.dataForm, function(index, val) {
					vm.dataForm[index] = null;
				})
				this.operateType = EDITENUM_ADD;
				vm.dataForm.orgCode = PLAT_GET_ORGCODE();
				this.dialogFormVisible = true;
				if(this.$refs['dataForm'] != null){
					this.$refs['dataForm'].clearValidate();//清空表单校验
				}
			},
			deleteById() { //删除
				let allSelectedList = $('#table').bootstrapTable('getAllSelections'); //获取选中的行
				let pageNo = $('#table').bootstrapTable('getOptions').pageNumber; //获取当前页码
				//获取每一页的行数
				var pagesize=$('#table').bootstrapTable("getOptions").pageSize;
				//获取总数据条数
				var totalRows=$('#table').bootstrapTable("getOptions").totalRows;
				//获取总页数
				var pages=$('#table').bootstrapTable("getOptions").totalPages;

				//如果当前页面是最后一页，并且选中的条数加上前几页的总条数 小于等于 总数据量，则意味着最后一页的数据都删除了，页码向前一页
				if(pageNo == pages && allSelectedList.length+(pageNo-1)*pagesize <= totalRows && pageNo != 1){
					vm.pageNo = pageNo -1;
				}else{
					vm.pageNo = pageNo;
				}

				if(allSelectedList == null || allSelectedList.length == 0) {
					this.$message.warning('请选择需要删除的行！');
					return false;
				} else {
					let ids = "";
					$.each(allSelectedList, function(index, val) {
						ids += val.id + ",";
					})
					this.$confirm('此操作将永久删除'+allSelectedList.length+'条数据, 是否继续?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						deleteById(ids); //执行删除
					}).catch(() => {
						this.$message({
							type: 'info',
							message: '已取消删除'
						});
					});
				}
			},
			query() { //查询
				this.pageNo = 1;
				queryTableValue();
			},
			confirmOperate(formName) { //弹出层，新增修改确认按钮点击事件
				if(this.operateType != EDITENUM_QUERY){
					this.$refs[formName].validate((valid) => {
						if (valid) {
							confirmOperate();
							this.dialogFormVisible = false;
						} else {
							this.$message.warning('请检查必填项！');;
							return false;
						}
					});
				}else{
					this.dialogFormVisible = false;
				}
			}
		},
		watch: {
			operateType: {
				handler(newValue, oldValue) {
					if(newValue == EDITENUM_ADD) {
						this.fieldIsDisabled = false;
						this.operateName = '新增';
					} else if(newValue == EDITENUM_UPDATE) {
						this.fieldIsDisabled = false;
						this.operateName = '修改';
					} else if(newValue == EDITENUM_QUERY){
						this.fieldIsDisabled = true;
						this.operateName = "查看详情";
					}
				},
				deep: true
			},

		}
	});
}

//初始化表头
function initTableHead(tableDomId) {
	let params = {};
	params.tenant = TENANT;
	params.orgCode = PLAT_GET_ORGCODE();
	params.applicationCode = APPLICATION_CODE;
	params.webPageCode = WEBPAGECODE;
	params.tableDomId = tableDomId;
	let _url = TABLE_COL_SETTINGS_URL;
	$.ajax({
		//接口地址
		url: _url,
		type: 'POST',
		dataType: "JSON",
		data: params,
		success: function(data) {
			let str = data;
			str = str.replace(/'/g, '"');
			cols_table = $.parseJSON(str);
			$.each(cols_table, function(index, val) {
				if(val.formatter == "initRow") {
					val.formatter = function(value, row, i) {
						//let pageNo = $('#table').bootstrapTable('getOptions').pageNumber; //获取当前页码
						//获取每一页的行数
						//var pagesize=$('#table').bootstrapTable("getOptions").pageSize;
						//return (pageNo-1) * pagesize + i+1;
						//如果是后端分页，放开上方注释，如果是前端分页使用默认输出
						return i + 1;
					};
				}
				if(val.formatter == "f_opt"){
					val.formatter = function(value, row, i) {
						return `
							<a class="a_opt"  style="color:${vm.count.button.bgc};"  onclick="viewDetail('${row.id}')" >详情</a>
							<a class="a_opt"  style="color:${vm.count.button.bgc};"  onclick="update('${row.id}')" >修改</a>
						`
					}
				}
				//此处自定义回调函数
			});
			//查询表格数据
			queryTableValue();
		},
		error: function(data) {
			notify.error(NOTIFY_SYS_ERROR);
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
		text: TABLE_LOADING_TEXT,
		spinner: 'el-icon-loading',
		background: 'rgba(255, 255, 255, 0.7)',
	});
	vm.queryForm.orgCode = PLAT_GET_ORGCODE();
	let params = vm.queryForm;
	let url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/queryAll";
	$.ajax({
		url: url,
		type: "POST",
		async: true,
		data:params,
		dataType: "json",
		xhrFields: {
			withCredentials: xhrFieldsState
		},
		success: function(data) {
			if(data.code == RESULT_SUCCESS) {
				$('#table').bootstrapTable('destroy');
				/*展示列表*/
				let tables = $('#table').bootstrapTable({
					showColumnsToggleAll:true,
					striped: true, //是否显示行间隔色
					cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
					pagination: true, //是否显示分页（*）
					sortable: true, //是否启用排序
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
					uniqueId: "id", //每一行的唯一标识，一般为主键列
					showToggle: false, //是否显示详细视图和列表视图的切换按钮
					cardView: false, //是否显示详细视图
					detailView: false, //是否显示父子表
					showExport: true,
					data: data.data,
					columns: cols_table
				});
				loading.close();
			}else{
				notify.error(data.msg,data.data);
			}
		},error: function(){
			notify.error(NOTIFY_SYS_ERROR);
		}
	});
}



/**
 * 分页查询表格数据
 */
function queryTableValueByPage() {
	let url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/queryAllByPage";
	$('#table').bootstrapTable('destroy');
	/*展示列表*/
	let tables = $('#table').bootstrapTable({
		url:url,
		method: "POST",
		contentType: "application/x-www-form-urlencoded",
		showColumnsToggleAll:true,
		striped: true, //是否显示行间隔色
		cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: true, //是否显示分页（*）
		sortable: false, //是否启用排序
		sortOrder: "asc", //排序方式
		sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber: vm.pageNo, //初始化加载第一页，默认第一页,并记录
		pageSize: 10, //每页的记录行数（*）
		pageList: [10, 20, 50, 100], //可供选择的每页的行数（*）
		search: false, //是否显示表格搜索
		strictSearch: false,
		showColumns: true, //是否显示所有的列（选择显示的列）
		showRefresh: false, //是否显示刷新按钮
		minimumCountColumns: PLAT_GET_MIN_COLUMN_SIZE(cols_table), //最少允许的列数
		clickToSelect: true, //是否启用点击选中行
		//height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId: "id", //每一行的唯一标识，一般为主键列
		showToggle: false, //是否显示详细视图和列表视图的切换按钮
		cardView: false, //是否显示详细视图
		detailView: false, //是否显示父子表
		showExport: true,
		queryParamsType: "",
		dataField: "data", //这是返回的json数组的key.默认好像是"rows".这里只有前后端约定好就行
		queryParams: function queryParams(param) { //设置查询参数
			vm.queryForm.orgCode = PLAT_GET_ORGCODE();
			let params = vm.queryForm;
			params.pageNo = param.pageNumber;
			params.pageSize = param.pageSize;
			return params;
		},
		responseHandler: function(res) {
			if(res.code == RESULT_SUCCESS) {
				var data = res.data;
				return {
					total: data.total,
					data: data.result
				};
			}else{
				notify.error(res.msg,res.data);
				return false;
			}
		},
		columns: cols_table
	});
}


/**
 * 更新 
 * @param {Object} id
*/
function update(id){
	if(vm.$refs['dataForm'] != null){
		vm.$refs['dataForm'].clearValidate();//清空表单校验
	}
	$.each(vm.dataForm, function(index, val) {
		vm.dataForm[index] = null;
	});//清空历史数据
	let pageNo = $('#table').bootstrapTable('getOptions').pageNumber; //获取当前页码
	vm.pageNo = pageNo
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
		success: function(data) {
			if(data.code == RESULT_SUCCESS) {
				$.each(vm.dataForm, function(index, val) {
					vm.dataForm[index] = data.data[index];
				})
				//获取当前选择行的数据
				vm.dialogFormVisible = true;
			} else {
				notify.error(data.msg,data.data);
			}
		},
		error: function(data) {
			notify.error(NOTIFY_SYS_ERROR);
		}
	});
}


/**
 * 查看详情
 * @param {Object} id
 */
function viewDetail(id){
	if(vm.$refs['dataForm'] != null){
		vm.$refs['dataForm'].clearValidate();//清空表单校验
	}
	$.each(vm.dataForm, function(index, val) {
		vm.dataForm[index] = null;
	});//清空历史数据
	vm.operateType = EDITENUM_QUERY; //设置操作类型为修改
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
		success: function(data) {
			if(data.code == RESULT_SUCCESS) {
				$.each(vm.dataForm, function(index, val) {
					vm.dataForm[index] = data.data[index];
				})
				//获取当前选择行的数据
				vm.dialogFormVisible = true;
			} else {
				notify.error(data.msg,data.data);
			}
		},
		error: function(data) {
			notify.error(NOTIFY_SYS_ERROR);
		}
	});
}



/**
 * 新增、修改
 */
function confirmOperate() {
	let params = vm.dataForm;
	let url = null;
	if(vm.operateType == EDITENUM_ADD) { //新增
		url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/insert";
	} else if(vm.operateType == EDITENUM_UPDATE) { //修改
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
		success: function(data) {
			if(data.code == RESULT_SUCCESS) {
				notify.success(data.msg);
				queryTableValue();
			} else {
				notify.error(data.msg,data.data);
			}
		},
		error: function(data) {
			notify.error(NOTIFY_SYS_ERROR);
		}
	});

}

function deleteById(ids) {
	let params = {};
	params.id = ids;
	let url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/deleteById";;

	$.ajax({
		url: url,
		type: "POST",
		async: true,
		data: params,
		dataType: "json",
		xhrFields: {
			withCredentials: xhrFieldsState
		},
		success: function(data) {
			if(data.code == RESULT_SUCCESS) {
				notify.success(data.msg);
				queryTableValue();
			} else {
				notify.error(data.msg,data.data);
			}
		},
		error: function(data) {
			notify.error(NOTIFY_SYS_ERROR);
		}
	});
}

/**
 * 初始化空表头 
 * @param {Object} tableId
 */
function initEmptyTable(tableId){
	//查询表格数据
	$('#'+tableId).bootstrapTable('destroy');
		/*展示列表*/
	let tables = $('#'+tableId).bootstrapTable({
		showColumnsToggleAll:true,
		striped: true, //是否显示行间隔色
		cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: true, //是否显示分页（*）
		sortable: true, //是否启用排序
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
		uniqueId: "id", //每一行的唯一标识，一般为主键列
		showToggle: false, //是否显示详细视图和列表视图的切换按钮
		cardView: false, //是否显示详细视图
		detailView: false, //是否显示父子表
		showExport: true,
		data: [],
		columns: cols_table
	});
}

/**
 * 上传附件
 */
function uploadFile(projectId) {
	$.each(vm.fileBaseConfig.dataForm_file_upload, function(index, val) {
		vm.fileBaseConfig.dataForm_file_upload[index] = null;
	})
	vm.fileBaseConfig.dialogUploadFileFormVisible = true;
	vm.fileBaseConfig.operateUploadFileName = "导入文件";

}



/**
 * 完成最后导入
 */
function confirmImport(){
	//提交文件
	var formData = new FormData();
	$.each(vm.fileBaseConfig.dataForm_file_upload,function(index,val){
		if(val != null){
			formData.append(index,val);
		}
	})
	let url = SERVER_IP_PORT + SERVER_PREFIX  + WEBPAGECODE +"/importFile";;
	$.ajax({
		type: "POST",           //因为是传输文件，所以必须是post
		url: url,         //对应的后台处理类的地址
		data: formData,
		processData: false,
		contentType: false,
		success: function (data) {
			data = JSON.parse(data);
			if(data.code == RESULT_SUCCESS){
				notify.success(data.msg,data.data)
				queryTableValue();
				vm.fileBaseConfig.dialogUploadFileFormVisible = false;
				vm.$refs['file-upload'].clearFiles();

			}else if (data.code == RESULT_ERROR){
				notify.error(data.msg)
			}else{
				notify.warning(data.msg)
			}
		},
		error: function(data) {
			notify.error(NOTIFY_SYS_ERROR);
		}
	});
}



/**
 * 查询自定义模板的字段列表
 */
function queryTemplateFields(){
	let url = SERVER_IP_PORT + SERVER_PREFIX  + WEBPAGECODE +"/queryTemplateFields";;
	$.ajax({
		type: "POST",           //因为是传输文件，所以必须是post
		url: url,         //对应的后台处理类的地址
		processData: false,
		contentType: false,
		success: function (data) {
			data = JSON.parse(data);
			if(data.code == RESULT_SUCCESS){
				vm.fileBaseConfig.fieldOptions = data.data;
			}else if (data.code == RESULT_ERROR){
				notify.error(data.msg)
			}else{
				notify.warning(data.msg)
			}
		},
		error: function(data) {
			notify.error(NOTIFY_SYS_ERROR);
		}
	});
}

