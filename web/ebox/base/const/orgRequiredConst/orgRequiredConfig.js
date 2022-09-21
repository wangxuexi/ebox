const ORG_REQUIRED_CONFIG = {
	ZSWJ:{
		name:true,//企业名称
		socialCreditCode:true,//统一代码
		orgCodeSelect:false,//组织部门
		foundDate:true,//成立日期
		frName:true,//法人代表
		cardNumber:false,//证件/号码
		telephone: false,//法人电话
		regCapital:true,//注册资本
		foreignCurrency:false,//外资部分
		regType:true,//注册性质
		phone:true,//法人手机
		entSource:true,//招商来源
		entSourceNote:false,//来源备注
		supportType:false,//扶持类型
		natureId:true,//所有制性质
		regAddress:true,//注册地址
		scopeDetail:false,//经营范围
		activeState:false,//设立状态
		cardState:false,//办理进度
		tagNames:false,//企业标签
		importDate:false,//迁入日期
		importType:false,//迁入类型
		importWay:false,//迁入来源
		shortName:false,//企业简称
		entType:false,//公司类型
		efficetiveYearNum:false,//营业期限
		licenseExp:false,//执照有效期
		certificateNoOriginal:false,//证书编号正本
		industryCategory:true,//产业大类
		isPlatformEnt:false,//是否平台企业
		entBelongPark:false,//所属园区
		certificateNoOriginalCopy:false,//证书编号副本
		chargeOrg:false,//关联集团
		operator:false,//经办人
		userId:false,//联络人
		fileBoxNoFile:false,//归档号
		belongBuildingId:false,//所属楼宇
		area:false,//面积
		regionsId:true,//企业归属区域
		manageClassId:false,//企业分类
		taxClassId:false,//税务状态
		contactAddr:false,//实际经营地址
		manageNote:false,//管理备注
	},
	ZSNH: {
		name: true,//企业名称
		socialCreditCode: true,//统一代码
		orgCodeSelect: false,//组织部门
		foundDate: true,//成立日期
		frName: true,//法人代表
		cardNumber: false,//证件/号码
		telephone: false,//法人电话
		regCapital: true,//注册资本
		foreignCurrency: false,//外资部分
		regType: false,//注册性质
		phone: false,//法人手机
		entSource: false,//招商来源
		entSourceNote: false,//来源备注
		supportType: false,//扶持类型
		natureId: false,//所有制性质
		regAddress: true,//注册地址
		scopeDetail: true,//经营范围
		activeState: false,//设立状态
		cardState: false,//办理进度
		tagNames: false,//企业标签
		importDate: false,//迁入日期
		importType: false,//迁入类型
		importWay: false,//迁入来源
		shortName: false,//企业简称
		entType: false,//公司类型
		efficetiveYearNum: false,//营业期限
		licenseExp: false,//执照有效期
		certificateNoOriginal: false,//证书编号正本
		industryCategory: true,//产业大类
		isPlatformEnt: false,//是否平台企业
		entBelongPark: false,//所属园区
		certificateNoOriginalCopy: false,//证书编号副本
		chargeOrg: false,//关联集团
		operator: false,//经办人
		userId: false,//联络人
		fileBoxNoFile: false,//归档号
		belongBuildingId: true,//所属楼宇
		area: false,//面积
		regionsId: false,//企业归属区域
		manageClassId: false,//企业分类
		taxClassId: true,//税务状态
		contactAddr: false,//实际经营地址
		manageNote: false,//管理备注
	},
	ZSJC: {
		name: true,//企业名称
		socialCreditCode: true,//统一代码
		orgCodeSelect: false,//组织部门
		foundDate: true,//成立日期
		frName: true,//法人代表
		cardNumber: false,//证件/号码
		telephone: false,//法人电话
		regCapital: true,//注册资本
		foreignCurrency: false,//外资部分
		regType: false,//注册性质
		phone: false,//法人手机
		entSource: true,//招商来源
		entSourceNote: false,//来源备注
		supportType: false,//扶持类型
		natureId: true,//所有制性质
		regAddress: true,//注册地址
		scopeDetail: true,//经营范围
		activeState: false,//设立状态
		cardState: false,//办理进度
		tagNames: false,//企业标签
		importDate: false,//迁入日期
		importType: false,//迁入类型
		importWay: false,//迁入来源
		shortName: false,//企业简称
		entType: false,//公司类型
		efficetiveYearNum: false,//营业期限
		licenseExp: false,//执照有效期
		certificateNoOriginal: false,//证书编号正本
		industryCategory: false,//产业大类
		isPlatformEnt: false,//是否平台企业
		entBelongPark: false,//所属园区
		certificateNoOriginalCopy: false,//证书编号副本
		chargeOrg: false,//关联集团
		operator: false,//经办人
		userId: false,//联络人
		fileBoxNoFile: false,//归档号
		belongBuildingId: false,//所属楼宇
		area: false,//面积
		regionsId: false,//企业归属区域
		manageClassId: false,//企业分类
		taxClassId: false,//税务状态
		contactAddr: false,//实际经营地址
		manageNote: false,//管理备注
	},
	ZSPUJ: {
		name: true,//企业名称
		socialCreditCode: true,//统一代码
		orgCodeSelect: false,//组织部门
		foundDate: true,//成立日期
		frName: true,//法人代表
		cardNumber: false,//证件/号码
		telephone: false,//法人电话
		regCapital: true,//注册资本
		foreignCurrency: false,//外资部分
		regType: false,//注册性质
		phone: false,//法人手机
		entSource: false,//招商来源
		entSourceNote: false,//来源备注
		supportType: false,//扶持类型
		natureId: false,//所有制性质
		regAddress: true,//注册地址
		scopeDetail: true,//经营范围
		activeState: false,//设立状态
		cardState: false,//办理进度
		tagNames: false,//企业标签
		importDate: false,//迁入日期
		importType: false,//迁入类型
		importWay: false,//迁入来源
		shortName: false,//企业简称
		entType: false,//公司类型
		efficetiveYearNum: false,//营业期限
		licenseExp: false,//执照有效期
		certificateNoOriginal: false,//证书编号正本
		industryCategory: false,//产业大类
		isPlatformEnt: false,//是否平台企业
		entBelongPark: false,//所属园区
		certificateNoOriginalCopy: false,//证书编号副本
		chargeOrg: false,//关联集团
		operator: false,//经办人
		userId: false,//联络人
		fileBoxNoFile: false,//归档号
		belongBuildingId: false,//所属楼宇
		area: false,//面积
		regionsId: false,//企业归属区域
		manageClassId: false,//企业分类
		taxClassId: false,//税务状态
		contactAddr: false,//实际经营地址
		manageNote: false,//管理备注
	},
	ZSPJ: {
		name: true,//企业名称
		socialCreditCode: true,//统一代码
		orgCodeSelect: false,//组织部门
		foundDate: true,//成立日期
		frName: true,//法人代表
		cardNumber: false,//证件/号码
		telephone: false,//法人电话
		regCapital: true,//注册资本
		foreignCurrency: false,//外资部分
		regType: false,//注册性质
		phone: false,//法人手机
		entSource: false,//招商来源
		entSourceNote: false,//来源备注
		supportType: false,//扶持类型
		natureId: false,//所有制性质
		regAddress: true,//注册地址
		scopeDetail: true,//经营范围
		activeState: false,//设立状态
		cardState: false,//办理进度
		tagNames: false,//企业标签
		importDate: false,//迁入日期
		importType: false,//迁入类型
		importWay: false,//迁入来源
		shortName: false,//企业简称
		entType: false,//公司类型
		efficetiveYearNum: false,//营业期限
		licenseExp: false,//执照有效期
		certificateNoOriginal: false,//证书编号正本
		industryCategory: false,//产业大类
		isPlatformEnt: false,//是否平台企业
		entBelongPark: false,//所属园区
		certificateNoOriginalCopy: false,//证书编号副本
		chargeOrg: false,//关联集团
		operator: false,//经办人
		userId: false,//联络人
		fileBoxNoFile: false,//归档号
		belongBuildingId: false,//所属楼宇
		area: false,//面积
		regionsId: false,//企业归属区域
		manageClassId: false,//企业分类
		taxClassId: false,//税务状态
		contactAddr: false,//实际经营地址
		manageNote: false,//管理备注
	},
	ZSQB: {
		name: true,//企业名称
		socialCreditCode: true,//统一代码
		orgCodeSelect: false,//组织部门
		foundDate: true,//成立日期
		frName: true,//法人代表
		cardNumber: false,//证件/号码
		telephone: false,//法人电话
		regCapital: true,//注册资本
		foreignCurrency: false,//外资部分
		regType: false,//注册性质
		phone: false,//法人手机
		entSource: false,//招商来源
		entSourceNote: false,//来源备注
		supportType: false,//扶持类型
		natureId: false,//所有制性质
		regAddress: true,//注册地址
		scopeDetail: true,//经营范围
		activeState: false,//设立状态
		cardState: false,//办理进度
		tagNames: false,//企业标签
		importDate: false,//迁入日期
		importType: false,//迁入类型
		importWay: false,//迁入来源
		shortName: false,//企业简称
		entType: false,//公司类型
		efficetiveYearNum: false,//营业期限
		licenseExp: false,//执照有效期
		certificateNoOriginal: false,//证书编号正本
		industryCategory: false,//产业大类
		isPlatformEnt: false,//是否平台企业
		entBelongPark: false,//所属园区
		certificateNoOriginalCopy: false,//证书编号副本
		chargeOrg: false,//关联集团
		operator: false,//经办人
		userId: false,//联络人
		fileBoxNoFile: false,//归档号
		belongBuildingId: false,//所属楼宇
		area: false,//面积
		regionsId: false,//企业归属区域
		manageClassId: false,//企业分类
		taxClassId: false,//税务状态
		contactAddr: false,//实际经营地址
		manageNote: false,//管理备注
	},
	ZSZQ: {
		name: true,//企业名称
		socialCreditCode: true,//统一代码
		orgCodeSelect: false,//组织部门
		foundDate: true,//成立日期
		frName: true,//法人代表
		cardNumber: false,//证件/号码
		telephone: false,//法人电话
		regCapital: true,//注册资本
		foreignCurrency: false,//外资部分
		regType: false,//注册性质
		phone: false,//法人手机
		entSource: false,//招商来源
		entSourceNote: false,//来源备注
		supportType: false,//扶持类型
		natureId: false,//所有制性质
		regAddress: true,//注册地址
		scopeDetail: true,//经营范围
		activeState: false,//设立状态
		cardState: false,//办理进度
		tagNames: false,//企业标签
		importDate: false,//迁入日期
		importType: false,//迁入类型
		importWay: false,//迁入来源
		shortName: false,//企业简称
		entType: false,//公司类型
		efficetiveYearNum: false,//营业期限
		licenseExp: false,//执照有效期
		certificateNoOriginal: false,//证书编号正本
		industryCategory: false,//产业大类
		isPlatformEnt: false,//是否平台企业
		entBelongPark: false,//所属园区
		certificateNoOriginalCopy: false,//证书编号副本
		chargeOrg: false,//关联集团
		operator: false,//经办人
		userId: false,//联络人
		fileBoxNoFile: false,//归档号
		belongBuildingId: false,//所属楼宇
		area: false,//面积
		regionsId: false,//企业归属区域
		manageClassId: false,//企业分类
		taxClassId: false,//税务状态
		contactAddr: false,//实际经营地址
		manageNote: false,//管理备注
	},
	ZSHC: {
		name: true,//企业名称
		socialCreditCode: true,//统一代码
		orgCodeSelect: false,//组织部门
		foundDate: true,//成立日期
		frName: true,//法人代表
		cardNumber: false,//证件/号码
		telephone: false,//法人电话
		regCapital: true,//注册资本
		foreignCurrency: false,//外资部分
		regType: false,//注册性质
		phone: false,//法人手机
		entSource: false,//招商来源
		entSourceNote: false,//来源备注
		supportType: false,//扶持类型
		natureId: false,//所有制性质
		regAddress: true,//注册地址
		scopeDetail: true,//经营范围
		activeState: false,//设立状态
		cardState: false,//办理进度
		tagNames: false,//企业标签
		importDate: false,//迁入日期
		importType: false,//迁入类型
		importWay: false,//迁入来源
		shortName: false,//企业简称
		entType: false,//公司类型
		efficetiveYearNum: false,//营业期限
		licenseExp: false,//执照有效期
		certificateNoOriginal: false,//证书编号正本
		industryCategory: false,//产业大类
		isPlatformEnt: false,//是否平台企业
		entBelongPark: false,//所属园区
		certificateNoOriginalCopy: false,//证书编号副本
		chargeOrg: false,//关联集团
		operator: false,//经办人
		userId: false,//联络人
		fileBoxNoFile: false,//归档号
		belongBuildingId: false,//所属楼宇
		area: false,//面积
		regionsId: false,//企业归属区域
		manageClassId: false,//企业分类
		taxClassId: false,//税务状态
		contactAddr: false,//实际经营地址
		manageNote: false,//管理备注
	},
	ZSGM: {
		name: true,//企业名称
		socialCreditCode: true,//统一代码
		orgCodeSelect: false,//组织部门
		foundDate: true,//成立日期
		frName: true,//法人代表
		cardNumber: false,//证件/号码
		telephone: false,//法人电话
		regCapital: true,//注册资本
		foreignCurrency: false,//外资部分
		regType: false,//注册性质
		phone: false,//法人手机
		entSource: false,//招商来源
		entSourceNote: false,//来源备注
		supportType: false,//扶持类型
		natureId: false,//所有制性质
		regAddress: true,//注册地址
		scopeDetail: true,//经营范围
		activeState: false,//设立状态
		cardState: false,//办理进度
		tagNames: false,//企业标签
		importDate: false,//迁入日期
		importType: false,//迁入类型
		importWay: false,//迁入来源
		shortName: false,//企业简称
		entType: false,//公司类型
		efficetiveYearNum: false,//营业期限
		licenseExp: false,//执照有效期
		certificateNoOriginal: false,//证书编号正本
		industryCategory: false,//产业大类
		isPlatformEnt: false,//是否平台企业
		entBelongPark: false,//所属园区
		certificateNoOriginalCopy: false,//证书编号副本
		chargeOrg: false,//关联集团
		operator: false,//经办人
		userId: false,//联络人
		fileBoxNoFile: false,//归档号
		belongBuildingId: false,//所属楼宇
		area: false,//面积
		regionsId: false,//企业归属区域
		manageClassId: false,//企业分类
		taxClassId: false,//税务状态
		contactAddr: false,//实际经营地址
		manageNote: false,//管理备注
	}
}
