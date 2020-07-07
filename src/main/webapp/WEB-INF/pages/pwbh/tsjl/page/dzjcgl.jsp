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
            elem: '#dzjcgl'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/pwbh_jl_dzjc/selectByAll?tsid=' + tsid //数据接口      改
            , toolbar: true
            , defaultToolbar: [
                {
                    title: '添加' //标题
                    , layEvent: 'ADD' //事件名，用于 toolbar 事件中使用
                    , icon: 'layui-icon-addition'
                }
                , {
                    title: '提交' //标题
                    , layEvent: 'SUBMIT' //事件名，用于 toolbar 事件中使用
                    , icon: 'layui-icon-ok'
                }
            ]
            , cols: [[ //表头
                {field: 'jg', title: '相别', width: '10%', align: 'center', fixed: 'left', sort: true, rowspan: 2}
                , {field: 'g1', title: '过流I段整定值', width: '11%', align: 'center', edit: 'text', rowspan: 2}
                , {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
                , {field: 'g2', title: '过流II段整定值', width: '11%', align: 'center', edit: 'text', rowspan: 2}
                , {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
                , {title: '操作', width: '8%', align: 'center', toolbar: '#barDemo', rowspan: 2}
            ],
                [
                    {field: 'g11', title: '不动作(A)', width: '15%', align: 'center', edit: 'text'}
                    , {field: 'g12', title: '动作(A)', width: '15%', align: 'center', edit: 'text'}
                    , {field: 'g21', title: '不动作(A)', width: '15%', align: 'center', edit: 'text'}
                    , {field: 'g22', title: '动作(A)', width: '15%', align: 'center', edit: 'text'}

                ]]
            , done: function (res) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    editbz();
                    // 自动保存X dz      ***
                    submitDz();
                    if (record.length === 0) {
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/pwbh_jl_dzjc/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("dzjcgl automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $.each(resdata, function (i, value) {
                    if (value.g1 == null || value.g1 === ""
                    || value.g11 == null || value.g11 === ""
                    || value.g12 == null || value.g12 === ""
                    || value.g2 == null || value.g2 === ""
                    || value.g12 == null || value.g12 === ""
                    || value.g22 == null || value.g22 === "") {
                        dzjcgl = false;
                        bgcolor(dzjcgl, dzjclx);
                        return;
                    }
                    dzjcgl = true;
                    bgcolor(dzjcgl, dzjclx);
                })
            }
        });

        // 编辑
        table.on('edit(dzjcgl)', function (obj) {         // 改
            let data = obj.data;
            $.each(record, function (i) {
                if (record[i].id === data.id) {
                    record[i].g1 = data.g1;
                    record[i].g11 = data.g11;
                    record[i].g12 = data.g12;
                    record[i].g2 = data.g2;
                    record[i].g21 = data.g21;
                    record[i].g22 = data.g22;
                }
            });
        });

        // 提交记录(删除前)
        function submitJl(record) {
            submitDz();
            // 表格没有数据
            if (record.length === 0) {
                return true;
            }
            let data = null;
            $.ajax({
                type: "POST",
                async: false,
                url: "${basePath}/pwbh_jl_dzjc/updateBatch",              // 改
                data: JSON.stringify(record),//必须
                contentType: "application/json;charsetset=UTF-8",//必须
                dataType: "json",//必须
                success: function (result) {
                    clearTimeout(autosave);
                    tableReload.reload();
                    record = [];
                    data = result;
                }
            });
            return data.code === 0;
        }

        //监听事件
        table.on('toolbar(dzjcgl)', function (obj) {      // 改
            switch (obj.event) {
                // 添加
                case 'ADD':
                    if (${requestScope.userType != 0}) {
                        layer.msg("权限不足！", {time: 1500, icon: 4});
                        return;
                    }
                    if (submitJl(record)) {
                        layer.open({
                            type: 2,
                            title: '添加 --> 定值检查',
                            maxmin: true,
                            // shadeClose: true, //点击遮罩关闭层
                            area: ['800px', '520px'],
                            content: '${basePath}/pwbh/tsjl/add/addDzjc/' + tsid + '/' + ssqy
                            , end: function () {
                                clearTimeout(autosave);
                                tableReload.reload();
                            }
                        });
                    } else {
                        layer.msg("添加前提交失败，请刷新重试！", {time: 2000, icon: 5});
                    }
                    break;
                // 提交
                case 'SUBMIT':
                    // 修改备注
                    editbz();
                    submitDz();
                    // if (record.length === 0) {
                    //     layer.msg("无数据提交", {time: 1000, icon: 3});
                    //     return;
                    // }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/pwbh_jl_dzjc/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
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

        //监听行操作
        table.on('tool(dzjcgl)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    // 删除前提交
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/pwbh_jl_dzjc/deleteByPrimaryKey/" + that.id, function (result) {
                            clearTimeout(autosave);
                            tableReload.reload();
                            result = JSON.parse(result);
                            if (result.code === 0) {
                                layer.msg(result.msg, {time: 1500, icon: 1});
                            } else {
                                layer.msg(result.msg, {time: 2000, icon: 5});
                            }
                        });
                    } else {
                        layer.msg("删除前提交失败，请刷新重试！", {time: 2000, icon: 5});
                    }
                });
            }
        });
    });
</script>
</body>
</html>
