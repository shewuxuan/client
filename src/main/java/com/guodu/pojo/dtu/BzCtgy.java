package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BzCtgy implements Serializable {
    /* CT安装工艺检查标准 */
    private String id;
    private String ssqy;//所属区域
    private String azyq;//安装要求

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSsqy() {
        return ssqy;
    }

    public void setSsqy(String ssqy) {
        this.ssqy = ssqy;
    }

    public String getAzyq() {
        return azyq;
    }

    public void setAzyq(String azyq) {
        this.azyq = azyq;
    }

    public String toString() {
        return "id=" + id + ",ssqy=" + ssqy + ",azyq=" + azyq;
    }
}