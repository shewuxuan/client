package com.guodu.pojo.ftu;

import lombok.Data;

import java.io.Serializable;

@Data
public class FtuBzHbdy implements Serializable {
    /* 后备电源标准 */
    private String id;
    private String ssqy;//所属区域
    private String jcnr;//检查内容
}
