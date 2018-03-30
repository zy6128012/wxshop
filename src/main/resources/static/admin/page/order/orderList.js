layui.use('table', function(){
    var table = layui.table;

    //方法级渲染
    /*
    table.render({
        elem: '#tableadd'
        ,url: '../../json/newslist.json'
        ,cols: [[
            {checkbox: true, fixed: true}
            ,{field:'newsId', title: 'ID', width:80, sort: true, fixed: true}
            ,{field:'newsName', title: '用户名', width:80}
            ,{field:'newsAuthor', title: '性别', width:80, sort: true}
            ,{field:'newsStatus', title: '城市', width:80}
            ,{field:'newsLook', title: '签名'}
            ,{field:'isShow', title: '积分', sort: true, width:80}
            ,{field:'newsTime', title: '评分', sort: true, width:80}
        ]]
        ,id: 'tableadd'
        ,page: true
        ,height: 'full-200'
    });
*/
    var $ = layui.$, active = {
        reload: function(){
            var tableadd = $('#tableadd');

            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    key: {
                        id: tableadd.val()
                    }
                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});