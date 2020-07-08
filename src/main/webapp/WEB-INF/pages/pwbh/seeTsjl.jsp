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
            <span style="font-size: 18px; color: black;">1、所需仪器仪表</span>
        </div>
        <div style="width: 95%; margin: 0 auto;">
            <table id="yqyb" ></table>
            <textarea id="yqybbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
        </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">2、外观检查</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="wgjc"></table>
        <textarea id="wgjcbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">3、紧线及对线</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="jxdx"></table>
        <textarea id="jxdxbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">4、校对时钟</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <span style="margin-left: 25px;">检验要求：将装置时钟校对至当前时钟，以便于系统故障时进行分析。</span><br>
        <span style="margin-left: 25px;">检查结果：<input id="szjcjg" type="text" readonly style="text-align: center;"></span><br>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">5、绝缘测试 </span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="jycs"></table>
        <textarea id="jycsbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">6、定值单检查</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="dzdjc"></table>
        <textarea id="dzdjcbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">7、电流、电压零漂校验</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="lpjy"></table>
        <textarea id="lpjybeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">8、电流、电压精度检验</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="jdjy"></table>
        <textarea id="jdjybeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">9、保护CT参数、变比试验</span><br>
        <span style="font-size: 18px; color: black;">9.1 保护CT极性</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="bhctjx"></table>
        <textarea id="bhctjxbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">9.2 保护CT变比</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="bhctbh"></table>
        <textarea id="bhctbhbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">10、定值检查</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <span style="margin-left: 25px;">测量步骤：</span><br>

        <label for="g1">（1）过流Ⅰ段定值 <input id="g1" style="text-align: center" type="text" readonly/>，</label>
        <label for="g2">过流Ⅱ段定值<input id="g2" style="text-align: center" type="text" readonly/>。</label>
        <br>
        <label for="g11" style="margin-left: 36px;">过流Ⅰ段时间<input id="g11" style="text-align: center" type="text" readonly/>秒，</label>
        <label for="g12">通电<input id="g12" style="text-align: center" type="text" readonly/>秒。</label>
        <br>
        <label for="g21" style="margin-left: 36px;">过流ⅠI段时间<input id="g21" style="text-align: center" type="text" readonly/>秒，</label>
        <label for="g22">通电<input id="g22" style="text-align: center" type="text" readonly/>秒。</label>
        <table id="dzjcgl"></table>
        <br>
        <label for="l1">（2）零序Ⅰ段定值 <input id="l1" style="text-align: center" type="text" readonly/>，</label>
        <label for="l2">零序Ⅱ段定值<input id="l2" style="text-align: center" type="text" readonly/>。</label>
        <br>
        <label for="l11" style="margin-left: 36px;">零序Ⅰ段时间<input id="l11" style="text-align: center" type="text" readonly/>秒，</label>
        <label for="l12">通电<input id="l12" style="text-align: center" type="text" readonly/>秒。</label>
        <br>
        <label for="l21" style="margin-left: 36px;">零序ⅠI段时间<input id="l21" style="text-align: center" type="text" readonly/>秒，</label>
        <label for="l22">通电<input id="l22" style="text-align: center" type="text" readonly/>秒。</label>
        <br>
        <table id="dzjclx"></table>
        <textarea id="dzjcbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">11、整组试验及实际断路器传动</span><br>
        <span style="margin-left: 25px;">此项试验前应将本次可以传动的断路器合入，上好出口跳闸压板</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="zzsy"></table>
        <textarea id="zzsybeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">12、保护CT回路接触电阻：（误差不得超过10％）</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="bhcthl"></table>
        <textarea id="bhcthlbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">13、 收工前需检查的项目</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="sgjc"></table>
        <textarea id="sgjcbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>
</div>
<div style="margin-bottom: 30px;">
<%--    <span style="color: #d0e9c6; text-align: center; display: block">——— 完 ———</span>--%>
</div>
<%-- 表格 --%>
<script>
    $(function () {
        var tsid = '${requestScope.pwbhJbxx.tsid}';
        $('#yqyb').datagrid({
            url: '${basePath}/pwbh_jl_yqyb/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            singleSelect: true,
            rownumbers: true,
            columns: [[
                {
                    field: 'yqmc', title: '试验仪器名称', width: '25%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'sbxh', title: '设备型号', width: '25%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'bh', title: '编号', width: '25%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'hgqx', title: '合格期限', width: '25%', formatter: function (value) {
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
        $('#wgjc').datagrid({
            url: '${basePath}/pwbh_jl_wgjc/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            singleSelect: true,
            rownumbers: true,
            columns: [[
                {
                    field: 'nr', title: '内容', width: '89%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jcjg', title: '检查结果', width: '10%', formatter: function (value) {
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
        $('#jxdx').datagrid({
            url: '${basePath}/pwbh_jl_jxdx/selectByAll?tsid=' + tsid,
            rownumbers: true,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            singleSelect: true,
            columns: [[
                {
                    field: 'nr', title: '内容', width: '89%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jcjg', title: '检查结果', width: '10%', formatter: function (value) {
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

        $.get("${basePath}/pwbh_beizhu/selectByPrimaryKey/" + tsid, function (data) {
                data = JSON.parse(data);

                $("#szjcjg").val(jg1(data.szjcjg));
            });

        $('#jycs').datagrid({
            url: '${basePath}/pwbh_jl_jycs/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'jcnr', title: '检查内容', width: '45%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'bz', title: '标准', width: '44%', formatter: function (value) {
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
        $('#dzdjc').datagrid({
            url: '${basePath}/pwbh_jl_dzdjc/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            // rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'cdrq', title: '出单日期', width: '20%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'dzdh', title: '定值单号', width: '20%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'bb', title: '版本', width: '20%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'jym', title: '校验码', width: '20%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'cxsj', title: '程序形成时间', width: '20%', formatter: function (value) {
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
            url: '${basePath}/pwbh_jl_lpjy/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'csjg', title: '测试结果', width: '40%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'cpu', title: 'CPU', width: '60%', formatter: function (value) {
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
        $('#jdjy').datagrid({
            url: '${basePath}/pwbh_jl_jdjy/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'csjg', title: '测试结果', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'tryq', title: '通入要求值', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'zzxs', title: '装置显示', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jcjg', title: '检查结果', width: '10%', formatter: function (value) {
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
        $('#bhctjx').datagrid({
            url: '${basePath}/pwbh_jl_bhctjx/selectByAll?tsid=' + tsid + "&gjlx=零序",
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'xb', title: '相别', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'mx1', title: '一次以母线为正', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'mx2', title: '二次以S1为正', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'bjzs', title: '表针指示', width: '30%', formatter: function (value) {
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
        $('#bhctbh').datagrid({
            url: '${basePath}/pwbh_jl_bhctbh/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'xb', title: '相别', width: '10%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'xh', title: '型号', width: '17%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'xqh', title: '线圈号', width: '18%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'cs', title: '参数', width: '18%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'mybh', title: '名义变比', width: '18%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'sybh', title: '试验变比', width: '18%', formatter: function (value) {
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
        $.get("${basePath}/pwbh_dz/selectByPrimaryKey/" + tsid, function (data) {
            data = JSON.parse(data).data;
            $("#l1").val(data.l1);
            $("#l11").val(data.l11);
            $("#l12").val(data.l12);
            $("#l2").val(data.l2);
            $("#l21").val(data.l21);
            $("#l22").val(data.l22);
            $("#g1").val(data.g1);
            $("#g11").val(data.g11);
            $("#g12").val(data.g12);
            $("#g2").val(data.g2);
            $("#g21").val(data.g21);
            $("#g22").val(data.g22);
        });
        $('#dzjcgl').datagrid({
            url: '${basePath}/pwbh_jl_dzjc/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[ //表头
                {field: 'jg', title: '相别', width: '10%', align: 'center', rowspan: 2}
                , {field: 'g1', title: '过流I段整定值', width: '14%', align: 'center', rowspan: 2}
                , {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
                , {field: 'g2', title: '过流II段整定值', width: '14%', align: 'center', rowspan: 2}
                , {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
            ],
                [
                    {field: 'g11', title: '不动作(A)', width: '15%', align: 'center'}
                    , {field: 'g12', title: '动作(A)', width: '15%', align: 'center'}
                    , {field: 'g21', title: '不动作(A)', width: '15%', align: 'center'}
                    , {field: 'g22', title: '动作(A)', width: '15%', align: 'center'}

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
        $('#dzjclx').datagrid({
            url: '${basePath}/pwbh_dz/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[ //表头
                {field: 'tsid', title: '相别', width: '10%', align: 'center', rowspan: 2,
                    formatter: function(value,row,index){
                        return 'L-0';
                    }
                }
                , {field: 'l1', title: '过流I段整定值', width: '14%', align: 'center', rowspan: 2}
                , {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
                , {field: 'l2', title: '过流II段整定值', width: '14%', align: 'center', rowspan: 2}
                , {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
            ],
                [
                    {field: 'l11', title: '不动作(A)', width: '15%', align: 'center'}
                    , {field: 'l12', title: '动作(A)', width: '15%', align: 'center'}
                    , {field: 'l21', title: '不动作(A)', width: '15%', align: 'center'}
                    , {field: 'l22', title: '动作(A)', width: '15%', align: 'center'}

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
        $('#zzsy').datagrid({
            url: '${basePath}/pwbh_jl_zzsy/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'jyxm', title: '检验项目', width: '17%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'gzmn', title: '故障模拟', width: '18%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'dlq', title: '断路器', width: '18%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'zzxh', title: '主站信号', width: '18%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'zzxs', title: '装置显示', width: '18%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jcjg', title: '检查结果', width: '10%', formatter: function (value) {
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
        $('#bhcthl').datagrid({
            url: '${basePath}/pwbh_jl_bhcthl/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'xb', title: '相别', width: '33%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'bhc', title: '保护侧', width: '33%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'ctc', title: 'CT侧', width: '33%', formatter: function (value) {
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

        $('#sgjc').datagrid({
            url: '${basePath}/pwbh_jl_sgjc/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'nr', title: '内容', width: '89%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jcjg', title: '检查结果', width: '10%', formatter: function (value) {
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
        // 查询备注信息
        <%--$.get("${basePath}/beizhu/selectBeizhuByPrimaryKey/" + '${jbxx.tsid}', function (data) {--%>
        <%--    let beizhu = $.parseJSON(data);--%>
        <%--    $("#ctgybeizhu").val(beizhu.jlCtgy);--%>
        <%--    $("#cyjdbeizhu").val(beizhu.jlCyjd);--%>
        <%--    $("#dlhlbeizhu").val(beizhu.jlDlhl);--%>
        <%--    $("#dtubeizhu").val(beizhu.jlDtu);--%>
        <%--    $("#dyxnbeizhu").val(beizhu.jlDyxn);--%>
        <%--    $("#gjcsbeizhu").val(beizhu.jlGjcs);--%>
        <%--    $("#gncsbeizhu").val(beizhu.jlGncs);--%>
        <%--    $("#hldzbeizhu").val(beizhu.jlHldz);--%>
        <%--    $("#jydzbeizhu").val(beizhu.jlJydz);--%>
        <%--    $("#kggbeizhu").val(beizhu.jlKgg);--%>
        <%--    $("#lpjybeizhu").val(beizhu.jlLpjy);--%>
        <%--    $("#wgpzbeizhu").val(beizhu.jlWgpz);--%>
        <%--    $("#xbybeizhu").val(beizhu.jlXb);--%>
        <%--    $("#yctsbeizhu").val(beizhu.jlYcts);--%>
        <%--    $("#yktsbeizhu").val(beizhu.jlYkts);--%>
        <%--    $("#ysbjbeizhu").val(beizhu.jlYsbj);--%>
        <%--});--%>

        // 查询告警定值
        <%--$.get("${basePath}/gjdz/selectByPrimaryKey/" + '${jbxx.tsid}', function (data) {--%>
        <%--    let gjdz = $.parseJSON(data);--%>
        <%--    $("#glgj1").val(gjdz.glgj1);--%>
        <%--    $("#glgj2").val(gjdz.glgj2);--%>
        <%--    $("#glgj3").val(gjdz.glgj3);--%>
        <%--    $("#glcs1").val(gjdz.glcs1);--%>
        <%--    $("#glcs2").val(gjdz.glcs2);--%>
        <%--    $("#lxgj1").val(gjdz.lxgj1);--%>
        <%--    $("#lxgj2").val(gjdz.lxgj2);--%>
        <%--    $("#lxgj3").val(gjdz.lxgj3);--%>
        <%--    $("#lxcs1").val(gjdz.lxcs1);--%>
        <%--    $("#lxcs2").val(gjdz.lxcs2);--%>
        <%--    if (gjdz.qygj1 == 1) {--%>
        <%--        $("#qygj1").val("正确");--%>
        <%--    } else if (gjdz.qygj1 == 0) {--%>
        <%--        $("#qygj1").val("错误");--%>
        <%--    } else {--%>
        <%--        $("#qygj1").val("N/A");--%>
        <%--    }--%>
        <%--    if (gjdz.qygj2 == 1) {--%>
        <%--        $("#qygj2").val("正确");--%>
        <%--    } else if (gjdz.qygj1 == 0) {--%>
        <%--        $("#qygj2").val("错误");--%>
        <%--    } else {--%>
        <%--        $("#qygj2").val("N/A");--%>
        <%--    }--%>
        <%--});--%>
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