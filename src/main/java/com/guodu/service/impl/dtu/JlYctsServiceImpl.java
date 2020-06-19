package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlYctsMapper;
import com.guodu.pojo.dtu.JlYcts;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlYctsServiceImpl extends BaseServiceImpl<JlYcts> {

    @Autowired
    private JlYctsMapper jlYctsMapper;

    public int deleteByPrimaryKey(String id) {
        return jlYctsMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlYcts record) {
        return jlYctsMapper.insert2(record);
    }

    public int insertSelective(JlYcts record) {
        return jlYctsMapper.insertSelective(record);
    }

    public JlYcts selectByPrimaryKey(String id) {
        return jlYctsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlYcts record) {
        return jlYctsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlYcts record) {
        return jlYctsMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlYcts> list) {
        return jlYctsMapper.updateBatch(list);
    }

    public int batchInsert(List<JlYcts> list) {
        return jlYctsMapper.batchInsert(list);
    }

    public List<JlYcts> selectByAll(JlYcts jlYcts) {
        return jlYctsMapper.selectByAll(jlYcts);
    }

    public int deleteByTsid(String tsid) {
        return jlYctsMapper.deleteByTsid(tsid);
    }
}
