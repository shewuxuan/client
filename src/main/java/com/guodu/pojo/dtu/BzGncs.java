package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;

@Data
public class BzGncs implements Serializable {
    /* 功能测试标准表 */
    private String id;
    private String ssqy;//所属区域
    private String csgn;//测试功能
    private String jsbz;//技术标准或测试要求
}
