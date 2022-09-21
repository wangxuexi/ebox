/**
 * 初始化租户选择下拉框
 */
function PLAT_GET_TENANT() {
	var tenant = null;
	var _url = SERVER_IP_PORT + SERVER_PREFIX + "FJSYSTN0001/queryAllTenant";
	$.ajax({
		//接口地址
		url: _url,
		type: 'GET',
		dataType: "JSON",
		async: false,
		success: function(data) {
			if (data.code == RESULT_SUCCESS) {
				tenant = data.data;
			}
		},
		error: function(returndata) {
			alert(data.msg)
		}
	});
	return tenant;
}
/**
 * 获取当前租户
 */
function PLAT_GET_NOW_TENANT() {
	var nowtenant = getCookie(TENANT_CODE + "_TENANT");
	if (nowtenant === null || nowtenant === "") nowtenant = 0;
	return nowtenant;
}

/**
 * 获取当前租户
 */
function PLAT_GET_UID(tenant) {
	var uId = null;
	if(tenant == null){
		uId = getCookie(TENANT_CODE + "_UID");
	}else {
		uId = getCookie(tenant + "_UID");
	}
	return uId;
}

/**
 * 将字符串转换成数组
 */
function PLAT_TRS2COLSETTING(str) {
	str = str.replace(/'/g, '"');
	var cols = [];
	if (str == null || str == "") {
		return cols;
	} else {
		cols = $.parseJSON(str);
	}

	return cols;
}


//获取orgCode
//获取orgCode
function PLAT_GET_ORGCODE(tenant) {
	if (tenant == null) {
		var orgCode = sessionStorage.getItem(TENANT_CODE + "_orgCode");
		return orgCode;
	} else {
		var orgCode = sessionStorage.getItem(tenant + "_orgCode");
		return orgCode;
	}
}
//获取orgName
function PLAT_GET_ORGNAME() {
	var orgName = sessionStorage.getItem(TENANT_CODE + "_orgName");
	return orgName;
}
//获取当前登录人账号信息
function PLAT_GET_USERNAME() {
	var userName = getCookie(TENANT_CODE+'_USERNAME');
	return userName;
}

//获取当前登录人姓名
function PLAT_GET_LOGINUSER() {
	var loginUser = getCookie(TENANT_CODE+'_LOGINUSER');
	return loginUser;
}

//获取当前登录人姓名
var PLAT_LOGIN_USER = () => getCookie(TENANT_CODE+'_LOGINUSER');

//获取当前登录人头像
var PLAT_HEAD_IMG = () => getCookie(TENANT_CODE+'_HEAD_IMG');

//获取用户所属组织
function PLAT_GET_USER_ORG(tenant) {
	if (tenant == null) {
		var orgCode = getCookie(TENANT_CODE + "_USER_ORG");
		return orgCode;
	} else {
		var orgCode = getCookie(tenant + "_USER_ORG");
		return orgCode;
	}
}


/**
 * 初始化数据字典
 * @param {Object} ddoptionList
 */
function PLAT_INIT_DDOPTION(ddoptionList, orgCode) {
	$.each(ddoptionList, function(index, val) {
		var params = {};
		params.code = val.ddCode;
		params.orgCode = orgCode;
		var url = SERVER_IP_PORT + SERVER_PREFIX + "sy/queryDDValuesByCode";
		$.ajax({
			url: url,
			type: "POST",
			dataType: "json",
			data: params,
			xhrFields: {
				withCredentials: xhrFieldsState
			},
			success: function(data) {
				if (data.code == 1) {
					var list = [];
					$.each(data.data, function(index, val) {

						if (val != null) {
							var optJson = {};
							optJson.value = val.name;
							optJson.label = val.name;
							list.push(optJson);
						}

					});
					val.recAttrForm[val.recAttrObjName][val.recAttrValName] = list;
				}
			}
		});
	});
}



/**
 * 初始化数据字典
 * @param {Object} ddoptionList
 */
function PLAT_INIT_DDOPTION_AUTO(ddoptionList, orgCode) {
	let code = [];
	$.each(ddoptionList, function(index, val) {
		//构建数据字典编码参数
		code.push(val.ddCode);
	});
	
	var params = {};
	params.code = code+"";
	params.orgCode = orgCode;
	var url = SERVER_IP_PORT + SERVER_PREFIX + "openApi/batchQueryDDValuesByCode";
	$.ajax({
		url: url,
		type: "POST",
		dataType: "json",
		data: params,
		xhrFields: {
			withCredentials: xhrFieldsState
		},
		success: function(data) {
			if (data.code == RESULT_SUCCESS) {
				let result = data.data;
				//遍历所有配置
				$.each(ddoptionList, function(index, val) {
					//根据返回数据集处理数据格式
					let list = [];
					//遍历返回的对应数据字典的列表
					$.each(result[val.ddCode],function(_index,_val){
						if(_val != null){
							var optJson = {};
							optJson.value = _val.name;
							optJson.label = _val.name;
							list.push(optJson);
						}
					})
					val.recAttrForm[val.recAttrValName] = list;
				});
			}
		}
	});
}

	
/**
 * 数据字典数组根据value值返回label值
 * @param {Object} selectList 
 * @param {Object} value
 */
function PLAT_SELECT_GET_LABEL(selectList, value) {
	for (var i = 0; i < selectList.length; i++) {
		var item = selectList[i]
		if (value == item.value) {
			return item.label
		}
	}
}





/**
 * 初始化数据字典 for insert
 * @param {Object} ddoptionList
 */
function PLAT_INIT_DDOPTION_FORINSERT_AUTO(ddoptionList, orgCode) {
	let code = [];
	$.each(ddoptionList, function(index, val) {
		//构建数据字典编码参数
		code.push(val.ddCode);
	});
	
	var params = {};
	params.code = code+"";
	params.orgCode = orgCode;
	var url = SERVER_IP_PORT + SERVER_PREFIX + "openApi/batchQueryDDValuesByCodeForInsert";
	$.ajax({
		url: url,
		type: "POST",
		dataType: "json",
		data: params,
		xhrFields: {
			withCredentials: xhrFieldsState
		},
		success: function(data) {
			if (data.code == RESULT_SUCCESS) {
				let result = data.data;
				//遍历所有配置
				$.each(ddoptionList, function(index, val) {
					//根据返回数据集处理数据格式
					let list = [];
					//遍历返回的对应数据字典的列表
					$.each(result[val.ddCode],function(_index,_val){
						if(_val != null){
							var optJson = {};
							optJson.value = _val.id;
							optJson.label = _val.name;
							list.push(optJson);
						}
					})
					val.recAttrForm[val.recAttrValName] = list;
				});
			}
		}
	});
}
/**
 * 替换字符串
 * @param {Object} oldString
 * @param {Object} newString
 */
function PLAT_REPLACE_STRING(oldString, newString) {
	return oldString.replace(new RegExp(oldString, "gm"), newString);
}




/*获取orgCode特殊处理，json去掉前后中括号格式*/
function STRING_COMMA() {
	let strs = PLAT_GET_ORGCODE();
	let arrs = strs.split(',');
	let aaas = JSON.stringify(arrs);
	let substr = aaas.substring(1);
	return substr.substring(0, substr.length - 1);

}

function STRING_IS_EMPTY(str) {
	if (str == null || str.replace(/\s/g, "") == "") {
		return true;
	} else {
		return false;
	}
}

function STRING_IS_NOT_EMPTY(str) {
	if (str == null || str.replace(/\s/g, "") == "") {
		return false;
	} else {
		return true;
	}
}

/**
 * 获取最少展示的列数
 */
function PLAT_GET_MIN_COLUMN_SIZE(cols_table){
	if(cols_table == null){
		return 0;
	}
	let size = cols_table.length;
	_.each(cols_table,function(val,key){
		if(val.visible == false){
			size--;
		}
	})
	size = size - 1;
	return size;
}

/**
 * 初始化页面授权
 */
function PLAT_INIT_AU_WEBDOM(webResource,tenant,applicationCode,webPageCode,authorityId){
	let url = SERVER_IP_PORT + SERVER_PREFIX + "openApi/queryAuthorityWebResourceInfo";
	let params = {};
	params.tenant = tenant;
	params.applicationCode = applicationCode;
	params.webPageCode = webPageCode;
	params.authorityId = authorityId;
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
				$.each(data.data,function(index,val){
					$("#"+val.domId).attr("title","无使用权限");
					$("#"+val.domId+" span").css("color","#ccc");
					$("#"+val.domId+" i").css("color","#ccc");
					webResource[`${val.domId}`] = {};
					if(val.defaultType == 1){
						webResource[`${val.domId}`]["display"] = "none";
						webResource[`${val.domId}`]["disabled"] = null;
					}else if (val.defaultType == 2){
						webResource[`${val.domId}`]["display"] = null;
						webResource[`${val.domId}`]["disabled"] = true;
					}else if (val.defaultType == 3){
						$("#"+val.domId).css("display","none");
					}
				})
			} else {
				vm.$message.error(data.msg);
			}
		},
		error: function(data) {
			vm.$message.error("系统错误！");
		}
	});
}

/**
 * 获取当前登录人权限组
 */
var PLAT_GET_AUTHORITY_ID = () => getCookie(TENANT_CODE + "_AUTHORITY_ID");


// 获取当前应用的租户编码
var PLAT_GET_SYS_TENANT = () => getCookie("SYS_TENANT");


// 获取缓存的orgtree
var PLAT_GET_ORGTREE = () => JSON.parse(sessionStorage.getItem(TENANT_CODE+"_orgTree"));

// 获取缓存的选中菜单
var PLAT_GET_SELECTMENU = () => JSON.parse(sessionStorage.getItem(TENANT_CODE+"_selectMenu"));
// 获取缓存所有菜单
var PLAT_GET_MENUALL = () => JSON.parse(sessionStorage.getItem(TENANT_CODE+"_menuAll"));
// 获取缓存菜单树
var PLAT_GET_MENUTREE = () => JSON.parse(sessionStorage.getItem(TENANT_CODE+"_menuTree"));
// 获取缓存的待我审批
var PLAT_GET_APPROVAL = () => JSON.parse(sessionStorage.getItem(TENANT_CODE+"_approval"));


// 获取缓存的菜单参数
var PLAT_GET_PARAMS = (key) =>{
	let params = sessionStorage.getItem(key);
	let result = null;
	try {
	    result = JSON.parse(params);
	} catch (error) {
		result = params
	}
	return result;
};

//处理公共的notify提示
var PLAT_NOTIFY = (title, content) => {
	let domId = new Date().getTime() + 'notify'
	if (content == undefined || content == 'undefined' || content == '' || content == null) {
		return `
			<div style="width:240px">
			<div style="width:240px;float:left;margin-top:-3px">
			<div style="width:240px;float:left">
				${title}
			</div> 
			</div> 
		</div>
		`
	} else {
		return `
	<div style="width:240px">
		<div style="width:240px;float:left;margin-top:-3px" class="notifyBox" id=${domId}>
			<div style="width:200px;overflow:hidden;float:left">
				${title}
			</div> 
			<div style="width:40px;float:right;overflow:hidden;text-align:right;color:#1665D8;cursor: pointer;" class="openNotify" onclick="openNotify(event)">展开</div>
			<div style="width:240px;float:left;color:#9EA0A5;font-size:12px;display:none" class="notifyContent">${content}</div>
	</div> 
</div>
	`
	}
}

var notify = {
	success: function (title,content) {
		vm.$notify({
			type: 'success',
			dangerouslyUseHTMLString: true,
			duration:3000,
			message: PLAT_NOTIFY(title,content)
		});
	},
	warning: function (title,content) {
		vm.$notify({
			type: 'warning',
			dangerouslyUseHTMLString: true,
			duration:0,
			message: PLAT_NOTIFY(title,content)
		});
	},
	info: function (title,content) {
		vm.$notify({
			type: 'info',
			dangerouslyUseHTMLString: true,
			duration:0,
			message: PLAT_NOTIFY(title,content)
		});
	},
	error: function (title,content) {
		vm.$notify({
			type: 'error',
			dangerouslyUseHTMLString: true,
			duration:0,
			message: PLAT_NOTIFY(title,content)
		});
	},
	empty: function (title,content) {
		vm.$notify({
			dangerouslyUseHTMLString: true,
			duration:0,
			message: PLAT_NOTIFY(title,content)
		});
	}
}

var openNotify = (e) => {
	console.log(e)
	console.log(e.target.parentNode.id)
	let ids = e.target.parentNode.id;
	let state = $('#'+ids + ' .notifyContent')[0].style.display;
	if(state == 'none'){
		$('#'+ids +" .openNotify").text('收缩')
		$('#'+ids +" .notifyContent").css({"display":"block"})
	}else if(state == 'block'){
		$('#'+ids+" .openNotify").text('展开')
		$('#'+ids+ " .notifyContent").css({"display":"none"})
	}
} 



/**
 * 查询用户当前页面的实体数据权限
 * @param {Object} tenant 租户编码
 * @param {Object} webpageCode 页面号
 * @param {Object} userId 用户id
 */
function PLAT_GET_USER_ENTITY_AUTHORITY_ORGCODE(tenant,applicationCode,webpageCode,userId,authority,keyName){
 let params = {};
 params.tenant = tenant;
 params.applicationCode = applicationCode;
 params.webpageCode = webpageCode;
 params.userId = userId;
 let url = SERVER_IP_PORT   + SERVER_PREFIX+ "openApi/queryOrgTreeByUserIdAndAuthorEntity";
 $.ajax({
  url: url,
  type: "POST",
  async: false,
  dataType: "json",
  data:params,
  xhrFields: {
   withCredentials: xhrFieldsState
  },
  success: function(data) {
   if(data.code == RESULT_SUCCESS) {
	
    // authority[keyName] = data.data;
	// PAGECODE_POSTMESSAGE(data.data,webpageCode,authority,keyName)
	authority[keyName] = data.data;
	//    PAGECODE_POSTMESSAGE(data.data,webpageCode,authority,keyName)
		let obj = {};
		let tree = data.data;
		let peon = tree.reduce((cur, next) => {
			obj[next.id] ? "" : obj[next.id] = true && cur.push(next);
			return cur;
		}, []);
		let orgDatas = transDate(peon, "id", "pId");
		let parentData = {
			operationType: 1, //操作类型
			orgData:orgDatas,
			webPageCode: TENANT_CODE+'_'+WEBPAGECODE
		}
		//向父页面传值
		window.parent.postMessage(parentData, '*');
   }else {
    vm_orgCodeList = [];
   }
  },
  error: function(data) {
   vm.$message.error("系统内部错误！");
  }
 });
}    



// 获取父框架的应用编码
var GET_PARENT_APPLICATION_CODE = () => window.parent.GET_APPLICATION_CODE();