package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuBzLpjyMapper;
import com.guodu.pojo.ftu.FtuBzLpjy;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtuBzLpjyServiceImpl extends BaseServiceImpl<FtuBzLpjy> {

    @Autowired
    private FtuBzLpjyMapper ftuBzLpjyMapper;

    public int insert2(FtuBzLpjy record) {return ftuBzLpjyMapper.insert2(record);}

    public List<FtuBzLpjy> selectByAll(FtuBzLpjy record) {
        return ftuBzLpjyMapper.selectByAll(record);
    }

}
