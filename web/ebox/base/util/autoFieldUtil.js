

/**
 * 获取对应类型的dom字符串
 * @param {Object} field
 */
function getFieldDom(fields,domId,type){
	var html = "";
	var countNum = 1;
	
	$.each(fields,function(index,field){
		var addFlag = true;
		let requiredClass = field.isRequired == 1 ? "requiredClass" : "";
		
		
		//判断是否加row
		if(countNum == 1 || countNum%4 == 0 ){
			html += '<el-row :gutter="10" >'
		}
		//存贮自定义字段的相应属性
		AUTOFIELDSTORAGE[field.domFieldId] = field;
		if(field.fieldType == FIELDTYPE["input"] ){
			html += '<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="'+requiredClass+'">';
			html += '<el-form-item label="'+ field.fieldTips +'" >';
			//判断操作类型，查询
			if(type === FIELDTYPE_QUERY){
				html += '<el-input v-model="autofield.input.'+field.domFieldId+'" class=" '+field.domFieldId+'" '+ 'id = "'+field.domFieldId+'" name = "'+field.domFieldName+'" value="'+field.defaultValue+'" :disabled = "true"   ></el-input>';
			}else{
				html += '<el-input v-model="autofield.input.'+field.domFieldId+'" class=" '+field.domFieldId+'" '+ 'id = "'+field.domFieldId+'" name = "'+field.domFieldName+'" value="'+field.defaultValue+'"></el-input>';
			}
			html += '</el-form-item>';
			html += '</el-col>';
			
		}else if ( field.fieldType == FIELDTYPE["number"]){
			html += '<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="'+requiredClass+'">';
			html += '<el-form-item label="'+ field.fieldTips +'" >';
			//判断操作类型，查询
			if(type === FIELDTYPE_QUERY){
				html += '<el-input v-model="autofield.number.'+field.domFieldId+'" class=" '+field.domFieldId+'" '+ 'id = "'+field.domFieldId+'" name = "'+field.domFieldName+'" value="'+field.defaultValue+'" :disabled = "true"   ></el-input>';
			}else{
				html += '<el-input v-model="autofield.number.'+field.domFieldId+'" class=" '+field.domFieldId+'" '+ 'id = "'+field.domFieldId+'" name = "'+field.domFieldName+'" value="'+field.defaultValue+'"></el-input>';
			}
			html += '</el-form-item>';
			html += '</el-col>';
		}else if (field.fieldType == FIELDTYPE["textarea"]){
			addFlag = false;
			if(countNum == 1){
				html += '<el-form-item label="'+ field.fieldTips +'" >';
			}else{
				html +=`</el-row>
				<el-row :gutter="10" >
                    <el-form-item label="`+ field.fieldTips +`" >
            		`;
                countNum = 1;
			}
			
						
            if(type === FIELDTYPE_QUERY){
            	html += `		<el-input type="textarea" v-model="autofield.textarea.${field.domFieldId}" class= "${field.domFieldId}" id="${field.domFieldId}" name ="${field.domFieldId}" value="${field.defaultValue}" :disabled ="true"></el-input>`;
			}else{
				html += `		<el-input type="textarea" v-model="autofield.textarea.${field.domFieldId}" class= "${field.domFieldId}" id="${field.domFieldId}" name ="${field.domFieldId}" value="${field.defaultValue}" ></el-input>`;
	        }
			html += ` 		</el-col>
						</el-form-item>
					</el-row>
        	`;
          
		}else if(field.fieldType == FIELDTYPE["datepicker"]){
			html += '<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="'+requiredClass+'">';
			html += '<el-form-item label="'+ field.fieldTips +'" >';
			
			//判断操作类型，查询
			if(type === FIELDTYPE_QUERY){
				html += '<el-date-picker 	type="date" 	placeholder="选择日期" 	format="yyyy-MM-dd"  value-format="yyyy-MM-dd" v-model="autofield.datepicker.'+field.domFieldId+'" class=" '+field.domFieldId+'" '+ 'id = "'+field.domFieldId+'" name = "'+field.domFieldName+'" value="'+field.defaultValue+'" :disabled = "true"   ></el-date-picker>';
			}else{
				html += '<el-date-picker 	type="date" 	placeholder="选择日期" 	format="yyyy-MM-dd"  value-format="yyyy-MM-dd" v-model="autofield.datepicker.'+field.domFieldId+'" class=" '+field.domFieldId+'" '+ 'id = "'+field.domFieldId+'" name = "'+field.domFieldName+'" value="'+field.defaultValue+'"  ></el-date-picker>';
			}
			
			html+= '</el-form-item>';
			html += '</el-col>';		      
		}else if(field.fieldType == FIELDTYPE["select"]){
			html += '<el-col :span="6" class="'+requiredClass+'">';
			html += '<el-form-item label="'+ field.fieldTips +'" >';
			
			if(type === FIELDTYPE_QUERY){
				html += '<el-select  v-model="autofield.select.'+field.domFieldId+'" placeholder="'+field.fieldTips+'" class=" '+field.domFieldId +' " :disabled ="true" clearable  filterable>';
			}else{
				html += '<el-select  v-model="autofield.select.'+field.domFieldId+'" placeholder="'+field.fieldTips+'" class=" '+field.domFieldId +' " clearable  filterable>';
			}
			var params = {};
			params.code = field.fucInterface;// 接口名统一换为 数据字典编码
			params.orgCode = PLAT_GET_ORGCODE();
			
			//请求接口
			var url = SERVER_IP_PORT+SERVER_PREFIX+'sy/queryDDValuesByCodeForInsert';
			$.ajax({
				url: url,
				type: "POST",
				data: params,
				dataType: "json",
				async:false,
				success: function (data) {
				    if (data.code == 1) {
				        result = data.data;
				        //动态生成
				        $.each(result,function(index,val){
				       		html += '<el-option label="'+val.name+'" value="'+val.id+'"></el-option>';
				     	})
					}
				}
			});
			html += '</el-select>';
			html+= '</el-form-item>';
			
			/* html += '<fj-lable content="'+field.fieldTips+'"></fj-lable>';
			if(type === FIELDTYPE_QUERY){
				html += '<el-select  v-model="autofield.select.'+field.domFieldId+'" placeholder="'+field.fieldTips+'" class="elInput '+field.domFieldId+' " :disabled ="true" clearable '+ required +' filterable>';
			}else{
				html += '<el-select  v-model="autofield.select.'+field.domFieldId+'" placeholder="'+field.fieldTips+'" class="elInput '+field.domFieldId+' " clearable '+ required +' filterable>';
			}
			var params = {};
			params.code = field.fucInterface;// 接口名统一换为 数据字典编码
			params.orgCode = PLAT_GET_ORGCODE();

			//请求接口
			var url = SERVER_IP_PORT+SERVER_PREFIX+'sy/queryDDValuesByCodeForInsert';
		   	$.ajax({
				url: url,
				type: "POST",
				data: params,
				dataType: "json",
				async:false,
				success: function (data) {
				    if (data.code == 1) {
				        result = data.data;
				        //动态生成
				        $.each(result,function(index,val){
				       		html += '<el-option label="'+val.name+'" value="'+val.id+'"></el-option>';
				     	})
					}
				}
		    });
			html += '</el-select>'; */
			html += '</el-col>';
		}
		if(countNum > 1 && (countNum%4) == 3){
			html += '</el-row>'
		}
		if(addFlag){
			countNum ++;
		}
		
  		
	})
	$("#"+domId).append(html) 
}


/**
 * 
 * @param {Object} tenant 租户编码
 * @param {Object} orgCode 组织编码
 * @param {Object} entityTypeCode 实体类型编码
 * @param {Object} objectId 实体id
 * @param {Object} domId dom元素的id
 * @param {Object} type 操作类型
 */
function appendDom(tenant,orgCode,entityTypeCode,objectId,domId,type){
	var url = SERVER_IP_PORT+SERVER_PREFIX+"queryByTeAndOrgAndWebAndEntity";
   	var params = {};
   	params.tenant = tenant;
   	params.orgCode = orgCode;
   	params.entityTypeCode = entityTypeCode;
   	params.webCode = null;
   	params.objectId = objectId;
   	var result = null;
   	$.ajax({
		url: url,
		type: "POST",
		async: true,
		data:params,
		dataType: "json",
		async: false,
		success: function (data) {
		    if (data.code == 1) {
		        result = data.data;
		         getFieldDom(result,domId,type);
			}
		}
    });
}


/**
 * 
 * @param {Object} tenant 租户编码
 * @param {Object} orgCode 组织编码
 * @param {Object} entityTypeCode 实体类型编码
 * @param {Object} objectId 实体id
 */
function initAUTOFIELDVM(tenant,orgCode,entityTypeCode,objectId){
	var url = SERVER_IP_PORT+SERVER_PREFIX+"queryByTeAndOrgAndWebAndEntity";
   	var params = {};
   	params.tenant = tenant;
   	params.orgCode = orgCode;
   	params.entityTypeCode = entityTypeCode;
   	params.webCode = null;
   	params.objectId = objectId;
   	var result = null;
   	$.ajax({
		url: url,
		type: "POST",
		async: true,
		data:params,
		dataType: "json",
		async: false,
		success: function (data) {
		    if (data.code == 1) {
		        result = data.data;
		        createFieldVm(result);
			}
		}
    });
}


/**
 * 
 * @param {Object} tenant 租户编码
 * @param {Object} orgCode 组织编码
 * @param {Object} entityTypeCode 实体类型编码
 * @param {Object} objectId 实体id
 */
function getOBJAUTOFIELDVAL(tenant,orgCode,entityTypeCode,objectId){

	var url = SERVER_IP_PORT+SERVER_PREFIX+"queryByTeAndOrgAndWebAndEntity";
   	var params = {};
   	params.tenant = tenant;
   	params.orgCode = orgCode;
   	params.entityTypeCode = entityTypeCode;
   	params.webCode = null;
   	params.objectId = objectId;
   	$.ajax({
		url: url,
		type: "POST",
		async: true,
		data:params,
		dataType: "json",
		async: false,
		success: function (data) {
		    if (data.code == 1) {
		        OBJVALUE = data.data;
		        
			}
		}
    });
}



/**
 * 创建自定义字段对应的属性
 * @param {Object} fields
 */
function createFieldVm(fields){
	AUTOFIELDVM.input = {};
	AUTOFIELDVM.select = {};
	AUTOFIELDVM.textarea = {};
	AUTOFIELDVM.number = {};
	AUTOFIELDVM.datepicker = {};
	
	//定义返回的vue实例的属性
	$.each(fields,function(index,field){
   		//校验类型
		if(field.fieldType == FIELDTYPE[field.fieldName] ){
			//文本框
			if(field.fieldType == FIELDTYPE["input"] ){
				if(field.value == null || field.value ==""){
					AUTOFIELDVM.input[field.domFieldId] = field.defaultValue;
				}else{
					AUTOFIELDVM.input[field.domFieldId] = field.value;
				}
			}else if (field.fieldType == FIELDTYPE["textarea"]){
				if(field.value == null || field.value ==""){
					AUTOFIELDVM.textarea[field.domFieldId] = field.defaultValue;
				}else{
					AUTOFIELDVM.textarea[field.domFieldId] = field.value;
				}
			}else if(field.fieldType == FIELDTYPE["select"]){
				if(field.value == null || field.value ==""){
					AUTOFIELDVM.select[field.domFieldId] = field.defaultValue;
				}else{
					AUTOFIELDVM.select[field.domFieldId] = field.value;
				}
			}else if(field.fieldType == FIELDTYPE["number"]){
				if(field.value == null || field.value ==""){
					AUTOFIELDVM.number[field.domFieldId] = field.defaultValue;
				}else{
					AUTOFIELDVM.number[field.domFieldId] = field.value;
				}
			}else if(field.fieldType == FIELDTYPE["datepicker"]){
				if(field.value == null || field.value ==""){
					AUTOFIELDVM.datepicker[field.domFieldId] = field.defaultValue;
				}else{
					AUTOFIELDVM.datepicker[field.domFieldId] = field.value;
				}
			}
		}
 	})
}


/**
 * 保存自定义字段值
 * @param {Object} obj
 */

function SAVE_AUTOFIELDVAL(obj){

	var url = SERVER_IP_PORT+SERVER_PREFIX+"saveCustomFieldValue";
   	var params = {};
   	params.id = obj.id;
   	params.tenant = obj.tenant;
   	params.orgCode = obj.orgCode;
	params.webCode = obj.webCode;
	params.customFieldId = obj.customFieldId;
	params.entityTypeCode = obj.entityTypeCode;
	params.objectId = obj.objectId;
	params.fieldSeq = obj.fieldSeq;
	params.value = obj.value;
  
   	var result = null;
   	$.ajax({
		url: url,
		type: "POST",
		async: true,
		data:params,
		dataType: "json",
		success: function (data) {
		    if (data.code == 1) {
		        result = data.data;
			}else{
				alert(data.msg)
			}
		}
    });
}
