<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/2/29
  Time: 10:56 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend id="mao10_3">10.3 溢水报警装置</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <table id="ysbj" lay-filter="ysbj"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="ysbjbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 10.3 溢水报警装置 --%>
<script>
    layui.use('table', function () {
        let table = layui.table
            , form = layui.form;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#ysbj'                           // 改
            , page: false
            , url: '${basePath}/jlysbj/selectByAll?tsid=' + tsid //数据接口      改
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
                {field: 'gswgj', title: '高水位告警', width: '25%', align: 'center', templet: '#gswgjjg'}
                , {field: 'dswgj', title: '低水位告警', width: '25%', align: 'center', templet: '#dswgjjg'}
                , {field: 'sbqdgj', title: '水泵启动告警', width: '25%', align: 'center', templet: '#sbqdgjjg'}
                , {field: 'yszzyc', title: '溢水装置异常', width: '25%', align: 'center', templet: '#yszzycjg'}
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
                        url: "${basePath}/jlysbj/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("ysbj automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $("[name='select4gswgj']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].gswgj = $(this).val();
                        }
                    });
                });
                $("[name='select4dswgj']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].dswgj = $(this).val();
                        }
                    });
                });
                $("[name='select4sbqdgj']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].sbqdgj = $(this).val();
                        }
                    });
                });
                $("[name='select4yszzyc']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].yszzyc = $(this).val();
                        }
                    });
                });
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].gswgj == null || resdata[i].gswgj === "" || resdata[i].gswgj == -1
                        || resdata[i].dswgj == null || resdata[i].dswgj === "" || resdata[i].dswgj == -1
                        || resdata[i].sbqdgj == null || resdata[i].sbqdgj === "" || resdata[i].sbqdgj == -1
                        || resdata[i].yszzyc == null || resdata[i].yszzyc === "" || resdata[i].yszzyc == -1
                    ) {
                        $("#dd_ysbj").css({"background-color": ""});
                        ysbj = false;
                        yxtsflag(kgg, dtu, ysbj, xb);
                        return;
                    }
                    ysbj = true;
                    yxtsflag(kgg, dtu, ysbj, xb);
                    $("#dd_ysbj").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(ysbj)', function (obj) {         // 改
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlYsbj: $("#ysbjbeizhu").val()
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
        table.on('toolbar(ysbj)', function (obj) {      // 改
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
                        url: "${basePath}/jlysbj/updateBatch",              // 改
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
        form.on('radio(gswgjjg)', function () {
            $.each(record, (i) => {
                if (record[i].id === this.name.split("_")[1]) {
                    record[i].gswgj = this.value;
                }
            });
        });
        form.on('radio(dswgjjg)', function () {
            $.each(record, (i) => {
                if (record[i].id === this.name.split("_")[1]) {
                    record[i].dswgj = this.value;
                }
            });
        });
        form.on('radio(sbqdgjjg)', function () {
            $.each(record, (i) => {
                if (record[i].id === this.name.split("_")[1]) {
                    record[i].sbqdgj = this.value;
                }
            });
        });
        form.on('radio(yszzycjg)', function () {
            $.each(record, (i) => {
                if (record[i].id === this.name.split("_")[1]) {
                    record[i].yszzyc = this.value;
                }
            });
        });
    });
</script>
<%-- 结果状态列(正常/异常)--%>
<script type="text/html" id="gswgjjg">
    <select name='select4gswgj' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.gswgj== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.gswgj== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.gswgj== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.gswgj== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="dswgjjg">
    <select name='select4dswgj' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.dswgj== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.dswgj== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.dswgj== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.dswgj== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="sbqdgjjg">
    <select name='select4sbqdgj' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.sbqdgj== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.sbqdgj== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.sbqdgj== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.sbqdgj== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="yszzycjg">
    <select name='select4yszzyc' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.yszzyc== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.yszzyc== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.yszzyc== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.yszzyc== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
</body>
</html>
