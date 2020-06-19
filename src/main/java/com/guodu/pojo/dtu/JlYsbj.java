package com.guodu.pojo.dtu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JlYsbj implements Serializable {
    /*溢水报警装置表*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String gswgj;//高水位告警
    private String dswgj;//低水位告警
    private String sbqdgj;//水泵启动告警
    private String yszzyc;//溢水装置异常
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
}
