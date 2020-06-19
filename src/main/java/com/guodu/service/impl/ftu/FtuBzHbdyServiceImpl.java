package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuBzHbdyMapper;
import com.guodu.pojo.ftu.FtuBzHbdy;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtuBzHbdyServiceImpl extends BaseServiceImpl<FtuBzHbdy> {

    @Autowired
    private FtuBzHbdyMapper ftuBzHbdyMapper;

    public int insert2(FtuBzHbdy record) {return ftuBzHbdyMapper.insert2(record);}

    public List<FtuBzHbdy> selectByAll(FtuBzHbdy record) {
        return ftuBzHbdyMapper.selectByAll(record);
    }

}
