package com.guodu.service.impl.ftu;

import com.guodu.pojo.ftu.*;
import com.guodu.service.ftu.*;
import com.guodu.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: FtuTsjlServiceImpl
 * @Description: TODO 生成FTU标准空记录/通过tsid删除记录
 * @Author: 2Uli
 * @Date: 2020/5/12 1:54 下午
 */
@Service
public class FtuTsjlServiceImpl implements FtuTsjlService {

    @Autowired
    private FtuBzCyjdServiceImpl ftuBzCyjdServiceImpl;
    @Autowired
    private FtuBzCyjdcsServiceImpl ftuBzCyjdcsServiceImpl;
    @Autowired
    private FtuBzGjtsServiceImpl ftuBzGjtsServiceImpl;
    @Autowired
    private FtuBzGncsServiceImpl ftuBzGncsServiceImpl;
    @Autowired
    private FtuBzHbdyServiceImpl ftuBzHbdyServiceImpl;
    @Autowired
    private FtuBzLpjyServiceImpl ftuBzLpjyServiceImpl;
    @Autowired
    private FtuBzTyjcServiceImpl ftuBzTyjcServiceImpl;
    @Autowired
    private FtuBzTzybServiceImpl ftuBzTzybServiceImpl;

    @Autowired
    private FtuJlCyjdService ftuJlCyjdServiceImpl;
    @Autowired
    private FtuJlCyjdcsService ftuJlCyjdcsServiceImpl;
    @Autowired
    private FtuJlGjtsService ftuJlGjtsServiceImpl;
    @Autowired
    private FtuJlGncsService ftuJlGncsServiceImpl;
    @Autowired
    private FtuJlHbdyService ftuJlHbdyServiceImpl;
    @Autowired
    private FtuJlKgdzService ftuJlKgdzServiceImpl;
    @Autowired
    private FtuJlLpjyService ftuJlLpjyServiceImpl;
    @Autowired
    private FtuJlTyjcService ftuJlTyjcServiceImpl;
    @Autowired
    private FtuJlTzybService ftuJlTzybServiceImpl;
    @Autowired
    private FtuJlYkcdService ftuJlYkcdServiceImpl;
    @Autowired
    private FtuJlYkkgService ftuJlYkkgServiceImpl;
    @Autowired
    private FtuJlGjdzService ftuJlGjdzServiceImpl;
    @Autowired
    private FtuJbxxBeizhuService ftuJbxxBeizhuServiceImpl;

    @Override
    public void insertFtuTsjl(FtuJbxx ftuJbxx) {
        String tsid = ftuJbxx.getTsid();
        String ssqy = ftuJbxx.getSsqy();
        String czr = ftuJbxx.getCzr();

        // 通过标准插入记录
        FtuBzCyjd ftuBzCyjd = new FtuBzCyjd();
        ftuBzCyjd.setSsqy(ssqy);
        List<FtuBzCyjd> ftuBzCyjds = ftuBzCyjdServiceImpl.selectByAll(ftuBzCyjd);
        for (FtuBzCyjd bzCyjd : ftuBzCyjds) {
            FtuJlCyjd ftuJlCyjd = new FtuJlCyjd(IDUtil.getStrId(), tsid, ssqy, bzCyjd.getClx(), null, null, null, null, null, "1", czr, new Date(), null);
            ftuJlCyjdServiceImpl.insert(ftuJlCyjd);
        }

        FtuBzCyjdcs ftuBzCyjdcs = new FtuBzCyjdcs();
        ftuBzCyjdcs.setSsqy(ssqy);
        List<FtuBzCyjdcs> ftuBzCyjdcss = ftuBzCyjdcsServiceImpl.selectByAll(ftuBzCyjdcs);
        for (FtuBzCyjdcs bzCyjdcss : ftuBzCyjdcss) {
            FtuJlCyjdcs ftuJlCyjdcs = new FtuJlCyjdcs(IDUtil.getStrId(), tsid, ssqy, bzCyjdcss.getClx(), null, null, null, "1", czr, new Date(), null);
            ftuJlCyjdcsServiceImpl.insert(ftuJlCyjdcs);
        }

        FtuBzGjts ftuBzGjts = new FtuBzGjts();
        ftuBzGjts.setSsqy(ssqy);
        List<FtuBzGjts> ftuBzGjtss = ftuBzGjtsServiceImpl.selectByAll(ftuBzGjts);
        for (FtuBzGjts bzGjtss : ftuBzGjtss) {
            FtuJlGjts ftuJlGjts = new FtuJlGjts(IDUtil.getStrId(), tsid, ssqy, bzGjtss.getCsx(), null, null, null, null, null, null, null, czr, new Date(), null);
            ftuJlGjtsServiceImpl.insert(ftuJlGjts);
        }


        FtuBzGncs ftuBzGncs = new FtuBzGncs();
        ftuBzGncs.setSsqy(ssqy);
        List<FtuBzGncs> ftuBzGncss = ftuBzGncsServiceImpl.selectByAll(ftuBzGncs);
        for (FtuBzGncs bzGncss : ftuBzGncss) {
            FtuJlGncs ftuJlGncs = new FtuJlGncs(IDUtil.getStrId(), tsid, ssqy, bzGncss.getCsgn(), bzGncss.getJsbz(), "1", czr, new Date(), null);
            ftuJlGncsServiceImpl.insert(ftuJlGncs);
        }

        FtuBzHbdy ftuBzHbdy = new FtuBzHbdy();
        ftuBzHbdy.setSsqy(ssqy);
        List<FtuBzHbdy> ftuBzHbdys = ftuBzHbdyServiceImpl.selectByAll(ftuBzHbdy);
        for (FtuBzHbdy bzHbdy : ftuBzHbdys) {
            FtuJlHbdy ftuJlHbdy = new FtuJlHbdy(IDUtil.getStrId(), tsid, ssqy, bzHbdy.getJcnr(), "1", czr, new Date(), null);
            ftuJlHbdyServiceImpl.insert(ftuJlHbdy);
        }

        FtuBzLpjy ftuBzLpjy = new FtuBzLpjy();
        ftuBzLpjy.setSsqy(ssqy);
        List<FtuBzLpjy> ftuBzLpjys = ftuBzLpjyServiceImpl.selectByAll(ftuBzLpjy);
        for (FtuBzLpjy bzLpjy : ftuBzLpjys) {
            FtuJlLpjy ftuJlLpjy = new FtuJlLpjy(IDUtil.getStrId(), tsid, ssqy, bzLpjy.getHl(), null, "1", czr, new Date(), null);
            ftuJlLpjyServiceImpl.insert(ftuJlLpjy);
        }

        FtuBzTyjc ftuBzTyjc = new FtuBzTyjc();
        ftuBzTyjc.setSsqy(ssqy);
        List<FtuBzTyjc> ftuBzTyjcs = ftuBzTyjcServiceImpl.selectByAll(ftuBzTyjc);
        for (FtuBzTyjc bzTyjc : ftuBzTyjcs) {
            FtuJlTyjc ftuJlTyjc = new FtuJlTyjc(IDUtil.getStrId(), tsid, ssqy, bzTyjc.getJcxm(), "1", czr, new Date(), null);
            ftuJlTyjcServiceImpl.insert(ftuJlTyjc);
        }

        FtuBzTzyb ftuBzTzyb = new FtuBzTzyb();
        ftuBzTzyb.setSsqy(ssqy);
        List<FtuBzTzyb> ftuBzTzybs = ftuBzTzybServiceImpl.selectByAll(ftuBzTzyb);
        for (FtuBzTzyb bzTzyb : ftuBzTzybs) {
            FtuJlTzyb ftuJlTzyb = new FtuJlTzyb(IDUtil.getStrId(), tsid, ssqy, bzTzyb.getJyxm(), bzTzyb.getGzmn(), "1", "1", "1", "1", czr, new Date(), null);
            ftuJlTzybServiceImpl.insert(ftuJlTzyb);
        }

        FtuJlKgdz ftuJlKgdz = new FtuJlKgdz(IDUtil.getStrId(), tsid, ssqy, null, null, null, null, null, null, null, null, null, czr, new Date(), null);
        ftuJlKgdzServiceImpl.insert(ftuJlKgdz);

        FtuJlYkcd ftuJlYkcd = new FtuJlYkcd(IDUtil.getStrId(), tsid, ssqy, "1", null, "1", null, czr, new Date(), null);
        ftuJlYkcdServiceImpl.insert(ftuJlYkcd);

        FtuJlYkkg ftuJlYkkg = new FtuJlYkkg(IDUtil.getStrId(), tsid, ssqy, "1", null, "1", null, czr, new Date(), null);
        ftuJlYkkgServiceImpl.insert(ftuJlYkkg);

        FtuJlGjdz ftuJlGjdz = new FtuJlGjdz(tsid, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        ftuJlGjdzServiceImpl.insert(ftuJlGjdz);

        FtuJbxxBeizhu ftuBeizhu = new FtuJbxxBeizhu(tsid, null, null, null, null, null, null, null, null, null, null, null);
        ftuJbxxBeizhuServiceImpl.insert(ftuBeizhu);
    }

    @Override
    public void deleteFtuTsjl(String tsid) {
        // 通过tsid删除记录
        ftuJlCyjdServiceImpl.deleteByTsid(tsid);
        ftuJlCyjdcsServiceImpl.deleteByTsid(tsid);
        ftuJlGjdzServiceImpl.deleteByTsid(tsid);
        ftuJlGjtsServiceImpl.deleteByTsid(tsid);
        ftuJlGncsServiceImpl.deleteByTsid(tsid);
        ftuJlHbdyServiceImpl.deleteByTsid(tsid);
        ftuJlKgdzServiceImpl.deleteByTsid(tsid);
        ftuJlLpjyServiceImpl.deleteByTsid(tsid);
        ftuJlTyjcServiceImpl.deleteByTsid(tsid);
        ftuJlTzybServiceImpl.deleteByTsid(tsid);
        ftuJlYkcdServiceImpl.deleteByTsid(tsid);
        ftuJlYkkgServiceImpl.deleteByTsid(tsid);
        ftuJbxxBeizhuServiceImpl.deleteByPrimaryKey(tsid);
    }
}
