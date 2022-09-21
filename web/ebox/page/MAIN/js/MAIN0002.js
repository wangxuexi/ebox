/**
 * Created by Kevin on 2020/11/18.
 */
var vm = null;
const TENANT = PLAT_GET_NOW_TENANT();
const ORGCODE = PLAT_GET_ORGCODE();
const WEBPAGECODE = 'MAIN0001';

Vue.use(httpVueLoader);

$(function() {
	initVue(); //实例化vue
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
				orgCode: ORGCODE, //组织编码
				close: false, //设置点击模态框外部不消失
				size: "medium", //设置按钮大小
				top: "2vh", //设置模态框距离顶部距离
				arr:[]
				
			}
		},
		mounted() {},
		created() {},
		methods: {
			
		}
	})
}

