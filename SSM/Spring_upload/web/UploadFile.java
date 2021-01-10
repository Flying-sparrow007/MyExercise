package web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFile {
	
	//注入业务层
	@Autowired
	private UploadService service;
	
	@RequestMapping("/showUploadHtml.do")
	public String showUpload(){
		return "upload";
	}
	
	@RequestMapping("/upload.do")
	@ResponseBody
	/*
	 * MultipartFile获得附件的集合,使用的技术有jQuery框架,以及SpringMVC框架,
	 * 主要实现异步文件上传,同时其封装对象
	 */
	public JsonResult<String> upload(MultipartFile file, HttpSession session, HttpServletRequest req) throws IllegalStateException, IOException{
		//System.out.println("URI = " + req.getRequestURI());
		//System.out.println("URL = " + req.getRequestURL());
		//获取真实路径
		String path = session.getServletContext().getRealPath("");
		System.out.println("真实路径: " + path);
		//获取文件的真实名字
		String fileName = file.getOriginalFilename();
		System.out.println("真实名字: " + fileName);
		
		//日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		
		File f = new File(path, "/upload/" + time + "/" + fileName);
		if(!f.exists()){//如果没有则创建目录
			f.mkdirs();
		}
		//上传文件
		file.transferTo(f);
		
		//返回路径
		String pathName = "/upload/" + time + "/" + fileName;
		
		//根据用户id修改数据库中的文件地址
		service.updateImageByIdService(1, pathName);
		
		return new JsonResult<String>(pathName);
	}
	
}
