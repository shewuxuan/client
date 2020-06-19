package com.guodu.pojo.dtu;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BzWgpz implements Serializable {
    private String id;
    private String ssqy;
    private String csxm;
    private String csyq;

    public String toString() {
        return "{'id':'" + id + "','userId':'" + ssqy + "','csxm':'" + csxm + "','csyq':'" + csyq + "'}";
    }
}
