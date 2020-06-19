package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JbxxPhoto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface JbxxPhotoMapper extends BaseMapper<JbxxPhoto> {
    List<Map<String,Object>> selectAllByTsid(Map<String,Object> vo);
}
