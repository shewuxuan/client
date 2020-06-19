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
<h4>此项试验前应上好出口跳闸压板</h4>
<table id="tzyb" lay-filter="tzyb"></table>
<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea id="tzybbeizhu" placeholder="" class="layui-textarea"></textarea>
        </div>
    </div>
</form>
<script>
    layui.use('table', function () {
        let table = layui.table;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#tzyb'                           // 改
            , page: false
            , skin: 'row,line' //行边框风格
            , size: 'sm'
            , even: false //开启隔行背景
            , url: '${basePath}/ftu_jl_tzyb/selectByAll?tsid=' + tsid //数据接口      改
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
                {field: 'id', title: 'ID', type: 'numbers', width: '5%', fixed: 'left', sort: true, rowspan: 2}
                , {field: 'jyxm', title: '检验项目', width: '20%', align: 'center', sort: true, rowspan: 2}
                , {field: 'gzmn', title: '故障模拟', width: '15%', align: 'center', sort: true, rowspan: 2}
                , {field: 'mndl', title: '模拟断路器<br>(或启动信号)', width: '20%', align: 'center', edit: 'text', rowspan: 2}
                , {title: '模拟断路器动作情况<br>(或信号动作情况)', align: 'center', colspan: 2}
                , {field: 'jcjg', title: '检查结果', width: '10%', align: 'center', templet: '#tzybjg', rowspan: 2}
                , {title: '操作', width: '8%', align: 'center', toolbar: '#barDemo', rowspan: 2}
            ], [
                {field: 'zzxh', title: '主站信号', width: '11%', align: 'center', edit: 'text'}
                , {field: 'zzxs', title: '装置显示', width: '11%', align: 'center', edit: 'text'}
            ]
            ]
            , done: function (res, curr, count) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_tzyb/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code === 0) {
                                console.log("tzyb automatically saved successfully!");
                            } else {
                                console.log("tzyb automatically saved failed!");
                            }
                        }
                    });
                    editbz();
                }, 30000);
                record = resdata;
                $("[name='select4tzyb']").change(function () {
                    let elem = $(this).parents('tr');
                    let dataindex = elem.attr("data-index");
                    $.each(record, (i, value) => {
                        if (value.LAY_TABLE_INDEX == dataindex) {
                            record[i].jcjg = $(this).val();
                        }
                    });
                });
                // 背景
                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].mndl == null || resdata[i].mndl === ""
                        || resdata[i].zzxh == null || resdata[i].zzxh === ""
                        || resdata[i].zzxs == null || resdata[i].zzxs === ""
                        || resdata[i].jcjg == null || resdata[i].jcjg === "" || resdata[i].jcjg == -1
                    ) {
                        tzyb = false;
                        gjtsflag(gjts, tzyb);
                        return;
                    }
                    tzyb = true;
                    gjtsflag(gjts, tzyb);
                }
            }
        });

        // 编辑
        table.on('edit(tzyb)', function (obj) {         // 改
            let data = obj.data;
            $.each(record, function (i) {
                if (record[i].id === data.id) {
                    record[i].mndl = data.mndl;
                    record[i].zzxh = data.zzxh;
                    record[i].zzxs = data.zzxs;
                }
            });
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlTzyb: $("#tzybbeizhu").val()
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
        table.on('toolbar(tzyb)', function (obj) {      // 改
            switch (obj.event) {
                // 添加
                case 'ADD':
                    if (${requestScope.userType == 0}) {
                        layer.open({
                            type: 2,
                            title: '添加 --> 跳闸压板',
                            maxmin: true,
                            area: ['800px', '520px'],
                            content: '${basePath}/ftuJbxx/ftuTsjl/add/addTzyb/' + tsid + '/' + ssqy
                            , end: function () {
                                tableReload.reload();
                            }
                        });
                    } else {
                        layer.msg("没权限, 你加不了", {time: 1500, icon: 4});
                    }
                    break;
                // 提交
                case 'SUBMIT':
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_tzyb/updateBatch",              // 改
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
        table.on('tool(tzyb)', function (obj) {
            let data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除吗？', function () {
                    $.get("${basePath}/ftu_jl_tzyb/deleteByPrimaryKey/" + data.id, function (result) {
                        layer.msg(JSON.parse(result).msg, {time: 1500, icon: 1});
                        tableReload.reload();
                    });
                });
            }
        });
    });
</script>

<%-- 结果状态列(正常/异常)--%>
<script type="text/html" id="tzybjg">
    <select name='select4tzyb' lay-ignore lay-search=''>
        <option value="-1" {{ d.jcjg== -1 ?
        'selected' : '' }}></option>
        <option value="1" {{ d.jcjg== 1 ?
        'selected' : '' }}>是</option>
        <option value="0" {{ d.jcjg== 0 ?
        'selected' : '' }}>否</option>
    </select>
</script>
</body>
</html>
