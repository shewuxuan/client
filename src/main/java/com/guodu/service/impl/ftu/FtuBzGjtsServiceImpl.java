package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuBzGjtsMapper;
import com.guodu.pojo.ftu.FtuBzGjts;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtuBzGjtsServiceImpl extends BaseServiceImpl<FtuBzGjts> {

    @Autowired
    private FtuBzGjtsMapper ftuBzGjtsMapper;

    public int insert2(FtuBzGjts record) {return ftuBzGjtsMapper.insert2(record);}

    public List<FtuBzGjts> selectByAll(FtuBzGjts record) {
        return ftuBzGjtsMapper.selectByAll(record);
    }
}
