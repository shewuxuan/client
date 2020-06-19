package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzYktsMapper;
import com.guodu.pojo.dtu.BzYkts;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzYktsServiceImpl extends BaseServiceImpl<BzYkts> {
    @Autowired
    private BzYktsMapper bzYktsMapper;

    public int deleteByPrimaryKey(String id) {
        return bzYktsMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzYkts record) {
        return bzYktsMapper.insert2(record);
    }

    public int insertSelective(BzYkts record) {
        return bzYktsMapper.insertSelective(record);
    }

    public BzYkts selectByPrimaryKey(String id) {
        return bzYktsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzYkts record) {
        return bzYktsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzYkts record) {
        return bzYktsMapper.updateByPrimaryKey(record);
    }

    public List<BzYkts> selectByAll(BzYkts bzYkts) {
        return bzYktsMapper.selectByAll(bzYkts);
    }

    public int updateBatch(List<BzYkts> list) {
        return bzYktsMapper.updateBatch(list);
    }

    public int batchInsert(List<BzYkts> list) {
        return bzYktsMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzYkts> bzYktss = bzYktsMapper.selectByPage((BzYkts) map.get("bzYkts"));

        PageInfo<BzYkts> pageInfo = new PageInfo<>(bzYktss);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}
