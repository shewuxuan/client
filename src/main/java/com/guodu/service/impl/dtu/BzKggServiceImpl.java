package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzKggMapper;
import com.guodu.pojo.dtu.BzKgg;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzKggServiceImpl extends BaseServiceImpl<BzKgg> {

    @Autowired
    private BzKggMapper bzKggMapper;

    public int deleteByPrimaryKey(String id) {
        return bzKggMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzKgg record) {
        return bzKggMapper.insert2(record);
    }

    public int insertSelective(BzKgg record) {
        return bzKggMapper.insertSelective(record);
    }

    public BzKgg selectByPrimaryKey(String id) {
        return bzKggMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzKgg record) {
        return bzKggMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzKgg record) {
        return bzKggMapper.updateByPrimaryKey(record);
    }

    public List<BzKgg> selectByAll(BzKgg bzKgg) {
        return bzKggMapper.selectByAll(bzKgg);
    }

    public int updateBatch(List<BzKgg> list) {
        return bzKggMapper.updateBatch(list);
    }

    public int batchInsert(List<BzKgg> list) {
        return bzKggMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzKgg> bzKggs = bzKggMapper.selectByPage((BzKgg) map.get("bzKgg"));

        PageInfo<BzKgg> pageInfo = new PageInfo<>(bzKggs);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}
