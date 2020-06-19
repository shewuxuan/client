package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlDtuMapper;
import com.guodu.pojo.dtu.JlDtu;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlDtuServiceImpl extends BaseServiceImpl<JlDtu> {

    @Autowired
    private JlDtuMapper jlDtuMapper;

    public int deleteByPrimaryKey(String id) {
        return jlDtuMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlDtu record) {
        return jlDtuMapper.insert2(record);
    }

    public int insertSelective(JlDtu record) {
        return jlDtuMapper.insertSelective(record);
    }

    public JlDtu selectByPrimaryKey(String id) {
        return jlDtuMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlDtu record) {
        return jlDtuMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlDtu record) {
        return jlDtuMapper.updateByPrimaryKey(record);
    }

    public List<JlDtu> selectByAll(JlDtu jlDtu) {
        return jlDtuMapper.selectByAll(jlDtu);
    }

    public int updateBatch(List<JlDtu> list) {
        return jlDtuMapper.updateBatch(list);
    }

    public int batchInsert(List<JlDtu> list) {
        return jlDtuMapper.batchInsert(list);
    }

    public int deleteByTsid(String tsid) {
        return jlDtuMapper.deleteByTsid(tsid);
    }

}

