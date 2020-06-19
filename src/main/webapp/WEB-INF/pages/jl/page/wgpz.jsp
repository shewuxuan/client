<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/2/29
  Time: 10:43 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend id="mao2">2、DTU外观结构及配置</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <table id="wgpz" lay-filter="wgpz"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="wgpzbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 2、DTU外观结构及配置 --%>
<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#wgpz'
            , title: '用户数据表'
            , skin: 'row,line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/jlwgpz/selectByAll?tsid=' + tsid //数据接口
            , toolbar: true
            , defaultToolbar: [{
                title: '添加' //标题
                , layEvent: 'ADD' //事件名，用于 toolbar 事件中使用
                , icon: 'layui-icon-addition'
            }
                // , {
                //     title: '刷新' //标题
                //     , layEvent: 'REFRESH' //事件名，用于 toolbar 事件中使用
                //     , icon: 'layui-icon-refresh'
                // }
                , {
                    title: '提交' //标题
                    , layEvent: 'SUBMIT' //事件名，用于 toolbar 事件中使用
                    , icon: 'layui-icon-ok'
                }
            ]
            , cols: [[ //表头
                {field: 'id', title: 'ID', type: 'numbers', width: '5%', fixed: 'left', sort: true}
                , {
                    field: 'csxm', title: '测试项目', width: '27%', align: 'center', sort: true, templet: function (d) {
                        return '<div style="text-align: left">' + d.csxm + '</div>';
                    }
                }
                , {
                    field: 'csyq',
                    title: '技术标准或测试要求',
                    width: '46%',
                    align: 'center',
                    sort: true,
                    templet: function (d) {
                        return '<div style="text-align: left">' + d.csyq + '</div>';
                    }
                }
                , {
                    field: 'csjg',
                    title: '检查结果',
                    width: '12%', /*edit: 'text',*/
                    align: 'center',
                    templet: '#status'
                }
                , {title: '操作', width: '10%', align: 'center', toolbar: '#barDemo'}
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
                        url: "${basePath}/jlwgpz/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("wgpz automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $("[name='select4wgpz']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].csjg = $(this).val();
                        }
                    });
                });
                // 设置导航条背景色
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (resdata[i].csjg == null || resdata[i].csjg === "" || resdata[i].csjg == -1) {
                        $("#li_wgpz").css({"background-color": ""});
                        break;
                    }
                    $("#li_wgpz").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(wgpz)', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        });

        //监听事件
        table.on('toolbar(wgpz)', function (obj) {
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
                            title: '添加 --> DTU外观结构及配置',
                            maxmin: true,
                            // shadeClose: true, //点击遮罩关闭层
                            area: ['800px', '520px'],
                            content: '${basePath}/jl/add/addWgpz/' + tsid + '/' + ssqy
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
                        url: "${basePath}/jlwgpz/updateBatch",
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

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlWgpz: $("#wgpzbeizhu").val()
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
                url: "${basePath}/jlwgpz/updateBatch",              // 改
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
        table.on('tool(wgpz)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    // 删除前提交
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/jlwgpz/deleteByPrimaryKey/" + that.id, function (result) {
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

<%-- 结果状态列--%>
<script type="text/html" id="status">
    <select name='select4wgpz' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.csjg== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.csjg== 1 ? 'selected' : '' }}>是</option>
        <option value="0" {{ d.csjg== 0 ? 'selected' : '' }}>否</option>
        <option value="2" {{ d.csjg== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
</body>
</html>
