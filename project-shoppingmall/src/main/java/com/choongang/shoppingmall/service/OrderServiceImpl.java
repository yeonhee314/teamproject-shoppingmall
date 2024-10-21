package com.choongang.shoppingmall.service;

import java.sql.SQLException;

import java.util.List;

import org.springframework.stereotype.Service;

import com.choongang.shoppingmall.dao.OrderDAO;
import com.choongang.shoppingmall.vo.Order_CompleteVO;
import com.choongang.shoppingmall.vo.OrdersVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{


	private final OrderDAO orderDAO;
	
	public OrderServiceImpl(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	// 주문서 작성
	@Override
	public void createOrder(OrdersVO ordersVO) throws SQLException {
		log.info("주문서 작성 시작 : {} ", ordersVO);
		orderDAO.insertOrder(ordersVO);		// DAO를 통해 주문 생성
		log.info("주문서 작성 완료 : {} ", ordersVO);
		
		
	}
	// 주문서 작성
	@Override
	public void createOrderComplete(Order_CompleteVO orderCompleteVO) throws SQLException {
		log.info("주문서 작성 시작 : {} ", orderCompleteVO);
		orderDAO.insertOrderCompleteVO(orderCompleteVO);	// DAO를 통해 주문 완료페이지 생성
		log.info("주문서 작성 완료 : {} ", orderCompleteVO);
	}
	// 주문 조회
	@Override
	public OrdersVO getOrderByid(int orderid) throws SQLException {
		log.info("주문서 조회 시작 : {} ", orderid);
		OrdersVO ordersVO = orderDAO.selectOrderById(orderid);	// DAO를 통해 주문 조회
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
	public List<Order_CompleteVO> getOrderCompleteByOrderId(int orderId) throws SQLException { // SQLException을 메서드 시그니처에 추가
	    log.info("주문 완료 정보 조회 시작: {}" , orderId);
	    List<Order_CompleteVO> orderCompleteList = orderDAO.selectOrderCompleteByOrderId(orderId); // DAO를 통해 주문 상세 조회
	    log.info("주문 완료 정보 조회 완료: {}" , orderCompleteList);
	    return orderCompleteList;
	}

	
	
}
