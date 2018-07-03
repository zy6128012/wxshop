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
        , url: '/consulte/getCon'
        , method: 'post'
        , response: comjs.vtablereq
        , cols: [[
            {checkbox: true, fixed: true}
            , {field: 'conID', title: '编号'}
            , {field: 'conTent', title: '咨询内容'}
            , {field: 'conStatue', title: '状态',templet: function(d){
                var conStatue = d.conStatue;
                if(conStatue=="1")
                {
                    return "未回复";
                }
                if(conStatue=="2")
                {
                    return "已回复";
                }
                return "其他";
            }}
            , {field: 'refContent', title: '回复内容'}
            , {field: 'conTime', title: '时间',templet: function(d){
            return comjs.getTime(d.conTime);}}
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
            var Context = $('#Context');
            if(Context.val()!="")
            {
                vwhereTemp.context=Context.val();
            }
            var conStatue = $("#conStatue");
            if(conStatue.val()!=""&&conStatue.val()!="0")
            {
                vwhereTemp.conStatue=conStatue.val();
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

    form.on('submit(replay)',function () {
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
        var vID=data[0].conID;
        var index = layui.layer.open({
            title : "回复咨询",
            type : 2,
            area:['500px','350px'],
            content : "replay.html?id="+vID,
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('回复咨询', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    });

});