package com.guodu.service.impl.dtu;

import com.guodu.pojo.dtu.*;
import com.guodu.service.dtu.JbxxBeizhuService;
import com.guodu.service.dtu.JbxxGjdzService;
import com.guodu.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: TsServiceImpl
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/2/19 11:31 上午
 */
@Service
public class CreateTsjlServiceImpl {

    @Autowired
    private BzCtgyServiceImpl bzCtgyServiceImpl;
    @Autowired
    private BzCyjdServiceImpl bzCyjdServiceImpl;
    @Autowired
    private BzDlhlServiceImpl bzDlhlServiceImpl;
    @Autowired
    private BzDyxnServiceImpl bzDyxnServiceImpl;
    @Autowired
    private BzGjcsServiceImpl bzGjcsServiceImpl;
    @Autowired
    private BzGncsServiceImpl bzGncsServiceImpl;
    @Autowired
    private BzHldzServiceImpl bzHldzServiceImpl;
    @Autowired
    private BzJydzServiceImpl bzJydzServiceImpl;
    @Autowired
    private BzKggServiceImpl bzKggServiceImpl;
    @Autowired
    private BzLpjyServiceImpl bzLpjyServiceImpl;
    @Autowired
    private BzWgpzServiceImpl bzWgpzServiceImpl;
    @Autowired
    private BzYctsServiceImpl bzYctsServiceImpl;
    @Autowired
    private BzYktsServiceImpl bzYktsServiceImpl;

    @Autowired
    private JlCtgyServiceImpl jlCtgyServiceImpl;
    @Autowired
    private JlCyjdServiceImpl jlCyjdServiceImpl;
    @Autowired
    private JlDlhlServiceImpl jlDlhlServiceImpl;
    @Autowired
    private JlDtuServiceImpl jlDtuServiceImpl;
    @Autowired
    private JlDyxnServiceImpl jlDyxnServiceImpl;
    @Autowired
    private JlGjcsServiceImpl jlGjcsServiceImpl;
    @Autowired
    private JlGncsServiceImpl jlGncsServiceImpl;
    @Autowired
    private JlHldzServiceImpl jlHldzServiceImpl;
    @Autowired
    private JlJydzServiceImpl jlJydzServiceImpl;
    @Autowired
    private JlKggServiceImpl jlKggServiceImpl;
    @Autowired
    private JlLpjyServiceImpl jlLpjyServiceImpl;
    @Autowired
    private JlWgpzServiceImpl jlWgpzServiceImpl;
    @Autowired
    private JlXbServiceImpl jlXbServiceImpl;
    @Autowired
    private JlYctsServiceImpl jlYctsServiceImpl;
    @Autowired
    private JlYktsServiceImpl jlYktsServiceImpl;
    @Autowired
    private JlYsbjServiceImpl jlYsbjServiceImpl;
    @Autowired
    private JbxxBeizhuService jbxxBeizhuServiceImpl;
    @Autowired
    private JbxxGjdzService jbxxGjdzServiceImpl;

    public void insertJlByJbxx(Jbxx jbxx) {
        String tsid = jbxx.getTsid();
        String ssqy = jbxx.getSsqy();
        String czr = jbxx.getCzr();
        String zzlx = jbxx.getZzlx();

        // 从标准表查询
        BzCtgy bzCtgy = new BzCtgy();
        bzCtgy.setSsqy(ssqy);
        List<BzCtgy> bzCtgies = bzCtgyServiceImpl.selectByAll(bzCtgy);
        // 结果存入记录表
        for (BzCtgy ctgy : bzCtgies) {
            JlCtgy jlCtgy = new JlCtgy(IDUtil.getStrId(), tsid, ssqy, ctgy.getAzyq(), "1", czr, new Date(), null);
            jlCtgyServiceImpl.insert2(jlCtgy);
        }


        BzCyjd bzCyjd = new BzCyjd();
        bzCyjd.setSsqy(ssqy);
        bzCyjd.setZzlx(zzlx);
        List<BzCyjd> bzCyjds = bzCyjdServiceImpl.selectByAll(bzCyjd);
        for (BzCyjd cyjd : bzCyjds) {
            JlCyjd jlCyjd = new JlCyjd(IDUtil.getStrId(), tsid, ssqy, cyjd.getJg(), cyjd.getCsgn(), cyjd.getClx(), null,null, null, null, "1", czr, new Date(), null);
            jlCyjdServiceImpl.insert2(jlCyjd);
        }

        BzDlhl bzDlhl = new BzDlhl();
        bzDlhl.setSsqy(ssqy);
        bzDlhl.setZzlx(zzlx);
        List<BzDlhl> bzDlhls = bzDlhlServiceImpl.selectByAll(bzDlhl);
        for (BzDlhl dlhl : bzDlhls) {
            JlDlhl jlDlhl = new JlDlhl(IDUtil.getStrId(), tsid, ssqy, dlhl.getJg(), dlhl.getCsgn(), dlhl.getCsff(), null, null, null, null, "1", czr, new Date(), null);
            jlDlhlServiceImpl.insert2(jlDlhl);
        }

        // DTU无标准
        JlDtu jlDtu = new JlDtu(IDUtil.getStrId(), tsid, ssqy, "1", "1", "1", "1", "1", czr, new Date(), null);
        jlDtuServiceImpl.insert2(jlDtu);

        BzDyxn bzDyxn = new BzDyxn();
        bzDyxn.setSsqy(ssqy);
        List<BzDyxn> bzDyxns = bzDyxnServiceImpl.selectByAll(bzDyxn);
        for (BzDyxn dyxn : bzDyxns) {
            JlDyxn jlDyxn = new JlDyxn(IDUtil.getStrId(), tsid, ssqy, dyxn.getCsxm(), dyxn.getJsbz(), "1", czr, new Date(), null);
            jlDyxnServiceImpl.insert2(jlDyxn);
        }

        BzGjcs bzGjcs = new BzGjcs();
        bzGjcs.setSsqy(ssqy);
        bzGjcs.setZzlx(zzlx);
        List<BzGjcs> bzGjcss = bzGjcsServiceImpl.selectByAll(bzGjcs);
        for (BzGjcs gjcs : bzGjcss) {
            JlGjcs jlGjcs = new JlGjcs(IDUtil.getStrId(), tsid, ssqy, gjcs.getJg(), gjcs.getGjlx(), null, null, null,null,null, null, null, null, "1", null, czr, new Date(), null);
            jlGjcsServiceImpl.insert2(jlGjcs);
        }

        BzGncs bzGncs = new BzGncs();
        bzGncs.setSsqy(ssqy);
        List<BzGncs> bzGncss = bzGncsServiceImpl.selectByAll(bzGncs);
        for (BzGncs gncs : bzGncss) {
            JlGncs jlGncs = new JlGncs(IDUtil.getStrId(), tsid, ssqy, gncs.getCsgn(), gncs.getJsbz(), "1", czr, new Date(), null);
            jlGncsServiceImpl.insert2(jlGncs);
        }

        BzHldz bzHldz = new BzHldz();
        bzHldz.setSsqy(ssqy);
        bzHldz.setZzlx(zzlx);
        List<BzHldz> bzHldzs = bzHldzServiceImpl.selectByAll(bzHldz);
        for (BzHldz hldz : bzHldzs) {
            JlHldz jlHldz = new JlHldz(IDUtil.getStrId(), tsid, ssqy, hldz.getJg(), hldz.getHl(), null, null, czr, new Date(), null);
            jlHldzServiceImpl.insert2(jlHldz);
        }

        BzJydz bzJydz = new BzJydz();
        bzJydz.setSsqy(ssqy);
        List<BzJydz> bzJydzs = bzJydzServiceImpl.selectByAll(bzJydz);
        for (BzJydz jydz : bzJydzs) {
            JlJydz jlJydz = new JlJydz(IDUtil.getStrId(), tsid, ssqy, jydz.getBchl(), jydz.getSydy(), null, null, czr, new Date(), null);
            jlJydzServiceImpl.insert2(jlJydz);
        }

        BzKgg bzKgg = new BzKgg();
        bzKgg.setSsqy(ssqy);
        bzKgg.setZzlx(zzlx);
        List<BzKgg> bzKggs = bzKggServiceImpl.selectByAll(bzKgg);
        for (BzKgg kgg : bzKggs) {
            JlKgg jlKgg = new JlKgg(IDUtil.getStrId(), tsid, ssqy, kgg.getJg(), "1", "1", "1", "1", "1", "1", czr, new Date(), null);
            jlKggServiceImpl.insert2(jlKgg);
        }

        BzLpjy bzLpjy = new BzLpjy();
        bzLpjy.setSsqy(ssqy);
        bzLpjy.setZzlx(zzlx);
        List<BzLpjy> bzLpjys = bzLpjyServiceImpl.selectByAll(bzLpjy);
        for (BzLpjy lpjy : bzLpjys) {
            JlLpjy jlLpjy = new JlLpjy(IDUtil.getStrId(), tsid, ssqy, lpjy.getJg(), lpjy.getHl(), null, "1", czr, new Date(), null);
            jlLpjyServiceImpl.insert2(jlLpjy);
        }

        BzWgpz bzWgpz = new BzWgpz();
        bzWgpz.setSsqy(ssqy);
        List<BzWgpz> bzWgpzs = bzWgpzServiceImpl.selectByAll(bzWgpz);
        for (BzWgpz wgpz : bzWgpzs) {
            JlWgpz jlWgpz = new JlWgpz(IDUtil.getStrId(), tsid, ssqy, wgpz.getCsxm(), wgpz.getCsyq(), "1", czr, new Date(), null);
            jlWgpzServiceImpl.insert2(jlWgpz);
        }

        // xb无标准
        JlXb jlXb = new JlXb(IDUtil.getStrId(), tsid, ssqy, "1", "1", czr, new Date(), null);
        jlXbServiceImpl.insert2(jlXb);

        BzYcts bzYcts = new BzYcts();
        bzYcts.setSsqy(ssqy);
        List<BzYcts> bzYctss = bzYctsServiceImpl.selectByAll(bzYcts);
        for (BzYcts ycts : bzYctss) {
            JlYcts jlYcts = new JlYcts(IDUtil.getStrId(), tsid, ssqy, ycts.getCsxm(), null, null, czr, new Date(), null);
            jlYctsServiceImpl.insert2(jlYcts);
        }

        BzYkts bzYkts = new BzYkts();
        bzYkts.setSsqy(ssqy);
        bzYkts.setZzlx(zzlx);
        List<BzYkts> bzYktss = bzYktsServiceImpl.selectByAll(bzYkts);
        for (BzYkts ykts : bzYktss) {
            JlYkts jlYkts = new JlYkts(IDUtil.getStrId(), tsid, ssqy, ykts.getJg(), "1", "1", czr, new Date(), null);
            jlYktsServiceImpl.insert2(jlYkts);
        }

        // ysbj无标准
        JlYsbj jlYsbj = new JlYsbj(IDUtil.getStrId(), tsid, ssqy, "1", "1", "1", "1", czr, new Date(), null);
        jlYsbjServiceImpl.insert2(jlYsbj);

        /*// 插入空记录到备注表
        JbxxBeizhu jbxxBeizhu = new JbxxBeizhu(tsid, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        jbxxBeizhuServiceImpl.insert(jbxxBeizhu);*/

       /* // 插入空记录到告警定值
        JbxxGjdz jbxxGjdz = new JbxxGjdz(tsid, null, null, null, null, null, null);
        jbxxGjdzServiceImpl.insert(jbxxGjdz);*/
    }

    public void deleteJlByJbxx(String tsid) {
        jlCtgyServiceImpl.deleteByTsid(tsid);
        jlCyjdServiceImpl.deleteByTsid(tsid);
        jlDlhlServiceImpl.deleteByTsid(tsid);
        jlDtuServiceImpl.deleteByTsid(tsid);
        jlDyxnServiceImpl.deleteByTsid(tsid);
        jlGjcsServiceImpl.deleteByTsid(tsid);
        jlGncsServiceImpl.deleteByTsid(tsid);
        jlHldzServiceImpl.deleteByTsid(tsid);
        jlJydzServiceImpl.deleteByTsid(tsid);
        jlKggServiceImpl.deleteByTsid(tsid);
        jlLpjyServiceImpl.deleteByTsid(tsid);
        jlWgpzServiceImpl.deleteByTsid(tsid);
        jlXbServiceImpl.deleteByTsid(tsid);
        jlYctsServiceImpl.deleteByTsid(tsid);
        jlYktsServiceImpl.deleteByTsid(tsid);
        jlYsbjServiceImpl.deleteByTsid(tsid);
    }
}
