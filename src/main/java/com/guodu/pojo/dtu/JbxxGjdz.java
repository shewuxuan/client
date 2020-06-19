package com.guodu.pojo.dtu;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: JbxxGjdz
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/21 2:00 下午
 */

/**
 * 告警定值表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JbxxGjdz implements Serializable {
    /**
     * 唯一标识ID
     */
    private String tsid;

    /**
     * 过流告警值1
     */
    private String glgj1;

    /**
     * 过流告警值2
     */
    private String glgj2;

    /**
     * 过流告警CT变比
     */
    private String glgj3;

    /**
     * 过流测试值1
     */
    private String glcs1;

    /**
     * 过流测试值2
     */
    private String glcs2;

    /**
     * 零序告警值1
     */
    private String lxgj1;

    /**
     * 零序告警值2
     */
    private String lxgj2;

    /**
     * 零序告警CT变比
     */
    private String lxgj3;

    /**
     * 零序测试值1
     */
    private String lxcs1;

    /**
     * 零序测试值2
     */
    private String lxcs2;

    /**
     * 开关柜_1#箱体气压低告警
     */
    private String qygj1;

    /**
     * 开关柜_2#箱体气压低告警
     */
    private String qygj2;

    private static final long serialVersionUID = 1L;
}