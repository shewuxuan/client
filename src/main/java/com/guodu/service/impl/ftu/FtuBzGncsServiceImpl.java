package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuBzGncsMapper;
import com.guodu.pojo.ftu.FtuBzGncs;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtuBzGncsServiceImpl extends BaseServiceImpl<FtuBzGncs> {

    @Autowired
    private FtuBzGncsMapper ftuBzGncsMapper;

    public int insert2(FtuBzGncs record) {return ftuBzGncsMapper.insert2(record);}

    public List<FtuBzGncs> selectByAll(FtuBzGncs record) {
        return ftuBzGncsMapper.selectByAll(record);
    }

}
