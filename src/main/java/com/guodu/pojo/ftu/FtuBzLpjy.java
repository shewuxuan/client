package com.guodu.pojo.ftu;

import lombok.Data;

import java.io.Serializable;

@Data
public class FtuBzLpjy implements Serializable {
    /* 电流电压零漂校验标准 */
    private String id;
    private String ssqy;//所属区域
    private String hl;//回路
}
