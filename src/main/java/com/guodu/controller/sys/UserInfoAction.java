package com.guodu.controller.sys;

import com.alibaba.fastjson.JSON;
import com.guodu.pojo.sys.Auth;
import com.guodu.pojo.sys.SysDb;
import com.guodu.pojo.sys.UserInfo;
import com.guodu.service.impl.sys.SysDbServiceImpl;
import com.guodu.service.impl.sys.UserInfoServiceImpl;
import com.guodu.util.F;
import com.guodu.util.MD5Utils;
import com.guodu.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserInfoAction {
    private static Logger log = Logger.getLogger(UserInfoAction.class);
    @Autowired
    UserInfoServiceImpl userInfoServiceImpl;
    @Autowired
    SysDbServiceImpl sysDbServiceImpl;

    @RequestMapping(value = {"test.action", "/"}, produces = {"application/json;charset=UTF-8"})
    public List test(HttpServletRequest request) {
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        return list;
    }

    @RequestMapping(value = {"sysByUser.action", "/"}, produces = {"application/json;charset=UTF-8"})
    public ModelAndView sysByUser(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("sys/userManage");
        view.addObject("funcMap", Auth.getAuth(request).getRoleFunc());
        return view;
    }

    @RequestMapping(value = {"selectListUserInfo.action", "/"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Object selectListUserInfo(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam Map<String, Object> form) {
        Map<String, Object> res = null;
        try {
            res = this.userInfoServiceImpl.selectPage(form);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return JSON.toJSONString(res);

    }

    @RequestMapping(value = {"toAddUserInfoView.action", "/"}, produces = {"application/json;charset=UTF-8"})
    public ModelAndView toAddUserInfoView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("sys/addUser");
        String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId").toString();
        if (!userId.equals("")) {
            Map<String, Object> vo = new HashMap<String, Object>();
            vo.put("userId", userId);
            UserInfo userInfo = this.userInfoServiceImpl.selectList(vo).get(0);
            view.addObject("userInfo", userInfo);
        }
        return view;
    }

    @RequestMapping(value = {"addUserInfo.action", "/"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Object addUserInfo(HttpServletRequest request, @RequestParam Map<String, Object> form) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", "0");
        res.put("message", "添加成功");
        String loginname = (String) form.get("loginname");
        Map<String, Object> vo = new HashMap<String, Object>();
        vo.put("loginname", loginname);
        List<UserInfo> selectList = userInfoServiceImpl.selectList(vo);
        if (selectList != null && selectList.size() > 0) {
            res.put("code", "-1");
            res.put("message", "账户名已被注册！");
            return JSON.toJSONString(res);
        }
        String name = (String) form.get("name");
        String roleId = (String) form.get("roleId");
        String phone = (String) form.get("phone");
        String email = (String) form.get("email");
        String position = (String) form.get("position");
        UserInfo userInfo = new UserInfo();
        try {
            Date createDate = new Date();
            userInfo.setUserName(name);
            userInfo.setLoginName(loginname);
            userInfo.setUserType(F.UserType.admin);
            userInfo.setRoleId(roleId);
            userInfo.setPhone(phone);
            userInfo.setEmail(email);
            userInfo.setZw(position);
            userInfo.setZt(F.UserStatus.enable);
            String userId = StringUtils.createDateRandomString(createDate);
            userInfo.setUserId(userId);
            userInfo.setCzsj(createDate);
            String password = MD5Utils.MD5(F.UserPwd.defaultPwd, "utf-8");
            userInfo.setPassword(password);
            userInfo.setCzr(Auth.getAuth(request).getUserId());
            res.put("lockData", "用户ID：" + userId + ",用户姓名：" + name);
            this.userInfoServiceImpl.add(userInfo);
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "操作失败");
            log.error(e.getMessage());
        }
        return JSON.toJSONString(res);
    }

    @RequestMapping(value = {"updeatUserInfo.action", "/"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Object updeatUserInfo(@RequestParam Map<String, Object> form, HttpServletRequest request) {
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
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            if (!password.equals(F.UserPwd.defaultPwd)) {
                userInfo.setPassword(MD5Utils.MD5(password, "utf-8"));
            }
            userInfo.setUserName(name);
            userInfo.setUserType(F.UserType.admin);
            userInfo.setRoleId(roleId);
            userInfo.setPhone(phone);
            userInfo.setEmail(email);
            userInfo.setZw(position);
            userInfo.setCzsj(new Date());
            res.put("lockData", "用户ID：" + userId + ",用户姓名：" + name);
            this.userInfoServiceImpl.edit(userInfo);
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "修改失败");
            log.error(e.getMessage());
        }
        return JSON.toJSONString(res);
    }

    @RequestMapping(value = {"delUserInfo.action", "/"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Object delUserInfo(@RequestParam Map<String, Object> form) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", "0");
        res.put("message", "删除成功");
        String userId = (String) form.get("userId");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setZt(F.UserStatus.cancel);
        userInfo.setCzsj(new Date());
        UserInfo userInfo2 = this.userInfoServiceImpl.selectById(userId);
        res.put("lockData", "用户ID：" + userId + ",用户姓名：" + userInfo2.getUserName());
        log.info("删除用户：userId=" + userId);
        try {
            this.userInfoServiceImpl.edit(userInfo);
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "删除失败");
            log.error(e.getMessage());
        }
        return JSON.toJSONString(res);
    }

    @RequestMapping(value = {"changePassword.action", "/"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Object changePassword(HttpServletRequest request, @RequestParam Map<String, Object> form) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", "0");
        res.put("message", "修改成功");
        String oldPassword = (String) form.get("oldPassword");
        String newPassword = (String) form.get("newPassword");
        String comPassword = (String) form.get("comPassword");
        if (!newPassword.equals(comPassword)) {
            res.put("code", "-1");
            res.put("message", "确认密码不一致");
            return JSON.toJSONString(res);
        }
        String encPassword = MD5Utils.MD5(oldPassword, "UTF-8");
        String userId = Auth.getAuth(request).getUserId();
        UserInfo userInfo2 = this.userInfoServiceImpl.selectById(userId);
        res.put("lockData", "用户ID：" + userId + ",用户姓名：" + userInfo2.getUserName());
        boolean check = userInfoServiceImpl.checkUserPassword(userId, encPassword);
        if (!check) {
            res.put("code", "-1");
            res.put("message", "原密码错误");
            return JSON.toJSONString(res);
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setPassword(MD5Utils.MD5(newPassword, "UTF-8"));
        userInfo.setCzsj(new Date());
        try {
            this.userInfoServiceImpl.edit(userInfo);
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "修改密码失败");
            log.error(e.getMessage());
        }

        return JSON.toJSONString(res);
    }

    @RequestMapping(value = {"selectSsqyAll.action", "/"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Object selectSsqyAll(@RequestParam Map<String, Object> form) {
        form.put("keycode", F.SysKey.region);
        List<SysDb> ssqyList = this.sysDbServiceImpl.selectList(form);
        return JSON.toJSONString(ssqyList);
    }
}