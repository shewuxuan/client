<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/2/29
  Time: 10:55 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend id="mao2">2. 开关定值</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <table id="kgdz" lay-filter="kgdz"></table>
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="kgdzbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>

<%-- 2. 开关定值 --%>
<script>
    layui.use('table', function () {
        let table = layui.table
            , form = layui.form;
        let record = [];
        let autosave = null;
        let tableReload = table.render({
            elem: '#kgdz'                           // 改
            , page: false
            , url: '${basePath}/ftu_jl_kgdz/selectByAll?tsid=' + tsid //数据接口      改
            , skin: 'line,row' //行边框风格
            , even: false //开启隔行背景
            , toolbar: true
            , defaultToolbar: [
                {
                    title: '提交' //标题
                    , layEvent: 'SUBMIT' //事件名，用于 toolbar 事件中使用
                    , icon: 'layui-icon-ok'
                }
            ]
            , cols: [
                [{title: '柱 上 断 路 器', align: 'center', colspan: 10}],
                [ //表头
                {field: 'id', title: 'ID', type: 'numbers', width: '5%', fixed: 'left'}
                , {field: 'dzdh', title: '定值单号', width: '15%', align: 'center', edit:'text'}
                , {field: 'g11', title: '过流Ｉ段', width: '10%', align: 'center', edit:'text'}
                , {field: 'g12', title: '过流Ｉ段', width: '10%', align: 'center', edit:'text'}
                , {field: 'g21', title: '过流II段', width: '10%', align: 'center', edit:'text'}
                , {field: 'g22', title: '过流II段', width: '10%', align: 'center', edit:'text'}
                , {field: 'lx11', title: '零序Ｉ段', width: '10%', align: 'center', edit:'text'}
                , {field: 'lx12', title: '零序Ｉ段', width: '10%', align: 'center', edit:'text'}
                , {field: 'lx21', title: '零序II段', width: '10%', align: 'center', edit:'text'}
                , {field: 'lx22', title: '零序II段', width: '9%', align: 'center', edit:'text'}
            ]]
            , done: function (res) {
                let resdata = res.data;
                // 开启自动保存（自动保存记录/备注）
                autosave = setInterval(function () {
                    editbz();
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_kgdz/updateBatch",              // 改
                        data: JSON.stringify(record),//必须
                        contentType: "application/json;charsetset=UTF-8",//必须
                        dataType: "json",//必须
                        success: function (data) {
                            if (data.code !== 0) {
                                console.log("kgdz automatically saved failed!");
                            }
                        }
                    });
                }, 10000);
                record = resdata;

                for (let i = 0, length = resdata.length; i < length; i++) {
                    if (
                        resdata[i].dzdh == null || resdata[i].dzdh === ""
                        || resdata[i].g11 == null || resdata[i].g11 === ""
                        || resdata[i].g12 == null || resdata[i].g12 === ""
                        || resdata[i].g21 == null || resdata[i].g21 === ""
                        || resdata[i].g22 == null || resdata[i].g22 === ""
                        || resdata[i].lx11 == null || resdata[i].lx11 === ""
                        || resdata[i].lx12 == null || resdata[i].lx12 === ""
                        || resdata[i].lx21 == null || resdata[i].lx21 === ""
                        || resdata[i].lx22 == null || resdata[i].lx22 === ""
                    ) {
                        $("#li_kgdz").css({"background-color": ""});
                        return;
                    }
                    $("#li_kgdz").css({"background-color": "#009688"});
                }
            }
        });

        // 编辑
        table.on('edit(kgdz)', function (obj) {         // 改
        });

        function editbz() {
            let beizhu = {
                tsid: tsid,
                jlKgdz: $("#kgdzbeizhu").val()
            };
            $.ajax({
                type: "POST",
                url: "${basePath}/ftu_beizhu/updateFtuBeizhuByPrimaryKey",
                data: JSON.stringify(beizhu),//必须
                contentType: "application/json;charsetset=UTF-8",//必须
                dataType: "json",//必须
                success: function (data) {
                }
            });
        }

//监听事件
        table.on('toolbar(kgdz)', function (obj) {      // 改
            switch (obj.event) {
                // 提交
                case 'SUBMIT':
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/ftu_jl_kgdz/updateBatch",              // 改
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
    });
</script>
</body>
</html>
