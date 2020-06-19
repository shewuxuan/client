package com.guodu.controller;

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
public class BzPageController {

    @RequestMapping("bz/{page}/{pageName}")
    public String bz1(@PathVariable String page, @PathVariable String pageName) {
        return "bz/" + page + "/" + pageName;
    }

    @RequestMapping("bz/{page}/{pageName}/{id}")
    public String bz2(@PathVariable String page,
                      @PathVariable String pageName,
                      @PathVariable String id,
                      Model model) {
        model.addAttribute("id", id);
        return "bz/" + page + "/" + pageName;
    }
}
