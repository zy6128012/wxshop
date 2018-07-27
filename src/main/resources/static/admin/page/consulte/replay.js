layui.config({
    base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    //创建一个编辑器
    var editIndex = layedit.build('news_content');
    var addNewsArray = [],addNews;
    var vID=comjs.getUrlParam("id");
    form.on("submit(setbtn)",function(data){
        var vParar= {
            conID: vID,
            repalyContent: $("#refContent").val()
        };
        var vindex=0;
        $.ajax({
            type: 'POST'
            , url: '/consulte/replay'
            , data:JSON.stringify(vParar)
            , dataType: 'json'
            , contentType: "application/json"
            , beforeSend: function () {
                vindex=top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            }
            , success: function (res) {
                if (res.nStatus == 0) {
                    top.layer.close(vindex);
                    top.layer.msg("回复成功！");
                    layer.closeAll("iframe");
                    parent.location.reload();
                }
                else {
                    top.layer.msg("回复失败：" + res.szError);
                    return false;
                }
            }
            , error: function (e, m) {

            }
        });

    })

})
