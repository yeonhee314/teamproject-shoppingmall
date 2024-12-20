package com.choongang.shoppingmall.dao;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.choongang.shoppingmall.vo.MyPageReviewInfo;
import com.choongang.shoppingmall.vo.Order_ItemVO;
import com.choongang.shoppingmall.vo.OrdersVO;

@Mapper
public interface OrderDAO {
	// 주문 개수
	int selectOrderCount(HashMap<String, String> map) throws SQLException;
	// 한 페이지 얻기
	List<OrdersVO> selectAdminOrderList(HashMap<String, String> map) throws SQLException;
	// 주문내역 하나 얻기
	OrdersVO selectOrderById(int order_id) throws SQLException;
	// 주문아이템 얻기
	List<Order_ItemVO> selectOrderItemByOrderId(int order_id) throws SQLException;
	// 결제정보 금액 얻기
	Order_ItemVO paymentPriceInfo(int order_id) throws SQLException;
	// 주문상태 배송중으로 변경
	void orderStatusUpdateShipping(int order_id) throws SQLException;
	// 주문상태 배송완료 변경
	void orderStatusUpdateDelivery(int order_id) throws SQLException;
	// 송장번호 저장
	void updateInvoice(OrdersVO ordersVO) throws SQLException;
	// 마이페이지 리뷰관리 : 아이디별 주문 내역
	List<MyPageReviewInfo> selectByMyReview(int user_id) throws SQLException;
	// 마이페이지 리뷰관리 : 아이디별 주문 내역 갯수
	int selectByMyReviewCount(int user_id) throws SQLException;
	// 마이페이지 리뷰관리 : 리뷰 작성 숨기기 시 상태 변경
	void updateReviewStatus(int order_item_id) throws SQLException;
	// 주문 상품 id로 주문 상품 한개 가져오기
	Order_ItemVO selectOrderItemByOrderItemId(int order_item_id) throws SQLException;
	
	// 주문 테이블 저장
	void addToOrder(OrdersVO vo) throws SQLException;
	// 주문 상품 리스트 저장
	void addToOrderItems(Order_ItemVO vo) throws SQLException;
	// 가장 큰 주문번호 가져오기
	Integer selectMaxOrderId() throws SQLException;
	// 첫번째 주문 데이터 가져올때만 사용한다.
	Integer selectFirstOrdersId() throws SQLException;
	
	//주문 횟수,총합 출력
	Map<String, Object> getOrderStatsByUserId(int userId);
	
	//주문 정보 
	List<OrdersVO> getOrdersByUserId(int userId);

	 
}
