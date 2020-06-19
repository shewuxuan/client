package com.guodu.service.impl.dtu;

import com.guodu.pojo.dtu.*;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by word .
 */
@Service
public class ExportDocumentService {
    public XWPFDocument createDocument()throws Exception {
        XWPFDocument document= new XWPFDocument();
        //添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText("附录A   站所终端（DTU）调试传动记录（资料性附录）");
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setFontSize(14);
        titleParagraphRun.setBold(false);
       /* //两个表格之间加个换行
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText("\r");
        //添加页脚
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        String footerText = "http://blog.csdn.net/zhouseawater";
        ctFooter.setStringValue(footerText);*/
        return document;
    }

    /***
     * 绘制基本信息表
     * @param document
     * @param jbxx
     * @return
     */
    public XWPFDocument createTableByJbxx(XWPFDocument document, Jbxx jbxx){
        createTitle(document,"1、基础信息");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.setHeight(10);
        infoTableRowOne.getCell(0).setText("调试时间");
        infoTableRowOne.addNewTableCell().setText(jbxx.getTssj().substring(0,10));
        infoTableRowOne.addNewTableCell().setText("安装地点及调度号");
        infoTableRowOne.addNewTableCell().setText(jbxx.getAzddDdh());
        setCellWidth(infoTableRowOne.getCell(1),360);
        setCellWidth(infoTableRowOne.getCell(3),360);

        //表格第二行
        XWPFTableRow infoTableRowTwo = infoTable.createRow();
        infoTableRowTwo.getCell(0).setText("IP地址");
        infoTableRowTwo.getCell(1).setText(jbxx.getIpdz());
        infoTableRowTwo.getCell(2).setText("物联网卡号");
        infoTableRowTwo.getCell(3).setText(jbxx.getWlwkh());

        //表格第三行
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("RTU地址");
        infoTableRowThree.getCell(1).setText(jbxx.getRtudz());
        infoTableRowThree.getCell(2).setText("端口号");
        infoTableRowThree.getCell(3).setText(jbxx.getDkh());

        //表格第四行
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("生产厂商");
        infoTableRowFour.getCell(1).setText(jbxx.getSccs());
        infoTableRowFour.getCell(2).setText("生产日期");
        infoTableRowFour.getCell(3).setText(jbxx.getScrq().substring(0,10));

        //表格第五行
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("装置型号");
        infoTableRowFive.getCell(1).setText(jbxx.getZzxh());
        infoTableRowFive.getCell(2).setText("装置编号");
        infoTableRowFive.getCell(3).setText(jbxx.getZzbh());

        //表格第六行
        XWPFTableRow infoTableRowSix = infoTable.createRow();
        infoTableRowOne.setHeight(20);
        infoTableRowSix.getCell(0).setText("软件版本及校验码");
        infoTableRowSix.getCell(1).setText(jbxx.getRjbbJym());
        infoTableRowSix.getCell(2).setText("硬件版本");
        infoTableRowSix.getCell(3).setText(jbxx.getYjbb());

        //表格第7行
        XWPFTableRow infoTableRowSeven = infoTable.createRow();
        infoTableRowSeven.getCell(0).setText("调试人员姓名");
        infoTableRowSeven.getCell(1).setText(jbxx.getTsryxm());
        infoTableRowSeven.getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
        mergeCellsHorizontal(infoTable,6,1,3);

        infoTable.getRow(6).getCell(1).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 4) * (3 - 1 + 1)));
        return document;
    }

    /***
     * DTU外观结构及配置
     * @param document
     * @return
     */
    public XWPFDocument createTableByWgpz(XWPFDocument document, Map map){
        toParagraph(document);
        createTitle(document,"2、DTU外观结构及配置");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.setHeight(20);
        infoTableRowOne.getCell(0).setText("项次");
        infoTableRowOne.addNewTableCell().setText("测试项目");
        infoTableRowOne.addNewTableCell().setText("技术标准或测试要求");
        infoTableRowOne.addNewTableCell().setText("结果");
        setCellWidth(infoTableRowOne.getCell(0),120);
        setCellWidth(infoTableRowOne.getCell(3),240);

        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));
        //表格第2行
        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("1");
        setCellTextAlign( infoTableRowTow.getCell(0));
        infoTableRowTow.getCell(1).setText("模块配置");
        infoTableRowTow.getCell(2).setText("遥信、遥测、遥控模块独立配置，方便、灵活的进行配置。");
        infoTableRowTow.getCell(3).setText(switchJcjg("0",returnString(map.get("1"))));
        setCellTextAlign( infoTableRowTow.getCell(3));
        setCellWidth(infoTableRowTow.getCell(0),120);
        setCellWidth(infoTableRowTow.getCell(3),240);

        //表格第3行
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("2");
        setCellTextAlign( infoTableRowThree.getCell(0));
        infoTableRowThree.getCell(1).setText("热拔插功能单元标配容量");
        infoTableRowThree.getCell(2).setText("遥信、遥测、遥控模块独立配置、支持热拔插。");
        infoTableRowThree.getCell(3).setText(switchJcjg("0",returnString(map.get("2"))));
        setCellTextAlign( infoTableRowThree.getCell(3));
        setCellWidth(infoTableRowThree.getCell(0),120);
        setCellWidth(infoTableRowThree.getCell(3),240);

        //表格第4行
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("3");
        setCellTextAlign( infoTableRowFour.getCell(0));
        infoTableRowFour.getCell(1).setText("通信接口");
        infoTableRowFour.getCell(2).setText("至少4 个串口");
        infoTableRowFour.getCell(3).setText(switchJcjg("0",returnString(map.get("3"))));
        setCellTextAlign( infoTableRowFour.getCell(3));
        setCellWidth(infoTableRowFour.getCell(0),120);
        setCellWidth(infoTableRowFour.getCell(3),240);

        //表格第5行
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("");
        infoTableRowFive.getCell(1).setText("");
        infoTableRowFive.getCell(2).setText("至少2 个串口");
        infoTableRowFive.getCell(3).setText(switchJcjg("0",returnString(map.get("4"))));
        setCellTextAlign( infoTableRowFive.getCell(3));
        setCellWidth(infoTableRowFive.getCell(0),120);
        setCellWidth(infoTableRowFive.getCell(3),240);

        mergeCellsVertically(infoTable,0,3,4);
        mergeCellsVertically(infoTable,1,3,4);

        /*XWPFTableRow lastRow = infoTable.createRow();
        lastRow.getCell(0).setText(returnString(map.get("REMARK")));
        mergeCellsHorizontal(infoTable,5,0,3);*/
        createRemark(document,returnString(map.get("REMARK")));
        return document;
    }

    /***
     * 绝缘电阻测试
     * @param document
     * @return
     */
    public XWPFDocument createTableByJydz(XWPFDocument document, Map map ){
        toParagraph(document);
        createTitle(document,"3、回路测试");
        createTitle(document," 3.1绝缘电阻测试");
        createTitle(document," 用500伏摇表按下表测量绝缘电阻，摇测完毕后,将各回路对地放电:");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("被测回路");
        infoTableRowOne.addNewTableCell().setText("试验电压（伏）");
        infoTableRowOne.addNewTableCell().setText("规程要求（兆欧）");
        infoTableRowOne.addNewTableCell().setText("测量阻值（兆欧）");
        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));

        //表格第2行
        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("电流回路对地");
        infoTableRowTow.getCell(1).setText("500");
        infoTableRowTow.getCell(2).setText(returnString2(map.get("R1_1")));
        infoTableRowTow.getCell(3).setText(returnString2(map.get("R1_2")));
        setCellTextRight(infoTableRowTow.getCell(1));
        setCellTextRight(infoTableRowTow.getCell(2));
        setCellTextRight(infoTableRowTow.getCell(3));

        //表格第3行
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("电压回路对地");
        infoTableRowThree.getCell(1).setText("500");
        infoTableRowThree.getCell(2).setText(returnString2(map.get("R2_1")));
        infoTableRowThree.getCell(3).setText(returnString2(map.get("R2_2")));
        setCellTextRight(infoTableRowThree.getCell(1));
        setCellTextRight(infoTableRowThree.getCell(2));
        setCellTextRight(infoTableRowThree.getCell(3));

        //表格第4行
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("信号回路对地");
        infoTableRowFour.getCell(1).setText("500");
        infoTableRowFour.getCell(2).setText(returnString2(map.get("R3_1")));
        infoTableRowFour.getCell(3).setText(returnString2(map.get("R3_2")));
        setCellTextRight(infoTableRowFour.getCell(1));
        setCellTextRight(infoTableRowFour.getCell(2));
        setCellTextRight(infoTableRowFour.getCell(3));

        //表格第5行
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("直流回路对地");
        infoTableRowFive.getCell(1).setText("500");
        infoTableRowFive.getCell(2).setText(returnString2(map.get("R4_1")));
        infoTableRowFive.getCell(3).setText(returnString2(map.get("R4_2")));
        setCellTextRight(infoTableRowFive.getCell(1));
        setCellTextRight(infoTableRowFive.getCell(2));
        setCellTextRight(infoTableRowFive.getCell(3));

        //表格第6行
        XWPFTableRow infoTableRowSix = infoTable.createRow();
        infoTableRowSix.getCell(0).setText("电流回路对电压回路");
        infoTableRowSix.getCell(1).setText("500");
        infoTableRowSix.getCell(2).setText(returnString2(map.get("R5_1")));
        infoTableRowSix.getCell(3).setText(returnString2(map.get("R5_2")));
        setCellTextRight(infoTableRowSix.getCell(1));
        setCellTextRight(infoTableRowSix.getCell(2));
        setCellTextRight(infoTableRowSix.getCell(3));

        //表格第7行
        XWPFTableRow infoTableRowSeven = infoTable.createRow();
        infoTableRowSeven.getCell(0).setText("电流回路对控制回路");
        infoTableRowSeven.getCell(1).setText("500");
        infoTableRowSeven.getCell(2).setText(returnString2(map.get("R6_1")));
        infoTableRowSeven.getCell(3).setText(returnString2(map.get("R6_2")));
        setCellTextRight(infoTableRowSeven.getCell(1));
        setCellTextRight(infoTableRowSeven.getCell(2));
        setCellTextRight(infoTableRowSeven.getCell(3));

        createRemark(document,returnString(map.get("REMARK")));
        return document;
    }

    /***
     * CT回路电阻测试
     * @param document
     * @param hldzs
     * @return
     */
    public XWPFDocument createTableByHldz(XWPFDocument document, List<List<JlHldz>> hldzs,String remark){
        toParagraph(document);
        createTitle(document,"3.2 CT回路电阻测试（测量值应不大于0.2Ω）");
        createTitle(document,"测量步骤：");
        createTitle(document,"（1）测试前，断开开关柜二次柜内的CT连接片，分别在端子排上口（一般为装置侧）和下口（一般为CT侧）进行测试。");
        createTitle(document,"（2）测试后，及时恢复CT连接片，防止开路。");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("间隔");
        infoTableRowOne.addNewTableCell().setText("回路");
        infoTableRowOne.addNewTableCell().setText("装置侧");
        infoTableRowOne.addNewTableCell().setText("CT侧");
        infoTableRowOne.addNewTableCell().setText("间隔");
        infoTableRowOne.addNewTableCell().setText("回路");
        infoTableRowOne.addNewTableCell().setText("装置侧");
        infoTableRowOne.addNewTableCell().setText("CT侧");
        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));
        setCellTextAlign( infoTableRowOne.getCell(4));
        setCellTextAlign( infoTableRowOne.getCell(5));
        setCellTextAlign( infoTableRowOne.getCell(6));
        setCellTextAlign( infoTableRowOne.getCell(7));

        int jgs = hldzs.size();

        if(jgs%2 == 0){
            formatJlHldz(infoTable,hldzs);
        }else {
            List<JlHldz> last = hldzs.get(jgs - 1);
            hldzs.remove(jgs - 1);
            formatJlHldz(infoTable, hldzs);
            for (int j = 1; j <= 4; j++) {
                XWPFTableRow infoTableRowTow = infoTable.createRow();
                JlHldz hldz = swichJlHldz(last, j);
                if (hldz != null) {
                    infoTableRowTow.getCell(0).setText(returnString(hldz.getJg()));
                    infoTableRowTow.getCell(1).setText(returnString(hldz.getHl()));
                    infoTableRowTow.getCell(2).setText(returnString2(hldz.getZzc()));
                    infoTableRowTow.getCell(3).setText(returnString2(hldz.getCtc()));
                } else {
                    infoTableRowTow.getCell(0).setText("");
                    infoTableRowTow.getCell(1).setText("");
                    infoTableRowTow.getCell(2).setText("");
                    infoTableRowTow.getCell(3).setText("");
                }
                infoTableRowTow.getCell(4).setText("");
                infoTableRowTow.getCell(5).setText("");
                infoTableRowTow.getCell(6).setText("");
                infoTableRowTow.getCell(7).setText("");

                setCellTextAlign(infoTableRowTow.getCell(0));
                setCellTextAlign(infoTableRowTow.getCell(1));
                setCellTextRight(infoTableRowTow.getCell(2));
                setCellTextRight(infoTableRowTow.getCell(3));
            }

            mergeCellsVertically(infoTable,0,infoTable.getRows().size()-4,infoTable.getRows().size()-1);
            mergeCellsVertically(infoTable,4,infoTable.getRows().size()-4,infoTable.getRows().size()-1);

        }
        createRemark(document,remark);
        return document;
    }
    //简单的问题复杂化
    public XWPFTable  formatJlHldz(XWPFTable infoTable,List<List<JlHldz>> hldzs){
        int jgs = hldzs.size();
        //判断间隔数量是否可以完整排列
        List<JlHldz> hldzs1 = null;
        List<JlHldz> hldzs2 = null;
        int p=0;
        for(int i=0;i<jgs;i=i+2){
            hldzs1 = hldzs.get(i);
            hldzs2 = hldzs.get(i+1);
            for(int j = 1;j<=4;j++){
                p =p+1;
                //表格第2行
                XWPFTableRow infoTableRowTow = infoTable.createRow();
                JlHldz hldz = swichJlHldz(hldzs1,j);
                if(hldz!=null){
                    infoTableRowTow.getCell(0).setText(returnString(hldz.getJg()));
                    infoTableRowTow.getCell(1).setText(returnString(hldz.getHl()));
                    infoTableRowTow.getCell(2).setText(returnString2(hldz.getZzc()));
                    infoTableRowTow.getCell(3).setText(returnString2(hldz.getCtc()));
                }else{
                    infoTableRowTow.getCell(0).setText("");
                    infoTableRowTow.getCell(1).setText("");
                    infoTableRowTow.getCell(2).setText("");
                    infoTableRowTow.getCell(3).setText("");
                }

                JlHldz hldz2 = swichJlHldz(hldzs2,j);
                if(hldz!=null){
                    infoTableRowTow.getCell(4).setText(returnString(hldz2.getJg()));
                    infoTableRowTow.getCell(5).setText(returnString(hldz2.getHl()));
                    infoTableRowTow.getCell(6).setText(returnString2(hldz2.getZzc()));
                    infoTableRowTow.getCell(7).setText(returnString2(hldz2.getCtc()));
                }else{
                    infoTableRowTow.getCell(4).setText("");
                    infoTableRowTow.getCell(5).setText("");
                    infoTableRowTow.getCell(6).setText("");
                    infoTableRowTow.getCell(7).setText("");
                }

                setCellTextAlign(infoTableRowTow.getCell(0));
                setCellTextAlign(infoTableRowTow.getCell(1));
                setCellTextRight(infoTableRowTow.getCell(2));
                setCellTextRight(infoTableRowTow.getCell(3));
                setCellTextAlign( infoTableRowTow.getCell(4));
                setCellTextAlign(infoTableRowTow.getCell(5));
                setCellTextRight(infoTableRowTow.getCell(6));
                setCellTextRight(infoTableRowTow.getCell(7));
            }
            if(p%4==0){
                mergeCellsVertically(infoTable,0,p-3,p);
                mergeCellsVertically(infoTable,4,p-3,p);
            }
        }
        return infoTable;
    }
    public JlHldz swichJlHldz(List<JlHldz> hldzs,int jg){
        String hl = "";
        if(jg == 1) hl = "AN";
        if(jg == 2) hl = "BN";
        if(jg == 3) hl = "CN";
        if(jg == 4) hl = "LN";
        for(JlHldz hldz:hldzs){
            if(hldz.getHl().toUpperCase().equals(hl)){
                return hldz;
            }
        }
        return null;
    }
    /***
     * 4、电源性能测试
     * @param document
     * @return
     */
    public XWPFDocument createTableByDyxn(XWPFDocument document, Map map){
        toParagraph(document);
        createTitle(document,"4、电源性能测试");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("项次");
        infoTableRowOne.addNewTableCell().setText("测试功能");
        infoTableRowOne.addNewTableCell().setText("技术标准或测试要求");
        infoTableRowOne.addNewTableCell().setText("检测结果");
        setCellWidth(infoTableRowOne.getCell(0),180);
        setCellWidth(infoTableRowOne.getCell(3),180);
        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));

        //表格第2行
        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("1");
        setCellTextAlign( infoTableRowTow.getCell(0));
        infoTableRowTow.getCell(1).setText("装置电源同时接纳交、直流供电，交、直流自动无扰动切换");
        infoTableRowTow.getCell(2).setText("装置同时接收两路交流电源互为备用方式供电；" +
                "交流供电时同时给直流电源蓄电池充电，交流失电自动切换到直流供电，交流恢复自动切换到交流供电。切换过程装置工作正常。交流输入有状态指示灯及遥信");
        infoTableRowTow.getCell(3).setText(switchJcjg("0",returnString(map.get("R1_JG"))));
        setCellTextAlign( infoTableRowTow.getCell(3));

        //表格第3行
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("2");
        setCellTextAlign( infoTableRowThree.getCell(0));
        infoTableRowThree.getCell(1).setText("后备电源");
        infoTableRowThree.getCell(2).setText("电池容量（参考标准8.5Ah）");
        infoTableRowThree.getCell(3).setText(switchJcjg("0",returnString(map.get("R2_JG"))));
        setCellTextAlign( infoTableRowThree.getCell(3));

        //表格第4行
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("3");
        setCellTextAlign( infoTableRowFour.getCell(0));
        infoTableRowFour.getCell(1).setText("");
        infoTableRowFour.getCell(2).setText("交流失电告警");
        infoTableRowFour.getCell(3).setText(switchJcjg("0",returnString(map.get("R3_JG"))));
        setCellTextAlign( infoTableRowFour.getCell(3));

        //表格第5行
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("4");
        setCellTextAlign( infoTableRowFive.getCell(0));
        infoTableRowFive.getCell(1).setText("");
        infoTableRowFive.getCell(2).setText("任一交流失电、后备电源更换或失效等情况都不影响终端及通信功能和开关的分合储能操作");
        infoTableRowFive.getCell(3).setText(switchJcjg("0",returnString(map.get("R4_JG"))));
        setCellTextAlign( infoTableRowFive.getCell(3));

        //表格第6行
        XWPFTableRow infoTableRowSix = infoTable.createRow();
        infoTableRowSix.getCell(0).setText("5");
        setCellTextAlign( infoTableRowSix.getCell(0));
        infoTableRowSix.getCell(1).setText("");
        infoTableRowSix.getCell(2).setText("具备蓄电池活化管理功能，能够自动实现对蓄电池的定期充放电，且活化周期、起始时间可设置。");
        infoTableRowSix.getCell(3).setText(switchJcjg("0",returnString(map.get("R5_JG"))));
        setCellTextAlign( infoTableRowSix.getCell(3));

        mergeCellsVertically(infoTable,1,2,5);
        createRemark(document,returnString(map.get("REMARK")));
        return document;
    }

    /***
     * 5、电流、电压零漂校验
     * @param document
     * @return
     */
    public XWPFDocument createTableByLpjy(XWPFDocument document,List<List<JlLpjy>> lpjyList,List<List<JlLpjy>> lpjyByDyList,String remark){
        toParagraph(document);
        createTitle(document,"5、电流、电压零漂校验（测量值在0.1%以内）");
        createTitle(document,"测量步骤：");
        createTitle(document,"在未加电流和电压的时候，观察并记录DTU管理软件显示的实时数据。");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("间隔");
        infoTableRowOne.addNewTableCell().setText("回路");
        infoTableRowOne.addNewTableCell().setText("装置读数");
        infoTableRowOne.addNewTableCell().setText("检查结果");
        infoTableRowOne.addNewTableCell().setText("间隔");
        infoTableRowOne.addNewTableCell().setText("回路");
        infoTableRowOne.addNewTableCell().setText("装置读数");
        infoTableRowOne.addNewTableCell().setText("检查结果");
        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));
        setCellTextAlign( infoTableRowOne.getCell(4));
        setCellTextAlign( infoTableRowOne.getCell(5));
        setCellTextAlign( infoTableRowOne.getCell(6));
        setCellTextAlign( infoTableRowOne.getCell(7));


        int jgs = lpjyList.size();
        if(jgs%2 == 0){
            formatJlLpjy(infoTable,lpjyList);
        }else {
            List<JlLpjy> last = lpjyList.get(jgs - 1);
            lpjyList.remove(jgs - 1);
            formatJlLpjy(infoTable, lpjyList);
            int p=0;
            for (int j = 1; j <= 4; j++) {
                p =p+1;
                XWPFTableRow infoTableRowTow = infoTable.createRow();
                JlLpjy lpjy = swichJlLpjy(last, j);
                if (lpjy != null) {
                    infoTableRowTow.getCell(0).setText(returnString(lpjy.getJg()));
                    infoTableRowTow.getCell(1).setText(returnString(lpjy.getHl()));
                    infoTableRowTow.getCell(2).setText(returnString2(lpjy.getZzds()));
                    infoTableRowTow.getCell(3).setText(switchJcjg("2",returnString(lpjy.getJcjg())));
                } else {
                    infoTableRowTow.getCell(0).setText("");
                    infoTableRowTow.getCell(1).setText("");
                    infoTableRowTow.getCell(2).setText("");
                    infoTableRowTow.getCell(3).setText("");
                }
                infoTableRowTow.getCell(4).setText("");
                infoTableRowTow.getCell(5).setText("");
                infoTableRowTow.getCell(6).setText("");
                infoTableRowTow.getCell(7).setText("");
                setCellTextAlign( infoTableRowTow.getCell(0));
                setCellTextAlign( infoTableRowTow.getCell(1));
                setCellTextAlign( infoTableRowTow.getCell(2));
                setCellTextAlign( infoTableRowTow.getCell(3));
            }
            mergeCellsVertically(infoTable,0,infoTable.getRows().size()-4,infoTable.getRows().size()-1);
            mergeCellsVertically(infoTable,4,infoTable.getRows().size()-4,infoTable.getRows().size()-1);

        }
        if(lpjyByDyList != null && lpjyByDyList.size()==2){
            List<JlLpjy> dys =lpjyByDyList.get(0);
            JlLpjy lpjy = dys.get(0);
            JlLpjy lpjy2 = dys.get(1);

            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText(returnString(lpjy.getJg()));
            infoTableRowTow.getCell(1).setText(returnString(lpjy.getHl()));
            infoTableRowTow.getCell(2).setText(returnString2(lpjy.getZzds()));
            infoTableRowTow.getCell(3).setText(switchJcjg("2",returnString(lpjy.getJcjg())));
            infoTableRowTow.getCell(4).setText(returnString(lpjy2.getJg()));
            infoTableRowTow.getCell(5).setText(returnString(lpjy2.getHl()));
            infoTableRowTow.getCell(6).setText(returnString2(lpjy2.getZzds()));
            infoTableRowTow.getCell(7).setText(switchJcjg("2",returnString(lpjy2.getJcjg())));

            setCellTextAlign( infoTableRowTow.getCell(0));
            setCellTextAlign( infoTableRowTow.getCell(1));
            setCellTextAlign( infoTableRowTow.getCell(2));
            setCellTextAlign( infoTableRowTow.getCell(3));
            setCellTextAlign( infoTableRowTow.getCell(4));
            setCellTextAlign( infoTableRowTow.getCell(5));
            setCellTextAlign( infoTableRowTow.getCell(6));
            setCellTextAlign( infoTableRowTow.getCell(7));
        }

        createRemark(document,remark);
        return document;
    }

    //简单的问题复杂化
    public XWPFTable  formatJlLpjy(XWPFTable infoTable,List<List<JlLpjy>> lpjys){
        int jgs = lpjys.size();
        //判断间隔数量是否可以完整排列
        List<JlLpjy> lpjys1 = null;
        List<JlLpjy> lpjys2 = null;
        int p=0;
        for(int i=0;i<jgs;i=i+2){
            lpjys1 = lpjys.get(i);
            lpjys2 = lpjys.get(i+1);
            for(int j = 1;j<=4;j++){
                p =p+1;
                //表格第2行
                XWPFTableRow infoTableRowTow = infoTable.createRow();
                JlLpjy lpjy = swichJlLpjy(lpjys1,j);
                if(lpjy!=null){
                    infoTableRowTow.getCell(0).setText(returnString(lpjy.getJg()));
                    infoTableRowTow.getCell(1).setText(returnString(lpjy.getHl()));
                    infoTableRowTow.getCell(2).setText(returnString2(lpjy.getZzds()));
                    infoTableRowTow.getCell(3).setText(switchJcjg("2",returnString(lpjy.getJcjg())));
                }else{
                    infoTableRowTow.getCell(0).setText("");
                    infoTableRowTow.getCell(1).setText("");
                    infoTableRowTow.getCell(2).setText("");
                    infoTableRowTow.getCell(3).setText("");
                }

                JlLpjy lpjy2 = swichJlLpjy(lpjys2,j);
                if(lpjy2!=null){
                    infoTableRowTow.getCell(4).setText(returnString(lpjy2.getJg()));
                    infoTableRowTow.getCell(5).setText(returnString(lpjy2.getHl()));
                    infoTableRowTow.getCell(6).setText(returnString2(lpjy2.getZzds()));
                    infoTableRowTow.getCell(7).setText(switchJcjg("2",returnString(lpjy2.getJcjg())));
                }else{
                    infoTableRowTow.getCell(4).setText("");
                    infoTableRowTow.getCell(5).setText("");
                    infoTableRowTow.getCell(6).setText("");
                    infoTableRowTow.getCell(7).setText("");
                }
                setCellTextAlign( infoTableRowTow.getCell(0));
                setCellTextAlign( infoTableRowTow.getCell(1));
                setCellTextAlign( infoTableRowTow.getCell(2));
                setCellTextAlign( infoTableRowTow.getCell(3));
                setCellTextAlign( infoTableRowTow.getCell(4));
                setCellTextAlign( infoTableRowTow.getCell(5));
                setCellTextAlign( infoTableRowTow.getCell(6));
                setCellTextAlign( infoTableRowTow.getCell(7));
            }
            if(p%4==0){
                mergeCellsVertically(infoTable,0,p-3,p);
                mergeCellsVertically(infoTable,4,p-3,p);
            }
        }
        return infoTable;
    }
    public JlLpjy swichJlLpjy(List<JlLpjy> lpjys,int jg){
        String hl = "";
        if(jg == 1) hl = "IA";
        if(jg == 2) hl = "IB";
        if(jg == 3) hl = "IC";
        if(jg == 4) hl = "I0";
        for(JlLpjy lpjy:lpjys){
            if(lpjy.getHl().toUpperCase().equals(hl)){
                return lpjy;
            }
        }
        return null;
    }

    /***
     * 6、采样精度测试
     * @param document
     * @param jlCyjds
     * @return
     */
    public XWPFDocument createTableByCyjd(XWPFDocument document, List<JlCyjd> jlCyjds, String remark) {
        toParagraph(document);
        createTitle(document, "6、采样精度测试（测量值在0.5%以内）");
        createTitle(document, "测量步骤：");
        createTitle(document, "（1）满度测量值应在4.975~5.025A以内");
        createTitle(document, "（2）半度测量值应在2.488~2.513A以内");
        XWPFTable infoTable = createTable(document);

        //表格第一行

        XWPFTableRow infoTableRowOne = infoTable.getRow(0);

        infoTableRowOne.getCell(0).setText("模拟量采集单元测试记录");
        setCellTextAlign(infoTableRowOne.getCell(0));
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("");
        /*infoTable.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 8) * (7 - 0 + 1)));*/

        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("间隔");
        infoTableRowTow.getCell(1).setText("测试功能");
        infoTableRowTow.getCell(2).setText("测量项(额定值)");
        infoTableRowTow.getCell(3).setText("读值");
        infoTableRowTow.getCell(4).setText("");
        infoTableRowTow.getCell(5).setText("");
        infoTableRowTow.getCell(6).setText("");
        infoTableRowTow.getCell(7).setText("结果");

        setCellWidth(infoTableRowTow.getCell(0),60);
        setCellWidth(infoTableRowTow.getCell(1),90);
        setCellWidth(infoTableRowTow.getCell(2),90);
        setCellWidth(infoTableRowTow.getCell(3),90);
        setCellWidth(infoTableRowTow.getCell(4),90);
        setCellWidth(infoTableRowTow.getCell(5),90);
        setCellWidth(infoTableRowTow.getCell(6),90);
        setCellWidth(infoTableRowTow.getCell(7),90);

        setCellTextAlign(infoTableRowTow.getCell(0));
        setCellTextAlign(infoTableRowTow.getCell(1));
        setCellTextAlign(infoTableRowTow.getCell(2));
        setCellTextAlign(infoTableRowTow.getCell(3));
        setCellTextAlign(infoTableRowTow.getCell(7));

        /*infoTable.getRow(1).getCell(3).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 8) * (6- 3 + 1)));*/

        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("");
        infoTableRowThree.getCell(1).setText("");
        infoTableRowThree.getCell(2).setText("");
        infoTableRowThree.getCell(3).setText("A");
        infoTableRowThree.getCell(4).setText("B");
        infoTableRowThree.getCell(5).setText("C");
        infoTableRowThree.getCell(6).setText("零序");
        infoTableRowThree.getCell(7).setText("");
        setCellWidth(infoTableRowThree.getCell(0),60);
        setCellWidth(infoTableRowThree.getCell(1),90);
        setCellWidth(infoTableRowThree.getCell(2),90);
        setCellWidth(infoTableRowThree.getCell(3),90);
        setCellWidth(infoTableRowThree.getCell(4),90);
        setCellWidth(infoTableRowThree.getCell(5),90);
        setCellWidth(infoTableRowThree.getCell(6),90);
        setCellWidth(infoTableRowThree.getCell(7),90);

        setCellTextAlign(infoTableRowThree.getCell(3));
        setCellTextAlign(infoTableRowThree.getCell(4));
        setCellTextAlign(infoTableRowThree.getCell(5));
        setCellTextAlign(infoTableRowThree.getCell(6));
        setCellTextAlign(infoTableRowThree.getCell(7));


        mergeCellsHorizontal(infoTable,0,0,7);
        mergeCellsHorizontal(infoTable,1,3,6);
        mergeCellsVertically(infoTable,0,1,2);
        mergeCellsVertically(infoTable,1,1,2);
        mergeCellsVertically(infoTable,2,1,2);
        mergeCellsVertically(infoTable,7,1,2);

        if(jlCyjds != null && jlCyjds.size()>0) {
            //一下需要重复
            int j = 3;
            int rows = jlCyjds.size();
            for (int i = 1; i <= rows; i++) {
                JlCyjd cyjd = jlCyjds.get(i - 1);
                XWPFTableRow infoTableRow1 = infoTable.createRow();
                infoTableRow1.getCell(0).setText(returnString(cyjd.getJg()));
                infoTableRow1.getCell(1).setText(returnString(cyjd.getCsgn()));
                infoTableRow1.getCell(2).setText(returnString(cyjd.getClx()));
                infoTableRow1.getCell(3).setText(returnString2(cyjd.getA()));
                infoTableRow1.getCell(4).setText(returnString2(cyjd.getB()));
                infoTableRow1.getCell(5).setText(returnString2(cyjd.getC()));
                infoTableRow1.getCell(6).setText(returnString2(cyjd.getLx()));
                infoTableRow1.getCell(7).setText(switchJcjg("2", returnString(cyjd.getCsjg())));

                setCellWidth(infoTableRow1.getCell(0),60);
                setCellWidth(infoTableRow1.getCell(1),90);
                setCellWidth(infoTableRow1.getCell(2),90);
                setCellWidth(infoTableRow1.getCell(3),90);
                setCellWidth(infoTableRow1.getCell(4),90);
                setCellWidth(infoTableRow1.getCell(5),90);
                setCellWidth(infoTableRow1.getCell(6),90);
                setCellWidth(infoTableRow1.getCell(7),90);

                setCellTextAlign( infoTableRow1.getCell(0));
                setCellTextRight(infoTableRow1.getCell(3));
                setCellTextRight(infoTableRow1.getCell(4));
                setCellTextRight(infoTableRow1.getCell(5));
                setCellTextRight(infoTableRow1.getCell(6));
                setCellTextAlign( infoTableRow1.getCell(7));
                if (i % 2 == 0) {
                    mergeCellsVertically(infoTable, 0, j, j + 1);
                    mergeCellsVertically(infoTable, 1, j, j + 1);
                    j += 2;
                }
            }
        }
        createRemark(document, returnString(remark));
        return document;
    }

    /***
     * 7、模拟CT一次电流验证回路
     * @param document
     * @param jlDlhls
     * @return
     */
    public XWPFDocument createTableByDlhl(XWPFDocument document, List<JlDlhl> jlDlhls, String remark) {
        toParagraph(document);
        createTitle(document, "7、模拟CT一次电流验证回路");
        createTitle(document, "测量步骤：");
        createTitle(document, "（1）在全部A、C相和零序CT缠绕绝缘导线，在相间CT上缠绕三圈，在零序CT缠绕两圈。");
        createTitle(document, "（2）继保仪Ia、In输出10A电流，分别用鳄鱼夹接导线两端。");
        createTitle(document, "（3）DTU二次值读数乘以CT变比，将计算值填入下表。");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("间隔");
        infoTableRowOne.addNewTableCell().setText("测试功能");
        infoTableRowOne.addNewTableCell().setText("测试方法");
        infoTableRowOne.addNewTableCell().setText("读值");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("结果");
        mergeCellsHorizontal(infoTable,0,3,6);
        setCellWidth(infoTableRowOne.getCell(0),90);
        setCellWidth(infoTableRowOne.getCell(1),90);
        setCellWidth(infoTableRowOne.getCell(2),270);
        setCellWidth(infoTableRowOne.getCell(3),90);
        setCellWidth(infoTableRowOne.getCell(4),90);
        setCellWidth(infoTableRowOne.getCell(5),90);
        setCellWidth(infoTableRowOne.getCell(6),90);
        setCellWidth(infoTableRowOne.getCell(7),90);
        /*infoTable.getRow(0).getCell(3).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 8) * (6 - 3 + 1)));*/

        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));
        setCellTextAlign( infoTableRowOne.getCell(4));
        setCellTextAlign( infoTableRowOne.getCell(5));
        setCellTextAlign( infoTableRowOne.getCell(6));
        setCellTextAlign( infoTableRowOne.getCell(7));

        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("");
        infoTableRowTow.getCell(1).setText("");
        infoTableRowTow.getCell(2).setText("");
        infoTableRowTow.getCell(3).setText("A(30A)");
        infoTableRowTow.getCell(4).setText("B(30A)");
        infoTableRowTow.getCell(5).setText("C(30A)");
        infoTableRowTow.getCell(6).setText("零(20A)");
        infoTableRowTow.getCell(7).setText("");
        setCellWidth(infoTableRowTow.getCell(0),90);
        setCellWidth(infoTableRowTow.getCell(1),90);
        setCellWidth(infoTableRowTow.getCell(2),270);
        setCellWidth(infoTableRowTow.getCell(3),90);
        setCellWidth(infoTableRowTow.getCell(4),90);
        setCellWidth(infoTableRowTow.getCell(5),90);
        setCellWidth(infoTableRowTow.getCell(6),90);
        setCellWidth(infoTableRowTow.getCell(7),90);

        setCellTextAlign( infoTableRowTow.getCell(3));
        setCellTextAlign( infoTableRowTow.getCell(4));
        setCellTextAlign( infoTableRowTow.getCell(5));
        setCellTextAlign( infoTableRowTow.getCell(6));

        mergeCellsVertically(infoTable,0,0,1);
        mergeCellsVertically(infoTable,1,0,1);
        mergeCellsVertically(infoTable,2,0,1);
        mergeCellsVertically(infoTable,7,0,1);

        if(jlDlhls != null && jlDlhls.size()>0) {
            int rows = jlDlhls.size();
            //以下需重复
            for (int i = 0; i < rows; i++) {
                JlDlhl dlhl = jlDlhls.get(i);
                XWPFTableRow infoTableRow1 = infoTable.createRow();
                infoTableRow1.getCell(0).setText(returnString(dlhl.getJg()));
                setCellTextAlign( infoTableRow1.getCell(0));
                infoTableRow1.getCell(1).setText(returnString(dlhl.getCsgn()));
                infoTableRow1.getCell(2).setText(returnString(dlhl.getCsff()));
                infoTableRow1.getCell(3).setText(returnString2(dlhl.getA()));
                infoTableRow1.getCell(4).setText(returnString2(dlhl.getB()));
                infoTableRow1.getCell(5).setText(returnString2(dlhl.getC()));
                infoTableRow1.getCell(6).setText(returnString2(dlhl.getLx()));
                infoTableRow1.getCell(7).setText(switchJcjg("2", returnString(dlhl.getCsjg())));

                setCellWidth(infoTableRow1.getCell(0),90);
                setCellWidth(infoTableRow1.getCell(1),90);
                setCellWidth(infoTableRow1.getCell(2),270);
                setCellWidth(infoTableRow1.getCell(3),90);
                setCellWidth(infoTableRow1.getCell(4),90);
                setCellWidth(infoTableRow1.getCell(5),90);
                setCellWidth(infoTableRow1.getCell(6),90);
                setCellWidth(infoTableRow1.getCell(7),90);

                setCellTextRight(infoTableRow1.getCell(3));
                setCellTextRight(infoTableRow1.getCell(4));
                setCellTextRight(infoTableRow1.getCell(5));
                setCellTextRight(infoTableRow1.getCell(6));
                setCellTextAlign( infoTableRow1.getCell(7));
            }
        }
        createRemark(document, returnString(remark));
        return document;
    }

    /***
     *8、功能调试
     * @param document
     * @return
     */
    public XWPFDocument createTableByGncs(XWPFDocument document, Map map) {
        toParagraph(document);
        createTitle(document,"8、功能调试");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("项次");
        setCellTextAlign( infoTableRowOne.getCell(0));
        infoTableRowOne.addNewTableCell().setText("测试功能");
        infoTableRowOne.addNewTableCell().setText("技术标准或测试要求");
        infoTableRowOne.addNewTableCell().setText("结果");
        setCellWidth(infoTableRowOne.getCell(0),180);
        setCellWidth(infoTableRowOne.getCell(3),180);
        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));

        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("1");
        setCellTextAlign( infoTableRowTow.getCell(0));
        infoTableRowTow.getCell(1).setText("遥控明确断开点测试");
        infoTableRowTow.getCell(2).setText("装置具备遥控硬压板");
        infoTableRowTow.getCell(3).setText(switchJcjg("0",returnString(map.get("R1_JG"))));
        setCellTextAlign( infoTableRowTow.getCell(3));

        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("2");
        setCellTextAlign( infoTableRowThree.getCell(0));
        infoTableRowThree.getCell(1).setText("故障指示");
        infoTableRowThree.getCell(2).setText("具有当地馈线故障指示");
        infoTableRowThree.getCell(3).setText(switchJcjg("0",returnString(map.get("R2_JG"))));
        setCellTextAlign( infoTableRowThree.getCell(3));

        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("3");
        setCellTextAlign( infoTableRowFour.getCell(0));
        infoTableRowFour.getCell(1).setText("故障信号复归测试");
        infoTableRowFour.getCell(2).setText("故障指示灯具备自动复归功能（可设定时延）");
        infoTableRowFour.getCell(3).setText(switchJcjg("0",returnString(map.get("R3_JG"))));
        setCellTextAlign( infoTableRowFour.getCell(3));

        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("");
        setCellTextAlign( infoTableRowFive.getCell(0));
        infoTableRowFive.getCell(1).setText("");
        infoTableRowFive.getCell(2).setText("故障指示灯可本地复归");
        infoTableRowFive.getCell(3).setText(switchJcjg("0",returnString(map.get("R4_JG"))));
        setCellTextAlign( infoTableRowFive.getCell(3));

        mergeCellsVertically(infoTable,0,3,4);
        mergeCellsVertically(infoTable,1,3,4);

        createRemark(document,returnString(map.get("REMARK")));
        return document;
    }

    /***
     * 9、CT安装工艺检查
     * @param document
     * @return
     */
    public XWPFDocument createTableByCtgy(XWPFDocument document, Map map) {
        toParagraph(document);
        createTitle(document,"9、CT安装工艺检查");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("项目");
        infoTableRowOne.addNewTableCell().setText("安装要求");
        infoTableRowOne.addNewTableCell().setText("结果");
        setCellWidth(infoTableRowOne.getCell(0),180);
        setCellWidth(infoTableRowOne.getCell(2),180);
        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));

        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("1");
        setCellTextAlign( infoTableRowTow.getCell(0));
        infoTableRowTow.getCell(1).setText("GB 50168-92《电气装置安装工程电缆线路施工及验收规范》第六章〈电缆终端和接头的制作〉第6.2.10条规定："+
                "电缆通过零序电流互感器时，电缆金属护层和接地线应对地绝缘，电缆接地点在互感器以下时，接地线应直接接地；接地点在互感器以上时，接地线应穿过互感器接地。");
        infoTableRowTow.getCell(2).setText(switchJcjg("0",returnString(map.get("R1_JG"))));
        setCellTextAlign( infoTableRowTow.getCell(2));

        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("2");
        setCellTextAlign( infoTableRowThree.getCell(0));
        infoTableRowThree.getCell(1).setText("CT接地小辫不能造成CT回路短路");
        infoTableRowThree.getCell(2).setText(switchJcjg("0",returnString(map.get("R2_JG"))));
        setCellTextAlign( infoTableRowThree.getCell(2));

        createRemark(document,returnString(map.get("REMARK")));
        return document;
    }

    /***
     *  10、遥信功能调试  10.1 开关柜
     * @param document
     * @param jlKggs
     * @return
     */
    public XWPFDocument createTableByKgg(XWPFDocument document, List<JlKgg> jlKggs, String remark, JbxxGjdz jbxxGjdz) {
        toParagraph(document);
        createTitle(document,"10、遥信功能调试");
        createTitle(document,"10.1 开关柜");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("间隔");
        infoTableRowOne.addNewTableCell().setText("手把");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("地刀");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("开关");
        infoTableRowOne.addNewTableCell().setText("");

        mergeCellsHorizontal(infoTable, 0, 1, 2);
        mergeCellsHorizontal(infoTable, 0, 3, 4);
        mergeCellsHorizontal(infoTable, 0, 5, 6);
        infoTable.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 7) * (2 - 1 + 1)));
        infoTable.getRow(0).getCell(3).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 7) * (4 - 3 + 1)));
        infoTable.getRow(0).getCell(5).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 7) * (6 - 5 + 1)));
        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));
        setCellTextAlign( infoTableRowOne.getCell(4));
        setCellTextAlign( infoTableRowOne.getCell(5));
        setCellTextAlign( infoTableRowOne.getCell(6));

        //表格第2行
        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("");
        infoTableRowTow.getCell(1).setText("远方");
        infoTableRowTow.getCell(2).setText("就地");
        infoTableRowTow.getCell(3).setText("分");
        infoTableRowTow.getCell(4).setText("合");
        infoTableRowTow.getCell(5).setText("分");
        infoTableRowTow.getCell(6).setText("合");
        setCellWidth(infoTableRowTow.getCell(1),240);
        setCellWidth(infoTableRowTow.getCell(3),240);
        setCellWidth(infoTableRowTow.getCell(5),240);

        mergeCellsVertically(infoTable, 0, 0, 1);
        setCellTextAlign( infoTableRowTow.getCell(0));
        setCellTextAlign( infoTableRowTow.getCell(1));
        setCellTextAlign( infoTableRowTow.getCell(2));
        setCellTextAlign( infoTableRowTow.getCell(3));
        setCellTextAlign( infoTableRowTow.getCell(4));
        setCellTextAlign( infoTableRowTow.getCell(5));
        setCellTextAlign( infoTableRowTow.getCell(6));

        if(jlKggs != null && jlKggs.size()>0) {
            int rows = jlKggs.size();
            for (int i = 0; i < rows; i++) {
                JlKgg jlKgg = jlKggs.get(i);
                XWPFTableRow infoTableRows = infoTable.createRow();
                infoTableRows.getCell(0).setText(returnString(jlKgg.getJg()));
                infoTableRows.getCell(1).setText(switchJcjg("1", returnString(jlKgg.getSbyf())));
                infoTableRows.getCell(2).setText(switchJcjg("1", returnString(jlKgg.getSbjd())));
                infoTableRows.getCell(3).setText(switchJcjg("1", returnString(jlKgg.getDdf())));
                infoTableRows.getCell(4).setText(switchJcjg("1", returnString(jlKgg.getDdh())));
                infoTableRows.getCell(5).setText(switchJcjg("1", returnString(jlKgg.getKgf())));
                infoTableRows.getCell(6).setText(switchJcjg("1", returnString(jlKgg.getKgh())));
                setCellTextAlign( infoTableRows.getCell(0));
                setCellTextAlign( infoTableRows.getCell(1));
                setCellTextAlign( infoTableRows.getCell(2));
                setCellTextAlign( infoTableRows.getCell(3));
                setCellTextAlign( infoTableRows.getCell(4));
                setCellTextAlign( infoTableRows.getCell(5));
                setCellTextAlign( infoTableRows.getCell(6));
            }

            XWPFTableRow infoTableRowOver = infoTable.createRow();
            infoTableRowOver.getCell(0).setText("1# 箱体气压低告警");
            infoTableRowOver.getCell(1).setText("");
            setCellTextAlign( infoTableRowOver.getCell(0));
            setCellTextAlign( infoTableRowOver.getCell(1));
            infoTableRowOver.getCell(2).setText(returnString(jbxxGjdz.getQygj1()));
            setCellTextAlign( infoTableRowOver.getCell(2));
            infoTableRowOver.getCell(3).setText("");
            infoTableRowOver.getCell(4).setText("2# 箱体气压低告警");
            setCellTextAlign( infoTableRowOver.getCell(4));
            setCellTextAlign( infoTableRowOver.getCell(5));
            infoTableRowOver.getCell(5).setText(returnString(jbxxGjdz.getQygj2()));
            infoTableRowOver.getCell(6).setText("");
            int overRows = infoTable.getRows().size();
            mergeCellsHorizontal(infoTable, overRows - 1, 0, 1);
            mergeCellsHorizontal(infoTable, overRows - 1, 2, 3);
            mergeCellsHorizontal(infoTable, overRows - 1, 5, 6);

            createRemark(document, returnString(remark));
        }
        return document;
    }

    /***
     *  10.2 DTU
     * @param document
     * @param jlDtu
     * @return
     */
    public XWPFDocument createTableByDtu(XWPFDocument document, JlDtu jlDtu, String remark) {
        toParagraph(document);
        createTitle(document,"10.2 DTU");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("远方");
        infoTableRowOne.addNewTableCell().setText("就地");
        infoTableRowOne.addNewTableCell().setText("电池欠压");
        infoTableRowOne.addNewTableCell().setText("电池活化");
        infoTableRowOne.addNewTableCell().setText("交流失电");
        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));
        setCellTextAlign( infoTableRowOne.getCell(4));

        //表格第2行
        if(jlDtu!=null){
            XWPFTableRow infoTableRow2 = infoTable.createRow();
            infoTableRow2.getCell(0).setText(switchJcjg("1",returnString(jlDtu.getYf())));
            infoTableRow2.getCell(1).setText(switchJcjg("1",returnString(jlDtu.getJd())));
            infoTableRow2.getCell(2).setText(switchJcjg("1",returnString(jlDtu.getDcqy())));
            infoTableRow2.getCell(3).setText(switchJcjg("1",returnString(jlDtu.getDchh())));
            infoTableRow2.getCell(4).setText(switchJcjg("1",returnString(jlDtu.getJlsd())));
            setCellTextAlign( infoTableRow2.getCell(0));
            setCellTextAlign( infoTableRow2.getCell(1));
            setCellTextAlign( infoTableRow2.getCell(2));
            setCellTextAlign( infoTableRow2.getCell(3));
            setCellTextAlign( infoTableRow2.getCell(4));
        }
        createRemark(document,returnString(remark));
        return document;
    }

    /***
     *   10.3 溢水报警装置
     * @param document
     * @param jlYsbj
     * @return
     */
    public XWPFDocument createTableByYsbj(XWPFDocument document, JlYsbj jlYsbj,String remark) {
        toParagraph(document);
        createTitle(document,"10.3 溢水报警装置");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("溢水报警装置告警");
        infoTableRowOne.addNewTableCell().setText("溢水报警装置动作/复归");
        infoTableRowOne.addNewTableCell().setText("水泵启动/停止");
        infoTableRowOne.addNewTableCell().setText("水泵交流失电告警");

        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));
        if(jlYsbj != null){
            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText(switchJcjg("1",returnString(jlYsbj.getGswgj())));
            infoTableRowTow.getCell(1).setText(switchJcjg("1",returnString(jlYsbj.getDswgj())));
            infoTableRowTow.getCell(2).setText(switchJcjg("1",returnString(jlYsbj.getSbqdgj())));
            infoTableRowTow.getCell(3).setText(switchJcjg("1",returnString(jlYsbj.getYszzyc())));
            setCellTextAlign( infoTableRowTow.getCell(0));
            setCellTextAlign( infoTableRowTow.getCell(1));
            setCellTextAlign( infoTableRowTow.getCell(2));
            setCellTextAlign( infoTableRowTow.getCell(3));

            XWPFTableRow infoTableRowThree = infoTable.createRow();
            infoTableRowThree.getCell(0).setText("水泵启动失败告警");
            infoTableRowThree.getCell(1).setText("水泵停止失败告警");

            XWPFTableRow infoTableRowFour = infoTable.createRow();
            infoTableRowFour.getCell(0).setText("");
            infoTableRowFour.getCell(1).setText("");
            setCellTextAlign( infoTableRowThree.getCell(0));
            setCellTextAlign( infoTableRowThree.getCell(1));
        }

        createRemark(document,returnString(remark));
        return document;
    }

    /***
     *   10.4 箱变
     * @param document
     * @return
     */
    public XWPFDocument createTableByXb(XWPFDocument document, JlXb jlXb,String remark) {
        toParagraph(document);
        createTitle(document,"10.4 箱变");
        XWPFTable infoTable = createTable(document);
        if(jlXb != null){
            //表格第一行
            XWPFTableRow infoTableRowOne = infoTable.getRow(0);
            infoTableRowOne.getCell(0).setText("变压器高温告警");
            infoTableRowOne.addNewTableCell().setText(switchJcjg("1",returnString(jlXb.getByqgwgj())));
            setCellWidth(infoTableRowOne.getCell(1),360);
            setCellTextAlign( infoTableRowOne.getCell(1));

            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText("变压器超温告警");
            infoTableRowTow.getCell(1).setText(switchJcjg("1",returnString(jlXb.getByqcwgj())));
            setCellTextAlign( infoTableRowTow.getCell(1));
        }

        createRemark(document,returnString(remark));
        return document;
    }

    /***
     *  11、遥控功能调试
     * @param document
     * @param jlYktss
     * @return
     */
    public XWPFDocument createTableByYkts(XWPFDocument document,List<JlYkts> jlYktss,String remark) {
        toParagraph(document);
        createTitle(document,"11、遥控功能调试");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("间隔");
        infoTableRowOne.addNewTableCell().setText("遥控");
        infoTableRowOne.addNewTableCell().setText("");
        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));

        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("");
        infoTableRowTow.getCell(1).setText("分");
        infoTableRowTow.getCell(2).setText("合");
        setCellTextAlign( infoTableRowTow.getCell(0));
        setCellTextAlign( infoTableRowTow.getCell(1));
        setCellTextAlign( infoTableRowTow.getCell(2));
        mergeCellsHorizontal(infoTable,0,1,2);
        infoTable.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 3) * (2 - 1 + 1)));
        mergeCellsVertically(infoTable,0,0,1);

        if(jlYktss != null && jlYktss.size()>0) {
            int rows = jlYktss.size();
            //以下需要重复
            for (int i = 0; i < rows; i++) {
                JlYkts jlYkts = jlYktss.get(i);
                XWPFTableRow infoTableRows = infoTable.createRow();
                infoTableRows.getCell(0).setText(returnString(jlYkts.getJg()));
                infoTableRows.getCell(1).setText(switchJcjg("1", returnString(jlYkts.getYkf())));
                infoTableRows.getCell(2).setText(switchJcjg("1", returnString(jlYkts.getYkh())));
                setCellTextAlign( infoTableRows.getCell(0));
                setCellTextAlign( infoTableRows.getCell(1));
                setCellTextAlign( infoTableRows.getCell(2));
            }
        }
        createRemark(document,returnString(remark));
        return document;
    }

    /***
     * 12、遥测功能调试
     * @param document
     * @return
     */
    public XWPFDocument createTableByYcts(XWPFDocument document, Map map) {
        toParagraph(document);
        createTitle(document,"12、遥测功能调试");
        createTitle(document,"测量步骤：");
        createTitle(document,"DTU一般监测母线电压Uac，测试时在电压接线端子A、 C 两相同时加57.735V，此时Uac线电压为100V，主站系数为0.1，母线电压为10KV。");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("");
        infoTableRowOne.addNewTableCell().setText("电压遥测");
        infoTableRowOne.addNewTableCell().setText("");
        setCellTextAlign( infoTableRowOne.getCell(1));

        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("");
        infoTableRowTow.getCell(1).setText("100V");
        infoTableRowTow.getCell(2).setText("220V");
        setCellTextAlign( infoTableRowTow.getCell(1));
        setCellTextAlign( infoTableRowTow.getCell(2));

        mergeCellsHorizontal(infoTable,0,1,2);
        infoTable.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 3) * (2 - 1 + 1)));
        mergeCellsVertically(infoTable,0,0,1);

        List<JlYcts> jlYctss = (List<JlYcts>)map.get("jlYctss");
        if(jlYctss!=null){
            for(JlYcts jlYcts:jlYctss){
                XWPFTableRow infoTableRowThree = infoTable.createRow();
                infoTableRowThree.getCell(0).setText(returnString(jlYcts.getCsxm()));
                infoTableRowThree.getCell(1).setText(returnString(jlYcts.getDdyc1()));
                infoTableRowThree.getCell(2).setText(returnString(jlYcts.getDdyc2()));
                setCellTextAlign(infoTableRowThree.getCell(1));
                setCellTextAlign(infoTableRowThree.getCell(2));
            }
        }
        /*XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("4#母线PT");
        infoTableRowThree.getCell(1).setText(returnString(map.get("R1_100V")));
        infoTableRowThree.getCell(2).setText(returnString(map.get("R1_220V")));

        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("5#母线PT");
        infoTableRowFour.getCell(1).setText(returnString(map.get("R2_100V")));
        infoTableRowFour.getCell(2).setText(returnString(map.get("R2_220V")));

        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("4#母线电压（主站一次值）");
        infoTableRowFive.getCell(1).setText(returnString(map.get("R3_100V")));
        infoTableRowFive.getCell(2).setText(returnString(map.get("R3_220V")));

        XWPFTableRow infoTableRowSix = infoTable.createRow();
        infoTableRowSix.getCell(0).setText("5#母线电压（主站一次值）");
        infoTableRowSix.getCell(1).setText(returnString(map.get("R4_100V")));
        infoTableRowSix.getCell(2).setText(returnString(map.get("R4_220V")));

        XWPFTableRow infoTableRowSeven = infoTable.createRow();
        infoTableRowSeven.getCell(0).setText("溢水报警装置水位值");
        infoTableRowSeven.getCell(1).setText(returnString(map.get("R5_100V")));
        infoTableRowSeven.getCell(2).setText(returnString(map.get("R5_220V")));
        mergeCellsHorizontal(infoTable,4,1,2);
        mergeCellsHorizontal(infoTable,5,1,2);
        mergeCellsHorizontal(infoTable,6,1,2);
        infoTable.getRow(4).getCell(1).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 3) * (2 - 1 + 1)));
        infoTable.getRow(5).getCell(1).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 3) * (2 - 1 + 1)));
        infoTable.getRow(6).getCell(1).getCTTc().addNewTcPr().addNewTcW()
                .setW(BigInteger.valueOf((9072 / 3) * (2 - 1 + 1)));*/

        createRemark(document,returnString(map.get("REMARK")));
        return document;
    }

    /***
     * 13、告警功能调试
     * @param document
     * @return
     */
    public XWPFDocument createTableByGjcs(XWPFDocument document, List<JlGjcs> jlGjcss1, List<JlGjcs> jlGjcss2, String remark, JbxxGjdz jbxxGjdz) {
        toParagraph(document);
        createTitle(document, "13、告警功能调试");
        createTitle(document, "测量步骤：");
        createTitle(document, "（1）在DTU设置过流和零序告警定值。");
        createTitle(document, "（2）在保护装置设置过流和零序定值，并投入跳闸功能。");
        createTitle(document, "（3）分别在测量CT、保护CT和零序CT端子上加电流，记录主站收到的一次电流值。记录是否收到过流告警、零序告警、"+
                "过流保护动作出口告警、零序保护动作出口告警，要求出现以上告警时必须伴随线路总告警。");
        createTitle(document, "过流告警定值：二次  __"+returnString(jbxxGjdz.getGlgj1())
                +"__  A  __"+returnString(jbxxGjdz.getGlgj2())+"__  ms;   CT变比：__"
                +returnString(jbxxGjdz.getGlgj3())+"__");
        createTitle(document, "过流告警测试值：二次  __"+returnString(jbxxGjdz.getGlcs1())
                +"__  A  __"+returnString(jbxxGjdz.getGlcs2())+"__  ms;");
        createTitle(document, "零序告警定值：二次  __"+returnString(jbxxGjdz.getLxgj1())
                +"__  A  __"+returnString(jbxxGjdz.getLxgj2())
                +"__  ms;   CT变比：__"+returnString(jbxxGjdz.getLxgj3())+"__ ");
        createTitle(document, "零序告警测试值：二次  __"+returnString(jbxxGjdz.getLxcs1())
                +"__  A  __"+returnString(jbxxGjdz.getLxcs2())+"__ ms;");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("间隔");
        infoTableRowOne.addNewTableCell().setText("告警类型");
        infoTableRowOne.addNewTableCell().setText("A相");
        infoTableRowOne.addNewTableCell().setText("A相");
        infoTableRowOne.addNewTableCell().setText("B相");
        infoTableRowOne.addNewTableCell().setText("B相");
        infoTableRowOne.addNewTableCell().setText("C相");
        infoTableRowOne.addNewTableCell().setText("C相");
        infoTableRowOne.addNewTableCell().setText("结果");
        setCellTextAlign( infoTableRowOne.getCell(0));
        setCellTextAlign( infoTableRowOne.getCell(1));
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));
        setCellTextAlign( infoTableRowOne.getCell(5));
        setCellTextAlign( infoTableRowOne.getCell(6));
        setCellTextAlign( infoTableRowOne.getCell(7));
        setCellTextAlign( infoTableRowOne.getCell(8));

        //表格第2行
        XWPFTableRow infoTableRow2 = infoTable.createRow();
        infoTableRow2.getCell(0).setText("");
        infoTableRow2.getCell(1).setText("");
        infoTableRow2.getCell(2).setText("0.95倍");
        infoTableRow2.getCell(3).setText("1.05倍");
        infoTableRow2.getCell(4).setText("0.95倍");
        infoTableRow2.getCell(5).setText("1.05倍");
        infoTableRow2.getCell(6).setText("0.95倍");
        infoTableRow2.getCell(7).setText("1.05倍");
        infoTableRow2.getCell(8).setText("");
        setCellTextAlign( infoTableRow2.getCell(2));
        setCellTextAlign( infoTableRow2.getCell(3));
        setCellTextAlign( infoTableRow2.getCell(4));
        setCellTextAlign( infoTableRow2.getCell(5));
        setCellTextAlign( infoTableRow2.getCell(6));
        setCellTextAlign( infoTableRow2.getCell(7));

       /* //表格第3行
        XWPFTableRow infoTableRow3 = infoTable.createRow();
        infoTableRow3.getCell(0).setText("");
        infoTableRow3.getCell(1).setText("");
        infoTableRow3.getCell(2).setText("（4.75A）");
        infoTableRow3.getCell(3).setText("（5.25A）");
        infoTableRow3.getCell(4).setText("（4.75A）");
        infoTableRow3.getCell(5).setText("（5.25A）");
        infoTableRow3.getCell(6).setText("");
        setCellTextAlign( infoTableRowOne.getCell(2));
        setCellTextAlign( infoTableRowOne.getCell(3));
        setCellTextAlign( infoTableRowOne.getCell(4));
        setCellTextAlign( infoTableRowOne.getCell(5));*/

        mergeCellsVertically(infoTable,0,0,1);
        mergeCellsVertically(infoTable,1,0,1);
        mergeCellsVertically(infoTable,8,0,1);
        //mergeCellsVertically(infoTable,6,0,2);

        if(jlGjcss1 != null && jlGjcss1.size()>0) {
            //以下重复
            int rows1 = jlGjcss1.size();
            for (int i = 0; i < rows1; i++) {
                JlGjcs jlGjcs = jlGjcss1.get(i);
                XWPFTableRow infoTableRows = infoTable.createRow();
                infoTableRows.getCell(0).setText(returnString(jlGjcs.getJg()));
                setCellTextAlign( infoTableRows.getCell(0));
                infoTableRows.getCell(1).setText(returnString(jlGjcs.getGjlx()));
                infoTableRows.getCell(2).setText(returnString2(jlGjcs.getA1()));
                infoTableRows.getCell(3).setText(returnString2(jlGjcs.getA2()));
                infoTableRows.getCell(4).setText(returnString2(jlGjcs.getB1()));
                infoTableRows.getCell(5).setText(returnString2(jlGjcs.getB2()));
                infoTableRows.getCell(6).setText(returnString2(jlGjcs.getC1()));
                infoTableRows.getCell(7).setText(returnString2(jlGjcs.getC2()));
                setCellTextRight(infoTableRows.getCell(2));
                setCellTextRight(infoTableRows.getCell(3));
                setCellTextRight(infoTableRows.getCell(4));
                setCellTextRight(infoTableRows.getCell(5));
                setCellTextRight(infoTableRows.getCell(6));
                setCellTextRight(infoTableRows.getCell(7));
                infoTableRows.getCell(8).setText(switchJcjg("2", returnString(jlGjcs.getCsjg1())));
                setCellTextAlign( infoTableRows.getCell(8));
            }
        }

        /** 13、告警功能调试 第二表格 **/
        createTitle(document, "");
        XWPFTable table2 = createTable(document);
        XWPFTableRow table2RowOne = table2.getRow(0);
        table2RowOne.getCell(0).setText("间隔");
        table2RowOne.addNewTableCell().setText("告警类型");
        table2RowOne.addNewTableCell().setText("零序");
        table2RowOne.addNewTableCell().setText("零序");
        table2RowOne.addNewTableCell().setText("结果");
        setCellTextAlign( table2RowOne.getCell(0));
        setCellTextAlign( table2RowOne.getCell(1));
        setCellTextAlign( table2RowOne.getCell(2));
        setCellTextAlign( table2RowOne.getCell(3));
        setCellTextAlign( table2RowOne.getCell(4));


       /* 需要addNewTcPr添加这一步，分开写) CTTc ctTc1 = cell1.getCTTc(); CTTcPr ctTcPr = ctTc1.addNewTcPr();
        CTTcBorders tblBorders = table2RowOne.getCell(2).getCTTc().addNewTcPr().addNewTcBorders();
        tblBorders.addNewRight().setVal(STBorder.NONE);
        CTTcBorders tblBorders2 = table2RowOne.getCell(3).getCTTc().addNewTcPr().addNewTcBorders();
        tblBorders2.addNewLeft().setVal(STBorder.NONE);
        table2RowOne.getCell(2).getCTTc().getTcPr().setTcBorders(tblBorders);
        table2RowOne.getCell(3).getCTTc().getTcPr().setTcBorders(tblBorders2);*/

        XWPFTableRow table2RowTow = table2.createRow();
        table2RowTow.getCell(0).setText("");
        table2RowTow.getCell(0).setText("");
        table2RowTow.getCell(1).setText("");
        table2RowTow.getCell(2).setText("0.95倍");
        table2RowTow.getCell(3).setText("1.05倍");
        table2RowTow.getCell(4).setText("");
        setCellTextAlign( table2RowTow.getCell(2));
        setCellTextAlign( table2RowTow.getCell(3));

        /*XWPFTableRow table2RowThree = table2.createRow();
        table2RowThree.getCell(0).setText("");
        table2RowThree.getCell(1).setText("");
        table2RowThree.getCell(2).setText("（0.95A）");
        table2RowThree.getCell(3).setText("（1.05A）");
        table2RowThree.getCell(4).setText("");
        setCellTextAlign( table2RowThree.getCell(2));
        setCellTextAlign( table2RowThree.getCell(3));*/

        mergeCellsVertically(table2,0,0,1);
        mergeCellsVertically(table2,1,0,1);
        mergeCellsVertically(table2,4,0,1);
        //mergeCellsVertically(table2,4,0,2);
        if(jlGjcss2 != null && jlGjcss2.size()>0) {
            int rows2 = jlGjcss2.size();
            for (int i = 0; i < rows2; i++) {
                JlGjcs jlGjcs = jlGjcss2.get(i);
                XWPFTableRow table2Rows = table2.createRow();
                table2Rows.getCell(0).setText(returnString(jlGjcs.getJg()));
                setCellTextAlign( table2Rows.getCell(0));
                table2Rows.getCell(1).setText(returnString(jlGjcs.getGjlx()));
                table2Rows.getCell(2).setText(returnString2(jlGjcs.getL1()));
                table2Rows.getCell(3).setText(returnString2(jlGjcs.getL2()));
                setCellTextRight(table2Rows.getCell(2));
                setCellTextRight(table2Rows.getCell(3));
                table2Rows.getCell(4).setText(switchJcjg("2", returnString(jlGjcs.getCsjg1())));
                setCellTextAlign( table2Rows.getCell(4));
            }
        }

        createRemark(document,remark);
        createTitle(document,"测试完毕后，注意要退出保护装置跳闸功能。");
        return document;
    }



    public void createRemark(XWPFDocument document,String remark){
        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText("备注："+ (remark==null?"":remark));
        run.setBold(false);
        run.setFontSize(8);
        XWPFRun runBr = firstParagraph.createRun();
    }
    /***
     * 创建标题
     * @param document
     * @param title
     * @return
     */
    public void createTitle(XWPFDocument document,String title){
        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText(title);
        run.setBold(false);
        run.setFontSize(10);
    }
    /***
     * 创建表格
     * @param document
     * @return
     */
    public XWPFTable createTable(XWPFDocument document){
        XWPFTable infoTable = document.createTable();
        //去表格边框
        // infoTable.getCTTbl().getTblPr().unsetTblBorders();

        //列宽自动分割
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072));

        return infoTable;
    }
    /***
     * 当前文档换行
     * @param document
     */
    public void toParagraph(XWPFDocument document){
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun paragraphRun1 = paragraph1.createRun();
        paragraphRun1.setText("\r");
    }
    /***
     * 跨列合并单元格
     * @param table
     * @param row
     * @param fromCell
     * @param toCell
     */
    public   static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if ( cellIndex == fromCell ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                //cell.getCTTc().addNewMoveFrom().addNewBar().setBarPr(CTBarPr.EQUAL);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    /***
     * word跨行并单元格
     * @param table
     * @param col
     * @param fromRow
     * @param toRow
     */
    public void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if ( rowIndex == fromRow ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    /***
     * 设置单元格宽度
     * @param cell
     */
    public void setCellWidth(XWPFTableCell cell,int w) {
        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
        CTTblWidth cellw = tcpr.addNewTcW();
        cellw.setType(STTblWidth.DXA);
        cellw.setW(BigInteger.valueOf(w * 5));
    }

    /***
     * 设置单元格内容居中
     * @param cell
     */
    public void setCellTextAlign(XWPFTableCell cell) {
        CTTc cttc = cell.getCTTc();
        CTTcPr ctPr = cttc.addNewTcPr();
        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
    }
    /***
     * 设置单元格内容靠右
     * @param cell
     */
    public void setCellTextRight(XWPFTableCell cell) {
        CTTc cttc = cell.getCTTc();
        CTTcPr ctPr = cttc.addNewTcPr();
        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);
    }
    /***
     * 设置单元格样式
     * @param cell
     */
    public void setCellStyle(XWPFDocument document,XWPFTableCell cell,String text,
                                     int FontSize,boolean isB,String setFloat) {
        XWPFParagraph p = document.createParagraph();
        p = cell.addParagraph();
        XWPFRun headRun0 = p.createRun();
        if(!text.equals("")) headRun0.setText(text);
        if (FontSize!=0) headRun0.setFontSize(FontSize);
        headRun0.setBold(isB);//是否粗体
        //cell.setColor("DEDEDE");//单元格背景颜色
        //垂直居中
       // p.setVerticalAlignment(TextAlignment.CENTER);
        //水平居中
        if(setFloat.equals("LEFT"))
            p.setAlignment(ParagraphAlignment.LEFT);
        else if(setFloat.equals("CENTER"))
            p.setAlignment(ParagraphAlignment.CENTER);
        if(setFloat.equals("RIGHT"))
            p.setAlignment(ParagraphAlignment.RIGHT);
    }

    /***
     * 设置动态标题等级
     * @param document
     */
    public void setTitleGrade(XWPFDocument document,String grade,String title) {
        XWPFParagraph para1 = document.createParagraph();
        // 关键行// 1级大纲
        para1.setStyle(grade);
        XWPFRun run1 = para1.createRun();
        // 标题内容
        run1.setText(title);
        run1.setFontSize(12);
    }

    public String returnString(Object obj){
        if(obj==null){
            return "";
        }
        return obj.toString();
    }

    public String returnString2(Object obj) {
        if (obj == null) {
            return "";
        } else {
            String result = obj.toString();
            Pattern pattern = Pattern.compile("-?[0-9]*.?[0-9]*");
            Matcher matcher = pattern.matcher((CharSequence) result);
            if (matcher.matches() && result.contains(".")) {
                String a = result.substring(0,result.indexOf("."));
                String b = result.substring(result.indexOf(".")+1);
                if(Integer.valueOf(a) == 0 && Integer.valueOf(b) == 0){
                    return "0";
                }
                if(Integer.valueOf(a) == 0){
                    a =  "0";
                }
                if(Integer.valueOf(b) != 0){
                    for(int i = b.length();i>=0;i--){
                        if(b.substring(b.length()-1).equals("0")){
                            b = b.substring(0,b.length()-1);
                        }else {
                            break;
                        }
                    }
                }else{
                    b = "0";
                }
                if(!b.equals("0")) return String.valueOf(a+"."+b);
                if(b.equals("0")) return String.valueOf(a);
            }
        }
        return obj.toString();
    }


    public String switchJcjg(String type,String jcjg){
        if(type.equals("0")){
            switch (jcjg){
                case "-1":
                    jcjg = "";
                    break;
                case "1":
                    jcjg = "是";
                    break;
                case "0":
                    jcjg = "否";
                    break;
                case "2":
                    jcjg = "N/A";
                    break;

            }
        }else if(type.equals("1")){
            switch (jcjg){
                case "-1":
                    jcjg = "";
                    break;
                case "1":
                    jcjg = "正确";
                    break;
                case "0":
                    jcjg = "错误";
                    break;
                case "2": 
                    jcjg = "N/A";
                    break;
            }
        }else if(type.equals("2")){
            switch (jcjg){
                case "-1":
                    jcjg = "";
                    break;
                case "1":
                    jcjg = "正常";
                    break;
                case "0":
                    jcjg = "超标";
                    break;
                case "2":
                    jcjg = "N/A";
                    break;
            }
        }
        return jcjg;
    }
}