package com.guodu.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.guodu.pojo.dtu.Jbxx;
import com.guodu.service.impl.dtu.JbxxServiceImpl;
import com.guodu.service.impl.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UploadController
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/3/3 5:33 下午
 */
@RestController
public class UploadController {

    @Autowired
    private UploadServiceImpl uploadServiceImpl;
    @Autowired
    private JbxxServiceImpl jbxxServiceImpl;

    @Value("${uploadJlUrl}")
    private String uploadJlUrl;

    @Value("${uploadJbxxPhotosUrl}")
    private String uploadJbxxPhotosUrl;

    @Value("${changeJbxxFilesUrl}")
    private String changeJbxxFilesUrl;

    private Map<String, Object> map = new HashMap<>(16);

    @RequestMapping("uploadJl")
    public String uploadJl(@RequestBody List<String> ids) {
        map.clear();
        List<Jbxx> succeed = new ArrayList<>();
        List<Jbxx> failed = new ArrayList<>();
        Jbxx jbxx;
        try {
            for (String id : ids) {
                // 查本地客户端jbxx中所有包含的信息, 全部放入jbxx
                jbxx = uploadServiceImpl.uploadJl(id);
                if (jbxx != null) {
                    String jbxxStr = JSONUtil.toJsonStr(jbxx);
                    // 开始上传jbxx
                    String post = HttpUtil.post(uploadJlUrl, jbxxStr);
                    uploadServiceImpl.uploadJbxxPhotoAndFile(jbxx.getTsid(),uploadJbxxPhotosUrl,changeJbxxFilesUrl);
                    JSONObject jsonObject = JSONUtil.parseObj(post);
                    // 上传成功/失败记录, 放入succeed/failed
                    if ((int) jsonObject.get("code") == 0) {
                        // 上传成功, 修改jbxx upload为1
                        jbxx.setUpload("1");
                        jbxxServiceImpl.updateByPrimaryKey(jbxx);
                        succeed.add(jbxx);
                    } else {
                        failed.add(jbxx);
                    }
                }
            }
            map.put("code", 0);
            map.put("msg", "上传记录完成！");
            StaticLog.info("上传记录完成 -----> " + ids, "INFO");
        } catch (Exception e) {
            map.put("code", -1);
            map.put("msg", "上传记录异常！");
            StaticLog.error(e, "上传记录异常 -----> ", "ERROR");
        }
        map.put("succeed", succeed);
        map.put("failed", failed);
        return JSONUtil.toJsonStr(map);
    }
}
