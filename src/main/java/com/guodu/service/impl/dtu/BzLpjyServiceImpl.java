package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzLpjyMapper;
import com.guodu.pojo.dtu.BzLpjy;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzLpjyServiceImpl extends BaseServiceImpl<BzLpjy> {

    @Autowired
    private BzLpjyMapper bzLpjyMapper;

    public int deleteByPrimaryKey(String id) {
        return bzLpjyMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzLpjy record) {
        return bzLpjyMapper.insert2(record);
    }

    public int insertSelective(BzLpjy record) {
        return bzLpjyMapper.insertSelective(record);
    }

    public BzLpjy selectByPrimaryKey(String id) {
        return bzLpjyMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzLpjy record) {
        return bzLpjyMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzLpjy record) {
        return bzLpjyMapper.updateByPrimaryKey(record);
    }

    public List<BzLpjy> selectByAll(BzLpjy bzLpjy) {
        return bzLpjyMapper.selectByAll(bzLpjy);
    }

    public int updateBatch(List<BzLpjy> list) {
        return bzLpjyMapper.updateBatch(list);
    }

    public int batchInsert(List<BzLpjy> list) {
        return bzLpjyMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzLpjy> bzLpjys = bzLpjyMapper.selectByPage((BzLpjy) map.get("bzLpjy"));
        PageInfo<BzLpjy> pageInfo = new PageInfo<>(bzLpjys);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}
