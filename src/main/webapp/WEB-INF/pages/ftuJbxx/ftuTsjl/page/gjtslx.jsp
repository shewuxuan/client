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
        let record = null;
        let autosave = null;
        let tableReload = table.render({
            elem: '#gjtslx'                           // 改
            , page: false
            , size: 'sm'
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/ftu_jl_gjdz/selectByAll?id=' + tsid //数据接口      改
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
                // 取出表格所需列(去掉无用列)
                record = {
                    id: res.data[0].id,
                    lx1: res.data[0].lx1,
                    lx11: res.data[0].lx11,
                    lx12: res.data[0].lx12,
                    lx2: res.data[0].lx2,
                    lx21: res.data[0].lx21,
                    lx22: res.data[0].lx22
                }
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_gjdz/updateByPrimaryKey",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("gjtslx automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                if (record.lx1 == null || record.lx1 === ""
                || record.lx11 == null || record.lx11 === ""
                || record.lx12 == null || record.lx12 === ""
                || record.lx2 == null || record.lx2 === ""
                || record.lx21 == null || record.lx21 === ""
                || record.lx22 == null || record.lx22 === ""){
                    gjtslx = false;
                    bgcolor(gjtsgl, gjtslx, tzyb);
                    return;
                }
                gjtslx = true;
                bgcolor(gjtsgl, gjtslx, tzyb);
            }
        });

        // 编辑
        table.on('edit(gjtslx)', function (obj) {         // 改
            let data = obj.data;
            record = {
                "id": tsid,
                "lx22": data.lx22,
                "lx21": data.lx21,
                "lx2": data.lx2,
                "lx12": data.lx12,
                "lx11": data.lx11,
                "lx1": data.lx1
            }
        });

        //监听事件
        table.on('toolbar(gjtslx)', function (obj) {      // 改
            switch (obj.event) {
                // 提交
                case 'SUBMIT':
                    // 修改备注
                    editbz();
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_gjdz/updateByPrimaryKey",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code === 0) {
                                clearTimeout(autosave);
                                tableReload.reload();
                                record = null;
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
