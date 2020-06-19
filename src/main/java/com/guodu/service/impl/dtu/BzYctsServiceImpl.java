package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzYctsMapper;
import com.guodu.pojo.dtu.BzYcts;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzYctsServiceImpl extends BaseServiceImpl<BzYcts> {

    @Autowired
    private BzYctsMapper bzYctsMapper;

    public int deleteByPrimaryKey(String id) {
        return bzYctsMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzYcts record) {
        return bzYctsMapper.insert2(record);
    }

    public int insertSelective(BzYcts record) {
        return bzYctsMapper.insertSelective(record);
    }

    public BzYcts selectByPrimaryKey(String id) {
        return bzYctsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzYcts record) {
        return bzYctsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzYcts record) {
        return bzYctsMapper.updateByPrimaryKey(record);
    }

    public List<BzYcts> selectByAll(BzYcts bzYcts) {
        return bzYctsMapper.selectByAll(bzYcts);
    }

    public int updateBatch(List<BzYcts> list) {
        return bzYctsMapper.updateBatch(list);
    }

    public int batchInsert(List<BzYcts> list) {
        return bzYctsMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzYcts> bzYctss = bzYctsMapper.selectByPage((BzYcts) map.get("bzYcts"));
        PageInfo<BzYcts> pageInfo = new PageInfo<>(bzYctss);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}
