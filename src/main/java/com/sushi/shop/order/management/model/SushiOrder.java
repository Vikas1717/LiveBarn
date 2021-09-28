package com.sushi.shop.order.management.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SushiOrder {
	
	int id;
	int sushiId;
	int statusId;
	Timestamp createdAt;

}
