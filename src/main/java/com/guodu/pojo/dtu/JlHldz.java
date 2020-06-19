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
public class JlHldz implements Serializable {
    /*回路电阻测试记录*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String jg;//间隔
    private String hl;//回路
    private String zzc;//装置侧
    private String ctc;//CT侧
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注

}
