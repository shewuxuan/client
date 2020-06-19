package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;

@Data
public class BzGjcs implements Serializable {
    /* 告警功能测试标注表 */
    private String id;
    private String ssqy;//所属区域
    private String jg;//间隔
    private String gjlx;//告警类型
    private String zzlx;
}
