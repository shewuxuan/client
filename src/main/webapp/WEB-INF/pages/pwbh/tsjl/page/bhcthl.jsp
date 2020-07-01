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
    <legend id="mao12">12、保护CT回路接触电阻：（误差不得超过10％）</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <table id="bhcthl" lay-filter="bhcthl"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="bhcthlbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#bhcthl'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/pwbh_jl_bhcthl/selectByAll?tsid=' + tsid //数据接口      改
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
                , {field: 'xb', title: '相别', width: '28%', align: 'center', sort: true}
                , {field: 'bhc', title: '保护侧', width: '28%', align: 'center', edit: 'text'}
                , {field: 'ctc', title: 'CT侧', width: '28%', align: 'center', edit: 'text'}
                , {title: '操作', width: '11%', align: 'center', toolbar: '#barDemo'}
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
                        url: "${basePath}/pwbh_jl_bhcthl/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("bhcthl automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $.each(resdata, function (i, value) {
                    if (value.bhc == null || value.bhc === ""
                    || value.ctc == null || value.ctc === "") {
                        $("#li_bhcthl").css({"background-color": ""});
                        return false;
                    }
                    $("#li_bhcthl").css({"background-color": "#009688"});
                })
            }
        });

        // 编辑
        table.on('edit(bhcthl)', function (obj) {         // 改
            let data = obj.data;
            $.each(record, function (i, value) {
                if (value.id === data.id) {
                    value.bhc = data.bhc;
                    value.ctc = data.ctc;
                }
            });
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                pwbhJlBhcthl: $("#bhcthlbeizhu").val()
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
                url: "${basePath}/pwbh_jl_bhcthl/updateBatch",              // 改
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
        table.on('toolbar(bhcthl)', function (obj) {      // 改
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
                            title: '添加 --> 保护CT回路接触电阻',
                            maxmin: true,
                            // shadeClose: true, //点击遮罩关闭层
                            area: ['800px', '520px'],
                            content: '${basePath}/pwbh/tsjl/add/addBhcthl/' + tsid + '/' + ssqy
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
                        url: "${basePath}/pwbh_jl_bhcthl/updateBatch",              // 改
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
        table.on('tool(bhcthl)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    // 删除前提交
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/pwbh_jl_bhcthl/deleteByPrimaryKey/" + that.id, function (result) {
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
