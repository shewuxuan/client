<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/2/29
  Time: 10:49 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend id="mao3_2">3.2 CT回路电阻测试（测量值应不大于0.2Ω）</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <h4>测量步骤：</h4>
        <h4>（1）测试前，断开开关柜二次柜内的CT连接片，分别在端子排上口（一般为装置侧）和下口（一般为CT侧）进行测试。</h4>
        <h4>（2）测试后，及时恢复CT连接片，防止开路。</h4>
        <table id="hldz" lay-filter="hldz" style="width: 70%; margin: auto;"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="hldzbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 3.2 CT回路电阻测试（测量值应不大于0.2Ω） --%>
<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#hldz'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/jlhldz/selectByAll?tsid=' + tsid //数据接口      改
            , toolbar: true
            , defaultToolbar: [
                {
                    title: '删除' //标题
                    , layEvent: 'DEL' //事件名，用于 toolbar 事件中使用
                    , icon: 'layui-icon-delete'
                }
                , {
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
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', type: 'numbers', width: '5%', fixed: 'left', sort: true}
                , {field: 'jg', title: '间隔', width: '14%', align: 'center', sort: true}
                , {field: 'hl', title: '回路', width: '17%', align: 'center', sort: true}
                , {field: 'zzc', title: '装置侧', width: '25%', edit: 'text', align: 'center'}
                , {field: 'ctc', title: 'CT侧', width: '25%', edit: 'text', align: 'center'}
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
                        url: "${basePath}/jlhldz/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("hldz automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                // 设置导航条背景色
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].zzc == null || resdata[i].zzc === ""
                        || resdata[i].ctc == null || resdata[i].ctc === ""
                    ) {
                        $("#dd_hldz").css({"background-color": ""});
                        hldz = false;
                        hlcsflag(jydz, hldz);
                        return;
                    }
                    hldz = true;
                    hlcsflag(jydz, hldz);
                    $("#dd_hldz").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(hldz)', function (obj) {         // 改
            let data = obj.data;
            $.each(record, function (i) {
                if (record[i].id === data.id) {
                    record[i].zzc = data.zzc;
                    record[i].ctc = data.ctc;
                }
            });
        });

        //监听事件
        table.on('toolbar(hldz)', function (obj) {      // 改
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
                            title: '添加 --> CT回路电阻测试',
                            maxmin: true,
                            // shadeClose: true, //点击遮罩关闭层
                            area: ['800px', '520px'],
                            content: '${basePath}/jl/add/addHldz/' + tsid + '/' + ssqy
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
                    // 提交数据
                    if (record.length === 0) {
                        layer.msg("无数据提交", {time: 1000, icon: 3});
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/jlhldz/updateBatch",              // 改
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
                                layer.msg(data.msg, {time: 2000, icon: 5});
                            }
                        }
                    });
                    break;
                case 'DEL':
                    if (${requestScope.userType != 0}) {
                        layer.msg("权限不足！", {time: 1500, icon: 4});
                        return;
                    }
                    let checkStatus = table.checkStatus('hldz')
                        , data = checkStatus.data
                        , ids = [];
                    $.each(data, function (i) {
                        ids.push(data[i].id);
                    });
                    if (ids.length === 0) {
                        layer.msg('未选中要删除的行',function () {});
                        return;
                    }
                    layer.confirm('真的删除吗？', function () {
                        if (submitJl(record)) {
                            // 删除
                            $.ajax({
                                type: "POST",
                                url: "${basePath}/jlhldz/batchDeleteByPrimaryKey",              // 改
                                data: JSON.stringify(ids),//必须
                                contentType: "application/json;charsetset=UTF-8",//必须
                                dataType: "json",//必须
                                success: function (result) {
                                    if (result.code === 0) {
                                        layer.msg(result.msg, {time: 1500, icon: 1});
                                    } else {
                                        layer.msg(result.msg, {time: 2000, icon: 5});
                                    }
                                    // 清除定时器
                                    clearTimeout(autosave);
                                    // 刷新表格
                                    tableReload.reload();
                                }
                            });
                        } else {
                            layer.msg("删除前提交失败，请刷新重试！", {time: 2000, icon: 5});
                        }
                    });
                    break;
            }
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlHldz: $("#hldzbeizhu").val()
            };
            $.ajax({
                type: "POST",
                url: "${basePath}/beizhu/updateByPrimaryKey",
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
                url: "${basePath}/jlhldz/updateBatch",              // 改
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

        //监听行操作
        table.on('tool(hldz)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/jlhldz/deleteByPrimaryKey/" + that.id, function (result) {
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
