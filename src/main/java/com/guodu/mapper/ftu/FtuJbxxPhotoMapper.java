package com.guodu.mapper.ftu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.ftu.FtuJbxxPhoto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface FtuJbxxPhotoMapper extends BaseMapper<FtuJbxxPhoto> {
    List<Map<String,Object>> selectAllByTsid(Map<String, Object> vo);
}
