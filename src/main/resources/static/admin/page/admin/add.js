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
    var editIndex = layedit.build('_content');
    var addNewsArray = [],addNews;
    form.on("submit(addadmin)",function(data){
        var vParar={
            logonname:$("#logonname").val(),
            truename:$("#truename").val(),
            manrole:$("#manrole").val(),
            password:hex_md5($("#logonname").val())
        };
        var vindex=0;
        $.ajax({
            type: 'POST'
            , url: '/admin/addAdmin'
            , data:JSON.stringify(vParar)
            , dataType: 'json'
            , contentType: "application/json"
            , beforeSend: function () {
                vindex=top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            }
            , success: function (res) {
                if (res.nStatus == 0) {
                    top.layer.close(vindex);
                    top.layer.msg("管理员添加成功！");
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
