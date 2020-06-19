<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/2/29
  Time: 10:55 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend id="mao10_2">10.2 DTU</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <table id="dtu" lay-filter="dtu"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="dtubeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 10.2 DTU --%>
<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#dtu'                           // 改
            , page: false
            , url: '${basePath}/jldtu/selectByAll?tsid=' + tsid //数据接口      改
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
                {field: 'yf', title: '远方', width: '20%', align: 'center', templet: '#yfjg'}
                , {field: 'jd', title: '就地', width: '20%', align: 'center', templet: '#jdjg'}
                , {field: 'dcqy', title: '电池欠压', width: '20%', align: 'center', templet: '#dcqyjg'}
                , {field: 'dchh', title: '电池活化', width: '20%', align: 'center', templet: '#dchhjg'}
                , {field: 'jlsd', title: '交流失电', width: '20%', align: 'center', templet: '#jlsdjg'}
            ]]
            , done: function (res) {
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
                        url: "${basePath}/jldtu/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("dtu automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $("[name='select4yfjg']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].yf = $(this).val();
                        }
                    });
                });
                $("[name='select4jdjg']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].jd = $(this).val();
                        }
                    });
                });
                $("[name='select4dcqyjg']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].dcqy = $(this).val();
                        }
                    });
                });
                $("[name='select4dchhjg']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].dchh = $(this).val();
                        }
                    });
                });
                $("[name='select4jlsdjg']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].jlsd = $(this).val();
                        }
                    });
                });


                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].yf == null || resdata[i].yf === "" || resdata[i].yf == -1
                        || resdata[i].jd == null || resdata[i].jd === "" || resdata[i].jd == -1
                        || resdata[i].dcqy == null || resdata[i].dcqy === "" || resdata[i].dcqy == -1
                        || resdata[i].dchh == null || resdata[i].dchh === ""  || resdata[i].dchh == -1
                        || resdata[i].jlsd == null || resdata[i].jlsd === "" || resdata[i].jlsd == -1
                    ) {
                        $("#dd_dtu").css({"background-color": ""});
                        dtu = false;
                        yxtsflag(kgg, dtu, ysbj, xb);
                        return;
                    }
                    dtu = true;
                    yxtsflag(kgg, dtu, ysbj, xb);
                    $("#dd_dtu").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(dtu)', function (obj) {         // 改
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlDtu: $("#dtubeizhu").val()
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
        table.on('toolbar(dtu)', function (obj) {      // 改
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
                        url: "${basePath}/jldtu/updateBatch",              // 改
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

                    // 修改备注
                    editbz();
                    break;
            }
        });
    });
</script>

<%-- 结果状态列(正常/异常)--%>
<script type="text/html" id="yfjg">
    <select name='select4yfjg' lay-ignore lay-filter="yfjg" lay-search=''>
        <option value="-1" {{ d.yf== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.yf== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.yf== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.yf== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="jdjg">
    <select name='select4jdjg' lay-ignore lay-filter="jdjg" lay-search=''>
        <option value="-1" {{ d.jd== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.jd== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.jd== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.jd== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="dcqyjg">
    <select name='select4dcqyjg' lay-ignore lay-filter="dcqyjg" lay-search=''>
        <option value="-1" {{ d.dcqy== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.dcqy== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.dcqy== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.dcqy== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="dchhjg">
    <select name='select4dchhjg' lay-ignore lay-filter="dchhjg" lay-search=''>
        <option value="-1" {{ d.dchh== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.dchh== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.dchh== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.dchh== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="jlsdjg">
    <select name='select4jlsdjg' lay-ignore lay-filter="jlsdjg" lay-search=''>
        <option value="-1" {{ d.jlsd== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.jlsd== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.jlsd== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.jlsd== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
</body>
</html>
