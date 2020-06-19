package com.guodu.pojo.dtu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JlCtgy implements Serializable {
    /*CT工艺检查记录表*/
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String azyq;//安装要求
    private String jg;//结果
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
}
