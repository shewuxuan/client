package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuBzCyjdcsMapper;
import com.guodu.pojo.ftu.FtuBzCyjdcs;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtuBzCyjdcsServiceImpl extends BaseServiceImpl<FtuBzCyjdcs> {

    @Autowired
    private FtuBzCyjdcsMapper ftuBzCyjdcsMapper;

    public int insert2(FtuBzCyjdcs record) {return ftuBzCyjdcsMapper.insert2(record);}

    public List<FtuBzCyjdcs> selectByAll(FtuBzCyjdcs record) {
        return ftuBzCyjdcsMapper.selectByAll(record);
    }

}
