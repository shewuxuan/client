package com.guodu.controller.dtu;

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
public class DtuPageController {

    /**
     * @MethodName: jl2
     * @Description: TODO 跳转jl/下的页面  ---> jl/ts.jsp
     * @Param: [page]
     * @Return: java.lang.String
     * @Author: 2uli
     * @Date: 2020/3/12 9:39 上午
     */
    @RequestMapping("jl/{page}")
    public String jl2(@PathVariable String page) {
        return "jl/" + page;
    }

    /**
     * @MethodName: jl1
     * @Description: TODO 调试页面控制器
     * @Param: [page, tsid, request]
     * @Return: java.lang.String
     * @Author: 2uli
     * @Date: 2020/3/5 2:04 下午
     */
    @RequestMapping("jl/{page}/{tsid}")
    public String jl1(@PathVariable String page, @PathVariable String tsid, HttpServletRequest request) {
        request.setAttribute("tsid", tsid);
        String userType = Auth.getAuth(request).getUserType();
        request.setAttribute("userType", userType);
        return "jl/" + page;
    }

    /**
     * @MethodName: jl3
     * @Description: TODO 添加单条记录控制器
     * @Param: [page, tsid, ssqy, model]
     * @Return: java.lang.String
     * @Author: 2uli
     * @Date: 2020/3/5 2:03 下午
     */
    @RequestMapping("jl/add/{page}/{tsid}/{ssqy}")
    public String jl3(@PathVariable String page, @PathVariable String tsid, @PathVariable String ssqy, Model model) {
        model.addAttribute("tsid", tsid);
        model.addAttribute("ssqy", ssqy);
        return "jl/add/" + page;
    }
}
