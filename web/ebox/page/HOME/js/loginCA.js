var vm = null;
const S_ALG = "SM3withSM2";

$(function () {
	
	var str = "MIIEfjCCBCOgAwIBAgIQRse0T54+TYybVqgTTlHkkzAMBggqgRzPVQGDdQUAMDQxCzAJBgNVBAYTAkNOMREwDwYDVQQKDAhVbmlUcnVzdDESMBAGA1UEAwwJU0hFQ0EgU00yMB4XDTIxMDkyNjA2MDEwOFoXDTIzMDkyNjE1NTk1OVowgbgxCzAJBgNVBAYTAkNOMQ8wDQYDVQQIDAbkuIrmtbcxDzANBgNVBAcMBuS4iua1tzEqMCgGA1UECgwh5Lit5YWx5LiK5rW35biC6Ze16KGM5Yy65aeU5YWa5qChMSowKAYDVQQLDCHkuK3lhbHkuIrmtbfluILpl7XooYzljLrlp5TlhZrmoKExHjAcBgkqhkiG9w0BCQEWD3llY0BzaG1oLmdvdi5jbjEPMA0GA1UEAwwG5Y+25pilMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEL+Rw5FzQW44jfV99qHdmVxrhiuDfKrvxuYZWeRAvI80G19C7r3IdHLdB0/rxNdj8O0jXdX3p5FP80s6d9vZtR6OCAo4wggKKMB8GA1UdIwQYMBaAFIkxBJF7Q6qqmr+EHZuG7vC4cJmgMB0GA1UdDgQWBBRZbxEY1nnQ9TipE/3e68sRvYx69zALBgNVHQ8EBAMCBsAwEwYDVR0lBAwwCgYIKwYBBQUHAwIwQgYDVR0gBDswOTA3BgkqgRwBhu86gRUwKjAoBggrBgEFBQcCARYcaHR0cDovL3d3dy5zaGVjYS5jb20vcG9saWN5LzAJBgNVHRMEAjAAMIHoBgNVHR8EgeAwgd0wgZ2ggZqggZeGgZRsZGFwOi8vbGRhcDIuc2hjYS5zaC5jZWduLmNuOjM4OS9jbj1DUkwwLmNybCxvdT1SQTIwMjEwODEwLG91PUNBOTEsb3U9Y3JsLG89VW5pVHJ1c3Q/Y2VydGlmaWNhdGVSZXZvY2F0aW9uTGlzdD9iYXNlP29iamVjdENsYXNzPWNSTERpc3RyaWJ1dGlvblBvaW50MDugOaA3hjVodHRwOi8vbGRhcDIuc2hjYS5zaC5jZWduLmNuL0NBOTEvUkEyMDIxMDgxMC9DUkwwLmNybDCBjQYIKwYBBQUHAQEEgYAwfjA9BggrBgEFBQcwAYYxaHR0cDovL29jc3Auc2hjYS5zaC5jZWduLmNuL29jc3Avc2hlY2Evc2hlY2Eub2NzcDA9BggrBgEFBQcwAoYxaHR0cDovL2xkYXAyLnNoY2Euc2guY2Vnbi5jbi9yb290L3NoZWNhc20yc3ViLmRlcjAgBggqgRyG7zoMaAQUExIzMTAqKioqKioqKioqKjAwMTIwOgYIKoEchu86DGkELhMsd3VwUFVYdnZzUjlyU2VaQWV5MzROVHJ2Wk5jT1BjWUpwYVg4alNpcU5iTT0wDAYIKoEcz1UBg3UFAANHADBEAh8yzX/VyzLHjuf1mIEXk2SBcz73BwXY29Wz/7OyU1OWAiEAwxBB/T/GSF7gotT+JeQVrwdJV2v6Ea9szCWBJD+eeA8=";

   var str2 = "MEQCIG5usn7IDIr+LSFmEGnM2dOomaBECGpouk8hGxuY19LOAiAZowCMvz5ibt8OQuQX56XdRUDcz4e7LRQgNS01bRpZHA==";
	var _str = str.replace(/\+/g,'%2B')
	var _str2 = str2.replace(/\+/g,'%2B')
	console.log(_str)
	console.log(str2)
	
    vm = new Vue({
        el: '#root',
        data() {
            return {
				dialogVisible:false,//是否打开弹框
				close:false,
				caInfo:{
					keySN:null,
					pin:null, //证书密码
					token:null,//后台返回的随机数
					cert:null,//证书
					sign:null,//数字签名
				},
                userName: '',//用户名
                passWord: '',//密码
				resData:null,
            }
        },
        beforeMount() {
            this.userName = localStorage.getItem("userNameRemember");
        },
        created: function () {
            //登录添加键盘事件,注意,不能直接在焦点事件上添加回车
            let that = this;
            document.onkeydown = function (e) {
                let key = window.event.keyCode;
                if (key === 13) {
                    logins();
                }
            }
        },
        methods: {
            login() {
                logins();
            },
			getSignature(){
				getSignature();
			},
			handleClose(){
				$.each(vm.caInfo,function(index,val){
					vm.caInfo[index] = null;
				})
				this.dialogVisible = false;
			}
        },
    })
})
function logins() {
    if (vm.userName == "") {
        vm.$message.error('请输入用户名!');
        //alert("请输入用户名!");
    } else if (vm.passWord == "") {
        vm.$message.error('请输入密码!');
        //alert("请输入密码!");
    } else {
        localStorage.setItem("userNameRemember", vm.userName);
        $.ajax({
            url: SERVER_IP_PORT + SERVER_PREFIX + "login",
            type: "POST",
            async: true,
            data: { "userName": vm.userName, "passWord": vm.passWord, "tenant": TENANT_CODE, "applicationCode": APPLICATION_CODE },
            dataType: "json",
            success: function (data) {
                if (data.code == "1") {
					vm.resData = data;
                    
					vm.caInfo.token = data.data.token;
					getCaUkeyInfo();
                  
                } else if (data.code == "0") {
                    vm.$message.error(data.msg);
                }
            },
            error: function (data) {
                vm.$message.error('出错了哦，请联系管理员！');
                //alert("登陆失败,请联系管理员！");
            }
        });
    }
}

/**
 * 获取ukey信息列表
 */
function getCaUkeyInfo(){
	let url = CA_URL + "/uk/action/100";
	$.ajax({
		type: "POST",           //因为是传输文件，所以必须是post
		url: url,         //对应的后台处理类的地址
		data: null,
		contentType: "application/x-www-form-urlencoded",
		success: function (data) {
			let ukeyInfo = JSON.parse(data.data);
			if(ukeyInfo.length == 0){
				notify.warning("请插入UKEY!");
			}else{
				vm.caInfo.keySN = ukeyInfo[0].keySN;
				//弹框输入pin
				vm.dialogVisible = true;
			}
			
		},
		error: function(data) {
			notify.error("请打开证书助手");
		}
	});
}

/**
 * 获取数字签名
 */
function getSignature(){
	let url = CA_URL + "/uk/action/300";
	let param = {};
	param.type = "300" ;
	param.keySN = vm.caInfo.keySN;
	param.pin = vm.caInfo.pin;
	param.raw = vm.caInfo.token;
	param.asymid = "2";
	param.wrapperType = "0";
	$.ajax({
		type: "POST",           //因为是传输文件，所以必须是post
		url: url,         //对应的后台处理类的地址
		data: param,
		contentType: "application/x-www-form-urlencoded",
		success: function (data) {
			if(data.code == 0){
				vm.caInfo.sign = data.data;
				getCertificate();//获取证书
			}else if (data.code == "167772199"){
				 vm.$message.error("证书PIN认证失败！请输入正确的口令！");
			}else{
				vm.$message.error("证书认证失败,请联系管理员!");
			}
			
		},
		error: function(data) {
			notify.error(NOTIFY_SYS_ERROR);
		}
	});
}

/**
 * 获取证书
 */

function getCertificate(){
	let url = CA_URL + "/uk/action/200";
	let param = {};
	param.type = "200" ;
	param.keySN = vm.caInfo.keySN;
	param.asymid = "2";
	param.usage = "2";

	$.ajax({
		type: "POST",           //因为是传输文件，所以必须是post
		url: url,         //对应的后台处理类的地址
		data: param,
		contentType: "application/x-www-form-urlencoded",
		success: function (data) {
			if(data.code == 0){
				console.log(data.data)
				vm.caInfo.cert = data.data;
				verifySignWithCert();
			}else{
				vm.$message.error("获取证书失败");
			}
			
		},
		error: function(data) {
			notify.error(NOTIFY_SYS_ERROR);
		}
	});
}


/**
 * 验签，校验
 */
function verifySignWithCert(){
	let url = SERVER_IP_PORT + SERVER_PREFIX + "openApi/doCheckMySign";
	let param = {};
	param.mycert = vm.caInfo.cert.replace(/\+/g,'%2B');
	param.mydata = vm.caInfo.token.replace(/\+/g,'%2B');
	param.mysign = vm.caInfo.sign.replace(/\+/g,'%2B');
	param.mysAlg = S_ALG.replace;
	console.log(param)
	
	$.ajax({
		type: "POST",           //因为是传输文件，所以必须是post
		url: url,         //对应的后台处理类的地址
		data: param,
		contentType: "application/x-www-form-urlencoded",
		success: function (data) {
			data = JSON.parse(data)
			if(data.data == "0"){
				var tenant = vm.resData.data.tenant;//租户
				var loginuser = encodeURIComponent(vm.resData.data.nickName);//登录人
				var authorityId = vm.resData.data.authorityId;//roleid
				var authorityName = encodeURIComponent(vm.resData.data.authorityName);//rolename
				var username = vm.resData.data.loginName;// USERNAME
				var uId = vm.resData.data.uId;// UID
				var headImgUrl = vm.resData.data.headImgUrl;// UID
				var userOrg = vm.resData.data.userOrg;
				tool.setCookie(TENANT_CODE+'_TENANT', tenant, COOKIE_TIME);
				tool.setCookie('SYS_TENANT', tenant, COOKIE_TIME);
				tool.setCookie(TENANT_CODE+'_LOGINUSER', loginuser, COOKIE_TIME);
				tool.setCookie(TENANT_CODE+'_AUTHORITY_ID', authorityId, COOKIE_TIME);
				tool.setCookie(TENANT_CODE+'_AUTHORITY_NAME', authorityName, COOKIE_TIME);
				tool.setCookie(TENANT_CODE+'_USERNAME', username, COOKIE_TIME);
				tool.setCookie(TENANT_CODE+'_UID', uId, COOKIE_TIME);
				tool.setCookie(TENANT_CODE+'_USER_ORG', userOrg, COOKIE_TIME);
				tool.setCookie(TENANT_CODE+'_HEAD_IMG', headImgUrl, COOKIE_TIME);
				tool.setCookie(TENANT_CODE+'_APPLICATION_CODE', APPLICATION_CODE, COOKIE_TIME);
				sessionStorage.setItem(TENANT_CODE+"_org", vm.resData.data.orgCode);
				sessionStorage.setItem(TENANT_CODE+"_orgCode", vm.resData.data.orgCode);
				window.location.href = "index.html?type=ca";
			}else{
				vm.$message.error("证书认证失败！");
			}
		},
		error: function(data) {
			notify.error(NOTIFY_SYS_ERROR);
		}
	});
}
