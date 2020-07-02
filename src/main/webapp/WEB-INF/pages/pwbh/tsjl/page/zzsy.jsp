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
    <legend id="mao11">11、整组试验及实际断路器传动</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <table id="zzsy" lay-filter="zzsy"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="zzsybeizhu" placeholder="" class="layui-textarea"></textarea>
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
            elem: '#zzsy'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/pwbh_jl_zzsy/selectByAll?tsid=' + tsid //数据接口      改
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
                , {field: 'jyxm', title: '检验项目', width: '20%', align: 'center', sort: true}
                , {field: 'gzmn', title: '故障模拟', width: '27%', align: 'center', edit: 'text'}
                , {field: 'dlq', title: '断路器', width: '10%', align: 'center', templet: '#zzsydlq'}
                , {field: 'zzxh', title: '主站信号', width: '10%', align: 'center', templet: '#zzsyzzxh'}
                , {field: 'zzxs', title: '装置显示', width: '10%', align: 'center', templet: '#zzsyzzxs'}
                , {field: 'jcjg', title: '检查结果', width: '10%', align: 'center', templet: '#zzsyjg'}
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
                        url: "${basePath}/pwbh_jl_zzsy/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("zzsy automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $("[name='select4zzsy']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            value.jcjg = $(this).val();
                        }
                    });
                });
                $("[name='select4dlq']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            value.dlq = $(this).val();
                        }
                    });
                });
                $("[name='select4zzxh']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            value.zzxh = $(this).val();
                        }
                    });
                });
                $("[name='select4zzxs']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            value.zzxs = $(this).val();
                        }
                    });
                });
                $.each(resdata, function (i, value) {
                    if (value.gzmn == null || value.gzmn === ""
                    || value.dlq == null || value.dlq === "" || value.dlq == -1
                    || value.zzxh == null || value.zzxh === "" || value.zzxh == -1
                    || value.zzxs == null || value.zzxs === "" || value.zzxs == -1
                    || value.jcjg == null || value.jcjg === "" || value.jcjg == -1) {
                        $("#li_zzsy").css({"background-color": ""});
                        return false;
                    }
                    $("#li_zzsy").css({"background-color": "#009688"});
                })
            }
        });

        // 编辑
        table.on('edit(zzsy)', function (obj) {         // 改
            let data = obj.data;
            // $.each(record, function (i, value) {
            //     if (value.id === data.id) {
            //         value.dlq = data.dlq;
            //         value.zzxh = data.zzxh;
            //         value.zzxs = data.zzxs;
            //     }
            // });
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                pwbhJlZzsy: $("#zzsybeizhu").val()
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
                url: "${basePath}/pwbh_jl_zzsy/updateBatch",              // 改
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
        table.on('toolbar(zzsy)', function (obj) {      // 改
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
                            title: '添加 --> 整组试验及实际断路器传动',
                            maxmin: true,
                            // shadeClose: true, //点击遮罩关闭层
                            area: ['800px', '520px'],
                            content: '${basePath}/pwbh/tsjl/add/addZzsy/' + tsid + '/' + ssqy
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
                        url: "${basePath}/pwbh_jl_zzsy/updateBatch",              // 改
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
        table.on('tool(zzsy)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    // 删除前提交
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/pwbh_jl_zzsy/deleteByPrimaryKey/" + that.id, function (result) {
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
<script type="text/html" id="zzsyjg">
    <select name='select4zzsy' lay-ignore lay-filter="zzsy" lay-search=''>
        <option value="-1" {{ d.jcjg== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.jcjg== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.jcjg== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.jcjg== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="zzsydlq">
    <select name='select4dlq' lay-ignore lay-filter="zzsydlq" lay-search=''>
        <option value="-1" {{ d.dlq== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.dlq== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.dlq== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.dlq== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="zzsyzzxh">
    <select name='select4zzxh' lay-ignore lay-filter="zzsyzzxh" lay-search=''>
        <option value="-1" {{ d.zzxh== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.zzxh== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.zzxh== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.zzxh== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
<script type="text/html" id="zzsyzzxs">
    <select name='select4zzxs' lay-ignore lay-filter="zzsyzzxs" lay-search=''>
        <option value="-1" {{ d.zzxs== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.zzxs== 1 ? 'selected' : '' }}>正确</option>
        <option value="0" {{ d.zzxs== 0 ? 'selected' : '' }}>错误</option>
        <option value="2" {{ d.zzxs== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
</body>
</html>
