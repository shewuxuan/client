package com.guodu.controller.dtu;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.guodu.pojo.sys.Auth;
import com.guodu.pojo.dtu.JlYkts;
import com.guodu.service.impl.dtu.JlYktsServiceImpl;
import com.guodu.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: JlYktsController
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/2/20 1:40 下午
 */
@RestController
@RequestMapping("jlykts/")
public class JlYktsController {

    @Autowired
    private JlYktsServiceImpl jlYktsServiceImpl;

    private final Map<String, Object> map = new HashMap<>(16);

    @RequestMapping("insert")
    public String insert(@RequestBody JlYkts record, HttpServletRequest request) {
        map.clear();
        try {
            record.setId(IDUtil.getStrId());
            record.setCzr(Auth.getAuth(request).getLoginname());
            record.setCzsj(new Date());
            int i = jlYktsServiceImpl.insert2(record);
            map.put("code", 0);
            map.put("msg", "添加成功！");
            map.put("data", i);
            StaticLog.info("jl_ykts表【插入】成功 -----> " + record.getId(), "INFO");
        } catch (Exception e) {
            map.put("code", -1);
            map.put("msg", "添加失败！");
            map.put("data", 0);
            StaticLog.error(e, "jl_ykts表【插入】失败 -----> " + record.getId(), "ERROR");
        }
        return JSONUtil.toJsonStr(map);
    }

    @RequestMapping("selectByAll")
    public String selectByAll(JlYkts jlYkts) {
        map.clear();
        try {
            List<JlYkts> jlYktss = jlYktsServiceImpl.selectByAll(jlYkts);
            map.put("code", 0);
            map.put("msg", "查询完成");
            map.put("data", jlYktss);
            map.put("rows", jlYktss);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "查询异常");
        }
        return JSONUtil.toJsonStr(map);
    }

    @RequestMapping("updateBatch")
    public String updateBatch(@RequestBody List<JlYkts> list, HttpServletRequest request) {
        map.clear();
        try {
            String czr = Auth.getAuth(request).getLoginname();
            for (JlYkts jlYkts : list) {
                jlYkts.setCzr(czr);
                jlYkts.setCzsj(new Date());
            }
            jlYktsServiceImpl.updateBatch(list);
            map.put("code", 0);
            map.put("msg", "提交成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "提交失败！");
        }
        return JSONUtil.toJsonStr(map);
    }

    @RequestMapping("deleteByPrimaryKey/{id}")
    public String deleteByPrimaryKey(@PathVariable String id) {
        map.clear();
        try {
            jlYktsServiceImpl.deleteByPrimaryKey(id);
            map.put("code", 0);
            map.put("msg", "删除成功");
            StaticLog.info("jl_ykts表【删除】成功 -----> " + id, "INFO");
        } catch (Exception e) {
            StaticLog.error(e, "jl_ykts表【删除】失败 -----> " + id, "ERROR");
            map.put("code", -1);
            map.put("msg", "删除失败！");
        }
        return JSONUtil.toJsonStr(map);
    }

    @RequestMapping("batchDeleteByPrimaryKey")
    public String batchDeleteByPrimaryKey(@RequestBody List<String> ids) {
        map.clear();
        try {
            for (String id : ids) {
                jlYktsServiceImpl.deleteByPrimaryKey(id);
            }
            map.put("code", 0);
            map.put("msg", "删除成功");
            map.put("data", ids);
            StaticLog.info("jl_ykts表【删除】成功 -----> " + ids, "INFO");
        } catch (Exception e) {
            map.put("code", -1);
            map.put("msg", "删除失败！");
            StaticLog.error(e, "jl_ykts表【删除】失败 -----> " + ids, "ERROR");
        }
        return JSONUtil.toJsonStr(map);
    }
}
