package com.choongang.shoppingmall.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choongang.shoppingmall.service.OrderService;
import com.choongang.shoppingmall.vo.Order_CompleteVO;
import com.choongang.shoppingmall.vo.OrdersVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders") // 기본 URL
@Slf4j
public class OrderController {

	@Autowired

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	// 주문서 작성
	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody OrdersVO ordersVO) {
		log.info("주문서 작성 요청 시작: {}", ordersVO); // 요청 시작 로그
		try {
			// 주문 정보 저장
			orderService.createOrder(ordersVO);
			// 주문 완료 페이지 저장
			Order_CompleteVO ordercomplete = new Order_CompleteVO();
			ordercomplete.setOrder_id(ordersVO.getOrder_id()); // 주문 ID 설정
			ordercomplete.setQuantity(ordersVO.getCount()); // 수량 설정
			ordercomplete.setTotal_price(ordersVO.getPrice()); // 총 가격 설정

			orderService.createOrder_Complete(ordercomplete); // 주문완료 페이지 저장

			log.info("주문서 작성 요청 성공: {}", ordersVO); // 성공 로그
			return new ResponseEntity<>("주문서 작성 성공", HttpStatus.CREATED);
		} catch (SQLException e) {
			log.error("주문서 작성 요청 실패: {}", e.getMessage());
			return new ResponseEntity<>("주문서 작성 실패: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 주문 조회
	@GetMapping("/{orderId}")
	public ResponseEntity<OrdersVO> getorder(@PathVariable int orderId) {
		log.info("주문 조회 요청 시작: 주문 ID = {}", orderId); // 요청 시작 로그
		try {
			OrdersVO ordersVO = orderService.getOrderByid(orderId);
			log.info("주문 조회 성공: {}", ordersVO); // 요청 시작 로그
			if (ordersVO != null) {
				return new ResponseEntity<>(ordersVO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 주문이 없는 경우
			}

		} catch (SQLException e) {
			log.error("주문 조회 실패: {}", e.getMessage()); // 요청 시작 로그
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 서버 에러
		}
	}

	@GetMapping("/orders.html")
	public String showOrderPage(Model model) {
		log.info("주문 페이지 요청"); // 주문 페이지 요청 로그 추가

		try {

			// 현재 인증된 사용자의 정보를 가져옴
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (authentication != null && authentication.isAuthenticated()) {
				String username = authentication.getName(); // 사용자 이름 가져오기
				// 추가적인 사용자 정보가 필요하면 Authentication 객체를 캐스팅하여 가져올수있음
				model.addAttribute("username", username); // 모델에 사용자 이름추가
			} else {
				log.warn("인증되지 않은 사용자"); // 인증되지 않은 사용자 처리
			}

			// 주문 목록을 서비스에서 가져옴
			List<OrdersVO> ordersList = orderService.getAllOrders();
			model.addAttribute("orders", ordersList); // Model 에 주문 목록 추가

		} catch (SQLException e) {
			log.error("주문 목록 조회 실패 {}", e.getMessage()); // 오류 로그 추가
		}

		return "orders"; // orders.html 뷰 이름 반환
	}

}
