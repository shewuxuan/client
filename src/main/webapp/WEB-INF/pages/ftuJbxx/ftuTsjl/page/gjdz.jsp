<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/5/19
  Time: 3:48 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>（1）过流Ⅰ段定值<input id="gl1ddz" type="text">, 过流Ⅱ段定值<input id="gl2ddz" type="text">；</h4>
<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;过流Ⅰ段时间<input id="gl1dsj" type="text">秒,通电<input id="gl1dtd" type="text">秒；</h4>
<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;过流Ⅱ段时间<input id="gl2dsj" type="text">秒，通电<input id="gl2dtd" type="text">秒。</h4>
<h4>（2）零序过流Ⅰ段定值<input id="lx1ddz" type="text">, 零序过流Ⅱ段定值<input id="lx2ddz" type="text">；</h4>
<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;零序Ⅰ段时间<input id="lx1dsj" type="text">秒，通电<input id="lx1dtd" type="text">秒；</h4>
<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;零序Ⅱ段时间<input id="lx2dsj" type="text">秒，通电<input id="lx2dtd" type="text">秒。</h4>
<input id="gjdz" type="button" value="保存">
</body>
<%-- 告警定值 --%>
<script>
    // 判断是否数值
    $("#gl1ddz,#gl1dsj,#gl1dtd,#gl2ddz,#gl2dsj,#gl2dtd,#lx1ddz,#lx1dsj,#lx1dtd,#lx2ddz,#lx2dsj,#lx2dtd").blur(function () {
        let that = $(this);
        let pattern = new RegExp(/^(-?\d+)(\.\d+)?$/);
        if (!pattern.test(that.val()) && that.val() !== '' && that.val() != null) {
            layer.msg('请检查输入是否数值！', {icon: 0}, function () {
            });
            that.focus().select();
        }
    });
    $(function () {
        // 开启自动保存（自动保存告警定值）
        setInterval(function () {
            let gjdz = {
                id: tsid,
                gl1ddz: $("#gl1ddz").val(),
                gl1dsj: $("#gl1dsj").val(),
                gl1dtd: $("#gl1dtd").val(),
                gl2ddz: $("#gl2ddz").val(),
                gl2dsj: $("#gl2dsj").val(),
                gl2dtd: $("#gl2dtd").val(),
                lx1ddz: $("#lx1ddz").val(),
                lx1dsj: $("#lx1dsj").val(),
                lx1dtd: $("#lx1dtd").val(),
                lx2ddz: $("#lx2ddz").val(),
                lx2dsj: $("#lx2dsj").val(),
                lx2dtd: $("#lx2dtd").val()
            };
            $.ajax({
                type: "POST",
                url: "${basePath}/ftu_jl_gjdz/updateByPrimaryKey",              // 改
                data: JSON.stringify(gjdz),//必须
                contentType: "application/json;charsetset=UTF-8",//必须
                dataType: "json",//必须
                success: function (data) {
                    if (data.code === 0) {
                        console.log("gjdz automatically saved successfully!");
                    } else {
                        console.log("gjdz automatically saved failed!");
                    }
                }
            });
        }, 30000);
    });

    // 提交告警定值
    $("#gjdz").click(function () {
        let gjdz = {
            id: tsid,
            gl1ddz: $("#gl1ddz").val(),
            gl1dsj: $("#gl1dsj").val(),
            gl1dtd: $("#gl1dtd").val(),
            gl2ddz: $("#gl2ddz").val(),
            gl2dsj: $("#gl2dsj").val(),
            gl2dtd: $("#gl2dtd").val(),
            lx1ddz: $("#lx1ddz").val(),
            lx1dsj: $("#lx1dsj").val(),
            lx1dtd: $("#lx1dtd").val(),
            lx2ddz: $("#lx2ddz").val(),
            lx2dsj: $("#lx2dsj").val(),
            lx2dtd: $("#lx2dtd").val()
        };
        $.ajax({
            type: "POST",
            url: "${basePath}/ftu_jl_gjdz/updateByPrimaryKey",              // 改
            data: JSON.stringify(gjdz),//必须
            contentType: "application/json;charsetset=UTF-8",//必须
            dataType: "json",//必须
            success: function (data) {
                if (data.code === 0) {
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
    });
</script>
</html>
