package com.sushi.shop.order.management.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushi.shop.order.management.dto.OrderResponseDTO;
import com.sushi.shop.order.management.model.Sushi;
import com.sushi.shop.order.management.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/orders")
	public OrderResponseDTO createOrder(@RequestBody Sushi sushi) {
		return orderService.createOrder(sushi);
	}
	
	@DeleteMapping("/orders/{order_id}")
	public OrderResponseDTO cancelOrder(@PathVariable Integer order_id) {
		return orderService.updateOrder(order_id,"cancelled");
	}
	@PutMapping("/orders/{order_id}/pause")
	public OrderResponseDTO pauseOrder(@PathVariable Integer order_id) {
		OrderResponseDTO dto= orderService.updateOrder(order_id,"paused");
		dto.setMsg("Order paused");
		return dto;
	}
	
	@PutMapping("/orders/{order_id}/resume")
	public OrderResponseDTO resumeOrder(@PathVariable  Integer order_id) {
		OrderResponseDTO dto= orderService.updateOrder(order_id,"in-progress");
		dto.setMsg("Order resumed");
		return dto;
		
	}
	
	@GetMapping("/orders/status")
	public List<Map<String, Object>> getOrdersByStatus(){
		return orderService.getOrdersByStatus();
		
	}
}
