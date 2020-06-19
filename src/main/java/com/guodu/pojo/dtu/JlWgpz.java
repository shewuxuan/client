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
public class JlWgpz implements Serializable {
    /*DTU外观配置测试记录表*/
    private String id;
    private String tsid;//关联调试ID
    private String ssqy;//所属区域(石景山、朝阳等)
    private String csxm;//测试项目
    private String csyq;//技术标准/测试要求
    private String csjg;//测试结果
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
}
