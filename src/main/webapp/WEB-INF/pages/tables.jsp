<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <title>评估方案管理</title>
    <link rel="stylesheet" type="text/css" href="/style/wholemain.css"/>
    <%--<link rel="Stylesheet" type="text/css" href="/style/main.css" />--%>
    <link rel="stylesheet" type="text/css" href="/css/fanganchuangjian.css"/>
    <link rel="stylesheet" type="text/css" href="/css/menu.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/style/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/style/easyui/themes/icon.css"/>
    <script type="text/javascript" src="/js/easyui-custom.js"></script>
    <script type="text/javascript" src="/js/easyui-datagrid.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
</head>
<style>
    .black_overlay {
        display: none;
        position: absolute;
        overflow-y: visible;
        bottom: 0;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: #dcdcdc;
        z-index: 1001;
        -moz-opacity: 1.2;
        opacity: .80;
        filter: alpha(opacity=88);
    }

    .white_content {
        overflow-y: visible;
        display: none;
        position: absolute;
        width: 80%;
        height: 60%;
        padding-left: 20px;
        z-index: 1002;
        /* overflow: auto;  */
    }

    .float-button {
        position: fixed;
        height: 30px;
        width: 50px;
        bottom: 90px;
        right: 50px;
        background: #1E9FFF;
    }
</style>
<body>
<div class="right_main">
    <div class="right_nav">
    </div>

    <div>
        <div class="tj">
            &nbsp; &nbsp;
            <input type="button" name="button" value="下载标准" class="iput_m" onclick="getStandard()"/>
            <input type="button" name="button" value="下载用户" class="iput_m" onclick="getUsers()">
        </div>

        <br/>
    </div>
    <div id="light" class="white_content">
        <div id="text1" style="text-align: center;margin:120px;"></div>
        <%-- <button onclick = "closeDialog()" class="float-button">关闭</button>--%>
    </div>
    <div id="fade" class="black_overlay"></div>
</div>
</body>
<script>
    var i = 0;

    function clocks() {
        document.getElementById('text1').innerHTML = '<b>正在下载  ' + i + ' 秒，请稍后。。。</b>';
        i = i + 1;
    }

    /*下载标准*/
    function getStandard() {
        if (confirm('确定重新下载所有标准吗？') == true) {
            // main.location.href = '/getStandard.action';
            document.getElementById('light').style.display = 'block';
            document.getElementById('fade').style.display = 'block';
            var times = setInterval(clocks, 1000);
            $.ajax({
                url: '/getStandard.action',
                type: 'POST',
                async: false,
                //data:getParam(),
                processData: false,
                contentType: false,

                complete: function () {
                    $.messager.progress('close');
                },
                success: function (data) {
                    window.clearInterval(times);
                    document.getElementById('text1').innerHTML = '<b>' + data.message + '</b>';
                    setTimeout(function () {
                            document.getElementById('light').style.display = 'none';
                            document.getElementById('fade').style.display = 'none';
                        }
                        , 3000);
                },
                error: function () {
                    alert("下载错误！");
                }
            });
        }
    }

    /*下载标准*/
    function getUsers() {
        if (confirm('确定重新下载所有用户信息吗？') == true) {
            // main.location.href = '/getStandard.action';
            document.getElementById('light').style.display = 'block';
            document.getElementById('fade').style.display = 'block';
            var times = setInterval(clocks, 1000);
            $.ajax({
                url: '/getUsers.action',
                type: 'POST',
                async: false,
                //data:getParam(),
                processData: false,
                contentType: false,
                success: function (data) {
                    window.clearInterval(times);
                    document.getElementById('text1').innerHTML = '<b>' + data.message + '</b>';
                    setTimeout(function () {
                            document.getElementById('light').style.display = 'none';
                            document.getElementById('fade').style.display = 'none';
                        }
                        , 3000);
                },
                error: function () {
                    alert("下载错误！");
                }
            });
        }
    }
</script>

</html>