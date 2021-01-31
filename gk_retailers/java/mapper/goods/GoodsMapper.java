package com.gk.retailers.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gk.retailers.entity.Goods;

/**
 * goodsMapper.xml的映射器
 * @author LENOVO
 *
 */
public interface GoodsMapper {
	/**根据商品的分类查询相关商品*/
	List<Goods> findGoodsByCategoryIdMapper(@Param("categoryId")Integer categoryId, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

	/**根据商品id查询商品详情*/
	Goods findGoodByIdMapper(Integer id);
	
	/**根据父Id查询商品的总数量*/
	Integer findCountByParentIdMapper(Integer categoryId);
}
