package com.guodu.service.impl.pwbh;

import com.guodu.mapper.pwbh.PwbhJbxxMapper;
import com.guodu.pojo.pwbh.PwbhJbxx;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PwbhJbxxServiceImpl extends BaseServiceImpl<PwbhJbxx> {
    /**单一实现类直接继承BaseServiceImpl，调用父类共有方法，不需要单独写接口**/
    @Autowired
    private PwbhJbxxMapper pwbhJbxxMapper;

    public PwbhJbxx selectByPrimaryKey(String tsid) {
        return pwbhJbxxMapper.selectByPrimaryKey(tsid);
    }

}
