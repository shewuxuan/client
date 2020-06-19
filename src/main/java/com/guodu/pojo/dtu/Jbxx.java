package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Jbxx implements Serializable {
    /* 基本信息表 */
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String tssj;//调试时间
    private String azddDdh;//安装地点及调度号
    private String zdmc;//终端名称
    private String ssxl;//所属线路
    /**
     * 变电站
     */
    private  String bdz;

    /**
     * 线路名称
     */
    private String xlmc;
    private String txmkcj;//通信模块厂家
    private String ipdz;//IP地址
    private String wlwkh;//物联网卡号
    private String rtudz;//RTU地址
    private String dkh;//端口号
    private String sccs;//生产厂商
    private String scrq;//生产日期
    private String zzxh;//装置型号
    private String zzbh;//装置编号
    private String zzlx;//装置类型（1开闭器；2分界室；3箱变；4配电室）
    private String rjbbJym;//软件版本及校验码
    private String yjbb;//硬件版本
    private String sccsYcg;//一次柜生产厂商
    private String zbxhYcg;//一次柜装置型号
    private String tsryxmYcg;//一次柜调试人员姓名
    private String sccsYs;//溢水生产厂商
    private String zbxhYs;//溢水柜装置型号
    private String tsryxmYs;//溢水柜调试人员姓名
    private String sccsXf;//消防生产厂商
    private String zbxhXf;//消防装置型号
    private String tsryxmXf;//消防调试人员姓名
    private String jd;//经度
    private String wd;//纬度
    private String xxwz;//详细位置
    private String tsryxm;//调试人员姓名
    private String flagArrayStr;//删除的照片pid
    private String[] imgName;//重置照片名称
    private List<JbxxPhoto> photo;
    /**
     * 0未上传; 1已上传
     */
    private String upload;
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注

    private List<JlCtgy> jlCtgys;
    private List<JlCyjd> jlCyjds;
    private List<JlDlhl> jlDlhls;
    private JlDtu jlDtu;
    private List<JlDyxn> jlDyxns;
    private List<JlGjcs> jlGjcss;
    private List<JlGncs> jlGncss;
    private List<JlHldz> jlHldzs;
    private List<JlJydz> jlJydzs;
    private List<JlKgg> jlKggs;
    private List<JlLpjy> jlLpjys;
    private List<JlWgpz> jlWgpzs;
    private JlXb jlXb;
    private List<JlYcts> jlYctss;
    private List<JlYkts> jlYktss;
    private JlYsbj jlYsbj;

    private JbxxGjdz jbxxGjdz;
    private JbxxBeizhu jbxxBeizhu;

    private static final long serialVersionUID = 1L;
}
