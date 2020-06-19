package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;

@Data
public class BzYkts implements Serializable {
    /* 遥测功能调试标准表 */
    private String id;
    private String ssqy;//所属区域
    private String jg;//间隔
    private String zzlx;
}
