package com.guodu.service.impl.pwbh;

import com.guodu.pojo.pwbh.*;
import com.guodu.service.pwbh.*;
import com.guodu.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: PwbhTsjlServiceImpl
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/6/15 2:34 下午
 */
@Service
public class PwbhTsjlServiceImpl implements PwbhTsjlService {
    @Autowired
    private PwbhBzBhctService pwbhBzBhctServiceImpl;
    @Autowired
    private PwbhBzDzjcService pwbhBzDzjcServiceImpl;
    @Autowired
    private PwbhBzJdjyService pwbhBzJdjyServiceImpl;
    @Autowired
    private PwbhBzJxdxService pwbhBzJxdxServiceImpl;
    @Autowired
    private PwbhBzJycsService pwbhBzJycsServiceImpl;
    @Autowired
    private PwbhBzLpjyService pwbhBzLpjyServiceImpl;
    @Autowired
    private PwbhBzSgjcService pwbhBzSgjcServiceImpl;
    @Autowired
    private PwbhBzWgjcService pwbhBzWgjcServiceImpl;
    @Autowired
    private PwbhBzYqybService pwbhBzYqybServiceImpl;
    @Autowired
    private PwbhBzZzsyService pwbhBzZzsyServiceImpl;

    @Autowired
    private PwbhJlBhctbhService pwbhJlBhctbhServiceImpl;
    @Autowired
    private PwbhJlBhcthlService pwbhJlBhcthlServiceImpl;
    @Autowired
    private PwbhJlBhctjxService pwbhJlBhctjxServiceImpl;
    @Autowired
    private PwbhJlDzdjcService pwbhJlDzdjcServiceImpl;
    @Autowired
    private PwbhJlDzjcService pwbhJlDzjcServiceImpl;
    @Autowired
    private PwbhJlJdjyService pwbhJlJdjyServiceImpl;
    @Autowired
    private PwbhJlJxdxService pwbhJlJxdxServiceImpl;
    @Autowired
    private PwbhJlJycsService pwbhJlJycsServiceImpl;
    @Autowired
    private PwbhJlLpjyService pwbhJlLpjyServiceImpl;
    @Autowired
    private PwbhJlSgjcService pwbhJlSgjcServiceImpl;
    @Autowired
    private PwbhJlWgjcService pwbhJlWgjcServiceImpl;
    @Autowired
    private PwbhJlYqybService pwbhJlYqybServiceImpl;
    @Autowired
    private PwbhJlZzsyService pwbhJlZzsyServiceImpl;
    @Autowired
    private PwbhJbxxBeizhuService pwbhJbxxBeizhuServiceImpl;

    @Override
    public void insertPwbhTsjlByPwbhJbxx(PwbhJbxx jbxx) {
        String tsid = jbxx.getTsid();
        String ssqy = jbxx.getSsqy();
        String czr = jbxx.getCzr();

        PwbhBzYqyb pwbhBzYqyb = new PwbhBzYqyb();
        pwbhBzYqyb.setSsqy(ssqy);
        List<PwbhBzYqyb> pwbhBzYqybs = pwbhBzYqybServiceImpl.selectByAll(pwbhBzYqyb);
        for (PwbhBzYqyb bzYqyb : pwbhBzYqybs) {
            PwbhJlYqyb pwbhJlYqyb = new PwbhJlYqyb(IDUtil.getStrId(), tsid, ssqy, bzYqyb.getYqmc(), null, null, null, czr, new Date(), null);
            pwbhJlYqybServiceImpl.insert(pwbhJlYqyb);
        }

        PwbhBzWgjc pwbhBzWgjc = new PwbhBzWgjc();
        pwbhBzWgjc.setSsqy(ssqy);
        List<PwbhBzWgjc> pwbhBzWgjcs = pwbhBzWgjcServiceImpl.selectByAll(pwbhBzWgjc);
        for (PwbhBzWgjc bzWgjc : pwbhBzWgjcs) {
            PwbhJlWgjc pwbhJlWgjc = new PwbhJlWgjc(IDUtil.getStrId(), tsid, ssqy, bzWgjc.getNr(), null, czr, new Date(), null);
            pwbhJlWgjcServiceImpl.insert(pwbhJlWgjc);
        }

        PwbhBzJxdx pwbhBzJxdx = new PwbhBzJxdx();
        pwbhBzJxdx.setSsqy(ssqy);
        List<PwbhBzJxdx> pwbhBzJxdxes = pwbhBzJxdxServiceImpl.selectByAll(pwbhBzJxdx);
        for (PwbhBzJxdx bzJxdx : pwbhBzJxdxes) {
            PwbhJlJxdx pwbhJlJxdx = new PwbhJlJxdx(IDUtil.getStrId(), tsid, ssqy, bzJxdx.getNr(), null, czr, new Date(), null);
            pwbhJlJxdxServiceImpl.insert(pwbhJlJxdx);
        }

        PwbhBzJycs pwbhBzJycs = new PwbhBzJycs();
        pwbhBzJycs.setSsqy(ssqy);
        List<PwbhBzJycs> pwbhBzJycss = pwbhBzJycsServiceImpl.selectByAll(pwbhBzJycs);
        for (PwbhBzJycs bzJycss : pwbhBzJycss) {
            PwbhJlJycs pwbhJlJycs = new PwbhJlJycs(IDUtil.getStrId(), tsid, ssqy, bzJycss.getJcnr(), bzJycss.getBz(), null, czr, new Date(), null);
            pwbhJlJycsServiceImpl.insert(pwbhJlJycs);
        }

        PwbhBzLpjy pwbhBzLpjy = new PwbhBzLpjy();
        pwbhBzLpjy.setSsqy(ssqy);
        List<PwbhBzLpjy> pwbhBzLpjies = pwbhBzLpjyServiceImpl.selectByAll(pwbhBzLpjy);
        for (PwbhBzLpjy bzLpjy : pwbhBzLpjies) {
            PwbhJlLpjy pwbhJlLpjy = new PwbhJlLpjy(IDUtil.getStrId(), tsid, ssqy, bzLpjy.getCsjg(), null, czr, new Date(), null);
            pwbhJlLpjyServiceImpl.insert(pwbhJlLpjy);
        }

        PwbhBzJdjy pwbhBzJdjy = new PwbhBzJdjy();
        pwbhBzJdjy.setSsqy(ssqy);
        List<PwbhBzJdjy> pwbhBzJdjies = pwbhBzJdjyServiceImpl.selectByAll(pwbhBzJdjy);
        for (PwbhBzJdjy bzJdjy : pwbhBzJdjies) {
            PwbhJlJdjy pwbhJlJdjy = new PwbhJlJdjy(IDUtil.getStrId(), tsid, ssqy, bzJdjy.getCsjg(), bzJdjy.getTryq(), null, null, czr, new Date(), null);
            pwbhJlJdjyServiceImpl.insert(pwbhJlJdjy);
        }

        PwbhBzBhct pwbhBzBhct = new PwbhBzBhct();
        pwbhBzBhct.setSsqy(ssqy);
        List<PwbhBzBhct> pwbhBzBhcts = pwbhBzBhctServiceImpl.selectByAll(pwbhBzBhct);
        for (PwbhBzBhct bzBhct : pwbhBzBhcts) {
            PwbhJlBhctjx pwbhJlBhctjx = new PwbhJlBhctjx(IDUtil.getStrId(), tsid, ssqy, bzBhct.getXb(), null, null, null, czr, new Date(), null);
            pwbhJlBhctjxServiceImpl.insert(pwbhJlBhctjx);
            PwbhJlBhctbh pwbhJlBhctbh = new PwbhJlBhctbh(IDUtil.getStrId(), tsid, ssqy, bzBhct.getXb(), null, null, null, null, null, czr, new Date(), null);
            pwbhJlBhctbhServiceImpl.insert(pwbhJlBhctbh);
            PwbhJlBhcthl pwbhJlBhcthl = new PwbhJlBhcthl(IDUtil.getStrId(), tsid, ssqy, bzBhct.getXb(), null, null, czr, new Date(), null);
            pwbhJlBhcthlServiceImpl.insert(pwbhJlBhcthl);
        }

        PwbhBzDzjc pwbhBzDzjc = new PwbhBzDzjc();
        pwbhBzDzjc.setSsqy(ssqy);
        List<PwbhBzDzjc> pwbhBzDzjcs = pwbhBzDzjcServiceImpl.selectByAll(pwbhBzDzjc);
        for (PwbhBzDzjc bzDzjc : pwbhBzDzjcs) {
            PwbhJlDzjc pwbhJlDzjc = new PwbhJlDzjc(IDUtil.getStrId(), tsid, ssqy, bzDzjc.getJg(), null, null, null, null, null, null, null, null, null, null, null, null, czr, new Date(), null);
            pwbhJlDzjcServiceImpl.insert(pwbhJlDzjc);
        }

        // zzsy 整组实验
        PwbhBzZzsy pwbhBzZzsy = new PwbhBzZzsy();
        pwbhBzZzsy.setSsqy(ssqy);
        List<PwbhBzZzsy> pwbhBzZzsies = pwbhBzZzsyServiceImpl.selectByAll(pwbhBzZzsy);
        for (PwbhBzZzsy bzZzsy : pwbhBzZzsies) {
            PwbhJlZzsy pwbhJlZzsy = new PwbhJlZzsy(IDUtil.getStrId(), tsid, ssqy, bzZzsy.getJyxm(), null, null, null, null, null, czr, new Date(), null);
            pwbhJlZzsyServiceImpl.insert(pwbhJlZzsy);
        }

        PwbhBzSgjc pwbhBzSgjc = new PwbhBzSgjc();
        pwbhBzSgjc.setSsqy(ssqy);
        List<PwbhBzSgjc> pwbhBzSgjcs = pwbhBzSgjcServiceImpl.selectByAll(pwbhBzSgjc);
        for (PwbhBzSgjc bzSgjc : pwbhBzSgjcs) {
            PwbhJlSgjc pwbhJlSgjc = new PwbhJlSgjc(IDUtil.getStrId(), tsid, ssqy, bzSgjc.getNr(), null, czr, new Date(), null);
            pwbhJlSgjcServiceImpl.insert(pwbhJlSgjc);
        }

        // dzdjc 定制检查记录表
        PwbhJlDzdjc pwbhJlDzdjc = new PwbhJlDzdjc(IDUtil.getStrId(), tsid, ssqy, null, null, null, null, null, czr, new Date(), null);
        pwbhJlDzdjcServiceImpl.insert(pwbhJlDzdjc);

        // 备注
        PwbhJbxxBeizhu pwbhJbxxBeizhu = new PwbhJbxxBeizhu(tsid, null, null, null, null, null, null, null, null, null, null, null, null);
        pwbhJbxxBeizhuServiceImpl.insert(pwbhJbxxBeizhu);
    }

    @Override
    public void deletePwbhTsjlByPwbhJbxx(String tsid) {

    }
}
