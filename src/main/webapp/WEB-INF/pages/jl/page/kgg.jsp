<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/2/29
  Time: 10:54 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend id="mao10_1">10.1 开关柜</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <table id="kgg" lay-filter="kgg"></table>
        <div id="div_qygj1" style="width: 50%; float: left; text-align: center;">
            <label for="qygj1" style="font-size: unset;">
                1#箱体气压低告警：
                <select id="qygj1">
                    <option value="1" selected>正确</option>
                    <option value="0">错误</option>
                    <option value="2">N/A</option>
                </select>
            </label>
        </div>
        <div id="div_qygj2" style="width: 50%; float: left; text-align: center;">
            <label for="qygj2" style="font-size: unset;">
                2#箱体气压低告警：
                <select id="qygj2">
                    <option value="1" selected="selected">正确</option>
                    <option value="0">错误</option>
                    <option value="2">N/A</option>
                </select>
            </label>
        </div>
        <br>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="kggbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<script>
    // 箱体气压低告警
    function submitGjdz() {
        let gjdz = {
            tsid: tsid,
            qygj1: $("#qygj1").val(),
            qygj2: $("#qygj2").val(),
        };
        $.ajax({
            type: "POST",
            url: "${basePath}/gjdz/updateByPrimaryKeyAndKgg",              // 改
            data: JSON.stringify(gjdz),//必须
            contentType: "application/json;charsetset=UTF-8",//必须
            dataType: "json",//必须
            success: function (data) {
                if (data.code !== 0) {
                    console.log("kgg_gjdz saved failed!");
                }
            }
        });
    }
</script>

<%-- 10.1 开关柜 --%>
<script>
    let kgg = false;
    let dtu = false;
    let ysbj = false;
    let xb = false;

    // 左侧导航栏父栏背景色
    function yxtsflag(kgg, dtu, ysbj, xb) {
        if (kgg && dtu && ysbj && xb) {
            $("#li_yxts").css({"background-color": "#009688"});
            return;
        }
        $("#li_yxts").css({"background-color": ""});
    }

    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#kgg'
            , title: 'CT工艺检查'
            , page: false
            , skin: 'row,line，nob' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/jlkgg/selectByAll?tsid=' + tsid //数据接口      改
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
                {type: 'checkbox', fixed: 'left', rowspan: 2}
                , {field: 'id', title: 'ID', type: 'numbers', width: '5%', fixed: 'left', rowspan: 2, sort: true}
                , {field: 'jg', title: '间隔', width: '9%', rowspan: 2, align: 'center', sort: true}
                , {title: '手把', align: 'center', colspan: 2}
                , {title: '地刀', align: 'center', colspan: 2}
                , {title: '开关', align: 'center', colspan: 2}
                , {title: '操作', width: '8%', align: 'center', rowspan: 2, toolbar: '#barDemo'}]

                , [{field: 'sbyf', title: '远方', width: '12%', align: 'center', templet: '#sbyfjg'}
                    , {field: 'sbjd', title: '就地', width: '12%', align: 'center', templet: '#sbjdjg'}
                    , {field: 'ddf', title: '分', width: '12%', align: 'center', templet: '#ddfjg'}
                    , {field: 'ddh', title: '合', width: '12%', align: 'center', templet: '#ddhjg'}
                    , {field: 'kgf', title: '分', width: '12%', align: 'center', templet: '#kgfjg'}
                    , {field: 'kgh', title: '合', width: '12%', align: 'center', templet: '#kghjg'}
                ]]
            , done: function (res) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    editbz();
                    submitGjdz();
                    if (record.length === 0) {
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/jlkgg/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("kgg automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $("[name='select4sbyf']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].sbyf = $(this).val();
                        }
                    });
                });
                $("[name='select4sbjd']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].sbjd = $(this).val();
                        }
                    });
                });
                $("[name='select4ddf']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].ddf = $(this).val();
                        }
                    });
                });
                $("[name='select4ddh']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].ddh = $(this).val();
                        }
                    });
                });
                $("[name='select4kgf']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].kgf = $(this).val();
                        }
                    });
                });
                $("[name='select4kgh']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].kgh = $(this).val();
                        }
                    });
                });
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].sbyf == null || resdata[i].sbyf === "" || resdata[i].sbyf == -1
                        || resdata[i].sbjd == null || resdata[i].sbjd === "" || resdata[i].sbjd == -1
                        || resdata[i].ddf == null || resdata[i].ddf === "" || resdata[i].ddf == -1
                        || resdata[i].ddh == null || resdata[i].ddh === "" || resdata[i].ddh == -1
                        || resdata[i].kgf == null || resdata[i].kgf === "" || resdata[i].kgf == -1
                        || resdata[i].kgh == null || resdata[i].kgh === "" || resdata[i].kgh == -1
                    ) {
                        $("#dd_kgg").css({"background-color": ""});
                        kgg = false;
                        yxtsflag(kgg, dtu, ysbj, xb);
                        return;
                    }
                    kgg = true;
                    yxtsflag(kgg, dtu, ysbj, xb);
                    $("#dd_kgg").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(kgg)', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            let data = obj.data;
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlKgg: $("#kggbeizhu").val()
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
                url: "${basePath}/jlkgg/updateBatch",              // 改
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
        table.on('toolbar(kgg)', function (obj) {
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
                            title: '添加 --> 开关柜',
                            maxmin: true,
                            // shadeClose: true, //点击遮罩关闭层
                            area: ['800px', '520px'],
                            content: '${basePath}/jl/add/addKgg/' + tsid + '/' + ssqy
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
                    // 箱体气压低告警
                    submitGjdz();
                    if (record.length === 0) {
                        layer.msg("无数据提交", {time: 1000, icon: 3});
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/jlkgg/updateBatch",
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
                case 'DEL':
                    if (${requestScope.userType != 0}) {
                        layer.msg("权限不足！", {time: 1500, icon: 4});
                        return;
                    }
                    let checkStatus = table.checkStatus('kgg')
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
                                url: "${basePath}/jlkgg/batchDeleteByPrimaryKey",              // 改
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

        //监听行操作
        table.on('tool(kgg)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/jlkgg/deleteByPrimaryKey/" + that.id, function (result) {
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

<%-- 结果状态列(正常/异常)--%>
<script type="text/html" id="sbyfjg">
    <select name='select4sbyf' lay-ignore lay-filter="sbyfjg" lay-search=''>
        <option value="-1" {{ d.sbyf== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.sbyf== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.sbyf== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.sbyf== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="sbjdjg">
    <select name='select4sbjd' lay-ignore lay-filter="sbjdjg" lay-search=''>
        <option value="-1" {{ d.sbjd== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.sbjd== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.sbjd== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.sbjd== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="ddfjg">
    <select name='select4ddf' lay-ignore lay-filter="ddfjg" lay-search=''>
        <option value="-1" {{ d.ddf== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.ddf== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.ddf== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.ddf== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="ddhjg">
    <select name='select4ddh' lay-ignore lay-filter="ddhjg" lay-search=''>
        <option value="-1" {{ d.ddh== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.ddh== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.ddh== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.ddh== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="kgfjg">
    <select name='select4kgf' lay-ignore lay-filter="kgfjg" lay-search=''>
        <option value="-1" {{ d.kgf== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.kgf== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.kgf== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.kgf== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="kghjg">
    <select name='select4kgh' lay-ignore lay-filter="kghjg" lay-search=''>
        <option value="-1" {{ d.kgh== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.kgh== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.kgh== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.kgh== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
</body>
</html>
