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
            elem: '#gjtsgl'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , size: 'sm'
            , even: false //开启隔行背景
            , url: '${basePath}/ftu_jl_gjts/selectByAll?tsid=' + tsid //数据接口      改
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
                {field: 'csx', title: '相别', width: '10%', align: 'center', fixed: 'left', sort: true, rowspan: 2}
                , {field: 'g1dz', title: '过流I段<br>整定值', width: '11%', align: 'center', edit: 'text', rowspan: 2}
                , {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
                , {field: 'g2dz', title: '过流II段<br>整定值', width: '11%', align: 'center', edit: 'text', rowspan: 2}
                , {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
                , {title: '操作', width: '8%', align: 'center', toolbar: '#barDemo', rowspan: 2}
            ],
                [
                    {field: 'g1sjtd1', title: '不动作(A)', width: '15%', align: 'center', edit: 'text'}
                    , {field: 'g1sjtd2', title: '动作(A)', width: '15%', align: 'center', edit: 'text'}
                    , {field: 'g2sjtd1', title: '不动作(A)', width: '15%', align: 'center', edit: 'text'}
                    , {field: 'g2sjtd2', title: '动作(A)', width: '15%', align: 'center', edit: 'text'}

                ]]
            , done: function (res) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    editbz();
                    if (record.length === 0) {
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_gjts/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("gjtsgl automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $.each(resdata, function (i, value) {
                    if (value.g1dz == null || value.g1dz === ""
                    || value.g1sjtd1 == null || value.g1sjtd1 === ""
                    || value.g1sjtd2 == null || value.g1sjtd2 === ""
                    || value.g2dz == null || value.g2dz === ""
                    || value.g1sjtd2 == null || value.g1sjtd2 === ""
                    || value.g2sjtd2 == null || value.g2sjtd2 === "") {
                        gjtsgl = false;
                        bgcolor(gjtsgl, gjtslx, tzyb);
                        return;
                    }
                    gjtsgl = true;
                    bgcolor(gjtsgl, gjtslx, tzyb);
                })
            }
        });

        // 编辑
        table.on('edit(gjtsgl)', function (obj) {         // 改
            let data = obj.data;
            $.each(record, function (i) {
                if (record[i].id === data.id) {
                    record[i].g1dz = data.g1dz;
                    record[i].g1sjtd1 = data.g1sjtd1;
                    record[i].g1sjtd2 = data.g1sjtd2;
                    record[i].g2dz = data.g2dz;
                    record[i].g2sjtd1 = data.g2sjtd1;
                    record[i].g2sjtd2 = data.g2sjtd2;
                }
            });
        });

        // 提交记录(删除前)
        function submitJl(record) {
            // 表格没有数据
            if (record.length === 0) {
                return true;
            }
            let data = null;
            $.ajax({
                type: "POST",
                async: false,
                url: "${basePath}/ftu_jl_gjts/updateBatch",              // 改
                data: JSON.stringify(record),//必须
                contentType: "application/json;charset=UTF-8",//必须
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
        table.on('toolbar(gjtsgl)', function (obj) {      // 改
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
                            title: '添加 --> 过流',
                            maxmin: true,
                            // shadeClose: true, //点击遮罩关闭层
                            area: ['800px', '520px'],
                            content: '${basePath}/ftuJbxx/ftuTsjl/add/addGjtsgl/' + tsid + '/' + ssqy
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
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_gjts/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charset=UTF-8",//必须
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
        table.on('tool(gjtsgl)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    // 删除前提交
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/ftu_jl_gjts/deleteByPrimaryKey/" + that.id, function (result) {
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
