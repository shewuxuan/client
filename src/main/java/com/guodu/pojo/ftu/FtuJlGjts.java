package com.guodu.pojo.ftu;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: FtuJlGjts
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/14 4:44 下午
 */

/**
 * 告警调试记录表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FtuJlGjts implements Serializable {
    /**
     * 记录Id
     */
    private String id;

    /**
     * 调试Id
     */
    private String tsid;

    /**
     * 所属区域
     */
    private String ssqy;

    /**
     * 测试项
     */
    private String csx;

    /**
     * 不动作/动作
     */
    private String dz;

    /**
     * 过流Ⅰ段整定值
     */
    private BigDecimal g1dz;

    /**
     * 实际通电0.95倍定值／1.05倍定值不动作
     */
    private BigDecimal g1sjtd1;

    /**
     * 实际通电0.95倍定值／1.05倍定值动作
     */
    private BigDecimal g1sjtd2;

    /**
     * 过流Ⅱ段整定值
     */
    private BigDecimal g2dz;

    /**
     * 实际通电0.95倍定值／1.05倍定值不动作
     */
    private BigDecimal g2sjtd1;

    /**
     * 实际通电0.95倍定值／1.05倍定值动作
     */
    private BigDecimal g2sjtd2;

    /**
     * 零序Ⅰ段整定值
     */
    private BigDecimal lx1dz;

    /**
     * 实际通电0.95倍定值／1.05倍定值不动作
     */
    private BigDecimal lx1sjtd1;

    /**
     * 实际通电0.95倍定值／1.05倍定值动作
     */
    private BigDecimal lx1sjtd2;

    /**
     * 零序Ⅱ段整定值
     */
    private BigDecimal lx2dz;

    /**
     * 实际通电0.95倍定值／1.05倍定值不动作
     */
    private BigDecimal lx2sjtd1;

    /**
     * 实际通电0.95倍定值／1.05倍定值动作
     */
    private BigDecimal lx2sjtd2;

    /**
     * 操作人
     */
    private String czr;

    /**
     * 操作时间
     */
    private Date czsj;

    /**
     * 备注
     */
    private String beizhu;

    private static final long serialVersionUID = 1L;
}