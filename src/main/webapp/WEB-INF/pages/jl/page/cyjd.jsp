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
    <legend id="mao6">6、采样精度测试（测量值在0.5%以内）</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <h4>测量步骤：</h4>
        <h4>继保仪Ia、In输出电流接开关柜的A、C相及零序电流试验端子，测试中满度值采用额定电流5A，半度值采用2.5A 。</h4>
        <h4>（1）满度测量值应在4.975~5.025A以内</h4>
        <h4>（2）半度测量值应在2.488~2.513A以内</h4>
        <table id="cyjd" lay-filter="cyjd"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="cyjdbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 6、采样精度测试（测量值在0.5%以内） --%>
<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#cyjd'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/jlcyjd/selectByAll?tsid=' + tsid //数据接口      改
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
            , cols: [
                // [{title: '模拟量采集单元测试记录', align: 'center', colspan: 3}],
                [{title: '模拟量采集单元测试记录', align: 'center', colspan: 11}]

                , [
                    {type: 'checkbox', fixed: 'left', rowspan: 2}
                    , {field: 'id', title: 'ID', type: 'numbers', width: '5%', fixed: 'left', rowspan: 2, sort: true}
                    , {field: 'jg', title: '间隔', width: '9%', rowspan: 2, align: 'center', sort: true}
                    , {
                        field: 'csgn',
                        title: '测试功能',
                        width: '18%',
                        rowspan: 2,
                        align: 'center',
                        sort: true,
                        templet: function (d) {
                            return '<div style="text-align: left">' + d.csgn + '</div>';
                        }
                    }
                    , {field: 'clx', title: '测量项', width: '9%', rowspan: 2, align: 'center'}
                    , {title: '读值', width: '10%', align: 'center', colspan: 4}
                    , {field: 'csjg', title: '检查结果', width: '10%', rowspan: 2, align: 'center', templet: '#cyjdjg'}
                    , {title: '操作', width: '8%', align: 'center', rowspan: 2, toolbar: '#barDemo'}
                ]

                , [{field: 'a', title: 'A', width: '9%', edit: 'text', align: 'center'}
                    , {field: 'b', title: 'B', width: '9%', edit: 'text', align: 'center'}
                    , {field: 'c', title: 'C', width: '9%', edit: 'text', align: 'center'}
                    , {field: 'lx', title: '零序', width: '9%', edit: 'text', align: 'center'}]
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
                        url: "${basePath}/jlcyjd/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("cyjd automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $("[name='select4cyjd']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX==dataindex) {
                            record[i].csjg = $(this).val();
                        }
                    });
                });
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].a == null || resdata[i].a === ""
                        || resdata[i].b == null || resdata[i].b === ""
                        || resdata[i].c == null || resdata[i].c === ""
                        || resdata[i].lx == null || resdata[i].lx === ""
                        || resdata[i].csjg == null || resdata[i].csjg === "" || resdata[i].csjg == -1
                    ) {
                        $("#li_cyjd").css({"background-color": ""});
                        return;
                    }
                    $("#li_cyjd").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(cyjd)', function (obj) {         // 改
            let data = obj.data;
            $.each(record, function (i) {
                if (record[i].id === data.id) {
                    record[i].a = data.a;
                    record[i].b = data.b;
                    record[i].c = data.c;
                    record[i].lx = data.lx;
                }
            });
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlCyjd: $("#cyjdbeizhu").val()
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

        // 提交记录
        function submitJl(record) {
            // 表格没有数据
            if (record.length === 0) {
                return true;
            }
            let data = null;
            $.ajax({
                type: "POST",
                async: false,
                url: "${basePath}/jlcyjd/updateBatch",              // 改
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
        table.on('toolbar(cyjd)', function (obj) {      // 改
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
                            title: '添加 --> 采样精度测试',
                            maxmin: true,
                            // shadeClose: true, //点击遮罩关闭层
                            area: ['800px', '520px'],
                            content: '${basePath}/jl/add/addCyjd/' + tsid + '/' + ssqy
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
                        url: "${basePath}/jlcyjd/updateBatch",              // 改
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
                    // 权限
                    if (${requestScope.userType != 0}) {
                        layer.msg("权限不足！", {time: 1500, icon: 4});
                        return;
                    }
                        let checkStatus = table.checkStatus('cyjd')
                        , data = checkStatus.data
                        , ids = [];
                    // 复选框id
                    $.each(data, function (i) {
                        ids.push(data[i].id);
                    });
                    if (ids.length === 0) {
                        layer.msg('未选中要删除的行',function () {});
                        return;
                    }
                    layer.confirm('真的删除吗？', function () {
                        // 删除前提交
                        if (submitJl(record)) {
                            // 删除
                            $.ajax({
                                type: "POST",
                                url: "${basePath}/jlcyjd/batchDeleteByPrimaryKey",              // 改
                                data: JSON.stringify(ids),//必须
                                contentType: "application/json;charsetset=UTF-8",//必须
                                dataType: "json",//必须
                                success: function (result) {
                                    clearTimeout(autosave);
                                    tableReload.reload();
                                    if (result.code === 0) {
                                        layer.msg(result.msg, {time: 1500, icon: 1});
                                    } else {
                                        layer.msg(result.msg, {time: 2000, icon: 5});
                                    }
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
        table.on('tool(cyjd)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/jlcyjd/deleteByPrimaryKey/" + that.id, function (result) {
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
<script type="text/html" id="cyjdjg">
    <select name='select4cyjd' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.csjg== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.csjg== 1 ? 'selected' : '' }}>正常</option>
        <option value="0" {{ d.csjg== 0 ? 'selected' : '' }}>超标</option>
        <option value="2" {{ d.csjg== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
</body>
</html>
