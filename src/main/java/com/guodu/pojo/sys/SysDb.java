package com.guodu.pojo.sys;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysDb implements Serializable {
    /* 基础字典表 */
    private String keycode;//字典代码
    private String keyvalue;//值
    private String keymemo;//说明
}
