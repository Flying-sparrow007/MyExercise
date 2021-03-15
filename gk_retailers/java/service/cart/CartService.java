package com.gk.retailers.service.cart;

import java.util.List;

import com.gk.retailers.entity.Cart;

public interface CartService {
	/**根据uId查询购物车的所有商品*/
	List<Cart> getAllCartByUIdService(Integer uId);

	/**根据id删除购物车商品*/
	void deleteCartByIdService(Integer id);

	/**批量删除购物车商品*/
	void delCartItemsService(String itemIds);
}
