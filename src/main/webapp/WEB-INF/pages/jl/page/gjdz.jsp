<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/5/27
  Time: 10:40 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br>
<span style="">过流告警定值:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二次<input id="glgj1" type="text"/> A <input id="glgj2" type="text"/>ms； CT变比：<input id="glgj3" type="text"/></span><br>
<span>过流告警测试值：二次<input id="glcs1" type="text"/> A <input id="glcs2" type="text"/>ms；</span><br>
<span>零序告警定值:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二次<input id="lxgj1" type="text"/> A <input id="lxgj2" type="text"/>ms； CT变比：<input id="lxgj3" type="text"/></span><br>
<span>零序告警测试值：二次<input id="lxcs1" type="text"/> A <input id="lxcs2" type="text"/>ms；</span>
<button id="gjdz" type="button" class="layui-btn layui-btn-sm">保存</button>
</body>
<%-- 告警定值 --%>
<script>
    $(function () {
        // 开启自动保存（自动保存告警定值）
        setInterval(function () {
            let gjdz = {
                tsid: tsid,
                glgj1: $("#glgj1").val(),
                glgj2: $("#glgj2").val(),
                glgj3: $("#glgj3").val(),
                glcs1: $("#glcs1").val(),
                glcs2: $("#glcs2").val(),
                lxgj1: $("#lxgj1").val(),
                lxgj2: $("#lxgj2").val(),
                lxgj3: $("#lxgj3").val(),
                lxcs1: $("#lxcs1").val(),
                lxcs2: $("#lxcs2").val()
            };
            $.ajax({
                type: "POST",
                url: "${basePath}/gjdz/updateByPrimaryKey",              // 改
                data: JSON.stringify(gjdz),//必须
                contentType: "application/json;charsetset=UTF-8",//必须
                dataType: "json",//必须
                success: function (data) {
                    if (data.code !== 0) {
                        console.log("gjdz automatically saved failed!");
                    }
                }
            });
        }, 10000);
    });

    // 提交告警定值
    $("#gjdz").click(function () {
        let gjdz = {
            tsid: tsid,
            glgj1: $("#glgj1").val(),
            glgj2: $("#glgj2").val(),
            glgj3: $("#glgj3").val(),
            glcs1: $("#glcs1").val(),
            glcs2: $("#glcs2").val(),
            lxgj1: $("#lxgj1").val(),
            lxgj2: $("#lxgj2").val(),
            lxgj3: $("#lxgj3").val(),
            lxcs1: $("#lxcs1").val(),
            lxcs2: $("#lxcs2").val()
        };
        $.ajax({
            type: "POST",
            url: "${basePath}/gjdz/updateByPrimaryKey",              // 改
            data: JSON.stringify(gjdz),//必须
            contentType: "application/json;charsetset=UTF-8",//必须
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
</script>
</html>
