<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/2/13
  Time: 10:58 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/demo.css">
    <script type="text/javascript" src="${basePath}/static/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="${basePath}/static/style/wholemain.css">
    <script type="text/javascript" src="${basePath}/static/js/easyui-lang-zh_CN.js"></script>
    <title></title>
    <style>
        /* 文本超长class */
        .textEllipsis {
            /*color:red;!*颜色*!*/
            overflow: hidden; /*不允许滚动条*/
            white-space: nowrap; /*不允许文本换行*/
            text-overflow: ellipsis; /*文本超长显示省略号*/
        }

        /* 鼠标移上,显示全文class */
        .textEllipsis:hover {
            height: auto;
            word-break: break-all;
            white-space: pre-wrap;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div style="width: 100%">
        <div style="margin-top: 20px; width: 100%;">
            <span style="font-size: 18px; color: black;">2、DTU外观结构及配置</span>
        </div>
        <div style="width: 95%; margin: 0 auto;">
            <table id="wgpz" ></table>
            <textarea id="wgpzbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
        </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">3、回路测试</span><br>
        <span style="font-size: 18px; color: black;">3.1绝缘电阻测试</span><br>
        <span style="margin-left: 25px;">用500伏摇表按下表测量绝缘电阻，摇测完毕后,将各回路对地放电:</span>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="jydz"></table>
        <textarea id="jydzbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">3.2 CT回路电阻测试（测量值应不大于0.2Ω）</span><br>
        <span style="margin-left: 25px;">测量步骤：</span><br>
        <span style="margin-left: 25px;">（1）测试前，断开开关柜二次柜内的CT连接片，分别在端子排上口（一般为装置侧）和下口（一般为CT侧）进行测试。</span><br>
        <span style="margin-left: 25px;">（2）测试后，及时恢复CT连接片，防止开路。</span>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="hldz"></table>
        <textarea id="hldzbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">4、电源性能测试</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="dyxn"></table>
        <textarea id="dyxnbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">5、电流、电压零漂校验（测量值在0.1%以内）</span><br>
        <span style="margin-left: 25px;">测量步骤：</span><br>
        <span style="margin-left: 25px;">在未加电流和电压的时候，观察并记录DTU管理软件显示的实时数据。</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="lpjy"></table>
        <textarea id="lpjybeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">6、采样精度测试（测量值在0.5%以内）</span><br>
        <span style="margin-left: 25px;">测量步骤：</span><br>
        <span style="margin-left: 25px;"> 继保仪Ia、In输出电流接开关柜的A、C相及零序电流试验端子，测试中满度值采用额定电流5A，半度值采用2.5A 。</span><br>
        <span style="margin-left: 25px;"> （1）满度测量值应在4.975~5.025A以内</span><br>
        <span style="margin-left: 25px;"> （2）半度测量值应在2.488~2.513A以内</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="cyjd"></table>
        <textarea id="cyjdbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">7、模拟CT一次电流验证回路</span><br>
        <span style="margin-left: 25px;">测量步骤：</span><br>
        <span style="margin-left: 25px;"> （1）在全部A、C相和零序CT缠绕绝缘导线，在相间CT上缠绕三圈，在零序CT缠绕两圈。</span><br>
        <span style="margin-left: 25px;"> （2）继保仪Ia、In输出10A电流，分别用鳄鱼夹接导线两端。</span><br>
        <span style="margin-left: 25px;"> （3）DTU二次值读数乘以CT变比，将计算值填入下表。</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="dlhl"></table>
        <textarea id="dlhlbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">8、功能调试</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="gncs"></table>
        <textarea id="gncsbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">9、CT安装工艺检查</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="ctgy"></table>
        <textarea id="ctgybeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">10、遥信功能调试</span><br>
        <span style="font-size: 18px; color: black;">10.1 开关柜</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="kgg"></table>
        <span style="text-align: center; display: block;">
            1#箱体气压低告警：<input id="qygj1" readonly type="text" style="text-align: center;"/>
            2#箱体气压低告警：<input id="qygj2" readonly type="text" style="text-align: center;"/>
        </span>
        <textarea id="kggbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">10.2 DTU</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="dtu"></table>
        <textarea id="dtubeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">10.3 溢水报警装置</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="ysbj"></table>
        <textarea id="ysbjbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div id="div_xb">
        <div style="margin-top: 20px; width: 100%;">
            <span style="font-size: 18px; color: black;">10.4 箱变</span><br>
        </div>
        <div style="width: 95%; margin: 0 auto;">
            <table id="xb"></table>
            <textarea id="xbbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
        </div>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">11、遥控功能调试</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="ykts"></table>
        <textarea id="yktsbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">12、遥测功能调试</span><br>
        <span style="margin-left: 25px;">测量步骤：</span><br>
        <span style="margin-left: 25px;">DTU一般监测母线电压Uac，测试时在电压接线端子A、 C 两相同时加57.735V，此时Uac线电压为100V，主站系数为0.1，母线电压为10KV。</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="ycts"></table>
        <textarea id="yctsbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">13、告警功能调试</span><br>
        <span style="margin-left: 25px;">测量步骤：</span><br>
        <span style="margin-left: 25px;">（1）在DTU设置过流和零序告警定值。</span><br>
        <span style="margin-left: 25px;">（2）在保护装置设置过流和零序定值，并投入跳闸功能。</span><br>
        <span style="margin-left: 25px;">（3）分别在测量CT、保护CT和零序CT端子上加电流，记录主站收到的一次电流值。记录是否收到过流告警、零序告警、过流保护动作出口告警、零序保护动作出口告警，要求出现以上告警时必须伴随线路总告警。</span><br>
        <span style="margin-left: 25px;">过流告警定值：二次<input id="glgj1" readonly type="text" style="text-align: center;"/> A <input id="glgj2" readonly type="text" style="text-align: center;"/>ms； CT变比：<input id="glgj3" readonly type="text" style="text-align: center;"/></span>
        <span>过流告警测试值：二次<input id="glcs1" readonly type="text" style="text-align: center;"/> A <input id="glcs2" readonly type="text" style="text-align: center;"/>ms；</span><br>
        <span style="margin-left: 25px;">零序告警定值：二次<input id="lxgj1" readonly type="text" style="text-align: center;"/> A <input id="lxgj2" readonly type="text" style="text-align: center;"/>ms； CT变比：<input id="lxgj3" readonly type="text" style="text-align: center;"/></span>
        <span>零序告警测试值：二次<input id="lxcs1" readonly type="text" style="text-align: center;"/> A <input id="lxcs2" readonly type="text" style="text-align: center;"/>ms；</span>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="gjcs1"></table>
        <br>
        <table id="gjcs2"></table>
        <textarea id="gjcsbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>
</div>
<div style="margin-bottom: 30px;">
<%--    <span style="color: #d0e9c6; text-align: center; display: block">——— 完 ———</span>--%>
</div>
<%-- 表格 --%>
<script>
    $(function () {
        $('#wgpz').datagrid({
            url: '${basePath}/jlwgpz/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            singleSelect: true,
            rownumbers: true,
            columns: [[
                {
                    field: 'csxm', title: '测试项目', width: '40%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'csyq', title: '测试要求', width: '50%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'csjg', title: '测试结果', width: '10%', formatter: function (value) {
                        return jg3(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });
        $('#ctgy').datagrid({
            url: '${basePath}/jlctgy/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            singleSelect: true,
            rownumbers: true,
            columns: [[
                {
                    field: 'azyq', title: '安装要求', width: '90%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jg', title: '检查结果', width: '10%', formatter: function (value) {
                        return jg3(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#cyjd').datagrid({
            url: '${basePath}/jlcyjd/selectByAll?tsid=' + tsid,
            rownumbers: true,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            singleSelect: true,
            columns: [[
                {field: 'jg', title: '间隔', width: '10%'},
                {
                    field: 'csgn', title: '测试功能', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'clx', title: '测量项', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'a', title: '额定值A', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'b', title: '额定值B', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'c', title: '额定值C', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'lx', title: '额定值零序', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'csjg', title: '检查结果', width: '10%', formatter: function (value) {
                        return jg2(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#dlhl').datagrid({
            url: '${basePath}/jldlhl/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'jg', title: '间隔', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'csgn', title: '测试功能', width: '15%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'csff', title: '测试方法', width: '25%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'a', title: 'A', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'b', title: 'B', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'c', title: 'C', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'lx', title: '零序', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'csjg', title: '检查结果', width: '10%', formatter: function (value) {
                        return jg2(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#dtu').datagrid({
            url: '${basePath}/jldtu/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            // rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'yf', title: '远方', width: '20%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'jd', title: '就地', width: '20%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'dcqy', title: '电池欠压', width: '20%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'dchh', title: '电池活化', width: '20%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'jlsd', title: '交流失电', width: '20%', formatter: function (value) {
                        return jg1(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#dyxn').datagrid({
            url: '${basePath}/jldyxn/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'csxm', title: '测试项目', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jsbz', title: '技术标准或测试要求', width: '50%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jcjg', title: '检查结果', width: '20%', formatter: function (value) {
                        return jg3(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#gjcs1').datagrid({
            url: '${basePath}/jlgjcs/selectByAll?tsid=' + tsid + "&gjlx=过流",
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'jg', title: '间隔', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'gjlx', title: '告警类型', width: '20%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'a1', title: 'A相0.95倍', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'a2', title: 'A相1.05倍', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'b1', title: 'B相0.95倍', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'b2', title: 'B相1.05倍', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'c1', title: 'C相0.95倍', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'c2', title: 'C相1.05倍', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'csjg1', title: '检查结果', width: '10%', formatter: function (value) {
                        return jg2(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#gjcs2').datagrid({
            url: '${basePath}/jlgjcs/selectByAll?tsid=' + tsid + "&gjlx=零序",
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'jg', title: '间隔', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'gjlx', title: '告警类型', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'l1', title: '零序0.95倍', width: '20%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'l2', title: '零序1.05倍', width: '20%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'csjg1', title: '检查结果', width: '20%', formatter: function (value) {
                        return jg2(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#gncs').datagrid({
            url: '${basePath}/jlgncs/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'csgn', title: '测试功能', width: '40%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jsbz', title: '技术标准或测试要求', width: '50%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jg', title: '检查结果', width: '10%', formatter: function (value) {
                        return jg3(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#hldz').datagrid({
            url: '${basePath}/jlhldz/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'jg', title: '间隔', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'hl', title: '回路', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'zzc', title: 'CT侧', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'ctc', title: '装置侧', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#jydz').datagrid({
            url: '${basePath}/jljydz/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'bchl', title: '被测回路', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'sydy', title: '试验电压', width: '20%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'gcyq', title: '规程要求', width: '25%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'clzz', title: '测量阻值', width: '25%', formatter: function (value) {
                        return contentFormat(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#kgg').datagrid({
            url: '${basePath}/jlkgg/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'jg', title: '间隔', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'sbyf', title: '手把远方', width: '15%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'sbjd', title: '手把就地', width: '15%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'ddf', title: '地刀分', width: '15%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'ddh', title: '地刀合', width: '15%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'kgf', title: '开关分', width: '15%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'kgh', title: '开关合', width: '15%', formatter: function (value) {
                        return jg1(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#lpjy').datagrid({
            url: '${basePath}/jllpjy/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'jg', title: '间隔', width: '25%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'hl', title: '回路', width: '25%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'zzds', title: '装置读数', width: '25%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jcjg', title: '检查结果', width: '25%', formatter: function (value) {
                        return jg2(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#xb').datagrid({
            url: '${basePath}/jlxb/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            // rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'byqgwgj', title: '变压器高温告警', width: '50%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'byqcwgj', title: '变压器超温告警', width: '50%', formatter: function (value) {
                        return jg1(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#ycts').datagrid({
            url: '${basePath}/jlycts/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'csxm', title: '测试项目', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'ddyc1', title: '电压遥测', width: '35%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'ddyc2', title: '电压遥测', width: '35%', formatter: function (value) {
                        return contentFormat(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#ykts').datagrid({
            url: '${basePath}/jlykts/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'jg', title: '间隔', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'ykf', title: '遥控分', width: '35%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'ykh', title: '遥控合', width: '35%', formatter: function (value) {
                        return jg1(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });

        $('#ysbj').datagrid({
            url: '${basePath}/jlysbj/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            // rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'gswgj', title: '高水位告警', width: '25%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'dswgj', title: '低水位告警', width: '25%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'sbqdgj', title: '水泵启动告警', width: '25%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'yszzyc', title: '溢水装置异常', width: '25%', formatter: function (value) {
                        return jg1(value);
                    }
                }
            ]]
            , onLoadSuccess: function (data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row");
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length / 2; i++) {
                        var rowDom = $rowDoms[i];
                        var rowDom2 = $rowDoms[i + $rowDoms.length / 2];
                        $(rowDom).height($(rowDom2).height());
                    }
                }

            }
        });
    })
</script>
<%-- 页面加载完执行 --%>
<script>
    $(function () {
        // 隐藏箱变
        if (zzlx != '4') {
            $("#div_xb").css("display", "none");
        }

        // 查询备注信息
        $.get("${basePath}/beizhu/selectBeizhuByPrimaryKey/" + '${jbxx.tsid}', function (data) {
            let beizhu = $.parseJSON(data);
            $("#ctgybeizhu").val(beizhu.jlCtgy);
            $("#cyjdbeizhu").val(beizhu.jlCyjd);
            $("#dlhlbeizhu").val(beizhu.jlDlhl);
            $("#dtubeizhu").val(beizhu.jlDtu);
            $("#dyxnbeizhu").val(beizhu.jlDyxn);
            $("#gjcsbeizhu").val(beizhu.jlGjcs);
            $("#gncsbeizhu").val(beizhu.jlGncs);
            $("#hldzbeizhu").val(beizhu.jlHldz);
            $("#jydzbeizhu").val(beizhu.jlJydz);
            $("#kggbeizhu").val(beizhu.jlKgg);
            $("#lpjybeizhu").val(beizhu.jlLpjy);
            $("#wgpzbeizhu").val(beizhu.jlWgpz);
            $("#xbybeizhu").val(beizhu.jlXb);
            $("#yctsbeizhu").val(beizhu.jlYcts);
            $("#yktsbeizhu").val(beizhu.jlYkts);
            $("#ysbjbeizhu").val(beizhu.jlYsbj);
        });

        // 查询告警定值
        $.get("${basePath}/gjdz/selectByPrimaryKey/" + '${jbxx.tsid}', function (data) {
            let gjdz = $.parseJSON(data);
            $("#glgj1").val(gjdz.glgj1);
            $("#glgj2").val(gjdz.glgj2);
            $("#glgj3").val(gjdz.glgj3);
            $("#glcs1").val(gjdz.glcs1);
            $("#glcs2").val(gjdz.glcs2);
            $("#lxgj1").val(gjdz.lxgj1);
            $("#lxgj2").val(gjdz.lxgj2);
            $("#lxgj3").val(gjdz.lxgj3);
            $("#lxcs1").val(gjdz.lxcs1);
            $("#lxcs2").val(gjdz.lxcs2);
            if (gjdz.qygj1 == 1) {
                $("#qygj1").val("正确");
            } else if (gjdz.qygj1 == 0) {
                $("#qygj1").val("错误");
            } else {
                $("#qygj1").val("N/A");
            }
            if (gjdz.qygj2 == 1) {
                $("#qygj2").val("正确");
            } else if (gjdz.qygj1 == 0) {
                $("#qygj2").val("错误");
            } else {
                $("#qygj2").val("N/A");
            }
        });
    })
</script>
<%-- 函数 --%>
<script>
    function jg1(value) {
        if (value == 1) {
            return "正确";
        } else if (value == 0) {
            return "错误";
        } else {
            return "N/A";
        }
    }

    function jg2(value) {
        if (value == 1) {
            return "正常";
        } else if (value == 0) {
            return "超标";
        } else {
            return "N/A";
        }
    }

    function jg3(value) {
        if (value == 1) {
            return "是";
        } else if (value == 0) {
            return "否";
        } else {
            return "N/A";
        }
    }

    // 鼠标悬浮显示
    function contentFormat(value) {
        if (value !== undefined && value !== '' && value != null) {
            // return "<span title='"+value+"' class='textEllipsis'>"+value+"</span>";
            return "<div title='" + value + "' class='textEllipsis'>" + value + "</div>";
        } else {
            return '';
        }
    }
</script>
</body>
</html>