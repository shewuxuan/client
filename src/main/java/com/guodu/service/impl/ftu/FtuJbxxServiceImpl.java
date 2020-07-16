package com.guodu.service.impl.ftu;

import com.guodu.mapper.ftu.FtuJbxxMapper;
import com.guodu.pojo.dtu.Jbxx;
import com.guodu.pojo.dtu.JbxxBeizhu;
import com.guodu.pojo.dtu.JbxxGjdz;
import com.guodu.pojo.dtu.JbxxPhoto;
import com.guodu.pojo.ftu.FtuJbxx;
import com.guodu.pojo.ftu.FtuJbxxPhoto;
import com.guodu.service.ftu.FtuJbxxBeizhuService;
import com.guodu.service.impl.BaseServiceImpl;
import com.guodu.service.impl.dtu.ExportDocumentService;
import com.guodu.util.FileHandleUtils;
import com.guodu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FtuJbxxServiceImpl extends BaseServiceImpl<FtuJbxx> {

    @Autowired
    private FtuJbxxMapper ftuJbxxMapper;


    /**
     * ftu标准
     **/
    @Autowired
    private ExportDocumentService exportDocumentService;
    @Autowired
    FtuTsjlServiceImpl ftuTsjlServiceImpl;
    @Autowired
    FtuJbxxPhotoServiceImpl ftuJbxxPhotoServiceImpl;
    @Autowired
    FtuJbxxBeizhuService ftuJbxxBeizhuServiceImpl;

    public FtuJbxx selectByPrimaryKey(String id) {
        return ftuJbxxMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void txAddJbxxAndPhoto(HttpServletRequest request,FtuJbxx ftuJbxx, String imgSavePath) throws Exception {
        //生成所有记录
        ftuTsjlServiceImpl.insertFtuTsjl(ftuJbxx);
        this.add(ftuJbxx);
        //上传文件
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        String[] imgNames = ftuJbxx.getImgName();
        for (int i=0;i<files.size();i++) {
            file = files.get(i);
            String pid = StringUtils.createDateRandomString(new Date());
            FileHandleUtils.singlefileUpload(request,file,imgSavePath,pid);
            String fileName = file.getOriginalFilename();
            FtuJbxxPhoto photo = new FtuJbxxPhoto();
            photo.setPid(pid);
            photo.setTsid(ftuJbxx.getTsid());
            if(imgNames.length<1 || imgNames[i]==null) photo.setPName("");
            else photo.setPName(imgNames[i]);
            photo.setPPath(imgSavePath + pid + fileName);
            this.ftuJbxxPhotoServiceImpl.add(photo);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void txUpdateJbxxAndPhoto(HttpServletRequest request,FtuJbxx ftuJbxx, String imgSavePath) throws Exception {
        //1.删除移除的照片
        String flagArrayStr = ftuJbxx.getFlagArrayStr();
        if (!flagArrayStr.equals("")) {
            String[] fileIds = flagArrayStr.split(",");
            FtuJbxxPhoto jbxxPhoto = null;
            for (String pid : fileIds) {
                jbxxPhoto = this.ftuJbxxPhotoServiceImpl.selectById(pid);
                FileHandleUtils.deleteFile(jbxxPhoto.getPPath());
                this.ftuJbxxPhotoServiceImpl.delete(pid);
            }
        }
        //2.更改未移除，但修改名称的照片
        List<FtuJbxxPhoto> photos = ftuJbxx.getPhoto();
        if(photos!=null && photos.size()>0){
            for(FtuJbxxPhoto photo:photos){
                photo.setPPath(null);
                photo.setTsid(null);
                ftuJbxxPhotoServiceImpl.edit(photo);
            }
        }

        //3.修改基本信息，和添加新增的照片
        this.ftuJbxxMapper.update(ftuJbxx);
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        String[] imgNames = ftuJbxx.getImgName();
        for (int i=0;i<files.size();i++) {
            file = files.get(i);
            String pid = StringUtils.createDateRandomString(new Date());
            FileHandleUtils.singlefileUpload(request,file,imgSavePath,pid);
            String fileName = file.getOriginalFilename();
            FtuJbxxPhoto photo = new FtuJbxxPhoto();
            photo.setPid(pid);
            photo.setTsid(ftuJbxx.getTsid());
            if(imgNames.length<1 || imgNames[i]==null) photo.setPName("");
            else photo.setPName(imgNames[i]);
            photo.setPPath(imgSavePath + pid + fileName);
            this.ftuJbxxPhotoServiceImpl.add(photo);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void txDeleteJbxxAndPhoto(String tsid) throws Exception {
        Map vo = new HashMap();
        vo.put("tsid",tsid);
        List<Map<String,Object>> photos = ftuJbxxPhotoServiceImpl.selectAllByTsid(vo);
        for(Map<String,Object> photo:photos){
            FileHandleUtils.deleteFile(photo.get("P_PATH").toString());//删除磁盘文件
            ftuJbxxPhotoServiceImpl.delete(photo.get("PID").toString());//删除文件记录
        }
        ftuJbxxBeizhuServiceImpl.deleteByPrimaryKey(tsid);//删除备注表
        ftuTsjlServiceImpl.deleteFtuTsjl(tsid);//删除所有调试记录
        this.delete(tsid);
    }
}
