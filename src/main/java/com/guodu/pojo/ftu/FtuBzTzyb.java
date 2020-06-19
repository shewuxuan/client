package com.guodu.pojo.ftu;

import lombok.Data;

import java.io.Serializable;

@Data
public class FtuBzTzyb implements Serializable {
    /* 跳闸压板标准 */
    private String id;
    private String ssqy;//所属区域
    private String jyxm;//检验项目
    private String gzmn;//故障模拟
}
