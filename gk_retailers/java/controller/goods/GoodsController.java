package com.gk.retailers.controller.goods;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk.retailers.commons.HandlerException;
import com.gk.retailers.commons.PageObject;
import com.gk.retailers.entity.Goods;
import com.gk.retailers.entity.User;
import com.gk.retailers.service.goods.GoodsService;

/**
 * 控制层
 * @author LENOVO
 *
 */
@Controller
@RequestMapping("goods")
public class GoodsController extends HandlerException {
	@Autowired
	private GoodsService service;
	
	/**四级分类页面*/
	@RequestMapping("toGoodsHtml.do")
	public String goodsHtml(HttpSession session, Integer id, ModelMap model, PageObject pageObject){
		User user = (User)session.getAttribute("user");
		/*if(user == null){//重定向到登录页面
			return "redirect:../user/toLoginHtml.do";
		}*/
		//绑定商品父id
		session.setAttribute("categoryId", id);
		Map<String, Object> map = service.findGoodsAndPageByParentIdService(id, pageObject);
		model.addAttribute("map", map);
		
		return "search";
	}
	
	/**商品详情页面*/
	@RequestMapping("goodsDetail.do")
	public String goodsDetail(Integer id, ModelMap model){
		Goods good = service.findGoodByIdService(id);
		model.addAttribute("good", good);
		return "product_details";
	}
	
	/**商品的下一页*/
	@RequestMapping("nextPage.do")
	public String nextPage(ModelMap model, PageObject pageObject, HttpSession session){
		//获取商品父id
		Integer categoryId = (Integer)session.getAttribute("categoryId");
		//获取当前页
		int pageCurrent = pageObject.getPageCurrent();
		//获取总行数
		int rowCount = service.findCountByParentIdService(categoryId);
		pageObject.setRowCount(rowCount);
		//获取总页数
		int pageCount = pageObject.getPageCount();
		//判断当前页数大于等于最大页数,当前页数为最大页数
		if(pageCurrent >= pageCount){
			pageObject.setPageCurrent(pageCurrent);
		}else{
			pageCurrent++;
			pageObject.setPageCurrent(pageCurrent);
		}
		
		Map<String, Object> map = service.findGoodsAndPageByParentIdService(categoryId, pageObject);
		
		model.addAttribute("map", map);
		
		return "search";
	}
	
	/**商品的下一页*/
	@RequestMapping("topPage.do")
	public String topPage(ModelMap model, PageObject pageObject, HttpSession session){
		//获取商品父id
		Integer categoryId = (Integer)session.getAttribute("categoryId");
		//获取当前页
		int pageCurrent = pageObject.getPageCurrent();
		//获取总行数
		int rowCount = service.findCountByParentIdService(categoryId);
		pageObject.setRowCount(rowCount);
		//获取总页数
		int pageCount = pageObject.getPageCount();
		//判断当前页数小于等于第一页,当前页数为1
		if(pageCurrent <= 1){
			pageObject.setPageCurrent(1);
		}else{
			pageCurrent--;
			pageObject.setPageCurrent(pageCurrent);
		}
		
		Map<String, Object> map = service.findGoodsAndPageByParentIdService(categoryId, pageObject);
		
		model.addAttribute("map", map);
		
		return "search";
	}
	
	/**获取商品对应页*/
	@RequestMapping("getPage.do")
	public String getPage(ModelMap model, PageObject pageObject, HttpSession session){
		
		//获取商品父id
		Integer categoryId = (Integer)session.getAttribute("categoryId");
		Map<String, Object> map = service.findGoodsAndPageByParentIdService(categoryId, pageObject);
		model.addAttribute("map", map);
		
		return "search";
	}
}
