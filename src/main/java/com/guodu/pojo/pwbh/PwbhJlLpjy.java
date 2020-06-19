package com.guodu.pojo.pwbh;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PwbhJlLpjy
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/6/11 4:26 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PwbhJlLpjy implements Serializable {
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
     * 测试结果
     */
    private String csjg;

    /**
     * CPU
     */
    private String cpu;

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