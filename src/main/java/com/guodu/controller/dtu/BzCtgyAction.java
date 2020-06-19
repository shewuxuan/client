package com.guodu.controller.dtu;

import com.alibaba.fastjson.JSON;
import com.guodu.pojo.sys.Auth;
import com.guodu.pojo.dtu.BzCtgy;
import com.guodu.service.impl.dtu.BzCtgyServiceImpl;
import com.guodu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("bzCtgy")
public class BzCtgyAction {
    @Autowired
    BzCtgyServiceImpl bzCtgyServiceImpl;

    @RequestMapping(value = {"toBzCtgyView.action", "/"}, produces = {"application/json;charset=UTF-8"})
    public ModelAndView toBzCtgyView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("jbxx/jbxxList");
        view.addObject("funcMap", Auth.getAuth(request).getRoleFunc());
        return view;
    }

    @RequestMapping(value = {"selectListBzCtgy.action", "/"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Object selectListBzCtgy(@RequestParam Map<String, Object> form) {
        Map<String, Object> res = new HashMap<String, Object>();
        String rows = (String) form.get("rows");
        String page = (String) form.get("page");
        Map<String, Object> vo = new HashMap<String, Object>();
        try {
            res = this.bzCtgyServiceImpl.selectPage(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(res);

    }

    @RequestMapping(value = {"toAddJbxxView.action", "/"}, produces = {"application/json;charset=UTF-8"})
    public ModelAndView toAddJbxxView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("jbxx/addJbxx");
        String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId").toString();
        if (!userId.equals("")) {
            Map<String, Object> vo = new HashMap<String, Object>();
            vo.put("userId", userId);
            BzCtgy bzCtgy = this.bzCtgyServiceImpl.selectList(vo).get(0);
            view.addObject("bzCtgy", bzCtgy);
        }
        return view;
    }

    @RequestMapping(value = {"addJbxx.action", "/"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Object addJbxx(HttpServletRequest request, @RequestParam Map<String, Object> form) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", "0");
        res.put("message", "添加成功");
        BzCtgy bzCtgy = new BzCtgy();
        try {
            Date createDate = new Date();
            String userId = StringUtils.createDateRandomString(createDate);
            this.bzCtgyServiceImpl.add(bzCtgy);
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "操作失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(res);
    }

    @RequestMapping(value = {"updeatJbxx.action", "/"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Object updeatJbxx(@RequestParam Map<String, Object> form, HttpServletRequest request) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", "0");
        res.put("message", "修改成功");
        String userId = (String) form.get("userId");
        String name = (String) form.get("name");
        String password = (String) form.get("password");
        String roleId = (String) form.get("roleId");
        String phone = (String) form.get("phone");
        String email = (String) form.get("email");
        String position = (String) form.get("position");
        try {
            BzCtgy bzCtgy = new BzCtgy();
            res.put("lockData", "用户ID：" + userId + ",用户姓名：" + name);
            this.bzCtgyServiceImpl.edit(bzCtgy);
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "修改失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(res);
    }

    @RequestMapping(value = {"delUserInfo.action", "/"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Object delUserInfo(@RequestParam BzCtgy bzCtgy) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", "0");
        res.put("message", "删除成功");
        try {
            this.bzCtgyServiceImpl.edit(bzCtgy);
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "删除失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(res);
    }

}