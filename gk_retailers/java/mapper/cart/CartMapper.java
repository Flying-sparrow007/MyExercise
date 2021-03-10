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

    /**����uId��ѯ���ﳵ��������Ʒ*/
	List<Cart> getAllCartByUIdMapper(Integer uId);

	/**����ɾ�����ﳵ��Ʒ*/
	void delCartItemsMapper(@Param("strArr")String[] strArr);
}