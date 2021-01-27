package com.gk.retailers.service.goods;

import java.util.List;
import java.util.Map;

import com.gk.retailers.commons.PageObject;
import com.gk.retailers.entity.Goods;

public interface GoodsService {
	/**������Ʒ����Id��ѯ��Ʒ*/
	List<Goods> findGoodsByCategoryIdService(Integer categoryId, Integer start, Integer pageSize);
	
	/**������Ʒ����Id��ѯ��Ʒ*/
	//List<Goods> findGoodsByCategoryIdService(Integer categoryId);
	
	/**������Ʒid��ѯ��Ʒ����*/
	Goods findGoodByIdService(Integer id);
	
	/**���ݸ�Id��ѯ���ݽ��з�ҳ��ѯ*/
	Map<String, Object> findGoodsAndPageByParentIdService(Integer categoryId, PageObject pageObject);
	
	/**���ݸ�id��ѯ��Ʒ��������*/
	int findCountByParentIdService(Integer categoryId);
}
