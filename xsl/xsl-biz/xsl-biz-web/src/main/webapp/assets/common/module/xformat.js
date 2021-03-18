layui.define(['jquery', 'laytpl'], function (exports) {
    var $ = layui.jquery;
    var xformat = {
        formatCens2Yuan: function (amt) {
            if(null == amt || "" == amt || "undefined" == amt){
                amt = 0;
            }

            var famt = parseFloat(amt) / 100;
            /*
               * 参数说明：
               * number：要格式化的数字
               * decimals：保留几位小数
               * dec_point：小数点符号
               * thousands_sep：千分位符号
               * */
            var number = (famt + '').replace(/[^0-9+-Ee.]/g, '');
            var decimals = 2, dec_point = ".", thousands_sep = ",";
            var n = !isFinite(+number) ? 0 : +number,
                prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
                sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
                dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
                s = '',
                toFixedFix = function(n,prec) {
                    return "" + n.toFixed(prec);
                }


            s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
            var re = /(-?\d+)(\d{3})/;
            while (re.test(s[0])) {
                s[0] = s[0].replace(re, "$1" + sep + "$2");
            }

            if ((s[1] || '').length < prec) {
                s[1] = s[1] || '';
                s[1] += new Array(prec - s[1].length + 1).join('0');
            }
            return s.join(dec);
        },
        checkAmount : function(amt) {
            var regx = /^\d+\.?\d{0,2}$/;
            if (regx.test(amt)) {
                return true;
            }else{
                return false;
            }
        },
        dateFormat : function(date, fmt) {
            if(null == fmt){
                fmt = 'YYYY-mm-dd';
            }

            if(null == date){
                date = new Date();
            }

            let ret;
            let opt = {
                "Y+": date.getFullYear().toString(),        // 年
                "m+": (date.getMonth() + 1).toString(),     // 月
                "d+": date.getDate().toString(),            // 日
                "H+": date.getHours().toString(),           // 时
                "M+": date.getMinutes().toString(),         // 分
                "S+": date.getSeconds().toString()          // 秒
                // 有其他格式化字符需求可以继续添加，必须转化成字符串
            };
            for (let k in opt) {
                ret = new RegExp("(" + k + ")").exec(fmt);
                if (ret) {
                    fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
                };
            };
            return fmt;
        },
        dateStrFormat : function(dateStr, separator, fmt) {
            var date = null;
            if(null == fmt){
                fmt = 'YYYY-mm-dd';
            }

            if(null == dateStr || dateStr.length == 0){
                return '';
            }

            if(null == separator){
                separator = "-";
            }

            if('' === separator){
                //默认为yyyyMMdd
                var year = parseInt(dateStr.substring(0,4));
                var month = parseInt(dateStr.substring(4,6));
                var day = parseInt(dateStr.substring(6));
                date = new Date(year,month -1,day);
            }else {
                var dateArr = dateStr.split(separator);
                var year = parseInt(dateArr[0]);
                var month;
                if(dateArr[1].indexOf("0") == 0){
                    month = parseInt(dateArr[1].substring(1));
                }else{
                    month = parseInt(dateArr[1]);
                }
                var day = parseInt(dateArr[2]);
                date = new Date(year,month -1,day);
            }

            let ret;
            let opt = {
                "Y+": date.getFullYear().toString(),        // 年
                "m+": (date.getMonth() + 1).toString(),     // 月
                "d+": date.getDate().toString(),            // 日
                "H+": date.getHours().toString(),           // 时
                "M+": date.getMinutes().toString(),         // 分
                "S+": date.getSeconds().toString()          // 秒
                // 有其他格式化字符需求可以继续添加，必须转化成字符串
            };
            for (let k in opt) {
                ret = new RegExp("(" + k + ")").exec(fmt);
                if (ret) {
                    fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
                };
            };
            return fmt;
        },
        pointToPercent : function(point, fixed){
            if(null == point || "" == point || "undefined" == point){
                amt = 0;
            }

            var str = Number(point*100).toFixed(fixed);
            str+="%";
            return str;
        }
    }


    exports('xformat', xformat);
})