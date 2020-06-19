package com.guodu.pojo.dtu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JlDtu implements Serializable {
    /* DTU表 */
    private String id;
    private String tsid;//调试ID
    private String ssqy;//所属区域
    private String yf;//远方
    private String jd;//就地
    private String dcqy;//电池欠压
    private String dchh;//电池活化
    private String jlsd;//交流失电
    private String czr;//操作人
    private Date czsj;//操作时间
    private String beizhu;//备注
}
