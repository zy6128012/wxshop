layui.use(['table','form','jquery','laydate'], function() {
    var table = layui.table
        , $ = layui.jquery
        , form = layui.form
        ,laydate = layui.laydate;
    var vwhere = {};

    function getdata() {

    }
    table.on('tool(admintable)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            window.open("../edit.html?type=detail&id="+data.goodsid );
            //layer.msg('ID：'+ data.goodsid + ' 的查看操作');
        } else if(obj.event === 'photo'){
            window.open("../edit.html?type=photo&id="+data.goodsid );
        }
    });

    laydate.render({
        elem: '#beginDate'
    });
    laydate.render({
        elem: '#endDate'
    });
    //方法级渲染
    table.render({
        elem: '#goodstableinfo'
        , url: '/goods/getGoods'
        , method: 'post'
        , response: comjs.vtablereq
        , cols: [[
            {checkbox: true, fixed: true}
            , {field: 'goodsid', title: '编号',width:60}
            , {field: 'goodsname', title: '名称'}
            , {field: 'suppcompany', title: '品牌', sort: true}
            , {field: 'buytype', title: '购买方式', sort: true,templet: function(d){return comjs.buytype(d.buytype);}}
            , {field: 'buyprice', title: '价格(元)', sort: true,templet: function(d){return comjs.price(d.buyprice);}}
            , {field: 'papersize', title: '支持纸张', sort: true,templet: function(d){return comjs.papersize(d.papersize);}}
            , {field: 'goodsstock', title: '库存数', sort: true}
            , {field: 'buydate', title: '购买日期', sort: true,templet: function(d){return comjs.getdate(d.buydate);}}
            , {field: 'goodsstatue', title: '商品状态', sort: true,templet: function(d){return comjs.goodsstatue(d.goodsstatue);}}
            , {field: 'goodsprop', title: '其它', sort: true,templet: function(d){return comjs.devprop(d.goodsprop);}}
            , {field: '', title: '操作', toolbar: '#barDemo',width:130}
        ]]
        , loading: true
        , where: vwhere
        , height: 300 //高度最大化减去差值
        , done: function (res, curr, count) {
        }
        , id: 'goodsReload'
        , page: true
        , limit: comjs.limit
    });

    //排序
    table.on('sort(admintable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        //尽管我们的 table 自带排序功能，但并没有请求服务端。
        //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，如：
        table.reload('goodsReload', {
            initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
            ,where: { //请求参数
                field: obj.field //排序字段
                ,order: obj.type //排序方式
            }
        });
    });
    var $ = layui.$, active = {
        reload: function () {
            var goodsName = $('#goodsName').val();
            var suppcompany = $("#suppcompany").val();
            var beginDate =0; //$('#beginDate').val().replace(/-/g,'');
            var endDate =0; //$("#endDate").val().replace(/-/g,'');
            var minPrice =parseInt($('#minPrice').val())*100;
            var maxPrice = parseInt($("#maxPrice").val())*100;
            var vdevprop=comjs.getcheckvalue(($("input:checkbox[name='goodsprop']")));
            //执行重载
            table.reload('goodsReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    goodsName:goodsName,
                    suppcompany:suppcompany,
                    beginBuyDate:beginDate,
                    endBuyDate:endDate,
                    minbuyprice:minPrice,
                    maxbuyprice:maxPrice,
                    goodsProp:vdevprop
                }
            });
        }
    };
    form.on('submit(btngoodsearch)', function (data) {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        return false;
    });
    form.on('submit(addgoods)',function () {
        var index = layui.layer.open({
            title : "新增商品",
            type : 2,
            area:['700px','510px'],
            content : "add.html",
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('新增商品', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    });
    form.on('submit(setgoods)',function () {
        var checkStatus = table.checkStatus('goodsReload')
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
        var checkStatus = table.checkStatus('goodsReload')
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
                    type: 'POST'
                    , url: '/goods/delGoods?goodsID='+temp.goodsid
                    , dataType: 'json'
                    , contentType: "application/json"
                    , success: function (res) {

                    }
                });

            }
                layer.msg('删除成功');
            layer.close(index);
            table.reload('goodsReload', {
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