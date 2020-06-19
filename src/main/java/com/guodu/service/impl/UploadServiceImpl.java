package com.guodu.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.guodu.pojo.dtu.*;
import com.guodu.service.dtu.JbxxGjdzService;
import com.guodu.service.impl.dtu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UploadServiceImpl
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/3/3 5:39 下午
 */
@Service
public class UploadServiceImpl {

    @Autowired
    private JlCtgyServiceImpl jlCtgyServiceImpl;
    @Autowired
    private JlCyjdServiceImpl jlCyjdServiceImpl;
    @Autowired
    private JlDlhlServiceImpl jlDlhlServiceImpl;
    @Autowired
    private JlDtuServiceImpl jlDtuServiceImpl;
    @Autowired
    private JlDyxnServiceImpl jlDyxnServiceImpl;
    @Autowired
    private JlGjcsServiceImpl jlGjcsServiceImpl;
    @Autowired
    private JlGncsServiceImpl jlGncsServiceImpl;
    @Autowired
    private JlHldzServiceImpl jlHldzServiceImpl;
    @Autowired
    private JlJydzServiceImpl jlJydzServiceImpl;
    @Autowired
    private JlKggServiceImpl jlKggServiceImpl;
    @Autowired
    private JlLpjyServiceImpl jlLpjyServiceImpl;
    @Autowired
    private JlWgpzServiceImpl jlWgpzServiceImpl;
    @Autowired
    private JlXbServiceImpl jlXbServiceImpl;
    @Autowired
    private JlYctsServiceImpl jlYctsServiceImpl;
    @Autowired
    private JlYktsServiceImpl jlYktsServiceImpl;
    @Autowired
    private JlYsbjServiceImpl jlYsbjServiceImpl;
    @Autowired
    private JbxxServiceImpl jbxxServiceImpl;
    @Autowired
    private JbxxGjdzService jbxxGjdzServiceImpl;
    @Autowired
    private JbxxPhotoServiceImpl jbxxPhotoServiceImpl;
    @Autowired
    private JbxxBeizhuServiceImpl jbxxBeizhuServiceImpl;

    public Jbxx uploadJl(String id) {
        Jbxx jbxx = jbxxServiceImpl.selectByPrimaryKey(id);

        if (jbxx != null) {
            JlCtgy jlCtgy = new JlCtgy();
            jlCtgy.setTsid(id);
            jbxx.setJlCtgys(jlCtgyServiceImpl.selectByAll(jlCtgy));

            JlCyjd jlCyjd = new JlCyjd();
            jlCyjd.setTsid(id);
            jbxx.setJlCyjds(jlCyjdServiceImpl.selectByAll(jlCyjd));

            JlDlhl jlDlhl = new JlDlhl();
            jlDlhl.setTsid(id);
            jbxx.setJlDlhls(jlDlhlServiceImpl.selectByAll(jlDlhl));

            JlDtu jlDtu = new JlDtu();
            jlDtu.setTsid(id);
            List<JlDtu> jlDtus = jlDtuServiceImpl.selectByAll(jlDtu);
            if (jlDtus.size() != 0) {
                jbxx.setJlDtu(jlDtus.get(0));
            }

            JlDyxn jlDyxn = new JlDyxn();
            jlDyxn.setTsid(id);
            jbxx.setJlDyxns(jlDyxnServiceImpl.selectByAll(jlDyxn));

            JlGjcs jlGjcs = new JlGjcs();
            jlGjcs.setTsid(id);
            jbxx.setJlGjcss(jlGjcsServiceImpl.selectByAll(jlGjcs));

            JlGncs jlGncs = new JlGncs();
            jlGncs.setTsid(id);
            jbxx.setJlGncss(jlGncsServiceImpl.selectByAll(jlGncs));

            JlHldz jlHldz = new JlHldz();
            jlHldz.setTsid(id);
            jbxx.setJlHldzs(jlHldzServiceImpl.selectByAll(jlHldz));

            JlJydz jlJydz = new JlJydz();
            jlJydz.setTsid(id);
            jbxx.setJlJydzs(jlJydzServiceImpl.selectByAll(jlJydz));

            JlKgg jlKgg = new JlKgg();
            jlKgg.setTsid(id);
            jbxx.setJlKggs(jlKggServiceImpl.selectByAll(jlKgg));

            JlLpjy jlLpjy = new JlLpjy();
            jlLpjy.setTsid(id);
            jbxx.setJlLpjys(jlLpjyServiceImpl.selectByAll(jlLpjy));

            JlWgpz jlWgpz = new JlWgpz();
            jlWgpz.setTsid(id);
            jbxx.setJlWgpzs(jlWgpzServiceImpl.selectByAll(jlWgpz));

            JlXb jlXb = new JlXb();
            jlXb.setTsid(id);
            List<JlXb> jlXbs = jlXbServiceImpl.selectByAll(jlXb);
            if (jlXbs.size() != 0) {
                jbxx.setJlXb(jlXbs.get(0));
            }

            JlYcts jlYcts = new JlYcts();
            jlYcts.setTsid(id);
            jbxx.setJlYctss(jlYctsServiceImpl.selectByAll(jlYcts));

            JlYkts jlYkts = new JlYkts();
            jlYkts.setTsid(id);
            jbxx.setJlYktss(jlYktsServiceImpl.selectByAll(jlYkts));

            JlYsbj jlYsbj = new JlYsbj();
            jlYsbj.setTsid(id);
            List<JlYsbj> jlYsbjs = jlYsbjServiceImpl.selectByAll(jlYsbj);
            if (jlYsbjs.size() != 0) {
                jbxx.setJlYsbj(jlYsbjs.get(0));
            }

            //添加上传备注，告警定值
            JbxxGjdz jbxxGjdz = jbxxGjdzServiceImpl.selectByPrimaryKey(id);
            JbxxBeizhu jbxxBeizhu = jbxxBeizhuServiceImpl.selectByPrimaryKey(id);
            jbxx.setJbxxGjdz(jbxxGjdz);
            jbxx.setJbxxBeizhu(jbxxBeizhu);
        }
        return jbxx;
    }

    public void uploadJbxxPhotoAndFile(String tsid,String uploadJbxxPhotosUrl,String changeJbxxFilesUrl){
        Map<String, Object> vo = new HashMap<>();
        vo.put("tsid",tsid);
        List<Map<String,Object>> jbxxPhotos = jbxxPhotoServiceImpl.selectAllByTsid(vo);
        String jbxxPhotosStr = JSONUtil.toJsonStr(jbxxPhotos);
        HttpUtil.post(uploadJbxxPhotosUrl, jbxxPhotosStr);

        ArrayList<File> imgList = new ArrayList<File>();
        for(Map<String,Object> photo:jbxxPhotos) {
           String path = photo.get("P_PATH").toString();
            File file = new File(path);
            if(!file.exists()) continue;
            imgList.add(file);
        }
       HttpUtil.post(changeJbxxFilesUrl, JSONUtil.toJsonStr(imgList));
    }
}
