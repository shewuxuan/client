package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzHldzMapper;
import com.guodu.pojo.dtu.BzHldz;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzHldzServiceImpl extends BaseServiceImpl<BzHldz> {

    @Autowired
    private BzHldzMapper bzHldzMapper;

    public int deleteByPrimaryKey(String id) {
        return bzHldzMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzHldz record) {
        return bzHldzMapper.insert2(record);
    }

    public int insertSelective(BzHldz record) {
        return bzHldzMapper.insertSelective(record);
    }

    public BzHldz selectByPrimaryKey(String id) {
        return bzHldzMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzHldz record) {
        return bzHldzMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzHldz record) {
        return bzHldzMapper.updateByPrimaryKey(record);
    }

    public List<BzHldz> selectByAll(BzHldz bzHldz) {
        return bzHldzMapper.selectByAll(bzHldz);
    }

    public int updateBatch(List<BzHldz> list) {
        return bzHldzMapper.updateBatch(list);
    }

    public int batchInsert(List<BzHldz> list) {
        return bzHldzMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzHldz> bzHldzs = bzHldzMapper.selectByPage((BzHldz) map.get("bzHldz"));
        PageInfo<BzHldz> pageInfo = new PageInfo<>(bzHldzs);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}
