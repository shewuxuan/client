package com.guodu.pojo.pwbh;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PwbhBzYqyb
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/6/11 4:00 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PwbhBzYqyb implements Serializable {
    /**
    * 主键
    */
    private String id;

    /**
    * 所属区域
    */
    private String ssqy;

    /**
    * 试验仪器名称
    */
    private String yqmc;

    private static final long serialVersionUID = 1L;
}