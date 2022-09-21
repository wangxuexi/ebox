const CHART_SERIES = 'series';
const PARAM_IS_FUNCTION = "1";

/**
 * 获取页面注册的图表配置
 * @param {Object} myChartOptionConfig dom槽位配置
 * @param {Object} tenant 租户编码
 * @param {Object} applicationCode 应用编码
 * @param {Object} webPageCode 页面号
 * @param {Object} customFunction 自定义函数 非必传
 * @param {Object} customOption 自定义配置 非必传
 */
function PLAT_INIT_WEB_CHART(
			myChartOptionConfig,
			tenant,
			applicationCode,
			webPageCode,
			customFunction,
			customOption){
	let params = {};
	params.tenant = tenant;//租户编码
	params.applicationCode = applicationCode;//应用编码
	params.webPageCode = webPageCode;//页面号
	let _url = CHART_CONFIG_URL;
	$.ajax({
		//接口地址
		url: _url,
		type: 'POST',
		dataType: "JSON",
		async: true,
		data: params,
		success: function(data) {
			//遍历返回回来的配置
			let dataList = data.data;
			let chartOptionList = [];
			$.each(dataList,function(index,val){
				if(typeof(myChartOptionConfig[val.domId]) != 'undefined'){
					let optionJson = strToJson(val.optionJson);
					val.optionJson = optionJson;
					chartOptionList.push(val);
				}
			})
			INIT_CHART_DOM(myChartOptionConfig,chartOptionList,customFunction,customOption);//初始化页面图表dom
		},
		error: function(data) {
			vm.$message.error("服务器异常");
		}
	});
}



/**
 * 初始化页面图表dom
 * @param {Object} chartOptionList
 */
function INIT_CHART_DOM(myChartOptionConfig,chartOptionList,customFunction,customOption){
	$.each(chartOptionList,function(index,val){//遍历当前页面所有的图表配置
		let dom = document.getElementById(val.domId);//获取页面dom
		if(dom){
			val.chartDom = echarts.init(dom);
			INIT_CHART_OPTION(myChartOptionConfig,val,customFunction,customOption);
		}
	})
}

function INIT_CHART_OPTION(myChartOptionConfig,chartOption,customFunction,customOption){
	let url = chartOption.apiInterface;
	if(url != '//'){
		let params = {};
		//遍历参数
		$.each(chartOption.params,function(index,val){
			if(val.isFunction == PARAM_IS_FUNCTION){
				params[val.paramKey] = customFunction[val.paramValue];
			}else{
				params[val.paramKey] = val.paramValue
			}
		})
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
				if (data.code == RESULT_SUCCESS) {
					let result = data.data;
					//遍历result
					$.each(result,function(index,val){
						//判断数据类型
						let keyArr = index.split(".");
						let myOption = chartOption.optionJson;
						let _temporaryOption = myOption;
						let rootKey = keyArr[0];//获取根节点key值
						$.each(keyArr,function(_index,_val){
							if(rootKey == CHART_SERIES) {
								//判断数据类型
								if(val.type == "other"){
									//判断是否是处理数据
									
									if($.inArray("data", keyArr) == 1){
										//遍历数据
										$.each(val.data,function(__index,__val){
											_temporaryOption[_val][__index].data = __val;
										})
										return false;
									}else{
										if(_index == keyArr.length-1){
											$.each(val.data,function(__index,__val){
												_temporaryOption[__index][_val] = __val;
											}) 
										}else{
											_temporaryOption = _temporaryOption[_val];
										}
									}
								}else{
									if(_temporaryOption[_val].length < val.length){ 
										//优先处理以有配置，超过部分按第一个配置处理
										let seriesOption = JSON.parse(JSON.stringify(_temporaryOption[_val][0])) ;
										$.each(val,function(__index,__val){
											if(__index<=(_temporaryOption[_val].length-1)){//如果当前下标没有超过配置的长度
												_temporaryOption[_val][__index].data = __val;//塞入数据
											}else{
												_temporaryOption[_val].push(seriesOption);
												_temporaryOption[_val][__index].data = __val;//塞入数据
											}
										})
									}else{
										$.each(val,function(__index,__val){
											_temporaryOption[_val][__index].data = __val;//塞入数据
										})
									}
									return false;
								}
							}else{
								//判断是否是特殊类型
								if(val.type == "other"){
									//遍历数据
									$.each(val.data,function(__index,__val){
										_temporaryOption[_val][__index].data = __val;
									})
									return false;
								}else{
									//如果根节点数据类型
									if(_index == keyArr.length-1){
										_temporaryOption[_val] = val;
									}else{
										_temporaryOption = _temporaryOption[_val];
									}
								}
								
								
							}
						})
					});
					if(customOption && customOption[chartOption.domId]){
						copyOption(chartOption.optionJson,customOption[chartOption.domId])
					}
					
					chartOption.chartDom.setOption(chartOption.optionJson);
					myChartOptionConfig[chartOption.domId] = chartOption.chartDom;
					window.addEventListener("resize",function(){
						chartOption.chartDom.resize(); 
					});
				} else {
					vm.$message.error(data.msg);
				}
				
			},
			error: function(data) {
				vm.$message.error("服务器异常");
			}
		});
	}else{
		
		copyOption(chartOption.optionJson,customOption[chartOption.domId])
		chartOption.chartDom.setOption(chartOption.optionJson);
		myChartOptionConfig[chartOption.domId] = chartOption.chartDom;
		window.addEventListener("resize",function(){
			chartOption.chartDom.resize(); 
		});
	}
	
}
/**
 * 拷贝json
 * @param {Object} oldOption
 * @param {Object} customOption
 */
function copyOption(oldOption,customOption){
	$.extend(true, oldOption, customOption);
}

function checkEchartsOptionAndDataFormat(chartOption,dataFormat){
	let result = dataFormat;
	let resultOption = null;
	//遍历result
	$.each(result,function(index,val){
		//判断数据类型
		let keyArr = index.split(".");
		let myOption = chartOption;
		let _temporaryOption = myOption;
		let rootKey = keyArr[0];//获取根节点key值
		$.each(keyArr,function(_index,_val){
			if(rootKey == CHART_SERIES) {
				//判断数据类型
				if(val.type == "other"){
					//判断是否是处理数据
					if($.inArray("data", keyArr) == 1){
						//遍历数据
						$.each(val.data,function(__index,__val){
							_temporaryOption[_val][__index].data = __val;
						})
						return false;
					}else{
						if(_index == keyArr.length-1){
							$.each(val.data,function(__index,__val){
								_temporaryOption[__index][_val] = __val;
							}) 
						}else{
							_temporaryOption = _temporaryOption[_val];
						}
					}
				}else{
					if(_temporaryOption[_val].length < val.length){ 
						//优先处理以有配置，超过部分按第一个配置处理
						let seriesOption = JSON.parse(JSON.stringify(_temporaryOption[_val][0])) ;
						$.each(val,function(__index,__val){
							if(__index<=(_temporaryOption[_val].length-1)){//如果当前下标没有超过配置的长度
								_temporaryOption[_val][__index].data = __val;//塞入数据
							}else{
								_temporaryOption[_val].push(seriesOption);
								_temporaryOption[_val][__index].data = __val;//塞入数据
							}
						})
					}else{
						$.each(val,function(__index,__val){
							_temporaryOption[_val][__index].data = __val;//塞入数据
						})
					}
					return false;
				}
			}else{
				//判断是否是特殊类型
				if(val.type == "other"){
					//遍历数据
					$.each(val.data,function(__index,__val){
						_temporaryOption[_val][__index].data = __val;
					})
					return false;
				}else{
					//如果根节点数据类型
					if(_index == keyArr.length-1){
						_temporaryOption[_val] = val;
					}else{
						_temporaryOption = _temporaryOption[_val];
					}
				}
			}
		})
		resultOption = _temporaryOption;
	});
	return resultOption;
}