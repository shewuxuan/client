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
public class JlCyjd implements Serializable {
    /*采样精度测试记录表*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String jg;//间隔
    private String csgn;//测试功能
    private String clx;//测量项（额定值）
    private String a;//额定值A
    private String b;//额定值B
    private String c;//额定值C
    private String lx;//额定值零序
    private String csjg;//测试结果
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注

}
