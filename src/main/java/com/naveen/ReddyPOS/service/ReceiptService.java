package com.naveen.ReddyPOS.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.naveen.ReddyPOS.constants.POSConstants;
import com.naveen.ReddyPOS.model.Products;
import com.naveen.ReddyPOS.model.Receipt;

@Service
public class ReceiptService {

	public Receipt printReceipt() {
		Receipt receipt = new Receipt();

		List<Products> products = addProducts();
		receipt.setProducts(products);

		Double subtotal = receipt.setSubtotal(getSubtotal(receipt.getProducts()));

		Double subtotalWithTaxAdded = receipt
				.setTaxAmountOnSubtotal((Double) ((receipt.getSubtotal() * POSConstants.MN_TAX) / 100));

		receipt.setTotal(totalCalculator(receipt, subtotal, subtotalWithTaxAdded));

		receipt.setStoreAddress(POSConstants.STORE_ADDRESS);
		receipt.setStorePhone(POSConstants.STORE_PHONE_NUMBER);
		receipt.setStoreName(POSConstants.STORE_NAME);

		return receipt;
	}

	private Double totalCalculator(Receipt receipt, Double subtotal, Double subtotalWithTaxAdded) {

		Double total = subtotal + subtotalWithTaxAdded;
		return total;
	}

	public Double getSubtotal(List<Products> products) {

		DoubleSummaryStatistics subtotal = products.stream().collect(Collectors.summarizingDouble(Products::getPrice));

		Double total = subtotal.getSum();
		return total;
	}

	private List<Products> addProducts() {
		List<Products> products = new ArrayList<Products>();

		Products product1 = new Products();
		product1.setProductName("Table");
		product1.setPrice(31.00);
		products.add(product1);

		Products product2 = new Products();
		product2.setProductName("Candy");
		product2.setPrice(.93);
		products.add(product2);

		return products;
	}

}
