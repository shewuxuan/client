package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzJydzMapper;
import com.guodu.pojo.dtu.BzJydz;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzJydzServiceImpl extends BaseServiceImpl<BzJydz> {

    @Autowired
    private BzJydzMapper bzJydzMapper;

    public int deleteByPrimaryKey(String id) {
        return bzJydzMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzJydz record) {
        return bzJydzMapper.insert2(record);
    }

    public int insertSelective(BzJydz record) {
        return bzJydzMapper.insertSelective(record);
    }

    public BzJydz selectByPrimaryKey(String id) {
        return bzJydzMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzJydz record) {
        return bzJydzMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzJydz record) {
        return bzJydzMapper.updateByPrimaryKey(record);
    }

    public List<BzJydz> selectByAll(BzJydz bzJydz) {
        return bzJydzMapper.selectByAll(bzJydz);
    }

    public int updateBatch(List<BzJydz> list) {
        return bzJydzMapper.updateBatch(list);
    }

    public int batchInsert(List<BzJydz> list) {
        return bzJydzMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzJydz> bzJydzs = bzJydzMapper.selectByPage((BzJydz) map.get("bzJydz"));

        PageInfo<BzJydz> pageInfo = new PageInfo<>(bzJydzs);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}
