var vm = null;

$(function () {
    vm = new Vue({
        el: '#root',
        data() {
            return {
                userName: '',//用户名
                passWord: '',//密码
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
                    var tenant = data.data.tenant;//租户
                    var loginuser = encodeURIComponent(data.data.nickName);//登录人
                    var authorityId = data.data.authorityId;//roleid
                    var authorityName = encodeURIComponent(data.data.authorityName);//rolename
                    var username = data.data.loginName;// USERNAME
                    var uId = data.data.uId;// UID
                    var headImgUrl = data.data.headImgUrl;// UID
                    var userOrg = data.data.userOrg;
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
                    sessionStorage.setItem(TENANT_CODE+"_org", data.data.orgCode);
                    sessionStorage.setItem(TENANT_CODE+"_orgCode", data.data.orgCode);
                    window.location.href = "index.html";
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