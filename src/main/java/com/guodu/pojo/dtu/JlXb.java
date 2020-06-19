package com.guodu.pojo.dtu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JlXb implements Serializable {
    /*箱变表*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String byqgwgj;//变压器高温告警
    private String byqcwgj;//变压器超温告警
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
}
