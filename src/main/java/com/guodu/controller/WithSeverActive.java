package com.guodu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.guodu.pojo.dtu.*;
import com.guodu.pojo.ftu.*;
import com.guodu.pojo.pwbh.*;
import com.guodu.pojo.sys.Auth;
import com.guodu.pojo.sys.SysSccj;
import com.guodu.pojo.sys.SysSsxl;
import com.guodu.pojo.sys.UserInfo;
import com.guodu.service.impl.HttpClientServiceImpl;
import com.guodu.service.impl.ftu.*;
import com.guodu.service.impl.pwbh.PwbhBzBhctServiceImpl;
import com.guodu.service.impl.sys.SysSccjServiceImpl;
import com.guodu.service.impl.sys.SysSsxlServiceImpl;
import com.guodu.service.impl.dtu.*;
import com.guodu.service.impl.sys.UserInfoServiceImpl;
import com.guodu.service.pwbh.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WithSeverActive {
    /** dtu **/
    @Value("${bzctgyUrl}")
    String bzctgyUrl;
    @Value("${bzcyjdUrl}")
    String bzcyjdUrl;
    @Value("${bzdlhlUrl}")
    String bzdlhlUrl;
    @Value("${bzdyxnUrl}")
    String bzdyxnUrl;
    @Value("${bzgjcsUrl}")
    String bzgjcsUrl;
    @Value("${bzgncsUrl}")
    String bzgncsUrl;
    @Value("${bzhldzUrl}")
    String bzhldzUrl;
    @Value("${bzjydzUrl}")
    String bzjydzUrl;
    @Value("${bzkggUrl}")
    String bzkggUrl;
    @Value("${bzlpjyUrl}")
    String bzlpjyUrl;
    @Value("${bzwgpzUrl}")
    String bzwgpzUrl;
    @Value("${bzyctsUrl}")
    String bzyctsUrl;
    @Value("${bzyktsUrl}")
    String bzyktsUrl;

    @Autowired
    HttpClientServiceImpl httpClientService;
    @Autowired
    BzCtgyServiceImpl bzCtgyServiceImpl;
    @Autowired
    BzCyjdServiceImpl bzCyjdServiceImpl;
    @Autowired
    BzDlhlServiceImpl bzDlhlServiceImpl;
    @Autowired
    BzDyxnServiceImpl bzDyxnServiceImpl;
    @Autowired
    BzGjcsServiceImpl bzGjcsServiceImpl;
    @Autowired
    BzGncsServiceImpl bzGncsServiceImpl;
    @Autowired
    BzHldzServiceImpl bzHldzServiceImpl;
    @Autowired
    BzJydzServiceImpl bzJydzServiceImpl;
    @Autowired
    BzKggServiceImpl bzKggServiceImpl;
    @Autowired
    BzLpjyServiceImpl bzLpjyServiceImpl;
    @Autowired
    BzWgpzServiceImpl bzWgpzServiceImpl;
    @Autowired
    BzYctsServiceImpl bzYctsServiceImpl;
    @Autowired
    BzYktsServiceImpl bzYktsServiceImpl;

    /** users **/
    @Value("${usersUrl}")
    String usersUrl;
    /** 生产厂家 **/
    @Value("${sccjUrl}")
    String sccjUrl;
    /** 所属线路 **/
    @Value("${ssxlUrl}")
    String ssxlUrl;
    @Autowired
    UserInfoServiceImpl userInfoServiceImpl;
    @Autowired
    SysSccjServiceImpl sysSccjServiceImpl;
    @Autowired
    SysSsxlServiceImpl SysSsxlServiceImpl;

    @RequestMapping(value = {"toTablesView.action", "/"}, produces = {"application/json;charset=UTF-8"})
    public ModelAndView toTablesView() {
        ModelAndView view = new ModelAndView("/tables");
        return view;
    }

    //下载所属线路
    @RequestMapping(value = {"getSsxl.action"}, produces = {"application/json;charset=UTF-8"})
    public Object getSsxl() {
        Map res = new HashMap<>();
        res.put("code", "0");
        res.put("message", "下载成功！");
        try {
            if (isConnect(ssxlUrl).equals("false")) {
                res.put("code", "-1");
                res.put("message", "连接服务器错误！");
            } else {
                String json = httpClientService.doGet(ssxlUrl, null);
                SysSsxlServiceImpl.delete("-1");
                List<SysSsxl> sysSsxls = JSON.parseObject(json, new TypeReference<List<SysSsxl>>() {
                });
                for (SysSsxl sysSsxl : sysSsxls) SysSsxlServiceImpl.insert(sysSsxl);
            }
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "下载错误！");
            e.printStackTrace();
        }
        return res;
    }

    //下载生产厂家
    @RequestMapping(value = {"getSccj.action"}, produces = {"application/json;charset=UTF-8"})
    public Object getSccj() {
        Map res = new HashMap<>();
        res.put("code", "0");
        res.put("message", "下载成功！");
        try {
            if (isConnect(sccjUrl).equals("false")) {
                res.put("code", "-1");
                res.put("message", "连接服务器错误！");
            } else {
                String json = httpClientService.doGet(sccjUrl, null);
                sysSccjServiceImpl.delete("-1");
                List<SysSccj> sysSccjs = JSON.parseObject(json, new TypeReference<List<SysSccj>>() {
                });
                //List<SysSccj> sysSccjs = JSONArray.parseArray(json, SysSccj.class);
                for (SysSccj sysSccj : sysSccjs) sysSccjServiceImpl.add(sysSccj);
            }
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "下载错误！");
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = {"getUsers.action", "/"}, produces = {"application/json;charset=UTF-8"})
    public Object getUsers() {
        Map res = new HashMap<>();
        res.put("code", "0");
        res.put("message", "下载成功！");
        try {
            if (isConnect(usersUrl).equals("false")) {
                res.put("code", "-1");
                res.put("message", "连接服务器错误！");
            } else {
                String json = httpClientService.doGet(usersUrl, null);
                userInfoServiceImpl.delete("-1");
                List<UserInfo> users = JSON.parseObject(json, new TypeReference<List<UserInfo>>() {
                });
                for (UserInfo user : users) userInfoServiceImpl.add(user);
            }
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "下载错误！");
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = {"getStandard.action"}, produces = {"application/json;charset=UTF-8"})
    public Object getStandard(HttpServletRequest request) {
        Map res = new HashMap<>();
        res.put("code", "0");
        res.put("message", "下载成功！");
        try {
            Map map = getDtuStandard(Auth.getAuth(request).getSsqy());
            if (map.get("code").toString().equals("-1")) {
                return map;
            }
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "下载错误！");
            e.printStackTrace();
        }
        return res;
    }

    /***
     * 下载dtu标准
     * @param ssqy
     * @return
     */
    @RequestMapping(value = {"getDtuStandard.action"}, produces = {"application/json;charset=UTF-8"})
    public Map getDtuStandard(String ssqy) {
        Map map = new HashMap();
        map.put("ssqy", ssqy);
        if (isConnect(bzctgyUrl).equals("false")) {
            map.put("code", "-1");
            map.put("message", "连接服务器错误！");
        } else {

            bzCtgyServiceImpl.delete("-1");
            String bzCtgyStr = httpClientService.doPost(bzctgyUrl, map);
            List<BzCtgy> bzCtgys = JSON.parseObject(bzCtgyStr, new TypeReference<List<BzCtgy>>() {
            });
            for (BzCtgy bzCtgy : bzCtgys) bzCtgyServiceImpl.add(bzCtgy);

            bzCyjdServiceImpl.delete("-1");
            String bzCyjdStr = httpClientService.doPost(bzcyjdUrl, null);
            List<BzCyjd> bzCyjds = JSON.parseObject(bzCyjdStr, new TypeReference<List<BzCyjd>>() {
            });
            for (BzCyjd bzCyjd : bzCyjds) bzCyjdServiceImpl.add(bzCyjd);

            bzDlhlServiceImpl.delete("-1");
            String bzDlhlStr = httpClientService.doPost(bzdlhlUrl, null);
            List<BzDlhl> bzDlhls = JSON.parseObject(bzDlhlStr, new TypeReference<List<BzDlhl>>() {
            });
            for (BzDlhl bzDlhl : bzDlhls) bzDlhlServiceImpl.add(bzDlhl);

            bzDyxnServiceImpl.delete("-1");
            String bzDyxnStr = httpClientService.doPost(bzdyxnUrl, null);
            List<BzDyxn> bzDyxns = JSON.parseObject(bzDyxnStr, new TypeReference<List<BzDyxn>>() {
            });
            for (BzDyxn bzDyxn : bzDyxns) bzDyxnServiceImpl.add(bzDyxn);

            bzGjcsServiceImpl.delete("-1");
            String bzGjcsStr = httpClientService.doPost(bzgjcsUrl, null);
            List<BzGjcs> bzGjcss = JSON.parseObject(bzGjcsStr, new TypeReference<List<BzGjcs>>() {
            });
            for (BzGjcs bzGjcs : bzGjcss) bzGjcsServiceImpl.add(bzGjcs);

            bzGncsServiceImpl.delete("-1");
            String bzGncsStr = httpClientService.doPost(bzgncsUrl, null);
            List<BzGncs> bzGncss = JSON.parseObject(bzGncsStr, new TypeReference<List<BzGncs>>() {
            });
            for (BzGncs bzGncs : bzGncss) bzGncsServiceImpl.add(bzGncs);

            bzHldzServiceImpl.delete("-1");
            String bzHldzStr = httpClientService.doPost(bzhldzUrl, null);
            List<BzHldz> bzHldzs = JSON.parseObject(bzHldzStr, new TypeReference<List<BzHldz>>() {
            });
            for (BzHldz bzHldz : bzHldzs) bzHldzServiceImpl.add(bzHldz);

            bzJydzServiceImpl.delete("-1");
            String bzJydzStr = httpClientService.doPost(bzjydzUrl, null);
            List<BzJydz> bzJydzs = JSON.parseObject(bzJydzStr, new TypeReference<List<BzJydz>>() {
            });
            for (BzJydz bzJydz : bzJydzs) bzJydzServiceImpl.add(bzJydz);

            bzKggServiceImpl.delete("-1");
            String bzKggStr = httpClientService.doPost(bzkggUrl, null);
            List<BzKgg> bzKggs = JSON.parseObject(bzKggStr, new TypeReference<List<BzKgg>>() {
            });
            for (BzKgg bzKgg : bzKggs) bzKggServiceImpl.add(bzKgg);

            bzLpjyServiceImpl.delete("-1");
            String bzLpjyStr = httpClientService.doPost(bzlpjyUrl, null);
            List<BzLpjy> bzLpjys = JSON.parseObject(bzLpjyStr, new TypeReference<List<BzLpjy>>() {
            });
            for (BzLpjy bzLpjy : bzLpjys) bzLpjyServiceImpl.add(bzLpjy);

            bzWgpzServiceImpl.delete("-1");
            String bzWgpzStr = httpClientService.doPost(bzwgpzUrl, null);
            List<BzWgpz> bzWgpzs = JSON.parseObject(bzWgpzStr, new TypeReference<List<BzWgpz>>() {
            });
            for (BzWgpz bzWgpz : bzWgpzs) bzWgpzServiceImpl.add(bzWgpz);

            bzYctsServiceImpl.delete("-1");
            String bzYctsStr = httpClientService.doPost(bzyctsUrl, null);
            List<BzYcts> bzYctss = JSON.parseObject(bzYctsStr, new TypeReference<List<BzYcts>>() {
            });
            for (BzYcts bzYcts : bzYctss) bzYctsServiceImpl.add(bzYcts);

            bzYktsServiceImpl.delete("-1");
            String bzYktsStr = httpClientService.doPost(bzyktsUrl, null);
            List<BzYkts> bzYktss = JSON.parseObject(bzYktsStr, new TypeReference<List<BzYkts>>() {
            });
            for (BzYkts bzYkts : bzYktss) bzYktsServiceImpl.add(bzYkts);

            map.put("code", "0");
            map.put("message", "下载成功！");
        }
        return map;
    }

    /** ftu **/
    @Value("${ftuUrl}")
    String ftuUrl;

    @Autowired
    FtuBzCyjdcsServiceImpl ftuBzCyjdcsServiceImpl;
    @Autowired
    FtuBzCyjdServiceImpl ftuBzCyjdServiceImpl;
    @Autowired
    FtuBzGjtsServiceImpl ftuBzGjtsServiceImpl;
    @Autowired
    FtuBzGncsServiceImpl ftuBzGncsServiceImpl;
    @Autowired
    FtuBzHbdyServiceImpl ftuBzHbdyServiceImpl;
    @Autowired
    FtuBzLpjyServiceImpl ftuBzLpjyServiceImpl;
    @Autowired
    FtuBzTyjcServiceImpl ftuBzTyjcServiceImpl;
    @Autowired
    FtuBzTzybServiceImpl ftuBzTzybServiceImpl;

    /***
     * 下载ftu标准
     * @param
     * @return
     */
    @RequestMapping(value = {"getFtuStandard.action"}, produces = {"application/json;charset=UTF-8"})
    public Object getFtuStandard(){
        Map res = new HashMap<>();
        res.put("code", "0");
        res.put("message", "下载成功！");
        try {
            String json = httpClientService.doGet(ftuUrl, null);
            if(!json.equals("-1")){
                Map<String,String> param = JSON.parseObject(json, new TypeReference<Map<String,String>>() {});
                extFtu(param);
            }else{
                res.put("code", "-1");
                res.put("message", "连接服务器错误！");
            }
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "下载错误！");
            e.printStackTrace();
        }
        return res;
    }

    public void extFtu(Map<String,String> param) throws Exception{
        List<FtuBzCyjdcs> bzCyjdcsList = JSON.parseObject(param.get("bzCyjdcs"), new TypeReference< List<FtuBzCyjdcs>>() {});
        ftuBzCyjdcsServiceImpl.delete("-1");
        for(FtuBzCyjdcs bzCyjdcs:bzCyjdcsList){
            ftuBzCyjdcsServiceImpl.add(bzCyjdcs);
        }

        List<FtuBzCyjd> bzCyjdList = JSON.parseObject(param.get("bzCyjd"), new TypeReference< List<FtuBzCyjd>>() {});
        ftuBzCyjdServiceImpl.delete("-1");
        for(FtuBzCyjd bzCyjd:bzCyjdList){
            ftuBzCyjdServiceImpl.add(bzCyjd);
        }

        List<FtuBzGjts> bzGjtsList = JSON.parseObject(param.get("bzGjts"), new TypeReference< List<FtuBzGjts>>() {});
        ftuBzGjtsServiceImpl.delete("-1");
        for(FtuBzGjts bzGjts:bzGjtsList){
            ftuBzGjtsServiceImpl.add(bzGjts);
        }

        List<FtuBzGncs> bzGncsList = JSON.parseObject(param.get("bzGncs"), new TypeReference< List<FtuBzGncs>>() {});
        ftuBzGncsServiceImpl.delete("-1");
        for(FtuBzGncs bzGncs:bzGncsList){
            ftuBzGncsServiceImpl.add(bzGncs);
        }

        List<FtuBzHbdy> bzHbdyList = JSON.parseObject(param.get("bzHbdy"), new TypeReference< List<FtuBzHbdy>>() {});
        ftuBzHbdyServiceImpl.delete("-1");
        for(FtuBzHbdy bzHbdy:bzHbdyList){
            ftuBzHbdyServiceImpl.add(bzHbdy);
        }

        List<FtuBzLpjy> bzLpjyList = JSON.parseObject(param.get("bzLpjy"), new TypeReference< List<FtuBzLpjy>>() {});
        ftuBzLpjyServiceImpl.delete("-1");
        for(FtuBzLpjy bzLpjy:bzLpjyList){
            ftuBzLpjyServiceImpl.add(bzLpjy);
        }

        List<FtuBzTyjc> bzTyjcList = JSON.parseObject(param.get("bzTyjc"), new TypeReference< List<FtuBzTyjc>>() {});
        ftuBzTyjcServiceImpl.delete("-1");
        for(FtuBzTyjc bzTyjc:bzTyjcList){
            ftuBzTyjcServiceImpl.add(bzTyjc);
        }

        List<FtuBzTzyb> bzTzybList = JSON.parseObject(param.get("bzTzyb"), new TypeReference< List<FtuBzTzyb>>() {});
        ftuBzTzybServiceImpl.delete("-1");
        for(FtuBzTzyb bzTzyb:bzTzybList){
            ftuBzTzybServiceImpl.add(bzTzyb);
        }
    }

    public String isConnect(String url) {
        String json = httpClientService.doGet(url, null);
        String flag = "true";
        if (json.equals("-1")) {
            flag = "false";
        }
        return flag;
    }

    /**配网保护**/
    @Autowired
    PwbhBzBhctService pwbhBzBhctServiceImpl;
    @Autowired
    PwbhBzDzjcService pwbhBzDzjcServiceImpl;
    @Autowired
    PwbhBzJdjyService pwbhBzJdjyServiceImpl;
    @Autowired
    PwbhBzJxdxService pwbhBzJxdxServiceImpl;
    @Autowired
    PwbhBzJycsService pwbhBzJycsServiceImpl;
    @Autowired
    PwbhBzLpjyService pwbhBzLpjyServiceImpl;
    @Autowired
    PwbhBzSgjcService pwbhBzSgjcServiceImpl;
    @Autowired
    PwbhBzWgjcService pwbhBzWgjcServiceImpl;
    @Autowired
    PwbhBzYqybService pwbhBzYqybServiceImpl;
    @Autowired
    PwbhBzZzsyService pwbhBzZzsyServiceImpl;
    @Value("${pwbhUrl}")
    String pwbhUrl;

    @RequestMapping(value = {"getPwbhStandard.action" }, produces = {"application/json;charset=UTF-8"})
    public Object getPwbhStandard() {
        Map res = new HashMap<>();
        res.put("code", "0");
        res.put("message", "下载成功！");
        try {
            String json = httpClientService.doGet(pwbhUrl, null);
            if(!json.equals("-1")){
                Map<String,String> param = JSON.parseObject(json, new TypeReference<Map<String,String>>() {});
                extPwbh(param);
            }else{
                res.put("code", "-1");
                res.put("message", "连接服务器错误！");
            }
        } catch (Exception e) {
            res.put("code", "-1");
            res.put("message", "下载错误！");
            e.printStackTrace();
        }
        return res;
    }

    public void extPwbh(Map<String,String> param) throws Exception{
        List<PwbhBzBhct> bzBhctList = JSON.parseObject(param.get("bzBhct"), new TypeReference< List<PwbhBzBhct>>() {});
        pwbhBzBhctServiceImpl.deleteByPrimaryKey("-1");
        for(PwbhBzBhct bzBhct:bzBhctList){
            pwbhBzBhctServiceImpl.insert(bzBhct);
        }

        List<PwbhBzDzjc> bzDzjcList = JSON.parseObject(param.get("bzDzjc"), new TypeReference< List<PwbhBzDzjc>>() {});
        pwbhBzDzjcServiceImpl.deleteByPrimaryKey("-1");
        for(PwbhBzDzjc bzDzjc:bzDzjcList){
            pwbhBzDzjcServiceImpl.insert(bzDzjc);
        }

        List<PwbhBzJdjy> bzJdjyList = JSON.parseObject(param.get("bzJdjy"), new TypeReference< List<PwbhBzJdjy>>() {});
        pwbhBzJdjyServiceImpl.deleteByPrimaryKey("-1");
        for(PwbhBzJdjy bzJdjy:bzJdjyList){
            pwbhBzJdjyServiceImpl.insert(bzJdjy);
        }

        List<PwbhBzJxdx> bzJxdxList = JSON.parseObject(param.get("bzJxdx"), new TypeReference< List<PwbhBzJxdx>>() {});
        pwbhBzJxdxServiceImpl.deleteByPrimaryKey("-1");
        for(PwbhBzJxdx bzJxdx:bzJxdxList){
            pwbhBzJxdxServiceImpl.insert(bzJxdx);
        }

        List<PwbhBzJycs> bzJycsList = JSON.parseObject(param.get("bzJycs"), new TypeReference< List<PwbhBzJycs>>() {});
        pwbhBzJycsServiceImpl.deleteByPrimaryKey("-1");
        for(PwbhBzJycs bzJycs:bzJycsList){
            pwbhBzJycsServiceImpl.insert(bzJycs);
        }

        List<PwbhBzLpjy> bzLpjyList = JSON.parseObject(param.get("bzLpjy"), new TypeReference< List<PwbhBzLpjy>>() {});
        pwbhBzLpjyServiceImpl.deleteByPrimaryKey("-1");
        for(PwbhBzLpjy bzLpjy:bzLpjyList){
            pwbhBzLpjyServiceImpl.insert(bzLpjy);
        }

        List<PwbhBzSgjc> bzSgjcList = JSON.parseObject(param.get("bzSgjc"), new TypeReference< List<PwbhBzSgjc>>() {});
        pwbhBzSgjcServiceImpl.deleteByPrimaryKey("-1");
        for(PwbhBzSgjc bzSgjc:bzSgjcList){
            pwbhBzSgjcServiceImpl.insert(bzSgjc);
        }

        List<PwbhBzWgjc> bzWgjcList = JSON.parseObject(param.get("bzWgjc"), new TypeReference< List<PwbhBzWgjc>>() {});
        pwbhBzWgjcServiceImpl.deleteByPrimaryKey("-1");
        for(PwbhBzWgjc bzWgjc:bzWgjcList){
            pwbhBzWgjcServiceImpl.insert(bzWgjc);
        }

        List<PwbhBzYqyb> bzYqybList = JSON.parseObject(param.get("bzYqyb"), new TypeReference< List<PwbhBzYqyb>>() {});
        pwbhBzYqybServiceImpl.deleteByPrimaryKey("-1");
        for(PwbhBzYqyb bzYqyb:bzYqybList){
            pwbhBzYqybServiceImpl.insert(bzYqyb);
        }

        List<PwbhBzZzsy> bzZzsyList = JSON.parseObject(param.get("bzZzsy"), new TypeReference< List<PwbhBzZzsy>>() {});
        pwbhBzZzsyServiceImpl.deleteByPrimaryKey("-1");
        for(PwbhBzZzsy bzZzsy:bzZzsyList){
            pwbhBzZzsyServiceImpl.insert(bzZzsy);
        }
    }

}
