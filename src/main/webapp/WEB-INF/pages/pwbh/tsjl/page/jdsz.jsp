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
        <label for="szjcjg">检查结果:
<%--            <input id="szjcjg" type="text"/>--%>
            <select id="szjcjg">
                <option value="-1"></option>
                <option value="1" selected>正确</option>
                <option value="0">错误</option>
                <option value="2">N/A</option>
            </select>
        </label>
        <button id="commit" type="button" class="layui-btn layui-btn-sm">提交</button>
    </blockquote>
</div>
</body>
<script>
    $(function () {
        $.get("${basePath}/pwbh_beizhu/selectByPrimaryKey/" + tsid, function (data) {
                data = JSON.parse(data);
                $("#szjcjg").val(data.szjcjg);
                if (data.szjcjg == null || data.szjcjg === "" || data.szjcjg == -1) {
                    $("#li_jdsz").css({"background-color": ""});
                } else {
                    $("#li_jdsz").css({"background-color": "#009688"});
                }
            }
        );
    });

    layui.use('layer', function(){
        let layer = layui.layer;
        $("#commit").click(function () {
            let record = {
                "tsid": tsid,
                "szjcjg": $("#szjcjg").val()
            };
            $.ajax({
                type: 'POST',
                processData: false, // 告诉jquery不要处理数据
                data: JSON.stringify(record),//必须
                contentType: "application/json;charsetset=UTF-8",//必须
                dataType: "json",//必须
                url: '${basePath}/pwbh_beizhu/updateByPrimaryKey',			// 改
                success: function (data) {
                    if (record.szjcjg == null || record.szjcjg === "" || record.szjcjg == -1) {
                        $("#li_jdsz").css({"background-color": ""});
                    } else {
                        $("#li_jdsz").css({"background-color": "#009688"});
                    }
                    if (data.code === 0) {
                        layer.msg('提交成功！', {time: 1000, icon: 6});
                    } else {
                        layer.msg('提交失败！', {time: 1000, icon: 5});
                    }
                }
            })
        });
    });
</script>
</html>
