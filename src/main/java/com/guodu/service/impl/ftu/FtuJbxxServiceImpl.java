package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuJbxxMapper;
import com.guodu.pojo.ftu.FtuJbxx;
import com.guodu.service.impl.BaseServiceImpl;
import com.guodu.service.impl.dtu.ExportDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FtuJbxxServiceImpl extends BaseServiceImpl<FtuJbxx> {

    @Autowired
    private FtuJbxxMapper ftuJbxxMapper;


    /**
     * ftu标准
     **/
    @Autowired
    private ExportDocumentService exportDocumentService;

    public FtuJbxx selectByPrimaryKey(String id) {
        return ftuJbxxMapper.selectByPrimaryKey(id);
    }
}
