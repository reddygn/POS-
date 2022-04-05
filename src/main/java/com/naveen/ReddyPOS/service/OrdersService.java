package com.naveen.ReddyPOS.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naveen.ReddyPOS.entity.ProductsDao;
import com.naveen.ReddyPOS.model.Products;
import com.naveen.ReddyPOS.repository.ProductsRepository;

@Service
public class OrdersService {

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	EmailService emailService;

	public List<Products> processOrders(List<Products> products) {

		String transactionId = UUID.randomUUID().toString();

		for (Products product : products) {

			ProductsDao pDao = new ProductsDao();
			pDao.setTransactionId(transactionId);
			pDao.setCustomerEmail(product.getCustomerEmail());
			pDao.setPrice(product.getPrice());
			pDao.setProductName(product.getProductName());

			productsRepository.save(pDao);
		}

		emailService.sendEmail(transactionId);

		return products;
	}

}
