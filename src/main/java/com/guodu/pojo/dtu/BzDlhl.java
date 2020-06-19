package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;

@Data
public class BzDlhl implements Serializable {
    /* 模拟CT一次电流验证回路表 */
    private String id;
    private String ssqy;//所属区域
    private String jg;//间隔
    private String csgn;//测试功能
    private String csff;//测试方法
    private String zzlx;
}
