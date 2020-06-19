package com.guodu.pojo.ftu;

import lombok.Data;

import java.io.Serializable;

@Data
public class FtuBzCyjdcs implements Serializable {
    /*采样精度测试标准 */
    private String id;
    private String ssqy;//所属区域
    private String clx;//测量项（额定值）
}
