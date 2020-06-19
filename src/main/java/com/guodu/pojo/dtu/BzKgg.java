package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;

@Data
public class BzKgg implements Serializable {
    /* 开关柜标准表 */
    private String id;
    private String ssqy;//所属区域
    private String jg;//间隔
    private String zzlx;
}
