<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>基本信息——添加</title>
    <link rel="stylesheet" type="text/css" href="/style/wholemain.css"/>
    <script src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/sccj.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=KU3KGvuGAZBLbLai55AuWrQikAsqmC2g"></script>
</head>

<script>
    $(function () {
        var curr_time = new Date();
        var strDate = curr_time.getFullYear() + "-";
        strDate += curr_time.getMonth() + 1 + "-";
        strDate += curr_time.getDate() + " ";
        $('#tssj').datebox('setValue', strDate);
    });
</script>
<body>
<div class="right_main">
    <div class="right_table" style="width:96%;">
        <form name="myForm" id="myForm" method="post">
            <table width="100%" cellpadding="0" cellspacing="1" id="testTab" bgcolor="#c6c6c6">
                <tbody>
                <tr>
                    <th colspan="4" align="center" bgcolor="#f5f2ea">添加</th>
                </tr>
                <tr>
                    <th bgcolor="#FFFFFF">调试时间</th>
                    <td bgcolor="#FFFFFF" style="text-align:left; ">
                        <input class="easyui-datebox" name="tssj" id="tssj"
                               data-options="required:true" style="width:120px"/>
                    </td>
                    <th bgcolor="#FFFFFF"><p>安装地点及调度号</p></th>
                    <td bgcolor="#FFFFFF" style="text-align:left; ">
                        <input type="text" name="azddDdh" id="azddDdh" style="height:25px; border:1px solid #CCC;"/>
                    </td>
                </tr>
                <tr>
                    <th bgcolor="#FFFFFF"><p>所属线路</p></th>
                    <td bgcolor="#FFFFFF" style="text-align:left; ">
                        <select id="bdz"></select>&nbsp;<select id="xlmc"></select>
                        <input type="hidden" name="ssxl" id="ssxl" value=""/>
                    </td>
                    <th bgcolor="#FFFFFF"><p>路名</p></th>
                    <td bgcolor="#FFFFFF" style="text-align:left; ">
                        <input type="text" name="lm"  ondblclick="defaultClick(this)" id="lm" style="height:25px; border:1px solid #CCC;"/>
                    </td>
                </tr>
                <tr>
                        <th bgcolor="#FFFFFF"><p>工作负责人</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="gzfzr" id="gzfzr" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                    <th bgcolor="#FFFFFF"><p>工作人员</p></th>
                    <td bgcolor="#FFFFFF" style="text-align:left; ">
                        <input type="text" name="gzry"  ondblclick="defaultClick(this)" id="gzry" style="height:25px; border:1px solid #CCC;"/>
                    </td>
                </tr>
                <tr>
                    <th bgcolor="#FFFFFF"><p>审核人</p></th>
                    <td bgcolor="#FFFFFF" style="text-align:left; ">
                        <input type="text" name="shr"  ondblclick="defaultClick(this)" id="shr" style="height:25px; border:1px solid #CCC;"/>
                    </td>
                </tr>
                <tr>
                    <th bgcolor="#FFFFFF"><p>备注</p></th>
                    <td bgcolor="#FFFFFF" style="text-align:left; " colspan="3">
                        <textarea name="beizhu" id="beizhu" cols="100" rows="5" style="border:1px solid #CCC;"></textarea>
                    </td>
                </tr>
                <tr>
                    <td height="33" colspan="4" bgcolor="#f5f2ea">
                        <input type="button" name="button" value="提 交" onclick="commit()" class="iput_m"
                               style="height:25px;"/>
                        <input type="button" onclick="javascript:window.close();" name="button" value="取 消"
                               class="iput_m" style="height:25px;"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">
    // 变电站
    $("#bdz").append("<option value=''>全部</option>");
    $.get("/ssxl/selectByGroup/bdz", function (data) {
        data = JSON.parse(data);
        $.each(data, function (i) {
            $("#bdz").append("<option value='" + data[i].bdz + "'>" + data[i].bdz + "</option>");
        })
    });
    // 线路名称
    $("#xlmc").append("<option value=''>全部</option>");

    // 变电站更改时，线路名称随之更改
    $("#bdz").change(function () {
        if ($("#bdz").val() === '') {
            $("#xlmc").empty();
            $("#xlmc").append("<option value=''>全部</option>");
            return;
        }
        $("#xlmc").empty();
        $("#xlmc").append("<option value=''>全部</option>");
        $.get("/ssxl/selectByGroup/xlmc",{"bdz": $("#bdz").val()}, function (data) {
            data = JSON.parse(data);
            $.each(data, function (i) {
                $("#xlmc").append("<option value='" + data[i].id + "'>" + data[i].xlmc + "</option>");
            })
        });
    });

    function defaultClick(e){
        e.value="N/A";
    }

</script>

<script>
    function commit() {
        //验证必填字段
        if($("#tssj") == ""){
            alert("调试时间不能为空！");
            return;
        }
        if($("#azddDdh").val() == ""){
            alert("安装地点及调度号不能为空！");
            return;
        }
        $("#ssxl").val($("#xlmc").val());
        if($("#ssxl").val() == ""){
            alert("请选择所属线路！");
            return;
        }
        $.ajax({
            url: '/pwbhJbxx/addPwbhJbxx.action',
            type: 'POST',
            async: false,
            data: new FormData($("#myForm")[0]),
            processData: false,
            contentType: false,
            beforeSend: function () {
                console.log("正在加载，请稍候");
            },
            success: function (data) {
                alert(data.message);
                if (data.code == 0) {
                    window.opener.location.reload();
                    window.close();
                }
            },
            error: function () {
                alert("页面加载错误！");
            }
        });
    }
</script>
</body>
</html>
