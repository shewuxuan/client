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
    <legend id="mao4">4、电流、电压零漂校验</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <table id="lpjy" lay-filter="lpjy"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="lpjybeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<script>
    layui.use('table', function () {
        let table = layui.table
            , form = layui.form;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#lpjy'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/ftu_jl_lpjy/selectByAll?tsid=' + tsid //数据接口      改
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
                {field: 'id', title: 'ID', type: 'numbers', width: '5%', fixed: 'left', sort: true}
                    , {field: 'hl', title: '回路', width: '40%', align: 'center', sort: true}
                    , {field: 'zzds', title: '装置读数', width: '37%', edit: 'text', align: 'center'}
                    , {field: 'jcjg', title: '检查结果', width: '10%', align: 'center', templet: '#lpjyjg'}
                    , {title: '操作', width: '8%', align: 'center', toolbar: '#barDemo'}
                ]
            ]
            , done: function (res, curr, count) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_lpjy/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code === 0) {
                                console.log("lpjy automatically saved successfully!");
                            } else {
                                console.log("lpjy automatically saved failed!");
                            }
                        }
                    });
                    editbz();
                }, 30000);
                record = resdata;
                $("[name='select4lpjy']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].jcjg = $(this).val();
                        }
                    });
                });
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].zzds == null || resdata[i].zzds === ""
                        || resdata[i].jcjg == null || resdata[i].jcjg === "" || resdata[i].jcjg == -1
                    ) {
                        $("#li_lpjy").css({"background-color": ""});
                        return;
                    }
                    $("#li_lpjy").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(lpjy)', function (obj) {         // 改
            let field = obj.field;
            let array = ['zzds'];
            if ($.inArray(field, array) >= 0 && $(this).val() !== '' && $(this).val() != null) {
                checkNum(obj, $(this).prev());
            }
            let data = obj.data;
            $.each(record, function (i) {
                if (record[i].id === data.id) {
                    record[i].zzds = data.zzds;
                }
            });
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlLpjy: $("#lpjybeizhu").val()
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
        table.on('toolbar(lpjy)', function (obj) {      // 改
            switch (obj.event) {
                // 添加
                case 'ADD':
                    if (${requestScope.userType == 0}) {
                        layer.open({
                            type: 2,
                            title: '添加 --> 电流、电压零漂校验',
                            maxmin: true,
                            area: ['800px', '520px'],
                            content: '${basePath}/ftuJbxx/ftuTsjl/add/addLpjy/' + tsid + '/' + ssqy
                            , end: function () {
                                tableReload.reload();
                            }
                        });
                    } else {
                        layer.msg("没权限, 你加不了", {time: 1500, icon: 4});
                    }
                    break;
                // 刷新
                case 'REFRESH':
                    tableReload.reload();
                    record = [];
                    // 刷新备注
                    $.get("${basePath}/beizhu/selectFtuBeizhuByPrimaryKey/" + tsid, function (data) {
                        $("#lpjybeizhu").val($.parseJSON(data).jlLpjy);
                    });
                    layer.msg('刷新完成', {time: 1000, icon: 6});
                    break;
                // 提交
                case 'SUBMIT':
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_lpjy/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
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

        //监听行操作
        table.on('tool(lpjy)', function (obj) {
            let data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    $.get("${basePath}/ftu_jl_lpjy/deleteByPrimaryKey/" + data.id, function (result) {
                        layer.msg(JSON.parse(result).msg, {time: 1500, icon: 1});
                        tableReload.reload();
                    });
                });
            }
        });
    });
</script>

<%-- 结果状态列(正常/异常)--%>
<script type="text/html" id="lpjyjg">
    <select name='select4lpjy' lay-ignore lay-search=''>
        <option value="-1" {{ d.jcjg== -1 ?
        'selected' : '' }}></option>
        <option value="1" {{ d.jcjg== 1 ?
        'selected' : '' }}>正常</option>
        <option value="0" {{ d.jcjg== 0 ?
        'selected' : '' }}>超标</option>
        <option value="2" {{ d.jcjg== 2 ?
        'selected' : '' }}>N/A</option>
    </select>
</script>
</body>
</html>
