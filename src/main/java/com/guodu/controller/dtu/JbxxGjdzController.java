package com.guodu.controller.dtu;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.guodu.pojo.dtu.JbxxGjdz;
import com.guodu.service.dtu.JbxxGjdzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: JbxxGjdzController
 * @Description: TODO
 * @Author: 2uli
 * @Date: 2020/5/6 1:52 下午
 */
@RestController
@RequestMapping("gjdz/")
public class JbxxGjdzController {

    @Autowired
    private JbxxGjdzService jbxxGjdzServiceImpl;

    private Map<String, Object> map = new HashMap<>(16);

    @RequestMapping("insert")
    public String insert(@RequestBody JbxxGjdz record) {
        map.clear();
        try {
            int i = jbxxGjdzServiceImpl.insert(record);
            map.put("code", 0);
            map.put("msg", "添加成功！");
            map.put("data", i);
        } catch (Exception e) {
            map.put("code", -1);
            map.put("msg", "添加失败！");
            map.put("data", 0);
            StaticLog.error(e, "jbxx_gjdz【插入】失败 -----> " + record.getTsid(), "ERROR");
        }
        return JSONUtil.toJsonStr(map);
    }

    @RequestMapping("updateByPrimaryKey")
    public String updateByPrimaryKey(@RequestBody JbxxGjdz record) {
        map.clear();
        try {
            int i = jbxxGjdzServiceImpl.updateByPrimaryKey(record);
            map.put("code", 0);
            map.put("msg", "修改成功！");
            map.put("data", i);
        } catch (Exception e) {
            map.put("code", -1);
            map.put("msg", "修改失败！");
            map.put("data", 0);
            StaticLog.error(e, "jbxx_gjdz【修改】失败 -----> " + record.getTsid(), "ERROR");
        }
        return JSONUtil.toJsonStr(map);
    }

    @RequestMapping("updateByPrimaryKeyAndKgg")
    public String updateByPrimaryKeyAndKgg(@RequestBody JbxxGjdz record) {
        map.clear();
        try {
            int i = jbxxGjdzServiceImpl.updateByPrimaryKeyAndKgg(record);
            map.put("code", 0);
            map.put("msg", "修改成功！");
            map.put("data", i);
        } catch (Exception e) {
            map.put("code", -1);
            map.put("msg", "修改失败！");
            map.put("data", 0);
            StaticLog.error(e, "jbxx_gjdz【修改】失败 -----> " + record.getTsid(), "ERROR");
        }
        return JSONUtil.toJsonStr(map);
    }

    @RequestMapping("deleteByPrimaryKey/{tsid}")
    public String deleteByPrimaryKey(@PathVariable String tsid) {
        map.clear();
        try {
            jbxxGjdzServiceImpl.deleteByPrimaryKey(tsid);
            map.put("code", "0");
            map.put("msg", "删除成功");
            StaticLog.info("jbxx_gjdz表【删除】成功 -----> " + tsid, "INFO");
        } catch (Exception e) {
            StaticLog.error(e, "jbxx_gjdz表【删除】失败 -----> " + tsid, "ERROR");
            map.put("code", "-1");
            map.put("msg", "删除失败！");
        }
        return JSONUtil.toJsonStr(map);
    }

    @GetMapping("selectByPrimaryKey/{tsid}")
    public String selectByPrimaryKey(@PathVariable String tsid) {
        JbxxGjdz jbxxGjdz = jbxxGjdzServiceImpl.selectByPrimaryKey(tsid);
        return JSONUtil.toJsonStr(jbxxGjdz);
    }
}
