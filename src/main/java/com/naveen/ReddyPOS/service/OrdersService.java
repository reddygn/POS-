package com.naveen.ReddyPOS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naveen.ReddyPOS.entity.ProductsDao;
import com.naveen.ReddyPOS.model.Products;
import com.naveen.ReddyPOS.repository.ProductsRepository;

@Service
public class OrdersService {

	@Autowired
	ProductsRepository productsRepository;

	public List<Products> processOrders(List<Products> products) {

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
