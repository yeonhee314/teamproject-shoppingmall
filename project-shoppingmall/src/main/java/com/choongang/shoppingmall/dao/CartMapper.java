package com.choongang.shoppingmall.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.choongang.shoppingmall.vo.CartVO;

@Mapper
public interface CartMapper {

	// 장바구니에 상품 추가
	void addCart(CartVO cartVO);
    
    // 사용자의 장바구니 목록 조회
    List<CartVO> getCartItems(int userId);
    
    //장바구니 상품 삭제
    void deleteCart(int cartId);
    
    //장바구니 수량 수정
    void updateCart(CartVO cartVO);
     
    int getCartItemCount(@Param("userId") int userId);
    
}