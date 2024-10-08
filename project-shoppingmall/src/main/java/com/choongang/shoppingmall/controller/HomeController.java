package com.choongang.shoppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.choongang.shoppingmall.service.ProductService;
import com.choongang.shoppingmall.vo.PagingVO;
import com.choongang.shoppingmall.vo.ProductPagingVO;
import com.choongang.shoppingmall.vo.ProductVO;

@Controller
@Configuration
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/index.html")
	public String index(@ModelAttribute ProductPagingVO productPagingVO, Model model) {
		PagingVO<ProductVO> pv = productService.getProductList(productPagingVO.getCurrentPage(), productPagingVO.getSizeOfPage(), productPagingVO.getSizeOfBlock());
		model.addAttribute("pv", pv);
		model.addAttribute("ppv", productPagingVO);
		model.addAttribute("newLine", "\n" );
		model.addAttribute("br", "<br>" );
		
		return "index";
	}
	@GetMapping("/about.html")
	public String about() {
		return "about";
	}
	@GetMapping("/blog.html")
	public String blog() {
		return "blog";
	}
	@GetMapping("/blog-detail.html")
	public String blogDetail() {
		return "blog-detail";
	}
	@GetMapping("/contact.html")
	public String contact() {
		return "contact";
	}
	@GetMapping("/home-02.html")
	public String home02() {
		return "home-02";
	}
	@GetMapping("/home-03.html")
	public String home03() {
		return "home-03";
	}
	@GetMapping("/product.html")
	public String product() {
		return "product";
	}
	
	@GetMapping("/product-detail.html")
	public String productDetail(
			@RequestParam("product_id") int product_id,
			@RequestParam("category_id") int category_id, 
			Model model) {
		ProductVO productVO = productService.selectByProductId(product_id);
		model.addAttribute("vo", productVO);
		
		return "product-detail";
	}
	
	@GetMapping("/shoping-cart.html")
	public String shopingCart() {
		return "shoping-cart";
	}
}
