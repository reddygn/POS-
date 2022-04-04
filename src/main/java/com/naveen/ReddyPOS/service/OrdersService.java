package com.naveen.ReddyPOS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.naveen.ReddyPOS.model.Products;

@Service
public class OrdersService {

	static List<Products> orders = new ArrayList<Products>();

	public List<Products> processOrders(List<Products> products) {

		orders.addAll(products);

		return products;
	}

}
