var comjsthis = (function(){
    //静态私有属性方法
    var  vtablereq={
        statusName: 'nStatus' //数据状态的字段名称，默认：code
        ,statusCode: 0 //成功的状态码，默认：0
        ,msgName: 'szError' //状态信息的字段名称，默认：msg
        ,countName: 'count' //数据总数的字段名称，默认：count
        ,dataName: 'data' //数据列表的字段名称，默认：data
    }
    //构造函数
    function _comjs() {
        var _this = this;
        _this.limit=10;
        _this.vtablereq = vtablereq;
        _this.papercolor = function (data) {
            return "彩色";
        };
        _this.buytype= function (data) {
            if(data==1)
            {
                return "包月";
            }
            else if(data==2)
            {
                return "包时";
            }
            return "";
        };
        _this.price = function (data) {
            if(data<=1)
            {
                return data/100;
            }
            var vyuan=data/100;
            var vjue=data%100;
            return parseInt(vyuan)+'.'+vjue;
        };
        _this.getTime = function (data) {
            var d = new Date(data);    //根据时间戳生成的时间对象
            var vMonth=d.getMonth()+1;
            if(vMonth<10)
            {
                vMonth="0"+vMonth;
            }
            var vDate=d.getDate();
            if(vDate<10)
            {
                vDate="0"+vDate;
            }
            var vHours=d.getHours();
            if(vHours<10)
            {
                vHours="0"+vHours;
            }
            var vMinutes=d.getMinutes();
            if(vMinutes<10)
            {
                vMinutes="0"+vMinutes;
            }
            var date = (d.getFullYear()) + "-" +vMonth+"-"+vDate+" "+vHours+":"+vMinutes;
            return date;
        };
        _this.papersize = function (data) {
            if(data==1)
            {
                return "A4";
            }
            else if(data==2)
            {
                return "A3";
            }
            return "";
        };
        _this.devprop= function (data) {
            var vres="";
            if((data&1)>0)
            {
                vres=vres+"WIFI,"
            }
            if((data&2)>0)
            {
                vres=vres+"双面,"
            }
            if((data&4)>0)
            {
                vres=vres+"USB,"
            }
            if((data&8)>0)
            {
                vres=vres+"彩色,"
            }
            if((data&16)>0)
            {
                vres=vres+"复印,"
            }
            if(vres.length>0)
            {
             vres=vres.substring(0,vres.length-1);
            }
            return vres;
        };
        _this.getdate= function (data) {
            var vtemp = data % 10000;
            var vYear = parseInt(data / 10000);
            var vmonth = parseInt(vtemp / 100);
            if (vmonth < 10) {
                vmonth = "0" + vmonth;
            }
            var vdate = vtemp % 100;
            if (vdate < 10) {
                vdate = "0" + vdate;
            }
            return vYear + '-' + vmonth + '-' + vdate;
        };

        _this.goodsstatue= function (data) {
            if(data==1)
            {
                return "正常";
            }
            else if(data==0)
            {
                return "不开放";
            }
            return "";
        };
        _this.getcheckvalue=function (checklist) {
            var value = 0;
            for(var i = 0; i < checklist.length; i++){
                if(checklist[i].checked)
                    value=value| parseInt(checklist[i].value);
            }
            return value;
        }
        _this.valtostr=function (value) {
            var vRes = "";
            if ((value & 1) > 0) {
                vRes = vRes+'1,';
            }
            if ((value & 2) > 0) {
                vRes = vRes+'2,';
            }
            if ((value & 4) > 0) {
                vRes =vRes+ '4,';
            }
            if ((value & 8) > 0) {
                vRes = vRes+'8,';
            }
            if ((value & 16) > 0) {
                vRes = vRes+'16,';
            }
            if ((value & 32) > 0) {
                vRes = vRes+'32,';
            }
            if ((value & 64) > 0) {
                vRes = vRes+'64,';
            }
            if ((value & 128) > 0) {
                vRes =vRes+ '128,';
            }
            if ((value & 256) > 0) {
                vRes = vRes+'256,';
            }
            if (vRes.length > 0) {
                vRes = vRes.substring(0, vRes.length - 1);
            }
            return vRes;
        }
        _this.getUrlParam=function(paraName) {
            var url = document.location.toString();
            var arrObj = url.split("?");

            if (arrObj.length > 1) {
                var arrPara = arrObj[1].split("&");
                var arr;

                for (var i = 0; i < arrPara.length; i++) {
                    arr = arrPara[i].split("=");

                    if (arr != null && arr[0] == paraName) {
                        return arr[1];
                    }
                }
                return "";
            }
            else {
                return "";
            }
        }
    }
    return _comjs;
})();
var comjs=new comjsthis();