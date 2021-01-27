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
 * ҵ���
 * @author LENOVO
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	//ע��־ò�
	@Autowired
	private GoodsMapper mapper;

	/**������Ʒ����Id��ѯ��Ʒ*/
	@Override
	public List<Goods> findGoodsByCategoryIdService(Integer categoryId, Integer start, Integer pageSize) {
		List<Goods> list = mapper.findGoodsByCategoryIdMapper(categoryId, start, pageSize);
		return list;
	}
	
	/**������Ʒ����Id��ѯ��Ʒ*/
	/*@Override
	public List<Goods> findGoodsByCategoryIdService(Integer categoryId) {
		List<Goods> list = mapper.findGoodsByCategoryIdMapper(categoryId, null, null);
		return list;
	}*/

	/**������Ʒid��ѯ��Ʒ����*/
	@Override
	public Goods findGoodByIdService(Integer id) {
		Goods good = mapper.findGoodByIdMapper(id);
		return good;
	}

	/**���ݸ�Id��ѯ���ݽ��з�ҳ��ѯ*/
	@Override
	public Map<String, Object> findGoodsAndPageByParentIdService(Integer categoryId, PageObject pageObject) {
		//��ȡ��ǰҳ
		int pageCurrent = pageObject.getPageCurrent();
		//��ȡ��ʼҳ
		int start = pageObject.getStartIndex();
		//��ȡÿҳ��ʾ�ļ�¼��
		int pageSize = pageObject.getPageSize();
		//��ȡ����Ʒ��������
		int rowCount = mapper.findCountByParentIdMapper(categoryId);
		pageObject.setRowCount(rowCount);
		//��ȡ����Ʒ����ҳ��
		int pageCount = pageObject.getPageCount();
		//���ݸ�id��ȡ��Ʒ��Ϣ
		List<Goods> goods = mapper.findGoodsByCategoryIdMapper(categoryId, start, pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods", goods);
		map.put("pageCurrent", pageCurrent);
		map.put("pageCount", pageCount);
		
		return map;
	}

	/**���ݸ�id��ѯ��Ʒ��������*/
	@Override
	public int findCountByParentIdService(Integer categoryId) {
		return mapper.findCountByParentIdMapper(categoryId);
	}
	
}
