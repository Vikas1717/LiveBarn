package com.sushi.shop.order.management.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.sushi.shop.order.management.model.Status;
import com.sushi.shop.order.management.model.Sushi;
import com.sushi.shop.order.management.model.SushiOrder;

@Repository
public class OrderDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Sushi> loadSushi(){
		
		List<Map<String, Object>> records=jdbcTemplate.queryForList("select * from sushi");
		List<Sushi> sushis=new ArrayList<>();
		for(Map<String, Object> map:records) {
			
			Sushi sushi=new Sushi();
			sushi.setId((int) map.get("id"));
			sushi.setName((String) map.get("name"));
			sushi.setTimeToMake((int) map.get("time_to_make"));
			sushis.add(sushi);
			
			
		}
		return sushis;
	}
	
	
	
	public List<Status> loadStatus(){
		List<Map<String, Object>> records=jdbcTemplate.queryForList("select * from status");
		List<Status> statuses=new ArrayList<>();
		for(Map<String, Object> map:records) {
			Status status=new Status();
			status.setId((int) map.get("id"));
			status.setName((String) map.get("name"));
			statuses.add(status);
		}
		return statuses;
		
	}
	
	public int updateOrder(Integer sushiId,Integer statusId) {
		return jdbcTemplate.update("update sushi_order set status_id=? where sushi_id=?",statusId,sushiId);
	}
	public int createOrder(SushiOrder order) {
		int status= jdbcTemplate.update("insert into sushi_order(status_id,sushi_id,createdAt) values(?,?,?)",order.getStatusId(),
				order.getSushiId(),order.getCreatedAt());
		if(status>0) {
			return jdbcTemplate.queryForObject("select max(id) from sushi_order", Integer.class);
		}else {
			return 0;
		}
	}



	public List<Map<String, Object>> getOderbyStatus() {
		
		return jdbcTemplate.queryForList("select * from sushi_order");
	}

}
