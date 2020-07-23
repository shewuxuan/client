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
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <label for="gl1ddz">（1）过流Ⅰ段定值 <input id="gl1ddz" style="text-align: center" type="text"/>，</label>
        <label for="gl2ddz">过流Ⅱ段定值<input id="gl2ddz" style="text-align: center" type="text"/>。</label>
        <br>
        <label for="gl1dsj" style="margin-left: 36px;">过流Ⅰ段时间<input id="gl1dsj" style="text-align: center" type="text"/>秒，</label>
        <label for="gl1dtd">通电<input id="gl1dtd" style="text-align: center" type="text"/>秒。</label>
        <br>
        <label for="gl2dsj" style="margin-left: 36px;">过流ⅠI段时间<input id="gl2dsj" style="text-align: center" type="text"/>秒，</label>
        <label for="gl2dtd">通电<input id="gl2dtd" style="text-align: center" type="text"/>秒。</label>
        <button id="" name="commit" type="button" class="layui-btn layui-btn-sm">提交</button>
        <table id="gjtsgl" lay-filter="gjtsgl"></table>

        <br>

        <label for="lx1ddz">（2）零序Ⅰ段定值 <input id="lx1ddz" style="text-align: center" type="text"/>，</label>
        <label for="lx2ddz">零序Ⅱ段定值<input id="lx2ddz" style="text-align: center" type="text"/>。</label>
        <br>
        <label for="lx1dsj" style="margin-left: 36px;">零序Ⅰ段时间<input id="lx1dsj" style="text-align: center" type="text"/>秒，</label>
        <label for="lx1dtd">通电<input id="lx1dtd" style="text-align: center" type="text"/>秒。</label>
        <br>
        <label for="lx2dsj" style="margin-left: 36px;">零序ⅠI段时间<input id="lx2dsj" style="text-align: center" type="text"/>秒，</label>
        <label for="lx2dtd">通电<input id="lx2dtd" style="text-align: center" type="text"/>秒。</label>
        <button id="" name="commit" type="button" class="layui-btn layui-btn-sm">提交</button>
        <table id="gjtslx" lay-filter="gjtslx"></table>

        <%@include file="gjtsgl.jsp"%>
        <%@include file="gjtslx.jsp"%>

        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="gjtsbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>

        <%@include file="tzyb.jsp"%>
    </blockquote>
</div>

<script>

    $(function () {
        // 告警定值赋值
        $.get("${basePath}/ftu_jl_gjdz/selectByPrimaryKey/" + tsid, function (data) {
            data = JSON.parse(data);
            $("#gl1ddz").val(data.gl1ddz);
            $("#gl1dsj").val(data.gl1dsj);
            $("#gl1dtd").val(data.gl1dtd);
            $("#gl2ddz").val(data.gl2ddz);
            $("#gl2dsj").val(data.gl2dsj);
            $("#gl2dtd").val(data.gl2dtd);
            $("#lx1ddz").val(data.lx1ddz);
            $("#lx1dsj").val(data.lx1dsj);
            $("#lx1dtd").val(data.lx1dtd);
            $("#lx2ddz").val(data.lx2ddz);
            $("#lx2dsj").val(data.lx2dsj);
            $("#lx2dtd").val(data.lx2dtd);
        });

    //    告警定值自动保存
        setInterval(function () {
            let data = {
                "id": tsid,
                "gl1ddz": $("#gl1ddz").val(),
                "gl1dsj": $("#gl1dsj").val(),
                "gl1dtd": $("#gl1dtd").val(),
                "gl2ddz": $("#gl2ddz").val(),
                "gl2dsj": $("#gl2dsj").val(),
                "gl2dtd": $("#gl2dtd").val(),
                "lx1ddz": $("#lx1ddz").val(),
                "lx1dsj": $("#lx1dsj").val(),
                "lx1dtd": $("#lx1dtd").val(),
                "lx2ddz": $("#lx2ddz").val(),
                "lx2dsj": $("#lx2dsj").val(),
                "lx2dtd": $("#lx2dtd").val()
            }
            $.ajax({
                type: "POST",
                url: "${basePath}/ftu_jl_gjdz/updateByPrimaryKey",
                data: JSON.stringify(data),//必须
                contentType: "application/json;charset=UTF-8",//必须
                dataType: "json",//必须
                success: function (data) {
                    if (data.code !== 0) {
                        console.log("dz automatically saved failed!");
                    }
                }
            });
        }, 10000);
    })

    // 提交告警定值
    $("button[name='commit']").click(function () {
        let data = {
            "id": tsid,
            "gl1ddz": $("#gl1ddz").val(),
            "gl1dsj": $("#gl1dsj").val(),
            "gl1dtd": $("#gl1dtd").val(),
            "gl2ddz": $("#gl2ddz").val(),
            "gl2dsj": $("#gl2dsj").val(),
            "gl2dtd": $("#gl2dtd").val(),
            "lx1ddz": $("#lx1ddz").val(),
            "lx1dsj": $("#lx1dsj").val(),
            "lx1dtd": $("#lx1dtd").val(),
            "lx2ddz": $("#lx2ddz").val(),
            "lx2dsj": $("#lx2dsj").val(),
            "lx2dtd": $("#lx2dtd").val()
        }
        $.ajax({
            type: "POST",
            async: false,
            url: "${basePath}/ftu_jl_gjdz/updateByPrimaryKey",
            data: JSON.stringify(data),//必须
            contentType: "application/json;charset=UTF-8",//必须
            dataType: "json",//必须
            success: function (data) {
                if (data.code === 0) {
                    layer.msg(data.msg, {time: 1000, icon: 6});
                } else {
                    layer.msg(data.msg, {time: 2000, icon: 5})
                }
            }
        });
    });

    // 告警调试备注
    function editbz() {
        let beizhu = {
            tsid: tsid,
            jlGjts: $("#gjtsbeizhu").val()
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
</script>

<%-- 告警调试导航栏背景--%>
<script>
    var gjtsgl, gjtslx, tzyb = false;
    function bgcolor(gjtsgl, gjtslx, tzyb) {
        if (gjtsgl && gjtslx && tzyb) {
            $("#li_gjts").css({"background-color": "#009688"});
            return;
        }
        $("#li_gjts").css({"background-color": ""});
    }
</script>
</body>
</html>
