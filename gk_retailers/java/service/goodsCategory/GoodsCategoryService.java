package com.gk.retailers.service.goodsCategory;

import java.util.Map;

public interface GoodsCategoryService {
	/**根据分类的父Id查找相关的商品*/
	Map<String, Object> findGoodsCategoryByParentIdService(Integer pId, Integer start, Integer pageSize);
}
