package com.choongang.shoppingmall.service;

import com.choongang.shoppingmall.vo.PagingVO;
import com.choongang.shoppingmall.vo.ProductVO;

public interface ProductService {
	// 한개 얻기
	ProductVO selectByProductId(int id);
	// 한 페이지 얻기
	PagingVO<ProductVO> getProductList(int currentPage, int sizeOfPage, int sizeOfBlock);
	// 카테고리 id로 필터링한 상품 한 페이지 얻기
	PagingVO<ProductVO> getFilterProductList (int categoryId, int currentPage, int sizeOfPage, int sizeOfBlock);
	// 상품명 중복세기
	int selectCountByProductName(String product_name);
	void insert(ProductVO productVO);
	void update(ProductVO productVO);
	void delete(int product_id);
}
