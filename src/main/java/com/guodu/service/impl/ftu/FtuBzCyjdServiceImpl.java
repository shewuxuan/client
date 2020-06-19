package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuBzCyjdMapper;
import com.guodu.pojo.ftu.FtuBzCyjd;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtuBzCyjdServiceImpl extends BaseServiceImpl<FtuBzCyjd> {

    @Autowired
    private FtuBzCyjdMapper ftuBzCyjdMapper;

    public int insert2(FtuBzCyjd record) {return ftuBzCyjdMapper.insert2(record);}

    public List<FtuBzCyjd> selectByAll(FtuBzCyjd record) {
        return ftuBzCyjdMapper.selectByAll(record);
    }
}
