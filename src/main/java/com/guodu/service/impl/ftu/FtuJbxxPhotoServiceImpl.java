package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuJbxxPhotoMapper;
import com.guodu.pojo.ftu.FtuJbxxPhoto;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FtuJbxxPhotoServiceImpl extends BaseServiceImpl<FtuJbxxPhoto> {
	@Autowired
	FtuJbxxPhotoMapper FtuJbxxPhotoMapper;

	public List<Map<String,Object>> selectAllByTsid(Map<String, Object> vo){
		return FtuJbxxPhotoMapper.selectAllByTsid(vo);
	}
}
