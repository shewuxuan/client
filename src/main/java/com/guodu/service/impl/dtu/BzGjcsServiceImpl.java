package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzGjcsMapper;
import com.guodu.pojo.dtu.BzGjcs;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzGjcsServiceImpl extends BaseServiceImpl<BzGjcs> {

    @Autowired
    private BzGjcsMapper bzGjcsMapper;

    public int deleteByPrimaryKey(String id) {
        return bzGjcsMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzGjcs record) {
        return bzGjcsMapper.insert2(record);
    }

    public int insertSelective(BzGjcs record) {
        return bzGjcsMapper.insertSelective(record);
    }

    public BzGjcs selectByPrimaryKey(String id) {
        return bzGjcsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzGjcs record) {
        return bzGjcsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzGjcs record) {
        return bzGjcsMapper.updateByPrimaryKey(record);
    }

    public List<BzGjcs> selectByAll(BzGjcs bzGjcs) {
        return bzGjcsMapper.selectByAll(bzGjcs);
    }

    public int updateBatch(List<BzGjcs> list) {
        return bzGjcsMapper.updateBatch(list);
    }

    public int batchInsert(List<BzGjcs> list) {
        return bzGjcsMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzGjcs> bzGjcss = bzGjcsMapper.selectByPage((BzGjcs) map.get("bzGjcs"));

        PageInfo<BzGjcs> pageInfo = new PageInfo<>(bzGjcss);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}
