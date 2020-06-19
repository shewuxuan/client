package com.guodu.pojo.pwbh;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PwbhBzLpjy
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/6/11 3:59 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PwbhBzLpjy implements Serializable {
    /**
    * 主键
    */
    private String id;

    /**
    * 所属区域
    */
    private String ssqy;

    /**
    * 测试结果
    */
    private String csjg;

    private static final long serialVersionUID = 1L;
}