package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;

@Data
public class BzCyjd implements Serializable {
    /* 采样精度标准表 */
    private String id;
    private String ssqy;//所属区域
    private String jg;//间隔
    private String csgn;//测试功能
    private String clx;//测量项（额定值）
    private String zzlx;
}
