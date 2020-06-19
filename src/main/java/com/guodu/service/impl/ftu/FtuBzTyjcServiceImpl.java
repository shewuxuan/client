package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuBzTyjcMapper;
import com.guodu.pojo.ftu.FtuBzTyjc;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtuBzTyjcServiceImpl extends BaseServiceImpl<FtuBzTyjc> {

    @Autowired
    private FtuBzTyjcMapper ftuBzTyjcMapper;

    public int insert2(FtuBzTyjc record) {return ftuBzTyjcMapper.insert2(record);}

    public List<FtuBzTyjc> selectByAll(FtuBzTyjc record) {
        return ftuBzTyjcMapper.selectByAll(record);
    }

}
