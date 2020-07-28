<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/2/29
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
    <legend id="mao9">9、采样精度测试（一次值）</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <h4>开关与控制器航空线已连接，在开关一次侧通入电流。</h4>
        <table id="cyjdcs" lay-filter="cyjdcs"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="cyjdcsbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 9、采样精度测试（一次值） --%>
<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#cyjdcs'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/ftu_jl_cyjdcs/selectByAll?tsid=' + tsid //数据接口      改
            , toolbar: true
            , defaultToolbar: [{
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
            , cols: [[
                    {field: 'id', title: 'ID', type: 'numbers', width: '5%', fixed: 'left', rowspan: 2, sort: true}
                    , {field: 'clx', title: '测量项', width: '42%', rowspan: 2, align: 'center', sort: true}
                    , {title: '读值', align: 'center', colspan: 3}
                    , {field: 'csjg', title: '检查结果', width: '10%', rowspan: 2, align: 'center', templet: '#cyjdcsjg'}
                    , {title: '操作', width: '8%', align: 'center', rowspan: 2, toolbar: '#barDemo'}
                ], [
                    {field: 'ia', title: 'IA', width: '12%', edit: 'text', align: 'center'}
                    , {field: 'ic', title: 'IC', width: '12%', edit: 'text', align: 'center'}
                    , {field: 'io', title: 'I0', width: '12%', edit: 'text', align: 'center'}]
            ]
            , done: function (res, curr, count) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    editbz();
                    if (record.length === 0) {
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_cyjdcs/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("hbdy automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $("[name='select4cyjdcs']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].csjg = $(this).val();
                        }
                    });
                });
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].ia == null || resdata[i].ia === ""
                        || resdata[i].ic == null || resdata[i].ic === ""
                        || resdata[i].io == null || resdata[i].io === ""
                        || resdata[i].csjg == null || resdata[i].csjg === "" || resdata[i].csjg == -1
                    ) {
                        $("#li_cyjdcs").css({"background-color": ""});
                        return;
                    }
                    $("#li_cyjdcs").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(cyjdcs)', function (obj) {         // 改
            // 验证只能输入数值的列
            let array = ['ia', 'ic', 'io'];
            if ($.inArray(obj.field, array) >= 0 && $(this).val() !== '' && $(this).val() != null) {
                checkNum(obj, $(this).prev());
            }
            let data = obj.data;
            $.each(record, function (i) {
                if (record[i].id === data.id) {
                    record[i].ia = data.ia;
                    record[i].ic = data.ic;
                    record[i].io = data.io;
                }
            });
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlCyjdcs: $("#cyjdcsbeizhu").val()
            };
            $.ajax({
                type: "POST",
                url: "${basePath}/ftu_beizhu/updateFtuBeizhuByPrimaryKey",
                data: JSON.stringify(beizhu),//必须
                contentType: "application/json;charset=UTF-8",//必须
                dataType: "json",//必须
                success: function (data) {
                }
            });
        }

        // 提交记录(添加/删除前)
        function submitJl(record) {
            // 表格没有数据
            if (record.length === 0) {
                return true;
            }
            let data = null;
            $.ajax({
                type: "POST",
                async: false,
                url: "${basePath}/ftu_jl_cyjdcs/updateBatch",              // 改
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
        table.on('toolbar(cyjdcs)', function (obj) {      // 改
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
                            title: '添加 --> 采样精度测试（一次值）',
                            maxmin: true,
                            area: ['800px', '520px'],
                            content: '${basePath}/ftuJbxx/ftuTsjl/add/addCyjdcs/' + tsid + '/' + ssqy
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
                    editbz();
                    if (record.length === 0) {
                        layer.msg("无数据提交", {time: 1000, icon: 3});
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_cyjdcs/updateBatch",              // 改
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
        table.on('tool(cyjdcs)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    // 删除前提交
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/ftu_jl_cyjdcs/deleteByPrimaryKey/" + that.id, function (result) {
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
<script type="text/html" id="cyjdcsjg">
    <select name='select4cyjdcs' lay-ignore lay-search=''>
        <option value="-1" {{ d.csjg== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.csjg== 1 ? 'selected' : '' }}>正常</option>
        <option value="0" {{ d.csjg== 0 ? 'selected' : '' }}>超标</option>
        <option value="2" {{ d.csjg== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
</body>
</html>
