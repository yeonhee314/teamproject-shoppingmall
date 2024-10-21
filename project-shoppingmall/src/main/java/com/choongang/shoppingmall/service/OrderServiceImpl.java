package com.choongang.shoppingmall.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choongang.shoppingmall.dao.OrderDAO;
import com.choongang.shoppingmall.vo.Order_CompleteVO;
import com.choongang.shoppingmall.vo.OrdersVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDAO orderDAO;

	// 주문서 작성
	@Override
	public void createOrder(OrdersVO ordersVO) throws SQLException {
		log.info("주문서 작성 시작 : {} ", ordersVO);
		orderDAO.insertOrder(ordersVO);
		log.info("주문서 작성 완료 : {} ", ordersVO);
		
		
	}
	// 주문서 작성
	@Override
	public void createOrder_Complete(Order_CompleteVO order_CompleteVO) throws SQLException {
		log.info("주문서 작성 시작 : {} ", order_CompleteVO);
		orderDAO.insertOrderCompleteVO(order_CompleteVO);
		log.info("주문서 작성 완료 : {} ", order_CompleteVO);
		
		
	}
	

	// 주문 조회
	@Override
	public OrdersVO getOrderByid(int orderid) throws SQLException {
		log.info("주문서 조회 시작 : {} ", orderid);
		OrdersVO ordersVO = orderDAO.selectOrderById(orderid);
		log.info("주문서 조회 완료 : {} ", ordersVO);
		return ordersVO;
	}
	
	// 모든 주문 목록 조회
	@Override
	public List<OrdersVO> getAllOrders() throws SQLException {
		log.info("모든 주문 목록 조회 시작");
		List<OrdersVO> orderList = orderDAO.selectAllOrders(); // DAO 를 통해 모든 주문 목록조회
		log.info("모든 주문 목록 조회 완료 : {}", orderList);
		return orderList;
		
	}
	
	@Override	
	public List<Order_CompleteVO> getOrderCompleteByOrderId(int orderId) {
		log.info("주문 완료 정보 조회 시작: {}" , orderId);
		//
		return null;
	}
	
	
}
