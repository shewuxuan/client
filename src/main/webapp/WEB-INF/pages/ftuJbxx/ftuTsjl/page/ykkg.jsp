<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/2/29
  Time: 10:57 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend id="mao10">10、带开关遥控</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <h4>开关与控制器航空线已连接。</h4>
        <table id="ykkg" lay-filter="ykkg"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="ykkgbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 10、带开关遥控 --%>
<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#ykkg'                           // 改
            , page: false
            , url: '${basePath}/ftu_jl_ykkg/selectByAll?tsid=' + tsid //数据接口      改
            , skin: 'line,row' //行边框风格
            , even: false //开启隔行背景
            , toolbar: true
            , defaultToolbar: [
                {
                    title: '提交' //标题
                    , layEvent: 'SUBMIT' //事件名，用于 toolbar 事件中使用
                    , icon: 'layui-icon-ok'
                }
            ]
            , cols: [[ //表头
                {field: 'ykf', title: '遥控分', width: '50%', align: 'center', templet: '#ykfjg'}
                , {field: 'ykh', title: '遥控合', width: '50%', align: 'center', templet: '#ykhjg'}
            ]]
            , done: function (res, curr, count) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_ykkg/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code === 0) {
                                console.log("ykkg automatically saved successfully!");
                            } else {
                                console.log("ykkg automatically saved failed!");
                            }
                        }
                    });
                    editbz();
                }, 30000);
                record = resdata;
                $("[name='select4ykf']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].ykf = $(this).val();
                        }
                    });
                });
                $("[name='select4ykh']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].ykh = $(this).val();
                        }
                    });
                });
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].ykf == null || resdata[i].ykf === "" || resdata[i].ykf == -1
                        || resdata[i].ykh == null || resdata[i].ykh === "" || resdata[i].ykh == -1
                    ) {
                        $("#li_ykkg").css({"background-color": ""});
                        return;
                    }
                    $("#li_ykkg").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(ykkg)', function (obj) {         // 改
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlYkkg: $("#ykkgbeizhu").val()
            };
            $.ajax({
                type: "POST",
                url: "${basePath}/beizhu/updateFtuBeizhuByPrimaryKey",
                data: JSON.stringify(beizhu),//必须
                contentType: "application/json;charsetset=UTF-8",//必须
                dataType: "json",//必须
                success: function (data) {
                }
            });
        }

//监听事件
        table.on('toolbar(ykkg)', function (obj) {      // 改
            switch (obj.event) {
                // 刷新
                case 'REFRESH':
                    tableReload.reload();
                    // 刷新备注
                    $.get("${basePath}/beizhu/selectFtuBeizhuByPrimaryKey/" + tsid, function (data) {
                        $("#ykkgbeizhu").val($.parseJSON(data).jlYkkg);
                    });
                    layer.msg('刷新完成', {time: 1000, icon: 6});
                    break;
                // 提交
                case 'SUBMIT':
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_ykkg/updateBatch",              // 改
                        data: JSON.stringify(record.slice(-1)),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code === 0) {
                                tableReload.reload();
                                record = [];
                                clearTimeout(autosave);
                                layer.msg(data.msg, {
                                    offset: 't',
                                    time: 1000,
                                    icon: 6
                                });
                            } else {
                                layer.msg(data.msg, {time: 2000, icon: 5})
                            }
                        }
                    });

                    // 修改备注
                    editbz();
                    break;
            }
        });
    });
</script>
<%-- 结果状态列(正常/异常)--%>
<script type="text/html" id="ykfjg">
    <select name='select4ykf' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.ykf== -1 ?
        'selected' : '' }}></option>
        <option value="1" {{ d.ykf== 1 ?
        'selected' : '' }}>正确</option>
        <option value="0" {{ d.ykf== 0 ?
        'selected' : '' }}>错误</option>
    </select>
</script>
<script type="text/html" id="ykhjg">
    <select name='select4ykh' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.ykh== -1 ?
        'selected' : '' }}></option>
        <option value="1" {{ d.ykh== 1 ?
        'selected' : '' }}>正确</option>
        <option value="0" {{ d.ykh== 0 ?
        'selected' : '' }}>错误</option>
    </select>
</script>
</body>
</html>
