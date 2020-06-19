package com.guodu.pojo.ftu;

import lombok.Data;

import java.io.Serializable;

@Data
public class FtuBzTyjc implements Serializable {
    /* 投运前检查标准 */
    private String id;
    private String ssqy;//所属区域
    private String jcxm;//检查项目
}
