package com.gk.retailers.service.cart;

import java.util.List;

import com.gk.retailers.entity.Cart;

public interface CartService {
	/**����uId��ѯ���ﳵ��������Ʒ*/
	List<Cart> getAllCartByUIdService(Integer uId);

	/**����idɾ�����ﳵ��Ʒ*/
	void deleteCartByIdService(Integer id);

	/**����ɾ�����ﳵ��Ʒ*/
	void delCartItemsService(String itemIds);
}
