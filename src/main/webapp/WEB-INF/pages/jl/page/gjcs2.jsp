<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/5/27
  Time: 10:44 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table id="gjcs2" lay-filter="gjcs2"></table>
</body>
<%-- 13、告警功能调试2 --%>
<script>
    layui.use('table', function () {
        let table = layui.table
            , form = layui.form;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#gjcs2'                           // 改
            , title: '绝缘电阻测试'
            , page: false
            , skin: 'row，line' //行边框风格
            , even: false //开启隔行背景
            , url: '${basePath}/jlgjcs/selectByAll?tsid=' + tsid + "&gjlx=零序" //数据接口      改
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
                , {field: 'jg', title: '间隔', width: '10%', align: 'center', rowspan: 2, sort: true}
                , {field: 'gjlx', title: '告警类型', width: '25%', rowspan: 2, align: 'center', sort: true}
                , {title: '零序', align: 'center', colspan: 2}
                , {
                    field: 'csjg1',
                    title: '检查结果',
                    width: '14%',
                    rowspan: 2,
                    align: 'center',
                    templet: '#gjcs2jg'
                }
                , {title: '操作', width: '8%', align: 'center', rowspan: 2, toolbar: '#barDemo'}]

                , [{field: 'l1', title: '0.95倍', width: '16%', edit: 'text', align: 'center'}
                    , {field: 'l2', title: '1.05倍', width: '16%', edit: 'text', align: 'center'}
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
                                console.log("gjcs2 automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;
                $("[name='select4gjcs2']").change(function () {
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
                        resdata[i].l1 == null || resdata[i].l1 === ""
                        || resdata[i].l2 == null || resdata[i].l2 === ""
                        || resdata[i].csjg1 == null || resdata[i].csjg1 === "" || resdata[i].csjg1 == -1
                    ) {
                        gjcs2 = false;
                        gjcsflag(gjcs1, gjcs2);
                        return;
                    }
                    gjcs2 = true;
                    gjcsflag(gjcs1, gjcs2);
                }
            }
        });

        // 编辑
        table.on('edit(gjcs2)', function (obj) {         // 改
            // 验证只能输入数值的列
            // let array = ['l1','l2'];
            // if ($.inArray(obj.field, array) >= 0 && $(this).val() !== '' && $(this).val() != null) {
            //     checkNum(obj, $(this).prev());
            // }
            let data = obj.data;
            $.each(record, function (i) {
                if (record[i].id === data.id) {
                    record[i].l1 = data.l1;
                    record[i].l2 = data.l2;
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
        table.on('toolbar(gjcs2)', function (obj) {      // 改
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
                            content: '${basePath}/jl/add/addGjcs2/' + tsid + '/' + ssqy
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
                    let checkStatus = table.checkStatus('gjcs2')
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
        table.on('tool(gjcs2)', function (obj) {
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
        form.on('radio(gjcs2jg)', function () {
            $.each(record, (i) => {
                if (record[i].id === this.name) {
                    record[i].csjg1 = this.value;
                }
            });
        });
    });
</script>

<%-- 结果状态列(正常/异常)--%>
<script type="text/html" id="gjcs2jg">
    <select name='select4gjcs2' lay-ignore lay-filter="wgpz" lay-search=''>
        <option value="-1" {{ d.csjg1== -1 ? 'selected' : '' }}></option>
        <option value="1" {{ d.csjg1== 1 ? 'selected' : '' }}>正常</option>
        <option value="0" {{ d.csjg1== 0 ? 'selected' : '' }}>超标</option>
        <option value="2" {{ d.csjg1== 2 ? 'selected' : '' }}>N/A</option>
    </select>
</script>
</html>
