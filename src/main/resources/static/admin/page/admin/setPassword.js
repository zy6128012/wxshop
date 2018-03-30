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
    var vAdminid=comjs.getUrlParam("id");

    form.on("submit(setpaasword)",function(data){
        var vParar={
            adminID:(vAdminid),
            password:hex_md5($("#password").val()),
            passwordConfirm:hex_md5($("#passwordConfirm").val())
        };
        if(vParar.password!=vParar.passwordConfirm)
        {
            top.layer.alert("两次密码必须一致！");
            return;
        }
        var vindex=0;
        $.ajax({
            type: 'POST'
            , url: '/admin/setPassword'
            , data:JSON.stringify(vParar)
            , dataType: 'json'
            , contentType: "application/json"
            , beforeSend: function () {
                vindex=top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            }
            , success: function (res) {
                if (res.nStatus == 0) {
                    top.layer.close(vindex);
                    top.layer.msg("密码重置成功！");
                    layer.closeAll("iframe");
                    parent.location.reload();
                }
                else {
                    top.layer.alert("密码重置失败：" + res.szError);
                    return false;
                }
            }
            , error: function (e, m) {

            }
        });

    })

})
