package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;

@Data
public class BzDyxn implements Serializable {
    /* 电源性能测试标准 */
    private String id;
    private String ssqy;//所属区域
    private String csxm;//测试项目
    private String jsbz;//技术标准或测试要求
}
