package com.gk.retailers.mapper.goodsCategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gk.retailers.entity.GoodsCategory;

/**
 * 映射器
 * @author LENOVO
 *
 */
public interface GoodsCategoryMapper {
	/**根据父id查询商品数据*/
	List<GoodsCategory> findGoodsCategoryByParentId(@Param("pId")Integer pId, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
}
