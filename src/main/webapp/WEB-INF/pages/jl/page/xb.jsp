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
<fieldset id="fieldset_xb" class="layui-elem-field layui-field-title">
    <legend id="mao10_4">10.4 箱变</legend>
</fieldset>
<div id="div_xb" class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <table id="xb" lay-filter="xb"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="xbbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 10.4 箱变 --%>
<script>
    layui.use('table', function () {
        let table = layui.table
            , form = layui.form;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#xb'                           // 改
            , page: false
            , url: '${basePath}/jlxb/selectByAll?tsid=' + tsid //数据接口      改
            , skin: 'line,row' //行边框风格
            , even: false //开启隔行背景
            , toolbar: true
            , defaultToolbar: [
                // {
                //     title: '刷新' //标题
                //     , layEvent: 'REFRESH' //事件名，用于 toolbar 事件中使用
                //     , icon: 'layui-icon-refresh'
                // },
                {
                    title: '提交' //标题
                    , layEvent: 'SUBMIT' //事件名，用于 toolbar 事件中使用
                    , icon: 'layui-icon-ok'
                }
            ]
            , cols: [[ //表头
                {field: 'byqgwgj', title: '变压器高温告警', width: '50%', align: 'center', templet: '#byqgwgjjg'}
                , {field: 'byqcwgj', title: '变压器超温告警', width: '50%', align: 'center', templet: '#byqcwgjjg'}
            ]]
            , done: function (res, curr, count) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    // 修改备注
                    editbz();
                    // 空数据
                    if (record.length === 0) {
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/jlxb/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("xb automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $("[name='select4byqgwgj']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].byqgwgj = $(this).val();
                        }
                    });
                });
                $("[name='select4byqcwgj']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].byqcwgj = $(this).val();
                        }
                    });
                });
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].byqgwgj == null || resdata[i].byqgwgj === "" || resdata[i].byqgwgj == -1
                        || resdata[i].byqcwgj == null || resdata[i].byqcwgj === "" || resdata[i].byqcwgj == -1
                    ) {
                        $("#dd_xb").css({"background-color": ""});
                        xb = false;
                        yxtsflag(kgg, dtu, ysbj, xb);
                        return;
                    }
                    xb = true;
                    yxtsflag(kgg, dtu, ysbj, xb);
                    $("#dd_xb").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(xb)', function (obj) {         // 改
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlXb: $("#xbbeizhu").val()
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

//监听事件
        table.on('toolbar(xb)', function (obj) {      // 改
            switch (obj.event) {
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
                        url: "${basePath}/jlxb/updateBatch",              // 改
                        data: JSON.stringify(record.slice(-1)),//必须
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

        //监听结果操作
        form.on('radio(byqgwgjjg)', function () {
            $.each(record, (i) => {
                if (record[i].id === this.name.split("_")[1]) {
                    record[i].byqgwgj = this.value;
                }
            });
        });
        form.on('radio(byqcwgjjg)', function () {
            $.each(record, (i) => {
                if (record[i].id === this.name.split("_")[1]) {
                    record[i].byqcwgj = this.value;
                }
            });
        });
    });
</script>
<%-- 结果状态列(正常/异常)--%>
<script type="text/html" id="byqgwgjjg">
    <select name='select4byqgwgj' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.byqgwgj== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.byqgwgj== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.byqgwgj== 0 ? 'selected' : '' }}>错误</option>
    </select>
</script>
<script type="text/html" id="byqcwgjjg">
    <select name='select4byqcwgj' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.byqcwgj== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.byqcwgj== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.byqcwgj== 0 ? 'selected' : '' }}>错误</option>
    </select>
</script>
</body>
</html>
