package com.guodu.controller.dtu;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.guodu.pojo.dtu.Jbxx;
import com.guodu.pojo.dtu.JbxxPhoto;
import com.guodu.pojo.sys.Auth;
import com.guodu.pojo.sys.SysDb;
import com.guodu.pojo.sys.SysSccj;
import com.guodu.service.impl.dtu.JbxxPhotoServiceImpl;
import com.guodu.service.impl.dtu.JbxxServiceImpl;
import com.guodu.service.impl.sys.SysDbServiceImpl;
import com.guodu.service.impl.sys.SysSccjServiceImpl;
import com.guodu.util.F;
import com.guodu.util.FileHandleUtils;
import com.guodu.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@RestController
@RequestMapping("jbxx")
public class JbxxAction {
	private static Logger log = Logger.getLogger(JbxxAction.class);
	@Autowired
	JbxxServiceImpl jbxxServiceImpl;
	@Autowired
	SysDbServiceImpl sysDbServiceImpl;
	@Value("${imgSavePath}")
	String imgSavePath;
	@Autowired
	JbxxPhotoServiceImpl jbxxPhotoServiceImpl;
	@Autowired
	SysSccjServiceImpl sysSccjServiceImpl;

	@RequestMapping(value = {"toJbxxView.action",  "/" }, produces = { "application/json;charset=UTF-8" })
	public ModelAndView toJbxxView(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("jbxx/jbxxList");
		//view.addObject("funcMap",Auth.getAuth(request).getRoleFunc());
		return view;
	}

	@RequestMapping(value = {"selectListJbxx.action",  "/" }, produces = { "application/json;charset=UTF-8" },method=RequestMethod.POST)
	public Object selectListJbxx(@RequestParam Map<String, Object> form,HttpServletRequest request) {
		Map res = new HashMap<String , Object>();
		try{
			String ssqy = Auth.getAuth(request).getSsqy();
			form.put("ssqy",ssqy);
			res = this.jbxxServiceImpl.selectPage(form);
		}catch(Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(res);

	}

	@RequestMapping(value = {"toSeeJbxxView.action",  "/" }, produces = { "application/json;charset=UTF-8" })
	public ModelAndView toSeeJbxxView(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("jbxx/seeJbxx");
		String tsid = request.getParameter("tsid");
		Jbxx jbxx = this.jbxxServiceImpl.selectById(tsid);
		view.addObject("jbxx", jbxx);

        Map<String, Object> vo = new HashMap<>();
        vo.put("tsid", tsid);
        List<JbxxPhoto> files = jbxxPhotoServiceImpl.selectList(vo);
        view.addObject("files",files);
		return view;
	}

	@RequestMapping(value = {"toAddJbxxView.action",  "/" }, produces = { "application/json;charset=UTF-8" })
	public ModelAndView toAddJbxxView(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("jbxx/addJbxx");
		return view;
	}
	
	@RequestMapping(value = {"toEditJbxxView.action",  "/" }, produces = { "application/json;charset=UTF-8" })
	public ModelAndView toEditJbxxView(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("jbxx/editJbxx");
		String tsid = request.getParameter("tsid");
		Map<String,Object> vo = new HashMap<String,Object>();
		vo.put("tsid", tsid);
		Jbxx jbxx = this.jbxxServiceImpl.selectList(vo).get(0);
		List<JbxxPhoto> jbxxPhoto = jbxxPhotoServiceImpl.selectList(vo);
		view.addObject("jbxxPhoto", jbxxPhoto);
		view.addObject("jbxx",jbxx);
		return view;
	}

	@RequestMapping(value = {"addJbxx.action",  "/" }, produces = { "application/json;charset=UTF-8" },method=RequestMethod.POST)
	public Object addJbxx(Jbxx jbxx,HttpServletRequest request) {
		Map<String , Object> res = new HashMap<String , Object>();
		res.put("code", "0");
		res.put("message", "添加成功");
		try {
			Date createDate = new Date();
			String ssqy = Auth.getAuth(request).getSsqy();
			String tsid = StringUtils.createDateRandomString(createDate);
			if(jbxx != null) {
				jbxx.setTsid(ssqy+tsid);
				jbxx.setSsqy(Auth.getAuth(request).getSsqy());
				jbxx.setCzr(Auth.getAuth(request).getLoginname());
				jbxx.setCzsj(createDate);
				jbxx.setUpload("0");
			}
			this.jbxxServiceImpl.txAddJbxxAndPhoto(request,jbxx,imgSavePath);

			//添加未保存的模块厂家
			String txmkcj = jbxx.getTxmkcj() == null?"":jbxx.getTxmkcj();
			if(!txmkcj.equals("")) sysSccjServiceImpl.addCjmcByName(txmkcj);

			//DTU生产厂商
			String sccs = jbxx.getSccs() == null?"":jbxx.getSccs();
			if(!sccs.equals("")) sysSccjServiceImpl.addCjmcByName(sccs);
			//一次柜生产厂商
			String sccsYcg = jbxx.getSccsYcg() == null?"":jbxx.getSccsYcg();
			if(!sccsYcg.equals("")) sysSccjServiceImpl.addCjmcByName(sccsYcg);
			//溢水生产厂商
			String sccsYs = jbxx.getSccsYs() == null?"":jbxx.getSccsYs();
			if(!sccsYs.equals("")) sysSccjServiceImpl.addCjmcByName(sccsYs);
			//消防生产厂商
			String sccsXf = jbxx.getSccsXf() == null?"":jbxx.getSccsXf();
			if(!sccsXf.equals("")) sysSccjServiceImpl.addCjmcByName(sccsXf);

		} catch (Exception e) {
			res.put("code", "-1");
			res.put("message", "操作失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(res);
	}

	@RequestMapping(value = {"updateJbxx.action",  "/" }, produces = {"application/json;charset=UTF-8"},method=RequestMethod.POST)
	public Object updateJbxx(Jbxx jbxx, HttpServletRequest request) {
		Map<String , Object> res = new HashMap<String , Object>();
		res.put("code", "0");
		res.put("message", "修改成功");
		try{
			jbxx.setSsqy(Auth.getAuth(request).getSsqy());
			jbxx.setCzr(Auth.getAuth(request).getLoginname());
			jbxx.setCzsj(new Date());
			this.jbxxServiceImpl.txUpdateJbxxAndPhoto(request,jbxx,imgSavePath);
			//添加未保存的模块厂家
			String txmkcj = jbxx.getTxmkcj() == null?"":jbxx.getTxmkcj();
			if(!txmkcj.equals("")) sysSccjServiceImpl.addCjmcByName(txmkcj);

			//DTU生产厂商
			String sccs = jbxx.getSccs() == null?"":jbxx.getSccs();
			if(!sccs.equals("")) sysSccjServiceImpl.addCjmcByName(sccs);
			//一次柜生产厂商
			String sccsYcg = jbxx.getSccsYcg() == null?"":jbxx.getSccsYcg();
			if(!sccsYcg.equals("")) sysSccjServiceImpl.addCjmcByName(sccsYcg);
			//溢水生产厂商
			String sccsYs = jbxx.getSccsYs() == null?"":jbxx.getSccsYs();
			if(!sccsYs.equals("")) sysSccjServiceImpl.addCjmcByName(sccsYs);
			//消防生产厂商
			String sccsXf = jbxx.getSccsXf() == null?"":jbxx.getSccsXf();
			if(!sccsXf.equals("")) sysSccjServiceImpl.addCjmcByName(sccsXf);
		}catch(Exception e){
			res.put("code", "-1");
			res.put("message", "修改失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(res);
	}

	@RequestMapping(value = {"delJbxx.action",  "/" }, produces = { "application/json;charset=UTF-8" },method=RequestMethod.POST)
	public Object delJbxx(@RequestParam Map<String, Object> form) {
		Map<String , Object> res = new HashMap<String , Object>();
		res.put("code", "0");
		res.put("message", "删除成功");
		String tsid = (String)form.get("tsid");
		try{
			this.jbxxServiceImpl.txDeleteJbxxAndPhoto(tsid);
		}catch(Exception e){
			res.put("code", "-1");
			res.put("message", "删除失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(res);
	}

	@RequestMapping(value = {"selectZzlxAll.action",  "/" }, produces = { "application/json;charset=UTF-8" },method=RequestMethod.POST)
	public Object selectZzlxAll(@RequestParam Map<String, Object> form) {
		form.put("keycode", F.SysKey.zzType);
		List<SysDb> ssqyList = this.sysDbServiceImpl.selectList(form);
		return JSON.toJSONString(ssqyList);
	}

	@RequestMapping("selectByPrimaryKey/{id}")
	public String selectByPrimaryKey(@PathVariable String id) {
		Jbxx jbxx = jbxxServiceImpl.selectByPrimaryKey(id);
		return JSONUtil.toJsonStr(jbxx);
	}

	/***
	 * 调试信息导出
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = {"exportToWordByJbxx.action",  "/" }, produces = { "application/json;charset=UTF-8" })
	public void exportToWordByJbxx(HttpServletRequest request, HttpServletResponse response) {
		String tsid = request.getParameter("tsid");
		XWPFDocument doc = null;
		OutputStream os = null;
		try {
			Jbxx jbxx = jbxxServiceImpl.selectById(tsid);
			doc = jbxxServiceImpl.exportToWordByWord(Auth.getAuth(request).getSsqy(),jbxx);
			os = response.getOutputStream();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/msword");
			response.setHeader("Content-disposition","attachment;filename="+java.net.URLEncoder.encode(jbxx.getAzddDdh()+"DTU调试记录表", "UTF-8")+".docx");//filename为导出的word的名字
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

    @RequestMapping(value = {"selectListJbxxToState.action",  "/" }, produces = { "application/json;charset=UTF-8" },method=RequestMethod.POST)
    public Object selectListJbxxToState(@RequestParam Map<String, Object> form) {
	   // String tsid = form.get("tsid").toString();
        String state = jbxxServiceImpl.selectListJbxxToState(form);
        if (state.equals("true")){
            form.put("state","已完成");
        }else {
            form.put("state","未完成");
        }
        return form;
    }

	/***
	 * 生成jbxx二维码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/createQRCodeByJbxxInfo.action")
	public void createQRCodeByJbxxInfo(HttpServletRequest request, HttpServletResponse response) {
		Jbxx jbxx = jbxxServiceImpl.selectById(request.getParameter("tsid"));
		BufferedImage image = null;
		response.setContentType("image/jpg");
		//禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			image = jbxxServiceImpl.createQRCodeByJbxxInfo(request,jbxx);
			// 输出图象到页面
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 查询调试信息所有超片
	 * @param form
	 * @return
	 */
	@RequestMapping("/getJbxxPhotos.action")
	public Object getJbxxPhotos(@RequestParam Map<String,Object> form) {
		List<JbxxPhoto> phootos = jbxxPhotoServiceImpl.selectList(form);
		return JSONUtil.toJsonStr(phootos);
	}

	/***
	 * 调试信息照片预览
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getJbxxPhotoView.action")
	public void getJbxxPhotoView(HttpServletRequest request, HttpServletResponse response) {
		JbxxPhoto jbxxPhoto = jbxxPhotoServiceImpl.selectById(request.getParameter("pid"));
		FileHandleUtils.previewImage(request,response,jbxxPhoto.getPPath());
	}

	/***
	 * 查询通信模块厂家
	 * @param param
	 * @return
	 */
	@RequestMapping("selectListSccj.action")
	public String selectListSccj(@RequestParam() Map<String,Object> param) {
		List<SysSccj> sysSccjList = sysSccjServiceImpl.selectList(param);
		return JSONUtil.toJsonStr(sysSccjList);
	}
}