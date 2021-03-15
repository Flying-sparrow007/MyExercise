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
	
	/**根据uId查询购物车的所有商品*/
	@Override
	public List<Cart> getAllCartByUIdService(Integer uId) {
		return cartMapper.getAllCartByUIdMapper(uId);
	}

	/**根据id删除购物车商品*/
	@Override
	public void deleteCartByIdService(Integer id) {
		cartMapper.deleteByPrimaryKey(id);
	}

	/**批量删除购物车商品*/
	@Override
	public void delCartItemsService(String itemIds) {
		//将itemIds分割返回数组
		String[] strArr = itemIds.split(",");
		cartMapper.delCartItemsMapper(strArr);
	}

}
