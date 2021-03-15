package com.gk.retailers.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.retailers.entity.Cart;
import com.gk.retailers.mapper.cart.CartMapper;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	public CartMapper cartMapper;
	
	/**����uId��ѯ���ﳵ��������Ʒ*/
	@Override
	public List<Cart> getAllCartByUIdService(Integer uId) {
		return cartMapper.getAllCartByUIdMapper(uId);
	}

	/**����idɾ�����ﳵ��Ʒ*/
	@Override
	public void deleteCartByIdService(Integer id) {
		cartMapper.deleteByPrimaryKey(id);
	}

	/**����ɾ�����ﳵ��Ʒ*/
	@Override
	public void delCartItemsService(String itemIds) {
		//��itemIds�ָ������
		String[] strArr = itemIds.split(",");
		cartMapper.delCartItemsMapper(strArr);
	}

}
