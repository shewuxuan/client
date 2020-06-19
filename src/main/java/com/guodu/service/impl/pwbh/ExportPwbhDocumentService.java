package com.guodu.service.impl.pwbh;

import com.guodu.pojo.dtu.*;
import com.guodu.pojo.pwbh.*;
import com.guodu.service.pwbh.PwbhJlJxdxService;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by word .
 */
@Service
public class ExportPwbhDocumentService {
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
     * 所需仪器仪表
     * @param document
     * @param pwbhJlYqybs
     * @return
     */
    public XWPFDocument createTableByYqyb(XWPFDocument document, List<PwbhJlYqyb> pwbhJlYqybs){
        createTitle(document,"检验作业流程控制");
        createTitle(document,"1、所需仪器仪表");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.setHeight(10);
        infoTableRowOne.getCell(0).setText("序号");
        infoTableRowOne.addNewTableCell().setText("试验仪器名称");
        infoTableRowOne.addNewTableCell().setText("设备型号");
        infoTableRowOne.addNewTableCell().setText("编号");
        infoTableRowOne.addNewTableCell().setText("合格期限");

        PwbhJlYqyb yqyb = null;
        for(int i=0;i<pwbhJlYqybs.size();i++){
            yqyb = pwbhJlYqybs.get(i);
            XWPFTableRow infoTableRowTwo = infoTable.createRow();
            infoTableRowTwo.getCell(0).setText(String.valueOf(i+1));
            infoTableRowTwo.getCell(1).setText(returnString(yqyb.getYqmc()));
            infoTableRowTwo.getCell(2).setText(returnString(yqyb.getSbxh()));
            infoTableRowTwo.getCell(3).setText(returnString(yqyb.getBh()));
            infoTableRowTwo.getCell(4).setText(returnString(yqyb.getHgqx()));
        }
        return document;
    }

    /***
     * 外观检查
     * @param document
     * @return
     */
    public XWPFDocument createTableByWgjc(XWPFDocument document, List<PwbhJlWgjc> pwbhJlWgjcs){
        toParagraph(document);
        createTitle(document,"2、外观检查");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.setHeight(20);
        infoTableRowOne.getCell(0).setText("内  容");
        infoTableRowOne.addNewTableCell().setText("检查结果");

        for(PwbhJlWgjc pwbhJlWgjc:pwbhJlWgjcs){
            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText(returnString(pwbhJlWgjc.getNr()));
            infoTableRowTow.getCell(1).setText(returnString(pwbhJlWgjc.getJcjg()));
        }
        return document;
    }

    /***
     * 紧线及对线
     * @param document
     * @return
     */
    public XWPFDocument createTableByJxdx(XWPFDocument document, List<PwbhJlJxdx> pwbhJlJxdxs ){
        toParagraph(document);
        createTitle(document,"3、紧线及对线");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("内  容");
        infoTableRowOne.addNewTableCell().setText("检查结果");

        for(PwbhJlJxdx pwbhJlJxdx:pwbhJlJxdxs){
            //表格第2行
            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText(returnString(pwbhJlJxdx.getNr()));
            infoTableRowTow.getCell(1).setText(returnString(pwbhJlJxdx.getJcjg()));
        }
        return document;
    }

    /***
     * 校对时钟
     * @param document
     * @return
     */
    public XWPFDocument createTableByJdsz(XWPFDocument document){
        toParagraph(document);
        createTitle(document,"4、校对时钟");
        createTitle(document,"检验要求：将装置时钟校对至当前时钟，以便于系统故障时进行分析。");
        createTitle(document,"检查结果：");
        return document;
    }
    /***
     * 绝缘测试
     * @param document
     * @return
     */
    public XWPFDocument createTableByJycs(XWPFDocument document, List<PwbhJlJycs> pwbhJlJycss){
        toParagraph(document);
        createTitle(document,"5、绝缘测试");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("检查内容");
        infoTableRowOne.addNewTableCell().setText("标  准");
        infoTableRowOne.addNewTableCell().setText("试验结果");

        for(PwbhJlJycs pwbhJlJycs:pwbhJlJycss){
            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText(returnString(pwbhJlJycs.getJcnr()));
            infoTableRowTow.getCell(1).setText(returnString(pwbhJlJycs.getBz()));
            infoTableRowTow.getCell(2).setText(returnString(pwbhJlJycs.getSyjg()));
        }
        return document;
    }

    /***
     * 定值单检查
     * @param document
     * @return
     */
    public XWPFDocument createTableByDzdjc(XWPFDocument document,List<PwbhJlDzdjc> pwbhJlDzdjcs){
        toParagraph(document);
        createTitle(document,"6、定值单检查:");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("出单日期");
        infoTableRowOne.addNewTableCell().setText("定值单号");
        infoTableRowOne.addNewTableCell().setText("版本");
        infoTableRowOne.addNewTableCell().setText("校验码");
        infoTableRowOne.addNewTableCell().setText("程序形成时间");
        for(PwbhJlDzdjc pwbhJlDzdjc:pwbhJlDzdjcs){
            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText(returnString(pwbhJlDzdjc.getCdrq()));
            infoTableRowTow.getCell(1).setText(returnString(pwbhJlDzdjc.getDzdh()));
            infoTableRowTow.getCell(2).setText(returnString(pwbhJlDzdjc.getBb()));
            infoTableRowTow.getCell(3).setText(returnString(pwbhJlDzdjc.getJym()));
            infoTableRowTow.getCell(4).setText(returnString(pwbhJlDzdjc.getCxsj()));
        }
        return document;
    }


    /***
     * 电流、电压零漂校验
     * @param document
     * @param pwbhJlLpjys
     * @return
     */
    public XWPFDocument createTableByLpjy(XWPFDocument document, List<PwbhJlLpjy> pwbhJlLpjys) {
        toParagraph(document);
        createTitle(document, "7、电流、电压零漂校验");
        XWPFTable infoTable = createTable(document);

        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("测试结果");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("CPU");

        for(PwbhJlLpjy pwbhJlLpjy:pwbhJlLpjys){
            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText("零漂");
            infoTableRowTow.getCell(1).setText(returnString(pwbhJlLpjy.getCsjg()));
            infoTableRowTow.getCell(2).setText(returnString(pwbhJlLpjy.getCpu()));
        }
        return document;
    }

    /***
     * 电流、电压精度检验
     * @param document
     * @param pwbhJlJdjys
     * @return
     */
    public XWPFDocument createTableByJdjy(XWPFDocument document, List<PwbhJlJdjy> pwbhJlJdjys) {
        toParagraph(document);
        createTitle(document, "8、电流、电压精度检验");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("测试结果");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("通入要求值");
        infoTableRowOne.addNewTableCell().setText("装置显示");
        infoTableRowOne.addNewTableCell().setText("检查结果");

        for(PwbhJlJdjy pwbhJlJdjy:pwbhJlJdjys){
            XWPFTableRow infoTableRowTow = infoTable.getRow(0);
            infoTableRowTow.getCell(0).setText("精度");
            infoTableRowTow.getCell(1).setText(returnString(pwbhJlJdjy.getJcjg()));
            infoTableRowTow.getCell(2).setText(returnString(pwbhJlJdjy.getTryq()));
            infoTableRowTow.getCell(3).setText(returnString(pwbhJlJdjy.getZzxs()));
            infoTableRowTow.getCell(4).setText(returnString(pwbhJlJdjy.getJcjg()));
        }
        return document;
    }

    /***
     *保护CT参数、变比试验
     * @param document
     * @return
     */
    public XWPFDocument createTableByBhctjx(XWPFDocument document, List<PwbhJlBhctjx> pwbhJlBhctjxs) {
        toParagraph(document);
        createTitle(document,"9、保护CT参数、变比试验");
        createTitle(document,"9.1 保护CT极性");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("相别");
        infoTableRowOne.addNewTableCell().setText("一次以母线为正");
        infoTableRowOne.addNewTableCell().setText("二次以母线为正");
        infoTableRowOne.addNewTableCell().setText("表计指示");

        for(PwbhJlBhctjx pwbhJlBhctjx:pwbhJlBhctjxs){
            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText(returnString(pwbhJlBhctjx.getXb()));
            infoTableRowTow.getCell(1).setText(returnString(pwbhJlBhctjx.getMx1()));
            infoTableRowTow.getCell(2).setText(returnString(pwbhJlBhctjx.getMx2()));
            infoTableRowTow.getCell(3).setText(returnString(pwbhJlBhctjx.getBjzs()));
        }
        return document;
    }

    /***
     * 保护CT变比
     * @param document
     * @return
     */
    public XWPFDocument createTableByCtgy(XWPFDocument document, List<PwbhJlBhctbh> pwbhJlBhctbhS) {
        toParagraph(document);
        createTitle(document,"9.2 保护CT变比");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("相别");
        infoTableRowOne.addNewTableCell().setText("型号");
        infoTableRowOne.addNewTableCell().setText("线圈号");
        infoTableRowOne.addNewTableCell().setText("参数");
        infoTableRowOne.addNewTableCell().setText("名义变比");
        infoTableRowOne.addNewTableCell().setText("试验变比");

        for(PwbhJlBhctbh pwbhJlBhctbh:pwbhJlBhctbhS){
            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText(returnString(pwbhJlBhctbh.getXb()));
            infoTableRowTow.getCell(1).setText(returnString(pwbhJlBhctbh.getXh()));
            infoTableRowTow.getCell(2).setText(returnString(pwbhJlBhctbh.getXqh()));
            infoTableRowTow.getCell(3).setText(returnString(pwbhJlBhctbh.getCs()));
            infoTableRowTow.getCell(4).setText(returnString(pwbhJlBhctbh.getMybh()));
            infoTableRowTow.getCell(5).setText(returnString(pwbhJlBhctbh.getSybh()));
        }
        return document;
    }

    /***
     * 定值检查
     * @param document
     * @param pwbhJlDzjcs
     * @return
     */
    public XWPFDocument createTableByDzjc(XWPFDocument document, List<PwbhJlDzjc> pwbhJlDzjcs) {
        toParagraph(document);
        createTitle(document,"10、定值检查：");
        createTitle(document,"（1）过流Ⅰ段定值             ,  过流Ⅱ段定值             。");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("");
        infoTableRowOne.addNewTableCell().setText("过流Ⅰ段整定值");
        infoTableRowOne.addNewTableCell().setText("实际通电0.95倍定值／1.05倍定值");
        infoTableRowOne.addNewTableCell().setText("过流Ⅱ段整定值");
        infoTableRowOne.addNewTableCell().setText("实际通电0.95倍定值／1.05倍定值");

        for(PwbhJlDzjc pwbhJlDzjc:pwbhJlDzjcs){
            XWPFTableRow infoTableRowTow = infoTable.createRow();
            infoTableRowTow.getCell(0).setText(returnString(pwbhJlDzjc.getJg()));
            infoTableRowTow.getCell(1).setText(returnString(pwbhJlDzjc.getG1()));
            infoTableRowTow.getCell(2).setText(returnString(pwbhJlDzjc.getG11()+pwbhJlDzjc.getG12()));
            infoTableRowTow.getCell(3).setText(returnString(pwbhJlDzjc.getG2()));
            infoTableRowTow.getCell(4).setText(returnString(pwbhJlDzjc.getG21()+pwbhJlDzjc.getG22()));
        }
        createTitle(document,"过流Ⅰ段时间：      秒,通电       秒,  过流Ⅱ段时间：      秒，通电       秒.");
        createTitle(document,"（2）零序过流Ⅰ段定值           ,  零序过流Ⅱ段定值             。");

        XWPFTable infoTable2 = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne2 = infoTable2.getRow(0);
        infoTableRowOne2.getCell(0).setText("零序Ⅰ段整定值");
        infoTableRowOne2.addNewTableCell().setText("实际通电0.95倍定值／1.05倍定值");
        infoTableRowOne2.addNewTableCell().setText("零序Ⅱ段整定值");
        infoTableRowOne2.addNewTableCell().setText("实际通电0.95倍定值／1.05倍定值");

        XWPFTableRow infoTableRowTow2 = infoTable2.createRow();
        infoTableRowTow2.getCell(0).setText("");
        infoTableRowTow2.getCell(1).setText("");
        infoTableRowTow2.getCell(2).setText("");
        infoTableRowTow2.getCell(3).setText("");
        infoTableRowTow2.getCell(4).setText("");
        createTitle(document,"零序Ⅰ段时间：       秒，通电      秒,  零序Ⅱ段时间：      秒，通电      秒 .");
        return document;
    }

    /***
     *  整组试验及实际断路器传动
     * @param document
     * @return
     */
    public XWPFDocument createTableByDtu(XWPFDocument document) {
        toParagraph(document);
        createTitle(document,"11、整组试验及实际断路器传动");
        createTitle(document,"此项试验前应将本次可以传动的断路器合入，上好出口跳闸压板 ");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("检验项目");
        infoTableRowOne.addNewTableCell().setText("故障模拟");
        infoTableRowOne.addNewTableCell().setText("断路器（或启动信号）");
        infoTableRowOne.addNewTableCell().setText("断路器动作情况（或信号动作情况）");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("检查结果（正确打√）");

        XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText("");
        infoTableRowTow.getCell(1).setText("");
        infoTableRowTow.getCell(2).setText("");
        infoTableRowTow.getCell(3).setText("主站信号");
        infoTableRowTow.getCell(4).setText("装置显示");
        infoTableRowTow.getCell(5).setText("");

        return document;
    }

    /***
     *   保护CT回路接触电阻
     * @param document
     * @return
     */
    public XWPFDocument createTableByYsbj(XWPFDocument document) {
        toParagraph(document);
        createTitle(document,"12、保护CT回路接触电阻：（误差不得超过10％）");
        XWPFTable infoTable = createTable(document);
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("用途：保护");
        infoTableRowOne.addNewTableCell().setText("");
        infoTableRowOne.addNewTableCell().setText("");

        return document;
    }

    /***
     *  收工前需检查的项目
     * @param document
     * @return
     */
    public XWPFDocument createTableByXb(XWPFDocument document) {
        toParagraph(document);
        createTitle(document,"13、 收工前需检查的项目");
        XWPFTable infoTable = createTable(document);
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("检查内容");
        infoTableRowOne.addNewTableCell().setText("结果");

        /*XWPFTableRow infoTableRowTow = infoTable.createRow();
        infoTableRowTow.getCell(0).setText(" ");
        infoTableRowTow.getCell(1).setText("");*/

        createTitle(document,"14、检验结论：（填写发现问题及处理情况、遗留问题，可否投运等）");
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


    public String returnString(Object obj){
        if(obj==null){
            return "";
        }
        return obj.toString();
    }

}