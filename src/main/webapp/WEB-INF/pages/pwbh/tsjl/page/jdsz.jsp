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
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend id="mao4">4、校对时钟</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <span>检验要求: 将装置时钟校对至当前时钟，以便于系统故障时进行分析。</span><br>
        <label for="szjcjg">检查结果: <input id="szjcjg" type="text"/></label>
        <button id="commit" type="button" class="layui-btn layui-btn-sm">保存</button>
    </blockquote>
</div>
</body>
<script>
    $(function () {
        $.get("${basePath}/pwbh_beizhu/selectByPrimaryKey/" + tsid, function (data) {
                data = JSON.parse(data);
                $("#szjcjg").val(data.szjcjg);
            }
        );

        $("#commit").click(function () {
            let data = {
                "tsid": tsid,
                "szjcjg": $("#szjcjg").val()
            };
            $.ajax({
                type: 'POST',
                processData: false, // 告诉jquery不要处理数据
                data: JSON.stringify(data),//必须
                contentType: "application/json;charsetset=UTF-8",//必须
                dataType: "json",//必须
                url: '${basePath}/pwbh_beizhu/updateByPrimaryKey',			// 改
                success: function (data) {
                    if (data.code === 0) {
                        layer.msg(data.msg, {time: 1000, icon: 6});
                    } else {
                        layer.msg(data.msg, {time: 1000, icon: 5});
                    }
                }
            })
        });
    });
</script>
</html>
