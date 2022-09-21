
var tool = {
    //获取数据类型，返回结果为 Number、String、Object、Array等
    getRawType: function (value) {
        return Object.prototype.toString.call(value).slice(8, -1)
    }
    //检查 value 是否为空 如果是null，直接返回true；如果是类数组，判断数据长度；如果是Object对象，判断是否具有属性；如果是其他数据，直接返回false
    , isEmpty: function (value) {
        if (value == null) {
            return false;
        }
        if (tool.isArrayLike(value)) {
            if (value.length == 0) {
                return false;
            } else {
                return true;
            }
        } else if (tool.isPlainObject(value)) {
            for (let key in value) {
                if (hasOwnProperty.call(value, key)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    },
    //检查 value 是否是类数组
    isArrayLike: function (value) {
        return value != null && tool.isLength(value.length) && !tool.isFunction(value);
    },
    //判断数据是不是Object类型的数据
    isPlainObject: function (obj) {
        return Object.prototype.toString.call(obj) === '[object Object]'
    },
    //检查 value 是否为有效的类数组长度
    isLength: function (value) {
        return typeof value == 'number' && value > -1 && value % 1 == 0 && value <= Number.MAX_SAFE_INTEGER;
    },
    //检查 value 是不是函数
    isFunction: function (value) {
        return Object.prototype.toString.call(value) === '[object Function]'
    },
    //获取Url参数，返回一个对象
    GetUrlParam: function () {
        let url = document.location.toString();
        let arrObj = url.split("?");
        let params = Object.create(null)
        if (arrObj.length > 1) {
            arrObj = arrObj[1].split("&");
            arrObj.forEach(item => {
                item = item.split("=");
                params[item[0]] = item[1]
            })
        }
        return params;
    },
    //数组去重
    unique: function (arr) {
        return Array.from(new Set(arr))
    },
    //千分符并保留两位小数 a为需要处理的数据，n为需要保留的位数
    formatNumber: function (a, n) {
        if (n === 0) {
            a = a.replace(/(\d)(?=(\d{3})+$)/g, "$1,");
        } else {
            n1 = Math.pow(10, n);
            a = (Math.round(a * n1) / n1).toFixed(n).toString().replace(/(\d)(?=(\d{3})+\.)/g, function ($0, $1) { return $1 + ","; });
        }
        return a;
    },
    //创建cookie 名字 值 天数
    setCookie: function (cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    },
    //获取cookie
    getCookie: function (cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    },
    //将后台传过来的树转成需要的格式 参数list为数据 idstr为id pid
    transDate: function (list, idstr, pidstr) {
        var result = [], temp = {};
        for (i = 0; i < list.length; i++) {
            temp[list[i][idstr]] = list[i];//将nodes数组转成对象类型
        }
        for (j = 0; j < list.length; j++) {
            tempVp = temp[list[j][pidstr]]; //获取每一个子对象的父对象
            if (tempVp) {//判断父对象是否存在，如果不存在直接将对象放到第一层
                if (!tempVp["children"]) tempVp["children"] = [];//如果父元素的nodes对象不存在，则创建数组
                tempVp["children"].push(list[j]);//将本对象压入父对象的nodes数组
            } else {
                result.push(list[j]);//将不存在父对象的对象直接放入一级目录
            }
        }
        return result;
    },
    //判断元素是否存在于数组中
    isInArray: function (arr, value) {
        if (arr.indexOf && typeof (arr.indexOf) == 'function') {
            var index = arr.indexOf(value);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    },
    //判断数组中是否存在某个key值
    isInList: function (list,value)  {
       return list.hasOwnProperty(value);
    },
    //生成从minNum到maxNum的随机数
    randomNum(minNum,maxNum){
        switch(arguments.length){
            case 1:
                return parseInt(Math.random()*minNum+1,10);
                break;
            case 2:
                return parseInt(Math.random()*(maxNum-minNum+1)+minNum,10);
                break;
            default:
                return 0;
                break;
        }
    },
    formatTime: function (time) {  //格式化时间
        let ymd = ''
        let mouth = (time.getMonth() + 1) >= 10 ? (time.getMonth() + 1) : ('0' + (time.getMonth() + 1))
        let day = time.getDate() >= 10 ? time.getDate() : ('0' + time.getDate())
        ymd += time.getFullYear() + '-' // 获取年份。
        ymd += mouth + '-' // 获取月份。
        ymd += day // 获取日。
        return ymd // 返回日期。
    },
    getAllDate: function (start, end) { //根据开始日期和结束日期获取中间的所有日期 参数为开始日期和结束日期
        let dateArr = []
        let startArr = start.split('-')
        let endArr = end.split('-')
        let db = new Date()
        db.setUTCFullYear(startArr[0], startArr[1] - 1, startArr[2])
        let de = new Date()
        de.setUTCFullYear(endArr[0], endArr[1] - 1, endArr[2])
        let unixDb = db.getTime()
        let unixDe = de.getTime()
        let stamp
        const oneDay = 24 * 60 * 60 * 1000;
        for (stamp = unixDb; stamp <= unixDe;) {
            dateArr.push(tool.formatTime(new Date(parseInt(stamp))))
            stamp = stamp + oneDay
        }
        return dateArr
    }

}







