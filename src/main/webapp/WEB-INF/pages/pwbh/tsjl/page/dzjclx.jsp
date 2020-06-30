<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/6/17
  Time: 10:51 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#dzjclx'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/pwbh_dz/selectByAll?tsid=' + tsid //数据接口      改
            , toolbar: true
            , defaultToolbar: [{
                    title: '提交' //标题
                    , layEvent: 'SUBMIT' //事件名，用于 toolbar 事件中使用
                    , icon: 'layui-icon-ok'
                }
            ]
            , cols: [[ //表头
                {title: '相别', width: '10%', align: 'center', fixed: 'left', rowspan: 2, templet: function () {
                    return 'L-0';
                    }}
                , {field: 'lx1', title: '零序I段整定值', width: '13%', align: 'center', edit: 'text', rowspan: 2}
                , {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
                , {field: 'lx2', title: '零序II段整定值', width: '13%', align: 'center', edit: 'text', rowspan: 2}
                , {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
            ],
                [
                    {field: 'lx11', title: '不动作(A)', width: '16%', align: 'center', edit: 'text'}
                    , {field: 'lx12', title: '动作(A)', width: '16%', align: 'center', edit: 'text'}
                    , {field: 'lx21', title: '不动作(A)', width: '16%', align: 'center', edit: 'text'}
                    , {field: 'lx22', title: '动作(A)', width: '16%', align: 'center', edit: 'text'}

                ]]
            , done: function (res) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    editbz();
                    submitDz();
                    if (record.length === 0) {
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/pwbh_dz/updateByPrimaryKey",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("dzjclx automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $.each(resdata, function (i) {
                    if (resdata[i].jcjg == null || resdata[i].jcjg === "" || resdata[i].jcjg == -1) {
                        dzjclx = false;
                        bgcolor(dzjcgl, dzjclx);
                        // $("#li_dzjc").css({"background-color": ""});
                        return;
                    }
                    dzjclx = true;
                    bgcolor(dzjcgl, dzjclx);
                    // $("#li_dzjc").css({"background-color": "#009688"});
                })
            }
        });

        // 编辑
        table.on('edit(dzjclx)', function (obj) {         // 改
            let data = obj.data;
            $.each(record, function (i, value) {
                if (value.tsid === data.tsid) {
                    value.lx1 = data.lx1;
                    value.lx11 = data.lx11;
                    value.lx12 = data.lx12;
                    value.lx2 = data.lx2;
                    value.lx21 = data.lx21;
                    value.lx22 = data.lx22;
                }
            });
            console.log(record)
        });

        //监听事件
        table.on('toolbar(dzjclx)', function (obj) {      // 改
            switch (obj.event) {
                // 提交
                case 'SUBMIT':
                    // 修改备注
                    editbz();
                    submitDz();
                    if (record.length === 0) {
                        layer.msg("无数据提交", {time: 1000, icon: 3});
                        return;
                    }
                    console.log(record)
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/pwbh_dz/updateByPrimaryKey",              // 改
                        data: JSON.stringify(record[0]),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code === 0) {
                                clearTimeout(autosave);
                                tableReload.reload();
                                record = [];
                                layer.msg(data.msg, {time: 1000, icon: 6});
                            } else {
                                layer.msg(data.msg, {time: 2000, icon: 5})
                            }
                        }
                    });
                    break;
            }
        });
    });
</script>
</body>
</html>
