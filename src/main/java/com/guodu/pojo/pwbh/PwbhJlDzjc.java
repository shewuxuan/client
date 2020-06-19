package com.guodu.pojo.pwbh;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PwbhJlDzjc
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/6/11 4:03 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PwbhJlDzjc implements Serializable {
    /**
    * 主键
    */
    private String id;

    /**
    * 调试ID
    */
    private String tsid;

    /**
    * 所属区域
    */
    private String ssqy;

    /**
    * 间隔
    */
    private String jg;

    /**
    * 过流1段整定值
    */
    private String g1;

    /**
    * 过流1段0.95倍定值
    */
    private String g11;

    /**
    * 过流1段1.05倍定值
    */
    private String g12;

    /**
    * 过流2段整定值
    */
    private String g2;

    /**
    * 过流2段0.95倍定值
    */
    private String g21;

    /**
    * 过流2段1.05倍定值
    */
    private String g22;

    /**
    * 零序1段整定值
    */
    private String l1;

    /**
    * 零序1段0.95倍定值
    */
    private String l11;

    /**
    * 零序1段1.05倍定值
    */
    private String l12;

    /**
    * 零序2段整定值
    */
    private String l2;

    /**
    * 零序2段0.95倍定值
    */
    private String l21;

    /**
    * 零序2段1.05倍定值
    */
    private String l22;

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