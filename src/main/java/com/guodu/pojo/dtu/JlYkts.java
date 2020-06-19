package com.guodu.pojo.dtu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JlYkts implements Serializable {
    /*遥控功能调试记录表*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String jg;//间隔
    private String ykf;//遥控（分）
    private String ykh;//遥控（合）
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
}
