<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2020/7/27
  Time: 10:58 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../import.jsp" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/easyui/themes/icon.css">
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
            <span style="font-size: 18px; color: black;">2、开关定值</span>
        </div>
        <div style="width: 95%; margin: 0 auto;">
            <table id="kgdz" ></table>
            <textarea id="kgdzbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
        </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">3、后备电源</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="hbdy"></table>
        <textarea id="hbdybeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">4、电流、电压零漂校验</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="lpjy"></table>
        <textarea id="lpjybeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">5、采样精度测试（二次值）</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="cyjd"></table>
        <textarea id="cyjdbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">6、功能调试</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="gncs"></table>
        <textarea id="gncsbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">7、跳闸、告警功能调试（通入二次值）</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <span style="margin-left: 25px;">(1)过流I段定值<input id="gl1ddz" readonly type="text" style="text-align: center;"/>, 过流II段定值<input id="gl2ddz" readonly type="text" style="text-align: center;"/></span><br>
        <span style="margin-left: 40px;">
            过流I段时间 <input id="gl1dsj" readonly type="text" style="text-align: center;"/>，通电<input id="gl1dtd" readonly type="text" style="text-align: center;"/>秒,
        </span><br>
        <span style="margin-left: 40px;">
            过流II段时间<input id="gl2dsj" readonly type="text" style="text-align: center;"/>，通电<input id="gl2dtd" readonly type="text" style="text-align: center;"/>秒.
        </span>
        <table id="gjtsgl"></table><br>
        <span style="margin-left: 25px;">(2)零序I段定值<input id="lx1ddz" readonly type="text" style="text-align: center;"/>，零序II段定值<input id="lx2ddz" readonly type="text" style="text-align: center;"/></span><br>
        <span style="margin-left: 40px;">
            过流I段时间 <input id="lx1dsj" readonly type="text" style="text-align: center;"/>，通电<input id="lx1dtd" readonly type="text" style="text-align: center;"/>秒，
        </span><br>
        <span style="margin-left: 40px;">
            过流II段时间<input id="lx2dsj" readonly type="text" style="text-align: center;"/>，通电<input id="lx2dtd" readonly type="text" style="text-align: center;"/>秒.
        </span>
        <table id="gjtslx"></table>
        <textarea id="gjtsbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="margin-left: 25px;">此项试验前应上好出口跳闸压板 </span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="tzyb"></table>
        <textarea id="tzybbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">8、遥控传动（带模拟开关）</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="ykcd"></table>
        <textarea id="ykcdbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">9、采样精度测试（一次值）</span><br>
        <span style="margin-left: 25px;">开关与控制器航空线已连接，在开关一次侧通入电流。</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="cyjdcs"></table>
        <textarea id="cyjdcsbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">10、带开关遥控</span><br>
        <span style="margin-left: 25px;">开关与控制器航空线已连接。</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="ykkg"></table>
        <textarea id="ykkgbeizhu" readonly rows="8" style="width: 100%;border:1px solid #CCC;"></textarea>
    </div>

    <div style="margin-top: 20px; width: 100%;">
        <span style="font-size: 18px; color: black;">11、投运前的检查</span><br>
    </div>
    <div style="width: 95%; margin: 0 auto;">
        <table id="tyjc"></table>
        <textarea id="tyjcbeizhu" readonly rows="8" style="width: 100%; border:1px solid #CCC;"></textarea>
    </div>
</div>
<div style="margin-bottom: 30px;">
<%--    <span style="color: #d0e9c6; text-align: center; display: block">——— 完 ———</span>--%>
</div>
<%-- 表格 --%>
<script>
    $(function () {
        var tsid = '${requestScope.tsid}';
        $('#kgdz').datagrid({
            url: '${basePath}/ftu_jl_kgdz/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            singleSelect: true,
            rownumbers: true,
            columns: [[
                {
                    field: 'dzdh', title: '定制单号', width: '11%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'g11', title: '过流I段', width: '11%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'g12', title: '过流I段', width: '11%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'g21', title: '过流II段', width: '11%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'g22', title: '过流II段', width: '11%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'lx11', title: '零序I段', width: '11%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'lx12', title: '零序I段', width: '11%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'lx21', title: '零序I段', width: '11%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'lx22', title: '零序I段', width: '11%', formatter: function (value) {
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

        $('#hbdy').datagrid({
            url: '${basePath}/ftu_jl_hbdy/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            singleSelect: true,
            rownumbers: true,
            columns: [[
                {
                    field: 'jcnr', title: '检查内容', width: '69%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jcjg', title: '检查结果', width: '30%', formatter: function (value) {
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

        $('#lpjy').datagrid({
            url: '${basePath}/ftu_jl_lpjy/selectByAll?tsid=' + tsid,
            rownumbers: true,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            singleSelect: true,
            columns: [[
                {field: 'hl', title: '回路', width: '29%'},
                {
                    field: 'zzds', title: '装置读数', width: '40%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jcjg', title: '检查结果', width: '30%', formatter: function (value) {
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

        $('#cyjd').datagrid({
            url: '${basePath}/ftu_jl_cyjd/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [
                [{title: '模拟量采集单元测试记录', align: 'center', colspan: 7}],
                [{
                    field: 'clx', title: '测量项', width: '9%', rowspan: 2, formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {title: '读     值', align: 'center', colspan: 5},
                {
                    field: 'csjg', title: '检查结果', width: '10%', rowspan:2, formatter: function (value) {
                        return jg2(value);
                    }
                }
            ],
            [
                {field: 'uab', title: 'Uab', width: '16%', align: 'center'}
                , {field: 'ubc', title: 'Ubc', width: '16%', align: 'center'}
                , {field: 'ia', title: 'IA', width: '16%', align: 'center'}
                , {field: 'ic', title: 'IC', width: '16%', align: 'center'}
                , {field: 'io', title: 'I0', width: '16%', align: 'center'}
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
            url: '${basePath}/ftu_jl_gncs/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'csgn', title: '测试功能', width: '30%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jsbz', title: '技术标准', width: '59%', formatter: function (value) {
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

        $('#gjtsgl').datagrid({
            url: '${basePath}/ftu_jl_gjts/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {field: 'csx', title: '相别', width: '9%', rowspan: 2, formatter: function (value) {return contentFormat(value);}},
                {field: 'g1dz', title: '过流I段整定值', width: '15%', rowspan: 2, formatter: function (value) {return contentFormat(value);}},
                {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2},
                {field: 'g2dz', title: '过流II段整定值', width: '15%', rowspan: 2, formatter: function (value) {return contentFormat(value);}},
                {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
            ],[
                {field: 'g1sjtd1', title: '不动作(A)', width: '15%', align: 'center'}
                , {field: 'g1sjtd2', title: '动作(A)', width: '15%', align: 'center'}
                , {field: 'g2sjtd1', title: '不动作(A)', width: '15%', align: 'center'}
                , {field: 'g2sjtd2', title: '动作(A)', width: '15%', align: 'center'}
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

        $('#gjtslx').datagrid({
            url: '${basePath}/ftu_jl_gjdz/selectByAll?id=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {field: 'clx', title: '相别', width: '9%', rowspan: 2, formatter: function (value) {return 'L-0';}},
                {field: 'lx1', title: '零序I段整定值', width: '15%', rowspan: 2, formatter: function (value) {return contentFormat(value);}},
                {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2},
                {field: 'lx2', title: '零序II段整定值', width: '15%', rowspan: 2, formatter: function (value) {return contentFormat(value);}},
                {title: '实际通电0.95倍定值／1.05倍定值', align: 'center', colspan: 2}
            ],[
                {field: 'lx11', title: '不动作(A)', width: '15%', align: 'center'}
                , {field: 'lx12', title: '动作(A)', width: '15%', align: 'center'}
                , {field: 'lx21', title: '不动作(A)', width: '15%', align: 'center'}
                , {field: 'lx22', title: '动作(A)', width: '15%', align: 'center'}
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

        $('#tzyb').datagrid({
            url: '${basePath}/ftu_jl_tzyb/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {field: 'jyxm', title: '检验项目', width: '19%', rowspan: 2, formatter: function (value) {return contentFormat(value);}},
                {field: 'gzmn', title: '故障模拟', width: '20%', rowspan: 2, formatter: function (value) {return contentFormat(value);}},
                {field: 'mndl', title: '模拟断路器(或启动信号)', width: '15%', rowspan: 2, formatter: function (value) {return jg1(value);}},
                {title: '模拟断路器动作情况(或信号动作情况)', align: 'center', colspan: 2},
                {field: 'jcjg', title: '检查结果', width: '15%', align: 'center', rowspan: 2, formatter: function (value) {return jg1(value);}}
            ], [
                {field: 'zzxh', title: '主站信号', width: '15%', align: 'center', formatter: function (value) {return jg1(value);}},
                {field: 'zzxs', title: '装置显示', width: '15%', align: 'center', formatter: function (value) {return jg1(value);}}
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

        $('#ykcd').datagrid({
            url: '${basePath}/ftu_jl_ykcd/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            // rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'ykf', title: '遥控分', width: '50%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'ykh', title: '遥控合', width: '50%', formatter: function (value) {
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

        $('#cyjdcs').datagrid({
            url: '${basePath}/ftu_jl_cyjdcs/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {field: 'clx', title: '测量项', width: '20%', rowspan: 2, formatter: function (value) {return contentFormat(value);}},
                {title: '读值', colspan: 3},
                {field: 'csjg', title: '检查结果', width: '19%',rowspan: 2, formatter: function (value) {return jg2(value);}}
            ], [
                {field: 'ia', title: 'IA', width: '20%', align: 'center'}
                , {field: 'ic', title: 'IC', width: '20%', align: 'center'}
                , {field: 'io', title: 'I0', width: '20%', align: 'center'}]]
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

        $('#ykkg').datagrid({
            url: '${basePath}/ftu_jl_ykkg/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            // rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'ykf', title: '遥控分', width: '50%', formatter: function (value) {
                        return jg1(value);
                    }
                },
                {
                    field: 'ykh', title: '遥控合', width: '50%', formatter: function (value) {
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

        $('#tyjc').datagrid({
            url: '${basePath}/ftu_jl_tyjc/selectByAll?tsid=' + tsid,
            autoRowHeight: false,	// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
            rownumbers: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'jcxm', title: '检查项目', width: '79%', formatter: function (value) {
                        return contentFormat(value);
                    }
                },
                {
                    field: 'jcjg', title: '检查结果', width: '20%', formatter: function (value) {
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
        var tsid = '${requestScope.tsid}';
        // 查询备注信息
        $.get("${basePath}/ftu_beizhu/selectFtuBeizhuByPrimaryKey/" + tsid, function (data) {
            let beizhu = $.parseJSON(data);
            $("#cyjdbeizhu").val(beizhu.jlCyjd);
            $("#cyjdcsbeizhu").val(beizhu.jlCyjdcs);
            $("#gjtsbeizhu").val(beizhu.jlGjts);
            $("#gncsbeizhu").val(beizhu.jlGncs);
            $("#hbdybeizhu").val(beizhu.jlHbdy);
            $("#kgdzbeizhu").val(beizhu.jlKgdz);
            $("#lpjybeizhu").val(beizhu.jlLpjy);
            $("#tyjcbeizhu").val(beizhu.jlTyjc);
            $("#tzybbeizhu").val(beizhu.jlTzyb);
            $("#ykcdbeizhu").val(beizhu.jlYkcd);
            $("#ykkgbeizhu").val(beizhu.jlYkkg);
        });

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