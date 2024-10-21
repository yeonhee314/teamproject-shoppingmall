package com.choongang.shoppingmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.choongang.shoppingmall.service.OrderService;
import com.choongang.shoppingmall.vo.CartVO;
import com.choongang.shoppingmall.vo.Order_CompleteVO;
import com.choongang.shoppingmall.vo.OrdersVO;
import com.choongang.shoppingmall.vo.ProductVO;
import com.choongang.shoppingmall.vo.UserVO;

@Controller
@RequestMapping("orderComplete") // 기본 URL
public class OrderCompleteController {

    @Autowired
    private OrderService orderService;

    // 주문 완료 페이지 표시
    @GetMapping("templates/orderComplete")
    public String showOrderCompletePage(@RequestParam int orderId, Model model) {
        try {
            List<Order_CompleteVO> orderCompleteList = orderService.getOrderCompleteByOrderId(orderId);
            OrdersVO orders = orderService.getOrderByid(orderId);
            
            // 사용자 정보 임시 객체
            UserVO user = new UserVO();
            
            CartVO cart = new CartVO();
            
            ProductVO product = new ProductVO();
            
            model.addAttribute("orders", orders);
            model.addAttribute("user", user);
            model.addAttribute("cart", cart); 
            model.addAttribute("product", product); // items를 모델에 추가
            model.addAttribute("orderCompleteList",orderCompleteList);
            
            return "orderComplete"; // orderComplete.html을 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 에러 페이지 반환
        }
        
    }
    
}

