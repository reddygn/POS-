package com.naveen.ReddyPOS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.ReddyPOS.model.Products;
import com.naveen.ReddyPOS.service.OrdersService;

@RestController
@RequestMapping("api/order")
public class OrdersController {

	@Autowired
	OrdersService ordersService;

	@PostMapping("/create")
	public List<Products> processOrders(@RequestBody List<Products> products) {

		return ordersService.processOrders(products);
	}

}
