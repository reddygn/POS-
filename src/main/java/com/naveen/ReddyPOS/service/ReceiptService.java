package com.naveen.ReddyPOS.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

		receipt.setStoreAddress(POSConstants.STORE_ADDRESS);
		receipt.setStorePhone(POSConstants.STORE_PHONE_NUMBER);
		receipt.setStoreName(POSConstants.STORE_NAME);

		receipt.setProducts(getProducts());

		Double subtotal = receipt.setSubtotal(getSubtotal(receipt.getProducts()));
		BigDecimal bg1 = new BigDecimal((receipt.getSubtotal() * POSConstants.MN_TAX) / 100).setScale(2,
				RoundingMode.HALF_UP);

		Double subtotalWithTaxAdded = receipt.setTaxAmountOnSubtotal(bg1.doubleValue());

		BigDecimal bg = new BigDecimal(subtotalWithTaxAdded).setScale(2, RoundingMode.HALF_UP);

		receipt.setTotal(totalCalculator(receipt, subtotal, bg.doubleValue()));

		return receipt;
	}

	private Double totalCalculator(Receipt receipt, Double subtotal, Double subtotalWithTaxAdded) {

		Double total = subtotal + subtotalWithTaxAdded;
		return total;
	}

	public Double getSubtotal(List<Products> products) {

		Double subtotal = products.stream().collect(Collectors.summingDouble(Products::getPrice));

		return subtotal;
	}

	public List<Products> getProducts() {
		OrdersService ordersService = new OrdersService();

		return ordersService.orders;
	}

}
