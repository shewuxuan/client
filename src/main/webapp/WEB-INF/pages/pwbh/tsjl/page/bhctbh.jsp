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
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend id="mao10_2">9.2 保护CT变比</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <table id="bhctbh" lay-filter="bhctbh"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="bhctbhbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 5、电流、电压零漂校验（测量值在0.1%以内） --%>
<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#bhctbh'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/pwbh_jl_bhctbh/selectByAll?tsid=' + tsid //数据接口      改
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
                {field: 'id', title: 'ID', type: 'numbers', width: '5%', fixed: 'left', sort: true}
                , {field: 'xb', title: '相别', width: '12%', align: 'center', sort: true}
                , {field: 'xh', title: '型号', width: '15%', align: 'center', edit: 'text'}
                , {field: 'xqh', title: '线圈号', width: '15%', align: 'center', edit: 'text'}
                , {field: 'cs', title: '参数', width: '15%', align: 'center', edit: 'text'}
                , {field: 'mybh', title: '名义变比', width: '15%', align: 'center', edit: 'text'}
                , {field: 'sybh', title: '实验变比', width: '15%', align: 'center', edit: 'text'}
                , {title: '操作', width: '8%', align: 'center', toolbar: '#barDemo'}
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
                        url: "${basePath}/pwbh_jl_bhctbh/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("bhctbh automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $.each(resdata, function (i, value) {
                    if (value.xh == null || value.xh === ""
                    || value.xqh == null || value.xqh === ""
                    || value.cs == null || value.cs === ""
                    || value.mybh == null || value.mybh === ""
                    || value.sybh == null || value.sybh === "") {
                        $("#dd_bhctbh").css({"background-color": ""});
                        return false;
                    }
                    $("#dd_bhctbh").css({"background-color": "#009688"});
                })
            }
        });

        // 编辑
        table.on('edit(bhctbh)', function (obj) {         // 改
            let data = obj.data;
            $.each(record, function (i, value) {
                if (value.id === data.id) {
                    value.xh = data.xh;
                    value.cs = data.cs;
                    value.mybh = data.mybh;
                    value.sybh = data.sybh;
                }
            });
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                pwbhJlBhctbh: $("#bhctbhbeizhu").val()
            };
            $.ajax({
                type: "POST",
                url: "${basePath}/pwbh_beizhu/updateByPrimaryKey",
                data: JSON.stringify(beizhu),//必须
                contentType: "application/json;charsetset=UTF-8",//必须
                dataType: "json",//必须
                success: function (data) {
                }
            });
        }

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
                url: "${basePath}/pwbh_jl_bhctbh/updateBatch",              // 改
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
        table.on('toolbar(bhctbh)', function (obj) {      // 改
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
                            title: '添加 --> 保护CT变比',
                            maxmin: true,
                            // shadeClose: true, //点击遮罩关闭层
                            area: ['800px', '520px'],
                            content: '${basePath}/pwbh/tsjl/add/addBhctbh/' + tsid + '/' + ssqy
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
                    if (record.length === 0) {
                        layer.msg("无数据提交", {time: 1000, icon: 3});
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/pwbh_jl_bhctbh/updateBatch",              // 改
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
        table.on('tool(bhctbh)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    // 删除前提交
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/pwbh_jl_bhctbh/deleteByPrimaryKey/" + that.id, function (result) {
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
