package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlLpjyMapper;
import com.guodu.pojo.dtu.JlLpjy;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JlLpjyServiceImpl extends BaseServiceImpl<JlLpjy> {

    @Autowired
    private JlLpjyMapper jlLpjyMapper;

    public int deleteByPrimaryKey(String id) {
        return jlLpjyMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlLpjy record) {
        return jlLpjyMapper.insert2(record);
    }

    public int insertSelective(JlLpjy record) {
        return jlLpjyMapper.insertSelective(record);
    }

    public JlLpjy selectByPrimaryKey(String id) {
        return jlLpjyMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlLpjy record) {
        return jlLpjyMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlLpjy record) {
        return jlLpjyMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlLpjy> list) {
        return jlLpjyMapper.updateBatch(list);
    }

    public int batchInsert(List<JlLpjy> list) {
        return jlLpjyMapper.batchInsert(list);
    }

    public List<JlLpjy> selectByAll(JlLpjy jlLpjy) {
        return jlLpjyMapper.selectByAll(jlLpjy);
    }

    public int deleteByTsid(String tsid) {
        return jlLpjyMapper.deleteByTsid(tsid);
    }

    public List<Map<String,String>> selectByJgGroup(String tsid) {
        return jlLpjyMapper.selectByJgGroup(tsid);
    }
}
