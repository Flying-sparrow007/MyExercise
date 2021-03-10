package com.gk.retailers.mapper.cart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gk.retailers.entity.Cart;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    /**根据uId查询购物车的所有商品*/
	List<Cart> getAllCartByUIdMapper(Integer uId);

	/**批量删除购物车商品*/
	void delCartItemsMapper(@Param("strArr")String[] strArr);
}