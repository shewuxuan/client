package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;

@Data
public class BzHldz implements Serializable {
    /* 绝缘电阻测试标准 */
    private String id;
    private String ssqy;//所属区域
    private String jg;//间隔
    private String hl;//回路
    private String zzlx;//装置类型,多个装置类型逗号隔开
}
