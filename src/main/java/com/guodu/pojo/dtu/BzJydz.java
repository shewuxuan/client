package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BzJydz implements Serializable {
    /* 绝缘电阻测试标准 */
    private String id;
    private String ssqy;//所属区域
    private String bchl;//北侧回路
    private String sydy;//试验电压（伏）
}
