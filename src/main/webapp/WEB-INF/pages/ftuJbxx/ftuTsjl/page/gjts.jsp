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
    <style>
        .layui-table-cell {
            height: auto !important;
        }
    </style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend id="mao7">7、跳闸、告警功能调试（通入二次值）</legend>
</fieldset>
<div class="layui-field-box" style="padding-left: 0px;">
    <blockquote class="layui-elem-quote" style="width: 98%; margin: auto;">
        <%-- 告警定值--%>
        <%@include file="gjdz.jsp" %>
        <table id="gjts" lay-filter="gjts"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="gjtsbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
        <%-- 跳闸压板--%>
        <%@include file="tzyb.jsp" %>
    </blockquote>
</div>

<%-- 导航背景--%>
<script>
    let gjts = false;
    let tzyb = false;
    function gjtsflag(gjts,tzyb) {
        if (gjts && tzyb) {
            $("#li_gjts").css({"background-color": "#009688"});
            return;
        }
        $("#li_gjts").css({"background-color": ""});
    }
</script>

<%-- 表格 --%>
<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#gjts'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , size: 'sm'
            , even: false //开启隔行背景
            , url: '${basePath}/ftu_jl_gjts/selectByAll?tsid=' + tsid //数据接口      改
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
                {field: 'csx', title: '测试项', width: '7%', rowspan: 2, align: 'center'}
                , {field: 'g1dz', title: '过流<br>Ⅰ段<br>整定值', width: '8%', rowspan: 2, align: 'center', edit: 'text'}
                , {title: '实际通电<br>0.95倍定值<br>/1.05倍定值', align: 'center', colspan: 2}
                , {field: 'g2dz', title: '过流<br>ⅠⅠ段<br>整定值', width: '8%', rowspan: 2, align: 'center', edit: 'text'}
                , {title: '实际通电<br>0.95倍定值<br>/1.05倍定值', align: 'center', colspan: 2}
                , {field: 'lx1dz', title: '零序<br>Ⅰ段<br>整定值', width: '8%', rowspan: 2, align: 'center', edit: 'text'}
                , {title: '实际通电<br>0.95倍定值<br>/1.05倍定值', align: 'center', colspan: 2}
                , {field: 'lx2dz', title: '零序<br>ⅠⅠ段<br>整定值', width: '8%', rowspan: 2, align: 'center', edit: 'text'}
                , {title: '实际通电<br>0.95倍定值<br>/1.05倍定值', align: 'center', colspan: 2}
                , {title: '操作', width: '7%', align: 'center', rowspan: 2, toolbar: '#barDemo'}
            ]

                , [
                    {field: 'g1sjtd1', title: '不动作', width: '7%', edit: 'text', align: 'center'}
                    , {field: 'g1sjtd2', title: '动作', width: '7%', edit: 'text', align: 'center'}
                    , {field: 'g2sjtd1', title: '不动作', width: '7%', edit: 'text', align: 'center'}
                    , {field: 'g2sjtd2', title: '动作', width: '7%', edit: 'text', align: 'center'}
                    , {field: 'lx1sjtd1', title: '不动作', width: '7%', edit: 'text', align: 'center'}
                    , {field: 'lx1sjtd2', title: '动作', width: '7%', edit: 'text', align: 'center'}
                    , {field: 'lx2sjtd1', title: '不动作', width: '7%', edit: 'text', align: 'center'}
                    , {field: 'lx2sjtd2', title: '动作', width: '7%', edit: 'text', align: 'center'}
                ]
            ]
            , done: function (res, curr, count) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_gjts/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code === 0) {
                                console.log("gjts automatically saved successfully!");
                            } else {
                                console.log("gjts automatically saved failed!");
                            }
                        }
                    });
                    editbz();
                }, 30000);
                record = resdata;
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].g1dz == null || resdata[i].g1dz === ""
                        || resdata[i].g1sjtd1 == null || resdata[i].g1sjtd1 === ""
                        || resdata[i].g1sjtd2 == null || resdata[i].g1sjtd2 === ""
                        || resdata[i].g2dz == null || resdata[i].g2dz === ""
                        || resdata[i].g2sjtd1 == null || resdata[i].g2sjtd1 === ""
                        || resdata[i].g2sjtd2 == null || resdata[i].g2sjtd2 === ""
                        || resdata[i].lx1dz == null || resdata[i].lx1dz === ""
                        || resdata[i].lx1sjtd1 == null || resdata[i].lx1sjtd1 === ""
                        || resdata[i].lx1sjtd2 == null || resdata[i].lx1sjtd2 === ""
                        || resdata[i].lx2dz == null || resdata[i].lx2dz === ""
                        || resdata[i].lx2sjtd1 == null || resdata[i].lx2sjtd1 === ""
                        || resdata[i].lx2sjtd2 == null || resdata[i].lx2sjtd2 === ""
                    ) {
                        gjts = false;
                        gjtsflag(gjts, tzyb);
                        return;
                    }
                    gjts = true;
                    gjtsflag(gjts, tzyb);
                }
            }
        });

        // 编辑
        table.on('edit(gjts)', function (obj) {         // 改
            // 验证只能输入数值的列
            let array = ['g1dz', 'g1sjtd1', 'g1sjtd2', 'g2dz', 'g2sjtd1', 'g2sjtd2', 'lx1dz', 'lx1sjtd1', 'lx1sjtd2', 'lx2dz', 'lx2sjtd1', 'lx2sjtd2'];
            if ($.inArray(obj.field, array) >= 0 && $(this).val() !== '' && $(this).val() != null) {
                checkNum(obj, $(this).prev());
            }
            let data = obj.data;
            $.each(record, function (i) {
                if (record[i].id === data.id) {
                    record[i].g1dz = data.g1dz;
                    record[i].g1sjtd1 = data.g1sjtd1;
                    record[i].g1sjtd2 = data.g1sjtd2;
                    record[i].g2dz = data.g2dz;
                    record[i].g2sjtd1 = data.g2sjtd1;
                    record[i].g2sjtd2 = data.g2sjtd2;
                    record[i].lx1dz = data.lx1dz;
                    record[i].lx1sjtd1 = data.lx1sjtd1;
                    record[i].lx1sjtd2 = data.lx1sjtd2;
                    record[i].lx2dz = data.lx2dz;
                    record[i].lx2sjtd1 = data.lx2sjtd1;
                    record[i].lx2sjtd2 = data.lx2sjtd2;
                }
            });
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlGjts: $("#gjtsbeizhu").val()
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
        table.on('toolbar(gjts)', function (obj) {      // 改
            switch (obj.event) {
                // 添加
                case 'ADD':
                    if (${requestScope.userType == 0}) {
                        layer.open({
                            type: 2,
                            title: '添加 --> 告警功能调试（通入二次值）',
                            maxmin: true,
                            area: ['800px', '520px'],
                            content: '${basePath}/ftuJbxx/ftuTsjl/add/addGjts/' + tsid + '/' + ssqy
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
                        $("#gjtsbeizhu").val($.parseJSON(data).jlGjts);
                    });
                    layer.msg('刷新完成', {time: 1000, icon: 6});
                    break;
                // 提交
                case 'SUBMIT':
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_gjts/updateBatch",              // 改
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
        table.on('tool(gjts)', function (obj) {
            let data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    $.get("${basePath}/ftu_jl_gjts/deleteByPrimaryKey/" + data.id, function (result) {
                        layer.msg(JSON.parse(result).msg, {time: 1500, icon: 1});
                        tableReload.reload();
                    });
                });
            }
        });
    });
</script>
</body>
</html>
