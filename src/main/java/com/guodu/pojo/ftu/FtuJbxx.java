package com.guodu.pojo.ftu;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FtuJbxx implements Serializable {
    /* FTU基本信息表 */
    private String tsid;//调试Id
    private String ssqy;//所属区域
    private String tssj;//调试时间
    private String ddbh;//调度编号
    private String xlmc;//线路名称
    private String ftuBm;//FTU编码
    private String ysry;//验收人员
    private String ysrq;//验收日期
    private String ysdw;//验收单位
    private String kgCj;//开关本体厂家
    private String kgXh;//开关本体型号
    private String kgScrq;//开关本体生产日期
    /**
     * 相间CT变比
     */
    private String kgXjbb;

    /**
     * 零序CT变比
     */
    private String kgLxbb;
    private String ftuCj;//FTU厂家
    private String ftuXh;//FTU型号
    private String ftuScrq;//FTU生产日期
    private String txsbCj;//通讯设备厂家
    private String txsbXh;//通讯设备型号
    private String txsbScrq;//通讯设备生产日期
    private String wxkh;//无线卡号
    private String ipdz;//IP地址
    private String zzlx;//装置类型
    private String tsryxm;//调试人员姓名

    /**
     * 经度
     */
    private String jd;

    /**
     * 纬度
     */
    private String wd;

    /**
     * 详细位置
     */
    private String xxwz;
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
    private String upload;//是否上传 0未上传1上传
}
