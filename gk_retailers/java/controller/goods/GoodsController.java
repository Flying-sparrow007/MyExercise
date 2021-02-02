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
 * ���Ʋ�
 * @author LENOVO
 *
 */
@Controller
@RequestMapping("goods")
public class GoodsController extends HandlerException {
	@Autowired
	private GoodsService service;
	
	/**�ļ�����ҳ��*/
	@RequestMapping("toGoodsHtml.do")
	public String goodsHtml(HttpSession session, Integer id, ModelMap model, PageObject pageObject){
		User user = (User)session.getAttribute("user");
		/*if(user == null){//�ض��򵽵�¼ҳ��
			return "redirect:../user/toLoginHtml.do";
		}*/
		//����Ʒ��id
		session.setAttribute("categoryId", id);
		Map<String, Object> map = service.findGoodsAndPageByParentIdService(id, pageObject);
		model.addAttribute("map", map);
		
		return "search";
	}
	
	/**��Ʒ����ҳ��*/
	@RequestMapping("goodsDetail.do")
	public String goodsDetail(Integer id, ModelMap model){
		Goods good = service.findGoodByIdService(id);
		model.addAttribute("good", good);
		return "product_details";
	}
	
	/**��Ʒ����һҳ*/
	@RequestMapping("nextPage.do")
	public String nextPage(ModelMap model, PageObject pageObject, HttpSession session){
		//��ȡ��Ʒ��id
		Integer categoryId = (Integer)session.getAttribute("categoryId");
		//��ȡ��ǰҳ
		int pageCurrent = pageObject.getPageCurrent();
		//��ȡ������
		int rowCount = service.findCountByParentIdService(categoryId);
		pageObject.setRowCount(rowCount);
		//��ȡ��ҳ��
		int pageCount = pageObject.getPageCount();
		//�жϵ�ǰҳ�����ڵ������ҳ��,��ǰҳ��Ϊ���ҳ��
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
	
	/**��Ʒ����һҳ*/
	@RequestMapping("topPage.do")
	public String topPage(ModelMap model, PageObject pageObject, HttpSession session){
		//��ȡ��Ʒ��id
		Integer categoryId = (Integer)session.getAttribute("categoryId");
		//��ȡ��ǰҳ
		int pageCurrent = pageObject.getPageCurrent();
		//��ȡ������
		int rowCount = service.findCountByParentIdService(categoryId);
		pageObject.setRowCount(rowCount);
		//��ȡ��ҳ��
		int pageCount = pageObject.getPageCount();
		//�жϵ�ǰҳ��С�ڵ��ڵ�һҳ,��ǰҳ��Ϊ1
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
	
	/**��ȡ��Ʒ��Ӧҳ*/
	@RequestMapping("getPage.do")
	public String getPage(ModelMap model, PageObject pageObject, HttpSession session){
		
		//��ȡ��Ʒ��id
		Integer categoryId = (Integer)session.getAttribute("categoryId");
		Map<String, Object> map = service.findGoodsAndPageByParentIdService(categoryId, pageObject);
		model.addAttribute("map", map);
		
		return "search";
	}
}
