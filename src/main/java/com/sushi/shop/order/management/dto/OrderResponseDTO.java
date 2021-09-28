package com.sushi.shop.order.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sushi.shop.order.management.model.SushiOrder;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class OrderResponseDTO {

	private SushiOrder order;
	private int code;
	private String msg;
}
