/**
 * Created by Kevin on 2020/11/18.
 */
var vm = null;


Vue.use(httpVueLoader);

$(function() {
	initVue(); //实例化vue
	querySqMsg();//查询诉求数量
	queryQzlyMsg();//群众留言
	queryLzMsg();//履职
});

var initVue = () => {
	vm = new Vue({
		el: "#root",
		components: {
			'secondary-menu': 'url:../../template/MENU/secondaryMenu.vue',
			'fj-anchor': 'url:../../template/PAGE/fj-anchor.vue',
			'fj-card': 'url:../../template/PAGE/fj-card.vue',
			'fj-resultbody': 'url:../../template/PAGE/fj-resultbody.vue',
			'fj-box': 'url:../../template/PAGE/fj-box.vue',
		},
		data() {
			return {
				
				sqMsg:{},//诉求信息
				qzlyMsg:{},//群众留言
				lzglMsg:{},//履职管理
			}
		},
		created() {
			
		},
		mounted() {
			
		},
		
		methods: {
			dbjyMenu(){
				window.open('http://rdzx.mhrd.gov.cn/rdyj/Login')
			},
			rdysMenu(){
				window.open('http://32.1.9.194')
			},
		}
	})
}


//查询诉求

var querySqMsg = () => {
	$.ajax({
		type: "post",
		url: SERVER_IP_PORT + SERVER_PREFIX + "RDGT0002/queryTotel",
		data: {
			"orgCode": PLAT_GET_ORGCODE(),
		},
		dataType: "json",
		xhrFields: {
			withCredentials: xhrFieldsState
		},
		success: (data) => {
			if (data.code == 1) {
				vm.sqMsg = data.data;
			} else {

			}

		},
		error: (data) => {

		}
	});
};

//查询群众留言

var queryQzlyMsg = () => {
	$.ajax({
		type: "post",
		url: SERVER_IP_PORT + SERVER_PREFIX + "RDGT0001/queryTotel",
		data: {
			"orgCode": PLAT_GET_ORGCODE(),
		},
		dataType: "json",
		xhrFields: {
			withCredentials: xhrFieldsState
		},
		success: (data) => {
			if (data.code == 1) {
				vm.qzlyMsg = data.data;
			} else {

			}

		},
		error: (data) => {

		}
	});
};



//查询履职

var queryLzMsg = () => {
	$.ajax({
		type: "post",
		url: SERVER_IP_PORT + SERVER_PREFIX + "RDLZ0002/queryTotel",
		data: {
			"orgCode": PLAT_GET_ORGCODE(),
		},
		dataType: "json",
		xhrFields: {
			withCredentials: xhrFieldsState
		},
		success: (data) => {
			if (data.code == 1) {
				vm.lzglMsg = data.data;
			} else {

			}

		},
		error: (data) => {

		}
	});
};