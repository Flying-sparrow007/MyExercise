package com.gk.retailers.service.goods;

import java.util.List;
import java.util.Map;

import com.gk.retailers.commons.PageObject;
import com.gk.retailers.entity.Goods;

public interface GoodsService {
	/**根据商品分类Id查询商品*/
	List<Goods> findGoodsByCategoryIdService(Integer categoryId, Integer start, Integer pageSize);
	
	/**根据商品分类Id查询商品*/
	//List<Goods> findGoodsByCategoryIdService(Integer categoryId);
	
	/**根据商品id查询商品详情*/
	Goods findGoodByIdService(Integer id);
	
	/**根据父Id查询数据进行分页查询*/
	Map<String, Object> findGoodsAndPageByParentIdService(Integer categoryId, PageObject pageObject);
	
	/**根据父id查询商品的总数量*/
	int findCountByParentIdService(Integer categoryId);
}
