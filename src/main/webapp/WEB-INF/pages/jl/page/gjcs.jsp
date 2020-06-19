<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/2/29
  Time: 11:00 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend id="mao13">13、告警功能调试</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <h4>测量步骤：</h4>
        <h4>（1）在DTU设置过流和零序告警定值。</h4>
        <h4>（2）在保护装置设置过流和零序定值，并投入跳闸功能。</h4>
        <h4>（3）分别在测量CT、保护CT和零序CT端子上加电流，记录主站收到的一次电流值。记录是否收到过流告警、零序告警、过流保护动作出口告警、零序保护动作出口告警，要求出现以上告警时必须伴随线路总告警。</h4>
        <%@include file="gjdz.jsp" %>
        <table id="gjcs1" lay-filter="gjcs1"></table>
        <%@include file="gjcs2.jsp"%>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="gjcsbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 13、告警功能调试1 --%>
<script>
    <%--  判断两个表格是否都有值  --%>
    let gjcs1 = false;
    let gjcs2 = false;

    function gjcsflag(gjcs1, gjcs2) {
        if (gjcs1 && gjcs2) {
            $("#li_gjcs").css({"background-color": "#009688"});
            return;
        }
        $("#li_gjcs").css({"background-color": ""});
    }

    layui.use('table', function () {
        let table = layui.table
            , form = layui.form;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#gjcs1'                           // 改
            , page: false
            , skin: 'row，line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/jlgjcs/selectByAll?tsid=' + tsid + "&gjlx=过流" //数据接口      改
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
                , {field: 'id', title: 'ID', type: 'numbers', width: '4%', fixed: 'left', rowspan: 2, sort: true}
                , {field: 'jg', title: '间隔', width: '9%', align: 'center', rowspan: 2, sort: true}
                , {field: 'gjlx', title: '告警类型', width: '10%', rowspan: 2, align: 'center'}
                , {title: 'A相', align: 'center', colspan: 2}
                , {title: 'B相', align: 'center', colspan: 2}
                , {title: 'C相', align: 'center', colspan: 2}
                , {field: 'csjg1', title: '检查结果', width: '10%', rowspan: 2, align: 'center', templet: '#gjcs1jg'}
                , {title: '操作', width: '8%', align: 'center', rowspan: 2, toolbar: '#barDemo'}]

                , [{field: 'a1', title: '0.95倍', width: '9%', edit: 'text', align: 'center'}
                    , {field: 'a2', title: '1.05倍', width: '9%', edit: 'text', align: 'center'}
                    , {field: 'b1', title: '0.95倍', width: '9%', edit: 'text', align: 'center'}
                    , {field: 'b2', title: '1.05倍', width: '9%', edit: 'text', align: 'center'}
                    , {field: 'c1', title: '0.95倍', width: '9%', edit: 'text', align: 'center'}
                    , {field: 'c2', title: '1.05倍', width: '9%', edit: 'text', align: 'center'}
                    // , {field: 'csjg2', title: '结果', width: '10%', edit: 'text', align: 'center', sort: true}
                ]
            ]
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
                        url: "${basePath}/jlgjcs/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("gjcs1 automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $("[name='select4gjcs1']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].csjg1 = $(this).val();
                        }
                    });
                });
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].a1 == null || resdata[i].a1 === ""
                        || resdata[i].a2 == null || resdata[i].a2 === ""
                        || resdata[i].b1 == null || resdata[i].b1 === ""
                        || resdata[i].b2 == null || resdata[i].b2 === ""
                        || resdata[i].c1 == null || resdata[i].c1 === ""
                        || resdata[i].c2 == null || resdata[i].c2 === ""
                        || resdata[i].csjg1 == null || resdata[i].csjg1 === "" || resdata[i].csjg1 == -1
                    ) {
                        gjcs1 = false;
                        gjcsflag(gjcs1, gjcs2);
                        return;
                    }
                    gjcs1 = true;
                    gjcsflag(gjcs1, gjcs2);
                }
            }
        });

        // 编辑
        table.on('edit(gjcs1)', function (obj) {         // 改
            let data = obj.data;
            $.each(record, function (i) {
                if (record[i].id === data.id) {
                    record[i].a1 = data.a1;
                    record[i].a2 = data.a2;
                    record[i].b1 = data.b1;
                    record[i].b2 = data.b2;
                    record[i].c1 = data.c1;
                    record[i].c2 = data.c2;
                }
            });
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlGjcs: $("#gjcsbeizhu").val()
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
                url: "${basePath}/jlgjcs/updateBatch",              // 改
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
        table.on('toolbar(gjcs1)', function (obj) {      // 改
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
                            title: '添加 --> 告警功能调试',
                            maxmin: true,
                            // shadeClose: true, //点击遮罩关闭层
                            area: ['800px', '520px'],
                            content: '${basePath}/jl/add/addGjcs1/' + tsid + '/' + ssqy
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
                        url: "${basePath}/jlgjcs/updateBatch",              // 改
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
                    let checkStatus = table.checkStatus('gjcs1')
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
                                url: "${basePath}/jlgjcs/batchDeleteByPrimaryKey",              // 改
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
        table.on('tool(gjcs1)', function (obj) {
            let that = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    if (submitJl(record)) {
                        // 删除
                        $.get("${basePath}/jlgjcs/deleteByPrimaryKey/" + that.id, function (result) {
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

        //监听结果操作
        form.on('radio(gjcs1jg)', function () {
            $.each(record, (i) => {
                if (record[i].id === this.name) {
                    record[i].csjg1 = this.value;
                }
            });
        });
    });
</script>

<%-- 结果状态列(正常/异常)--%>
<script type="text/html" id="gjcs1jg">
    <select name='select4gjcs1' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.csjg1== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.csjg1== 1 ? 'selected' : '' }}>正常</option>
        <option value="0" {{ d.csjg1== 0 ? 'selected' : '' }}>超标</option>
        <option value="2" {{ d.csjg1== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
</body>
</html>
