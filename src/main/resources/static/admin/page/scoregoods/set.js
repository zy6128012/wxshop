layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;

    laydate.render({
        elem: '#buydate'
    });
    var vID=comjs.getUrlParam("id");

var vget={goodsID:vID};
    $.ajax({
        type: 'POST'
        , url: '/goods/getGoods'
        , data: JSON.stringify(vget)
        , dataType: 'json'
        , contentType: "application/json"
        , success: function (res) {
            if (res.nStatus == 0) {
                var vgoods = res.data[0];
                $("#goodsid").val(vgoods.goodsid);
                $("#goodsname").val(vgoods.goodsname);
                $("#buydate").val(comjs.getdate(vgoods.buydate));
                $("#suppcompany").val(vgoods.suppcompany);
                $("#model").val(vgoods.model);
                $("#buyprice").val(comjs.price(vgoods.buyprice));
                $("#goodsstock").val(vgoods.goodsstock);
                var goodsprops = ',' + comjs.valtostr(vgoods.goodsprop) + ',';

                $("input:checkbox[name='goodsprop']").each(function (index, temp) {
                    var vtempvalue = ','+temp.value+',';
                    if (goodsprops.indexOf(vtempvalue) > -1) {
                        this.checked = true;
                    }
                });
                $("input:checkbox[name='goodsstatue']").each(function (index, temp) {
                    var vtempvalue = temp.value;
                    if (vgoods.goodsstatue.toString().indexOf(vtempvalue) > -1) {
                        this.checked = true;
                    }
                });
                $("input:radio[name='papersize']").each(function (index, temp) {
                    var vtempvalue = temp.value;
                    if (vgoods.papersize.toString().indexOf(vtempvalue) > -1) {
                        this.checked = true;
                    }
                });
                $("input:radio[name='papersize']").each(function (index, temp) {

                    var vtempvalue = temp.value;
                    if (vgoods.papersize.toString().indexOf(vtempvalue) > -1) {
                        $(this).attr("checked", true);
                    }
                });
                $("input:radio[name='buytype']").each(function (index, temp) {
                    var vtempvalue = temp.value;
                    if (vgoods.buytype.toString().indexOf(vtempvalue) > -1) {
                        $(this).attr("checked", true);
                    }
                });
                form.render(); //更新全部
            }
            else {
                layui.msg(res.szError);
                return;
            }
        }
    });



	//创建一个编辑器
 	var editIndex = layedit.build('news_content');
 	var addNewsArray = [],addNews;
 	form.on("submit(setgoods)",function(data){

		var vPrice=parseInt($("#buyprice").val()*100);
		var vbuydate=$("#buydate").val();
		var vbuydateno=vbuydate.replace(/-/g,"");
		var vgoodsprop=comjs.getcheckvalue($("input:checkbox[name='goodsprop']"));
        var vpapersize=comjs.getcheckvalue($("input:radio[name='papersize']"));
        var vgoodsstatue=comjs.getcheckvalue($("input:checkbox[name='goodsstatue']"));
        var vbuytype=comjs.getcheckvalue($("input:radio[name='buytype']"));
 		var vParar={
 		    goodsid:$("#goodsid").val(),
 			goodsname:$("#goodsname").val(),
            buydate:vbuydateno,
            suppcompany:$("#suppcompany").val(),
            model:$("#model").val(),
            buyprice:vPrice,
            goodsprop:vgoodsprop,
            buytype:vbuytype,
            papersize:vpapersize,
            goodsstock:$("#goodsstock").val(),
            goodsstatue:vgoodsstatue
		};
        var vindex=0;
        $.ajax({
            type: 'POST'
            , url: '/goods/updateGoods'
            , data:JSON.stringify(vParar)
            , dataType: 'json'
            , contentType: "application/json"
            , beforeSend: function () {
                vindex=top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            }
            , success: function (res) {
                if (res.nStatus == 0) {
                    top.layer.close(vindex);
                    top.layer.msg("商品修改成功！");
                    layer.closeAll("iframe");
                    parent.location.reload();
                }
                else {
                    top.layer.msg("修改失败：" + res.szError);
                    return false;
                }
            }
            , error: function (e, m) {

            }
        });

 	})
	
})
