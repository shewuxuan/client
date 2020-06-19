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
public class JlKgg implements Serializable {
    /*开关柜记录表*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String jg;//间隔
    private String sbyf;//手把（远方）
    private String sbjd;//手把（就地）
    private String ddf;//地刀（分）
    private String ddh;//地刀（合）
    private String kgf;//开关（分）
    private String kgh;//开关（合）
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
}
