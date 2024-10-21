package com.choongang.shoppingmall.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.choongang.shoppingmall.vo.Order_CompleteVO;
import com.choongang.shoppingmall.vo.OrdersVO;

@Mapper
@Repository
public interface OrderDAO {
	// 주문서 작성
	void insertOrder(OrdersVO ordersVO) throws SQLException;
	
	// 주문 완료 페이지
	void insertOrderCompleteVO(Order_CompleteVO order_CompleteVO) throws SQLException;

	// 주문 조회
	OrdersVO selectOrderById(int orderId) throws SQLException;
	
	// 모든 주문 목록 조회
	List<OrdersVO> selectAllOrders() throws SQLException;

	// 모든 주문 상세 조회
	List<Order_CompleteVO> selectOrderCompleteByOrderId(int orderId) throws SQLException;
	
}
