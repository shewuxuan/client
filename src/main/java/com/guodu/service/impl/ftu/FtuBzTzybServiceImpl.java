package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuBzTzybMapper;
import com.guodu.pojo.ftu.FtuBzTzyb;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtuBzTzybServiceImpl extends BaseServiceImpl<FtuBzTzyb> {

    @Autowired
    private FtuBzTzybMapper ftuBzTzybMapper;

    public int insert2(FtuBzTzyb record) {return ftuBzTzybMapper.insert2(record);}

    public List<FtuBzTzyb> selectByAll(FtuBzTzyb record) {
        return ftuBzTzybMapper.selectByAll(record);
    }

}
