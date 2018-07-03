layui.use(['table','form','jquery','laydate'], function() {
    var table = layui.table
        , $ = layui.jquery
        ,laydate = layui.laydate
        , form = layui.form;
    var vwhere = {};
    laydate.render({
        elem: '#scoreDate'
    });
    function getdata() {

    }

    //方法级渲染
    table.render({
        elem: '#t_customerInfo'
        , url: '/score/getScore'
        , method: 'post'
        , response: comjs.vtablereq
        , cols: [[
            {checkbox: true, fixed: true}
            , {field: 'scoreid', title: '编号'}
            , {field: 'createdate', title: '姓名', templet: function(d){
                var name = d.userObj.username;
                return name;}
            }
            , {field: 'createdate', title: '电话',templet: function(d){
                var name = d.userObj.telphone;
                return name;}
            }
            , {field: 'scorevalue', title: '积分值'}
            , {field: 'scoretype', title: '方式',templet: function(d){
                var scoretype = d.scoretype;
                if(scoretype=="1")
                {
                    return "分享";
                }
                if(scoretype=="2")
                {
                    return "购买";
                }
                return "其他";
            }}
            , {field: 'scoretime', title: '时间',templet: function(d){

            return comjs.getTime(d.scoretime);}}
        ]]
        , loading: true
        , where: vwhere
        , done: function (res, curr, count) {
        }
        , id: 'scoretable'
        , page: true
        , height: 315
        , limit: comjs.limit
    });
    //排序
    table.on('sort(scoretable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        //尽管我们的 table 自带排序功能，但并没有请求服务端。
        //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，如：
        table.reload('testReload', {
            initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
            ,where: { //请求参数
                field: obj.field //排序字段
                ,order: obj.type //排序方式
            }
        });
    });
    var $ = layui.$, active = {
        reload: function () {
            var vwhereTemp={};
            var telphone = $('#telphone');
            if(telphone.val()!="")
            {
                vwhereTemp.telphone=telphone.val();
            }
            var scoreType = $("#scoreType");
            if(scoreType.val()!=""&&scoreType.val()!="0")
            {
                vwhereTemp.scoreType=scoreType.val();
            }

            var scoreDate = $("#scoreDate");
            if(scoreDate.val()!="")
            {
                vwhereTemp.scoreDate=scoreDate.val().replace(/-/g,"")
            }
            debugger;
            vwhereTemp=JSON.stringify(vwhereTemp);
            //执行重载
            table.reload('scoretable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: vwhereTemp
            });
        }
    };

    form.on('submit(btnadminsearch)', function (data) {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        return false;
    });
    form.on('submit(addadmin)',function () {
        var index = layui.layer.open({
            title : "新增管理员",
            type : 2,
            area:['500px','400px'],
            content : "add.html",
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('新增管理员', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    });
    form.on('submit(setadmin)',function () {
        var checkStatus = table.checkStatus('scoretable')
            ,data = checkStatus.data;


        if(data.length>1) {
            layer.msg('只能选择一个');
            return;
        }
        else if(data.length==0){
            layer.msg('必须选择一个');
            return;
        }
        var vID=data[0].adminid;
        var index = layui.layer.open({
            title : "修改管理员",
            type : 2,
            area:['500px','400px'],
            content : "set.html?id="+vID,
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('修改管理员', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    });

});