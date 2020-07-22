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
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=KU3KGvuGAZBLbLai55AuWrQikAsqmC2g"></script>
    <script type="text/javascript" src="/js/sccj.js"></script>
</head>
<style>
    #searchresult
    {
        position: absolute;
        z-index: 1;
        overflow: hidden;
        background: #f0f0f0;
        border-top: none;
    }
    .sccs
    {
        width: 170px;
        font-size:15px;
        height:20px;
    }
    .line
    {
        font-size: 12px;
        background: #E0E0E0;
        width: 140px;
    }
    .hover
    {
        background: #007ab8;
        width: 140px;
        color: #fff;
    }
    .std
    {
        width: 165px;
        height: 20px;
    }
</style>
<script>
    $(function () {
        var curr_time = new Date();
        var strDate = curr_time.getFullYear() + "-";
        strDate += curr_time.getMonth() + 1 + "-";
        strDate += curr_time.getDate() + " ";
        var tssj = '${jbxx}';
        if (tssj == '' || tssj == null) {
            $('#tssj').datebox('setValue', strDate);
            $('#ysrq').datebox('setValue', strDate);
            $('#kgScrq').datebox('setValue', strDate);
            $('#ftuScrq').datebox('setValue', strDate);
            $('#txsbScrq').datebox('setValue', strDate);
        }
    });
</script>
<body>
<div class="right_main">
    <div class="right_table" style="width:96%;">
        <form name="myForm" id="myForm" method="post">
            <table width="100%" cellpadding="0" cellspacing="1" bgcolor="#c6c6c6" id="testTab">
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
                        <th bgcolor="#FFFFFF"><p>FTU型号</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="ftuXh" id="ftuXh" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF"><p>装置类型</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <select name="zzlx" id="zzlx" style="height:25px; border:1px solid #CCC;">
                            </select>
                        </td>
                        <th bgcolor="#FFFFFF"><p>FTU编码</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="ftuBm" id="ftuBm" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF"><p>调度编号</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="ddbh" id="ddbh"  ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                        <th bgcolor="#FFFFFF"><p>FTU厂家</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="ftuCj" id="txmkcj" class="sccs" onclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                            <div id="searchresult" style="display: none;margin-top:10px;">
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF"><p>线路名称</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="xlmc" id="xlmc" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                        <th bgcolor="#FFFFFF"><p>FTU生产日期</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input class="easyui-datebox" name="ftuScrq" id="ftuScrq"
                                   data-options="required:true" style="width:120px"/>
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF"><p>无线卡号</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="wxkh" id="wxkh" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                        <th bgcolor="#FFFFFF"><p>开关本体型号</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="kgXh" id="kgXh" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF"><p>IP地址</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="ipdz" id="ipdz" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                        <th bgcolor="#FFFFFF"><p>开关本体厂家</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="kgCj" id="sccs" ondblclick="defaultClick(this)" class="sccs" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF"><p>验收人员</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="ysry" id="ysry" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                        <th bgcolor="#FFFFFF"><p>开关本体生产日期</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input class="easyui-datebox" name="kgScrq" id="kgScrq"
                                   data-options="required:true" style="width:120px"/>
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF"><p>验收单位</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="ysdw" id="ysdw" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                        <th bgcolor="#FFFFFF"><p>通讯设备型号</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="txsbXh" id="txsbXh" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF"><p>验收日期</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input class="easyui-datebox" name="ysrq" id="ysrq" ondblclick="defaultClick(this)"
                                   data-options="required:true" style="width:120px"/>
                        </td>
                        <th bgcolor="#FFFFFF"><p>通讯设备厂家</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="txsbCj" id="sccsYcg" ondblclick="defaultClick(this)" class="sccs" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF"><p>相间CT变比</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="kgXjbb" id="kgXjbb" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                        <th bgcolor="#FFFFFF"><p>通讯设备生产日期</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input class="easyui-datebox" name="txsbScrq" id="txsbScrq"
                                   data-options="required:true" style="width:120px"/>
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF"><p>零序CT变比</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="kgLxbb" id="kgLxbb" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                        <th bgcolor="#FFFFFF"><p>调试人员姓名</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; ">
                            <input type="text" name="tsryxm" id="tsryxm" ondblclick="defaultClick(this)" style="height:25px; border:1px solid #CCC;"/>
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF">添加照片</th>
                        <td bgcolor="#FFFFFF" colspan="3" style="text-align: left;">&nbsp;
                            <input type="button" value="添加"  onclick="addTr(13);" class="iput_m" />
                        </td>
                    </tr>
                    <tr>
                        <th bgcolor="#FFFFFF" style="width: 20%"><p>百度经纬度</p></th>
                        <td bgcolor="#FFFFFF" style="text-align:left; " colspan="3">
                            <span class="label">腾讯地图经度</span>
                            <input type="text" name="t_jd" id="t_jd" style="height:25px; border:1px solid #CCC;"/>
                            <span class="label">腾讯地图纬度</span>
                            <input type="text" name="t_wd" id="t_wd" style="height:25px; border:1px solid #CCC;"/>
                            <input type="button" onclick="switchXY()" value="确 定" class="iput_m"
                                   style="height:25px;"/>
                            <br/> <br/>
                            <span class="label">百度地图经度</span>
                            <input type="text" name="jd" id="jd" style="height:25px; border:1px solid #CCC;"/>
                            <span class="label">百度地图纬度</span>
                            <input type="text" name="wd" id="wd" style="height:25px; border:1px solid #CCC;"/>
                            <span class="label">详细位置</span>
                            <input type='text' name='xxwz' id='xxwz' size="30" style="height:25px; border:1px solid #CCC;">
                            <div class="main-div" style="height: 426px;width: 800px;margin-top: 5px;">
                                <div id='allmap' style='width:  75%; height:400px; position: absolute;'></div>
                            </div>
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
            <div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
                <div id="innerdiv" style="position:absolute;">
                    <img id="bigimg" style="border:1px solid #fff;" src="" />
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function defaultClick(e){
        e.value="N/A";
    }
    //放大图片
    function imgMaxView(e) {
        var _this = $(e);
        var src = _this.attr("src");
        imgShow("#outerdiv", "#innerdiv", "#bigimg", src);
    }

    function imgShow(outerdiv, innerdiv, bigimg, src){
        $(bigimg).attr("src", src);
        $("<img/>").attr("src", src).load(function(){
            var windowW = $(window).width();
            var windowH = $(window).height();
            var realWidth = this.width;
            var realHeight = this.height;
            var imgWidth, imgHeight;
            var scale = 0.8;

            if(realHeight>windowH*scale) {
                imgHeight = windowH*scale;
                imgWidth = imgHeight/realHeight*realWidth;
                if(imgWidth>windowW*scale) {
                    imgWidth = windowW*scale;
                }
            } else if(realWidth>windowW*scale) {
                imgWidth = windowW*scale;
                imgHeight = imgWidth/realWidth*realHeight;
            } else {
                imgWidth = realWidth;
                imgHeight = realHeight;
            }
            $(bigimg).css("width",imgWidth);

            var w = (windowW-imgWidth)/2;
            var h = (windowH-imgHeight)/2;
            $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
            $(outerdiv).fadeIn("fast");
        });

        $(outerdiv).click(function(){
            $(this).fadeOut("fast");
        });
    }
    function previewImage(file, prvid,imgName) {
        var tip = "请选择图片格式的文件!"; // 设定提示信息
        var filters = {
            "jpeg" : "/9j/4",
            "gif" : "R0lGOD",
            "png" : "iVBORw"
        }
        var prvbox = document.getElementById(prvid);
        prvbox.innerHTML = "";
        if (window.FileReader) { // html5方案
            for (var i = 0, f; f = file.files[i]; i++) {
                var fr = new FileReader();
                fr.onload = function(e) {
                    var src = e.target.result;
                    if (!validateImg(src)) {
                        alert(tip)
                    } else {
                        showPrvImg(src);
                    }
                }
                fr.readAsDataURL(f);
            }
        } else { // 降级处理

            if (!/\.jpg$|\.png$|\.gif$/i.test(file.value)) {
                alert(tip);
            } else {
                showPrvImg(file.value);
            }
        }

        function validateImg(data) {
            var pos = data.indexOf(",") + 1;
            for ( var e in filters) {
                if (data.indexOf(filters[e]) === pos) {
                    return e;
                }
            }
            return null;
        }

        function showPrvImg(src) {
            var img = document.createElement("img");
            img.src = src;
            img.height = 100;
            prvbox.appendChild(img);
            $(img).bind("click",function(){
                imgMaxView(this);
            });
        }
    }
    //添加照片
    var id=1;
    function addTr(index) {
        var tb=document.getElementById('testTab');
        var newTr = tb.insertRow(index);//在最下的位置
        newTr.height=500;
        newTr.id='tr'+id;
        var newTd0 = newTr.insertCell();
        var newTd1 = newTr.insertCell();
        var newTd2 = newTr.insertCell();
        var newTd3 = newTr.insertCell();
        newTd0.innerHTML="<p style='font-size: 12px;font-weight: bold;color: #333;font-family: \"宋体\";'>图片名称</p>";
        newTd1.innerHTML="<input type='text' name='imgName' id='imgName"+id+"' style='height:25px; border:1px solid #CCC;float: left;'/>";
        newTd2.innerHTML= "<input style='float: left;margin: 40px 10px;width: 180px;' id='file"+id+"' name='file' type=file onchange=\"previewImage(this, 'prvid"+id+"','imgName"+id+"')\">";
        newTd3.innerHTML= "<div style='height:100px;float: left;margin-left: 60px;' id='prvid"+id+"'></div><button class='iput_m'onclick=\"moveTr('"+id+"');\" style='float: right;margin-left:-50px;margin-top: 60px;'>移除</button>";
        id++;
    }
    function moveTr(id){
        var tb=document.getElementById('testTab');
        var tr=document.getElementById('tr'+id);
        tb.deleteRow(tr.rowIndex);
    }
</script>

<script>

    $(function () {
        var formdata = new FormData(); // 模拟表单对象
        $.ajax({
            url: '/jbxx/selectZzlxAll.action',
            type: 'POST',
            async: false,
            data: formdata,
            processData: false,
            contentType: false,
            beforeSend: function () {
                console.log("正在加载，请稍候");
            },
            success: function (result) {
                $.each(result, function (index, value) {
                    var oldZzlx = $("#oldZzlx").val();
                    if (oldZzlx == value.keyvalue) {
                        $("#zzlx").append("<option value='" + value.keyvalue + "' selected>" + value.keymemo + "</option>");
                    } else {
                        $("#zzlx").append("<option value='" + value.keyvalue + "' >" + value.keymemo + "</option>");
                    }
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            },
            async: false             //false表示同步
        });
    });

    function commit() {
        //验证必填字段
        if($("#ddbh").val() == ""){
            alert("调度编号不能为空！");
            return;
        }
        if($("#zzlx").val() == ""){
            alert("装置类型不能为空！");
            return;
        }
        $.ajax({
            url: '/ftuJbxx/addFtuJbxx.action',
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

    function change() {
        $.ajax({
            url: '/ftuJbxx/updateFtuJbxx.action',
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

    //调用百度api
    var map = new BMap.Map("allmap");
    var geoc = new BMap.Geocoder();  //地址解析对象
    var markersArray = [];
    var geolocation = new BMap.Geolocation();
    var point = new BMap.Point(116.331398, 39.897445);
    map.centerAndZoom(point, 15); // 中心点
    geolocation.getCurrentPosition(function (r) {
        if (this.getStatus() == BMAP_STATUS_SUCCESS) {
            var mk = new BMap.Marker(r.point);
            // map.addOverlay(mk);
            //map.panTo(r.point);
            map.enableScrollWheelZoom(true);
        }
        else {
            alert('failed' + this.getStatus());
        }
    }, {enableHighAccuracy: true})
    map.addEventListener("click", showInfo);
    //清除标识
    function clearOverlays() {
        if (markersArray) {
            for (i in markersArray) {
                map.removeOverlay(markersArray[i])
            }
        }
    }
    //地图上标注
    function addMarker(point) {
        var marker = new BMap.Marker(point);
        markersArray.push(marker);
        clearOverlays();
        map.addOverlay(marker);
    }
    //点击地图时间处理
    function showInfo(e) {
        document.getElementById('jd').value = e.point.lng;
        document.getElementById('wd').value = e.point.lat;
        geoc.getLocation(e.point, function (rs) {
            var addComp = rs.addressComponents;
            var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
            document.getElementById('xxwz').value = address;
        });
        addMarker(e.point);
    }

    //缩放按钮
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
    var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
    //缩放控件type有四种类型:
    map.addControl(top_left_control);
    map.addControl(top_left_navigation);
    map.addControl(top_right_navigation);

    // 添加定位控件
    var geolocationControl = new BMap.GeolocationControl();
    geolocationControl.addEventListener("locationSuccess", function(e){
        // 定位成功事件
        var address = '';
        address += e.addressComponent.province;
        address += e.addressComponent.city;
        address += e.addressComponent.district;
        address += e.addressComponent.street;
        address += e.addressComponent.streetNumber;

        document.getElementById('jd').value = e.point.lng;
        document.getElementById('wd').value = e.point.lat;
        document.getElementById('xxwz').value = address;
        //alert("当前定位地址为：" + address);
    });
    geolocationControl.addEventListener("locationError",function(e){
        // 定位失败事件
        alert(e.message);
    });
    //添加定位到地图
    map.addControl(geolocationControl);

    //中国正常GCJ02坐标---->百度地图BD09坐标
    function convertGCJ02ToBD09($lat, $lng)
    {
        $xPi   = 3.14159265358979324 * 3000.0 / 180.0;
        $x     = $lng;
        $y     = $lat;
        $z     = Math.sqrt($x * $x + $y * $y) + 0.00002 * Math.sin($y * $xPi);
        $theta = Math.atan2($y, $x) + 0.000003 * Math.cos($x * $xPi);
        $lng   = $z * Math.cos($theta) + 0.0065;
        $lat   = $z * Math.sin($theta) + 0.006;

        $arr = [];
        $arr['lng'] = $lng;
        $arr['lat'] = $lat;

        return $arr;
    }
    function switchXY() {
        var inputJd = $("#t_jd").val();
        var inputWd = $("#t_wd").val();

        if(inputJd!='' && inputWd!=''){
            $arr = [];
            $arr = convertGCJ02ToBD09(inputWd,inputJd);
            deletePoint();
            map.clearOverlays();
            var point = new BMap.Point($arr.lng,$arr.lat);
            map.centerAndZoom(point, 15);
            addMarker(point);
            $("#jd").val($arr.lng);
            $("#wd").val($arr.lat);
            $("#xxwz").val("");
        }
    }
    // $("#jd").blur(function () {switchXY();});
    //$("#wd").blur(function () {switchXY();});

    //删除标注
    function deletePoint(){
        var allOverlay = map.getOverlays();
        for (var i = 0; i < allOverlay.length -1; i++){
            //if(allOverlay[i].getLabel().content == "我是id=1"){
            map.removeOverlay(allOverlay[i]);
            //}
        }
    }
</script>
</body>
</html>
