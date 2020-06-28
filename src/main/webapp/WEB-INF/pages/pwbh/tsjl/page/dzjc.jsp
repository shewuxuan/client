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
    <legend id="mao3">10、定值检查</legend>
</fieldset>
<div class="layui-field-box">
    <blockquote class="layui-elem-quote" style="width: 90%; margin: auto;">
        <label for="g1">（1）过流Ⅰ段定值 <input id="g1" type="text"/>，</label>
        <label for="g2">过流Ⅱ段定值<input id="g2" type="text"/>。</label>
        <br>
        <label for="g11">过流Ⅰ段时间<input id="g11" type="text"/>秒，</label>
        <label for="g12">通电<input id="g12" type="text"/>秒。</label>
        <label for="g21">过流ⅠI段时间<input id="g21" type="text"/>秒，</label>
        <label for="g22">通电<input id="g22" type="text"/>秒。</label>


        <br>
        <label for="l1">（2）零序Ⅰ段定值 <input id="l1" type="text"/>，</label>
        <label for="l2">零序Ⅱ段定值<input id="l2" type="text"/>。</label>
        <br>
        <label for="l11">零序Ⅰ段时间<input id="l11" type="text"/>秒，</label>
        <label for="l12">通电<input id="l12" type="text"/>秒。</label>
        <label for="l21">零序ⅠI段时间<input id="l21" type="text"/>秒，</label>
        <label for="l22">通电<input id="l22" type="text"/>秒。</label>

        <%--        <table id="dzjcgl" lay-filter="dzjcgl"></table>--%>

        <%@include file="dzjcgl.jsp" %>
        <%@include file="dzjclx.jsp" %>

        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="dzjcbeizhu" placeholder="" class="layui-textarea"></textarea>
                </div>
            </div>
        </form>
    </blockquote>
</div>
</body>
</html>
