var vm = null;
const TENANT = PLAT_GET_NOW_TENANT();
const ORGCODE = PLAT_GET_ORGCODE();
const WEBPAGECODE = 'ECSY0007';
var cols_table = null; //表格id为table的表头配置存放仓库

$(document).ready(function() {
	initVm(); //实例化vue
	initZtree();

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
			queryForm: {
				id:null,//
				category:null,//分类 0:类型            1：数据字典
				level:null,//层级
				code:null,//编码
				name:null,//名称
				parentId:null,//父级id
				remark:null,//描述
				tenant:null,//租户编码
				orgCode:null,//组织编码
				enabled:null,//是否有效
				removed:null,//是否删除
				createUserId:null,//创建人ID
				createUserName:null,//创建人
				createTime:null,//创建时间
				updateUserId:null,//更新人ID
				updateUserName:null,//更新人
				updateTime:null,//更新时间
				sortNo:null,//排序码，步长为5
			},
			queryDdOptionArray: { //数据字典列表项
				stockerTypeList: [],
			},
			dataForm: {
				id:null,//
				category:null,//分类 0:类型            1：数据字典
				level:null,//层级
				code:null,//编码
				name:null,//名称
				parentId:null,//父级id
				remark:null,//描述
				tenant:null,//租户编码
				orgCode:null,//组织编码
				enabled:null,//是否有效
				removed:null,//是否删除
				createUserId:null,//创建人ID
				createUserName:null,//创建人
				createTime:null,//创建时间
				updateUserId:null,//更新人ID
				updateUserName:null,//更新人
				updateTime:null,//更新时间
				sortNo:null,//排序码，步长为5
			},
			rules: { //提交表单验证
				id: [{
					required: false,
					message: '请输入',
					trigger: 'blur'
				}],
				category: [{
					required: false,
					message: '请输入分类 0:类型            1：数据字典',
					trigger: 'blur'
				}],
				level: [{
					required: false,
					message: '请输入层级',
					trigger: 'blur'
				}],
				code: [{
					required: true,
					message: '请输入编码',
					trigger: 'blur'
				}],
				name: [{
					required: true,
					message: '请输入名称',
					trigger: 'blur'
				}],
				parentId: [{
					required: false,
					message: '请输入父级id',
					trigger: 'blur'
				}],
				remark: [{
					required: false,
					message: '请输入描述',
					trigger: 'blur'
				}],
				tenant: [{
					required: false,
					message: '请输入租户编码',
					trigger: 'blur'
				}],
				orgCode: [{
					required: false,
					message: '请输入组织编码',
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
				sortNo: [{
					required: false,
					message: '请输入排序码，步长为5',
					trigger: 'blur'
				}],
			},
			queryStr:null,//查询条件值
			columnList: null,
			parentId:'', // 选中节点id
			parentName:'',//选中节点name
			parentChildren:false,
			treeDate:[],//选中节点数据
			pNode:'',//选中节点的父节点
			drawer: false,//抽屉
		},
		computed: { //获取vuex数据
			count() {
				return store.state.cssSettings
			}
		},
		methods: {
			insert() { //新增
				//清空form表单
				$.each(vm.dataForm, function(index, val) {
					vm.dataForm[index] = null;
				})
				this.operateType = EDITENUM_ADD;
				this.dialogFormVisible = true;
			},
			deleteById() { //删除
				if(this.parentId == null || this.parentId == '') {
					this.$message.warning('请先选择需要删除的数据字典！');
					return false;
				} else {
					if (this.parentChildren == false){
						this.$message.warning('父节点不能删除！');
					}else {
						this.$confirm('此操作将删除此条数据字典, 是否继续?', '提示', {
							confirmButtonText: '确定',
							cancelButtonText: '取消',
							type: 'warning'
						}).then(() => {
							deleteById(); //执行删除
						}).catch(() => {
							this.$message({
								type: 'info',
								message: '已取消删除'
							});
						});
					}

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
						this.$message.warning('请检查必填项！');;
						return false;
					}
				});
			},
			update(){
				if (this.parentId  == '' || this.parentId  == null){
					this.$message.warning('请先选择需要修改的节点！');
				}else {
					confirmOperate();
				}

			},
			parentClick(){   //调整父级节点
				if (this.parentId  == '' || this.parentId  == null){
					this.$message.warning('请先选择需要调整的节点！');
				}else {
					this.drawer = true;
					var setting1 = {
						data: {
							simpleData: {
								enable: true,
								idKey: "id",
								pIdKey: "pId",
								rootPId: 0
							}
						},
						callback:{    //第一步
							onClick:zTreeOnClick2
						}
					};
					var zTreeObj1;
					$.ajax({
						url: SERVER_IP_PORT+SERVER_PREFIX+ WEBPAGECODE +"/queryListByTenant",
						type: "POST",
						data: {'tenant':TENANT},
						async: true,
						dataType: "json",
						xhrFields: {
							withCredentials: xhrFieldsState
						},
						success: function (data) {
							if(data.code == '1') {
								zTreeObj1 = $.fn.zTree.init($("#treeDemo1"), setting1, data.data);
								zTreeObj1.expandAll(true);
							} else {
								vm.$message.error(data.msg);
							}
						},
						error: function(data) {
							vm.$message.error('失败');
						}
					});
					function zTreeOnClick2(event, treeId, treeNode) {       //第二步
						$.ajax({
							url: SERVER_IP_PORT+SERVER_PREFIX+ WEBPAGECODE +"/update",
							type: "POST",
							data: {'parentId':treeNode.id,'id':vm.treeDate.id},
							async: true,
							dataType: "json",
							xhrFields: {
								withCredentials: xhrFieldsState
							},
							success: function (data) {
								if(data.code == '1') {
									vm.drawer = false;
									$.fn.zTree.destroy("treeDemo");
									initZtree();
									$.each(vm.queryForm, function(index, val) {
										vm.queryForm[index] = null;
									})
									vm.parentId=''; // 选中节点id
									vm.parentName='';//选中节点name
									vm.pNode='';//选中节点name
								} else {
									vm.$message.error(data.msg);
								}
							},
							error: function(data) {
								vm.$message.error('失败');
							}
						});
					}
				}

			}
		},
		watch: {
			operateType: {
				handler(newValue, oldValue) {
					if(newValue == EDITENUM_ADD) {
						this.operateName = '新增';
					} else if(newValue == EDITENUM_UPDATE) {
						this.operateName = '修改'
					}
				},
				deep: true
			},
			parentId(newValue, oldValue){
				if(newValue == '' || newValue==null){

				}else {
					update(newValue)
				}

			}
		}
	});
}

function initZtree(){
	var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		},
		callback:{    //第一步
			onClick:zTreeOnClick1
		}
	};
	var zTreeObj;
	$.ajax({
		url: SERVER_IP_PORT+SERVER_PREFIX+ WEBPAGECODE +"/queryListByTenant",
		type: "POST",
		data: {'tenant':TENANT},
		async: true,
		dataType: "json",
		xhrFields: {
			withCredentials: xhrFieldsState
		},
		success: function (data) {
			if(data.code == '1') {
				zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data.data);
				zTreeObj.expandAll(true);
			} else {
				vm.$message.error(data.msg);
			}
		},
		error: function(data) {
			vm.$message.error('失败');
		}
	});
	function zTreeOnClick1(event, treeId, treeNode) {       //第二步
		vm.parentId  = treeNode.id;
		vm.parentName  = treeNode.name;
		vm.treeDate = treeNode;
		if (treeNode.children){
			vm.parentChildren = false
		}else {
			vm.parentChildren = true
		}

		var pNode = treeNode.getParentNode();
		if (pNode == null){
			vm.pNode = '';
		}else {
			vm.pNode = pNode.name;
		}
	}
}



/**
 * 更新 
 * @param {Object} id
*/
function update(id){
	$.each(vm.queryForm, function(index, val) {
		vm.queryForm[index] = null;
	});//清空历史数据
	//let pageNo = $('#table').bootstrapTable('getOptions').pageNumber; //获取当前页码
	//vm.pageNo = pageNo;
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
				$.each(vm.queryForm, function(index, val) {
					vm.queryForm[index] = data.data[index];
				})
				//获取当前选择行的数据
				//vm.dialogFormVisible = true;
			} else {
				vm.$message.error(data.msg);
			}
		},
		error: function(data) {
			vm.$message.error(data);
		}
	});
}
/**
 * 新增、修改
 */
function confirmOperate() {
	let params = null;
	let url = null;
	if(vm.operateType == EDITENUM_ADD) { //新增
		params = vm.dataForm;
		url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/insert";
		vm.dataForm.tenant = TENANT;//租户
		vm.dataForm.parentId = vm.parentId;//父级id
		vm.dataForm.category = '0';//
	} else if(vm.operateType == EDITENUM_UPDATE) { //修改
		params = vm.queryForm;
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
				vm.$notify({
					title: '成功',
					message: data.msg,
					type: 'success'
				});
				$.fn.zTree.destroy("treeDemo");
				initZtree()
				vm.parentId=''; // 选中节点id
				vm.parentName='';//选中节点name
				vm.pNode='';//选中节点name
				$.each(vm.queryForm, function(index, val) {
					vm.queryForm[index] = null;
				});
			} else {
				vm.$message.error(data.msg);
			}
		},
		error: function(data) {
			vm.$message.error(data);
		}
	});


}

function deleteById() {

	let url = SERVER_IP_PORT + SERVER_PREFIX + WEBPAGECODE + "/deleteById";
	$.ajax({
		url: url,
		type: "POST",
		async: true,
		data: {'id':vm.parentId},
		dataType: "json",
		xhrFields: {
			withCredentials: xhrFieldsState
		},
		success: function(data) {
			if(data.code == RESULT_SUCCESS) {
				vm.$notify({
					title: '成功',
					message: data.msg,
					type: 'success'
				});
				$.fn.zTree.destroy("treeDemo");
				initZtree();
				$.each(vm.queryForm, function(index, val) {
					vm.queryForm[index] = null;
				})
				vm.parentId=''; // 选中节点id
				vm.parentName='';//选中节点name
				vm.pNode='';//选中节点name
			} else {
				vm.$message.error(data.msg);
			}
		},
		error: function(data) {
			vm.$message.error(data);
		}
	});
}

