package com.gk.retailers.service.goodsCategory;

import java.util.Map;

public interface GoodsCategoryService {
	/**���ݷ���ĸ�Id������ص���Ʒ*/
	Map<String, Object> findGoodsCategoryByParentIdService(Integer pId, Integer start, Integer pageSize);
}
