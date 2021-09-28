package com.sushi.shop.order.management.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushi.shop.order.management.dao.OrderDao;
import com.sushi.shop.order.management.dto.OrderResponseDTO;
import com.sushi.shop.order.management.model.Status;
import com.sushi.shop.order.management.model.Sushi;
import com.sushi.shop.order.management.model.SushiOrder;

@Service
public class OrderService {

	
	@Autowired
	private OrderDao orderDao;
	
	private List<Sushi> sushis;
	private List<Status> statuses;
	
	@PostConstruct
	public void loadData() {
		sushis=orderDao.loadSushi();
		statuses=orderDao.loadStatus();
	}
	
	public int getSushiId(String name) {
		System.out.println(sushis);
		return sushis.stream().filter(obj->obj.getName().equalsIgnoreCase(name)).findAny().get().getId();
	}
	public int getStatusId(String name) {
		System.out.println(statuses);
		return statuses.stream().filter(obj->obj.getName().equalsIgnoreCase(name)).findAny().get().getId();
	}
	public OrderResponseDTO createOrder(Sushi sushi) {
		
		SushiOrder sushiOrder=new SushiOrder();
		sushiOrder.setSushiId(getSushiId(sushi.getName()));
		sushiOrder.setStatusId(getStatusId("created"));
		sushiOrder.setCreatedAt(new Timestamp(new Date().getTime()));
		int status=orderDao.createOrder(sushiOrder);
		OrderResponseDTO dto=new OrderResponseDTO();
		if(status>0) {
			sushiOrder.setId(status);
			dto.setOrder(sushiOrder);
			dto.setCode(0);
			dto.setMsg("Order Created");
		}
		return dto;
		
	}
	
	public OrderResponseDTO updateOrder(Integer orderId,String statusVal) {
		System.out.println("updat eto id :::"+getStatusId(statusVal));
		int status= orderDao.updateOrder(orderId, getStatusId(statusVal));
		System.out.println("sttaus:::::"+status);
		OrderResponseDTO dto=new OrderResponseDTO();
		if(status>=0) {
			dto.setCode(0);
			dto.setMsg("Order cancelled");
		}
		return dto;
	}

	public List<Map<String, Object>> getOrdersByStatus() {
		return orderDao.getOderbyStatus();
		
	}
	
	
	
}
