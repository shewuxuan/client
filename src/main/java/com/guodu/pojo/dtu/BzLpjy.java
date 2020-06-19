package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;

@Data
public class BzLpjy implements Serializable {
    /* 电流电压零漂校验表 */
    private String id;
    private String ssqy;//所属区域
    private String jg;//间隔
    private String hl;//回路
    private String zzlx;
}
