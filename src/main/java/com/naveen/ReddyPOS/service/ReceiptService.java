package com.naveen.ReddyPOS.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naveen.ReddyPOS.constants.POSConstants;
import com.naveen.ReddyPOS.entity.ProductsDao;
import com.naveen.ReddyPOS.model.Products;
import com.naveen.ReddyPOS.model.Receipt;
import com.naveen.ReddyPOS.repository.ProductsRepository;

@Service
public class ReceiptService {

	@Autowired
	ProductsRepository productsRepository;

	public Receipt printReceipt(String transactionId) {
		Receipt receipt = new Receipt();

		receipt.setStoreAddress(POSConstants.STORE_ADDRESS);
		receipt.setStorePhone(POSConstants.STORE_PHONE_NUMBER);
		receipt.setStoreName(POSConstants.STORE_NAME);

		receipt.setProducts(getProducts(transactionId));

		Double subtotal = receipt.setSubtotal(getSubtotal(receipt.getProducts()));

		BigDecimal bigDecimal = new BigDecimal((receipt.getSubtotal() * POSConstants.MN_TAX) / 100).setScale(2,
				RoundingMode.HALF_UP);

		Double subtotalWithTaxAdded = receipt.setTaxAmountOnSubtotal(bigDecimal.doubleValue());

		receipt.setTotal(totalCalculator(subtotal, subtotalWithTaxAdded));

		return receipt;
	}

	private Double totalCalculator(Double subtotal, Double subtotalWithTaxAdded) {

		Double total = subtotal + subtotalWithTaxAdded;
		return total;
	}

	public Double getSubtotal(List<Products> products) {

		Double subtotal = products.stream().collect(Collectors.summingDouble(Products::getPrice));

		BigDecimal bigDecimal = new BigDecimal(subtotal).setScale(2, RoundingMode.HALF_UP);
		subtotal = bigDecimal.doubleValue();

		return subtotal;
	}

	public List<Products> getProducts(String transactionId) {

		List<ProductsDao> ProdsListDao = productsRepository.findAllByTransactionId(transactionId);
		
		//List<ProductsDao> ProdsListDao = productsRepository.findAllByCustomerEmail(customerEmail);

		List<Products> prodsDtoList = new ArrayList<Products>();
		for (ProductsDao productsDao : ProdsListDao) {
			Products product = new Products();

			product.setCustomerEmail(productsDao.getCustomerEmail());
			product.setPrice(productsDao.getPrice());
			product.setProductName(productsDao.getProductName());
			prodsDtoList.add(product);
		}

		return prodsDtoList;
	}

}
