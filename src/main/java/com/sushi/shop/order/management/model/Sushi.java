package com.sushi.shop.order.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sushi {
	
	private int id;
	@JsonProperty("sushi_name")
	private String name;
	private int timeToMake;

}
