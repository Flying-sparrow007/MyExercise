package com.gk.retailers.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gk.retailers.entity.Goods;

/**
 * goodsMapper.xml��ӳ����
 * @author LENOVO
 *
 */
public interface GoodsMapper {
	/**������Ʒ�ķ����ѯ�����Ʒ*/
	List<Goods> findGoodsByCategoryIdMapper(@Param("categoryId")Integer categoryId, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

	/**������Ʒid��ѯ��Ʒ����*/
	Goods findGoodByIdMapper(Integer id);
	
	/**���ݸ�Id��ѯ��Ʒ��������*/
	Integer findCountByParentIdMapper(Integer categoryId);
}
