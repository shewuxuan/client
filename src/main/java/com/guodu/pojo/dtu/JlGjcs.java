package com.guodu.pojo.dtu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JlGjcs implements Serializable {
    /*告警功能测试记录表*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String jg;//间隔
    private String gjlx;//告警类型
    private String a1;//A相（0.95倍, 4.75A）
    private String a2;//A相（1.05倍, 5.25A)
    private String b1;//B相
    private String b2;//B相
    private String c1;//C相（0.95倍, 4.75A）
    private String c2;//C相（1.05倍, 5.25A）
    private String l1;//零序（0.95倍, 0.95A）
    private String l2;//零序（1.05倍, 1.05A）
    /**
     * 测试结果1
     */
    private String csjg1;

    /**
     * 测试结果2
     */
    private String csjg2;
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注

}
