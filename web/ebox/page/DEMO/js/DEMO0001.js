var vm = null;

$(document).ready(function() {
	initVm(); //实例化vue
});

//初始化vue实例
function initVm() {
	vm = new Vue({
		el: "#root",
		data: {
			tableData: [
			/* {
				phone: '15800983926',
				name: '参数A',
				address: '上海市普陀区金沙江路 1518 弄',
				openId: 'o3EXU1JwAMzMRX6m5Os1nlCy7jEs'
			}, */ {
				phone: '18672675202',
				name: '富贵张',
				address: '上海市普陀区金沙江路 1517 弄',
				openId: 'o3EXU1M9gBBtK69FnYEiTZmigp2o'
			},
			{
				phone: '17601373533',
				name: '测试C',
				address: '上海市普陀区金沙江路 1519 弄',
				openId: 'o3EXU1HTnbRvJpm-fVES3TBTqByU'
			},
			/* {
				phone: '17601373533',
				name: '测试D',
				address: '上海市普陀区金沙江路 1519 弄',
				openId: 'o3EXU1PyKXeCN0HXqWNOzBe8Gz_Y'
			},
			{
				phone: '17601373533',
				name: '测试E',
				address: '上海市普陀区金沙江路 1519 弄',
				openId: 'o3EXU1N5T8D97JRtBa8iuofaWeqM'
			},
			{
				phone: '17601373533',
				name: '测试F',
				address: '上海市普陀区金沙江路 1519 弄',
				openId: 'o3EXU1MBZTChfo4f7ztNlmWLozZw'
			} */
			]
		},
		//获取vuex数据
		computed: {},
		created() {
			let url = getQueryString();
		},
		methods: {
			//确认
			confirmChoice(){
				
				//向父页面传递的参数
				var param = {
					'flag': false,	//抽屉状态
					'perArr': vm.tableData,	//挑选的人员数组
				};
				window.parent.postMessage(param, '*');
			}
		},
		watch: {}
	});
}
