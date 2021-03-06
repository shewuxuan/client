package com.guodu.controller.dtu;

import cn.hutool.json.JSONUtil;
import com.guodu.pojo.ftu.FtuJbxxBeizhu;
import com.guodu.pojo.dtu.JbxxBeizhu;
import com.guodu.service.ftu.FtuJbxxBeizhuService;
import com.guodu.service.dtu.JbxxBeizhuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: JbxxBeizhuController
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/4/29 12:33 下午
 */
@RequestMapping("beizhu/")
@RestController
public class BeizhuController {

    @Autowired
    private JbxxBeizhuService jbxxBeizhuServiceImpl;
    private final Map<String, Object> map = new HashMap<>(16);

    /**
     * @MethodName: selectBeizhuByPrimaryKey
     * @Description: TODO 查询DTU备注
     * @Param: [tsid]
     * @Return: java.lang.String
     * @Author: 2uli
     * @Date: 2020/5/13 1:17 下午
     */
    @GetMapping("selectBeizhuByPrimaryKey/{tsid}")
    public String selectBeizhuByPrimaryKey(@PathVariable String tsid) {
        JbxxBeizhu jbxxBeizhu = jbxxBeizhuServiceImpl.selectByPrimaryKey(tsid);
        return JSONUtil.toJsonStr(jbxxBeizhu);
    }

    /**
     * @MethodName: updateByPrimaryKey
     * @Description: TODO 修改DTU备注
     * @Param: [record]
     * @Return: java.lang.String
     * @Author: 2uli
     * @Date: 2020/5/13 1:17 下午
     */
    @PostMapping("updateByPrimaryKey")
    public String updateByPrimaryKey(@RequestBody JbxxBeizhu record) {
        map.clear();
        try {
            int i = jbxxBeizhuServiceImpl.updateByPrimaryKeySelective(record);
            map.put("code", 0);
            map.put("msg", "修改成功！");
            map.put("data", i);
        } catch (Exception e) {
            map.put("code", -1);
            map.put("msg", "修改失败！");
        }
        return JSONUtil.toJsonStr(map);
    }
}
