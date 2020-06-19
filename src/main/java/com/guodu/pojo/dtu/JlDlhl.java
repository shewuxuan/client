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
public class JlDlhl implements Serializable {
    /*模拟CT一次电流验证回路记录表*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String jg;//间隔
    private String csgn;//测试功能
    private String csff;//测试方法
    private String a;//A(30A)
    private String b;//B(30A)
    private String c;//C(30A)
    private String lx;//零序（20A）
    private String csjg;//测试结果
    private String czr;//操作人
    private Date czsj;//测试时间
    private String beizhu;//备注
}
