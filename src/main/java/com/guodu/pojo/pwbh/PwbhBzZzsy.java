package com.guodu.pojo.pwbh;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PwbhBzZzsy
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/6/15 4:19 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PwbhBzZzsy implements Serializable {
    /**
    * ID
    */
    private String id;

    /**
    * 所属区域
    */
    private String ssqy;

    /**
    * 检验项目
    */
    private String jyxm;

    private static final long serialVersionUID = 1L;
}