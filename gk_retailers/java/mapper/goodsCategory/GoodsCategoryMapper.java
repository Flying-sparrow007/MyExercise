package com.gk.retailers.mapper.goodsCategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gk.retailers.entity.GoodsCategory;

/**
 * ӳ����
 * @author LENOVO
 *
 */
public interface GoodsCategoryMapper {
	/**���ݸ�id��ѯ��Ʒ����*/
	List<GoodsCategory> findGoodsCategoryByParentId(@Param("pId")Integer pId, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
}
