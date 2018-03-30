layui.config({
	base : "js/"
}).use(['form','layer'],function() {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    //video背景
    $(window).resize(function () {
        if ($(".video-player").width() > $(window).width()) {
            $(".video-player").css({
                "height": $(window).height(),
                "width": "auto",
                "left": -($(".video-player").width() - $(window).width()) / 2
            });
        } else {
            $(".video-player").css({
                "width": $(window).width(),
                "height": "auto",
                "left": -($(".video-player").width() - $(window).width()) / 2
            });
        }
    }).resize();

    //登录按钮事件
    form.on("submit(login)", function (data) {
        var vLoginInfo = data.field;
        vLoginInfo.password=hex_md5(vLoginInfo.password);
        vLoginInfo = JSON.stringify(vLoginInfo);
        $.ajax({
            type: "post",
            contentType: 'application/json',
            url: "/admin/adminLogin",
            async: false,
            dataType: 'json',
            data: vLoginInfo,
            success: function (res) {
                if (res.nStatus == 0) {
                    window.location.href = "../../index.html";
                }
                else {
                    layer.alert(res.szError);
                }
            }
        });

        return false;
    })
})
