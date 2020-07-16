package com.guodu.controller.ftu;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.guodu.pojo.dtu.Jbxx;
import com.guodu.pojo.dtu.JbxxPhoto;
import com.guodu.pojo.ftu.FtuJbxxPhoto;
import com.guodu.pojo.sys.Auth;
import com.guodu.pojo.ftu.FtuJbxx;
import com.guodu.service.impl.ftu.FtuJbxxPhotoServiceImpl;
import com.guodu.service.impl.ftu.FtuJbxxServiceImpl;
import com.guodu.util.FileHandleUtils;
import com.guodu.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ftuJbxx")
public class FtuJbxxAction {
	private static Logger log = Logger.getLogger(FtuJbxxAction.class);
	@Autowired
	FtuJbxxServiceImpl ftuJbxxServiceImpl;
	@Value("${imgSavePath}")
	String imgSavePath;
	@Autowired
	FtuJbxxPhotoServiceImpl ftuJbxxPhotoServiceImpl;

	@RequestMapping(value = {"toFtuJbxxView.action"}, produces = { "application/json;charset=UTF-8" })
	public ModelAndView toFtuJbxxView(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("ftuJbxx/ftuJbxxList");
		//view.addObject("funcMap",Auth.getAuth(request).getRoleFunc());
		return view;
	}

	@RequestMapping(value = {"selectListFtuJbxx.action"}, produces = { "application/json;charset=UTF-8" },method=RequestMethod.POST)
	public Object selectListFtuJbxx(@RequestParam Map<String, Object> form,HttpServletRequest request) {
		Map res = new HashMap<String , Object>();
		try{
			String ssqy = Auth.getAuth(request).getSsqy();
			form.put("ssqy",ssqy);
			res = this.ftuJbxxServiceImpl.selectPage(form);
		}catch(Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(res);

	}

	@RequestMapping(value = {"toAddFtuJbxxView.action"}, produces = { "application/json;charset=UTF-8" })
	public ModelAndView toAddFtuJbxxView(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("ftuJbxx/addFtuJbxx");
		return view;
	}

	@RequestMapping(value = {"toEditFtuJbxxView.action"}, produces = { "application/json;charset=UTF-8" })
	public ModelAndView toEditFtuJbxxView(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("ftuJbxx/editFtuJbxx");
		String tsid = request.getParameter("tsid");
		Map<String,Object> vo = new HashMap<String,Object>();
		vo.put("tsid", tsid);
		FtuJbxx ftuJbxx = this.ftuJbxxServiceImpl.selectList(vo).get(0);
		List<FtuJbxxPhoto> jbxxPhoto = ftuJbxxPhotoServiceImpl.selectList(vo);
		view.addObject("jbxxPhoto", jbxxPhoto);
		view.addObject("ftuJbxx",ftuJbxx);
		return view;
	}

	@RequestMapping(value = {"addFtuJbxx.action"}, produces = { "application/json;charset=UTF-8" },method=RequestMethod.POST)
	public Object addFtuJbxx(FtuJbxx ftuJbxx,HttpServletRequest request) {
		Map<String , Object> res = new HashMap<String , Object>();
		res.put("code", "0");
		res.put("message", "添加成功");
		try {
			Date createDate = new Date();
			String ssqy = Auth.getAuth(request).getSsqy();
			String tsid = StringUtils.createDateRandomString(createDate);
			if(ftuJbxx != null) {
				ftuJbxx.setTsid(ssqy+tsid);
				ftuJbxx.setSsqy(Auth.getAuth(request).getSsqy());
				ftuJbxx.setCzr(Auth.getAuth(request).getLoginname());
				ftuJbxx.setCzsj(createDate);
				ftuJbxx.setUpload("0");
			}
			this.ftuJbxxServiceImpl.txAddJbxxAndPhoto(request,ftuJbxx,imgSavePath);
		} catch (Exception e) {
			res.put("code", "-1");
			res.put("message", "操作失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(res);
	}

	@RequestMapping(value = {"updateFtuJbxx.action"}, produces = {"application/json;charset=UTF-8"},method=RequestMethod.POST)
	public Object updateFtuJbxx(FtuJbxx ftuJbxx, HttpServletRequest request) {
		Map<String , Object> res = new HashMap<String , Object>();
		res.put("code", "0");
		res.put("message", "修改成功");
		try{
			ftuJbxx.setSsqy(Auth.getAuth(request).getSsqy());
			ftuJbxx.setCzr(Auth.getAuth(request).getLoginname());
			ftuJbxx.setCzsj(new Date());
			this.ftuJbxxServiceImpl.txUpdateJbxxAndPhoto(request,ftuJbxx,imgSavePath);
		}catch(Exception e){
			res.put("code", "-1");
			res.put("message", "修改失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(res);
	}

	@RequestMapping(value = {"delFtuJbxx.action"}, produces = { "application/json;charset=UTF-8" },method=RequestMethod.POST)
	public Object delFtuJbxx(@RequestParam Map<String, Object> form) {
		Map<String , Object> res = new HashMap<String , Object>();
		res.put("code", "0");
		res.put("message", "删除成功");
		String tsid = (String)form.get("tsid");
		try{
			this.ftuJbxxServiceImpl.txDeleteJbxxAndPhoto(tsid);
		}catch(Exception e){
			res.put("code", "-1");
			res.put("message", "删除失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(res);
	}


	/***
	 * 调试信息导出
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = {"exportToWordByFtuJbxx.action"}, produces = { "application/json;charset=UTF-8" })
	public void exportToWordByFtuJbxx(HttpServletRequest request, HttpServletResponse response) {
		String tsid = request.getParameter("tsid");
		XWPFDocument doc = null;
		OutputStream os = null;
		try {
			//doc = ftuJbxxServiceImpl.exportToWordByWord(Auth.getAuth(request).getSsqy(),tsid);
			os = response.getOutputStream();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/msword");
			response.setHeader("Content-disposition","attachment;filename=Jbxx_"+System.currentTimeMillis()+".docx");//filename为导出的word的名字
			doc.write(os);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("导出失败，请重新导出！");
		}finally {
			try {
				if(os!=null){
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@GetMapping("selectByPrimaryKey/{id}")
	public String selectByPrimaryKey(@PathVariable String id) {
		FtuJbxx ftuJbxx = ftuJbxxServiceImpl.selectByPrimaryKey(id);
		return JSONUtil.toJsonStr(ftuJbxx);
	}

	/***
	 * 调试信息照片预览
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getFtuJbxxPhotoView.action")
	public void getJbxxPhotoView(HttpServletRequest request, HttpServletResponse response) {
		FtuJbxxPhoto ftuJbxxPhoto = ftuJbxxPhotoServiceImpl.selectById(request.getParameter("pid"));
		FileHandleUtils.previewImage(request,response,ftuJbxxPhoto.getPPath());
	}
}