package com.gk.retailers.service.goodsCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.retailers.entity.Goods;
import com.gk.retailers.entity.GoodsCategory;
import com.gk.retailers.mapper.goods.GoodsMapper;
import com.gk.retailers.mapper.goodsCategory.GoodsCategoryMapper;

/**
 * GoodsCategory��ҵ���
 * @author LENOVO
 *
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
	@Autowired
	private GoodsCategoryMapper mapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public Map<String, Object> findGoodsCategoryByParentIdService(Integer pId, Integer start, Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		//��ȡ�����������Ʒ
		List<GoodsCategory> list = mapper.findGoodsCategoryByParentId(pId, start, pageSize);
		//��ȡ�����������Ʒ
		List<List<GoodsCategory>> list2 = new ArrayList<>();
		for(GoodsCategory g: list){//�������������ȡ��������
			List<GoodsCategory> ls = mapper.findGoodsCategoryByParentId(g.getId(), null, null);
			list2.add(ls);
		}
		map.put("list", list);
		map.put("list2", list2);
		return map;
	}
	
}
