package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlJydzMapper;
import com.guodu.pojo.dtu.JlJydz;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlJydzServiceImpl extends BaseServiceImpl<JlJydz> {

    @Autowired
    private JlJydzMapper jlJydzMapper;

    public int deleteByPrimaryKey(String id) {
        return jlJydzMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlJydz record) {
        return jlJydzMapper.insert2(record);
    }

    public int insertSelective(JlJydz record) {
        return jlJydzMapper.insertSelective(record);
    }

    public JlJydz selectByPrimaryKey(String id) {
        return jlJydzMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlJydz record) {
        return jlJydzMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlJydz record) {
        return jlJydzMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlJydz> list) {
        return jlJydzMapper.updateBatch(list);
    }

    public int batchInsert(List<JlJydz> list) {
        return jlJydzMapper.batchInsert(list);
    }

    public List<JlJydz> selectByAll(JlJydz jlJydz) {
        return jlJydzMapper.selectByAll(jlJydz);
    }

    public int deleteByTsid(String tsid) {
        return jlJydzMapper.deleteByTsid(tsid);
    }
}
