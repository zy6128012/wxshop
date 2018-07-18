layui.use(['table','form','jquery','laydate'], function() {
    var table = layui.table
        , $ = layui.jquery
        , form = layui.form
        ,laydate = layui.laydate;
    var vwhere = {};

    function getdata() {

    }
    table.on('tool(newstable)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            window.open("../edit.html?type=detail&id="+data.goodsid );
            //layer.msg('ID：'+ data.goodsid + ' 的查看操作');
        } else if(obj.event === 'photo'){
            window.open("../edit.html?type=photo&id="+data.goodsid );
        }
    });

    //方法级渲染
    table.render({
        elem: '#newstableinfo'
        , url: '/news/getNews'
        , method: 'post'
        , response: comjs.vtablereq
        , cols: [[
            {checkbox: true, fixed: true}
            , {field: 'newid', title: '编号',width:60}
            , {field: 'newtitle', title: '标题'}
            , {field: 'newtime', title: '时间',templet: function(d){return comjs.getTime(d.newtime);}}
        ]]
        , loading: true
        , where: vwhere
        , height: 300 //高度最大化减去差值
        , done: function (res, curr, count) {
        }
        , id: 'newsReload'
        , page: true
        , limit: comjs.limit
    });

    //排序
    table.on('sort(newstable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        //尽管我们的 table 自带排序功能，但并没有请求服务端。
        //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，如：
        table.reload('newsReload', {
            initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
            ,where: { //请求参数

            }
        });
    });
    var $ = layui.$, active = {
        reload: function () {

            //执行重载
            table.reload('newsReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {

                }
            });
        }
    };

    form.on('submit(addgoods)',function () {
        window.open("../edit.html?type=news");
    });
    form.on('submit(setgoods)',function () {
        var checkStatus = table.checkStatus('newsReload')
            ,data = checkStatus.data;
        if(data.length>1) {
            layer.msg('只能选择一个');
            return;
        }
        else if(data.length==0){
            layer.msg('必须选择一个');
            return;
        }
        var vID=data[0].goodsid;
        var index = layui.layer.open({
            title : "修改商品",
            type : 2,
            area:['700px','510px'],
            content : "set.html?id="+vID,
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('修改商品', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    });
    form.on('submit(delgoods)',function () {
        var checkStatus = table.checkStatus('newsReload')
            ,data = checkStatus.data;
        if(data.length==0){
            layer.msg('必须选择一个');
            return;
        }
        var index = layer.confirm();
        layer.confirm('确定删除['+data.length+']条数据？', {
            btn : [ '确定', '取消' ]//按钮
            ,btn1: function(index) {
                for(var i=0;i<data.length;i++)
                {
                    var temp=data[i];
                    $.ajax({
                        type: 'GET'
                        , url: '/news/delNews?id='+temp.newid
                        , dataType: 'json'
                        , contentType: "application/json"
                        , success: function (res) {

                        }
                    });

                }
                layer.msg('删除成功');
                layer.close(index);
                table.reload('newsReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },btn2:function(){
                layer.close(index);
            }
        });

    });
});