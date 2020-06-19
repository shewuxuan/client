package com.guodu.pojo.dtu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JlYcts implements Serializable {
    /*遥测功能调试记录表*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String csxm;//测试项目
    private String ddyc1;//电压遥测1（100V）
    private String ddyc2;//电压遥测2（100V）
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
}
