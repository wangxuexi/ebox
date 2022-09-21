/**
 * Created by Kevin on 2019/10/30.
 */
//千分符并保留两位小数  a为需要处理的数据，n为需要保留的位数
function formatNumber(a, n) {
    if (n == 0) {
        a = a.toString().replace(/(\d)(?=(\d{3})+$)/g, "$1,");
    } else {
        n1 = Math.pow(10, n);
        a = (Math.round(a * n1) / n1).toFixed(n).toString().replace(/(\d)(?=(\d{3})+\.)/g, function ($0, $1) {
            return $1 + ",";
        });
    }
    return a;
}

//创建cookie  名字  值  天数
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}


//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
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


////获取时间
var nowDate = new Date();
var year = nowDate.getFullYear();
var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
var hour = nowDate.getHours() < 10 ? "0" + nowDate.getHours() : nowDate.getHours();
var minute = nowDate.getMinutes() < 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();
var second = nowDate.getSeconds() < 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();
var time = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
var dates = year + "-" + month + "-" + date;
var week;
if (new Date().getDay() == 0) week = "星期日"
if (new Date().getDay() == 1) week = "星期一"
if (new Date().getDay() == 2) week = "星期二"
if (new Date().getDay() == 3) week = "星期三"
if (new Date().getDay() == 4) week = "星期四"
if (new Date().getDay() == 5) week = "星期五"
if (new Date().getDay() == 6) week = "星期六"
var times = year + "年" + month + "月" + date + "" + " " + week + " " + hour + ":" + minute + ":" + second;
var mapTime = year + month + date;
var mapTimes = year + "-" + month + "-" + date;


//将后台传过来的树转成需要的格式
function transDate(list, idstr, pidstr) {
    var result = [], temp = {};
    for (i = 0; i < list.length; i++) {
        temp[list[i][idstr]] = list[i];//将nodes数组转成对象类型
    }
    for (j = 0; j < list.length; j++) {
        tempVp = temp[list[j][pidstr]]; //获取每一个子对象的父对象
        if (tempVp) {//判断父对象是否存在，如果不存在直接将对象放到第一层
            if (!tempVp["children"]) tempVp["children"] = [];//如果父元素的nodes对象不存在，则创建数组
            tempVp["children"].push(list[j]);//将本对象压入父对象的nodes数组
        } else {
            result.push(list[j]);//将不存在父对象的对象直接放入一级目录
        }
    }
    return result;
}

//中国标准时间转换为标准格式
function formatTen(num) {
    return num > 9 ? (num + "") : ("0" + num);
}

function formatDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    return year + "-" + formatTen(month);
}


/**
 *时间类型格式转化
 * @param {Date} date 要转换的时间
 * @param {Object} type 要转换的格式类型（1:YYYY-MM-DD HH:mm:ss,2:YYYY-MM-DD）
 */
function dateFormat(date, type) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    if (type == 1) { //YYYY-MM-DD HH:mm:ss
        return year + "-" + formatTen(month) + "-" + formatTen(day) + " " + formatTen(hour) + ":" + formatTen(minute) + ":" + formatTen(second);
    } else if (type == 2) { //YYYY-MM-DD
        return year + "-" + formatTen(month) + "-" + formatTen(day);
    }
}

/**
 * 判断是否包含某个字符串
 * @param {Object} arg1
 * @param {Object} arg2
 */
function hasC(arg1, arg2) {
    if (arg1 == null) {
        return false;
    } else if (arg1.indexOf(arg2) != -1) {
        return true;
    } else {
        return false;
    }
}


function isNumber(value) {         //验证是否为数字
    var patrn = /^(-)?\d+(\.\d+)?$/;
    if (patrn.exec(value) == null || value == "") {
        return false
    } else {
        return true
    }
}

/**
 * 获取当前日期
 */
function getdateYMD() {
    var date = new Date();

    var mon = date.getMonth() + 1;         //getMonth()返回的是0-11，则需要加1
    if (mon <= 9) {                                     //如果小于9的话，则需要加上0
        mon = "0" + mon;
    }
    var day = date.getDate();                   //getdate()返回的是1-31，则不需要加1
    if (day <= 9) {                                     //如果小于9的话，则需要加上0
        day = "0" + day;
    }
    var time = date.getFullYear() + "-" + mon + "-" + day;
    return time;
}

/**
 * 获取当前年份
 */
function getYearBegin() {
    var date = new Date();

    var mon = date.getMonth() + 1;         //getMonth()返回的是0-11，则需要加1
    if (mon <= 9) {                                     //如果小于9的话，则需要加上0
        mon = "0" + mon;
    }
    var day = date.getDate();                   //getdate()返回的是1-31，则不需要加1
    if (day <= 9) {                                     //如果小于9的话，则需要加上0
        day = "0" + day;
    }
    var time = date.getFullYear() + "-01-01";
    return time;
}

/**
 * 获取当前年份
 */
function getCurrYear() {
    var date = new Date();

    var mon = date.getMonth() + 1;         //getMonth()返回的是0-11，则需要加1
    if (mon <= 9) {                                     //如果小于9的话，则需要加上0
        mon = "0" + mon;
    }
    var day = date.getDate();                   //getdate()返回的是1-31，则不需要加1
    if (day <= 9) {                                     //如果小于9的话，则需要加上0
        day = "0" + day;
    }
    var time = date.getFullYear();
    return time;
}




/**
 * 获取当年结束月份
 */
function getYearEnd() {
    var date = new Date();

    var mon = date.getMonth() + 1;         //getMonth()返回的是0-11，则需要加1
    if (mon <= 9) {                                     //如果小于9的话，则需要加上0
        mon = "0" + mon;
    }
    var day = date.getDate();                   //getdate()返回的是1-31，则不需要加1
    if (day <= 9) {                                     //如果小于9的话，则需要加上0
        day = "0" + day;
    }
    var time = date.getFullYear() + "-12-31";
    return time;
}

/**
 * 获取当年开始月份
 */
function getYearBeginMonth() {
    var date = new Date();
    var mon = date.getMonth();
    if (mon == 0) {
        return (date.getFullYear() - 1) + "-01";
    }
    var time = date.getFullYear() + "-01";
    return time;
}

/**
 * 获取上个月
 */
function getdateYM() {
    var date = new Date();
    var mon = date.getMonth();
    if (mon == 0) {
        return (date.getFullYear() - 1) + "-12";
    } else {
        mon = mon + 1;
        if (mon <= 9) {                                     //如果小于9的话，则需要加上0
            mon = "0" + mon;
        }
        var time = "" + date.getFullYear() + "-" + mon;
        return time;
    }

}

/**
 * 获取上个月1号
 */
function getlastYear() {
    var date = new Date();
    var mon = date.getMonth();
    if (mon == 0) {
        return (date.getFullYear()-1) + "-12-01";
    } else {
        // mon = mon + 1;
        if (mon <= 9) {                                     //如果小于9的话，则需要加上0
            mon = "0" + mon;
        }
        var day = date.getDate();                   //getdate()返回的是1-31，则不需要加1
        if (day <= 9) {                                     //如果小于9的话，则需要加上0
            day = "0" + day;
        }
        var time = "" + (date.getFullYear()) + "-" + mon + "-" + "01";
        return time;

    }
}


//取url参数
function getQueryString() {
    var qs = {};
    var url = decodeURIComponent(window.location.href);
    //不管有没有伪静态 都看一下?问号后面的参数
    if (url.indexOf('?') > -1) {
        url = url.substring(url.indexOf('?') + 1);
        var prm = url.split('&');
        for (var p in prm) {
            if (prm[p]) {
                var sp = prm[p].split('=');
                if (sp.length > 1) {
                    var spkey = sp[0];
                    var spvalue = sp[1];

                    if (spvalue.indexOf('#') > -1) {
                        spvalue = spvalue.substring(0, spvalue.indexOf('#'));
                    }
                    qs[spkey] = spvalue;
                }
            }
        }
    }
    return qs;
};


//判断url后有无参数
function getQueryList(urls) {
    var qsList = {};
    var url = urls;
    //不管有没有伪静态 都看一下?问号后面的参数
    if (url.indexOf('?') > -1) {
        url = url.substring(url.indexOf('?') + 1);
        var prm = url.split('&');
        for (var p in prm) {
            if (prm[p]) {
                var sp = prm[p].split('=');
                if (sp.length > 1) {
                    var spkey = sp[0];
                    var spvalue = sp[1];

                    if (spvalue.indexOf('#') > -1) {
                        spvalue = spvalue.substring(0, spvalue.indexOf('#'));
                    }
                    qsList[spkey] = spvalue;
                }
            }
        }
    }
    return qsList;
};

// 获取url后面的get参数
function _get(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
}

//去除orgCode的子节点
function removeOrgCodeChildren(str, tenant) {
	let orgTrees = null;
	if (tenant == null) {
		orgTrees = JSON.parse(sessionStorage.getItem(TENANT_CODE + "_orgTree"));
	} else {
		orgTrees = JSON.parse(sessionStorage.getItem(tenant + "_orgTree"));
	}
	var arr = str.split(',');
	var strList = [];
	arr.forEach((item, k) => {
		orgTrees.forEach((element, i) => {
			if (item == element.value) {
				strList.push(item);
			}
		})
	});
	let unstrList = unique(strList);
	let resultStr = '';
	unstrList.forEach(result => {
		resultStr += result + ",";
	});
	let resultStrs = resultStr.substring(0, resultStr.length - 1);
	return resultStrs;
}

//去除orgName的子节点
function removeOrgNameChildren(str) {
    let orgTrees = JSON.parse(sessionStorage.getItem(TENANT_CODE+"_orgTree"));
    var arr = str.split(',');
    var strList = [];
    arr.forEach((item, k) => {
        orgTrees.forEach((element, i) => {
            if (item == element.value) {
                console.log(element.label)
                strList.push(element.label);
            }
        })
    });
    let unstrList = unique(strList);
    let resultStr = '';
    unstrList.forEach(result => {
        resultStr += result + ",";
    });
    let resultStrs = resultStr.substring(0, resultStr.length - 1);
    return resultStrs;
}



//通过子节点获取父节点的orgCode
function findParentOrgCode(tenant,str) {
	let orgTrees = null;
	if (tenant == null) {
	 	orgTrees = JSON.parse(sessionStorage.getItem(TENANT_CODE + "_orgTree"));
	} else {
		orgTrees = JSON.parse(sessionStorage.getItem(tenant + "_orgTree"));
	}
    var arr = str.split(',');
    var strList = [];
    arr.forEach((item, k) => {
        orgTrees.forEach((element, i) => {
            if (item == element.value) {
                strList.push(item);
            }else {
                if (element.children != undefined) {
                    element.children.forEach(children => {
                        if (item == children.value) {
                            strList.push(element.value)
                        }
                    })
                }
            }
        })
    });
    let unstrList = unique(strList);
    let resultStr = '';
    unstrList.forEach(result => {
        resultStr += result + ",";
    });
    let resultStrs = resultStr.substring(0, resultStr.length - 1);
    return resultStrs;
}


//去除orgCode的父节点
function removeOrgCodeParent(str,tenant) {
	let orgTrees = null;
	if (tenant == null) {
	 	orgTrees = JSON.parse(sessionStorage.getItem(TENANT_CODE + "_orgTree"));
	} else {
		orgTrees = JSON.parse(sessionStorage.getItem(tenant + "_orgTree"));
	}
    var arr = str.split(',');
    var strList = [];
    arr.forEach((item, k) => {
        orgTrees.forEach((element, i) => {
            if (item == element.value && element.children == undefined) {
                strList.push(item);
            } else {
                if (element.children != undefined) {
                    element.children.forEach(children => {
                        if (item == children.value) {
                            strList.push(children.value)
                        }
                    })
                }
            }
        })
    });
    let unstrList = unique(strList);
    let resultStr = '';
    unstrList.forEach(result => {
        resultStr += result + ",";
    });
    let resultStrs = resultStr.substring(0, resultStr.length - 1);
    return resultStrs;
}


//去除orgCode的父节点 通过组件方法嵌入页面  参数为 
/**
 * 
 * @param {*} str  //获取的选择的组织
 * @param {*} pageCode  //页面号 
 * @param {*} tenant  //租户
 * @returns 
 */
function removeOrgCodeParentAssembly(str,pageCode,tenant) {
	let orgTrees = null;
	if (tenant == null) {
	 	orgTrees = JSON.parse(sessionStorage.getItem(TENANT_CODE +'_'+ pageCode + "_orgTree"));
	} else {
		orgTrees = JSON.parse(sessionStorage.getItem(tenant +'_'+ pageCode + "_orgTree"));
	}
    var arr = str.split(',');
    var strList = [];
    arr.forEach((item, k) => {
        orgTrees.forEach((element, i) => {
            if (item == element.value && element.children == undefined) {
                strList.push(item);
            } else {
                if (element.children != undefined) {
                    element.children.forEach(children => {
                        if (item == children.value) {
                            strList.push(children.value)
                        }
                    })
                }
            }
        })
    });
    let unstrList = unique(strList);
    let resultStr = '';
    unstrList.forEach(result => {
        resultStr += result + ",";
    });
    let resultStrs = resultStr.substring(0, resultStr.length - 1);
    return resultStrs;
}




/**
 * 去除orgName的父节点
 * @param str 逗号分隔的orgName字符串
 * @returns {string}
 */
function removeOrgNameParent(tenant,strOrgName) {
	let orgTrees = null;
	if (tenant == null) {
	 	orgTrees = JSON.parse(sessionStorage.getItem(TENANT_CODE + "_orgTree"));
	} else {
		orgTrees = JSON.parse(sessionStorage.getItem(tenant + "_orgTree"));
	}
    var orgNameArr = strOrgName.split(',');
    var strList = [];
    orgNameArr.forEach((item, k) => {
        orgTrees.forEach((element, i) => {
            if (item == element.label && element.children == undefined) {
                strList.push(item);
            } else {
                if (element.children != undefined) {
                    element.children.forEach(children => {
                        if (item == children.label) {
                            strList.push(children.label)
                        }
                    })
                }
            }
        })
    });

    let unstrList = unique(strList);
    let resultStr = '';
    unstrList.forEach(result => {
        resultStr += result + ",";
    });
    let resultStrs = resultStr.substring(0, resultStr.length - 1);
    return resultStrs;
}




/**
 * 去除orgName的父节点 通过组件嵌入的页面
 * @param str 逗号分隔的orgName字符串
 * @returns {string}
 */
 function removeOrgNameParentAssembly(strOrgName,pageCode,tenant) {
	let orgTrees = null;
	if (tenant == null) {
	 	orgTrees = JSON.parse(sessionStorage.getItem(TENANT_CODE +'_'+ pageCode + "_orgTree"));
	} else {
		orgTrees = JSON.parse(sessionStorage.getItem(tenant +'_'+ pageCode + "_orgTree"));
	}
    var orgNameArr = strOrgName.split(',');
    var strList = [];
    orgNameArr.forEach((item, k) => {
        orgTrees.forEach((element, i) => {
            if (item == element.label && element.children == undefined) {
                strList.push(item);
            } else {
                if (element.children != undefined) {
                    element.children.forEach(children => {
                        if (item == children.label) {
                            strList.push(children.label)
                        }
                    })
                }
            }
        })
    });

    let unstrList = unique(strList);
    let resultStr = '';
    unstrList.forEach(result => {
        resultStr += result + ",";
    });
    let resultStrs = resultStr.substring(0, resultStr.length - 1);
    return resultStrs;
}

function unique(arr) {  //数组去重
    return Array.from(new Set(arr))
}


//根据orgCode判断选择了几个父节点   1为1个  0为其它
function orgCodeHierarchy(strOrgCode) {
    let orgTrees = JSON.parse(sessionStorage.getItem(TENANT_CODE+"_orgTree"));
    let orgCodeArr = strOrgCode.split(',');
    let strCodeList = [];
    orgCodeArr.forEach((item, k) => {
        orgTrees.forEach((element, i) => {
            if (item == element.value) {
                strCodeList.push(item);
            }
        })
    });
    if (strCodeList.length == '1') {
        return '1';
    } else {
        return '0';
    }
}

/**
 * 初始化数据字典
 * @param code     数据字典编码
 * @param orgCodes 组织，如果有多个，使用逗号分隔
 * @return 返回单个数据字典中文值
 */
function initDdvalue(code, ddId, orgCodes) {
    let url = SERVER_IP_PORT + SERVER_PREFIX + "sy/queryDDValuesByCodeForInsert";
    let name = null;
    //城市类型
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        async: false,
        data: {
            code: code,
            orgCode: orgCodes
        },
        xhrFields: {
            withCredentials: xhrFieldsState
        },
        success: function (data) {
            if (data.code == 1) {
                let result = data.data;
                $.each(result, function (index, val) {
                    if (ddId == val.id) {
                        name = val.name;
                    }
                })
            }
        }
    });
    return name;
}


function STRING_IS_EMPTY(str) {
	str = str+"";
	if (str == null || str.replace(/\s/g, "") == "" || str =="null" || str == "") {
		return true;
	} else {
		return false;
	}
}

function STRING_IS_NOT_EMPTY(str) {
	str = str+"";
	if (str == null || str.replace(/\s/g, "") == "" || str =="null" || str != "") {
		return false;
	} else {
		return true;
	}
}

function STRING_STRIP_SCRIPT(s){
	let rs = s.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g, "")
	return rs;
}

//*
//
// 传入orgcode的字符串来获取orgname
// */
function obtainOrgName(str,webPageCode) {
    if(webPageCode != undefined){
        var orgTrees = JSON.parse(sessionStorage.getItem(webPageCode+"_orgTree"));
    }else{
        var orgTrees = JSON.parse(sessionStorage.getItem(TENANT_CODE+"_orgTree"));
    }
    let orgArr = str.split( ',' );
    let orgName = '';
    orgArr.forEach(item=>{
        orgTrees.forEach(element=>{
            if (item == element.value){
                orgName = orgName + element.label + ','
            }
            if(element.children){
                element.children.forEach(key=>{
                    if (item == key.value){
                        orgName = orgName + key.label + ','
                    }
                })
            }
        })
    })
    let orgNames = orgName.substring(0,orgName.length - 1);
    return orgNames;
}

function strToJson(str){
    var json = (new Function("return " + str))();
    return json;
}


function getDaysBetween(dateString1,dateString2){
   var  startDate = Date.parse(dateString1);
   var  endDate = Date.parse(dateString2);
   var days=(endDate - startDate)/(1*24*60*60*1000);
   return  days+1;
}

/**
 * 获取日期所在的星期
 * @param {Object} dateStr
 */
function getWeek(dateStr){

	let date =  new Date(Date.parse(dateStr));
	var weekday=new Array(7);
	weekday[0]="星期天";
	weekday[1]="星期一";
	weekday[2]="星期二";
	weekday[3]="星期三";
	weekday[4]="星期四";
	weekday[5]="星期五";
	weekday[6]="星期六";
	return weekday[date.getDay()];

}

function getdiffdate(stime,etime){
    //初始化日期列表，数组
    var diffdate = new Array();
    var i=0;
    //开始日期小于等于结束日期,并循环
    while(stime<=etime){
        diffdate[i] = stime;
        
        //获取开始日期时间戳
        var stime_ts = new Date(stime).getTime();
        //增加一天时间戳后的日期
        var next_date = stime_ts + (24*60*60*1000);
        //拼接年月日，这里的月份会返回（0-11），所以要+1
        var next_dates_y = new Date(next_date).getFullYear()+'-';
        var next_dates_m = (new Date(next_date).getMonth()+1 < 10)?'0'+(new Date(next_date).getMonth()+1)+'-':(new Date(next_date).getMonth()+1)+'-';
        var next_dates_d = (new Date(next_date).getDate() < 10)?'0'+new Date(next_date).getDate():new Date(next_date).getDate();
        stime = next_dates_y+next_dates_m+next_dates_d;
        //增加数组key
        i++;
    }
    return diffdate;
}


/**
 * 比较日期
 * @param {Object} s1
 * @param {Object} s2
 */
function compareDate(s1,s2,countday){
	let day1 = new Date(s1.replace(/-/g,"\/"));
	let day2 = new Date(s2.replace(/-/g,"\/"));
	countday = countday == null ? 0 : countday;
	day2 = day2.setDate(day2.getDate()+countday);
	day2 = new Date(day2);
	
　　return (day1.getTime() > day2.getTime());
}

/**
 * 开启表格检查
 * @param {Object} tableName
 */
function PLAT_TABLE_OPEN_EXAMINE(tableName,overAllIds,customKeyFieldName){
	$("#"+tableName).on('uncheck.bs.table check.bs.table check-all.bs.table  ',function(e,rows){
		var datas = $.isArray(rows) ? rows: [rows];
		PLAT_TABLE_EXAMINE(e.type,datas,overAllIds,customKeyFieldName);
	})
	$("#"+tableName).on('uncheck-all.bs.table',function(e,rowsAfter,rowsBefore){
		var datas = $.isArray(rowsBefore) ? rowsBefore: [rowsBefore];
		PLAT_TABLE_EXAMINE(e.type,datas,overAllIds,customKeyFieldName);
	})
}

/**
 * 表格检查选中
 * @param {Object} type
 * @param {Object} datas
 */
function PLAT_TABLE_EXAMINE(type,datas,overAllIds,customKeyFieldName){
	if(type.indexOf('uncheck') == -1){
		$.each(datas,function(i,v){
			if(STRING_IS_EMPTY(customKeyFieldName)){//校验是否启用自定义字段属性名
				overAllIds.indexOf(v.id) == -1 ? overAllIds.push(v.id) : -1; //默认使用id
			}else{
				overAllIds.indexOf(v[customKeyFieldName]) == -1 ? overAllIds.push(v[customKeyFieldName]) : -1; //否则使用自定义属性字段
			}
		});
	}else{
		$.each(datas,function(i,v){
			if(STRING_IS_EMPTY(customKeyFieldName)){//校验是否启用自定义字段属性名
				overAllIds.splice(overAllIds.indexOf(v.id),1); //默认使用id
			}else{
				overAllIds.splice(overAllIds.indexOf(v[customKeyFieldName]),1);//否则使用自定义属性字段
			}
		})
	}
}



/**
 * 这里处理权限的组织树  参数：tree（组织树对象） webPageCode(页面号)
 * 
 */
var ORGCODE_JURISDICTION_TREE = (tree,webPageCode,authority,keyName) =>{
    let obj = {};
    let peon = tree.reduce((cur, next) => {
        obj[next.id] ? "" : obj[next.id] = true && cur.push(next);
        return cur;
    }, []);
    let orgDatas = transDate(peon, "id", "pId");
    sessionStorage.setItem(webPageCode + "_orgTree", JSON.stringify(orgDatas));
    setTimeout(()=>{
        authority[keyName] = orgDatas;
        vm.initOrgTreeComplete();
    },500)
    
}


/**
 * 
 * @param {*} org 
 * @param {*} webPageCode 
 * 这里处理权限实体通知父页面改变组织树
 */
var PAGECODE_POSTMESSAGE = (org,WEBPAGECODE,authority,keyName) =>{
    ORGCODE_JURISDICTION_TREE(org,TENANT_CODE+'_'+WEBPAGECODE,authority,keyName);//参数为组织树、页面号
    let parentData = {
        operationType: 1, //操作类型
        webPageCode: TENANT_CODE+'_'+WEBPAGECODE
    }
    //向父页面传值
    window.parent.postMessage(parentData, '*');
}