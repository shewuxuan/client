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
public class JlJydz implements Serializable {
    /*绝缘电阻测试记录*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String bchl;//被测回路
    private String sydy;//试验电压（伏）
    private String gcyq;//规程要求(兆欧）
    private String clzz;//测量阻值（兆欧）
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注


}
