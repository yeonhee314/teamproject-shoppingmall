package com.choongang.shoppingmall.vo;

import lombok.Data;

@Data
public class CartVO {
	
    private int cartId;  // 장바구니 고유번호
	private int userId;	// 회원 고유번호
	private int productId;     // 상품 ID
	private int cartCount;     // 장바구니에 담긴 수량
	private int cartPrice; // 장바구니 총금액
	//private ProductVO product; 
	
    private String productName; // 상품 이름
    private String productOption; // 상품 옵션
    
    private int productPrice;  // 상품 가격
	private int discount;	
    private int productCategory;  // 상품 카테고리
    private int productStock;  // 상품 재고
    
 // 할인가 계산
 	public int getDiscountPrice(int productPrice, int discount) {
 		return productPrice - (productPrice * discount / 100);
 	}
}
