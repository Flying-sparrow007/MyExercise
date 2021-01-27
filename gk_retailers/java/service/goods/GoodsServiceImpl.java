package com.gk.retailers.service.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.retailers.commons.PageObject;
import com.gk.retailers.entity.Goods;
import com.gk.retailers.mapper.goods.GoodsMapper;
/**
 * 业务层
 * @author LENOVO
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	//注入持久层
	@Autowired
	private GoodsMapper mapper;

	/**根据商品分类Id查询商品*/
	@Override
	public List<Goods> findGoodsByCategoryIdService(Integer categoryId, Integer start, Integer pageSize) {
		List<Goods> list = mapper.findGoodsByCategoryIdMapper(categoryId, start, pageSize);
		return list;
	}
	
	/**根据商品分类Id查询商品*/
	/*@Override
	public List<Goods> findGoodsByCategoryIdService(Integer categoryId) {
		List<Goods> list = mapper.findGoodsByCategoryIdMapper(categoryId, null, null);
		return list;
	}*/

	/**根据商品id查询商品详情*/
	@Override
	public Goods findGoodByIdService(Integer id) {
		Goods good = mapper.findGoodByIdMapper(id);
		return good;
	}

	/**根据父Id查询数据进行分页查询*/
	@Override
	public Map<String, Object> findGoodsAndPageByParentIdService(Integer categoryId, PageObject pageObject) {
		//获取当前页
		int pageCurrent = pageObject.getPageCurrent();
		//获取起始页
		int start = pageObject.getStartIndex();
		//获取每页显示的记录数
		int pageSize = pageObject.getPageSize();
		//获取该商品的总行数
		int rowCount = mapper.findCountByParentIdMapper(categoryId);
		pageObject.setRowCount(rowCount);
		//获取该商品的总页数
		int pageCount = pageObject.getPageCount();
		//根据父id获取商品信息
		List<Goods> goods = mapper.findGoodsByCategoryIdMapper(categoryId, start, pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods", goods);
		map.put("pageCurrent", pageCurrent);
		map.put("pageCount", pageCount);
		
		return map;
	}

	/**根据父id查询商品的总数量*/
	@Override
	public int findCountByParentIdService(Integer categoryId) {
		return mapper.findCountByParentIdMapper(categoryId);
	}
	
}
