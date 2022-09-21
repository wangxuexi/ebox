var vm = null;
var nullPage = '../template/404.html'; //空页面

Vue.use(httpVueLoader);
const CA_TYPE = "ca";
const type = _get("type")

$(function() {
	initVue(); //初始化vue
	initIframeHeight(); // 初始化iframe的高度
	changeOrg(); //初始化组织
	messageSignal(); //iframe通信
})


var initVue = () => {
	vm = new Vue({
		el: '#root',
		components: {
			'org-select': 'url:../../template/HOME/orgSelect.vue'
		},
		data() {
			var checkAge = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请输入原密码'));
				} else {
					callback();
				}
			};
			var validatePass = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请输入密码'));
				} else {
					if (this.ruleForm.checkPass !== '') {
						this.$refs.ruleForm.validateField('checkPass');
					}
					callback();
				}
			};
			var validatePass2 = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请再次输入密码'));
				} else if (value !== this.ruleForm.pass) {
					callback(new Error('两次输入密码不一致!'));
				} else {
					callback();
				}
			};
			return {
				size: "medium",
				activeName: '',
				ruleForm: {
					pass: '',
					checkPass: '',
					oldPassword: '',
					name: localStorage.getItem("userNameRemember")
				},
				rules: {
					pass: [{
						validator: validatePass,
						trigger: 'blur'
					}],
					checkPass: [{
						validator: validatePass2,
						trigger: 'blur'
					}],
					oldPassword: [{
						validator: checkAge,
						trigger: 'blur'
					}]
				},
				iframeHeight: '', //iframe高度
				orgMsg: {
					orgcode: "ZSHC",
					title: "快乐的小魔方",
					url: "../../img/home/logo.png"
				}, //标题及logo
				drawer: false, //修改密码抽屉
				nickName: PLAT_LOGIN_USER(), //用户姓名
				headImg: PLAT_HEAD_IMG() == '' ? '../../img/home/hradimg.png' : PLAT_HEAD_IMG(), //用户头像
				transDate: null, //存储菜单
				titleShow: '1', //  为1展示搜索框，为2展示选择组织
				keyWord: '', //收索引擎输入框
				timeout: null,
				selectMenu: {}, //暂存选中的菜单
				iframeUrl: '', //选中菜单的链接
				versionVisible: false, //版本号模态框
				versionDatas: [], //版本号数据
				close: false, //设置点击模态框外部不消失
				pageType: '', //1为二级菜单  2为多tab页
				contentHight: '', //右侧iframe高度
				editableTabsValue: '', //
				editableTabs: [],
				iframesHight: '', //右侧内容区域高度
				msgForm: { //消息
					totalNum: '', //总数
					list: [{
						msgName: '',
						num: '',
						msgFrom: '',
						url: ''
					}]
				},
				activeIndex: '',
				menuicon: false, //是否显示菜单左右滚动图标
				moveLength: 0,
				approvalList: [], //需要显示数字的菜单
				menuTemplateType: menuTemplateType, //拿到菜单的模板类型
				menuType: { //菜单的三种模板类型
					menuTemplateA: menuTemplateA, //第一种菜单模板  类似于经济管理平台
					menuTemplateB: menuTemplateB, //第二种菜单模板  类似于华漕项目
					menuTemplateC: menuTemplateC, //第三种菜单模板  类似于党校系统
				},
				imgDirection: false, //修改头像
				imageUrl: PLAT_HEAD_IMG() == '' ? '../../img/home/hradimg.png' : PLAT_HEAD_IMG(), //用户头像
				keyChilder:null,//
			}
		},
		created() {
			//根据不同的组织展示不同的logo及名称
			let orgMsg = PLAT_GET_USER_ORG();
			orgCodeMsg.forEach(item => {
				if (item.orgCode == orgMsg) {
					this.orgMsg = item;

				}
			})
			//初始化菜单
			$.ajax({
				url: SERVER_IP_PORT + SERVER_PREFIX + "queryAuthorityMenu",
				type: "POST",
				data: {
					"tenant": TENANT_CODE,
					"authorityId": PLAT_GET_AUTHORITY_ID(),
					"applicationCode": APPLICATION_CODE
				},
				async: true,
				dataType: "json",
				xhrFields: {
					withCredentials: xhrFieldsState
				},
				success: (data) => {
					nodes = data.data;
					sessionStorage.setItem(TENANT_CODE + "_menuAll", JSON.stringify(nodes));
					console.log(nodes);
					this.approvalList = [];
					nodes.forEach(item => {
						if (item.backParams != '') {
							/*处理需要显示数字的菜单*/
							try {
								let urlData = JSON.parse(item.backParams);
								if (urlData.approval != undefined) {
									var _nstr = urlData.approval.replace(/'/g, '"');
							 	let urlDataJson = _nstr.split(';');
									let summary = [];
									urlDataJson.forEach(key => {
										summary.push(JSON.parse(key))
									})
									this.approvalList.push({
										domId: item.menuDomId,
										approval: summary
									})
								}
							} catch (error) {

							}

							/*处理菜单的参数作为缓存*/
							let menuKey = TENANT_CODE + '_' + APPLICATION_CODE + '_' +
								item.id;
							sessionStorage.setItem(menuKey, item.backParams);
						}
					});

					nodes = _.rest(nodes);
					this.transDate = transDate(nodes, "id", "pId");
					this.activeName = this.transDate[0].menuName;
					sessionStorage.setItem(TENANT_CODE + "_menuTree", JSON.stringify(this
						.transDate));
					setTimeout(() => {
						if (this.transDate.length > 0) {
							if (this.menuTemplateType == this.menuType
								.menuTemplateA) { //第一种模板类型   类似于经济管理平台
								this.iframeUrl = menuTemplateAurl
							} else if (this.menuTemplateType == this.menuType
								.menuTemplateC) { //第三种模板类型   类似于党校系统
								sessionStorage.setItem(TENANT_CODE + "_selectMenu", JSON
									.stringify(this.transDate[0]));
								if (this.transDate[0].menuUrl == null || this.transDate[
										0].menuUrl == "") {
									this.iframeUrl = nullPage
								} else {
									this.iframeUrl = this.transDate[0].menuUrl;
								}
							} else { //第二种模板类型   类似于华漕
								sessionStorage.setItem(TENANT_CODE + "_selectMenu", JSON
									.stringify(this.transDate[0]));
								if (this.transDate[0].menuUrl == null || this.transDate[
										0].menuUrl == "") {
									this.iframeUrl = nullPage
								} else {
									this.iframeUrl = this.transDate[0].menuUrl;
								}
							}
						} else {
							this.iframeUrl = nullPage;
						}
						$('.menuBox').css("width", this.transDate.length * 100 + 'px');
						var menuWight = $(".menuContent").outerWidth(true);
						if (menuWight > this.transDate.length * 100) {
							this.menuicon = false;
						} else {
							this.menuicon = true;
						}
					}, 300);
				}
			});
			// 获取版本
			$.ajax({
				url: SERVER_IP_PORT + SERVER_PREFIX + "queryVersion",
				type: "POST",
				data: {
					"tenant": TENANT_CODE,
					"applicationCode": APPLICATION_CODE
				},
				async: true,
				dataType: "json",
				xhrFields: {
					withCredentials: xhrFieldsState
				},
				success: (data) => {
					this.versionDatas = data.data;
				}
			});

			//待我审批
			/* $.ajax({
				type: "post",
				url: SERVER_IP_PORT + SERVER_PREFIX + "openApi/queryAllPendingApprovalGroup",
				data: {
					"orgCode": PLAT_GET_ORGCODE(),
					tenant: TENANT_CODE,
					uId: PLAT_GET_UID()
				},
				dataType: "json",
				xhrFields: {
					withCredentials: xhrFieldsState
				},
				success: function(data) {
					if (data.code == 1) {
						sessionStorage.setItem(TENANT_CODE + "_approval", JSON.stringify(data
							.data));
					} else {

					}

				},
				error: (data) => {}
			}); */

			//消息通知
			this.queryMsg();




		},
		methods: {
			fatherOrgNotice() {  //更新orgCode完成时通知父页面
                // console.log('在这里orgCode已经更新完毕，可以通知子页面查询数据了');
                // console.log(PLAT_GET_ORGCODE())
                // console.log(this.editableTabs)
                if(this.pageType == '2'){  //在当前页面直接打开了标签页
                    // console.log(this.editableTabsValue)
                    // console.log(this.editableTabs)
                    this.editableTabs.forEach((item,index)=>{
                        if(item.name == this.editableTabsValue){
                            let childWindow = $(".iframetab")[index].contentWindow; //
                            childWindow.initOrgTreeComplete()
                        }
                    })
                }else{  //有左侧菜单栏的菜单
                    let childWindow = $("#myframe")[0].contentWindow; //
                    childWindow.initOrgTreeComplete()
                }
            },
			refresh() { //待我审批强制刷新
				var newMsg = this.$notify({
					title: '提示',
					dangerouslyUseHTMLString: true,
					message: `<i class="el-icon-loading"></i>正在接收最新的消息通知！`,
					duration: 0
				});
				/* $.ajax({
					type: "post",
					url: SERVER_IP_PORT + SERVER_PREFIX + "openApi/queryAllPendingApprovalGroup",
					data: {
						"orgCode": PLAT_GET_ORGCODE(),
						tenant: TENANT_CODE,
						uId: PLAT_GET_UID(),
						isRefresh: true
					},
					dataType: "json",
					xhrFields: {
						withCredentials: xhrFieldsState
					},
					success: (data) => {
						if (data.code == 1) {
							sessionStorage.setItem(TENANT_CODE + "_approval", JSON.stringify(
								data.data));
							newMsg.close();
							this.$notify({
								title: '成功',
								message: '已获取最新的消息通知！',
								type: 'success',
							});
						} else {
							newMsg.close();
							this.$notify({
								title: '失败',
								message: '查询失败，请稍后重试！',
								type: 'warning',
							});
						}

					},
					error: (data) => {
						newMsg.close();
						this.$notify({
							title: '失败',
							message: '查询失败，请稍后重试！',
							type: 'warning',
						});
					}
				}); */
			},
			queryMsg() { //消息通知
				$.ajax({
					url: SERVER_IP_PORT + SERVER_PREFIX + "ECSY0009/getMsgList",
					type: "POST",
					data: {
						"userId": PLAT_GET_UID()
					},
					async: true,
					dataType: "json",
					xhrFields: {
						withCredentials: xhrFieldsState
					},
					success: (data) => {
						if (data.code == RESULT_SUCCESS) {
							this.msgForm.totalNum = data.data.length;
							this.msgForm.list = [];
							let sortData = sortClass(data.data);
							sortData.forEach(item => {
								msgType.forEach(element => {
									if (item[0].entityTypeCode == element
										.entityTypeCode) {
										this.msgForm.list.push({
											name: element.name,
											num: item.length,
											msgFrom: element.type,
											url: element.url
										})
									}
								})
							});
						} else {
							this.msgForm.totalNum = 0;
							this.msgForm.list = [];
						}
					}
				});
			},
			msgClick(item) {
				if (item.url != '') { //如果需要跳转
					item.page = item.url + '?type=' + item.msgFrom;
					let path = [item.url + "*" + item.name + "*" + "false"];
					localStorage.setItem("paths", JSON.stringify(path));
					vm.pageType = '';
					let flag = true;
					for (let k = 0; k < vm.editableTabs.length; k++) {
						if (vm.editableTabs[k].name == item.name) {
							flag = false;
						}
					}
					if (flag) {
						let getUrl = getQueryList(item.page);
						if (getUrl.length > 0 || item.page.indexOf("?") == -1) {
							vm.editableTabs.push({
								title: item.name,
								name: item.name,
								content: `<iframe class="iframetab"    :height="iframesHight" src=${item.page+'?v='+new Date().getTime()} frameborder="0"></iframe>`
							})
						} else {
							vm.editableTabs.push({
								title: item.name,
								name: item.name,
								content: `<iframe class="iframetab"   :height="iframesHight" src=${item.page+'&v='+new Date().getTime()} frameborder="0"></iframe>`
							})
						}
					}
					vm.editableTabsValue = item.name
					setTimeout(() => {
						addHeight();
					}, 100);
				}
			},
			querySearchAsync(queryString, cb) { //全局搜索
				if (this.keyWord != '') {
					$.ajax({
						url: SERVER_IP_PORT + SERVER_PREFIX +
							"openApi/withNoFieldBatchSearchWithScore",
						type: "POST",
						data: {
							"tenant": TENANT_CODE,
							"orgCode": PLAT_GET_ORGCODE(),
							"keyWord": this.keyWord
						},
						async: true,
						dataType: "json",
						xhrFields: {
							withCredentials: xhrFieldsState
						},
						success: (data) => {

							if (data.code == '1') {
								let datas = data.data;
								datas.forEach((item, index) => {
									item.value = (index + 1) + ' ' + item.name;
								})
								clearTimeout(this.timeout);
								this.timeout = setTimeout(() => {
									cb(datas);
								}, 1000 * Math.random());
							} else {
								this.$notify.error({
									title: '失败',
									message: data.msg
								});
							}
						}
					});
				}
			},
			handleSelect(item) { //点击搜索引擎的搜索结果
				item.page = '../ECGL/ECGL000101.html?id=' + item.id + '&orgCode=' + item.orgCode;
				vm.pageType = NEWTABS;
				let flag = true;
				for (let k = 0; k < vm.editableTabs.length; k++) {
					if (vm.editableTabs[k].name == item.name + '-查看') {
						flag = false;
					}
				}
				if (flag) {
					let getUrl = getQueryList(item.page);
					if (getUrl.length > 0 || item.page.indexOf("?") == -1) {
						vm.editableTabs.push({
							title: item.name + '-查看',
							name: item.name + '-查看',
							content: `<iframe class="iframetab"    :height="iframesHight" src=${item.page+'?v='+new Date().getTime()} frameborder="0"></iframe>`
						})
					} else {
						vm.editableTabs.push({
							title: item.name + '-查看',
							name: item.name + '-查看',
							content: `<iframe class="iframetab"   :height="iframesHight" src=${item.page+'&v='+new Date().getTime()} frameborder="0"></iframe>`
						})
					}
				}
				vm.editableTabsValue = item.name + '-查看'
				setTimeout(() => {
					addHeight();
				}, 100);

			},
			handleClick(item) { //点击菜单


				if (this.menuTemplateType == this.menuType.menuTemplateB) { //类似于华漕
					// changeOrg();
					this.activeName = item.id;
					this.selectMenu = item;
					this.iframeUrl = '';
					setTimeout(() => {
						sessionStorage.setItem(TENANT_CODE + "_selectMenu", JSON.stringify(this
							.selectMenu));
						if (this.selectMenu.menuUrl == null || this.selectMenu.menuUrl == "") {
							this.iframeUrl = nullPage
						} else {
							this.iframeUrl = this.selectMenu.menuUrl + '?' + MENU_BACKPARAMS_KEY +
								'=' + TENANT_CODE + '_' + APPLICATION_CODE + '_' + item.id
						}
					}, 300);
					//切换为全文检索
					this.titleShow = '1';
					this.pageType = '';
					this.editableTabs = [];
				} else if (this.menuTemplateType == this.menuType.menuTemplateC) { //类似于党校
					// changeOrg();
					this.activeName = item.id;
					this.selectMenu = item;
					this.iframeUrl = '';
					setTimeout(() => {
						sessionStorage.setItem(TENANT_CODE + "_selectMenu", JSON.stringify(this
							.selectMenu));
						if (this.selectMenu.menuUrl == null || this.selectMenu.menuUrl == "") {
							if (this.selectMenu.children) {
								this.iframeUrl = '../template/menuTemplateC.html'
							} else {
								this.iframeUrl = nullPage
							}
						} else {
							if (this.selectMenu.children) {
								this.iframeUrl = '../template/menuTemplateC.html'
							} else {
								this.iframeUrl = this.selectMenu.menuUrl + '?' +
									MENU_BACKPARAMS_KEY + '=' + TENANT_CODE + '_' +
									APPLICATION_CODE + '_' + item.id
							}
						}
					}, 300);
					//切换为全文检索
					this.titleShow = '1';
					this.pageType = '';
					this.editableTabs = [];
				}

			},
			handleSelect(key, keyPath) {
				// changeOrg();
				this.transDate.forEach(item => {
					if (key == item.id) {
						this.selectMenu = item;
					}
				})
				this.iframeUrl = '';
				setTimeout(() => {
					sessionStorage.setItem(TENANT_CODE + "_selectMenu", JSON.stringify(this
						.selectMenu));
					if (this.selectMenu.menuUrl == null || this.selectMenu.menuUrl == "") {
						this.iframeUrl = nullPage
					} else {
						this.iframeUrl = this.selectMenu.menuUrl
					}
				}, 300);
				//切换为全文检索
				this.titleShow = '1';
				this.pageType = '1';
				this.editableTabs = [];
			},
			change() { //修改密码
				this.drawer = true
			},
			submitForm(formName) { //修改密码提交
				this.$refs[formName].validate((valid) => {
					if (valid) {
						$.ajax({
							url: SERVER_IP_PORT + SERVER_PREFIX + "updatePasswordByTenant",
							type: "POST",
							data: {
								"name": this.ruleForm.name,
								"oldPassword": this.ruleForm.oldPassword,
								"newPassword": this.ruleForm.pass,
								"tenant": TENANT_CODE
							},
							async: true,
							dataType: "json",
							xhrFields: {
								withCredentials: xhrFieldsState
							},
							success: (data) => {
								if (data.code == "1") {
									this.$message.success('修改成功！');
									localStorage.clear();
									sessionStorage.clear();
									// var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
									cookieList.forEach(item => {
										setCookie(item, "", -1)
									})
									if (type == CA_TYPE) {
										parent.window.location.href = "loginCA.html";
									} else {
										parent.window.location.href = "login.html";
									}

								} else {
									this.$message.error(data.msg);
								}
							},
							error: (data) => {
								this.$message.error("修改失败,请联系管理员！");
							}
						});
					} else {
						return false;
					}
				});
			},
			passwordClose() {
				this.ruleForm.oldPassword = '';
				this.ruleForm.pass = '';
				this.ruleForm.checkPass = '';
			},
			signOut() { //退出登录
				localStorage.clear();
				sessionStorage.clear();
				// var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
				cookieList.forEach(item => {
					setCookie(item, "", -1)
				})
				if (type == CA_TYPE) {
					parent.window.location.href = "loginCA.html";
				} else {
					parent.window.location.href = "login.html";
				}

			},
			versionClick() { //点击查看版本号
				this.versionVisible = true
			},
			handleTabsEdit(targetName, action) {
				if (action === 'remove') {
					let tabs = this.editableTabs;
					let activeName = this.editableTabsValue;
					if (activeName === targetName) {
						tabs.forEach((tab, index) => {
							if (tab.name === targetName) {
								let nextTab = tabs[index + 1] || tabs[index - 1];
								if (nextTab) {
									activeName = nextTab.name;
								}
							}
						});
					}

					this.editableTabsValue = activeName;
					this.editableTabs = tabs.filter(tab => tab.name !== targetName);
				}
			},
			tabClick(e) {
				let localPath = [];
				localPath.push('../template/404.html' + '*' + e.label + '*' + 'false')
				localStorage.setItem("paths", JSON.stringify(localPath));
			},
			menuClick(element) {
				if (element.hasOwnProperty('children') == false) { //没有三级菜单
					if (element.menuUrl == "" || element.menuUrl == null) {
						this.$message.warning("此功能暂未开放哦！");
					} else {
						if (this.pageType == '1') { //打开了空菜单页面
							var iframeWindow = document.getElementById('myframe').contentWindow;
							if (element.menuUrl.indexOf("?") != -1) { //已经有参数
								let parentData = {
									page: element.menuUrl + '&' + MENU_BACKPARAMS_KEY + '=' +
										TENANT_CODE + '_' + APPLICATION_CODE + '_' + element.id,
									name: element.menuName,
								}
								//向父页面传值
								iframeWindow.postMessage(parentData, "*");
							} else { //没有参数
								let parentData = {
									page: element.menuUrl + '?' + MENU_BACKPARAMS_KEY + '=' +
										TENANT_CODE + '_' + APPLICATION_CODE + '_' + element.id,
									name: element.menuName,
								}
								//向父页面传值
								iframeWindow.postMessage(parentData, "*");
							}


						} else {
							if (element.backParams != '' && element.backParams != null) {

								if (element.menuUrl.indexOf("?") != -1) { //已经有参数
									let parentData = {
										pageType: NEWTABS,
										page: element.menuUrl + '&' + MENU_BACKPARAMS_KEY + '=' +
											TENANT_CODE + '_' + APPLICATION_CODE + '_' + element.id,
										name: element.menuName,
										topType: 1
									}
									//向父页面传值
									messageSignal1(parentData)
								} else { //没有参数
									let parentData = {
										pageType: NEWTABS,
										page: element.menuUrl + '?' + MENU_BACKPARAMS_KEY + '=' +
											TENANT_CODE + '_' + APPLICATION_CODE + '_' + element.id,
										name: element.menuName,
										topType: 1
									}
									//向父页面传值
									messageSignal1(parentData)

								}
							} else {
								let parentData = {
									pageType: NEWTABS,
									page: element.menuUrl,
									name: element.menuName,
									topType: 1
								}
								//向父页面传值
								messageSignal1(parentData)
							}
						}
					}

				} else {
					sessionStorage.setItem(TENANT_CODE + "_threeMenu", JSON.stringify(element));
					this.pageType = 1;
					this.iframeUrl = "";
					setTimeout(() => {
						this.iframeUrl = "../template/emptyMenu.html";
					}, 200)

					// window.location.href = "../template/emptyMenu.html"
				}
			},
			leftClick() { //点击菜单向左按钮
				let menuWight = $(".menuContent").outerWidth(true); //框子宽度

				let moveNum = parseInt(menuWight / 100) * 100; //移动长度取整
				if (this.moveLength != 0) { //移动的长度加上下次移动的长度小于内容总宽度就移动
					this.moveLength--;
					$('.menuBox').css('left', '-' + this.moveLength * moveNum + 'px')
				} else {

				}
			},
			rightClick() { //点击菜单向右按钮

				let menuWight = $(".menuContent").outerWidth(true); //框子宽度
				let menuboxWight = this.transDate.length * 100; //内容宽度

				let moveNum = parseInt(menuWight / 100) * 100; //移动长度取整
				if ((this.moveLength * moveNum + moveNum) < menuboxWight) { //移动的长度加上下次移动的长度小于内容总宽度就移动
					this.moveLength++;
					$('.menuBox').css('left', '-' + this.moveLength * moveNum + 'px')
				} else {

				}
			},
			fourmenuClick(element) { //点击四级菜单


				if (element.children) { //如果还有五级菜单

				} else {
					if (this.pageType == '1') { //打开了空菜单页面
						var iframeWindow = document.getElementById('myframe').contentWindow;
						if (element.menuUrl.indexOf("?") != -1) { //已经有参数
							let parentData = {
								page: element.menuUrl + '&' + MENU_BACKPARAMS_KEY + '=' + TENANT_CODE +
									'_' + APPLICATION_CODE + '_' + element.id,
								name: element.menuName,
							}
							//向父页面传值
							iframeWindow.postMessage(parentData, "*");
						} else { //没有参数
							let parentData = {
								page: element.menuUrl + '?' + MENU_BACKPARAMS_KEY + '=' + TENANT_CODE +
									'_' + APPLICATION_CODE + '_' + element.id,
								name: element.menuName,
							}
							//向父页面传值
							iframeWindow.postMessage(parentData, "*");
						}


					} else {
						if (element.menuUrl == "" || element.menuUrl == null) {
							this.$message.warning("此功能暂未开放哦！");
						} else {
							if (element.backParams != '' && element.backParams != null) {

								if (element.menuUrl.indexOf("?") != -1) { //已经有参数
									let parentData = {
										pageType: NEWTABS,
										page: element.menuUrl + '&' + MENU_BACKPARAMS_KEY + '=' +
											TENANT_CODE + '_' + APPLICATION_CODE + '_' + element.id,
										name: element.menuName,
										topType: 1
									}
									//向父页面传值
									messageSignal1(parentData)
								} else { //没有参数
									let parentData = {
										pageType: NEWTABS,
										page: element.menuUrl + '?' + MENU_BACKPARAMS_KEY + '=' +
											TENANT_CODE + '_' + APPLICATION_CODE + '_' + element.id,
										name: element.menuName,
										topType: 1
									}
									//向父页面传值
									messageSignal1(parentData)

								}
							} else {
								let parentData = {
									pageType: NEWTABS,
									page: element.menuUrl,
									name: element.menuName,
									topType: 1
								}
								//向父页面传值
								messageSignal1(parentData)
							}
						}
					}
				}




			},
			fivemenuClick(element) { //点击五级菜单
				if (this.pageType == '1') { //打开了空菜单页面
					var iframeWindow = document.getElementById('myframe').contentWindow;
					if (element.menuUrl.indexOf("?") != -1) { //已经有参数
						let parentData = {
							page: element.menuUrl + '&' + MENU_BACKPARAMS_KEY + '=' + TENANT_CODE +
								'_' + APPLICATION_CODE + '_' + element.id,
							name: element.menuName,
						}
						//向父页面传值
						iframeWindow.postMessage(parentData, "*");
					} else { //没有参数
						let parentData = {
							page: element.menuUrl + '?' + MENU_BACKPARAMS_KEY + '=' + TENANT_CODE +
								'_' + APPLICATION_CODE + '_' + element.id,
							name: element.menuName,
						}
						//向父页面传值
						iframeWindow.postMessage(parentData, "*");
					}
				} else {
					if (element.menuUrl == "" || element.menuUrl == null) {
						this.$message.warning("此功能暂未开放哦！");
					} else {
						if (element.backParams != '' && element.backParams != null) {

							if (element.menuUrl.indexOf("?") != -1) { //已经有参数
								let parentData = {
									pageType: NEWTABS,
									page: element.menuUrl + '&' + MENU_BACKPARAMS_KEY + '=' +
										TENANT_CODE + '_' + APPLICATION_CODE + '_' + element.id,
									name: element.menuName,
									topType: 1
								}
								//向父页面传值
								messageSignal1(parentData)
							} else { //没有参数
								let parentData = {
									pageType: NEWTABS,
									page: element.menuUrl + '?' + MENU_BACKPARAMS_KEY + '=' +
										TENANT_CODE + '_' + APPLICATION_CODE + '_' + element.id,
									name: element.menuName,
									topType: 1
								}
								//向父页面传值
								messageSignal1(parentData)

							}
						} else {
							let parentData = {
								pageType: NEWTABS,
								page: element.menuUrl,
								name: element.menuName,
								topType: 1
							}
							//向父页面传值
							messageSignal1(parentData)
						}
					}
				}
			},
			changeImg() { //修改头像
				this.imgDirection = true;
			},
			imgClose() { //关闭修改头像

			},
			imgUpdate() { //确认修改头像

				$.ajax({
					url: SERVER_IP_PORT + SERVER_PREFIX + "updatePlatUserBaseInfo",
					type: "POST",
					async: true,
					data: {
						"id": PLAT_GET_UID(),
						headImgUrl: vm.imageUrl,
					},
					dataType: "json",
					xhrFields: {
						withCredentials: xhrFieldsState
					},
					success: (data) => {
						if (data.code == RESULT_SUCCESS) {
							vm.headImg = vm.imageUrl;
							tool.setCookie(TENANT_CODE + '_HEAD_IMG', vm.imageUrl, COOKIE_TIME);
							vm.imgDirection = false;
						} else {
							notify.error(data.msg, data.data)
						}
					},
					error: function(data) {
						// vm.$message.error('新增失败！');
					}
				});
			},
			beforeAvatarUpload(file) {
				console.log(file)
				var formData = new FormData();
				formData.append("file", file.raw);
				formData.append("tenant", TENANT_CODE);
				formData.append("orgCode", PLAT_GET_ORGCODE());
				formData.append("modular", "IMG");
				$.ajax({
					url: SERVER_IP_PORT + 'file/fileUplod',
					type: "POST",
					async: true,
					data: formData,
					dataType: "json",
					processData: false,
					contentType: false,
					multiple: true,
					xhrFields: {
						withCredentials: xhrFieldsState
					},
					success: (data) => {
						if (data.code == RESULT_SUCCESS) {
							vm.$message.success('上传成功，请保存！');
							vm.imageUrl = data.data.filePath;

						} else {
							notify.error(data.msg, data.data)
						}
					},
					error: (data) => {

					}
				});
			}
		},
		mounted() {
			setTimeout(() => {
				//一级菜单显示二级菜单
				$(".menuItem").mouseover(function() {
					$(this).children('.displayItem').css("display", "block");
					$(this).children('.menuSelect').find("i").attr("class",
						"el-icon-arrow-up")
				});
				$(".menuItem").mouseout(function() {
					$(this).children('.displayItem').css("display", "none");
					$(this).children('.menuSelect').find("i").attr("class",
						"el-icon-arrow-down")
				});
				//二级菜单显示三级菜单
				$(".twoName").mouseover(function() {
					$(this).children('.threeMenu').css("display", "block");
				});
				$(".twoName").mouseout(function() {
					$(this).children('.threeMenu').css("display", "none");
				});
				//三级菜单显示四级菜单
				$(".threeName").mouseover(function() {
					$(this).find('.fourMenu').css("display", "block");
				});
				$(".threeName").mouseout(function() {
					$(this).find('.fourMenu').css("display", "none");
				});
				//四级菜单显示五级菜单
				$(".fourName").mouseover(function() {
					$(this).find('.fiveMenu').css("display", "block");
				});
				$(".fourName").mouseout(function() {
					$(this).find('.fiveMenu').css("display", "none");
				});




				//在菜单上加上待我审批的数字角标
				var datas = PLAT_GET_APPROVAL();
				if (datas != null) {
					this.approvalList.forEach(item => { //遍历菜单回传参数构造的对象
						let nums = 0; //显示的数量
						item.approval.forEach(element => { //如果一个菜单牵涉到多张表  那这里就是一个数组  需要遍历
							Object.keys(datas).forEach((key) => { //这里遍历待我审批的所有数据
								if (element.approvalDb ==
									key) { //根据拿到的待我审批的数据的   实体类型+表明   跟菜单回传参数做对比
									if (element.approvalState == '') { //查全部
										nums = nums + (datas[key].resultList
											.length - 0);
									} else { //根据状态过滤
										let stateList = [];
										let stateArr = element.approvalState
											.split(',');
										stateArr.forEach(items => {
											datas[key].resultList
												.forEach(type => {
													if (items ==
														type
														.state
														) {
														stateList
															.push(
																type
																)
													}
												})
										})
										nums = nums + (stateList.length -
										0);
									}
								}
							});
						})

						$(`.${item.domId}`).text('(' + nums + ')');
					})
				}

			}, 3000);
		}
	});
}

const sortClass = (sortData) => {
	const groupBy = (array, f) => {
		let groups = {};
		array.forEach((o) => {
			let group = JSON.stringify(f(o));
			groups[group] = groups[group] || [];
			groups[group].push(o);
		});
		return Object.keys(groups).map((group) => {
			return groups[group];
		});
	};
	const sorted = groupBy(sortData, (item) => {
		return item.entityTypeCode; // 返回需要分组的对象
	});
	return sorted;
};

// 初始化iframe的高度
var initIframeHeight = () => {
	var headerHight = $(".header").outerHeight(true);
	var initialHight = document.documentElement.clientHeight;
	vm.iframeHeight = initialHight - headerHight + 'px';
	$(window).resize(function() { //当浏览器大小变化时
		var resizeHight = $(window).height();
		var headerResizeHight = $(".header").outerHeight(true);
		vm.iframeHeight = resizeHight - headerResizeHight + 'px';


		//顶部菜单
		var menuWight = $(".menuContent").outerWidth(true);
		if (menuWight > vm.transDate.length * 100) {
			vm.menuicon = false;
		} else {
			vm.menuicon = true;
		}
	});
}

var changeOrg = () => {
	$.ajax({
		url: SERVER_IP_PORT + SERVER_PREFIX + "queryOrgTreeByUserId",
		type: "POST",
		data: {
			"userId": PLAT_GET_UID()
		},
		async: true,
		dataType: "json",
		xhrFields: {
			withCredentials: xhrFieldsState
		},
		success: (data) => {
			let org = data.data;
			let obj = {};
			let peon = org.reduce((cur, next) => {
				obj[next.id] ? "" : obj[next.id] = true && cur.push(next);
				return cur;
			}, []);
			let orgDatas = transDate(peon, "id", "pId");
			sessionStorage.setItem(TENANT_CODE + "_orgTree", JSON.stringify(orgDatas));

			let orgDatasAttr = orgDatas;
			// let resultid="";
			// let resultname="";
			let resultid = getOrgCode(orgDatasAttr);
			let resultname = getOrgName(orgDatasAttr);
			/* orgDatasAttr.forEach((item,i)=>{
			     if (item.children){
			         resultid+= item.value+",";
			         item.children.forEach((k,i)=>{
			             resultid+= k.value+",";
			         });
			     }else {
			         resultid+= item.value+",";
			     }
			 });*/
			let resultArrs = resultid.substring(0, resultid.length - 1);

			sessionStorage.setItem(TENANT_CODE + "_orgCode", resultArrs);
			/*取子节点的name*/
			/* orgDatasAttr.forEach((name,i)=>{
			 if (name.children){
			 resultname+= name.label+",";
			 name.children.forEach((n,i)=>{
			 resultname+= n.label+",";
			 });
			 }else {
			 resultname+= name.label+",";
			 }
			 }); */
			let resultnames = resultname.substring(0, resultname.length - 1);
			sessionStorage.setItem(TENANT_CODE + "_orgName", resultnames);


		}
	});
}


//当打开新菜单的时候高度
var addHeight = () => {

	let resizeHight = document.documentElement.clientHeight;
	let headerResizeHight = $(".contentTabs .el-tabs__header").outerHeight(true);
	$(".contentTabs .iframetab").height(resizeHight - headerResizeHight - 60);
	vm.iframesHight = resizeHight - headerResizeHight - 60 + "px";
	vm.contentHight = resizeHight + 'px';
	$(window).resize(function() { //当浏览器大小变化时
		var resizeHight = $(window).height();
		let headerResizeHight = $(".contentTabs .el-tabs__header").outerHeight(true);
		$(".contentTabs .iframetab").height(resizeHight - headerResizeHight - 60);
		vm.iframesHight = resizeHight - headerResizeHight - 60 + "px";
		vm.contentHight = resizeHight + 'px';
	});
}

var messageSignal = () => {
	window.addEventListener('message', (event) => { //子获取父消息

		
		let data = event.data;
		if (data.topType != undefined) {
			if (data.topType == ORG_SHOW) { //展示组织选择框
				vm.titleShow = ORG_SHOW;
			} else {
				vm.titleShow = ORG_NOSHOW;
				vm.keyWord = '';
				// changeOrg();
			}
		} else {
			vm.titleShow = ORG_SHOW;
		}
		if (data.pageType != undefined) { 
			if (data.pageType == NEWTABS) { //打开新标签
				vm.pageType = NEWTABS;
				let flag = true;
				for (let k = 0; k < vm.editableTabs.length; k++) {
					if (vm.editableTabs[k].name == event.data.name) {
						flag = false;
					}
				}
				if (flag) {
					let path = [event.data.page + "*" + event.data.name + "*" + "false"];
					localStorage.setItem("paths", JSON.stringify(path));
					let getUrl = getQueryList(event.data.page);
					if (getUrl.length > 0 || event.data.page.indexOf("?") == -1) {
						vm.editableTabs.push({
							title: event.data.name,
							name: event.data.name,
							content: `<iframe class="iframetab"    :height="iframesHight" src=${event.data.page+'?v='+new Date().getTime()} frameborder="0"></iframe>`
						})
					} else {
						vm.editableTabs.push({
							title: event.data.name,
							name: event.data.name,
							content: `<iframe class="iframetab"   :height="iframesHight" src=${event.data.page+'&v='+new Date().getTime()} frameborder="0"></iframe>`
						})
					}
				}
				vm.editableTabsValue = event.data.name
				setTimeout(() => {
					addHeight();
				}, 100);
			}
		} else if (data.name != undefined && data.page != undefined) {
			vm.pageType = NEWTABS;
			let flag = true;
			for (let k = 0; k < vm.editableTabs.length; k++) {
				if (vm.editableTabs[k].name == event.data.name) {
					flag = false;
				}
			}
			if (flag) {
				let path = [event.data.page + "*" + event.data.name + "*" + "false"];
				localStorage.setItem("paths", JSON.stringify(path));
				let getUrl = getQueryList(event.data.page);
				if (getUrl.length > 0 || event.data.page.indexOf("?") == -1) {
					vm.editableTabs.push({
						title: event.data.name,
						name: event.data.name,
						content: `<iframe class="iframetab"    :height="iframesHight" src=${event.data.page+'?v='+new Date().getTime()} frameborder="0"></iframe>`
					})
				} else {
					vm.editableTabs.push({
						title: event.data.name,
						name: event.data.name,
						content: `<iframe class="iframetab"   :height="iframesHight" src=${event.data.page+'&v='+new Date().getTime()} frameborder="0"></iframe>`
					})
				}
			}
			vm.editableTabsValue = event.data.name
			setTimeout(() => {
				addHeight();
			}, 100);
		}


		if(data.operationType == 1){   //打开新页签  页面初始化  修改orgCode树
            var site = sessionStorage.getItem(data.webPageCode + "_orgTree"); 
            if(site != undefined){
                updateMsg();
            }
            sessionStorage.setItem(data.webPageCode + "_orgTree", JSON.stringify(data.orgData));
			vm.keyChilder = JSON.stringify(data.orgData)
            vm.editableTabs.forEach(item=>{
                if(item.name == vm.editableTabsValue){
                    item.pageCode = data.webPageCode
                }
            })
            
		}else if(data.operationType == 2){   //切换页签时  修改orgCode树
			var site = data.orgTree; 
			vm.keyChilder = site
            
		}else{
            // debugger
        }
		

	}, false);
}

var messageSignal1 = (event) => {
	console.log(event)
	let data = event;
	console.log(data)
	if (data.topType != undefined) {
		if (data.topType == ORG_SHOW) { //展示组织选择框
			vm.titleShow = ORG_SHOW;
		} else {
			vm.titleShow = ORG_NOSHOW;
			vm.keyWord = '';
			// changeOrg();
		}
	} else {
		vm.titleShow = ORG_SHOW;
	}
	vm.titleShow = ORG_SHOW;
	if (data.pageType != undefined) {
		if (data.pageType == NEWTABS) { //打开新标签
			vm.pageType = NEWTABS;
			let flag = true;
			for (let k = 0; k < vm.editableTabs.length; k++) {
				if (vm.editableTabs[k].name == event.name) {
					flag = false;
				}
			}
			if (flag) {
				let path = [event.page + "*" + event.name + "*" + "false"];
				localStorage.setItem("paths", JSON.stringify(path));
				let getUrl = getQueryList(event.page);
				if (getUrl.length > 0 || event.page.indexOf("?") == -1) {
					vm.editableTabs.push({
						title: event.name,
						name: event.name,
						content: `<iframe class="iframetab"    :height="iframesHight" src=${event.page+'?v='+new Date().getTime()} frameborder="0"></iframe>`
					})
				} else {
					vm.editableTabs.push({
						title: event.name,
						name: event.name,
						content: `<iframe class="iframetab"   :height="iframesHight" src=${event.page+'&v='+new Date().getTime()} frameborder="0"></iframe>`
					})
				}
			}
			vm.editableTabsValue = event.name
			setTimeout(() => {
				addHeight();
			}, 100);
		}
	}
}


function GET_APPLICATION_CODE(){
    return APPLICATION_CODE;
}


function updateMsg(){
    if(vm.pageType == '2'){  //在当前页面直接打开了标签页
        vm.editableTabs.forEach((item,index)=>{
            if(item.name == vm.editableTabsValue){
                let childWindow = $(".iframetab")[index].contentWindow; //
                childWindow.initOrgTreeComplete()
            }
        })
    }else{  //有左侧菜单栏的菜单
        let childWindow = $("#myframe")[0].contentWindow; //
        childWindow.initOrgTreeComplete()
    }
}