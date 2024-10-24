package com.choongang.shoppingmall.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.choongang.shoppingmall.vo.ProductVO;

@Mapper
public interface ProductDAO {
	// 상품 갯수 세기
	int selectProductCount() throws SQLException;
	// 필터링된 상품 갯수 세기
	int selectFilterProductCount(int category_id) throws SQLException;
	// 한개 얻기
	ProductVO selectByProductId(int product_id) throws SQLException;
	// 중복 이름 세기
	int selectCountByProductName(String product_name) throws Exception;
	// 한 페이지 얻기
	List<ProductVO> selectProductList(HashMap<String, Integer> map) throws SQLException;
	// 카테고리 id로 필터링한 상품 한 페이지 얻기
	List<ProductVO> selectFilterProductList (HashMap<String, Integer> map) throws SQLException;
	
	void insert(ProductVO productVO) throws SQLException;
	void update(ProductVO productVO) throws SQLException;
	void delete(int product_id) throws SQLException;
}
