package com.guodu.pojo.ftu;

import lombok.Data;

import java.io.Serializable;

@Data
public class FtuBzGjts implements Serializable {
    /* 告警调试标准 */
    private String id;
    private String ssqy;//所属区域
    private String csx;//测试项
}
