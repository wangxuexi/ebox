//获取时间
var nowDate = new Date();
var year = nowDate.getFullYear();
var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
var hour = nowDate.getHours() < 10 ? "0" + nowDate.getHours() : nowDate.getHours();
var minute = nowDate.getMinutes() < 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();
var second = nowDate.getSeconds() < 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();
var time = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
var dates = year + "-" + month + "-" + date;
var week;
if (new Date().getDay() == 0) week = "星期日"
if (new Date().getDay() == 1) week = "星期一"
if (new Date().getDay() == 2) week = "星期二"
if (new Date().getDay() == 3) week = "星期三"
if (new Date().getDay() == 4) week = "星期四"
if (new Date().getDay() == 5) week = "星期五"
if (new Date().getDay() == 6) week = "星期六"
var times = year + "年" + month + "月" + date + "" + " " + week + " " + hour + ":" + minute + ":" + second;
var mapTime = year + month + date;
var mapTimes = year + "-" + month + "-" + date;//年-月-日
var timeHour = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second; //年-月-日 时-分-秒