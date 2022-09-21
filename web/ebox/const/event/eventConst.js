//配置事件对应的接口路径
var EVENTLIST = {
	//企业变更(个人)
	'DXRM0003_ROOM_RESERVED_RECORD_HEADER': {
		detailUrl: '../DXRM/DXRM0004.html',//单据详情路径 ../ECXB/ECXB0002.html
		customeHtmlPath: '../DXRM/DXRM0004.html',//自定义基本信息展示 ../ECRL/ECRL000201.html
		eventInterface: {
			RS_PASS: 'DXRM0003/applicationStateJump', //key 触发事件编码， value 触发事件对应的接口名
			RS_BACK: 'DXRM0003/applicationStateJump', 
		},
		isCustomApprove: { //是否需要自定义审批  key:对应的单据状态  flag :否需要自定义审批 默认不开启为false  path :自定义审批页面路径
			/* 1: {
				flag: false,
				path: ''
			}, */
		},
		customOptName: { //表头操作列对应的文字	（移动端不需要配置）
			1: '审批',
			2: '查看',
			3: '查看'
		}
	},
	//外出教研
	'DXDL0002_OUT_RESEARCH_HEAD': {
		detailUrl: '',//单据详情路径 ../ECXB/ECXB0002.html
		customeHtmlPath: '',//自定义基本信息展示 ../ECRL/ECRL000201.html
		eventInterface: {
			JY_PASS: 'DXDL0002/applicationStateJump', //key 触发事件编码， value 触发事件对应的接口名
			JY_BACK: 'DXDL0002/applicationStateJump', 
		},
		isCustomApprove: { //是否需要自定义审批  key:对应的单据状态  flag :否需要自定义审批 默认不开启为false  path :自定义审批页面路径
			1: {
				flag: false,
				path: ''
			},
			2: {
				flag: false,
				path: ''
			},
			3: {
				flag: false,
				path: ''
			},
		},
		customOptName: { //表头操作列对应的文字	（移动端不需要配置）
			1: '审批',
			2: '查看',
			3: '查看'
		}
	},
};
