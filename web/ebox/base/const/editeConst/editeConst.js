var EDITENUM_ADD = "1";  //新增
var EDITENUM_UPDATE = "2"; //修改
var EDITENUM_DELETE = "3"; //删除
var EDITENUM_QUERY = "4";
var EDIT_FLAG = false;
var NEWTABS = '2';//  如果为2  则二级页面打开新标签页

var ORG_SHOW = '2';//显示组织
var ORG_NOSHOW = '1';//显示全文检索


var ANCHOR_FLAG = true; //锚点时是否有二级菜单

var msgType = [   //首页的消息通知配置
    {type:1,name:'系统消息',url:'../XXTZ/XXTZ0001.html',entityTypeCode:'ECTZ0001'},
    {type:2,name:'预警消息',url:'',entityTypeCode:'ECTX0001'},
    {type:3,name:'快递消息',url:'../ECKD/ECKD0001.html?fromType=1',entityTypeCode:'ECKD0001'},
    {type:4,name:'会场预定',url:'../DXRM/DXRM0003.html',entityTypeCode:'DXRM0003'},
];