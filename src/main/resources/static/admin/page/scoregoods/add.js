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


	//创建一个编辑器
 	var editIndex = layedit.build('news_content');
 	var addNewsArray = [],addNews;
 	form.on("submit(addgoods)",function(data){
 		debugger;
		var vPrice=parseInt($("#buyprice").val()*100);
		var vbuydate=$("#buydate").val();
		var vbuydateno=vbuydate.replace(/-/g,"");
		var vgoodsprop=comjs.getcheckvalue($("input:checkbox[name='goodsprop']"));
        var vpapersize=comjs.getcheckvalue($("input:radio[name='papersize']"));
        var vgoodsstatue=comjs.getcheckvalue($("input:checkbox[name='goodsstatue']"));
        var vbuytype=comjs.getcheckvalue($("input:radio[name='buytype']"));
 		var vParar={
 			goodsname:$("#goodsname").val(),
            buydate:vbuydateno,
            suppcompany:$("#suppcompany").val(),
            model:$("#model").val(),
            buyprice:vPrice,
            goodsprop:vgoodsprop,
            buytype:vbuytype,
            usetype:2,
            papersize:vpapersize,
            goodsstock:$("#goodsstock").val(),
            goodsstatue:vgoodsstatue
		};
        var vindex=0;
        $.ajax({
            type: 'POST'
            , url: '/goods/addGoods'
            , data:JSON.stringify(vParar)
            , dataType: 'json'
            , contentType: "application/json"
            , beforeSend: function () {
                vindex=top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            }
            , success: function (res) {
                if (res.nStatus == 0) {
                    top.layer.close(vindex);
                    top.layer.msg("商品添加成功！");
                    layer.closeAll("iframe");
                    parent.location.reload();
                }
                else {
                    top.layer.msg("添加失败：" + res.szError);
                    return false;
                }
            }
            , error: function (e, m) {

            }
        });

 	})
	
})
