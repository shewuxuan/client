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
public class JlDyxn implements Serializable {
    /*电源性能测试记录*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String csxm;//测试项目
    private String jsbz;//技术标准或测试要求
    private String jcjg;//检测结果
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
}
