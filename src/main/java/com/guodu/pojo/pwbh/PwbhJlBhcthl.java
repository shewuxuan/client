package com.guodu.pojo.pwbh;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PwbhJlBhcthl
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/6/11 4:02 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PwbhJlBhcthl implements Serializable {
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
    * 相别
    */
    private String xb;

    /**
    * 一次以母线为正
    */
    private String bhc;

    /**
    * 二次以母线为正
    */
    private String ctc;

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