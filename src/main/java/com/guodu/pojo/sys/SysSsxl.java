package com.guodu.pojo.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: SysSsxl
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/25 10:53 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysSsxl implements Serializable {
    private String id;

    /**
     * 所属区域
     */
    private String ssqy;

    /**
     * 变电站
     */
    private String bdz;

    /**
     * 线路名称
     */
    private String xlmc;

    private static final long serialVersionUID = 1L;
}