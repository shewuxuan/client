package com.guodu.controller.pwbh;

import com.guodu.pojo.sys.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: PageController
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/2/14 10:51 上午
 */
@Controller
@RequestMapping("pwbh/")
public class PwbhPageController {

    @RequestMapping("tsjl/{page}/{tsid}")
    public String tsjl(@PathVariable String page, @PathVariable String tsid, HttpServletRequest request) {
        request.setAttribute("tsid", tsid);
        String userType = Auth.getAuth(request).getUserType();
        request.setAttribute("userType", userType);
        return "pwbh/tsjl/" + page;
    }

    @RequestMapping("tsjl/add/{page}/{tsid}/{ssqy}")
    public String jl3(@PathVariable String page, @PathVariable String tsid, @PathVariable String ssqy, Model model) {
        model.addAttribute("tsid", tsid);
        model.addAttribute("ssqy", ssqy);
        return "pwbh/tsjl/add/" + page;
    }
}
