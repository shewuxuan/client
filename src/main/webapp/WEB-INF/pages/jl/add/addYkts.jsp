<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/3/4
  Time: 6:09 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../import.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="${basePath}/static/layui/css/layui.css" media="all">
    <script type="text/javascript" src="${basePath}/static/js/jquery.min.js"></script>
    <script src="${basePath}/static/layui/layui.js" charset="utf-8"></script>
    <title>添加</title>
</head>
<body>
<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">间隔</label>
        <div class="layui-input-block">
            <input type="text" name="jg" autocomplete="off" placeholder="请输入内容" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div>
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="add">提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script>
    layui.use(['form'], function () {
        var form = layui.form;
        let index = parent.layer.getFrameIndex(window.name);
        //监听提交
        form.on('submit(add)', function (data) {
            let obj = data.field;
            obj.tsid = "${requestScope.tsid}";
            obj.ssqy = "${requestScope.ssqy}";
            // 判断输入内容不为空
            if (obj.jg == null || obj.jg === "") {
                parent.layer.msg("请输入内容", {time: 1500, anim: 6});
                return false;
            }
            $.ajax({
                type: "POST",
                url: "${basePath}/jlykts/insert",
                data: JSON.stringify(obj),//必须
                contentType: "application/json;charsetset=UTF-8",//必须
                dataType: "json",//必须
                success: function (data) {
                    if (data.code === 0) {
                        parent.layer.msg(data.msg, {time: 1500, icon: 6});
                    } else {
                        parent.layer.msg(data.msg, {time: 1500, icon: 5});
                    }
                    parent.layer.close(index);
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
