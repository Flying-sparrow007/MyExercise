package com.gk.retailers.controller.goodsCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk.retailers.commons.HandlerException;
import com.gk.retailers.service.goodsCategory.GoodsCategoryService;

/**
 * ¿ØÖÆ²ã
 * @author LENOVO
 *
 */
@Controller
@RequestMapping("category")
public class GoodsCategoryController extends HandlerException {
	@Autowired
	private GoodsCategoryService service;
}
