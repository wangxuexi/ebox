var FIELDTYPE = null ;
FIELDTYPE = {
    "input" : 1,//输入框
    "textarea" : 2,//文本域
    "number" : 3,//数字
    "datepicker" : 4,//日期选择器
    "select" : 5//选择器
}
var AUTOFIELDVM = {};

var AUTOFIELDSTORAGE = {};

var OBJVALUE = {};

FIELDTYPE_QUERY = true;
FIELDTYPE_EDIT = false;

const MENU_BACKPARAMS_KEY = "MENUKEY";//菜单回调参数 的key值前缀