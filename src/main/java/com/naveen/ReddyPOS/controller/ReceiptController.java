package com.naveen.ReddyPOS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.ReddyPOS.model.Receipt;
import com.naveen.ReddyPOS.service.ReceiptService;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

	@Autowired
	ReceiptService receiptService;

	@GetMapping("/print/{transactionId}")
	public Receipt printReceipt(@PathVariable String transactionId) {

		return receiptService.printReceipt(transactionId);
	}
}
