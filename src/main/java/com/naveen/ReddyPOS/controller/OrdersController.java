package com.naveen.ReddyPOS.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.ReddyPOS.entity.ProductsDao;
import com.naveen.ReddyPOS.model.Products;
import com.naveen.ReddyPOS.repository.ProductsRepository;
import com.naveen.ReddyPOS.service.OrdersService;

@RestController
@RequestMapping("api/order")
public class OrdersController {

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	OrdersService ordersService;

	@PostMapping("/create")
	public List<Products> processOrders(@RequestBody List<Products> products) {

		for (Products products2 : products) {

			ProductsDao pDao = new ProductsDao();

			pDao.setCustomerEmail(products2.getCustomerEmail());
			pDao.setPrice(products2.getPrice());
			pDao.setProductName(products2.getProductName());

			productsRepository.save(pDao);
		}

		return products;
	}

}
