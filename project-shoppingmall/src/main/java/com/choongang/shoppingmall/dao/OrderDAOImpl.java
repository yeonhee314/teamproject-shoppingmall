package com.choongang.shoppingmall.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.choongang.shoppingmall.vo.Order_CompleteVO;
import com.choongang.shoppingmall.vo.OrdersVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.choongang.shoppingmall.mappers.OrderMapper";

    // 주문서 작성 / 배송지 / 요청사항
    @Override
    public void insertOrder(OrdersVO ordersVO) throws SQLException {
        log.info("zipcode: {}, addr: {}, addr_detail: {}, req: {}", 
            ordersVO.getZipcode(), ordersVO.getAddr(), ordersVO.getAddr_detail(), ordersVO.getReq());
        sqlSession.insert(NAMESPACE + ".insertOrder", ordersVO);
    }

    // 주문 상세 작성 / 주문 상품 / 할인율 /결제수단 / 총 결제 금액
    @Override
    public void insertOrderCompleteVO(Order_CompleteVO order_CompleteVO) throws SQLException {
        log.info("product_name: {}, quantity: {}, product_price: {}, thumb: {}, product_status: {}, discount: {}, pay_method: {}, total_price: {}", 
            order_CompleteVO.getProduct_name(), 
            order_CompleteVO.getQuantity(),
            order_CompleteVO.getProduct_price(),
            order_CompleteVO.getThumb(),
            order_CompleteVO.getProduct_status(),
            order_CompleteVO.getDiscount(),
            order_CompleteVO.getPay_ment(),
            order_CompleteVO.getTotal_price());
        sqlSession.insert(NAMESPACE + ".insertOrder_CompleteVO", order_CompleteVO);
    }

    // 주문 조회
    @Override
    public OrdersVO selectOrderById(int orderId) throws SQLException {
        log.info("주문 조회 시작: {}", orderId);
        return sqlSession.selectOne(NAMESPACE + ".selectOrderById", orderId);
    }

    @Override
    public List<OrdersVO> selectAllOrders() throws SQLException {
        log.info("모든 주문 조회 시작");
        return sqlSession.selectList(NAMESPACE + ".selectAllOrders"); 
    }

    // 주문 완료 정보 조회
    @Override
    public List<Order_CompleteVO> selectOrderCompleteByOrderId(int orderId) throws SQLException {
        log.info("주문 완료 정보 조회 시작: {}", orderId);
        List<Order_CompleteVO> orderCompleteList = sqlSession.selectList(NAMESPACE + ".selectOrderCompleteByOrderId", orderId); 
        log.info("주문 완료 정보 조회 완료: {}", orderCompleteList);
        return orderCompleteList;
    }
}
