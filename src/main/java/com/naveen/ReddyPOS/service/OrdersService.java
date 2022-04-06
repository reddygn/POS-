package com.naveen.ReddyPOS.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naveen.ReddyPOS.entity.ProductsDao;
import com.naveen.ReddyPOS.model.Products;
import com.naveen.ReddyPOS.repository.ProductsRepository;
import com.naveen.ReddyPOS.util.POSException;

@Service
public class OrdersService {

	private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	EmailService emailService;

	public List<Products> processOrders(List<Products> products) throws POSException {

		logger.info("Processing Orders");

		String transactionId = UUID.randomUUID().toString();

		for (Products product : products) {

			ProductsDao pDao = new ProductsDao();
			pDao.setTransactionId(transactionId);
			pDao.setCustomerEmail(product.getCustomerEmail());
			pDao.setPrice(product.getPrice());
			pDao.setProductName(product.getProductName());

			productsRepository.save(pDao);
		}

		try {
			if (transactionId != null && productsRepository.findAllByTransactionId(transactionId).size() > 0) {

				if (productsRepository.findAllByTransactionId(transactionId) != null) {

					emailService.sendEmail(transactionId);

					logger.info("Email Sent :: ", transactionId);

				}
			} else {

				throw new POSException("TransactionID Not Found");
			}

		} catch (POSException e) {

			logger.error("Error occured while sending email to the given transactionId :: ", e);
		}

		return products;
	}

}
