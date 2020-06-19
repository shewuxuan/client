package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JbxxMapper;
import com.guodu.pojo.dtu.*;
import com.guodu.service.impl.BaseServiceImpl;
import com.guodu.service.dtu.JbxxGjdzService;
import com.guodu.util.FileHandleUtils;
import com.guodu.util.QRCodeUtil;
import com.guodu.util.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.util.*;

@Service
public class JbxxServiceImpl extends BaseServiceImpl<Jbxx> {
    @Autowired
    private JbxxMapper jbxxMapper;

    public Jbxx selectByPrimaryKey(String tsid) {
        return jbxxMapper.selectByPrimaryKey(tsid);
    }

    public int updateByPrimaryKey(Jbxx record) {
        return jbxxMapper.updateByPrimaryKey(record);
    }

    /**
     * jbxx导出
     **/
    @Autowired
    private ExportDocumentService exportDocumentService;

    @Autowired
    private JlWgpzServiceImpl jlWgpzServiceImpl;
    @Autowired
    private JlJydzServiceImpl jlJydzServiceImpl;
    @Autowired
    private JlHldzServiceImpl jlHldzServiceImpl;
    @Autowired
    private JlDyxnServiceImpl jlDyxnServiceImpl;
    @Autowired
    private JlLpjyServiceImpl jlLpjyServiceImpl;
    @Autowired
    private JlCyjdServiceImpl jlCyjdServiceImpl;
    @Autowired
    private JlDlhlServiceImpl jlDlhlServiceImpl;
    @Autowired
    private JlGncsServiceImpl jlGncsServiceImpl;
    @Autowired
    private JlCtgyServiceImpl jlCtgyServiceImpl;
    @Autowired
    private JlKggServiceImpl jlKggServiceImpl;
    @Autowired
    private JlDtuServiceImpl jlDtuServiceImpl;
    @Autowired
    private JlYsbjServiceImpl jlYsbjServiceImpl;
    @Autowired
    private JlXbServiceImpl jlXbServiceImpl;
    @Autowired
    private JlYktsServiceImpl jlYktsServiceImpl;
    @Autowired
    private JlYctsServiceImpl jlYctsServiceImpl;
    @Autowired
    private JlGjcsServiceImpl jlGjcsServiceImpl;

    public XWPFDocument exportToWordByWord(String ssqy, Jbxx jbxx) throws Exception {
        Map vo = new HashMap();
        vo.put("tsid", jbxx.getTsid());
        vo.put("ssqy", ssqy);
        XWPFDocument document = null;
        try {
            document = exportDocumentService.createDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
        exportDocumentService.createTableByJbxx(document, jbxx);
        JbxxBeizhu jbxxBeizhu = jbxxBeizhuServiceImpl.selectByPrimaryKey(jbxx.getTsid());
        JbxxGjdz jbxxGjdz = jbxxGjdzServiceImpl.selectByPrimaryKey(jbxx.getTsid());

        List<JlWgpz> wgpzs = jlWgpzServiceImpl.selectList(vo);//DTU外观配置测试
        Map map = new HashMap();
        map.put("REMARK",jbxxBeizhu.getJlWgpz());
            for (JlWgpz wgpz : wgpzs) {
            if (wgpz.getCsxm().equals("模块配置")) {
                map.put("1", wgpz.getCsjg() == null ? "" : wgpz.getCsjg());
            } else if (wgpz.getCsxm().equals("热拔插功能 单元标配容量")) {
                map.put("2", wgpz.getCsjg() == null ? "" : wgpz.getCsjg());
            } else if (wgpz.getCsyq().equals("至少4 个串口")) {
                map.put("3", wgpz.getCsjg() == null ? "" : wgpz.getCsjg());
            } else if (wgpz.getCsyq().equals("至少2 个网口")) {
                map.put("4", wgpz.getCsjg() == null ? "" : wgpz.getCsjg());
            }
        }
        exportDocumentService.createTableByWgpz(document, map);


        List<JlJydz> jydzs = jlJydzServiceImpl.selectList(vo);//绝缘电阻测试
        map.clear();
        map.put("REMARK",jbxxBeizhu.getJlJydz());
        for (JlJydz jydz : jydzs) {
            String gcyq = jydz.getGcyq() == null ? "" : jydz.getGcyq().toString();
            String clzz = jydz.getClzz() == null ? "" : jydz.getClzz().toString();
            if (jydz.getBchl().equals("电流回路对地")) {
                map.put("R1_1", gcyq);
                map.put("R1_2", clzz);
            } else if (jydz.getBchl().equals("电压回路对地")) {
                map.put("R2_1", gcyq);
                map.put("R2_2", clzz);
            } else if (jydz.getBchl().equals("信号回路对地")) {
                map.put("R3_1", gcyq);
                map.put("R3_2", clzz);
            } else if (jydz.getBchl().equals("直流回路对地")) {
                map.put("R4_1", gcyq);
                map.put("R4_2", clzz);
            } else if (jydz.getBchl().equals("电流回路对电压回路")) {
                map.put("R5_1", gcyq);
                map.put("R5_2", clzz);
            } else if (jydz.getBchl().equals("电流回路对控制回路")) {
                map.put("R6_1", gcyq);
                map.put("R6_2", clzz);
            }
        }
        exportDocumentService.createTableByJydz(document, map);


        List<List<JlHldz>> hldzs = new ArrayList<List<JlHldz>>();//CT回路电阻测试
        List<Map<String,String>> jgGroups = jlHldzServiceImpl.selectByJgGroup(jbxx.getTsid());//CT回路电阻测试
        for(Map jg:jgGroups){
            vo.put("jg",jg.get("jg"));
            List<JlHldz> hldzByData = jlHldzServiceImpl.selectList(vo);//CT回路电阻测试
            hldzs.add(hldzByData);
        }
        exportDocumentService.createTableByHldz(document, hldzs,jbxxBeizhu.getJlHldz());


        List<JlDyxn> dyxns = jlDyxnServiceImpl.selectList(vo);//电源性能测试
        map.clear();
        map.put("REMARK",jbxxBeizhu.getJlDyxn());
        for (JlDyxn dyxn : dyxns) {
            String jcjg = dyxn.getJcjg() == null ? "" : dyxn.getJcjg();
            if (dyxn.getCsxm().equals("后备电源")) {
                if (dyxn.getJsbz().equals("电池容量（参考标准8.5Ah）")) {
                    map.put("R2_JG", jcjg);
                } else if (dyxn.getJsbz().equals("交流失电告警")) {
                    map.put("R3_JG", jcjg);
                } else if (dyxn.getJsbz().equals("任一交流失电、后备电源更换或失效等情况都不影响终端及通信功能和开关的分合储能操作")) {
                    map.put("R4_JG", jcjg);
                } else {
                    map.put("R5_JG", jcjg);
                }
            } else {
                map.put("R1_JG", jcjg);
            }

        }
        exportDocumentService.createTableByDyxn(document, map);

        List<List<JlLpjy>> lpjyList = new ArrayList<List<JlLpjy>>();
        List<List<JlLpjy>> lpjyByDyList = new ArrayList<List<JlLpjy>>();
        List<Map<String,String>> jgGroupByLpjy = jlLpjyServiceImpl.selectByJgGroup(jbxx.getTsid());//CT回路电阻测试
        for(Map lpjy:jgGroupByLpjy){
            String lpjyJg = lpjy.get("jg").toString();
            vo.put("jg",lpjyJg);
            List<JlLpjy> lpjyByData = jlLpjyServiceImpl.selectList(vo);//CT回路电阻测试
            if(!lpjyJg.equals("电压")){
                lpjyList.add(lpjyByData);
            }else{
                lpjyByDyList.add(lpjyByData);
            }
        }
        vo.remove("jg");
        exportDocumentService.createTableByLpjy(document, lpjyList,lpjyByDyList,jbxxBeizhu.getJlLpjy());

        List<JlCyjd> jlCyjds = jlCyjdServiceImpl.selectList(vo);//采样精度测试
        exportDocumentService.createTableByCyjd(document, jlCyjds,jbxxBeizhu.getJlCyjd());

        List<JlDlhl> jlDlhls = jlDlhlServiceImpl.selectList(vo);//模拟CT一次电流验证回路
        exportDocumentService.createTableByDlhl(document, jlDlhls,jbxxBeizhu.getJlDlhl());

        List<JlGncs> jlGncss = jlGncsServiceImpl.selectList(vo);//功能测试
        map.clear();
        map.put("REMARK",jbxxBeizhu.getJlGncs());
        for (JlGncs jlGncs : jlGncss) {
            String jg = jlGncs.getJg() == null ? "" : jlGncs.getJg();
            if (jlGncs.getCsgn().equals("遥控明确断开点测试")) {
                map.put("R1_JG", jg);
            } else if (jlGncs.getCsgn().equals("故障指示")) {
                map.put("R2_JG", jg);
            } else {
                if (jlGncs.getJsbz().equals("故障指示灯具备自动复归功能（可设定时延）")) {
                    map.put("R3_JG", jg);
                } else {
                    map.put("R4_JG", jg);
                }
            }
        }
        exportDocumentService.createTableByGncs(document, map);

        List<JlCtgy> jlCtgys = jlCtgyServiceImpl.selectList(vo);//CT安装工艺检查
        map.clear();
        map.put("REMARK",jbxxBeizhu.getJlCtgy());
        for (JlCtgy jlCtgy : jlCtgys) {
            if (jlCtgy.getAzyq().equals("CT接地小辫不能造成CT回路短路")) {
                map.put("R2_JG", jlCtgy.getJg() == null ? "" : jlCtgy.getJg());
            } else {
                map.put("R1_JG", jlCtgy.getJg() == null ? "" : jlCtgy.getJg());
            }
        }
        exportDocumentService.createTableByCtgy(document, map);

        List<JlKgg> jlKggs = jlKggServiceImpl.selectList(vo);//开关柜记录
        JbxxGjdz jbxxGjdz_kgg = jbxxGjdzServiceImpl.selectByPrimaryKey(jbxx.getTsid());
        exportDocumentService.createTableByKgg(document, jlKggs,jbxxBeizhu.getJlKgg(),jbxxGjdz_kgg);

        List<JlDtu> jlDtus = jlDtuServiceImpl.selectList(vo);//dtu
        if (jlDtus != null && jlDtus.size() > 0)
            exportDocumentService.createTableByDtu(document, jlDtus.get(0),jbxxBeizhu.getJlDtu());

        List<JlYsbj> jlYsbjs = jlYsbjServiceImpl.selectList(vo);//溢水报警装置
        if (jlYsbjs != null && jlYsbjs.size() > 0)
            exportDocumentService.createTableByYsbj(document, jlYsbjs.get(0),jbxxBeizhu.getJlYsbj());


        if(jbxx.getZzlx().equals("4")){
            List<JlXb> jlXbs = jlXbServiceImpl.selectList(vo);//箱变
            if (jlXbs != null && jlXbs.size() > 0)
                exportDocumentService.createTableByXb(document, jlXbs.get(0),jbxxBeizhu.getJlXb());
        }

        List<JlYkts> jlYktss = jlYktsServiceImpl.selectList(vo);//11、遥控功能调试
        exportDocumentService.createTableByYkts(document, jlYktss,jbxxBeizhu.getJlYkts());

        List<JlYcts> jlYctss = jlYctsServiceImpl.selectList(vo);//12、遥测功能调试
        map.clear();
        map.put("REMARK",jbxxBeizhu.getJlYcts());
        /*for (JlYcts jlYcts : jlYctss) {
            String ddyc1 = jlYcts.getDdyc1();
            String ddyc2 = jlYcts.getDdyc2();
            if (jlYcts.getCsxm().equals("4#母线PT")) {
                map.put("R1_100V", ddyc1);
                map.put("R1_220V", ddyc2);
            } else if (jlYcts.getCsxm().equals("5#母线PT")) {
                map.put("R2_100V", ddyc1);
                map.put("R2_220V", ddyc2);
            } else if (jlYcts.getCsxm().equals("4#母线电压（主站一次值）")) {
                map.put("R3_100V", ddyc1);
                map.put("R3_220V", ddyc2);
            } else if (jlYcts.getCsxm().equals("5#母线电压（主站一次值）")) {
                map.put("R4_100V", ddyc1);
                map.put("R4_220V", ddyc2);
            } else {
                map.put("R5_100V", ddyc1);
                map.put("R5_220V", ddyc2);
            }
        }*/
        map.put("jlYctss",jlYctss);
        exportDocumentService.createTableByYcts(document, map);

        vo.put("gjlx", "过流告警/事故总");
        List<JlGjcs> jlGjcss1 = jlGjcsServiceImpl.selectList(vo);//13、告警功能调试_过流告警/事故总
        vo.put("gjlx", "零序告警/事故总");
        List<JlGjcs> jlGjcss2 = jlGjcsServiceImpl.selectList(vo);//13、告警功能调试_零序告警/事故总

        exportDocumentService.createTableByGjcs(document, jlGjcss1, jlGjcss2,jbxxBeizhu.getJlGjcs(),jbxxGjdz);
        return document;
    }

    public String selectListJbxxToState(Map vo) {
        String state = "false";

        List<JlWgpz> jlWgpzs = jlWgpzServiceImpl.selectList(vo);
        if (jlWgpzs == null || jlWgpzs.size() < 1) {
            return state;
        } else {
            for (JlWgpz jlWgpz : jlWgpzs) {
                if (jlWgpz.getCsjg() == null || jlWgpz.getCsjg().equals("") || jlWgpz.getCsjg().equals("-1")) {
                    return state;
                }
            }
        }

        List<JlJydz> jlJydzs = jlJydzServiceImpl.selectList(vo);
        if (jlJydzs == null || jlJydzs.size() < 1) {
            return state;
        } else {
            for (JlJydz jlJydz : jlJydzs) {
                if (jlJydz.getGcyq() == null || jlJydz.getGcyq().equals("")) {
                    return state;
                } else if (jlJydz.getClzz() == null || jlJydz.getClzz().equals("")) {
                    return state;
                }
            }
        }

        List<JlHldz> jlHldzs = jlHldzServiceImpl.selectList(vo);
        if (jlHldzs == null || jlHldzs.size() < 1) {
            return state;
        } else {
            for (JlHldz jlHldz : jlHldzs) {
                if (jlHldz.getZzc() == null || jlHldz.getZzc().equals("")) {
                    return state;
                } else if (jlHldz.getCtc() == null || jlHldz.getCtc().equals("")) {
                    return state;
                }
            }
        }

        List<JlDyxn> jlDyxns = jlDyxnServiceImpl.selectList(vo);
        if (jlDyxns == null || jlDyxns.size() < 1) {
            return state;
        } else {
            for (JlDyxn jlDyxn : jlDyxns) {
                if (jlDyxn.getJcjg() == null || jlDyxn.getJcjg().equals("") || jlDyxn.getJcjg().equals("-1")) {
                    return state;
                }
            }
        }

        List<JlLpjy> jlLpjys = jlLpjyServiceImpl.selectList(vo);
        if (jlLpjys == null || jlLpjys.size() < 1) {
            return state;
        } else {
            for (JlLpjy jlLpjy : jlLpjys) {
                if (jlLpjy.getJcjg() == null || jlLpjy.getJcjg().equals("")) {
                    return state;
                } else if (jlLpjy.getZzds() == null || jlLpjy.getZzds().equals("")) {
                    return state;
                }
            }
        }

        List<JlCyjd> jlCyjds = jlCyjdServiceImpl.selectList(vo);
        if (jlCyjds == null || jlCyjds.size() < 1) {
            return state;
        } else {
            for (JlCyjd jlCyjd : jlCyjds) {
                if (jlCyjd.getA() == null || jlCyjd.getA().equals("")) {
                    return state;
                } else if (jlCyjd.getC() == null || jlCyjd.getC().equals("")) {
                    return state;
                } else if (jlCyjd.getLx() == null || jlCyjd.getLx().equals("")) {
                    return state;
                } else if (jlCyjd.getCsjg() == null || jlCyjd.getCsjg().equals("")) {
                    return state;
                }
            }
        }

        List<JlDlhl> jlDlhls = jlDlhlServiceImpl.selectList(vo);
        if (jlDlhls == null || jlDlhls.size() < 1) {
            return state;
        } else {
            for (JlDlhl jlDlhl : jlDlhls) {
                if (jlDlhl.getA() == null || jlDlhl.getA().equals("")) {
                    return state;
                } else if (jlDlhl.getC() == null || jlDlhl.getC().equals("")) {
                    return state;
                } else if (jlDlhl.getLx() == null || jlDlhl.getLx().equals("")) {
                    return state;
                } else if (jlDlhl.getCsjg() == null || jlDlhl.getCsjg().equals("")) {
                    return state;
                }
            }
        }

        List<JlGncs> jlGncss = jlGncsServiceImpl.selectList(vo);
        if (jlGncss == null || jlGncss.size() < 1) {
            return state;
        } else {
            for (JlGncs jlGncs : jlGncss) {
                if (jlGncs.getJg() == null || jlGncs.getJg().equals("") || jlGncs.getJg().equals("-1")) {
                    return state;
                }
            }
        }

        List<JlCtgy> jlCtgys = jlCtgyServiceImpl.selectList(vo);
        if (jlCtgys == null || jlCtgys.size() < 1) {
            return state;
        } else {
            for (JlCtgy jlCtgy : jlCtgys) {
                if (jlCtgy.getJg() == null || jlCtgy.getJg().equals("") || jlCtgy.getJg().equals("-1")) {
                    return state;
                }
            }
        }

        List<JlKgg> jlKggs = jlKggServiceImpl.selectList(vo);
        if (jlKggs == null || jlKggs.size() < 1) {
            return state;
        } else {
            for (JlKgg jlKgg : jlKggs) {
                if (jlKgg.getSbyf() == null || jlKgg.getSbyf().equals("") || jlKgg.getSbyf().equals("-1")) {
                    return state;
                } else if (jlKgg.getSbjd() == null || jlKgg.getSbjd().equals("") || jlKgg.getSbjd().equals("-1")) {
                    return state;
                } else if (jlKgg.getDdh() == null || jlKgg.getDdh().equals("") || jlKgg.getDdh().equals("-1")) {
                    return state;
                } else if (jlKgg.getDdf() == null || jlKgg.getDdf().equals("") || jlKgg.getDdf().equals("-1")) {
                    return state;
                } else if (jlKgg.getKgf() == null || jlKgg.getKgf().equals("") || jlKgg.getKgf().equals("-1")) {
                    return state;
                } else if (jlKgg.getKgh() == null || jlKgg.getKgh().equals("") || jlKgg.getKgh().equals("-1")) {
                    return state;
                }
            }
        }

        List<JlDtu> jlDtus = jlDtuServiceImpl.selectList(vo);
        if (jlDtus == null || jlDtus.size() < 1) {
            return state;
        } else {
            for (JlDtu jlDtu : jlDtus) {
                if (jlDtu.getYf() == null || jlDtu.getYf().equals("")) {
                    return state;
                } else if (jlDtu.getJd() == null || jlDtu.getJd().equals("") || jlDtu.getJd().equals("-1")) {
                    return state;
                } else if (jlDtu.getDcqy() == null || jlDtu.getDcqy().equals("") || jlDtu.getDcqy().equals("-1")) {
                    return state;
                } else if (jlDtu.getDchh() == null || jlDtu.getDchh().equals("") || jlDtu.getDchh().equals("-1")) {
                    return state;
                } else if (jlDtu.getJlsd() == null || jlDtu.getJlsd().equals("") || jlDtu.getJlsd().equals("-1")) {
                    return state;
                }
            }
        }
        List<JlYsbj> jlYsbjs = jlYsbjServiceImpl.selectList(vo);
        if (jlYsbjs == null || jlYsbjs.size() < 1) {
            return state;
        } else {
            for (JlYsbj jlYsbj : jlYsbjs) {
                if (jlYsbj.getGswgj() == null || jlYsbj.getGswgj().equals("") || jlYsbj.getGswgj().equals("-1")) {
                    return state;
                } else if (jlYsbj.getDswgj() == null || jlYsbj.getDswgj().equals("") || jlYsbj.getDswgj().equals("-1")) {
                    return state;
                } else if (jlYsbj.getSbqdgj() == null || jlYsbj.getSbqdgj().equals("") || jlYsbj.getSbqdgj().equals("-1")) {
                    return state;
                } else if (jlYsbj.getYszzyc() == null || jlYsbj.getYszzyc().equals("") || jlYsbj.getYszzyc().equals("-1")) {
                    return state;
                }
            }
        }

        List<JlXb> jlXbs = jlXbServiceImpl.selectList(vo);
        if (jlXbs == null || jlXbs.size() < 1) {
            return state;
        } else {
            for (JlXb jlXb : jlXbs) {
                if (jlXb.getByqcwgj() == null || jlXb.getByqcwgj().equals("") || jlXb.getByqcwgj().equals("-1")) {
                    return state;
                } else if (jlXb.getByqgwgj() == null || jlXb.getByqgwgj().equals("") || jlXb.getByqgwgj().equals("-1")) {
                    return state;
                }
            }
        }

        List<JlYkts> jlYktss = jlYktsServiceImpl.selectList(vo);
        if (jlYktss == null || jlYktss.size() < 1) {
            return state;
        } else {
            for (JlYkts jlYkts : jlYktss) {
                if (jlYkts.getYkf() == null || jlYkts.getYkf().equals("") || jlYkts.getYkf().equals("-1")) {
                    return state;
                } else if (jlYkts.getYkh() == null || jlYkts.getYkh().equals("") || jlYkts.getYkf().equals("-1")) {
                    return state;
                }
            }
        }

        List<JlYcts> jlYctss = jlYctsServiceImpl.selectList(vo);
        if (jlYctss == null || jlYctss.size() < 1) {
            return state;
        } else {
            for (JlYcts jlYcts : jlYctss) {
                if (jlYcts.getDdyc1() == null || jlYcts.getDdyc1().equals("")) {
                    return state;
                } else if (jlYcts.getDdyc2() == null || jlYcts.getDdyc2().equals("")) {
                    return state;
                }
            }
        }

        List<JlGjcs> jlGjcss = jlGjcsServiceImpl.selectList(vo);
        if (jlGjcss == null || jlGjcss.size() < 1) {
            return state;
        } else {
            for (JlGjcs jlGjcs : jlGjcss) {
                if (jlGjcs.getA1() == null || jlGjcs.getA1().equals("")) {
                    return state;
                } else if (jlGjcs.getA2() == null || jlGjcs.getA2().equals("")) {
                    return state;
                } else if (jlGjcs.getC1() == null || jlGjcs.getC1().equals("")) {
                    return state;
                } else if (jlGjcs.getC2() == null || jlGjcs.getC2().equals("")) {
                    return state;
                } else if (jlGjcs.getL1() == null || jlGjcs.getL1().equals("")) {
                    return state;
                } else if (jlGjcs.getL2() == null || jlGjcs.getL2().equals("")) {
                    return state;
                } else if (jlGjcs.getCsjg1() == null || jlGjcs.getCsjg1().equals("")) {
                    return state;
                }
            }
        }
        state = "true";
        return state;
    }

    /* 添加修改调试信息 和 调试照片*/
    @Autowired
    JbxxPhotoServiceImpl jbxxPhotoServiceImpl;
    @Autowired
    CreateTsjlServiceImpl createTsjlServiceImpl;
    @Autowired
    JbxxBeizhuServiceImpl jbxxBeizhuServiceImpl;
    @Autowired
    JbxxGjdzService jbxxGjdzServiceImpl;
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void txAddJbxxAndPhoto(HttpServletRequest request, Jbxx jbxx, String imgSavePath) throws Exception {
        //生成所有记录
        createTsjlServiceImpl.insertJlByJbxx(jbxx);
        this.jbxxMapper.insert(jbxx);
        //插入备注表tsid
        JbxxBeizhu jbxxBeizhu = new JbxxBeizhu();
        jbxxBeizhu.setTsid(jbxx.getTsid());
        jbxxBeizhuServiceImpl.insert(jbxxBeizhu);
        //插入告警定值
        JbxxGjdz jbxxGjdz = new JbxxGjdz();
        jbxxGjdz.setTsid(jbxx.getTsid());
        jbxxGjdz.setQygj1("1");
        jbxxGjdz.setQygj2("1");
        jbxxGjdzServiceImpl.insert(jbxxGjdz);
        //上传文件
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        String[] imgNames = jbxx.getImgName();
        for (int i=0;i<files.size();i++) {
            file = files.get(i);
            String pid = StringUtils.createDateRandomString(new Date());
            FileHandleUtils.singlefileUpload(request,file,imgSavePath,pid);
            String fileName = file.getOriginalFilename();
            JbxxPhoto photo = new JbxxPhoto();
            photo.setPid(pid);
            photo.setTsid(jbxx.getTsid());
            if(imgNames.length<1 || imgNames[i]==null) photo.setPName("");
            else photo.setPName(imgNames[i]);
            photo.setPPath(imgSavePath + pid + fileName);
            this.jbxxPhotoServiceImpl.add(photo);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void txUpdateJbxxAndPhoto(HttpServletRequest request,Jbxx jbxx, String imgSavePath) throws Exception {
        //1.删除移除的照片
        String flagArrayStr = jbxx.getFlagArrayStr();
        if (!flagArrayStr.equals("")) {
            String[] fileIds = flagArrayStr.split(",");
            JbxxPhoto jbxxPhoto = null;
            for (String pid : fileIds) {
                jbxxPhoto = this.jbxxPhotoServiceImpl.selectById(pid);
                FileHandleUtils.deleteFile(jbxxPhoto.getPPath());
                this.jbxxPhotoServiceImpl.delete(pid);
            }
        }
        //2.更改未移除，但修改名称的照片
        List<JbxxPhoto> photos = jbxx.getPhoto();
        if(photos!=null && photos.size()>0){
                for(JbxxPhoto photo:photos){
                    photo.setPPath(null);
                    photo.setTsid(null);
                    jbxxPhotoServiceImpl.edit(photo);
                }
        }

        //3.修改基本信息，和添加新增的照片
        this.jbxxMapper.update(jbxx);
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        String[] imgNames = jbxx.getImgName();
        for (int i=0;i<files.size();i++) {
            file = files.get(i);
            String pid = StringUtils.createDateRandomString(new Date());
            FileHandleUtils.singlefileUpload(request,file,imgSavePath,pid);
            String fileName = file.getOriginalFilename();
            JbxxPhoto photo = new JbxxPhoto();
            photo.setPid(pid);
            photo.setTsid(jbxx.getTsid());
            if(imgNames.length<1 || imgNames[i]==null) photo.setPName("");
            else photo.setPName(imgNames[i]);
            photo.setPPath(imgSavePath + pid + fileName);
            this.jbxxPhotoServiceImpl.add(photo);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void txDeleteJbxxAndPhoto(String tsid) throws Exception {
        Map vo = new HashMap();
        vo.put("tsid",tsid);
        List<Map<String,Object>> photos = jbxxPhotoServiceImpl.selectAllByTsid(vo);
        for(Map<String,Object> photo:photos){
            FileHandleUtils.deleteFile(photo.get("P_PATH").toString());//删除磁盘文件
            jbxxPhotoServiceImpl.delete(photo.get("PID").toString());//删除文件记录
        }
        jbxxBeizhuServiceImpl.deleteByPrimaryKey(tsid);//删除备注表
        createTsjlServiceImpl.deleteJlByJbxx(tsid);//删除所有调试记录
        jbxxGjdzServiceImpl.deleteByPrimaryKey(tsid);//删除告警定值记录
        this.delete(tsid);
    }

    /***
     * 根据调试id生成二维码
     * @param request
     * @param jbxx
     * @return
     * @throws Exception
     */
    public BufferedImage createQRCodeByJbxxInfo(HttpServletRequest request, Jbxx jbxx) throws Exception {
        //反向地址解析  与标注相反location	lat<纬度>,lng<经度>	必选
        String url="http://api.map.baidu.com/marker?location="+jbxx.getWd()+","+jbxx.getJd()+"&title="+jbxx.getXxwz()
                +"&content="
                +"终端名称:"+jbxx.getZdmc()
                +"&output=html&src=webapp.baidu.openAPIdemo";
        BufferedImage img = QRCodeUtil.encode(url,"","",true);
        return img;
    }
}
