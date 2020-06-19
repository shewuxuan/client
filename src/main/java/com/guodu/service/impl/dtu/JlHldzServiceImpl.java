package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlHldzMapper;
import com.guodu.pojo.dtu.JlHldz;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JlHldzServiceImpl extends BaseServiceImpl<JlHldz> {

    @Autowired
    private JlHldzMapper jlHldzMapper;

    public int deleteByPrimaryKey(String id) {
        return jlHldzMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlHldz record) {
        return jlHldzMapper.insert2(record);
    }

    public int insertSelective(JlHldz record) {
        return jlHldzMapper.insertSelective(record);
    }

    public JlHldz selectByPrimaryKey(String id) {
        return jlHldzMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlHldz record) {
        return jlHldzMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlHldz record) {
        return jlHldzMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlHldz> list) {
        return jlHldzMapper.updateBatch(list);
    }

    public int batchInsert(List<JlHldz> list) {
        return jlHldzMapper.batchInsert(list);
    }

    public List<JlHldz> selectByAll(JlHldz jlHldz) {
        return jlHldzMapper.selectByAll(jlHldz);
    }

    public int deleteByTsid(String tsid) {
        return jlHldzMapper.deleteByTsid(tsid);
    }

    public List<Map<String,String>> selectByJgGroup(String tsid) {
        return jlHldzMapper.selectByJgGroup(tsid);
    }
}
