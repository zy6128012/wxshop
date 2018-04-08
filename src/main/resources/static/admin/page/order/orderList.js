layui.use(['table','form','jquery'], function() {
    var table = layui.table
        , $ = layui.jquery
        , form = layui.form;
    var vwhere = {};

    function getdata() {

    }

    //方法级渲染
    table.render({
        elem: '#t_customerInfo'
        , url: '/order/getOrder'
        , method: 'post'
        , response: comjs.vtablereq
        , cols: [[
            {checkbox: true, fixed: true}
            , {field: 'orderid', title: '订单号'}
            , {
                field: 'name', title: '姓名', templet: function (d) {
                    var name = d.addressObj.conname;
                    return name;
                }
            }
            , {
                field: 'name', title: '电话', templet: function (d) {
                    var telphone = d.addressObj.telphone;
                    return telphone;
                }
            }
            , {
                field: 'totalsum', title: '总额(元)', templet: function (d) {
                    var telphone = comjs.price(d.totalsum)
                    return telphone;
                }
            }
            , {
                field: 'goodname', title: '商品(单价)', templet: function (d) {
                    var goodsObj = d.orderGoodsObjList[0].goodsObj;
                    var vRes = goodsObj.goodsname + '(' + comjs.price(goodsObj.buyprice) + ')';
                    return vRes;
                }
            }
            , {
                field: 'goodNum', title: '数量', templet: function (d) {
                    var goodsObj = d.orderGoodsObjList[0].goodsObj;
                    var vRes = d.orderGoodsObjList[0].goodsnum;
                    return vRes;
                }
            }
            , {
                field: 'orderstatus', title: '状态', templet: function (d) {
                    var orderstatus = d.orderstatus;
                    if (orderstatus == "1") {
                        vmansz = "未发货";
                    }
                    else if (orderstatus == "2") {
                        vmansz = "已发货";
                    }
                    else {
                        vmansz = "其他";
                    }
                    return vmansz;
                }
            }
        ]]
        , loading: true
        , where: vwhere
        , done: function (res, curr, count) {
        }
        , id: 'ordertable'
        , page: true
        , height: 300
        , limit: comjs.limit
    });
    form.on('submit(btnordersearch)', function (data) {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        return false;
    });
    //排序
    table.on('sort(ordertable1)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        //尽管我们的 table 自带排序功能，但并没有请求服务端。
        //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，如：
        table.reload('ordertable', {
            initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
            , where: { //请求参数
                field: obj.field //排序字段
                , order: obj.type //排序方式
            }
        });
    });
    var $ = layui.$, active = {
        reload: function () {
            var vwhere={};
            if($("#orderStatus").val()!="0")
            {
                vwhere.status=$("#orderStatus").val();
            }
            vwhere=vwhere;
            table.reload('ordertable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where:vwhere
            });
        }
    };

    form.on('submit(btnadminsearch)', function (data) {

        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        return false;
    });

    form.on('submit(statusSend)', function () {
        var checkStatus = table.checkStatus('ordertable')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.msg('必须选择一个');
            return;
        }
        var index = layer.confirm();
        layer.confirm('确定设[' + data.length + ']条数据发货状态？', {
            btn: ['确定', '取消']//按钮
            , btn1: function (index) {
                for (var i = 0; i < data.length; i++) {
                    var temp = data[i];
                    $.ajax({
                        type: 'POST'
                        , url: '/order/setSend?orderID=' + temp.orderid
                        , dataType: 'json'
                        , contentType: "application/json"
                        , success: function (res) {

                        }
                    });

                }
                layer.msg('设置成功');
                layer.close(index);
                table.reload('ordertable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            }, btn2: function () {
                layer.close(index);
            }
        });
    });

    form.on('submit(statusNoSend)', function () {
        var checkStatus = table.checkStatus('ordertable')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.msg('必须选择一个');
            return;
        }
        var index = layer.confirm();
        layer.confirm('确定设[' + data.length + ']条数据未发货状态？', {
            btn: ['确定', '取消']//按钮
            , btn1: function (index) {
                for (var i = 0; i < data.length; i++) {
                    var temp = data[i];
                    $.ajax({
                        type: 'POST'
                        , url: '/order/setNoSend?orderID=' + temp.orderid
                        , dataType: 'json'
                        , contentType: "application/json"
                        , success: function (res) {

                        }
                    });

                }
                layer.msg('设置成功');
                layer.close(index);
                table.reload('ordertable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            }, btn2: function () {
                layer.close(index);
            }
        });
    });

});