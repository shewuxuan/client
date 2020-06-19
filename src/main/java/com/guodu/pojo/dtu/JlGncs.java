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
public class JlGncs implements Serializable {
    /*功能测试记录表*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String csgn;//测试功能
    private String jsbz;//技术标准或测试要求
    private String jg;//结果
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
}
