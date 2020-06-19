package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlYktsMapper;
import com.guodu.pojo.dtu.JlYkts;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlYktsServiceImpl extends BaseServiceImpl<JlYkts> {
    @Autowired
    private JlYktsMapper jlYktsMapper;

    public int deleteByPrimaryKey(String id) {
        return jlYktsMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlYkts record) {
        return jlYktsMapper.insert2(record);
    }

    public int insertSelective(JlYkts record) {
        return jlYktsMapper.insertSelective(record);
    }

    public JlYkts selectByPrimaryKey(String id) {
        return jlYktsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlYkts record) {
        return jlYktsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlYkts record) {
        return jlYktsMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlYkts> list) {
        return jlYktsMapper.updateBatch(list);
    }

    public int batchInsert(List<JlYkts> list) {
        return jlYktsMapper.batchInsert(list);
    }

    public List<JlYkts> selectByAll(JlYkts jlYkts) {
        return jlYktsMapper.selectByAll(jlYkts);
    }

    public int deleteByTsid(String tsid) {
        return jlYktsMapper.deleteByTsid(tsid);
    }
}
